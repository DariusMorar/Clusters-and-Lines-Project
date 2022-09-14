import java.util.List;
import java.util.LinkedList;
import java.util.Observable;


/**
 * This class manages points and lines 
 * 
 * Because this is a container class, notifyObserver() is called by ClassifierModel class.
 * the ClassifierModel class pass UpdateOption argument to handlers when calling motifyObserver().
 * 
 * @author Yeongbae Jeon
 * @author Aditya Malladi
 * @version 2022.07.07
 */
public class Board extends Observable {

    private List<Point> points = null;
    private List<Line> lines = null;
    private List<Point> clusterCenter = null;
    private List<Integer> clusterRadius = null;
    private int clusterCount;

    public Board() {
        this.points = new LinkedList<Point>();
        this.lines = new LinkedList<Line>();
        this.clusterCenter = new LinkedList<Point>();
        this.clusterRadius = new LinkedList<Integer>();
        this.clusterCount = 1;

        this.addObserver(new NearestNeighborHandler());
        this.addObserver(new KMClusterHandler());
    }

    /**
     * This method adds a Point to the points list in Board.
     * 
     * @param newPoint
     */
    public void addPoint(Point newPoint) {
        this.points.add(newPoint);
        this.setChanged();
    }

    public List<Point> getPoints() {
        return this.points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
        this.setChanged();
    }

    public List<Line> getLines() {
        return this.lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
        this.setChanged();
    }
    
    public List<Point> getClusterCenter() {
    	return this.clusterCenter;
    }
    
    public void setClusterCenter(List<Point> centers) {
    	this.clusterCenter = centers;
    }
    
    public List<Integer> getClusterRadius() {
    	return this.clusterRadius;
    }
    
    public void setClusterRadius(List<Integer> radius){
    	this.clusterRadius = radius;
    }
    
    public int getClusterCount() {
    	return this.clusterCount;
    }
    
    public void setClusterCount(int value) {
    	this.clusterCount = value;
    }
    
}