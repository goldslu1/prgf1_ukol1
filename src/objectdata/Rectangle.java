package objectdata;


/**
 * Represents a rectangle drawn onto 2D space.
 */
public class Rectangle extends Polygon {
    protected Point p1;
    protected Point p2;
    private Point p3;
    private Point p4;

    public Rectangle(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        this.calculateCorners();
    }
    void calculateCorners(){
        double xc = (p1.getX() + p2.getX()) / 2;
        double yc = (p1.getY() + p2.getY()) / 2;
        double xd = (p1.getX() - p2.getX()) / 2;
        double yd = (p1.getY() - p2.getY()) / 2;

        p3 = new Point(xc - yd, yc + xd);
        p4 = new Point(xc + yd, yc - xd);
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
    }

    public Point getP3() {
        return p3;
    }

    public Point getP4() {
        return p4;
    }
}
