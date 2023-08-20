package knight.arkham.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import knight.arkham.scenes.Hud;

public class Ball extends GameObject {
    public static int livesQuantity;
    private final float speed;
    private final Vector2 velocity;
    private final Vector2 initialPosition;

    public Ball(Rectangle bounds) {
        super(bounds, "images/ball.png", "fall.wav");
        speed = 250;
        velocity = new Vector2(getRandomDirection(), -1);
        livesQuantity = 2;
        initialPosition = new Vector2(bounds.x, bounds.y);
    }

    private float getRandomDirection(){

        return (Math.random() < 0.5) ? 1 : -1;
    }

    private void resetBallPosition(){
        velocity.set(getRandomDirection(), -1);

        bounds.x = initialPosition.x;
        bounds.y = initialPosition.y;
    }

    public void update(float deltaTime){

        bounds.x += velocity.x * speed * deltaTime;
        bounds.y += velocity.y * speed * deltaTime;

        if (livesQuantity > -1 && bounds.y < 200){

            if (livesQuantity > 0){
                collisionSound.play(0.5f);
                resetBallPosition();
            }

            Hud.takeAvailableBalls();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.R))
            resetBallPosition();
    }

    public void reverseVelocityX(){velocity.x *= -1;}

    public void reverseVelocityY(){velocity.y *= -1;}
}
