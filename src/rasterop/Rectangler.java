package rasterop;

import objectdata.Rectangle;

/**
 * Represents a trivial algorithm for drawing rectangles
 */

public class Rectangler {
    /**
     * Procedure that draws a rectangle onto the canvas
     * @param rectangle rectangle to be drawn
     * @param liner liner for drawing lines of the rectangle
     */
    public void draw(Rectangle rectangle, Liner liner){
        liner.drawLine(rectangle.getP1(), rectangle.getP3());
        liner.drawLine(rectangle.getP1(), rectangle.getP4());
        liner.drawLine(rectangle.getP2(), rectangle.getP3());
        liner.drawLine(rectangle.getP2(), rectangle.getP4());
    }
}
