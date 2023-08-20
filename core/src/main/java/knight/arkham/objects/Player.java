package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {
    public static int score;
    private final float speed;

    public Player(Rectangle bounds) {
        super(bounds, "images/players.png", "drop.wav");
        speed = 500;
        score = 0;
    }

    public void update(float deltaTime) {

        boolean hasLeftWallCollision = actualBounds.x < 483;
        boolean hasRightWallCollision = actualBounds.x > 1370;

        if (!hasRightWallCollision && Gdx.input.isKeyPressed(Input.Keys.D))
            actualBounds.x += speed * deltaTime;

        else if (!hasLeftWallCollision && Gdx.input.isKeyPressed(Input.Keys.A))
            actualBounds.x -= speed * deltaTime;
    }

    public void hitTheBall() {

        collisionSound.play(0.6f);
    }
}
