import processing.core.PImage;

import java.util.List;
import java.util.Random;
import java.util.Optional;

abstract public class ActiveEntity extends AbstractEntity{

    public ActiveEntity(String id,
                        Point position,
                        List<PImage> images,
                        int resourceLimit,
                        int resourceCount,
                        int actionPeriod,
                        int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public void scheduleActions(
            EventScheduler scheduler,
            WorldModel world,
            ImageStore imageStore) {
        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                this.getActionPeriod());
        scheduler.scheduleEvent(this,
                Factory.createAnimationAction(this, 0),
                this.getAnimationPeriod());
    }

//    public void execute(WorldModel world,
//                        ImageStore imageStore,
//                        EventScheduler scheduler) {
//
//    }

    public abstract void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler);


}
