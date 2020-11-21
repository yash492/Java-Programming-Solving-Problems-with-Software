import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num = 0;
        for (Point dummy : s.getPoints()){
            num = num + 1;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double sum = getPerimeter(s);
        int num = getNumPoints(s);
        double avg = sum/num;
        return avg;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double max_len = 0;
        Point prev_point = s.getLastPoint();
        for (Point currPoint: s.getPoints()){
            double currDt = prev_point.distance(currPoint);
            if (currDt > max_len){
                max_len = currDt;
            }
            prev_point = currPoint;
        }
        return max_len;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double max_x = 0;
        for(Point currPt: s.getPoints()){
            int x = currPt.getX();
            if (x > max_x){
                max_x = (double) x;
            }
        }
        return max_x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double maxPerimeter = 0;
        for (File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (maxPerimeter < perimeter){
                maxPerimeter = perimeter;
            }
        }
        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        File temp = null;
        double maxPerimeter = 0;
        for(File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double perimeter = getPerimeter(s);
            if (maxPerimeter < perimeter){
                maxPerimeter = perimeter;
                temp = f;
            }
        }
           // replace this code
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
    }

    public void testgetNumPoints(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        int num = getNumPoints(s);
        System.out.println("No. of points = " + num);
    }

    public void testgetAverageLength(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double avg = getAverageLength(s);
        System.out.println("Average: " + avg);
    }

    public void testgetLargestSide(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double max_len = getLargestSide(s);
        System.out.println("Max Len: " + max_len);
    }
    
    public void testgetLargestX(){
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double max_x = getLargestX(s);
        System.out.println("Max X: " + max_x);
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double maxPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("Max Perimeter: " + maxPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String getname = getFileWithLargestPerimeter();
        System.out.println("Max Name: " + getname);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        // pr.testPerimeter();
        // pr.testgetNumPoints();
        // pr.testgetAverageLength();
         pr.testgetLargestSide();
        // pr.testgetLargestX();
        // pr.testPerimeterMultipleFiles();
        // pr.testFileWithLargestPerimeter();
    }
}
