package knight.arkham.objects.structures;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import knight.arkham.helpers.AssetsHelper;

public abstract class Structure {
    protected final Rectangle actualBounds;
    private final Sound hitSound;

    protected Structure(Rectangle bounds) {
        actualBounds = bounds;
        hitSound = AssetsHelper.loadSound("magic.wav");
    }

    public void hitByTheBall() {

        hitSound.play();
    }

    public void dispose(){
        hitSound.dispose();
    }
}
