import clip.Clip;
import objectdata.Edge;
import objectdata.Point;
import objectdata.Rectangle;
import rasterdata.RasterBI;
import rasterop.LinerTrivial;
import rasterop.Rectangler;
import java.io.Serial;
import rasterop.fill.Pattern;
import rasterop.fill.ScanLine;
import rasterop.fill.SeedFill;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * Class for drawing on Canvas
 *
 * @author Lukas Goldsmid
 * @version 2023
 */

public class Canvas {
	final JFrame frame;
	private final JPanel panel;
	private LinerTrivial ln;
	private RasterBI img;
	Clip cl = new Clip();
	private boolean seedMode = false;
	private Point seed;
	private final int background;
	private final SeedFill sf = new SeedFill();
	private final ScanLine sl = new ScanLine();
	Rectangle rectangle;
	private final Rectangler rt = new Rectangler();
	boolean shift = false;
	boolean o1 = true;
	boolean o2 = true;
	boolean o3 = true;
	boolean o4 = true;
	private final List<Point> points = new ArrayList<>();
	private final List<Point> clipPoints = new ArrayList<>();
	private final List<Point> recPoints = new ArrayList<>();
	private int mode;

	public Canvas(int width, int height) {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		img = new RasterBI(width, height);

		background = 3092271;
		panel = new JPanel() {
			@Serial
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				img.present(g);
			}
		};
		panel.setPreferredSize(new Dimension(width, height));
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		ln = new LinerTrivial(img);
		sf.setImg(img);
		sl.setImg(img);
		sl.setLiner(ln);
		panel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				img = new RasterBI(panel.getWidth(), panel.getHeight());
				ln = new LinerTrivial(img);
				sf.setImg(img);
				sl.setImg(img);
				draw();
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == 1) { //When clicking with LMB
					mode = 1;
					if (points.size() == 0) {
						points.add(new Point(e.getX(),e.getY()));
					}
				}
				if (e.getButton() == 3){ //When clicking with RMB
					mode = 3;
					if (clipPoints.size() == 0) {
						clipPoints.add(new Point(e.getX(), e.getY()));
					}
				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == 1){ //When releasing LMB
					points.add(new Point(e.getX(),e.getY()));
				}
                if (e.getButton() == 2){ //When releasing MMB
					seedMode = true;
					seed = new Point(e.getX(), e.getY());
				}
				if (e.getButton() == 3){ //When releasing RMB
					if ((recPoints.size() < 2) && shift) {
						recPoints.add(new Point(e.getX(), e.getY()));
					}
					if (!shift){
					Point p = new Point(e.getX(), e.getY());
					Edge edge = new Edge(clipPoints.get(clipPoints.size() - 1), clipPoints.get(0));
					Edge edge1 = new Edge(clipPoints.get(0), clipPoints.get(1));
					Edge edge2 = new Edge(clipPoints.get(clipPoints.size() - 2), clipPoints.get(clipPoints.size() - 1));
						if (!edge.inside(p) && edge1.inside(p) && edge2.inside(p)) {
							clipPoints.add(p);
						}
					}
				}
				mode = 0;
				draw();
				panel.repaint();

			}
		});

		panel.addMouseMotionListener(new MouseMotionAdapter() {
			/**
			 * Event that happens when mouse is dragged on the panel
			 * @param e the event to be processed
			 */
			@Override
			public void mouseDragged(MouseEvent e) {
				draw();
				ln.setColor(16776960);
				int i;
				if (mode == 1 && points.size() > 0) { //WHen holding LMB
					i = points.size();
					ln.drawLine(points.get(i - 1).getX(), points.get(i - 1).getY(), e.getX(), e.getY());
					ln.drawLine(points.get(0).getX(), points.get(0).getY(), e.getX(), e.getY());
				}
				if (mode == 3 && clipPoints.size() > 0) { //When holding the RMB
					i = clipPoints.size();
					ln.drawLine(clipPoints.get(i - 1).getX(), clipPoints.get(i - 1).getY(), e.getX(), e.getY());  //index out of bounds?
					ln.drawLine(clipPoints.get(0).getX(), clipPoints.get(0).getY(), e.getX(), e.getY());
				}
				panel.repaint();
			}

			/**
			 * When mouse is moved
			 * @param e the event to be processed
			 */
			@Override
			public void mouseMoved(MouseEvent e) {
                draw();
				panel.repaint();
			}
		});
		panel.requestFocus();
		panel.requestFocusInWindow();
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if (e.getKeyCode() == KeyEvent.VK_SHIFT){
					shift = true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				if (e.getKeyCode() == KeyEvent.VK_C) { //when C is pressed
					init();
				}
				if (e.getKeyCode() == KeyEvent.VK_SHIFT){
					shift = false;
				}
				if (e.getKeyCode() == KeyEvent.VK_1) {
					o1 = !o1; // 1 - clipping points outside the blue polygon
				}
				draw();
				panel.repaint();
			}
		});
		init();
	}

	/**
	 * Procedure for resetting the canvas to its starting state
	 */
	private void init() {
		o1 = true;
		o2 = true;
		o3 = true;
		o4 = true;
		seedMode = false;
		clipPoints.clear();
		clipPoints.add(new Point(10.0, 10.0));
		clipPoints.add(new Point(100.0, 600.0));
		clipPoints.add(new Point(500.0, 500.0));
		points.clear();
		recPoints.clear();
	}

	/**
	 * Procedure for redrawing a polyline
	 * @param points list of points of the polyline
	 * @param color int value
	 */
	public void redrawPolyLine(List<Point> points, int color) {
		ln.setColor(color);
		if (points.size() > 1){
			for (int i = 0; i < points.size(); ++i) {
				ln.drawLine(points.get((i + 1) % points.size()).getX(), points.get((i + 1) % points.size()).getY(),points.get(i).getX(), points.get(i).getY());
			}
		}
	}

	/**
	 * Represents a procedure for redrawing objects onto the canvas
	 */
	public void draw() {
		img.clear(background);
		if (o1) {
			redrawPolyLine(points, 16776960);
		}
		if (o2) {
			redrawPolyLine(clipPoints, 65535);
		}

		List<Point> c = cl.clip(points, clipPoints);
		if (o4){ //Scan line - not working
			sl.setPoints(c, 8912896, 34952);
			sl.draw((p) -> true);
		}
		if (o3) {
			redrawPolyLine(c, 16711680);
		}
		if (recPoints.size() == 2){ //Drawing a rectangle
			rectangle = new Rectangle(recPoints.get(0), recPoints.get(1));
			rt.draw(rectangle, ln);
		}
		if (seedMode){ //Seed fill
			sf.setSeed((int)seed.getX(), (int)seed.getY(), 65416);
			sf.draw(new Pattern());
		}

		img.setPixel(10, 10, 16776960);
		img.getGraphics().drawString("LMB - for drawing lines of the polygon | MMB - press anywhere for filling the area with a pattern by seed-fill algorithm | 1 - for clipping user defined polygon with the blue polygon",5,img.getHeight() - 20);
		img.getGraphics().drawString("RMB - for adding new points to the blue clipping polygon | SHIFT + RMB - for drawing a rectangle by only 2 points | C - for clearing the canvas",5,img.getHeight() - 5);
	}

	public void start() {
		draw();
		panel.repaint();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Canvas(1200, 600).start());
	}

}