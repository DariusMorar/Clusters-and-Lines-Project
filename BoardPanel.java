import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

import java.util.LinkedList;
import java.util.List;
/**
* The BoardPanel is responsible for drawing/erasing points and lines.
* @author Cungang Zhang
* @author Aditya Malladi
* @version 2022.07.07
* */

public class BoardPanel extends JPanel {
    private List<Point> points;
    private List<Line> lines;
    private List<Point> clusterCenter;
    private List<Integer> clusterRadius;

    /**
     * Constructor for BoardPanel class
     */
    public BoardPanel(){
        this.setBackground(Color.GRAY);
        this.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                ClassifierModel model = ClassifierModel.getInstance();
                List<Point> points = model.addPoint(new Point(x, y, Color.BLACK));
                drawPoints(points);
            }
        });
    }

    /**
     * This method will draw points on the BoardPanel.
     * 
     * @param points - A list of Points to be drawn
     */
    public void drawPoints(List<Point> points) {
        this.lines = null;
        this.clusterCenter = null;
        this.clusterRadius = null;
        this.points = points;
        repaint();
    }
    
    /**
     * This method will draw lines on the BoardPanel.
     * 
     * @param lines - A list of lines to be drawn
     */
    public void drawLines(List<Line> lines){
        this.lines = lines;
        this.clusterCenter = null;
        this.clusterRadius = null;
        repaint();
    }
    
    /**
     * This method will draw circles on the BoardPanel and is
     * used for the line + cluster option.
     * 
     * @param centers
     * @param radius
     */
    public void drawCircles(List<Point> centers, List<Integer> radius) {
    	this.clusterCenter = centers;
    	this.clusterRadius = radius;
    	repaint();
    }
    
    /**
     * This method will clear the values in board, add new points to board,
     * and then redraw BoardPanel. Used for when the load button is pressed
     * in MainFrame.
     * 
     * @param points
     */
    public void drawPointsFromLoad(List<Point> points) {
    	clear();
    	ClassifierModel model = ClassifierModel.getInstance();
    	List<Point> dots = new LinkedList<Point>();
    	for (Point p : points) {
    		dots = model.addPoint(p);
    	}
    	drawPoints(dots);
    }
    
    /**
     * This method will clear the values in board and redraw BoardPanel
     * resulting in an empty screen.
     */
    public void clear() {
    	ClassifierModel model = ClassifierModel.getInstance();
    	model.clearBoard();
    	drawPoints(model.getPoints());
    }
    
    /**
     * This method is an override of the paintComponent method in BoardPanel,
     * and will draw points, lines, and circles to the panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.points != null) {
            for (Point p : this.points) {
                g.setColor(p.getColor());
                g.fillOval(p.getX()-5, p.getY()-5, 10, 10);
            }
        }
        if (this.lines != null){
            for(Line l : this.lines){
                Point x1 = l.getEndPoint1();
                Point x2 = l.getEndPoint2();
                g.setColor(Color.ORANGE);
                g.drawLine(x1.getX(),x1.getY(),x2.getX(),x2.getY());
            }
        }
        if ((this.clusterCenter != null) && (this.clusterRadius != null)) {
        	if (!this.clusterCenter.isEmpty() && !this.clusterRadius.isEmpty()) {
        		for (int i = 0; i < clusterRadius.size(); i++) {
            		Point p = clusterCenter.get(i);
            		g.setColor(p.getColor());
            		int radius = clusterRadius.get(i);
            		g.drawOval(p.getX() - radius, p.getY() - radius, radius*2, radius*2);
            	}
        	}
        }
    }
}