package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Point2DTest {

	@Test
	void testPoint2DString() {
		Point2D point = new Point2D("5,5");
        assertEquals(5, point.x(), 0.0001);
        assertEquals(5, point.y(), 0.0001);
    }

	@Test
	void testAdd() {
		  Point2D point1 = new Point2D(5, 5);
	        Point2D point2 = new Point2D(10, 10);
	        Point2D result = point1.add(point2);
	        assertEquals(15, result.x(), 0.0001);
	        assertEquals(15, result.y(), 0.0001);
	    }
	@Test
	void testDistancePoint2D() {
		Point2D point1 = new Point2D(5, 5);
        Point2D point2 = new Point2D(10, 10);
        double result = point1.distance(point2);
        assertEquals(7.0710678118654755, result, 0.0001);
    }
	@Test
	void testVector() {
		 Point2D point1 = new Point2D(5, 5);
	        Point2D point2 = new Point2D(10, 10);
	        Point2D result = point1.vector(point2);
	        assertEquals(5, result.x(), 0.0001);
	        assertEquals(5, result.y(), 0.0001);
	    }
	@Test
	void testMove() {
		Point2D point1 = new Point2D(5, 5);
        Point2D point2 = new Point2D(10, 10);
        point1.move(point2);
        assertEquals(15, point1.x(), 0.0001);
        assertEquals(15, point1.y(), 0.0001);
    }

	@Test
	void testScale() {
		 Point2D point = new Point2D(5, 5);
	        Point2D center = new Point2D(5, 5);
	        point.scale(center, 2);
	        assertEquals(5, point.x(), 0.0001);
	        assertEquals(5, point.y(), 0.0001);
	    }
	@Test
	void testRotate() {
		 Point2D point = new Point2D(5, 5);
	        Point2D center = new Point2D(5, 5);
	        point.rotate(center, 45);
	        }

}