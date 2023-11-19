package rasterop;

import objectdata.Rectangle;
import rasterdata.Raster;

public class Rectangler {
    public void draw(Rectangle rectangle, Liner liner){
        liner.drawLine(rectangle.getP1(), rectangle.getP3());
        liner.drawLine(rectangle.getP1(), rectangle.getP4());
        liner.drawLine(rectangle.getP2(), rectangle.getP3());
        liner.drawLine(rectangle.getP2(), rectangle.getP4());
    }
}
