//public final class Action
//{
//    private ActionKind kind;
//    private Entity entity;
//    private WorldModel world;
//    private ImageStore imageStore;
//    private final int repeatCount;
//
//    public Action(
//            ActionKind kind,
//            Entity entity,
//            WorldModel world,
//            ImageStore imageStore,
//            int repeatCount)
//    {
//        this.kind = kind;
//        this.entity = entity;
//        this.world = world;
//        this.imageStore = imageStore;
//        this.repeatCount = repeatCount;
//    }


    //FUNCTIONS

//    public void executeAction(EventScheduler scheduler) {
//        switch (this.kind) {
//            case ACTIVITY:
//                executeActivityAction(scheduler);
//                break;
//
//            case ANIMATION:
//                executeAnimationAction(scheduler);
//                break;
//        }
//    }

//    public void executeAnimationAction(EventScheduler scheduler)
//    {
//        //nextImage(this.entity);
//        this.entity.nextImage();
//
//        if (this.repeatCount != 1) {
//            //scheduleEvent(scheduler, this.entity,
//                    //createAnimationAction(action.entity,
//                            //Math.max(action.repeatCount - 1,
//                                    //0)),
//            scheduler.scheduleEvent(this.entity, Factory.createAnimationAction(this.entity, Math.max(this.repeatCount - 1, 0)), this.entity.getAnimationPeriod());
//                    //getAnimationPeriod(action.entity));
//        }
//    }

//    public void executeActivityAction(EventScheduler scheduler)
//    {
//        switch (this.entity.getEntityKind()) {
//            case MINER_FULL:
//                //executeMinerFullActivity(action.entity, action.world,
//                        //action.imageStore, scheduler);
//                this.entity.executeMinerFullActivity(this.world, this.imageStore, scheduler);
//                break;
//
//            case MINER_NOT_FULL:
//                //executeMinerNotFullActivity(action.entity, action.world,
//                        //action.imageStore, scheduler);
//                this.entity.executeMinerNotFullActivity(this.world, this.imageStore, scheduler);
//                break;
//
//            case ORE:
//                //executeOreActivity(action.entity, action.world,
//                        //action.imageStore, scheduler);
//                this.entity.executeOreActivity(this.world, this.imageStore, scheduler);
//                break;
//
//            case ORE_BLOB:
//                //executeOreBlobActivity(action.entity, action.world,
//                        //action.imageStore, scheduler);
//                this.entity.executeOreBlobActivity(this.world, this.imageStore, scheduler);
//                break;
//
//            case QUAKE:
//                //executeQuakeActivity(action.entity, action.world,
//                        //action.imageStore, scheduler);
//                this.entity.executeQuakeActivity(this.world, this.imageStore, scheduler);
//                break;
//
//            case VEIN:
//                //executeVeinActivity(action.entity, action.world,
//                        //action.imageStore, scheduler);
//                this.entity.executeVeinActivity(this.world, this.imageStore, scheduler);
//                break;
//
//            default:
//                throw new UnsupportedOperationException(String.format(
//                        "executeActivityAction not supported for %s",
//                        this.entity.getEntityKind()));
//        }
//    }
//
//}

public interface Action {

    void executeAction(EventScheduler scheduler);

}