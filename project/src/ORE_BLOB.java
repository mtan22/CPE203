import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;


//public class ORE_BLOB implements ExtensionAnimation{
public class ORE_BLOB extends Miners{

    public static final Random rand = new Random();

    public static final String BLOB_KEY = "blob";
    public static final String BLOB_ID_SUFFIX = " -- blob";
    public static final int BLOB_PERIOD_SCALE = 4;
    public static final int BLOB_ANIMATION_MIN = 50;
    public static final int BLOB_ANIMATION_MAX = 150;

    public static final String ORE_ID_PREFIX = "ore -- ";
    public static final int ORE_CORRUPT_MIN = 9000;
    public static final int ORE_CORRUPT_MAX = 20000;
    public static final int ORE_REACH = 1;

    public static final String QUAKE_KEY = "quake";
    public static final String QUAKE_ID = "quake";
    public static final int QUAKE_ACTION_PERIOD = 1100;
    public static final int QUAKE_ANIMATION_PERIOD = 100;
    public static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;

    public static final String ORE_KEY = "ore";
    public static final int ORE_NUM_PROPERTIES = 5;
    public static final int ORE_ID = 1;
    public static final int ORE_COL = 2;
    public static final int ORE_ROW = 3;
    public static final int ORE_ACTION_PERIOD = 4;

//    private final String id;
//    private Point position;
//    private final List<PImage> images;
//    private int imageIndex;
//    private int resourceLimit;
//    private int resourceCount;
//    private final int actionPeriod;
//    private final int animationPeriod;

    public ORE_BLOB(
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

//    //GETTERS
//
//    public Point getPosition() {
//        return this.position;
//    }
//
//
//
//    //SETTERS
//
//    public void setPosition(Point p) {
//        this.position = p;
//    }


    //FUNCTIONS

    public void execute(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        Optional<Entity> blobTarget =
                world.findNearest(this.getPosition(), VEIN.class);
        long nextPeriod = this.getActionPeriod();

        if (blobTarget.isPresent()) {
            Point tgtPos = blobTarget.get().getPosition();

            if (moveToOreBlob(this, world, blobTarget.get(), scheduler)) {
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

    public boolean moveToOreBlob(
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

//    public Point nextPosition(
//            WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - this.getPosition().x);
//        Point newPos = new Point(this.getPosition().x + horiz, this.getPosition().y);
//
//        Optional<Entity> occupant = world.getOccupant(newPos);
//
//        if (horiz == 0 || (occupant.isPresent() && !(occupant.get().getClass()
//                == ORE.class)))
//        {
//            int vert = Integer.signum(destPos.y - this.getPosition().y);
//            newPos = new Point(this.getPosition().x, this.getPosition().y + vert);
//            occupant = world.getOccupant(newPos);
//
//            if (vert == 0 || (occupant.isPresent() && !(occupant.get().getClass()
//                    == ORE.class)))
//            {
//                newPos = this.getPosition();
//            }
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

//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//
////            case ORE_BLOB:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Factory.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
////                break;
//
//    }


//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
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

//    public PImage getCurrentImage(Object entity) {
//        //return this.images.get(((Entity)entity).imageIndex);
//        return this.images.get(this.imageIndex);
//    }

//    public boolean adjacent(Point p1, Point p2) {
//        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y
//                && Math.abs(p1.x - p2.x) == 1);
//    }


}
