import static org.junit.Assert.*;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

/**
 * This file contains test cases for the KMClusterHandler class.
 *
 * @author Yaci Zhu
 * @version 2022.07.07
 */

public class KMClusterHandlerTest {

    @Test
    @DisplayName("Test when the update option is line")
    public void testUpdate1() {
        /*
         * test when update option is line
         * nothing will happen in cluster class
         * and all the points should be in default black
         */
        KMClusterHandler kmClusterHandler = new KMClusterHandler();
        Board board = new Board();
        UpdateOption option = UpdateOption.LINE;
        List<Point> points = new LinkedList<Point>();
        points.add(new Point(0,0,Color.BLACK));
        points.add(new Point(10,10,Color.BLACK));
        board.setPoints(points);
        kmClusterHandler.update(board, option);
        assertEquals(Color.BLACK,board.getPoints().get(0).getColor());
        assertEquals(Color.BLACK,board.getPoints().get(1).getColor());

    }

    @Test
    @DisplayName("Test when the update option is cluster and there are points in board, also the test number of cluster is 1")
    public void testUpdate2() {
        /*
         * test when update option is  cluster
         * the class will achieve the function make cluster points different color
         * and all the points should not be in default black
         * because the cluster number is 1 then the test two points should have same color but not black
         */

        KMClusterHandler kmClusterHandler = new KMClusterHandler();
        Board board = new Board();
        UpdateOption option = UpdateOption.CLUSTER;
        List<Point> points = new LinkedList<Point>();

        // add points to board
        Point point1 = new Point(0, 0, Color.BLACK);
        Point point2 = new Point(10, 10, Color.BLACK);
        points.add(point1);
        points.add(point2);
        board.setPoints(points);
        board.setClusterCount(1);

        kmClusterHandler.update(board, option);
        assertNotEquals(Color.BLACK,board.getPoints().get(0).getColor());
        assertNotEquals(Color.BLACK,board.getPoints().get(1).getColor());

    }

    @Test
    @DisplayName("Test when the update option is cluster and there are points in board, also the test number of cluster is 2")
    public void testUpdate3() {
        /*
         * test when update option is  cluster
         * the class will achieve the function make cluster points different color
         */

        KMClusterHandler kmClusterHandler = new KMClusterHandler();
        Board board = new Board();
        UpdateOption option = UpdateOption.CLUSTER;
        List<Point> points = new LinkedList<Point>();

        // add points to board
        Point point1 = new Point(0, 0, Color.BLACK);
        Point point2 = new Point(10, 10, Color.BLACK);
        points.add(0,point1);
        points.add(1,point2);
        board.setPoints(points);
        board.setClusterCount(2);

        kmClusterHandler.update(board, option);
        assertNotEquals(Color.BLACK,board.getPoints().get(0).getColor());
        assertNotEquals(Color.BLACK,board.getPoints().get(1).getColor());

    }


    @Test
    @DisplayName("Test when the update option is both and cluster number is 2 and there are points in board")
    public void testUpdate4() {
        /*
         * test when update option is both and there are two clusters.
         * since the cluster number is 2, the cluster will return list of centpoints to board
         * and also return a list of radius integer list of radius to board
         */

        KMClusterHandler kmClusterHandler = new KMClusterHandler();
        Board board = new Board();
        UpdateOption option = UpdateOption.BOTH;
        List<Point> points = new LinkedList<Point>();


        // add points to board, add clusters to board
        Point point1 = new Point(0, 0, Color.BLACK);
        Point point2 = new Point(10, 10, Color.BLACK);
        Point point3 = new Point(100, 100, Color.BLACK);
        Point point4 = new Point(90, 90, Color.BLACK);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        board.setPoints(points);
        board.setClusterCount(2);

        kmClusterHandler.update(board, option);
        assertNotNull(board.getClusterCenter());
        assertNotNull(board.getClusterRadius());
        assertNotEquals(Color.BLACK,board.getPoints().get(0).getColor());
        assertNotEquals(Color.BLACK,board.getPoints().get(1).getColor());
    }

    @Test
    @DisplayName("Test when the update option is both and cluster number is not 2 and there are points in board")
    public void testUpdate5() {
        /*
         * test when update option is both and there are not 2 clusters.
         * since the cluster number is not 2, the cluster will return null list of centpoints to board
         * and also return a null list of radius integer list of radius to board
         * and the points' color won't be default black
         * just make sure the cluster do clusters and color function while the line function is on
         */

        KMClusterHandler kmClusterHandler = new KMClusterHandler();
        Board board = new Board();
        UpdateOption option = UpdateOption.BOTH;
        List<Point> points = new LinkedList<Point>();


        // add points to board, add clusters to board
        Point point1 = new Point(0, 0, Color.BLACK);
        Point point2 = new Point(10, 10, Color.BLACK);
        Point point3 = new Point(100, 100, Color.BLACK);
        Point point4 = new Point(90, 90, Color.BLACK);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        board.setPoints(points);
        board.setClusterCount(3);

        kmClusterHandler.update(board, option);
        assertNull(board.getClusterCenter());
        assertNull(board.getClusterRadius());
        assertNotEquals(Color.BLACK,board.getPoints().get(0).getColor());
        assertNotEquals(Color.BLACK,board.getPoints().get(1).getColor());

    }
}