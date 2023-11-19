package objectdata;

/**
 * Represents an edge defined by 2 points where two polygons meet
 */
public class Edge {
    Point p1;
    Point p2;

    public Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * represents a function that asks if a point is inside the edge
     * @param p represents instance from the class Point
     * @return boolean yes or no
     */
    public boolean inside(Point p) {
        Point v1 = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        Point n1 = new Point(-v1.getY(), v1.getX());
        Point v2 = new Point(p.getX() - p1.getX(), p.getY() - p1.getY());
        return n1.getX() * v2.getX() + n1.getY() * v2.getY() < 0.0;
    }

    /**
     * function that returns the intersection point of 2 points
     * @param v1 represents instance from the class Point
     * @param v2 represents instance from the class Point
     * @return point where is the intersection between 2 points
     */
    public Point intersection(Point v1, Point v2) {
        double px = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (p1.getX() - p2.getX()) - (p1.getX() * p2.getY() - p1.getY() * p2.getX()) * (v1.getX() - v2.getX())) / ((v1.getX() - v2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() - p2.getX()) * (v1.getY() - v2.getY()));
        double py = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() * p2.getY() - p1.getY() * p2.getX()) * (v1.getY() - v2.getY())) / ((v1.getX() - v2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() - p2.getX()) * (v1.getY() - v2.getY()));
        return new Point(px, py);
    }
}
