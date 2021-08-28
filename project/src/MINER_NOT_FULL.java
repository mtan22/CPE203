import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;


//public class MINER_NOT_FULL implements Extension{
public class MINER_NOT_FULL extends Miners{

//    private final String id;
//    private Point position;
//    private List<PImage> images;
//    private int imageIndex;
//    private int resourceLimit;
//    private int resourceCount;
//    private final int actionPeriod;
//    private final int animationPeriod;

    public MINER_NOT_FULL(
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
//
//    //GETTERS
//
//    public Point getPosition() {
//        return this.position;
//    }
//
//    public List<PImage> getImages() {
//        return images;
//    }
//
//
//    //SETTERS
//
//    public void setPosition(Point p) {
//        this.position = p;
//    }
//
//    public void setImages(List<PImage> images) {
//        this.images = images;
//    }
//
//    public int getResourceLimit() {
//        return resourceLimit;
//    }
//
//    public int getResourceCount() {
//        return resourceCount;
//    }

    //FUNCTIONS

//    public int getAnimationPeriod() {
////        switch (this.getEntityKind()) {
////            case MINER_FULL:
////            case MINER_NOT_FULL:
////            case ORE_BLOB:
////            case QUAKE:
////                return this.animationPeriod;
////            default:
////                throw new UnsupportedOperationException(
////                        String.format("getAnimationPeriod not supported for %s",
////                                this.getEntityKind()));
////        }
//        return this.animationPeriod;
//    }
//
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }

    public void execute(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> notFullTarget =
                world.findNearest(this.getPosition(), ORE.class);

        if (!notFullTarget.isPresent() || !this.moveToNotFull(world,
                notFullTarget.get(),
                scheduler)
                || !this.transformNotFull(world, scheduler, imageStore))
        {
            scheduler.scheduleEvent(this,
                    Factory.createActivityAction(this, world, imageStore),
                    this.getActionPeriod());
        }
    }

    public boolean transformNotFull(
            WorldModel world,
            EventScheduler scheduler,
            ImageStore imageStore)
    {
        if (this.resourceCount >= this.resourceLimit) {
            ActiveEntity miner = Factory.createMinerFull(this.getId(), this.resourceLimit,
                    this.getPosition(), this.getActionPeriod(),
                    this.getAnimationPeriod(),
                    this.getImages());

//            Extension miner = Factory.createMinerFull(this.id, this.position, this.images, this.resourceLimit, this.resourceLimit, this.actionPeriod,
//                    this.animationPeriod);

//            return new MINER_FULL(id, position, images,
//                    resourceLimit, resourceLimit, actionPeriod,
//                    animationPeriod);

            world.removeEntity(this);
            scheduler.unscheduleAllEvents(this);

            world.addEntity(miner);
            ((MINER_FULL) miner).scheduleActions(scheduler, world, imageStore);

            return true;
        }

        return false;
    }

    public boolean moveToNotFull(
            WorldModel world,
            Entity target,
            EventScheduler scheduler)
    {
        if (this.adjacent(this.getPosition(), target.getPosition())) {
            this.resourceCount += 1;
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

                world.moveEntity(this, nextPos);
            }
            return false;
        }

    }

//    public Point nextPosition(
//            WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - this.getPosition().x);
//        int vert = Integer.signum(destPos.y - this.getPosition().y);
//
//        Point newPos = new Point(this.getPosition().x + horiz, this.getPosition().y + vert);
//
//        if (world.isOccupied(newPos)) {
//
//            newPos = new Point(this.getPosition().x, this.getPosition().y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = new Point(this.getPosition().x + horiz, this.getPosition().y);
//                if (horiz == 0 || world.isOccupied(newPos))
//                {
//                    newPos = this.getPosition();
//                }
//            }
//
//        }
//
//        return newPos;
//    }

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

//    public PImage getCurrentImage(Object entity) {
//        //return ((Entity)entity).images.get(((Entity)entity).imageIndex);
//        return this.images.get(this.imageIndex);
//    }

//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
////        switch (this.kind) {
//
//
////            case MINER_NOT_FULL:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Factory.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
////                break;
////        }
//
//    }

//    public boolean adjacent(Point p1, Point p2) {
//        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y
//                && Math.abs(p1.x - p2.x) == 1);
//    }

}
