package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Rect2DTest {
	Exe.Ex4.geo.Point2D p1=new Exe.Ex4.geo.Point2D(1,1);
	Exe.Ex4.geo.Point2D p2=new Exe.Ex4.geo.Point2D(5,1);
	Exe.Ex4.geo.Point2D p3=new Exe.Ex4.geo.Point2D(5,5);
	Exe.Ex4.geo.Point2D p4=new Exe.Ex4.geo.Point2D(1,5);
	Exe.Ex4.geo.Point2D p5=new Exe.Ex4.geo.Point2D(3,3);
	Exe.Ex4.geo.Point2D p6=new Exe.Ex4.geo.Point2D(6,6);
	Exe.Ex4.geo.Point2D p7=new Exe.Ex4.geo.Point2D(0,0);

	@Test
	void testRect2D() {
		Point2D p1 = new Point2D(this.p1);
		Point2D p2 = new Point2D(this.p3);
		Rect2D rect = new Rect2D(p1, p2);

		Point2D[] points = rect.getPoints();
		Point2D point1 = points[0];
		Point2D point2 = points[1];
		Point2D point3 = points[2];
		Point2D point4 = points[3];

		assertEquals(p1.ix(), point1.ix());
		assertEquals(p1.iy(), point1.iy());
		assertEquals(p1.ix(), point2.ix());
		assertEquals(p2.iy(), point2.iy());
		assertEquals(p2.ix(), point3.ix());
		assertEquals(p2.iy(), point3.iy());
		assertEquals(p2.ix(), point4.ix());
		assertEquals(p1.iy(), point4.iy());
	}

	@Test
	void testToString() {
		Point2D p1 = new Point2D(this.p1);
		Point2D p2 = new Point2D(this.p3);
		Rect2D rect = new Rect2D(p1, p2);

		String expected = p1.toString() + ", " +(p2.x() + "," + p1.y()) + ", " + p2.toString() + ", " + (p1.x() + "," + p2.y()) ;
		String result = rect.toString();

		assertEquals(expected, result);	}

	@Test
	void testContains() {
		Rect2D rect = new Rect2D(p1, p3);

		Point2D pointInside = new Point2D(p5);
		Point2D pointOutside = new Point2D(p6);

		assertTrue(rect.contains(pointInside));
		assertFalse(rect.contains(pointOutside));	}

	@Test
	void testArea() {
		Rect2D rect = new Rect2D(p1, p3);

		double width = p1.ix() - p2.ix();
		double height = p1.iy() - p3.iy();
		double expectedArea = width * height;
		double resultArea = rect.area();

		assertEquals(expectedArea, resultArea, 0.1);	}

	@Test
	void testPerimeter() {
		Rect2D rect = new Rect2D(p1, p3);

		double width = Math.abs( p1.ix() - p3.ix());
		double height = Math.abs(p1.iy() - p3.iy());
		double expectedPerimeter = (width + height) * 2;
		double resultPerimeter = rect.perimeter();

		assertEquals(expectedPerimeter, resultPerimeter, 0.1);	}

	@Test
	void testMove() {
		Point2D p1 = new Point2D(this.p1);
		Point2D p2 = new Point2D(this.p3);
		Rect2D rect = new Rect2D(p1, p2);

		Point2D vec = new Point2D(p6);
		Point2D expectedP1 = new Point2D(p1.ix() + vec.ix(), p1.iy() + vec.iy());
		Point2D expectedP2 = new Point2D(p2.ix() + vec.ix(), p2.iy() + vec.iy());

		rect.move(vec);
		Point2D[] points = rect.getPoints();
		Point2D movedP1 = points[0];
		Point2D movedP2 = points[1];

		assertEquals(expectedP1.ix(), movedP1.ix());
		assertEquals(expectedP1.iy(), movedP1.iy());
		assertEquals(expectedP2.iy(), movedP2.iy());

	}

	@Test
	void testCopy() {
		Point2D p1 = new Point2D(1, 2);
		Point2D p2 = new Point2D(3, 4);
		Rect2D rect = new Rect2D(p1, p2);

		Rect2D copiedRect = (Rect2D) rect.copy();

		Point2D[] originalPoints = rect.getPoints();
		Point2D[] copiedPoints = copiedRect.getPoints();

		assertNotSame(rect, copiedRect);
		assertArrayEquals(originalPoints, copiedPoints);	}

	@Test
	void testScale() {
		Rect2D r1 = new Rect2D(p5, p7);                                     // rect from 2 points
	        r1.scale(new Point2D(0,0),0.9);
	        assertEquals(2.7, r1.getPoints()[1].x(),0.001);		
	        assertEquals(0, r1.getPoints()[1].y(), 0.001);		
	        assertEquals(2.7, r1.getPoints()[0].x(), 0.001);		
	       assertEquals(2.7, r1.getPoints()[0].y(), 0.001);

	}

	


	@Test
	void testRotate() {
		Rect2D Rect = new Rect2D(new Point2D(p1), new Point2D(p3));

		    // Rotate the segment 90 degrees around the origin
		Rect.rotate(new Point2D(p4), 0.5*Math.PI);

		    // The start and end points of the segment should now be (-2, 1) and (-4, 3) respectively
		    Point2D[] points = Rect.getPoints();
		    assertEquals(new Point2D(5, 5), points[0]);
		    assertEquals(new Point2D(1, 5), points[1]);	
	}

	@Test
	void testGetPoints() {
		Point2D p1 = new Point2D(1, 1);
	    Point2D p2 = new Point2D(5, 5);
	    Rect2D rect = new Rect2D(p1, p2);

	    Point2D[] points = rect.getPoints();

	    // check that the array of points has the correct length
	    assertEquals(4, points.length);

	    // check that the first point in the array is the same as the one passed to the constructor
	    assertEquals(p1.x(), points[0].x(), 0.01);
	    assertEquals(p1.y(), points[0].y(), 0.01);

	    // check that the last point in the array is the same as the one passed to the constructor
	    assertEquals(p2.x(), points[2].x(), 0.01);
	    assertEquals(p2.y(), points[2].y(), 0.01);	}

}
