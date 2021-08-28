import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;


//public class OBSTACLE implements Entity{
public class OBSTACLE extends AbstractEntity{

//    private final String id;
//    private Point position;
//    private final List<PImage> images;
//    private int imageIndex;
//    private int resourceLimit;
//    private int resourceCount;
//    private final int actionPeriod;
//    private final int animationPeriod;

    public static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;


    public OBSTACLE(
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
//
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
