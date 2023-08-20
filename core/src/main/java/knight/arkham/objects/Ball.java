package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import knight.arkham.helpers.AssetsHelper;
import knight.arkham.scenes.Hud;

public class Ball extends GameObject {
    public static int livesQuantity;
    private final float speed;
    private final Vector2 velocity;
    private final Vector2 initialPosition;
    private final Sound boundariesCollisionSound;

    public Ball(Rectangle bounds) {
        super(bounds, "images/ball.png", "fall.wav");
        speed = 250;
        velocity = new Vector2(getRandomDirection(), -1);
        livesQuantity = 2;
        initialPosition = new Vector2(bounds.x, bounds.y);
        boundariesCollisionSound = AssetsHelper.loadSound("magic.wav");
    }

    private float getRandomDirection(){

        return (Math.random() < 0.5) ? 1 : -1;
    }

    private void resetBallPosition(){
        velocity.set(getRandomDirection(), -1);

        actualBounds.x = initialPosition.x;
        actualBounds.y = initialPosition.y;
    }

    public void update(float deltaTime){

        actualBounds.x += velocity.x * speed * deltaTime;
        actualBounds.y += velocity.y * speed * deltaTime;

        if (livesQuantity > -1 && actualBounds.y < 200){

            if (livesQuantity > 0){
                collisionSound.play(0.5f);
                resetBallPosition();
            }

            Hud.takeAvailableBalls();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R))
            resetBallPosition();

        manageScreenBoundariesCollision();
    }

    private void manageScreenBoundariesCollision() {

        boolean hasRightCollision = actualBounds.x > 1425;
        boolean hasLeftCollision = actualBounds.x < 480;
        boolean hasTopCollision = actualBounds.y > 930;

        if (hasLeftCollision || hasRightCollision){

            reverseVelocityX();
            boundariesCollisionSound.play();
        }

        else if (hasTopCollision) {
            reverseVelocityY();
        }
    }

    public void hasPlayerCollision(Player player){

        boolean hasCollision = actualBounds.overlaps(player.getBounds());

        if (hasCollision){

            player.hitTheBall();
            reverseVelocityY();
        }
    }

    public void reverseVelocityX(){velocity.x *= -1;}

    public void reverseVelocityY(){velocity.y *= -1;}
}
