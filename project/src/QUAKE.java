import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;


//public class QUAKE implements ExtensionAnimation{
public class QUAKE extends AnimatedEntity{

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

    public QUAKE(
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

//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//
////            case QUAKE:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this, Factory.createAnimationAction(this,
//                        QUAKE_ANIMATION_REPEAT_COUNT),
//                        this.getAnimationPeriod());
////                break;
//
//    }

    public void execute(
            WorldModel world,
            ImageStore imageStore,
            EventScheduler scheduler)
    {
        scheduler.unscheduleAllEvents(this);
        world.removeEntity(this);
    }

//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }

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
//        //return ((Entity)entity).images.get(((Entity)entity).imageIndex);
//        return this.images.get(this.imageIndex);
//    }

}
