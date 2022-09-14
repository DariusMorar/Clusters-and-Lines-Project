import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * The LoadDot is for load the X & Y coordinate from file.
 * @author: Yaci Zhu
 * @author Cungang Zhang
 * @version: 7/7/2022
 * */
public class LoadPoint {
    protected LinkedList<Point> loadPoint = new LinkedList<>();
    void loadFile() {
        FileChooser fChoose = new FileChooser();
        String csvFile = "NULL";
        csvFile = fChoose.fChooser();
        while(loadPoint.size() != 0)
            loadPoint.remove();
        if(csvFile != "NULL") {
            String delim = ",";
            LinkedList<String> temp = new LinkedList<String>();
            try {
                File file = new File(csvFile);
                FileReader fileRead = new FileReader(file);
                BufferedReader buffRead = new BufferedReader(fileRead);
                String line = "";
                while((line = buffRead.readLine()) != null) {
                    StringTokenizer toParse = new StringTokenizer(line, delim);
                    while (toParse.hasMoreTokens())
                        temp.add(toParse.nextToken());
                    Point getPoint = new Point(0,0,Color.BLACK);
                    getPoint.setX(Integer.parseInt(temp.getFirst()));
                    temp.removeFirst();
                    getPoint.setY(Integer.parseInt(temp.getFirst()));
                    temp.removeFirst();
                    loadPoint.add(getPoint);
                }
                buffRead.close();
            } catch(IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
    LinkedList<Point> getLoadPoint() {
        return loadPoint;
    }
}