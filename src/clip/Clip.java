package clip;

import java.util.ArrayList;
import java.util.Iterator;

import objectdata.Edge;
import objectdata.Point;
import java.util.List;

/**
 * Represents a class for clipping polygons
 */
public class Clip {
    public Clip() {

    }

    /**
     * function for clipping polygon
     * @param points represents points of the user defined polygon
     * @param clipPoints represents points of the clipping polygon already drawn onto canvas
     * @return list of clipped points
     */
    public List<Point> clip(List<Point> points, List<Point> clipPoints) {
        if (clipPoints.size() < 2) {
            return points;
        } else {
            List<Point> newPoints = points;
            Point p1 = clipPoints.get(clipPoints.size() - 1);

            Point p2;
            for(Iterator<Point> var6 = clipPoints.iterator(); var6.hasNext(); p1 = p2) {
                p2 = var6.next();
                newPoints = clipEdge(points, new Edge(p1, p2));
                points = newPoints;
            }
            return newPoints;
        }
    }

    /**
     * Represents a function that cuts the edges of the user defined polygon with the fixed polygon
     * @param points represents points of the user defined polygon
     * @param e represents the edge of the fixed polygon with the user defined polygon
     * @return list of clipped points
     */
    private List<Point> clipEdge(List<Point> points, Edge e) {
        if (points.size() < 2) {
            return points;
        } else {
            List<Point> out = new ArrayList<>();
            Point v1 = points.get(points.size() - 1);

            Point v2;
            for(Iterator<Point> var6 = points.iterator(); var6.hasNext(); v1 = v2) {
                v2 = var6.next();
                if (e.inside(v2)) {
                    if (!e.inside(v1)) {
                        out.add(e.intersection(v1, v2));
                    }

                    out.add(v2);
                } else if (e.inside(v1)) {
                    out.add(e.intersection(v1, v2));
                }
            }
            return out;
        }
    }
}
