import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
import java.util.Random;

import processing.core.*;
import processing.event.MouseEvent;

public final class VirtualWorld extends PApplet
{
    public static boolean ANGEL_CHECK = false;
    public static final String ANGEL_KEY = "angel";
    public static final String DEVIL_KEY = "devil";
    public static final String RAINBOW_KEY = "rainbow";
    public static final String VEIN_KEY = "vein";
    public static final int TIMER_ACTION_PERIOD = 100;

    public static final int VIEW_WIDTH = 640;
    public static final int VIEW_HEIGHT = 480;
    public static final int TILE_WIDTH = 32;
    public static final int TILE_HEIGHT = 32;
    public static final int WORLD_WIDTH_SCALE = 2;
    public static final int WORLD_HEIGHT_SCALE = 2;

    public static final int VIEW_COLS = VIEW_WIDTH / TILE_WIDTH;
    public static final int VIEW_ROWS = VIEW_HEIGHT / TILE_HEIGHT;
    public static final int WORLD_COLS = VIEW_COLS * WORLD_WIDTH_SCALE;
    public static final int WORLD_ROWS = VIEW_ROWS * WORLD_HEIGHT_SCALE;

    public static final String IMAGE_LIST_FILE_NAME = "imagelist";
    public static final String DEFAULT_IMAGE_NAME = "background_default";
    public static final int DEFAULT_IMAGE_COLOR = 0x808080;

    public static final String LOAD_FILE_NAME = "world.sav";

    public static final String FAST_FLAG = "-fast";
    public static final String FASTER_FLAG = "-faster";
    public static final String FASTEST_FLAG = "-fastest";
    public static final double FAST_SCALE = 0.5;
    public static final double FASTER_SCALE = 0.25;
    public static final double FASTEST_SCALE = 0.10;

    public static double timeScale = 1.0;

    public ImageStore imageStore;
    private WorldModel world;
    private WorldView view;
    private EventScheduler scheduler;

    private long nextTime;

    public void settings() {
        size(VIEW_WIDTH, VIEW_HEIGHT);
    }

    /*
       Processing entry point for "sketch" setup.
    */
    public void setup() {
        this.imageStore = new ImageStore(
                createImageColored(TILE_WIDTH, TILE_HEIGHT,
                        DEFAULT_IMAGE_COLOR));
        this.world = new WorldModel(WORLD_ROWS, WORLD_COLS,
                createDefaultBackground(imageStore));
        this.view = new WorldView(VIEW_ROWS, VIEW_COLS, this, world, TILE_WIDTH,
                TILE_HEIGHT);
        this.scheduler = new EventScheduler(timeScale);

        loadImages(IMAGE_LIST_FILE_NAME, imageStore, this);
        loadWorld(world, LOAD_FILE_NAME, imageStore);

        scheduleActions(world, scheduler, imageStore);

        nextTime = System.currentTimeMillis() + TIMER_ACTION_PERIOD;
    }

    public void draw() {
        long time = System.currentTimeMillis();
        if (time >= nextTime) {
            scheduler.updateOnTime(time);
            nextTime = time + TIMER_ACTION_PERIOD;
        }

        view.drawViewport();
    }

    public void keyPressed() {
        if (key == CODED) {
            int dx = 0;
            int dy = 0;

            switch (keyCode) {
                case UP:
                    dy = -1;
                    break;
                case DOWN:
                    dy = 1;
                    break;
                case LEFT:
                    dx = -1;
                    break;
                case RIGHT:
                    dx = 1;
                    break;
            }
            view.shiftView(dx, dy);
        }
    }

    public static Background createDefaultBackground(ImageStore imageStore) {
        return new Background(DEFAULT_IMAGE_NAME,
                Functions.getImageList(imageStore,
                        DEFAULT_IMAGE_NAME));
    }

    public static PImage createImageColored(int width, int height, int color) {
        PImage img = new PImage(width, height, RGB);
        img.loadPixels();
        for (int i = 0; i < img.pixels.length; i++) {
            img.pixels[i] = color;
        }
        img.updatePixels();
        return img;
    }

