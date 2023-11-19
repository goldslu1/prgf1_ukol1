package objectdata;


import java.util.ArrayList;
import java.util.List;

/**
 * Represent a polygon defined a list of edges in 2D space
 */
public class Polygon {
    List<Point> points;
    int color;
    public Polygon(){
        points = new ArrayList<>();
    }
    public Polygon(List<Point> points){
        this.points = points;
    }
    public Polygon(List<Point> points, int color){
        this.points = points;
        this.color = color;
    }

    /**
     * represents a function for adding a point into a polygon
     * @param p represents parameter from Point
     * @return back the same Polygon with newly added point
     */
    public Polygon addPoint(Point p){
        points.add(p);
        return this;
    }

    /**
     * represents a function for getting the list of all lines from the polygon
     * @return List of lines
     */
    public List<Line> getLines(){
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < points.size(); i++){
            lines.add(new Line(points.get(i), points.get((i + 1) % points.size())));
        }
        return lines;
    }
}
