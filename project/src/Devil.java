import java.security.cert.Extension;
import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;


public class Devil extends Miners{

    public static final String QUAKE_KEY = "quake";
    public static final String DEVIL_KEY = "devil";

    public Devil(
            String id,
            Point position,
            List<PImage> images,
            int resourceLimit,
            int resourceCount,
            int actionPeriod,
            int animationPeriod)
    {
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//        this.resourceLimit = resourceLimit;
//        this.resourceCount = resourceCount;
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);
    }

// Devils have similar function to oreblobs. They smash veins faster than oreblobs can and turns oreblobs into Devils

    @Override
    public void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler) {

        Optional<Entity> DevilTarget =
                world.findNearest(this.getPosition(), VEIN.class);
        long nextPeriod = this.getActionPeriod();

        if (DevilTarget.isPresent()) {
            Point tgtPos = DevilTarget.get().getPosition();

            if (moveToDevil(world, DevilTarget.get(), scheduler, imageStore)) {
                Entity quake = Factory.createQuake(tgtPos,
                        Functions.getImageList(imageStore, QUAKE_KEY));

                world.addEntity(quake);
                nextPeriod += this.getActionPeriod();
                ((QUAKE) quake).scheduleActions(scheduler, world, imageStore);
            }
        }

        scheduler.scheduleEvent(this,
                Factory.createActivityAction(this, world, imageStore),
                nextPeriod);


    }


    public boolean moveToDevil(
            WorldModel world,
            Entity target,
            EventScheduler scheduler,
            ImageStore imageStore)
    {

        if (this.adjacent(this.getPosition(), target.getPosition())) {
            world.removeEntity(target);
            scheduler.unscheduleAllEvents(target);

            if (world.getOccupant(target.getPosition()).isPresent()) {
                transformDevil(world, target, scheduler, imageStore);
                return true;
            }
            return true;

        }
        else {
            Point nextPos = this.nextPosition(world, target.getPosition());

            if (!this.getPosition().equals(nextPos)) {
                Optional<Entity> occupant = world.getOccupant(nextPos);
                if (occupant.isPresent()) {
                    scheduler.unscheduleAllEvents(occupant.get());
                }

                world.moveEntity(this, nextPos);
            }
            return false;
        }
    }

    public void transformDevil(WorldModel world,
                               Entity target,
                               EventScheduler scheduler,
                               ImageStore imageStore) {
        ORE_BLOB newDevil = (ORE_BLOB) world.getOccupant(target.getPosition()).get();
        //!!!!!!!!!;
        newDevil.setImageIndex(5);
        newDevil.setImages(Functions.getImageList(imageStore, DEVIL_KEY));
        newDevil.setAnimationPeriod(7);
        newDevil.setActionPeriod(7);

    }


    public Point nextPosition(
            WorldModel world, Point destPos) {

//        PathingStrategy strategy = new SingleStepPathingStrategy();
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



}



















