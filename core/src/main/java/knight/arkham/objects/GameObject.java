package knight.arkham.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.helpers.AssetsHelper;

public abstract class GameObject {
    protected final Rectangle bounds;
    protected final Texture sprite;
    protected final Sound collisionSound;

    protected GameObject(Rectangle bounds, String spritePath, String soundPath) {
        this.bounds = bounds;
        sprite = new Texture(spritePath);
        collisionSound = AssetsHelper.loadSound(soundPath);
    }

    public void draw(Batch batch) {

        batch.draw(sprite, bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public Rectangle getBounds() {return bounds;}

    public void dispose() {
        sprite.dispose();
        collisionSound.dispose();
    }
}
