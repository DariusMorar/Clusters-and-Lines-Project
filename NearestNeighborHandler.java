import java.awt.Color;
import java.util.*;

/**
 * This class handles updating the List<Line> in board.
 * 
 *@author Aditya Malladi
 *@version 2022.07.07
 */
public class NearestNeighborHandler implements Observer {

	/**
	 * This method will update the List<Line> in board based on received options.
	 *
	 *@param o
	 *@param args
	 */
	@Override
	public void update(Observable o, Object args) {
		Board board = (Board)o;
		UpdateOption option = (UpdateOption)args;
		if (option == UpdateOption.CLUSTER) {
			board.setLines(new LinkedList<>());
		}
		else if (option == UpdateOption.LINE){
			board.setLines(nearestNeighbor(board.getPoints()));
		}
		else if (board.getClusterCount() == 2) {
			board.setLines(nearestNeighbor(board.getClusterCenter()));
		}
		else {
			board.setLines(nearestNeighbor(board.getPoints()));
		}
	}
	
	private List<Line> nearestNeighbor(List<Point> points) {
		List<Line> lines = new LinkedList<Line>();
		int total = points.size();
		Boolean[] visited = new Boolean[total];
		Queue<Integer> q = new LinkedList<Integer>();
		
		if (total < 2) {
			return new LinkedList<>();
		}
		
		Point topLeft = new Point(0, 0, Color.BLACK);
		int startIndex = 0;
		{
			float minDistance = Float.MAX_VALUE;
			for (int i = 0; i < total; i++) {
				float distance = distanceTo(topLeft, points.get(i));
				if (distance < minDistance) {
					minDistance = distance;
					startIndex = i;
				}
			}
		}
		
		for (int i = 0; i < total; i++) {
			visited[i] = false;
		}
		visited[startIndex] = true;
		q.add(startIndex);
		
		int count = 0;
		while (!q.isEmpty()) {
			count++;
			if (count == total) {
				break;
			}
			Point startPoint = points.get(q.poll());
			int endPointIndex = 0;
			float minDistance = Float.MAX_VALUE;
			
			for (int i = 0; i < total; i++) {
				if (visited[i] == false) {
					float distance = distanceTo(startPoint, points.get(i));
					if (distance < minDistance) {
						minDistance = distance;
						endPointIndex = i;
					}
				}
			}
			
			visited[endPointIndex] = true;
			q.add(endPointIndex);
			lines.add(new Line(startPoint, points.get(endPointIndex)));
		}
		
		return lines;
	}
	
	private float distanceTo(Point a, Point b) {
		return (float) Math.sqrt((a.getX() - b.getX())*(a.getX() - b.getX())
				+ (a.getY() - b.getY())*(a.getY() - b.getY()));
	}

}