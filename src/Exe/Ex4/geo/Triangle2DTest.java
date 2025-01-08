package Exe.Ex4.geo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;

class Triangle2DTest {
	Exe.Ex4.geo.Point2D p1=new Exe.Ex4.geo.Point2D(1,1);
	Exe.Ex4.geo.Point2D p2=new Exe.Ex4.geo.Point2D(1,8);
	Exe.Ex4.geo.Point2D p3=new Exe.Ex4.geo.Point2D(5,1);
	Exe.Ex4.geo.Triangle2D tri1=new Exe.Ex4.geo.Triangle2D(p2,p1,p3);
	Exe.Ex4.geo.Point2D p5=new Exe.Ex4.geo.Point2D(2,3);

	Exe.Ex4.geo.Point2D up=new Exe.Ex4.geo.Point2D(2,8);
	Exe.Ex4.geo.Point2D left=new Exe.Ex4.geo.Point2D(5,9);
	Exe.Ex4.geo.Point2D down=new Exe.Ex4.geo.Point2D(10,2);
	Exe.Ex4.geo.Triangle2D tri2=new Exe.Ex4.geo.Triangle2D(up,left,down);

	@Test
	void testContains() {
		boolean a=tri1.contains(p5);
		if(a!=true)	fail();
	}

	@Test
	void testArea() {
		double are=tri1.area();
		assertEquals(14, are);

	}

	@Test
	void testPerimeter() {
		double per=tri1.perimeter();
		assertEquals(19.06225774829855, per);

		//fail("Not yet implemented");
	}

	@Test
	void testMove() {
		Point2D p1 = new Point2D(1, 1);
		Point2D p2 = new Point2D(5, 1);
		Point2D p3 = new Point2D(5, 5);

		Triangle2D triangle = new Triangle2D(p1, p2, p3);

		// Test the move method
		Point2D vec = new Point2D(2, 2);
		Point2D[] expectedPointsAfterMove = {new Point2D(3, 3), new Point2D(7, 3), new Point2D(7, 7)};
		triangle.move(vec);
		Point2D[] returnedPoints = triangle.getPoints();
		assertArrayEquals(expectedPointsAfterMove, returnedPoints);

	}



	@Test
	void testCopy() {

	}

	@Test
	void testScale() {
		Exe.Ex4.geo.Point2D k1= new Exe.Ex4.geo.Point2D(1,1);
		Exe.Ex4.geo.Point2D k2= new Exe.Ex4.geo.Point2D(7,1);
		Exe.Ex4.geo.Point2D k3= new Exe.Ex4.geo.Point2D(7,7);
		Exe.Ex4.geo.Point2D cen= new Exe.Ex4.geo.Point2D(5,5);

		Triangle2D	trg1 = new Triangle2D(k1,k2,k3);
		Triangle2D	trg2 = new Triangle2D(k1,k2,cen);

		trg1.scale(cen, 0.9);
		trg2.scale(cen, 1.1);

		double area=trg1.area();
		double area2=trg2.area();


		assertEquals(area,14.58);
		assertEquals(area2,14.520000000000003);




	}



	@Test
	void testRotate() {

		Exe.Ex4.geo.Point2D k1= new Exe.Ex4.geo.Point2D(1,1);
		Exe.Ex4.geo.Point2D k2= new Exe.Ex4.geo.Point2D(7,1);
		Exe.Ex4.geo.Point2D k3= new Exe.Ex4.geo.Point2D(7,7);
		Exe.Ex4.geo.Point2D cen= new Exe.Ex4.geo.Point2D(5,5);

		Triangle2D	trg1 = new Triangle2D(k1,k2,k3);
		Triangle2D	trg2 = new Triangle2D(k1,k2,cen);
		Point2D []ans1=trg1.getPoints();
		Point2D []ans2=trg2.getPoints();


		assertEquals(ans1[0].x(),1);
		assertEquals(ans1[0].y(),1);
		assertEquals(ans1[1].x(),7);
		assertEquals(ans1[1].y(),1.0);
		assertEquals(ans1[2].x(),7);
		assertEquals(ans1[2].y(),7.0);


		assertEquals(ans2[0].x(),1.0);
		assertEquals(ans2[0].y(),1);
		assertEquals(ans2[1].x(),7);
		assertEquals(ans2[1].y(),1);
		assertEquals(ans2[2].x(),5);
		assertEquals(ans2[2].y(),5);



	}

	@Test
	void testGetPoints() {
		Point2D[] test2=new Point2D[5];
		test2=tri2.getPoints();
		double x=test2[0].x();
		double y=test2[0].y();
		assertEquals(2, x);
		assertEquals(8, y);
		double x1=test2[1].ix();
		double y1=test2[1].y();
		double x2=down.x();
		double y2=down.y();
		assertEquals(5, x1);
		assertEquals(9, y1);
		assertEquals(10, x2);
		assertEquals(2, y2);
	}



}