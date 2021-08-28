import java.security.cert.Extension;
import java.util.List;
import java.util.Random;
import java.util.Optional;

import processing.core.PImage;

abstract public class Miners extends AnimatedEntity{

    protected int resourceLimit;
    protected int resourceCount;

    public Miners(String id,
                  Point position,
                  List<PImage> images,
                  int resourceLimit,
                  int resourceCount,
                  int actionPeriod,
                  int animationPeriod) {

        super(id, position, images, resourceLimit, resourceCount, actionPeriod, animationPeriod);

    }

    //GETTERS

    public int getResourceLimit() {
        return resourceLimit;
    }

    public int getResourceCount() {
        return resourceCount;
    }



    public boolean adjacent(Point p1, Point p2) {
        return (p1.x == p2.x && Math.abs(p1.y - p2.y) == 1) || (p1.y == p2.y
                && Math.abs(p1.x - p2.x) == 1);
    }


//    public abstract void execute(WorldModel world, ImageStore imageStore, EventScheduler scheduler);
}
