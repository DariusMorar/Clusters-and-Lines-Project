import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
/**
 * The LoadDot is for save the X & Y coordinate into file.
 * @author: Cungang Zhang
 * @version: 7/7/2022
 * */
public class FileChooser {
    String fChooser(){
        String csvfile = "NULL";
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int show = fileChooser.showOpenDialog(null);
        if(show == JFileChooser.APPROVE_OPTION){
            csvfile = fileChooser.getSelectedFile().getAbsolutePath();
        }else{
            csvfile = null;
        }
        return csvfile;
    }
}