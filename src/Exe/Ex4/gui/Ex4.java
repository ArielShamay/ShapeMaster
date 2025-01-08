

package Exe.Ex4.gui;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Exe.Ex4.Ex4_Const;
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
import Exe.Ex4.geo.ShapeComp;
import Exe.Ex4.geo.Triangle2D;

/**    
 * ID1:207565573, ID2:342753688
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI{
	private  ShapeCollectionable _shapes = new ShapeCollection();
	private  GUI_Shapeable _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private  Point2D _p1;
	private  Point2D _p2;
	ArrayList<Point2D> vertexs = new ArrayList<Point2D>();

	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}
	public void init(ShapeCollectionable s) {
		if(s==null) {_shapes = new ShapeCollection();}
		else {_shapes = s.copy();}
		GUI_Shapeable _gs = null;
		Polygon2D _pp = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		Point2D _p1 = null;
	}
	public GUI_Shapeable get_gs() {
		return this._gs;
		
	}
	
	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}
	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}
	private static void drawShape(GUI_Shapeable g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if(g.isSelected()) {StdDraw_Ex4.setPenColor(Color.gray);}
		GeoShapeable gs = g.getShape();
		boolean isFill = g.isFilled();
		if(gs instanceof Circle2D) {
			Circle2D c = (Circle2D)gs;
			Point2D cen = c.getPoints()[0];
			double rad = c.getRadius();
			if(isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			}
			else { 
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		if(gs instanceof Segment2D) {
			Segment2D c = (Segment2D)gs;
			Point2D start = c.getPoints()[0];
			Point2D dest = c.getPoints()[1];
			StdDraw_Ex4.line(start.x(),start.y(), dest.x(),dest.y());
		}
		if(gs instanceof Rect2D) {
			Rect2D r = (Rect2D)gs;
			if (r.stillRect()) {
				Point2D center = r.GetCenter();
				double width = r.GetWidth();
				double height = r.GetHeight();
			
			if(isFill) {
				StdDraw_Ex4.filledRectangle(center.x(),center.y(), width/2, height/2);
			}
			else { 

				StdDraw_Ex4.rectangle(center.x(),center.y(), width/2, height/2);
			}
		}
			else {
				double [] Xlist = new double [4];
				double [] Ylist = new double [4];
				for (int i = 0; i <4; i++) {
					Xlist [i] = r.getPoints()[i].x();
					Ylist[i] = r.getPoints()[i].y();
				}
				if(isFill) {
					StdDraw_Ex4.filledPolygon(Xlist, Ylist);
				}else 
				{ 
					StdDraw_Ex4.polygon(Xlist, Ylist);
				}
			}
		}
		if(gs instanceof Triangle2D) {
			Triangle2D t = (Triangle2D)gs;
			Point2D p1 = t.getPoints()[0];
			Point2D p2 = t.getPoints()[1];
			Point2D p3 = t.getPoints()[2];
			double [] Xlist = {p1.x(),p2.x(),p3.x()};
			double [] Ylist = {p1.iy(), p2.y(),p3.y()};
			if(isFill) {
				StdDraw_Ex4.filledPolygon(Xlist, Ylist);
			}else 
			{ 
				StdDraw_Ex4.polygon(Xlist, Ylist);
			}
		}
		if (gs instanceof Polygon2D)
		{
			int size = ((Polygon2D)gs).vertexs.length;
			double [] Xlist = new double[size];
			double [] Ylist = new double[size];
			for (int i = 0; i < size; i++) {
				Xlist[i] = ((Polygon2D)gs).vertexs[i].x();
				Ylist[i] = ((Polygon2D)gs).vertexs[i].y();
			}
			
			if(isFill)
			{
				StdDraw_Ex4.filledPolygon(Xlist, Ylist);
			}
			else 
			{
				StdDraw_Ex4.polygon(Xlist, Ylist);
			}
		}

	}
	private void setColor(Color c) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}
	private void setFill() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll();}
		if(_mode.equals("ByArea")) {
			_shapes.sort(ShapeComp.CompByArea);
		}
		if(_mode.equals("ByAntiArea")) {
			_shapes.sort(ShapeComp.CompByAntiArea);
		}
		if(_mode.equals("ByToString")) {
			_shapes.sort(ShapeComp.CompByToString);
		}
		if(_mode.equals("ByAntiToString")) {
			_shapes.sort(ShapeComp.CompByAntyToString);
		}
		if(_mode.equals("ByPerimeter")) {
			_shapes.sort(ShapeComp.CompByPerimeter);
		}
		if(_mode.equals("ByAntiPerimeter")) {
			_shapes.sort(ShapeComp.CompByAntiPerimeter);
		}
		if(_mode.equals("ByTag")) {
			_shapes.sort(ShapeComp.CompByTag);
		}
		if(_mode.equals("ByAntiTag")) {
			_shapes.sort(ShapeComp.CompByAntiTag);
		}

		if(_mode.equals("Remove")) {
			
				remove();
		}	
		if(_mode.equals("All")) {
			selectAll();
		}
		if(_mode.equals("Anti")) {
			selectAnti();
		}
		if(_mode.equals("None")) {
			selectNone();
		}
		if(_mode.equals("Save")) {
			save();
		}
		if(_mode.equals("Load")) {
			load();
		}
		if(_mode.equals("Info")) {
			getInfo();
			String info = getInfo();
			System.out.println(info);
		}

		drawShapes();

	}


	public void mouseClicked(Point2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Segment")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Rect")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}
			else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
			}
		}
		if(_mode.equals("Triangle")) {
			if(_gs==null) {
				_p1 = new Point2D(p);
			}else if(_p2 == null) {
				_p2 = new Point2D(p);
			}else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				_p2 = null;
			}
		}
		if(_mode.equals("Polygon")) {
			vertexs.add(p);
		}
		
		if(_mode.equals("Move")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}
		if(_mode.equals("Copy")) {
			if(_p1==null) {_p1 = new Point2D(p);}
			else {
				_p1 = new Point2D(p.x()-_p1.x(), p.y()-_p1.y());
				copy();
				_p1 = null;
			}
		}

		
		if (_mode.equals("Rotate"))
		{
			if (_p1 == null)
			{
				_p1 = p;
			}
			else
			{
				GeoShapeable [] shapes = getSelected();
				for (int i = 0; i < shapes.length; i++)
				{
					double angle = Math.atan2(p.y() - _p1.y(), p.x() - _p1.x());
					if (angle < 0)
						angle += 2 * Math.PI;
					shapes[i].rotate(_p1, angle);
				}
				_p1 = null;
			}
		}
	
	

		if(_mode.equals("Point")) {
			select(p);
		}
		
		if(_mode.equals("Scale_90%"))
		{
			GeoShapeable [] shapes = getSelected();
			for (int i = 0; i < shapes.length; i++)
			{
				shapes[i].scale(p, 0.9);
			}
		}
		if(_mode.equals("Scale_110%"))
		{
			GeoShapeable [] shapes = getSelected();
			for (int i = 0; i < shapes.length; i++)
			{
				shapes[i].scale(p, 1.1);
			}
		}
		
		drawShapes();
	}
	
	private GeoShapeable [] getSelected()
	{
		ArrayList<GeoShapeable> shapes = new ArrayList<GeoShapeable>();
		for (int i = 0; i < _shapes.size(); i++)
		{
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected())
			{
				shapes.add(s.getShape());
			}
		}
		return shapes.toArray(new GeoShapeable[0]);
	}

	private void select(Point2D p) {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void selectAll() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(!s.isSelected()) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	private void selectAnti() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			s.setSelected(!s.isSelected());
		}
	}

	private void selectNone() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			if(s.isSelected()) {
				s.setSelected(!s.isSelected());
			}
		}
	}
	private void move() {
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.move(_p1);
			}
		}
	}
	private void remove() {
		int i =0;
		while(i<_shapes.size()) {
			GUI_Shapeable s = _shapes.get(i);
			if (s.isSelected()) {
				_shapes.removeElementAt(i);
			}
			else
			{
				i++;
			}
		}
	}
	
	private void copy() {
		int origen =  _shapes.size();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(s.isSelected() && g!=null) {
				GUI_Shapeable news = s.copy();
				GeoShapeable newg = news.getShape();
				newg.move(_p1);
				_shapes.add(news);
				
			}
		}
	}
	private void save() {
	    try {
	        FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
	        fileDialog.setVisible(true);

	        String fileName = fileDialog.getFile();
	        if (fileName == null) {
	            return;
	        }

	        String directory = fileDialog.getDirectory();
	        String fullPath = directory + File.separator + fileName;
	        _shapes.save(fullPath);
	    } catch (Exception e) {
	        System.out.println("Error occurred while saving the file: " + e.getMessage());
	    }
	}
	private void load() {
	    FileDialog fileDialog = new FileDialog(new Frame(), "Please select a file to load", FileDialog.LOAD);
	    fileDialog.setVisible(true);
	    String fileName = fileDialog.getFile();
	    if (fileName == null) {
	        return;
	    }

	    String directory = fileDialog.getDirectory();
	    String fullPath = directory + File.separator + fileName;
	    this._shapes.load(fullPath);
	}

	
	
	public void mouseRightClicked(Point2D p) {
		System.out.println("right click!");
		
		if(_mode.equals("Polygon"))
		{
			_gs.setColor(_color);
			_gs.setFilled(_fill);
			_shapes.add(_gs);
			_gs = null;
			
			
			vertexs.clear();
			drawShapes();
		}
	}
	
	public void mouseMoved(MouseEvent e) {
		if ((_mode.equals("Polygon")) && (vertexs.size() >= 1))
		{
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			vertexs.add(new Point2D(x1, y1));
			Point2D [] v = vertexs.toArray(new Point2D[0]);
			GeoShapeable gs = new Polygon2D(v);
			vertexs.remove(vertexs.size() - 1);
			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
		else if(_p1!=null) {
			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShapeable gs = null;
			Point2D p = new Point2D(x1,y1);
			
			if(_mode.equals("Circle")) {
				double r = _p1.distance(p);
				gs = new Circle2D(_p1,r);
			}
			if(_mode.equals("Segment")) {

				gs = new Segment2D(_p1,p);
			}
			if(_mode.equals("Rect")) {

				gs = new Rect2D(_p1,p);
			}
			if(_mode.equals("Triangle")) {
				if(_p2 != null) {
					gs = new Triangle2D(_p1,_p2,p);
				}else {
					gs = new Segment2D(_p1,p);
				}
			}

			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}
	@Override
	public ShapeCollectionable getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}
	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shapeable s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		System.out.println(ans);
		return ans;
	}
}
