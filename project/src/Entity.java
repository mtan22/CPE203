import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;

//final class Entity
//{
//
//    public static final Random rand = new Random();
//
//    public static final String BLOB_KEY = "blob";
//    public static final String BLOB_ID_SUFFIX = " -- blob";
//    public static final int BLOB_PERIOD_SCALE = 4;
//    public static final int BLOB_ANIMATION_MIN = 50;
//    public static final int BLOB_ANIMATION_MAX = 150;
//
//    public static final String ORE_ID_PREFIX = "ore -- ";
//    public static final int ORE_CORRUPT_MIN = 9000;
//    public static final int ORE_CORRUPT_MAX = 20000;
//    public static final int ORE_REACH = 1;
//
//    public static final String QUAKE_KEY = "quake";
//    public static final String QUAKE_ID = "quake";
//    public static final int QUAKE_ACTION_PERIOD = 1100;
//    public static final int QUAKE_ANIMATION_PERIOD = 100;
//    public static final int QUAKE_ANIMATION_REPEAT_COUNT = 10;
//
//    public static final String ORE_KEY = "ore";
//    public static final int ORE_NUM_PROPERTIES = 5;
//    public static final int ORE_ID = 1;
//    public static final int ORE_COL = 2;
//    public static final int ORE_ROW = 3;
//    public static final int ORE_ACTION_PERIOD = 4;
//
//    private final EntityKind kind;
//    private final String id;
//    private Point position;
//    private List<PImage> images;
//    private int imageIndex;
//    private final int resourceLimit;
//    private int resourceCount;
//    private final int actionPeriod;
//    private final int animationPeriod;

//    public Entity(
//            EntityKind kind,
//            String id,
//            Point position,
//            List<PImage> images,
//            int resourceLimit,
//            int resourceCount,
//            int actionPeriod,
//            int animationPeriod)
//    {
//        this.kind = kind;
//        this.id = id;
//        this.position = position;
//        this.images = images;
//        this.imageIndex = 0;
//        this.resourceLimit = resourceLimit;
//        this.resourceCount = resourceCount;
//        this.actionPeriod = actionPeriod;
//        this.animationPeriod = animationPeriod;
//    }
//
//    //GETTERS
//
//    public EntityKind getEntityKind() {
//        return this.kind;
//    }
//
//    public Point getPosition() {
//        return this.position;
//    }
//
//
//    //SETTERS
//
//    public void setPosition(Point p) {
//        this.position = p;
//    }
//
//
//    //FUNCTIONS
//
//    public int getAnimationPeriod() {
//        switch (this.getEntityKind()) {
//            case MINER_FULL:
//            case MINER_NOT_FULL:
//            case ORE_BLOB:
//            case QUAKE:
//                return this.animationPeriod;
//            default:
//                throw new UnsupportedOperationException(
//                        String.format("getAnimationPeriod not supported for %s",
//                                this.getEntityKind()));
//        }
//    }
//
//    public void nextImage() {
//        this.imageIndex = (this.imageIndex + 1) % this.images.size();
//    }
//
//    public void executeMinerFullActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> fullTarget =
//                world.findNearest(this.position, EntityKind.BLACKSMITH);
//                //findNearest(world, entity.position, EntityKind.BLACKSMITH);
//
//        if (fullTarget.isPresent() && this.moveToFull(world, fullTarget.get(), scheduler))
//        {
//            this.transformFull(world, scheduler, imageStore);
//        }
//        else {
//            scheduler.scheduleEvent(this,
//                    Factory.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }


