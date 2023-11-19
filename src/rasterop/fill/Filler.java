package rasterop.fill;

import objectdata.Point;
import rasterdata.Raster;

import java.util.function.Predicate;

/**
 * Represents an algorithm for filling areas of the polygon
 */

public interface Filler {
    /**
     * Represents a procedure that sets the image to draw on
     * @param img
     */
    void setImg(Raster img);

    /**
     * Represents a procedure for drawing the filled area
     * @param pattern parameter from Predicate class
     */
    void draw(Predicate<Point> pattern);
}
