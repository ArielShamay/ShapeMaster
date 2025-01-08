
package Exe.Ex4;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

import javax.imageio.ImageIO;

import Exe.Ex4.geo.Circle2D;
import Exe.Ex4.geo.GeoShapeable;
import Exe.Ex4.geo.Point2D;
import Exe.Ex4.geo.Polygon2D;
import Exe.Ex4.geo.Rect2D;
import Exe.Ex4.geo.Segment2D;
import Exe.Ex4.geo.Triangle2D;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements ShapeCollectionable{
	private ArrayList<GUI_Shapeable> _shapes;
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shapeable>();
	}
	@Override
	public GUI_Shapeable get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shapeable removeElementAt(int i) {
		_shapes.remove(i);
		_shapes.trimToSize();
		return null;
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shapeable s, int i) {
		_shapes.add(i , s);
		_shapes.ensureCapacity(_shapes.size() + 1);
	}
	@Override
	public void add(GUI_Shapeable s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollectionable copy() {
		ShapeCollection deepCopy = new ShapeCollection();
		if(_shapes!=null) {
			for(int i =0; i<_shapes.size();i++) {
				deepCopy.add(_shapes.get(i).copy());
			}
			return deepCopy;
		}

		return null;

	}

	@Override
	public void sort(Comparator<GUI_Shapeable> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.removeAll(_shapes);


	}

	@Override
	public void save(String filePath) {
	    try (
	        FileOutputStream fileOutputStream = new FileOutputStream(new File(filePath));
	        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
	        Writer writer = new BufferedWriter(outputStreamWriter);
	    ) {
	        for (int i = 0; i < this._shapes.size(); i++) {
	            GeoShapeable shape = _shapes.get(i).getShape();

	            String points = shape.toString();
	            String shapeName = shape.getClass().getSimpleName();
	            Color color = _shapes.get(i).getColor();
	            boolean filled = _shapes.get(i).isFilled();
	            writer.write(_shapes.get(i).getClass().getSimpleName() + "," + color.getRGB() + "," + filled + "," + 0 + "," + shapeName + "," + points + "\n");
	        }
	    } catch (IOException e) {
	        System.out.println("Error occurred while saving the file: " + e.getMessage());
	    }
	}
	@Override
	public void load(String filePath) {
	    _shapes.clear();
	    try (Scanner scanner = new Scanner(new File(filePath))) {
	        while (scanner.hasNextLine()) {
	            String currentLine = scanner.nextLine();
	            String[] line = currentLine.split(",");
	            GeoShapeable shape = null;
	            if (currentLine.contains("Circle2D")) {
	                Point2D center = new Point2D(Double.parseDouble(line[5]), Double.parseDouble(line[6]));
	                shape = new Circle2D(center, Double.parseDouble(line[7]));
	            } else {
	                String[] strPoints = Arrays.copyOfRange(line, 5, line.length);
	                Point2D[] points = new Point2D[strPoints.length / 2];
	                for (int i = 0; i < strPoints.length / 2; i++) {
	                    points[i] = new Point2D(Double.parseDouble(strPoints[2 * i]), Double.parseDouble(strPoints[(2 * i) + 1]));
	                }
	                shape = new Polygon2D(points);
	            }
	            GUIShape guiShape = new GUIShape(shape, currentLine.contains("Segment2D") ? false : Boolean.parseBoolean(line[2]), new Color(Integer.parseInt(line[1])), Integer.parseInt(line[3]));
	            _shapes.add(guiShape);
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Error occurred while loading the file: " + e.getMessage());
	    }
	}	@Override
	public Rect2D getBoundingBox() {
		Rect2D ans = null;
		double lowX = 0 , lowY = 0 , upX = 0 ,  upY = 0;
		double finelLowX = 10, finelupX = 0,finelLowY= 10,  finelupY = 0;
		Point2D leftlow = new Point2D (0,0), rightup = new Point2D (0,0);
		Point2D [] tempAns;
		for (int i = 0; i < _shapes.size(); i++) {
			GUI_Shapeable s = _shapes.get(i);
			GeoShapeable g = s.getShape();
			if(g!= null && g instanceof Circle2D) {
				tempAns = g.getPoints();
				double rad = Math.abs(tempAns[1].y() - tempAns[0].y());
				lowX = tempAns [0].x()- rad;
				lowY = tempAns [0].y()- rad;
				upX = tempAns [0].x()- rad;
				upY = tempAns [0].y()- rad;
			}
			if(g!= null && !( g instanceof Circle2D)) {
				tempAns = g.getPoints();
				lowX = tempAns [0].x();
				lowY = tempAns [0].y();
				upX = tempAns [0].x();
				upY = tempAns [0].y();
				for (int j = 1; j < tempAns.length; j++) {
					
					if(lowY > tempAns[j].y()) {
						lowY = tempAns[j].y();
					}
					if(upY > tempAns[j].y()) {
						upY = tempAns[j].y();
					}
					if(lowX > tempAns[j].x()) {
						lowX = tempAns[j].x();
					}
					if(upX < tempAns[j].x()) {
						upX = tempAns[j].x();
					}
				}
			}
			if(finelLowY > lowY) {
				finelLowY = lowY;
			}
			if(finelLowX > lowX) {
				finelLowX = lowX;
			}
			if(finelupY > upY) {
				finelupY = upY;
			}
			
			leftlow = new Point2D (finelLowX, finelLowY);  
			rightup = new Point2D (finelupX, finelupY);

		}
		
		ans  = new Rect2D (leftlow,rightup );
		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}







}
