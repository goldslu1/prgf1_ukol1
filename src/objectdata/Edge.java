package objectdata;

public class Edge {
    Point p1;
    Point p2;

    public Edge(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public boolean inside(Point p) {
        Point v1 = new Point(p2.getX() - p1.getX(), p2.getY() - p1.getY());
        Point n1 = new Point(-v1.getY(), v1.getX());
        Point v2 = new Point(p.getX() - p1.getX(), p.getY() - p1.getY());
        return n1.getX() * v2.getX() + n1.getY() * v2.getY() < 0.0;
    }

    public Point intersection(Point v1, Point v2) {
        double px = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (p1.getX() - p2.getX()) - (p1.getX() * p2.getY() - p1.getY() * p2.getX()) * (v1.getX() - v2.getX())) / ((v1.getX() - v2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() - p2.getX()) * (v1.getY() - v2.getY()));
        double py = ((v1.getX() * v2.getY() - v1.getY() * v2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() * p2.getY() - p1.getY() * p2.getX()) * (v1.getY() - v2.getY())) / ((v1.getX() - v2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() - p2.getX()) * (v1.getY() - v2.getY()));
        return new Point(px, py);
    }
}
