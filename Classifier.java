/**
 * This program let the user add circles to a board, and run clustering algorithm
 * or algorithm for connecting set of points.
 * 
 * @author Yeongbae Jeon
 * @author Aditya Malladi
 * @version 2022.07.07 
 */
public class Classifier {
	private static MainFrame mainFrame;
	
	/**
	 * This method creates a new MainFrame.
	 * 
	 * @param args
	 */
    public static void main(String[] args) {
        mainFrame = new MainFrame();
    }
    
    /**
     * This method returns the current MainFrame, and is used to
     * attach MainFrame as an observer of NumOfClustersFrame.
     * 
     * @return MainFrame
     */
    public static MainFrame getMainFrame() {
    	return mainFrame;
    }
}