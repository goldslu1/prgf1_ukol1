package rasterop.fill;

import objectdata.Point;
import rasterdata.Raster;

import java.util.function.Predicate;

/**
 * Represents seed-fill algorithm for filling areas
 */
public class SeedFill implements Filler{
    private int background;
    private int color;
    private Raster img;
    private int x,y;
    Predicate<Point> pattern;
    public SeedFill(){

    }
    @Override
    public void setImg(Raster img){
        this.img = img;
    }

    /**
     * Procedure that sets the seed by a point and color
     * @param x x parameter from a point
     * @param y y parameter from a point
     * @param color color of the fill
     */
    public void setSeed(int x, int y, int color){
        this.color = color;
        this.background = img.getPixel(x, y);
        this.x = x;
        this.y = y;
    }
    @Override
    public void draw(Predicate<Point> pattern){
        this.pattern = pattern;
        background = img.getPixel(x, y);
        drawSeed(x, y);
    }

    /**
     * Procedure that starts filling any area after setting the seed
     * @param x x parameter of a point
     * @param y y parameter of a point
     */
    private void drawSeed(int x, int y){
        if (x > 0 && y > 0 && x < img.getWidth() - 1 && y < img.getHeight() - 1) {
            int bp = img.getPixel(x, y);
            if (bp == background) {
                if (pattern.test(new Point(x, y))) {
                    img.setPixel(x, y, color);
                } else {
                    img.setPixel(x, y, 657930);
                }

                drawSeed(x + 1, y);
                drawSeed(x - 1, y);
                drawSeed(x, y + 1);
                drawSeed(x, y - 1);
            }
        }
    }
}
