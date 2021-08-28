import java.util.List;

import processing.core.PImage;

public final class Background
{
    private final String id;
    private final List<PImage> images;
    private int imageIndex;

    public Background(String id, List<PImage> images) {
        this.id = id;
        this.images = images;
    }



    //FUNCTIONS
    public PImage getCurrentImage() {
        this.nextImage();
        return this.images.get(
                this.imageIndex);
    }

    public void nextImage() {
        this.imageIndex = (this.imageIndex + 1) % this.images.size();
    }

}