package rasterdata;

import java.util.Optional;

/**
 * Represents a two-dimensional raster with pixels of type int
 */
public interface Raster {

    /**
     * Returns the number of columns in this raster
     */
    int getWidth();

    /**
     * returns the number of rows in this raster
     */
    int getHeight();

    /**
     * Sets the pixel at the specified address to the provided color value
     * @param c column address
     * @param r row address
     * @param color new color value
     * @return true if the provided address was valid; false otherwise
     */
    boolean setColor(int c, int r, int color);

    /**
     * Returns the pixel value at the specified address
     * @param c column address
     * @param r row address
     * @return Optional of the pixel value if the provided address was valid; empty Optional otherwise
     */
    Optional<Integer> getColor(int c, int r);

    /**
     * Sets all pixels of thi image to the provided color
     * @param background color of the background
     */
    void clear(int background);

    /**
     * Returns an integer pixel in the default RGB color model
     * @param x first coordinate of the pixel
     * @param y second coordinate of the pixel
     * @return an integer pixel in the default RGB color model and default sRGB colorspace.
     */
    int getPixel(int x, int y);

    /**
     * Sets a pixel in this BufferedImage to the specified RGB value
     * @param x the X coordinate of the pixel to set
     * @param y the Y coordinate of the pixel to set
     * @param color numeric RGB value
     */
    void setPixel(int x, int y, int color) ;
}

