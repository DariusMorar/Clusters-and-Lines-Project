import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the about page. It displays team names.
 *
 * @author Darius Morar
 * @version 07.05.2022
 */

public class AboutFrame extends JFrame {
    // some blanks
    private String TAB = "        ";

    public AboutFrame() {
        // page title
        this.setTitle("About");

        // create the panel
        JPanel panel = new JPanel();

        // multi-row, one column.
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(10);
        panel.setLayout(layout);

        // add labels
        panel.add(new JLabel("Team members:"));
        panel.add(new JLabel(TAB + "Darius Morar"));
        panel.add(new JLabel(TAB + "Cungang Zhang"));
        panel.add(new JLabel(TAB + "Aditya Malladi"));
        panel.add(new JLabel(TAB + "Yaci Zhu"));
        JButton btn = new JButton("Ok");
        panel.add(btn);

        AboutFrame that = this;
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // close current window.
                that.dispose();
            }
        });


        // add panel to jFrame
        this.add(panel);
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(30);
        this.setLayout(flowLayout);

        // show the jFrame
        this.setSize(300,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.requestFocus();
    }


}