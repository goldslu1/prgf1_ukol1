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

    /**
     * Getter for returning x from the first point
     * @return double value
     */
    public double getX1() {
        return x1;
    }

    /**
     * Getter for returning y from the first point
     * @return double value
     */
    public double getY1() {
        return y1;
    }

    /**
     * Getter for returning x from the second point
     * @return double value
     */
    public double getX2() {
        return x2;
    }

    /**
     * Getter for returning y from the second point
     * @return double value
     */
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

    /**
     * represents a function that asks if y is the intersection between 2 points
     * @param y parameter type Int
     * @return boolean yes or no
     */
    public boolean hasYIntercept(int y){
        return (double)y >= this.y1 && (double)y < this.y2;
    }

    /**
     * represents a function that returns the value of the intersection
     * @param y parameter type Int
     * @return double value
     */
    public double getIntersection(int y) {
        return k * (double)y + q;
    }

    /**
     * represents a procedure that exchanges points if y1 > y2
     */
    private void sort() {
        if (y1 > y2) {
            double p = x1;
            x1 = x2;
            x2 = p;
            p = y1;
            y1 = y2;
            y2 = p;
        }
    }

    /**
     * represents a procedure that calculates the k and q of this line
     */
    void calculate() {
        if (y1 != y2) {
            k = (x2 - x1) / (y2 - y1);
            q = x1 - k * y1;
        }

    }
}
