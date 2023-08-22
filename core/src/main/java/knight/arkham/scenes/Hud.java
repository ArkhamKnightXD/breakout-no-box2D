package knight.arkham.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import knight.arkham.Breakout;
import knight.arkham.objects.Ball;
import knight.arkham.objects.Player;

public class Hud {
    public final Stage stage;
    private static Label scoreLabel;
    private static Label livesLabel;

    public Hud() {

        Breakout game = Breakout.INSTANCE;

        Viewport viewport = new ExtendViewport(game.screenWidth, game.screenHeight);

        stage = new Stage(viewport);

        Table table = new Table();

        table.top();

        table.setFillParent(true);

        scoreLabel = new Label("0", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label("2", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label worldLabel = new Label("LEVEL 01", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreLabel).expandX().padTop(10);
        table.add(livesLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public static void addScore(int brickPoints) {

        Player.score += brickPoints;

        scoreLabel.setText(Player.score);
    }

    public static void takeAvailableBalls() {

        Ball.livesQuantity -= 1;

        livesLabel.setText(Ball.livesQuantity);
    }

    public void dispose(){
        stage.dispose();
    }
}
