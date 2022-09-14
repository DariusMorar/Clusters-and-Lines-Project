import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * The LoadDot is for save the X & Y coordinate into file.
 * @author: Cungang Zhang
 * @version: 7/7/2022
 * */
public class SavePoint {
    void saveFile(){
        ClassifierModel model = ClassifierModel.getInstance();
        List<Point> points = model.getPoints();
        JFrame parentFrame = new JFrame();
        File saveLocation = new File(" ");
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a location to save file");
            int selection = fileChooser.showSaveDialog(parentFrame);
            if (selection == JFileChooser.APPROVE_OPTION) {
                saveLocation = fileChooser.getSelectedFile();
            }
            FileWriter csv = new FileWriter(saveLocation);
            for (Point point : model.getPoints()) {
                String x= String.valueOf(point.getX());
                csv.write(x);
                csv.write(',');
                String y= String.valueOf(point.getY());
                csv.write(y);
                csv.write("\n");
            }
            csv.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}