    private static void loadImages(
            String filename, ImageStore imageStore, PApplet screen)
    {
        try {
            Scanner in = new Scanner(new File(filename));
            Functions.loadImages(in, imageStore, screen);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void loadWorld(
            WorldModel world, String filename, ImageStore imageStore)
    {
        try {
            Scanner in = new Scanner(new File(filename));
            Functions.load(in, world, imageStore);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void scheduleActions(
            WorldModel world, EventScheduler scheduler, ImageStore imageStore)
    {
        for (Entity entity : world.entities) {
            //((Entity) entity).scheduleActions(scheduler, world, imageStore);
//            ((Entity) entity).scheduleActions(scheduler, world, imageStore);
            if (entity instanceof ActiveEntity) {
                ((ActiveEntity) entity).scheduleActions(scheduler, world, imageStore);
            }
//            ((Extension) entity).scheduleActions(scheduler, world, imageStore);

        }
    }

    public static void parseCommandLine(String[] args) {
        for (String arg : args) {
            switch (arg) {
                case FAST_FLAG:
                    timeScale = Math.min(FAST_SCALE, timeScale);
                    break;
                case FASTER_FLAG:
                    timeScale = Math.min(FASTER_SCALE, timeScale);
                    break;
                case FASTEST_FLAG:
                    timeScale = Math.min(FASTEST_SCALE, timeScale);
                    break;
            }
        }
    }

    public void mousePressed(MouseEvent click)
    {
        if (ANGEL_CHECK == false) {
            Point pressed = mouseToPoint(mouseX, mouseY);
            Point p1 = pressed;
            final Function<Point, Stream<Point>> CARDINAL_NEIGHBORS =
                    point ->
                            Stream.<Point>builder()
                                    .add(new Point(point.x, point.y))
                                    .add(new Point(point.x, point.y - 1))
                                    .add(new Point(point.x, point.y + 1))
                                    .add(new Point(point.x - 1, point.y))
                                    .add(new Point(point.x + 1, point.y))
                                    .add(new Point(point.x - 2, point.y))
                                    .add(new Point(point.x + 2, point.y))
                                    .add(new Point(point.x, point.y - 2))
                                    .add(new Point(point.x, point.y + 2))
                                    .add(new Point(point.x, point.y + 3))
                                    .build();
            try {
                List<Point> neighboring_points = CARDINAL_NEIGHBORS.apply(p1).collect(Collectors.toList());
                for (Point n : neighboring_points) {
                    world.setBackground(n, new Background(RAINBOW_KEY, Functions.getImageList(imageStore, "rainbow")));
                }
                for (Point n : neighboring_points) {
                    if (world.isOccupied(n)) {
                        Entity pos = (Entity)(world.getOccupant(n).get());
                        world.removeEntity(pos);
                        scheduler.unscheduleAllEvents(pos);
                        Angel a = (Angel) Factory.createAngel(ANGEL_KEY, pressed, Functions.getImageList(imageStore, ANGEL_KEY), 0, 0,
                                2, 0);
                        world.addEntity(a);
                        ((Angel) a).scheduleActions(scheduler, world, imageStore);
                        clickVeins();
                    }
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of bounds!");
            }
            ANGEL_CHECK = true;
        }

        else {
            Point pressed = mouseToPoint(mouseX, mouseY);
            try {
                clickDevils(4, 4, pressed);
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Out of bounds!");
            }
            ANGEL_CHECK = false;
        }

    }

    public Point randXYPoint() {
        Random r = new Random();
        int x = r.nextInt(WORLD_ROWS);
        int y = r.nextInt(WORLD_COLS);
        Point rand = new Point(x,y);
        while (world.isOccupied(rand)) {
            x = r.nextInt(WORLD_ROWS); //generate a value between 0 and 100
            y = r.nextInt(WORLD_COLS);
            rand = new Point(x, y);
        }
        return rand;
    }

    private void clickVeins(){
        for (int i = 0; i < 2; i++) {
            VEIN v = (VEIN) Factory.createVein(VEIN_KEY, randXYPoint(), 2000,
                    Functions.getImageList(imageStore, VEIN_KEY));
            world.addEntity(v);
            ((VEIN)v).scheduleActions(scheduler, world, imageStore);
        }
    }

    private void clickDevils(int actionPeriod, int animationPeriod, Point pressed){
        for (int i = 0; i < 5; i++) {
            Devil d = (Devil) Factory.createDevil(DEVIL_KEY, pressed, Functions.getImageList(imageStore, DEVIL_KEY), 0, 0,
                    actionPeriod, animationPeriod);
            world.addEntity(d);
            (d).scheduleActions(scheduler, world, imageStore);
        }
    }

    private Point mouseToPoint(int x, int y)
    {
        Point p = view.viewport.viewportToWorld(mouseX/TILE_WIDTH, mouseY/TILE_HEIGHT);
        return p;
    }


    public static void main(String[] args) {
        parseCommandLine(args);
        PApplet.main(VirtualWorld.class);
    }
}
