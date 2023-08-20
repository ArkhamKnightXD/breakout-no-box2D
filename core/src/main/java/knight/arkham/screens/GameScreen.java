package knight.arkham.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import knight.arkham.Breakout;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.helpers.GameDataHelper;
import knight.arkham.objects.*;
import knight.arkham.scenes.Hud;
import knight.arkham.scenes.PauseMenu;

public class GameScreen extends ScreenAdapter {
    private final Breakout game;
    private final OrthographicCamera camera;
    public SpriteBatch batch;
    private final Hud hud;
    private final PauseMenu pauseMenu;
    private final Player player;
    private final Ball ball;
    private final Array<Brick> bricks;
    private final Sound winSound;
    public static boolean isGamePaused;

    public GameScreen() {

        game = Breakout.INSTANCE;

        camera = game.camera;

        batch = new SpriteBatch();

        player = new Player(new Rectangle(925, 350, 64, 16));
        ball = new Ball(new Rectangle(925, 700, 20, 20));

        winSound = AssetsHelper.loadSound("win.wav");

        bricks = createBricks();
        hud = new Hud();
        pauseMenu = new PauseMenu();

        isGamePaused = false;
    }

    private Array<Brick> createBricks() {
        int positionX;
        int positionY = 0;
        int brickPoints = 8;
        String spritePath;

        Array<Brick> temporalBricks = new Array<>();

        for (int i = 0; i < 8; i++) {

            positionX = 0;

            if (i % 2 == 0)
                spritePath = "images/light-blue-brick.png";

            else
                spritePath = "images/purple-brick.png";

            for (int j = 0; j < 15; j++) {

                temporalBricks.add(new Brick(positionX, positionY, spritePath, brickPoints));
                positionX += 64;
            }

            brickPoints--;
            positionY += 20;
        }

        return temporalBricks;
    }

    @Override
    public void resize(int width, int height) {
        game.viewport.update(width, height);
    }

    private void update(float deltaTime) {

        player.update(deltaTime);
        ball.update(deltaTime);

        ball.hasPlayerCollision(player);

        for (Brick brick : bricks){
            brick.update();
            brick.hasBallCollision(ball);
        }
    }

    @Override
    public void render(float deltaTime) {

        ScreenUtils.clear(0, 0, 0, 0);

        if (!isGamePaused) {
            update(deltaTime);
            draw();
        } else {

            pauseMenu.stage.act();
            pauseMenu.stage.draw();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.F1))
            isGamePaused = !isGamePaused;

        setGameOverScreen();
    }

    private void setGameOverScreen() {

        if (Player.score == 540) {

            winSound.play();
            GameDataHelper.saveHighScore();
            game.setScreen(new MainMenuScreen());
        } else if (Ball.livesQuantity < 0) {
            GameDataHelper.saveHighScore();
            game.setScreen(new MainMenuScreen());
        }
    }

    private void draw() {

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        for (Brick brick : bricks)
            brick.draw(batch);

        player.draw(batch);

        ball.draw(batch);

        batch.end();

        hud.stage.draw();
    }

    @Override
    public void hide() {

        dispose();
    }

    @Override
    public void dispose() {

        player.dispose();
        ball.dispose();
        hud.dispose();
        pauseMenu.dispose();
        winSound.dispose();
        batch.dispose();

        for (Brick brick : bricks)
            brick.dispose();
    }
}
