package rasterop;

import rasterdata.Raster;

/**
 * Represents a trivial algorithm for drawing lines
 */
public class LinerTrivial implements Liner{
    private final Raster img;
    private int color;

    public LinerTrivial(Raster img) {
        this(img, 16776960);
    }
    public LinerTrivial(Raster img, int color) {
        this.img = img;
        this.color = color;
    }
    @Override
    public void drawLine(double x1, double y1, double x2, double y2) {
        double temp;
        double k;
        double q;
        double y;

        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
        if (x2 < x1) {
            temp = x1;
            x1 = x2;
            x2 = temp;

            temp = y1;
            y1 = y2;
            y2 = temp;
        }

            k = (y2 - y1) / (x2 - x1);
            q = y1 - (k * x1);

            for (int x = (int) Math.round(x1); x < x2; x++) {
                y = k * x + q;
                img.setColor(x, (int) y, color);
            }
        } else {
            if (y2 < y1) {
                temp = x1;
                x1 = x2;
                x2 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            k = (x2 - x1) / (y2 - y1);
            q = x1 - (k * y1);

            for (int x = (int) Math.round(y1); x < y2; x++) {
                y = k * x + q;
                img.setColor((int) y, x, color);
            }
        }
    }

    @Override
    public void drawDashLine(double x1, double y1, double x2, double y2) {
        double temp;
        double k;
        double q;
        double y;
        int odd = 0;
        int even = 0;
        boolean dots = false;

        if (Math.abs(y2 - y1) < Math.abs(x2 - x1)) {
            if (x2 < x1) {
                temp = x1;
                x1 = x2;
                x2 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;
            }

            k = (y2 - y1) / (x2 - x1);
            q = y1 - (k * x1);

            for (int x = (int) Math.round(x1); x < x2; x++) {
                y = k * x + q;
                if (!dots) {
                    even++;
                    img.setColor(x, (int) y, color);
                    if (even == 10){
                        even = 0;
                        dots = true;
                    }
                }
                else {
                    odd++;
                    if (odd == 10){
                        odd = 0;
                        dots = false;
                    }
                }
            }
        } else {
            if (y2 < y1) {
                temp = x1;
                x1 = x2;
                x2 = temp;

                temp = y1;
                y1 = y2;
                y2 = temp;
            }
            k = (x2 - x1) / (y2 - y1);
            q = x1 - (k * y1);

            for (int x = (int) Math.round(y1); x < y2; x++) {
                y = k * x + q;
                if (!dots) {
                    even++;
                    img.setColor((int)y, x, color);
                    if (even == 10){
                        even = 0;
                        dots = true;
                    }
                }
                else {
                    odd++;
                    if (odd == 10){
                        odd = 0;
                        dots = false;
                    }
                }
            }
        }
    }
    public void setColor(int color) {
        this.color = color;
    }
}
