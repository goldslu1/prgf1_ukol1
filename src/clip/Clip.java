package clip;

import java.util.ArrayList;
import java.util.Iterator;

import objectdata.Edge;
import objectdata.Point;
import java.util.List;

public class Clip {
    public Clip() {

    }
    public List<Point> clip(List<Point> points, List<Point> clipPoints) {
        if (clipPoints.size() < 2) {
            return points;
        } else {
            List<Point> newPoints = points;
            Point p1 = clipPoints.get(clipPoints.size() - 1);

            Point p2;
            for(Iterator<Point> var6 = clipPoints.iterator(); var6.hasNext(); p1 = p2) {
                p2 = var6.next();
                newPoints = this.clipEdge(points, new Edge(p1, p2));
                points = newPoints;
            }
            return newPoints;
        }
    }

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
