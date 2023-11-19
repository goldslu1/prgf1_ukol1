package rasterop;

import objectdata.Line;
import objectdata.Polygon;

import java.util.List;

public class Polygoner{
    void draw(Polygon polygon, Liner liner){
        List<Line> lines = polygon.getLines();
        for (Line line : lines) {
            liner.drawLine(line);
        }
    }
}
