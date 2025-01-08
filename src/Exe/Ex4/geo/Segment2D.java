package Exe.Ex4.geo;



/**
 * This class represents a 2D segment on the plane, 
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */

public class Segment2D implements GeoShapeable{
	private Point2D _start;
	private Point2D _dest;
	private double slope;
	
	public static int deltaX(Point2D p1, Point2D p2) {
		return p2.ix() - p1.ix();
	}
	public static int deltaY(Point2D p1, Point2D p2) {
		return p2.iy() - p1.iy();
	}
	
	public Segment2D(Point2D start,Point2D dest) {
	this._start = new Point2D(start);
	this._dest = new Point2D(dest);
	double slope = (double)deltaY(start, dest) / (double)deltaX(start, dest);
	
	}
	 public String toString(){
	     return _start.toString()+", "+_dest.toString();
	    }
	@Override
	public boolean contains(Point2D ot) {
		double dist = (double)deltaY(ot, _dest) / (double)deltaX(ot, _dest);
		if( dist == slope) {
		return true; 
	} else
		return false;
	}

	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		
		double res = _start.distance(_dest)*2;
		
		return res;
	}

	@Override
	public void move(Point2D vec) {
		
		_dest.move(vec);
		_start.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		
		return new Segment2D(_start, _dest);
		
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_start.scale(center, ratio);
		_dest.scale(center, ratio);		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		_start.rotate(center, angleDegrees);
		_dest.rotate(center, angleDegrees);
		
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[2];
		ans[0] =new Point2D(this._start);
		ans[1] =new Point2D(this._dest);
		return ans;
		
	}
	
}