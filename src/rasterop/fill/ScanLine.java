package rasterop.fill;

import objectdata.Line;
import objectdata.Point;
import rasterdata.Raster;
import rasterop.LinerTrivial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Represents the scanline algorithm for filling an area
 */

public class ScanLine implements Filler{
    private Raster img;
    private List<Point> vertices;
    private int colorFill;
    private int colorBorder;
    Predicate<Point> pattern;
    LinerTrivial ln = null;
    public ScanLine() {
    }
    @Override
    public void setImg(Raster img) {
        this.img = img;
    }

    /**
     * Setter for setting the liner
     * @param ln parameter from LinerTrivial that draws lines
     */
    public void setLiner(LinerTrivial ln) {
        this.ln = ln;
    }

    /**
     * Procedure that sets the vertices and color of the fill and border
     * @param vertices
     * @param colorFill
     * @param colorBorder
     */
    public void setPoints(List<Point> vertices, int colorFill, int colorBorder) {
        this.colorBorder = colorBorder;
        this.colorFill = colorFill;
        this.vertices = vertices;
    }
    @Override
    public void draw(Predicate<Point> pattern) {
        this.pattern = pattern;
        List<Line> usecky = new ArrayList<>(10);
        List<Line> pUsecky = new ArrayList<>(10);
        List<Double> prus = new ArrayList<>(10);
        if (vertices.size() >= 2) {
            double x1;
            double x2;
            double y1;
            double y2;
            int y;
            Line us;
            for(y = 0; y < vertices.size(); ++y) {
                x1 = (vertices.get(y)).getX();
                y1 = (vertices.get(y)).getY();
                x2 = (vertices.get((y + 1) % vertices.size())).getX();
                y2 = (vertices.get((y + 1) % vertices.size())).getY();
                us = new Line(x1, y1, x2, y2);
                usecky.add(us);
            }

            double ymin = (usecky.get(0)).getY1();
            double ymax = (usecky.get(0)).getY1();
            Iterator<Line> it = usecky.iterator();

            Line pUs;
            while(it.hasNext()) {
                us = it.next();
                if (us.getY1() != us.getY2()) {
                    pUs = new Line(us.getX1(), us.getY1(), us.getX2(), us.getY2());
                    pUsecky.add(pUs);
                    if (ymin > pUs.getY1()) {
                        ymin = pUs.getY1();
                    }

                    if (ymax < pUs.getY2()) {
                        ymax = pUs.getY2();
                    }
                }
            }

            for(y = (int)ymin; y <= (int)ymax + 1; ++y) {
                prus.clear();

                int i;
                for(i = 0; i < pUsecky.size(); ++i) {
                    pUs = pUsecky.get(i);
                    if (pUs.hasYIntercept(y)) {
                        prus.add((pUs.getIntersection(y)));
                    }
                }

                Collections.sort(prus);

                for(i = prus.size() - 1; i > 0; i -= 2) {
                    for(int x = (prus.get(i - 1)).intValue(); x <= (prus.get(i)).intValue(); ++x) {
                        if (pattern.test(new Point(x, y))) {
                            img.setPixel(x, y, colorFill);
                        }
                    }
                }
            }

            ln.setColor(colorBorder);

            for(y = 0; y < vertices.size(); ++y) {
                x1 = vertices.get(y).x;
                y1 = vertices.get(y).y;
                x2 = vertices.get((y + 1) % vertices.size()).x;
                y2 = vertices.get((y + 1) % vertices.size()).y;
                ln.drawLine(x1, y1, x2, y2);
            }

        }
    }
}
