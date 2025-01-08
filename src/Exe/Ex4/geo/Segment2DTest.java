package Exe.Ex4.geo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.Ex4_Const;
import Exe.Ex4.gui.Ex4;

class Segment2DTest {
	Exe.Ex4.geo.Point2D p1=new Exe.Ex4.geo.Point2D(1,1);
	Exe.Ex4.geo.Point2D p2=new Exe.Ex4.geo.Point2D(3,1);
	Exe.Ex4.geo.Point2D p3=new Exe.Ex4.geo.Point2D(5,1);
	Exe.Ex4.geo.Point2D p4=new Exe.Ex4.geo.Point2D(3,3);

	@Test
	void testSegment2D() {
		  // Create a segment with start at (1, 2) and end at (3, 4)
        Segment2D segment = new Segment2D(new Point2D(p1), new Point2D(p3));

        // The toString method should return "(1.0,2.0), (3.0,4.0)"
        assertEquals("1.0,1.0, 5.0,1.0", segment.toString());
    }
	

	@Test
	void testToString() {
		Segment2D segment = new Segment2D(new Point2D(p1), new Point2D(p3));

        // The segment should contain the point (2, 3)
        assertTrue(segment.contains(new Point2D(3, 1)));

        // The segment should not contain the point (4, 5)
        assertFalse(segment.contains(new Point2D(4, 5)));
    
	}

	@Test
	void testContains() {
		 Segment2D segment = new Segment2D(new Point2D(p1), new Point2D(p3));

		    // The segment should contain the point (2, 3)
		    assertTrue(segment.contains(new Point2D(p2)));

		    // The segment should not contain the point (4, 5)
		    assertFalse(segment.contains(new Point2D(p4)));
	}

	

	@Test
	void testPerimeter() {
		 // Create a segment with start at (1, 2) and end at (3, 4)
        Segment2D segment = new Segment2D(new Point2D(p1), new Point2D(p3));

        // The perimeter of the segment should be 2 * sqrt(2)
        assertEquals(8, segment.perimeter(), 0.001);	}

	@Test
	void testMove() {
		  // Create a segment with start at (1, 2) and end at (3, 4)
        Segment2D segment = new Segment2D(new Point2D(1, 2), new Point2D(3, 4));

        // Move the segment by (1, 1)
        segment.move(new Point2D(1, 1));

        // The start and end points of the segment should now be (2, 3) and (4, 5) respectively
        Point2D[] points = segment.getPoints();
        assertEquals(new Point2D(2, 3), points[0]);
        assertEquals(new Point2D(4, 5), points[1]);
	}

	@Test
	void testCopy() {
		 Segment2D original = new Segment2D(new Point2D(1, 2), new Point2D(3, 4));

		    // Make a copy of the segment
		    Segment2D copy = (Segment2D) original.copy();

		    // The start and end points of the copy should be the same as the original
		    Point2D[] originalPoints = original.getPoints();
		    Point2D[] copyPoints = copy.getPoints();
		    assertArrayEquals(originalPoints, copyPoints);

		    // Modifying the original segment should not affect the copy
		    original.move(new Point2D(1, 1));
		    originalPoints = original.getPoints();
		    copyPoints = copy.getPoints();
		    assertNotEquals(originalPoints, copyPoints);	}

	@Test
	void testScale() {
		  Segment2D segment = new Segment2D(new Point2D(1, 2), new Point2D(3, 4));

		    // Scale the segment by a factor of 2 around the origin
		    segment.scale(new Point2D(0, 0), 2);

		    // The start and end points of the segment should now be (2, 4) and (6, 8) respectively
		    Point2D[] points = segment.getPoints();
		    assertEquals(new Point2D(2, 4), points[0]);
		    assertEquals(new Point2D(6, 8), points[1]);	}

	@Test
	void testRotate() {
		 Segment2D segment = new Segment2D(new Point2D(p1), new Point2D(p3));

		    // Rotate the segment 90 degrees around the origin
		    segment.rotate(new Point2D(p4), 0.5*Math.PI);

		    // The start and end points of the segment should now be (-2, 1) and (-4, 3) respectively
		    Point2D[] points = segment.getPoints();
		    assertEquals(new Point2D(5, 1), points[0]);
		    assertEquals(new Point2D(5, 5), points[1]);	}

	@Test
	void testGetPoints() {
	    Point2D start = new Point2D(1, 2);
	    Point2D end = new Point2D(3, 4);
	    Segment2D segment = new Segment2D(start, end);

	    Point2D[] points = segment.getPoints();
	    Point2D startPoint = points[0];
	    Point2D endPoint = points[1];

	    assertEquals(start.ix(), startPoint.ix());
	    assertEquals(start.iy(), startPoint.iy());
	    assertEquals(end.ix(), endPoint.ix());
	    assertEquals(end.iy(), endPoint.iy());
	}

}
