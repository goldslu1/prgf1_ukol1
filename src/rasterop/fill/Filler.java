package rasterop.fill;

import objectdata.Point;
import rasterdata.Raster;

import java.util.function.Predicate;

public interface Filler {
    void setImg(Raster img);
    void draw(Predicate<Point> pattern);
}
