//public class Animation implements Action{
public class Animation extends AbstractAction{

//    private Entity entity;
//    private WorldModel world;
//    private ImageStore imageStore;
//    private final int repeatCount;

    public Animation(
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

//    public void executeAnimationAction(EventScheduler scheduler)
    public void executeAction(EventScheduler scheduler)
    {
        //nextImage(this.entity);
        this.getEntity().nextImage();

        if (this.getRepeatCount() != 1) {
            //scheduleEvent(scheduler, this.entity,
            //createAnimationAction(action.entity,
            //Math.max(action.repeatCount - 1,
            //0)),
            scheduler.scheduleEvent(this.getEntity(), Factory.createAnimationAction(this.getEntity(), Math.max(this.getRepeatCount() - 1, 0)), this.getEntity().getAnimationPeriod());
            //getAnimationPeriod(action.entity));

        }
    }


}
