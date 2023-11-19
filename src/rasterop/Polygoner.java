package rasterop;

import objectdata.Line;
import objectdata.Polygon;

import java.util.List;

/**
 * Represents a trivial algorithm for drawing polygons
 */

public class Polygoner{
    /**
     * Procedure that draws a polygon onto the canvas
     * @param polygon polygon to be drawn
     * @param liner liner for drawing lines of the polygon
     */
    public void draw(Polygon polygon, Liner liner){
        List<Line> lines = polygon.getLines();
        for (Line line : lines) {
            liner.drawLine(line);
        }
    }
}
