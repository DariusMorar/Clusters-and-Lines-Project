/**
 * wrapper class for line
 * 
 * @author Yeongbae Jeon
 * @author Aditya Malladi
 * @version 2022.07.07
 */
public class Line {
    private Point endPoint1;
    private Point endPoint2;

    public Line(Point endPoint1, Point endPoint2) {
        this.endPoint1 = endPoint1;
        this.endPoint2 = endPoint2;
    }

    public Point getEndPoint1() {
        return this.endPoint1;
    }

    public Point getEndPoint2() {
        return this.endPoint2;
    }
    
    @Override
    public boolean equals(Object o) {
        Line others=(Line) o;
        boolean end1x = this.getEndPoint1().getX() ==
        		others.getEndPoint1().getX();
        boolean end1y = this.getEndPoint1().getY() ==
        		others.getEndPoint1().getY();
        boolean end2x = this.getEndPoint2().getX() ==
        		others.getEndPoint2().getX();
        boolean end2y = this.getEndPoint2().getY() ==
        		others.getEndPoint2().getY();
        return (end1x && end1y && end2x && end2y);
    }
}