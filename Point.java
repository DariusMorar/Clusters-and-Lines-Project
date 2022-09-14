/**
 * wrapper class for point
 * 
 * @author Yeongbae Jeon
 * @version 1.0
 */
import java.awt.*;
import java.awt.Color.*;
public class Point {
    private int x;
    private int y;
    private Color color;

    public Point(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public  Color getColor() {
        return this.color;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        Point others=(Point) o;
        return this.x== others.x&&this.y==others.y;
    }
}



