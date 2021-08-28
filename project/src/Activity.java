//public class Activity implements Action {
public class Activity extends AbstractAction {

//    private Entity entity;
//    private WorldModel world;
//    private ImageStore imageStore;
//    private final int repeatCount;

    public Activity(
            Entity entity,
            WorldModel world,
            ImageStore imageStore,
            int repeatCount)
    {
//        this.entity = entity;
//        this.world = world;
//        this.imageStore = imageStore;
//        this.repeatCount = repeatCount;
        super(entity, world, imageStore, repeatCount);
    }

    public void executeAction(EventScheduler scheduler) {
        ((ActiveEntity) entity).execute(this.world, this.imageStore, scheduler);
    }



}
