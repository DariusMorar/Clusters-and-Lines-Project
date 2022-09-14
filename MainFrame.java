import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MenuListener;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;  
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;  


/**
 * This is the main GUI class. It creates the menu
 * with the panel underneath for dots.
 *
 * @author Darius Morar
 * @version 07.05.2022
 */

public class MainFrame extends JFrame implements ActionListener, Observer {
	
	private JMenuBar menuBar;
	private JMenu fileMenu, connections, help;
	private JMenuItem neww, load, save, line, clusters, lineClusters, about;
	private LoadPoint loadPoint;
	private SavePoint savePoint;
	private UpdateOption option;
	private BoardPanel boardPanel = new BoardPanel();
	
/**
 * This constructor makes the GUI with the menu options added.
 * It adds in the other panels and frames needed, 
 * but is the starting point to the whole project.
 * 
 * @author Darius Morar
 */
    public MainFrame() {
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setTitle("Final Project");
    	
		
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		connections = new JMenu("Connections");
		help = new JMenu("Help");
		
		neww = new JMenuItem("New");
		load = new JMenuItem("Load");
		save = new JMenuItem("Save");
		line = new JMenuItem("Line - nearest neighbor");
		clusters = new JMenuItem("Clusters");
		lineClusters = new JMenuItem("Line + Clusters");
		about = new JMenuItem("About");
		
		loadPoint = new LoadPoint();
		savePoint = new SavePoint();
		
		neww.addActionListener(this);
		load.addActionListener(this);
		save.addActionListener(this);
		line.addActionListener(this);
		clusters.addActionListener(this);
		lineClusters.addActionListener(this);
		about.addActionListener(this);
		
		fileMenu.add(neww);
		fileMenu.add(load);
		fileMenu.add(save);
		connections.add(line);
		connections.add(clusters);
		connections.add(lineClusters);
		help.add(about);
		
		menuBar.add(fileMenu);
		menuBar.add(connections);
		menuBar.add(help);
		
		this.add(this.boardPanel);
		
		this.setJMenuBar(menuBar);
		this.setVisible(true);
		this.pack();
		this.setSize(800, 500);
    }
    
   /**
    * This actionlistener sees when menu options are selected
    * and then calls appropiate objects and methods.
    * 
    * @author Darius Morar
    */
    @Override
	public void actionPerformed(ActionEvent e) {
		
    	ClassifierModel model = ClassifierModel.getInstance();
    	
    	
		if (e.getSource() == neww) {
			boardPanel.clear();
		}
		if (e.getSource() == load) {
			loadPoint.loadFile();
			if (loadPoint.getLoadPoint() != null) {
				boardPanel.drawPointsFromLoad(loadPoint.getLoadPoint());
			}
		}
		if (e.getSource() == save) {
			savePoint.saveFile();
		}
		if (e.getSource() == line) {
			model.calculateLines();
            boardPanel.drawLines(model.getLines());
		}
		if (e.getSource() == clusters) {
			option = UpdateOption.CLUSTER;
			new NumOfClustersFrame();
			
		}
		if (e.getSource() == lineClusters) {
			option = UpdateOption.BOTH;
			new NumOfClustersFrame();
			
		}
		if (e.getSource() == about) {
			new AboutFrame();
		}
		
	}

    /**
     * We found making this class an Observer solved one of our
     * problems with button clikcing. It observes the button "OK"
     * when user inputs how mnay clusters they want.
     * 
     * @author Darius Morar
     */
	@Override
	public void update(Observable o, Object arg) {
		if (option == UpdateOption.CLUSTER) {
			ClassifierModel.getInstance().calculateCluster();
            boardPanel.drawPoints(ClassifierModel.getInstance().getPoints());
		}
		else if (option == UpdateOption.BOTH) {
			
			ClassifierModel model = ClassifierModel.getInstance();
			model.calculateBoth();
			boardPanel.drawPoints(model.getPoints());
			boardPanel.drawLines(model.getLines());
			boardPanel.drawCircles(model.getClusterCenter(), model.getClusterRadius());
		}
		
	}
    
}