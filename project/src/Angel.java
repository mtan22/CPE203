import java.security.cert.Extension;
import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;

public class Angel extends Miners{

    public static final String ANGEL_KEY = "angel";

    public Angel(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod) {
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

    public Point nextPosition(
            WorldModel world, Point destPos) {

//      PathingStrategy strategy = new SingleStepPathingStrategy();
        PathingStrategy strategy = new AStarPathingStrategy();
        List<Point> points = strategy.computePath(getPosition(), destPos,
                p -> (world.withinBounds(p) && !world.isOccupied(p)),
                (point, point2) -> (this.adjacent(point, point2)),
                PathingStrategy.CARDINAL_NEIGHBORS);
        if (points.size() != 0) {
            return points.get(0);
        }
        else {
            return getPosition();
        }

    }

    public void execute(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> blobTarget =
                world.findNearest(this.getPosition(), Devil.class) ;//target
        long nextPeriod = this.getActionPeriod();

        if (blobTarget.isPresent()) {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToAngel(this, world, blobTarget.get(), scheduler)) {
                Entity quake = Factory.createQuake(tgtPos,
                        Functions.getImageList(imageStore, ANGEL_KEY));

                world.addEntity(quake);
                nextPeriod += this.getActionPeriod();
                ((QUAKE) quake).scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                nextPeriod);
    }

    public boolean moveToAngel(
            Entity blob,
            WorldModel world,
            Entity target,
            EventScheduler scheduler)
    {
        if (this.adjacent(this.getPosition(), target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);
            return true;
        }
        else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(blob, nextPos);
            }
            return false;
        }
    }




}


