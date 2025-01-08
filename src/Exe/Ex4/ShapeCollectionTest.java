package Exe.Ex4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;

class ShapeCollectionTest {


	@Test
	void testShapeCollection() {

		ShapeCollection sc = new ShapeCollection();
		assertNotNull(sc);
		assertEquals(0, sc.size());
	}


	@Test
	void testGet() {

		ShapeCollection sc = new ShapeCollection();
		GUI_Shapeable shape1 = new GUIShape(new Circle2D(new Point2D(5, 5), 5), true, Color.RED, 1);
		GUI_Shapeable shape2 = new GUIShape(new Circle2D(new Point2D(10, 10), 10), true, Color.BLUE, 1);
		sc.add(shape1);
		sc.add(shape2);
		assertEquals(shape1, sc.get(0));
		assertEquals(shape2, sc.get(1));
	}

	@Test
	void testSize() {

		// Create a shape collection with some test data
		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(new Circle2D(new Point2D(5, 5), 5), true, Color.RED, 1));
		sc.add(new GUIShape(new Circle2D(new Point2D(10, 10), 3), true, Color.BLUE, 1));
		sc.add(new GUIShape(new Circle2D(new Point2D(8, 8), 4), true, Color.YELLOW, 1));

		// check the size of the collection
		assertEquals(3, sc.size());
	}


	@Test
	public void testRemoveElementAt() {
		// Create a shape collection with some test data
		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(new Circle2D(new Point2D(5, 5), 2), true, Color.RED, 1));
		sc.add(new GUIShape(new Polygon2D(new Point2D[] {
				new Point2D(1, 1), new Point2D(2, 3),
				new Point2D(3, 4), new Point2D(4, 4)
		}), false, Color.BLUE, 1));
		sc.add(new GUIShape(new Triangle2D(new Point2D(1,1), new Point2D(2,2), new Point2D(3,3)), false, Color.YELLOW, 1));

		// Remove the first element
		sc.removeElementAt(0);
		// Verify the result
		assertEquals(2, sc.size());
		assertTrue(sc.get(0).getShape() instanceof Polygon2D);
	}


	@Test
	void testAddAt() {

		ShapeCollection sc = new ShapeCollection();
		GUI_Shapeable shape1 = new GUIShape(new Circle2D(new Point2D(5, 5), 5), true, Color.RED, 1);
		GUI_Shapeable shape2 = new GUIShape(new Circle2D(new Point2D(10, 10), 10), true, Color.BLUE, 1);
		sc.add(shape1);
		sc.addAt(shape2, 0);
		assertEquals(2, sc.size());
		assertEquals(shape2, sc.get(0));
		assertEquals(shape1, sc.get(1));
	}

	@Test
	void testAdd() {

		ShapeCollection sc = new ShapeCollection();
		GUI_Shapeable shape = new GUIShape(new Circle2D(new Point2D(5, 5), 5), true, Color.RED, 1);
		sc.add(shape);
		assertEquals(1, sc.size());
		assertEquals(shape, sc.get(0));
	}

	@Test
	void testCopy() {


		ShapeCollection sc = new ShapeCollection();
		GUI_Shapeable shape1 = new GUIShape(new Circle2D(new Point2D(5, 5), 5), true, Color.RED, 1);
		GUI_Shapeable shape2 = new GUIShape(new Circle2D(new Point2D(10, 10), 10), true, Color.BLUE, 1);
		sc.add(shape1);
		sc.add(shape2);
		sc.copy();
		assertEquals(shape1,sc.copy().get(0));
		assertEquals(shape2,sc.copy().get(1));
	}


	@Test
	void testSort() {

		// Create a shape collection with some test data
		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(new Circle2D(new Point2D(5, 5), 5), true, Color.RED, 1));
		sc.add(new GUIShape(new Circle2D(new Point2D(10, 10), 3), true, Color.BLUE, 1));
		sc.add(new GUIShape(new Circle2D(new Point2D(8, 8), 4), true, Color.YELLOW, 1));

		// sort the collection by the area of the shapes
		sc.sort(new Comparator<GUI_Shapeable>() {
			@Override
			public int compare(GUI_Shapeable o1, GUI_Shapeable o2) {
				double area1 = o1.getShape().area();
				double area2 = o2.getShape().area();
				return Double.compare(area1, area2);
			}
		});

		// Verify the result
		assertTrue(sc.get(0).getShape().area() <= sc.get(1).getShape().area());
		assertTrue(sc.get(1).getShape().area() <= sc.get(2).getShape().area());
		// colors will not change by sorting, so no need to check for colors
	}

	@Test
	public void testRemoveAll() {
		// Create a shape collection with some test data
		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(new Circle2D(new Point2D(5, 5), 2), true, Color.RED, 1));
		sc.add(new GUIShape(new Polygon2D(new Point2D[] {
				new Point2D(1, 1), new Point2D(2, 3),
				new Point2D(3, 4), new Point2D(4, 4)
		}), false, Color.BLUE, 1));
		sc.add(new GUIShape(new Triangle2D(new Point2D(1,1), new Point2D(2,2), new Point2D(3,3)), false, Color.YELLOW, 1));
		// Call the removeAll method
		sc.removeAll();
		// Verify the result
		assertEquals(0, sc.size());
	}


	@Test
	void testSave() {
		// Create a new ShapeCollection
		ShapeCollection sc = new ShapeCollection();

		// Add a new Circle2D shape to the ShapeCollection
		Circle2D c1 = new Circle2D(new Point2D(0,0), 1);
		sc.add(new GUIShape(c1, true, Color.BLACK, 0));

		// Add a new Triangle2D shape to the ShapeCollection
		Point2D p1 = new Point2D(0,0);
		Point2D p2 = new Point2D(1,0);
		Point2D p3 = new Point2D(0,1);
		Triangle2D t1 = new Triangle2D(p1, p2, p3);
		sc.add(new GUIShape(t1, true, Color.BLUE, 0));

		// Call the save method and provide a file path
		String filePath = "shape_collection.txt";
		sc.save(filePath);

		// Check that the file was created and contains the expected data
		File file = new File(filePath);
		assertTrue(file.exists());
		assertTrue(file.isFile());
		assertTrue(file.canRead());
		assertTrue(file.length() > 0);

		// Read the file and check that it contains the expected data
		try {
			Scanner scanner = new Scanner(file);
			String line1 = scanner.nextLine();
			String line2 = scanner.nextLine();
			assertTrue(line1.contains("Circle2D") && line1.contains("0") && line1.contains("1.0"));
			assertTrue(line2.contains("Triangle2D") && line2.contains("0.0,0.0") && line2.contains("1.0,0.0"));
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("Unexpected exception");
		}
	}


	@Test
	void testLoad() {    

		String file = "testFile.txt";
		ShapeCollection sc = new ShapeCollection();

		// create a test file with some data
		try (PrintWriter writer = new PrintWriter(file)) {
			writer.println("Circle2D,255,true,2,some string,3.0,4.0,5.0");
			writer.println("Polygon2D,16711680,false,3,some string,1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// call the load method
		sc.load(file);

		// Verify that the shapes list has the expected number of elements
		assertEquals(2, sc.size());

		// check the properties of the first shape (a circle)
		GUI_Shapeable s1 = sc.get(0);
		assertTrue(s1.getShape() instanceof Circle2D);
		assertEquals(new Color(255), s1.getColor());
		assertTrue(s1.isFilled());


		// check the properties of the second shape (a polygon)
		GUI_Shapeable s2 = sc.get(1);
		assertTrue(s2.getShape() instanceof Polygon2D);
		assertEquals(new Color(16711680), s2.getColor());
		assertFalse(s2.isFilled());

		// clean-up
		new File(file).delete();
	}



	@Test
	void testGetBoundingBox() {

		Point2D p1 = new Point2D(0, 0);
		Point2D p2 = new Point2D(1, 1);
		Point2D p3 = new Point2D(2, 2);
		Point2D p4 = new Point2D(3, 3);
		Circle2D circle = new Circle2D(p1, 1);
		Polygon2D polygon = new Polygon2D(new Point2D[] {p2, p3, p4});
		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(circle, true, Color.BLACK, 0));
		sc.add(new GUIShape(polygon, true, Color.BLACK, 0));

		Rect2D boundingBox = sc.getBoundingBox();
		Point2D[] points = boundingBox.getPoints();

		assertEquals(-1, points[0].x());
		assertEquals(-1, points[0].y());
		assertEquals(-1, points[1].x());
		assertEquals(-1, points[1].y());
	}


	@Test
	public void testToString() {
		// Create a shape collection with some test data
		ShapeCollection sc = new ShapeCollection();
		sc.add(new GUIShape(new Circle2D(new Point2D(5, 5), 2), true, Color.RED, 1));
		sc.add(new GUIShape(new Polygon2D(new Point2D[] {
				new Point2D(1, 1), new Point2D(2, 3),
				new Point2D(3, 4), new Point2D(4, 4)
		}), false, Color.BLUE, 1));

		// Call the toString method
		String result = sc.toString();

		// Verify the result
		assertTrue(result.contains("Circle2D"));
		assertTrue(result.contains("Polygon2D"));
		assertTrue(result.contains(Integer.toString(Color.RED.getRGB())));
		assertTrue(result.contains(Integer.toString(Color.BLUE.getRGB())));
	}

}
