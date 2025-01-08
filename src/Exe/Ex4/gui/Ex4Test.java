package Exe.Ex4.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

class Ex4Test {
	Ex4 ex4 = Ex4.getInstance();
//	private  GUI_Shapeable _gs;
	@Test
	void testInit() {

		//		ShapeCollectionable shapes = new ShapeCollection();
		//		GeoShapeable s = new Segment2D(new Point2D(1,2),new Point2D(3,4));
		//		GUIShape gs = new GUIShape (s ,true, Color.pink,0);
		//		shapes.add(gs);
		//		Ex4 ex4 = Ex4.getInstance();
		//		ex4.init(shapes);
		//		ShapeCollectionable shapes2 = ex4.getShape_Collection();
		//		assertEquals(shapes.toString()," ");
		//		

		GeoShapeable c = new Circle2D(new Point2D(1,2),5);
		GeoShapeable s = new Segment2D(new Point2D(1,2),new Point2D(3,4));
		GeoShapeable r = new Rect2D(new Point2D(1,2),new Point2D(3,4));
		GeoShapeable t = new Triangle2D(new Point2D(1,2),new Point2D(3,4),new Point2D(5,6));
		Point2D[] points =  {new Point2D(1,2),new Point2D(3,4),new Point2D(5,6)};
		GeoShapeable p = new Polygon2D(points); 
		ShapeCollectionable shapes = new ShapeCollection();
		shapes.add(new GUIShape(c ,true, Color.BLACK,0));
		shapes.add(new GUIShape(s ,true, Color.BLUE,1));
		shapes.add(new GUIShape(r,false, Color.GREEN ,2 ));
		shapes.add(new GUIShape(t, false, Color.RED ,3 ));
		shapes.add(new GUIShape(p ,true, Color.BLUE,4));
		Ex4 ex4 = Ex4.getInstance();
		ex4.init(shapes);
		ShapeCollectionable Nshapes = ex4.getShape_Collection();
		assertEquals(shapes.toString(),Nshapes.toString());
		assertEquals(shapes.size(),Nshapes.size());
	}


		// Verify that the internal state of ex4 is as expected
		//assertEquals(shapes.toString(),"GUIShape,-16777216,true,0,Circle2D,1.0,2.0, 5.0GUIShape,-16776961,true,1,Segment2D,1.0,2.0, 3.0,4.0GUIShape,-16711936,false,2,Rect2D,1.0,2.0, 3.0,2.0, 3.0,4.0, 1.0,4.0GUIShape,-65536,false,3,Triangle2D,1.0,2.0, 3.0,4.0, 5.0,6.0GUIShape,-16776961,true,4,Polygon2D, 1.0,2.0, 3.0,4.0, 5.0,6.0, ");
	
	@Test
	void testGetShape_collectin() {

		ShapeCollectionable shapes = new ShapeCollection();
		GeoShapeable r = new Rect2D(new Point2D(1,2),new Point2D(3,4));
		shapes.add(new GUIShape(r,false, Color.GREEN ,2 ));
		Ex4 ex4 = Ex4.getInstance();
		ex4.init(shapes);
		ShapeCollectionable Nshapes = ex4.getShape_Collection();
		assertEquals(shapes.toString(),Nshapes.toString());
		assertEquals(shapes.size(),Nshapes.size());
	}

	@Test
	void testGetInfo() {
		Ex4 instance = Ex4.getInstance();
		Point2D p1 = new Point2D(1, 2);
		Point2D p2 = new Point2D(4, 3);
		GeoShapeable s1 = new Segment2D(p1,p2);
		ShapeCollectionable shapes = instance.getShape_Collection();
		GUI_Shapeable gs1 = new GUIShape(s1, true, Color.black, 1);
		shapes.add(gs1);
		instance.init(shapes);
		Ex4 ex4 = Ex4.getInstance();
		assertEquals(ex4.getInfo(),instance.getInfo());
	}
	}


