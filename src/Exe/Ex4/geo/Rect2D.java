package Exe.Ex4.geo;

/**
 * This class represents a 2D rectangle (NOT necessarily axis parallel - this shape can be rotated!)
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect2D implements GeoShapeable {
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	private Point2D _p4;
	private Point2D _center;
	private double widte;
	private double height;

	public Rect2D(Point2D p1, Point2D p2) {
		// TODO Auto-generated constructor stub
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(_p1.x(),_p2.y());
		this._p4 = new Point2D(_p2.x(),_p1.y());
		this._center = new Point2D ((_p1.ix()+_p2.ix())/2, _p1.y()+ _p2.iy()/2);
		this.widte = _p1.distance(_p4);
		this.height = _p4.distance(p2);

	}
	 public String toString(){
	     return _p1.toString()+", "+_p4.toString() + ", " +_p2.toString()+", "+_p3.toString();
	    
	    }
	 
	 
	public boolean stillRect() {
		if (_p1.ix() == _p3.ix() && _p2.ix() == _p4.ix() && _p1.ix() == _p4.iy() && _p2.iy() == _p3.iy()){
		
		return true;
	}
		return false;
	}
	public Point2D GetCenter() {
		return this._center;
	}
	public double GetWidth() {
		return this.widte;
	}
	public double GetHeight() {
		return this.height;
	}

	


	@Override
	public boolean contains(Point2D ot) {
		int i, j;
		boolean result = false;
		Point2D[] con =  getPoints();
		for (i = 0, j = 4 - 1; i < 4; j = i++) {
			if ((con[i].y() > ot.y()) != (con[j].y() > ot.y()) && 
					(ot.x() < (con[j].x() - con[i].x()) * (ot.y() - con[i].y()) / (con[j].y() - con[i].y()) + con[i].x()))
			{
				result = !result;
			}
		}
		return result;	}


	@Override
	public double area() {
		// TODO Auto-generated method stub
		double ans = widte* height ;
		return ans;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double ans = widte *2  + height *2;
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
		_p4.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		return new Rect2D(_p1, _p2);
		
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
		_p4.scale(center, ratio);
		
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		_p1.rotate(center, angleDegrees);
		_p2.rotate(center, angleDegrees);
		_p3.rotate(center, angleDegrees);
		_p4.rotate(center, angleDegrees);

		
	}
	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[4];
		ans[0] =new Point2D(this._p1);
		ans[2] =new Point2D(this._p2);
		ans[1] =new Point2D(this._p3);
		ans[3] =new Point2D(this._p4);
		return ans;
		
	}

}
