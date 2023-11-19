package rasterop.fill;

import objectdata.Point;

import java.util.function.Predicate;

/**
 * Represents a predicate value that sets the pattern for filling the polygon via seed-fill
 */
public class Pattern  implements Predicate<Point> {
    public Pattern(){

    }

    /**
     * Function that tests a point
     * @param point the input argument
     * @return boolean true or false
     */
    @Override
    public boolean test(Point point) {
        int x = (int)point.getX() % 5;
        int y = (int)point.getY() % 5;
        return x == y;
    }
}
