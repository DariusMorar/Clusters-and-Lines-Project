import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static java.awt.Color.*;
import java.util.Random;
/**
 *
 * Using the K-Mean clustering methods to deal withe board coordinates and return the center point and radian.
 *
 * @author Yaci Zhu
 * @version 7/7/2022
 */
public class KMClusterHandler implements Observer{
	private List<Point> centPoints;
	private List<Integer> radi;
	private List<Point> cent;
	private final List<Point>[] cluster =new ArrayList[10000];
	/**
	 *
	 * Update the object for observable class
	 * @param o & option
	 */
	@Override
	public void update(Observable o, Object arg1) {
		List<Point> dots = ((Board) o).getPoints();
		int numCluster= ((Board)o).getClusterCount();
		UpdateOption option = (UpdateOption)arg1;
		if (option == UpdateOption.CLUSTER) {
			clusterGroups(dots,numCluster);
		}else if (option == UpdateOption.LINE) {
			clearAllColors(dots);
		}
		else {
			clusterGroups(dots,numCluster);
			((Board) o).setClusterCenter(findCentPoint(numCluster));
			((Board) o).setClusterRadius(findRad(numCluster));
		}
	}


	private void clusterGroups(List<Point> points, int numCluster) {
		cent= new ArrayList<>(numCluster);
		for(int i=0;i<numCluster;i++){
			cluster[i]= new ArrayList<Point>(points.size());
			cent.add(i,randomCent(points));
		}

		int maxTimes = 30;
		do {
			for(int i=0;i<numCluster;i++){
				cluster[i].clear();
			}

			for(Point p:points){
				int [] dis =new int[numCluster];
				for (int i=0;i<numCluster;i++){
					dis[i]=distance(cent.get(i),p);
				}
				int index = findNearDist(dis);
				cluster[index].add(p);
				cent.set(index,meanOfCluster(cluster[index]));
			}
			maxTimes--;
		} while (maxTimes == 0);
		Color[] c= new Color[numCluster];
		for(int i=0; i<numCluster;i++){
			c[i]=randomColor();
			for (Point p:cluster[i]){
				p.setColor(c[i]);
			}}

	}

	private List<Point> findCentPoint (int numCluster){
		if (numCluster==2) {
			centPoints= new ArrayList<>(numCluster);
			for (int i = 0; i < numCluster; i++) {
				int[] ras = new int[cluster[i].size()];
				for (Point r : cluster[i]) {
					ras[i] = distance(cent.get(i), r);
				}
				int centP = findNearDist(ras);
				centPoints.add(i, cluster[i].get(centP));
			}return centPoints;
		} return centPoints=null;
	}

	private List<Integer> findRad( int numCluster){
		if(numCluster==2) {
			radi=new ArrayList<>(numCluster);
			for(int i=0;i<numCluster;i++){
				int[] radius = new int[cluster[i].size()];
				for(int j=0;j<cluster[i].size();j++){
					for (Point p:cluster[i]){
						radius[j]=distance(centPoints.get(i),p);
					}
				}
				int index=findFar(radius);
				radi.add(i,radius[index]);
			}
		}
		return radi;
	}

	private int findFar(int[] radius){
		int index=0;
		for ( int i = 1; i < radius.length; i++ ) {
			if ( radius[i] > radius[index] ) index = i;
		}
		return index;
	}

	private Color randomColor(){
		Random randomGenerator = new Random();
		int red = randomGenerator.nextInt(256);
		int green = randomGenerator.nextInt(256);
		int blue = randomGenerator.nextInt(256);
		Color randomColour = new Color(red,green,blue);
		return randomColour;
	}

	private int findNearDist(int[] dis) {
		int index = 0;
		if (dis.length == 1) {
			return 0;
		} else {
			for (int i = 1; i < dis.length; i++) {
				if (dis[i] < dis[index]) index = i;
			}

			return index;
		}
	}

	private void clearAllColors(List<Point> points) {
		for (Point p : points) {
			p.setColor(BLACK);
		}
	}

	private Point meanOfCluster(List<Point> cluster) {
		double meanX = 0;
		double meanY = 0;

		for (Point p : cluster) {
			meanX += p.getX();
			meanY += p.getY();
		}
		meanX /= cluster.size();
		meanY /= cluster.size();

		int mX =(int) Math.round(meanX);
		int mY =(int) Math.round(meanY);

		return new Point(mX,mY , BLACK);
	}

	private int distance(Point a, Point b) {
		double xDiffSquared = Math.pow(a.getX() - b.getX(), 2);
		double yDiffSquared = Math.pow(a.getY() - b.getY(), 2);
		double d =	Math.sqrt(xDiffSquared + yDiffSquared);
		int dis =(int) Math.round(d);
		return dis;
	}

	private Point randomCent(List<Point> points) {
		int randomNum = ThreadLocalRandom.current().nextInt(0, points.size());
		Point p = points.get(randomNum);
		return new Point(p.getX(), p.getY(), BLACK);
	}

}