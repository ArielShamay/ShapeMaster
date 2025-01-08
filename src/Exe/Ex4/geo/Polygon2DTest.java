package Exe.Ex4.geo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Polygon2DTest {

	@Test
	void testPolygon2D() {
		 Point2D[] points = {new Point2D(1, 1), new Point2D(5, 1), new Point2D(5, 5), new Point2D(1, 5)};
	        Polygon2D polygon = new Polygon2D(points);

	        // Test the vertexs field
	        Point2D[] returnedPoints = polygon.vertexs;
	        assertArrayEquals(points, returnedPoints);	}

	@Test
	void testToString() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(2, 2), new Point2D(3, 3)};
		Polygon2D polygon = new Polygon2D(vertexs);
		String expected = " 1.0,1.0, 2.0,2.0, 3.0,3.0, ";
		assertEquals(expected, polygon.toString());	}

	@Test
	void testContains() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(1, 5), new Point2D(5, 5),new Point2D(5, 1)};
		Polygon2D polygon = new Polygon2D(vertexs);
		Point2D point = new Point2D(2, 2);
		Point2D Fpoint = new Point2D(7, 7);
		assertTrue(polygon.contains(point));
		assertFalse(polygon.contains(Fpoint));}

	@Test
	void testArea() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(1, 5), new Point2D(5, 5),new Point2D(5, 1)};
		Polygon2D polygon = new Polygon2D(vertexs);
		assertEquals(16.0, polygon.area(), 0.01);
	}

	@Test
	void testPerimeter() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(1, 5), new Point2D(5, 5),new Point2D(5, 1)};
		Polygon2D polygon = new Polygon2D(vertexs);
		assertEquals(16.0, polygon.perimeter(), 0.01);
	}

	@Test
	void testMove() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(2, 2), new Point2D(3, 3)};
		Polygon2D polygon = new Polygon2D(vertexs);
		Point2D vec = new Point2D(1, 1);
		polygon.move(vec);
		assertEquals(2.0, vertexs[0].x(), 0.01);
		assertEquals(2.0, vertexs[0].y(), 0.01);
	}

	@Test
	void testCopy() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(2, 2), new Point2D(3, 3)};
		Polygon2D polygon = new Polygon2D(vertexs);
		Polygon2D copy = (Polygon2D) polygon.copy();
		assertEquals(polygon.toString(), copy.toString());

		// Check that the copy is a new object with the same properties
		assertNotSame(polygon, copy);

		// Modify the original polygon and check that the copy is not affected
		Point2D vec = new Point2D(1, 1);
		polygon.move(vec);
		assertNotEquals(polygon.toString(), copy.toString());
	}
	@Test
	void testScale() {
		Point2D[] vertexs = {new Point2D(1, 1), new Point2D(5, 1), new Point2D(5, 5), new Point2D(1, 5)};
		Polygon2D polygon = new Polygon2D(vertexs);
		polygon.scale(new Point2D(0,0),0.9);
	        assertEquals(4.5, polygon.getPoints()[1].x(),0.001);		
	        assertEquals(0.9, polygon.getPoints()[1].y(), 0.001);		
	        assertEquals(0.9, polygon.getPoints()[0].x(), 0.001);		
	       assertEquals(0.9, polygon.getPoints()[0].y(), 0.001);
	       assertEquals(4.5, polygon.getPoints()[2].x(),0.001);		
	        assertEquals(4.5, polygon.getPoints()[2].y(), 0.001);		
	        assertEquals(0.9, polygon.getPoints()[3].x(),0.001);		
	        assertEquals(4.5, polygon.getPoints()[3].y(), 0.001);		
	       
		}

	
	@Test
	void testRotate() {
		Point2D[] points = {new Point2D(1, 1), new Point2D(5, 1), new Point2D(5, 5), new Point2D(1, 5)};
		Polygon2D polygon = new Polygon2D(points);
		double EPS = 0.001;
		// Test the rotate method
		Point2D center = new Point2D(3, 3);
		double angle =0.5*Math.PI;
		polygon.rotate(center, angle);

		Point2D[] expectedPointsAfterRotation = {new Point2D(5, 1), new Point2D(5, 5), new Point2D(1.0000000000000002, 5), new Point2D(1, 1.0000000000000002)};
		Point2D[] returnedPoints = polygon.getPoints();
		assertArrayEquals(expectedPointsAfterRotation, returnedPoints);
	}

	@Test
	void testGetPoints() {
		   Point2D[] points = {new Point2D(1, 1), new Point2D(5, 1), new Point2D(5, 5), new Point2D(1, 5)};
	        Polygon2D polygon = new Polygon2D(points);

	        // Test the getPoints method
	        Point2D[] returnedPoints = polygon.getPoints();
	        assertArrayEquals(points, returnedPoints);
	    	}

}
