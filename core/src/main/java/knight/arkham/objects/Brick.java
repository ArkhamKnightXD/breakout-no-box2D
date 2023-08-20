package knight.arkham.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.scenes.Hud;

public class Brick extends GameObject {
    private boolean isDestroyed;
    private boolean setToDestroy;
    private final int brickPoints;

    public Brick(int positionX, int positionY, String spritePath, int brickPoints) {
        super(
            new Rectangle(
                515 + positionX,
                900 - positionY, 64, 20
            ), spritePath, "okay.wav"
        );
        this.brickPoints = brickPoints;
    }

    public void update() {

        if (setToDestroy && !isDestroyed)
            destroyBrick();
    }

    private void destroyBrick() {

        isDestroyed = true;
    }

    @Override
    public void draw(Batch batch) {

        if (!isDestroyed)
            batch.draw(sprite, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public void hitByTheBall() {
        setToDestroy = true;

        Hud.addScore(brickPoints);

        collisionSound.play(0.6f);
    }
}
