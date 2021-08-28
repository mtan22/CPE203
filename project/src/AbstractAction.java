abstract public class AbstractAction implements Action{

    protected Entity entity;
    protected WorldModel world;
    protected ImageStore imageStore;
    protected int repeatCount;

    public AbstractAction(
            Entity entity,
            WorldModel world,
            ImageStore imageStore,
            int repeatCount)
    {
        this.entity = entity;
        this.world = world;
        this.imageStore = imageStore;
        this.repeatCount = repeatCount;
    }

    public Entity getEntity() {
        return entity;
    }

    public WorldModel getWorld() {
        return world;
    }

    public ImageStore getImageStore() {
        return imageStore;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    abstract public void executeAction(EventScheduler scheduler);

//    protected abstract void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
