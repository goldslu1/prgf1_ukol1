package objectdata;

/**
 * Represents a line in a 2D space; immutable
 */
public class Line {
    protected double x1;
    protected double y1;
    protected double x2;
    protected double y2;
    public double k;
    public double q;

    public double getX1() {
        return x1;
    }

    public double getY1() {
        return y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public Line(double x1, double y1, double x2, double y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

    }

    public Line(Point p1, Point p2){
        this.x1 = p1.getX();
        this.y1 = p1.getY();
        this.x2 = p2.getX();
        this.y2 = p2.getY();
        this.sort();
        this.calculate();
    }

    public boolean hasYIntercept(int y){
        return (double)y >= this.y1 && (double)y < this.y2;
    }
    public double getIntersection(int y) {
        return k * (double)y + q;
    }
    private void sort() {
        if (this.y1 > this.y2) {
            double p = this.x1;
            this.x1 = this.x2;
            this.x2 = p;
            p = this.y1;
            this.y1 = this.y2;
            this.y2 = p;
        }

    }
    void calculate() {
        if (y1 != y2) {
            k = (x2 - x1) / (y2 - y1);
            q = x1 - k * y1;
        }

    }
}
