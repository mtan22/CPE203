import processing.core.PImage;

import java.util.List;
import java.util.Random;
import java.util.Optional;

public abstract class AnimatedEntity extends ActiveEntity {

    public AnimatedEntity(String id,
                          Point position,
                          List<PImage> images,
                          int resourceLimit,
                          int resourceCount,
                          int actionPeriod,
                          int animationPeriod) {
//        super(id, position, images, actionPeriod, animationPeriod);
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }


    @Override
    public void nextImage() {
        super.nextImage();
    }
}
