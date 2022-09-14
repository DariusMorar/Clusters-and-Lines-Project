import static org.junit.Assert.*;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * This file contains test cases for the NearestNeighborHandler class.
 * 
 * @author Aditya Malladi
 * @version 2022.07.07
 */

public class NearestNeighborHandlerTest {

	@Test
	public void testUpdate1() {
		// test when the update option is line and there are no points in board
		NearestNeighborHandler line = new NearestNeighborHandler();
		Board board = new Board();
		UpdateOption option = UpdateOption.LINE;
		board.setPoints(new LinkedList<>());
		line.update(board, option);
		assertEquals(board.getLines(), new LinkedList<>());
	}
	
	@Test
	public void testUpdate2() {
		// test when the update option is cluster
		NearestNeighborHandler line = new NearestNeighborHandler();
		Board board = new Board();
		UpdateOption option = UpdateOption.CLUSTER;
		List<Point> points = new LinkedList<Point>();
		points.add(new Point(0,0,Color.BLACK));
		points.add(new Point(10,10,Color.BLACK));
		board.setPoints(points);
		line.update(board, option);
		assertEquals(board.getLines(), new LinkedList<>());
	}
	
	@Test
	public void testUpdate3() {
		// test when the update option is line and there are points in board
		NearestNeighborHandler line = new NearestNeighborHandler();
		Board board = new Board();
		UpdateOption option = UpdateOption.LINE;
		List<Point> points = new LinkedList<Point>();
		
		// add points to board
		Point point1 = new Point(0, 0, Color.BLACK);
		Point point2 = new Point(10, 10, Color.BLACK);
		points.add(point1);
		points.add(point2);
		board.setPoints(points);
		
		line.update(board, option);
		assertEquals(board.getLines().get(0), new Line(point1, point2));
	}
	
	@Test
	public void testUpdate4() {
		/*
		 * test when update option is both and there are two clusters.
		 * since the cluster number is 2, line handler will draw line between
		 * the center of clusters rather than all points
		 */
		
		NearestNeighborHandler line = new NearestNeighborHandler();
		Board board = new Board();
		UpdateOption option = UpdateOption.BOTH;
		List<Point> points = new LinkedList<Point>();
		List<Point> cluster = new LinkedList<Point>();
		
		// add points to board, add clusters to board
		Point point1 = new Point(0, 0, Color.BLACK);
		Point point2 = new Point(10, 10, Color.BLACK);
		Point point3 = new Point(100, 100, Color.BLACK);
		Point point4 = new Point(90, 90, Color.BLACK);
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		cluster.add(point2);
		cluster.add(point4);
		board.setPoints(points);
		board.setClusterCenter(cluster);
		board.setClusterCount(2);
		
		line.update(board, option);
		assertEquals(board.getLines().get(0), new Line(point2, point4));
	}
	
	@Test
	public void testUpdate5() {
		/*
		 * test when update option is both and there are not two clusters.
		 * since the cluster number is not 2, line handler will draw line between
		 * all points rather than center of clusters
		 */
		
		NearestNeighborHandler line = new NearestNeighborHandler();
		Board board = new Board();
		UpdateOption option = UpdateOption.BOTH;
		List<Point> points = new LinkedList<Point>();
		List<Point> cluster = new LinkedList<Point>();
		
		// add points to board, add clusters to board
		Point point1 = new Point(0, 0, Color.BLACK);
		Point point2 = new Point(10, 10, Color.BLACK);
		Point point3 = new Point(100, 100, Color.BLACK);
		Point point4 = new Point(90, 90, Color.BLACK);
		points.add(point1);
		points.add(point2);
		points.add(point3);
		points.add(point4);
		cluster.add(point2);
		cluster.add(point3);
		cluster.add(point4);
		board.setPoints(points);
		board.setClusterCenter(cluster);
		board.setClusterCount(3);
		
		line.update(board, option);
		assertEquals(board.getLines().get(0), new Line(point1, point2));
		assertEquals(board.getLines().get(1), new Line(point2, point4));
		assertEquals(board.getLines().get(2), new Line(point4, point3));
	}

}