//    public void executeMinerNotFullActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> notFullTarget =
//                world.findNearest(this.position, EntityKind.ORE);
//
//        if (!notFullTarget.isPresent() || !this.moveToNotFull(world,
//                notFullTarget.get(),
//                scheduler)
//                || !this.transformNotFull(world, scheduler, imageStore))
//        {
//            scheduler.scheduleEvent(this,
//                    Factory.createActivityAction(this, world, imageStore),
//                    this.actionPeriod);
//        }
//    }
//
//    public void executeOreActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Point pos = this.position;
//
//        world.removeEntity(this);
//        scheduler.unscheduleAllEvents(this);
//
//        Entity blob = Factory.createOreBlob(this.id + BLOB_ID_SUFFIX, pos,
//                this.actionPeriod / BLOB_PERIOD_SCALE,
//                BLOB_ANIMATION_MIN + rand.nextInt(
//                        BLOB_ANIMATION_MAX
//                                - BLOB_ANIMATION_MIN),
//                Functions.getImageList(imageStore, BLOB_KEY));
//
//        world.addEntity(blob);
//        blob.scheduleActions(scheduler, world, imageStore);
//    }
//
//    public void executeOreBlobActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Entity> blobTarget =
//                world.findNearest(this.position, EntityKind.VEIN);
//        long nextPeriod = this.actionPeriod;
//
//        if (blobTarget.isPresent()) {
//            Point tgtPos = blobTarget.get().position;
//
//            if (moveToOreBlob(this, world, blobTarget.get(), scheduler)) {
//                Entity quake = Factory.createQuake(tgtPos,
//                        Functions.getImageList(imageStore, QUAKE_KEY));
//
//                world.addEntity(quake);
//                nextPeriod += this.actionPeriod;
//                quake.scheduleActions(scheduler, world, imageStore);
//            }
//        }
//
//        scheduler.scheduleEvent(this,
//                Factory.createActivityAction(this, world, imageStore),
//                nextPeriod);
//    }
//
//    public void executeQuakeActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        scheduler.unscheduleAllEvents(this);
//        world.removeEntity(this);
//    }
//
//    public void executeVeinActivity(
//            WorldModel world,
//            ImageStore imageStore,
//            EventScheduler scheduler)
//    {
//        Optional<Point> openPt = world.findOpenAround(this.position);
//
//        if (openPt.isPresent()) {
//            Entity ore = Factory.createOre(ORE_ID_PREFIX + this.id, openPt.get(),
//                    ORE_CORRUPT_MIN + rand.nextInt(
//                            ORE_CORRUPT_MAX - ORE_CORRUPT_MIN),
//                    Functions.getImageList(imageStore, ORE_KEY));
//            world.addEntity(ore);
//            ore.scheduleActions(scheduler, world, imageStore);
//        }
//
//        scheduler.scheduleEvent(this,
//                Factory.createActivityAction(this, world, imageStore),
//                this.actionPeriod);
//    }
//
//    public void scheduleActions(
//            EventScheduler scheduler,
//            WorldModel world,
//            ImageStore imageStore)
//    {
//        switch (this.kind) {
//            case MINER_FULL:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Factory.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case MINER_NOT_FULL:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Factory.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case ORE:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                break;
//
//            case ORE_BLOB:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this,
//                        Factory.createAnimationAction(this, 0),
//                        this.getAnimationPeriod());
//                break;
//
//            case QUAKE:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                scheduler.scheduleEvent(this, Factory.createAnimationAction(this,
//                        QUAKE_ANIMATION_REPEAT_COUNT),
//                        this.getAnimationPeriod());
//                break;
//
//            case VEIN:
//                scheduler.scheduleEvent(this,
//                        Factory.createActivityAction(this, world, imageStore),
//                        this.actionPeriod);
//                break;
//
//            default:
//        }
//
//    }
//

