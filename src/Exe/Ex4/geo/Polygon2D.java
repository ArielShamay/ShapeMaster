package Exe.Ex4.geo;

import Exe.Ex4.gui.Ex4;

/**
 * This class represents a 2D polygon, as in https://en.wikipedia.org/wiki/Polygon
 * This polygon can be assumed to be simple in terms of area and contains.
 * 
 * You should update this class!
 * @author boaz.benmoshe
 *
 */
public class Polygon2D implements GeoShapeable{
	public Point2D [] vertexs;

	public Polygon2D(Point2D [] vertexs)
	{
		this.vertexs = vertexs;
	}
	@Override
	public String toString(){
		 String ans = " ";
		 for (int i = 0; i < vertexs.length; i++) {
			ans = ans + vertexs[i].toString() + ", ";
		}
	     return ans;
	    }
	@Override
	public boolean contains(Point2D point) {
		int i, j;
		boolean result = false;

		for (i = 0, j = vertexs.length - 1; i < vertexs.length; j = i++) {
			if ((vertexs[i].y() > point.y()) != (vertexs[j].y() > point.y()) && 
					(point.x() < (vertexs[j].x() - vertexs[i].x()) * (point.y() - vertexs[i].y()) / (vertexs[j].y() - vertexs[i].y()) + vertexs[i].x()))
			{
				result = !result;
			}
		}
		return result;
	}
	
	@Override
	public double area() {
		int size = this.vertexs.length;
		double [] Xlist = new double[size];
		double [] Ylist = new double[size];
		for (int i = 0; i < size; i++) {
			Xlist[i] = vertexs[i].x();
			Ylist[i] = vertexs[i].y();
		}
		double area = 0.0;
		int j = vertexs.length - 1;
		for (int i = 0; i <vertexs.length; i++)
		{
			area += (Xlist[j] + Xlist[i]) * (Ylist[j] - Ylist[i]);
			j = i;
			
		}	
			return Math.abs(area / 2.0);
	}
	

	@Override
	public double perimeter() {
		int size = this.vertexs.length;
		double preim = 0.0;
		for (int i = 0; i < size-1; i++) {
			preim += vertexs[i].distance(vertexs[i+1]);
		}
		preim += vertexs[size -1].distance(vertexs[0]);
		return preim;
	}

	@Override
	public void move(Point2D vec) {
		for (int i = 0; i < vertexs.length; i++) {
			vertexs[i].move(vec);
		}

	}

	@Override
	public GeoShapeable copy() {
		// TODO Auto-generated method stub
		Point2D[] points  = new Point2D[vertexs.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point2D(vertexs[i].x(),vertexs[i].y());
		}
		
		return new Polygon2D(points);
	}

	@Override
	public void scale(Point2D center, double ratio) {
		for (int i = 0; i < vertexs.length; i++) {
			vertexs[i].scale(center, ratio);
		}
	}

	@Override
	public void rotate(Point2D center, double angleDegrees) {
		for (int i = 0; i	 < vertexs.length; i++) {
			vertexs[i].rotate(center, angleDegrees);
		}
	}

	@Override
	public Point2D[] getPoints() {
		
		
		
		
		return vertexs.clone();
	}

}
