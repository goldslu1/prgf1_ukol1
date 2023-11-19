package rasterop.fill;

import objectdata.Point;

import java.util.function.Predicate;

public class Pattern  implements Predicate<Point> {
    public Pattern(){

    }

    @Override
    public boolean test(Point point) {
        int x = (int)point.getX() % 5;
        int y = (int)point.getY() % 5;
        return x == y;
    }
}
