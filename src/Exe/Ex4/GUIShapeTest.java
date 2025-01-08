package Exe.Ex4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import Exe.Ex4.GUIShape;
import Exe.Ex4.GUI_Shapeable;
import Exe.Ex4.ShapeCollection;
import Exe.Ex4.ShapeCollectionable;
import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Triangle2D;

class GUIShapeTest {
	Exe.Ex4.geo.Point2D p1=new Exe.Ex4.geo.Point2D(1,1);
	Exe.Ex4.geo.Point2D p2=new Exe.Ex4.geo.Point2D(1,8);
	Exe.Ex4.geo.Point2D p3=new Exe.Ex4.geo.Point2D(2,9);
	Exe.Ex4.geo.Point2D p4=new Exe.Ex4.geo.Point2D(2,2);
	Exe.Ex4.geo.Point2D p5=new Exe.Ex4.geo.Point2D(3,2);
	Exe.Ex4.geo.Point2D p6=new Exe.Ex4.geo.Point2D(10,5);
	Exe.Ex4.geo.Point2D a1= new Exe.Ex4.geo.Point2D(5,5);
	Exe.Ex4.geo.Rect2D rec1=new Exe.Ex4.geo.Rect2D(p1, p2);
	Exe.Ex4.geo.Rect2D rec2=new Exe.Ex4.geo.Rect2D(p3, p4);
	private Color _color=Color.blue;
	private Color _color1=Color.pink;

	GeoShapeable gs1=new Rect2D(p1, a1);
	GeoShapeable gs2=new Triangle2D(p2,p3,p4);

	GUIShape g1= new GUIShape(gs1,false,_color,1);
	private ShapeCollectionable _shapes =new ShapeCollection();


	@Test
	void testGetShape() {
		_shapes.add(g1);
		GUI_Shapeable sh = _shapes.get(0);
		if (sh==null) {
			fail("Not yet implemented");}
	}

	@Test
	void testIsFilled() {
		_shapes.add(g1);

		boolean a=_shapes.get(0).isFilled();
		if(a==true) {
			fail("Not yet implemented");}
	}

	@Test
	void testSetFilled() {
		_shapes.add(g1);

		_shapes.get(0).setFilled(true);
		boolean a=_shapes.get(0).isFilled();
		if(a==false) {
			fail("Not yet implemented");}
	}

	@Test
	void testGetColor() {
		_shapes.add(g1);		//_shapes.get(0).getColor();
		assertEquals(_color.blue, _shapes.get(0).getColor());


	}

	@Test
	void testSetColor() {
		_shapes.add(g1);
		_shapes.get(0).setColor(_color1);
		assertEquals(_color.pink, _shapes.get(0).getColor());

	}

	@Test
	void testGetTag() {
		_shapes.add(g1);
		_shapes.get(0).getTag();
		assertEquals(1,_shapes.get(0).getTag());

	}

	@Test
	void testSetTag() {
		_shapes.add(g1);
		_shapes.get(0).setTag(2);
		assertEquals(2,_shapes.get(0).getTag());		
	}

	@Test
	void testCopy() {
		//_shapes.copy();
		//	ShapeCollectionable _shapes1 =_shapes.copy();
		//assertEquals(_shapes1.get(0).getTag(),_shapes.get(0).getTag());
		// Arrange
		Color c = Color.RED;
		Exe.Ex4.geo.Point2D hh8=new Exe.Ex4.geo.Point2D(1,2);

		Circle2D circle = new Circle2D(hh8, 5);
		GUIShape original = new GUIShape(circle, true, c, 1);

		// Act
		GUI_Shapeable copy = original.copy();

		// Assert
		assertNotSame(original, copy);
		//assertEquals(original.getShape(), copy.getShape());
		assertEquals(original.isFilled(), copy.isFilled());
		assertEquals(original.getColor(), copy.getColor());
		assertEquals(original.getTag(), copy.getTag());


	}

	@Test
	void testToString() {
		// Arrange
		Color c = Color.RED;
		Exe.Ex4.geo.Point2D hh8=new Exe.Ex4.geo.Point2D(1,2);

		Circle2D circle = new Circle2D(hh8, 5);
		GUIShape shape = new GUIShape(circle, true, c, 1);

		// Act
		String stringValue = shape.toString();

		// Assert
		assertTrue(stringValue.contains(shape.getClass().getSimpleName()));
		assertTrue(stringValue.contains(Integer.toString(c.getRGB())));
		assertTrue(stringValue.contains(Boolean.toString(shape.isFilled())));
		assertTrue(stringValue.contains(Integer.toString(shape.getTag())));
		assertTrue(stringValue.contains(circle.getClass().getSimpleName()));
		assertTrue(stringValue.contains(circle.toString()));
	}

	@Test
	void testIsSelected() {
		Color c = Color.RED;
		Exe.Ex4.geo.Point2D hh8=new Exe.Ex4.geo.Point2D(1,2);

		Circle2D circle = new Circle2D(hh8, 5);
		GUIShape shape = new GUIShape(circle, true, c, 1);

		// Assert
		assertFalse(shape.isSelected());

		// Act
		shape.setSelected(true);

		// Assert
		assertTrue(shape.isSelected());

		// Act
		shape.setSelected(false);

		// Assert
		assertFalse(shape.isSelected());
	}

	@Test
	void testSetSelected() {
		// Arrange
		Color c = Color.RED;
		Exe.Ex4.geo.Point2D hh8=new Exe.Ex4.geo.Point2D(1,2);

		Circle2D circle = new Circle2D(hh8, 5);
		GUIShape shape = new GUIShape(circle, true, c, 1);

		// Act
		shape.setSelected(true);

		// Assert
		assertTrue(shape.isSelected());

		// Act
		shape.setSelected(false);

		// Assert
		assertFalse(shape.isSelected());
	}

	@Test
	void testSetShape() {
		// Arrange
		Color c = Color.RED;
		Exe.Ex4.geo.Point2D hh8=new Exe.Ex4.geo.Point2D(1,2);

		Circle2D circle = new Circle2D(hh8, 5);
		GUIShape shape = new GUIShape(circle, true, c, 1);

		// Act
		Exe.Ex4.geo.Point2D hh9=new Exe.Ex4.geo.Point2D(1,1);
		Exe.Ex4.geo.Point2D hh10=new Exe.Ex4.geo.Point2D(3,4);

		Rect2D rect=new Rect2D(hh10, hh9);
		//Rect2D rect = new Rect2D(new Point2D(1, 1), 2, 3);
		shape.setShape(rect);

		// Assert

	}

}
