import java.util.*;


/**
 * This class declares methods related to data such as points and lines. There is also
 * Board class that manages the data, but view part should use this class. 
 * 
 * @author Yeongbae Jeon
 * @author Aditya Malladi
 * @version 2022.07.07
 */
public class ClassifierModel {
    private static ClassifierModel _instance;

    private Board board;

    /**
     * This method calls a handler with the clustering algorithm to create two groups of points.
     * Instead of returning two lists, every point has one of two colors. And the returned list
     * is a reference in this class, so the list should not be modified.
     * 
     * 
     */
    public void calculateCluster() {
        this.board.notifyObservers(UpdateOption.CLUSTER);
    }

    /**
     * This method calls a handler with the point conneting algorithm to create one long line.
     * the returned list is a reference in this class, so the list should not be modified.
     * 
     * 
     */
    public void calculateLines() {
        this.board.notifyObservers(UpdateOption.LINE);
    }
    
    /**
     * This method calls both handlers to create groups of points and draw lines between the
     * center of groups.
     */
    public void calculateBoth() {
    	this.board.notifyObservers(UpdateOption.BOTH);
    }

    /**
     * This method add a new point to model, but does not call any handlers. And the returned
     * list should be readonly.
     * 
     * @param newPoint a new point to add
     * @return A list of points with the new point.
     */
    public List<Point> addPoint(Point newPoint) {
        this.board.addPoint(newPoint);
        return this.board.getPoints();
    }
    
    /**
     * This will return the list of points in the model.
     * 
     * @return A list of points
     */
    public List<Point> getPoints() {
    	return this.board.getPoints();
    }
    
    /**
     * This method will return the list of lines in the model.
     * 
     * @return A list of lines
     */
    public List<Line> getLines() {
    	return this.board.getLines();
    }
    
    /**
     * This method will return the x and y coordinates of cluster centers
     * in the model.
     * 
     * @return A list of points
     */
    public List<Point> getClusterCenter() {
    	return this.board.getClusterCenter();
    }
    
    /**
     * This method will return the radiuses of clusters in the model.
     * 
     * @return A list of integers
     */
    public List<Integer> getClusterRadius(){
    	return this.board.getClusterRadius();
    }
    
    /**
     * This method will return the number of clusters in the model.
     * 
     * @return an integer
     */
    public int getClusterCount() {
    	return this.board.getClusterCount();
    }
    
    /**
     * This method will change the number of clusters in the model.
     * 
     * @param value
     */
    public void setClusterCount(int value) {
    	this.board.setClusterCount(value);
    }

    /**
     * clear all values in the model.
     */
    public void clearBoard() {
        this.board.setPoints(new LinkedList<>());
        this.board.setLines(new LinkedList<>());
        this.board.setClusterCenter(new LinkedList<>());
        this.board.setClusterRadius(new LinkedList<>());
        this.board.setClusterCount(1);
    }

    /**
     * get a singleton object of this class
     * @return singleton object
     */
    public static ClassifierModel getInstance() {
        if (_instance == null)
            new ClassifierModel();

        return _instance;
    }

    private ClassifierModel() {
        this.board = new Board();
        _instance = this;
    }
}