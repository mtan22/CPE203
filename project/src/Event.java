public final class Event
{
    private Action action;
    private long time;
    private Entity entity;

    //GETTERS

    public Entity getEntity() {
        return this.entity;
    }

    public long getTime() {
        return this.time;
    }

    public Action getAction() {
        return this.action;
    }


    //FUNCTIONS

    public Event(Action action, long time, Entity entity) {
        this.action = action;
        this.time = time;
        this.entity = entity;
    }
}
