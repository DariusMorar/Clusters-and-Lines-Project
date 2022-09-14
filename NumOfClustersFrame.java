import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * This is the frame that pops up whenever a user wants
 * to make clusters. It asks how many clusters the user wants.
 * 
 * @author Darius Morar
 * @version 07.07.2022
 */

public class NumOfClustersFrame extends Observable {

	JFrame frame;
	JPanel panel;
    JLabel input;
    JTextField text;
    JButton okButton;
    JButton cancelButton;
	
    
    /**
     * this constructor makes frame that asks user for cluster amount.
     * It has two listeners for "OK" button and "cancel" button.
     * 
     * @author Darius Morar
     */
    public NumOfClustersFrame() {
    	this.addObserver(Classifier.getMainFrame());

    	frame = new JFrame();
        panel = new JPanel();
        input = new JLabel("Enter number of Clusters:");
        text = new JTextField();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        GridLayout layout = new GridLayout(2, 2);
        panel.setLayout(layout);
        
        panel.add(input);
        panel.add(text);
        panel.add(okButton);
        panel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close current window.
                frame.dispose();
            }
        });
        
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == okButton) {
                	String temp = text.getText();
                	int inputCluster = Integer.parseInt(temp);
                	
                	if((inputCluster > ClassifierModel.getInstance().getPoints().size())) {
                		text.setText("Invalid Number.");
                	}
                	else {
                		ClassifierModel.getInstance().setClusterCount(inputCluster);
                		setChange();
                	}
                	
                	
                }
            }
        });

        frame.add(panel);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(30);
        frame.setLayout(flowLayout);
        
        // show the jFrame
        frame.setTitle("Input");
        frame.setSize(400,200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocus();
    }
    
    private void setChange() {
    	this.setChanged();
    	this.notifyObservers();
    }

}