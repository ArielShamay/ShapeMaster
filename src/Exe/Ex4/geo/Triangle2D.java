package Exe.Ex4.geo;

import Exe.Ex4.Ex4_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle2D implements GeoShapeable{
	private Point2D _p1;
	private Point2D _p2;
	private Point2D _p3;
	
	
	public Triangle2D(Point2D p1, Point2D p2, Point2D p3) {
		
		this._p1 = new Point2D(p1);
		this._p2 = new Point2D(p2);
		this._p3 = new Point2D(p3);
		
	}
	public double Smallarea(Point2D p1, Point2D p2, Point2D p3) {
		double ans = Math.abs((p1.x() *(p2.y()-p3.y())+p2.x()*(p3.y()-p1.y())+p3.x()*(p1.y()-p2.y()))/ 2);
		return ans;
	}
	 public String toString(){
	     return _p1.toString()+", "+_p2.toString()+", "+_p3.toString();
	    }
	@Override
	public boolean contains(Point2D ot) {
		// TODO Auto-generated method stub
		 double a = Smallarea(_p1,_p2,ot);
		double b = Smallarea(_p2,_p3,ot);
		double c = Smallarea(_p3,_p1,ot);
		double mid = a+b+c;
		double origen = Smallarea(_p1,_p2,_p3);
		if (Math.abs(origen-mid)<Ex4_Const.EPS) {
			return true;
		}else
		return false;
	}

	@Override
	public double area() {
		
		
		double ans = Smallarea(_p1, _p2, _p3);
		
		return ans;
	}

	@Override
	public double perimeter() {
		// TODO Auto-generated method stub
		double long1 = _p1.distance(_p2);
		double long2 = _p2.distance(_p3);
		double long3 = _p3.distance(_p1);
		double ans = long1+long2+long3;
		return ans;
	}

	@Override
	public void move(Point2D vec) {
		// TODO Auto-generated method stub
		_p1.move(vec);
		_p2.move(vec);
		_p3.move(vec);
	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		return new Triangle2D(_p1, _p2, _p3);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		_p1.scale(center, ratio);
		_p2.scale(center, ratio);
		_p3.scale(center, ratio);
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		Point2D[] ans = new Point2D[3];
		ans[0] =new Point2D(this._p1);
		ans[1] =new Point2D(this._p2);
		ans[2] =new Point2D(this._p3);
		ans[0].rotate(center, angleDegrees);
		ans[1].rotate(center, angleDegrees);
		ans[2].rotate(center, angleDegrees);
		_p1 = ans [0];
		_p2 = ans[1];
		_p3 = ans [2];
	}

	@Override
	public Point2D[] getPoints() {
		// TODO Auto-generated method stub
		Point2D[] ans = new Point2D[3];
		ans[0] =new Point2D(this._p1);
		ans[1] =new Point2D(this._p2);
		ans[2] =new Point2D(this._p3);
		return ans;
	}
	
}