//    public boolean transformNotFull(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        if (this.resourceCount >= this.resourceLimit) {
//            Entity miner = Factory.createMinerFull(this.id, this.resourceLimit,
//                    this.position, this.actionPeriod,
//                    this.animationPeriod,
//                    this.images);
//
//            world.removeEntity(this);
//            scheduler.unscheduleAllEvents(this);
//
//            world.addEntity(miner);
//            miner.scheduleActions(scheduler, world, imageStore);
//
//            return true;
//        }
//
//        return false;
//    }
//
//    public void transformFull(
//            WorldModel world,
//            EventScheduler scheduler,
//            ImageStore imageStore)
//    {
//        Entity miner = Factory.createMinerNotFull(this.id, this.resourceLimit,
//                this.position, this.actionPeriod,
//                this.animationPeriod,
//                this.images);
//
//        world.removeEntity(this);
//        scheduler.unscheduleAllEvents(this);
//
//        world.addEntity(miner);
//        miner.scheduleActions(scheduler, world, imageStore);
//    }
//
//    public boolean moveToNotFull(
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (adjacent(this.position, target.position)) {
//            this.resourceCount += 1;
//            world.removeEntity(target);
//            scheduler.unscheduleAllEvents(target);
//
//            return true;
//        }
//        else {
//            Point nextPos = this.nextPositionMiner(world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant(nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents(occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//
//    }
//
//    public boolean moveToFull(
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (adjacent(this.position, target.position)) {
//            return true;
//        }
//        else {
//            Point nextPos = this.nextPositionMiner(world, target.position);
//
//            if (!this.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant(nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents(occupant.get());
//                }
//
//                world.moveEntity(this, nextPos);
//            }
//            return false;
//        }
//    }
//
//    public boolean moveToOreBlob(
//            Entity blob,
//            WorldModel world,
//            Entity target,
//            EventScheduler scheduler)
//    {
//        if (adjacent(blob.position, target.position)) {
//            world.removeEntity(target);
//            scheduler.unscheduleAllEvents(target);
//            return true;
//        }
//        else {
//            Point nextPos = blob.nextPositionOreBlob(world, target.position);
//
//            if (!blob.position.equals(nextPos)) {
//                Optional<Entity> occupant = world.getOccupant(nextPos);
//                if (occupant.isPresent()) {
//                    scheduler.unscheduleAllEvents(occupant.get());
//                }
//
//                world.moveEntity(blob, nextPos);
//            }
//            return false;
//        }
//    }
//
//    public Point nextPositionMiner(
//            WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        int vert = Integer.signum(destPos.y - this.position.y);
//
//        Point newPos = new Point(this.position.x + horiz, this.position.y + vert);
//
//        if (world.isOccupied(newPos)) {
//
//            newPos = new Point(this.position.x, this.position.y + vert);
//
//            if (vert == 0 || world.isOccupied(newPos)) {
//                newPos = new Point(this.position.x + horiz, this.position.y);
//                if (horiz == 0 || world.isOccupied(newPos))
//                {
//                    newPos = this.position;
//                }
//            }
//
//        }
//
//        return newPos;
//    }
//
//    public Point nextPositionOreBlob(
//            WorldModel world, Point destPos)
//    {
//        int horiz = Integer.signum(destPos.x - this.position.x);
//        Point newPos = new Point(this.position.x + horiz, this.position.y);
//
//        Optional<Entity> occupant = world.getOccupant(newPos);
//
//        if (horiz == 0 || (occupant.isPresent() && !(occupant.get().kind
//                == EntityKind.ORE)))
//        {
//            int vert = Integer.signum(destPos.y - this.position.y);
//            newPos = new Point(this.position.x, this.position.y + vert);
//            occupant = world.getOccupant(newPos);
//
//            if (vert == 0 || (occupant.isPresent() && !(occupant.get().kind
//                    == EntityKind.ORE)))
//            {
//                newPos = this.position;
//            }
//        }
//
//        return newPos;
//    }
//
//    public boolean adjacent(Point p1, Point p2) {
//        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y
//                && Math.abs(p1.x - p2.x) == 1);
//    }
//
//    public PImage getCurrentImage(Object entity) {
//        return ((Entity)entity).images.get(((Entity)entity).imageIndex);
//    }
//
//}

public interface Entity {
    Point getPosition();
    void setPosition(Point p);
    int getAnimationPeriod();
    void nextImage();
    PImage getCurrentImage(Object entity);
//    boolean adjacent(Point p1, Point p2);
//    void scheduleActions(
//            EventScheduler scheduler, WorldModel world, ImageStore imageStore);

}