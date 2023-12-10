package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.scenes.Hud;

public class Brick extends GameObject {
    private boolean isDestroyed;
    private final int brickPoints;

    public Brick(int positionX, int positionY, String spritePath, int brickPoints) {
        super(
            new Rectangle(
                483 + positionX, 900 - positionY, 64, 20
            ), spritePath, "okay.wav"
        );
        this.brickPoints = brickPoints;
    }

    @Override
    public void draw(Batch batch) {

        if (!isDestroyed)
            super.draw(batch);
    }

    public void checkCollisionWithBall(Ball ball){

        boolean hasCollide = actualBounds.overlaps(ball.actualBounds);

        if (!isDestroyed && hasCollide){

            ball.reverseVelocityY();

            isDestroyed = true;

            Hud.addScore(brickPoints);

            collisionSound.play(0.6f);
        }
    }
}
