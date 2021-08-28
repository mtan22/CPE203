import processing.core.PImage;

import java.util.List;

public class Factory {
    public static Animation createAnimationAction(Entity entity, int repeatCount) {
//        return new Action(ActionKind.ANIMATION, entity, null, null,
//                          repeatCount);
        return new Animation(entity, null, null,
                repeatCount);
    }

//    public static Action createActivityAction(
//            Entity entity, WorldModel world, ImageStore imageStore)
//    {
//        return new Activity(entity, world, imageStore, 0);
//    }

    public static Activity createActivityAction(
            Entity entity, WorldModel world, ImageStore imageStore)
    {
        return new Activity(entity, world, imageStore, 0);
    }

    public static Entity createBlacksmith(
            String id, Point position, List<PImage> images)
    {
//        return new Entity(EntityKind.BLACKSMITH, id, position, images, 0, 0, 0,
//                          0);
        return new BLACKSMITH(id, position, images, 0, 0, 0,
                0);
    }

    public static MINER_FULL createMinerFull(
            String id,
            int resourceLimit,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
//        return new Entity(EntityKind.MINER_FULL, id, position, images,
//                          resourceLimit, resourceLimit, actionPeriod,
//                          animationPeriod);
        return new MINER_FULL(id, position, images,
                resourceLimit, resourceLimit, actionPeriod,
                animationPeriod);
    }

    public static MINER_NOT_FULL createMinerNotFull(
            String id,
            int resourceLimit,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
//        return new Entity(EntityKind.MINER_NOT_FULL, id, position, images,
//                          resourceLimit, 0, actionPeriod, animationPeriod);
        return new MINER_NOT_FULL(id, position, images,
                resourceLimit, 0, actionPeriod, animationPeriod);
    }

    public static Entity createObstacle(
            String id, Point position, List<PImage> images)
    {
//        return new Entity(EntityKind.OBSTACLE, id, position, images, 0, 0, 0,
//                          0);
        return new OBSTACLE(id, position, images, 0, 0, 0,
                0);
    }

    public static Entity createOre(
            String id, Point position, int actionPeriod, List<PImage> images)
    {
//        return new Entity(EntityKind.ORE, id, position, images, 0, 0,
//                          actionPeriod, 0);
        return new ORE(id, position, images, 0, 0,
                actionPeriod, 0);
    }

    public static Entity createOreBlob(
            String id,
            Point position,
            int actionPeriod,
            int animationPeriod,
            List<PImage> images)
    {
//        return new Entity(EntityKind.ORE_BLOB, id, position, images, 0, 0,
//                          actionPeriod, animationPeriod);
        return new ORE_BLOB(id, position, images, 0, 0,
                actionPeriod, animationPeriod);
//        return new ExtensionPosition(id, position, images, 0, 0,
//                actionPeriod, animationPeriod);
    }



    public static Entity createDevil(String id,
                                     Point position,
                                     List<PImage> images,
                                     int resourceLimit,
                                     int resourceCount,
                                     int actionPeriod,
                                     int animationPeriod)
    {
        return new Devil(id, position, images, 0, 0,
                actionPeriod, animationPeriod);
    }

    public static Entity createAngel(String id,
                                     Point position,
                                     List<PImage> images,
                                     int resourceLimit,
                                     int resourceCount,
                                     int actionPeriod,
                                     int animationPeriod)
    {
        return new Angel(id, position, images, 0, 0,
                actionPeriod, animationPeriod);
    }




    public static Entity createQuake(
            Point position, List<PImage> images)
    {
//        return new Entity(EntityKind.QUAKE, Functions.QUAKE_ID, position, images, 0, 0,
//                          Functions.QUAKE_ACTION_PERIOD, Functions.QUAKE_ANIMATION_PERIOD);
        return new QUAKE(Functions.QUAKE_ID, position, images, 0, 0,
                Functions.QUAKE_ACTION_PERIOD, Functions.QUAKE_ANIMATION_PERIOD);
    }

    public static Entity createVein(
            String id, Point position, int actionPeriod, List<PImage> images)
    {
//        return new Entity(EntityKind.VEIN, id, position, images, 0, 0,
//                          actionPeriod, 0);
        return new VEIN(id, position, images, 0, 0,
                actionPeriod, 0);
    }
}
