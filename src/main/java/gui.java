package CurrentWeather;

import CurrentWeather.weather;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class gui implements ActionListener {
    static {
        try {
            weather weather = new weather();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JButton updateButton = new JButton();
    private JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem about = new JMenuItem("About");
    private JFrame frame = new JFrame();
    public gui(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        menuBar.add(file);
        file.add(about);
        about.addActionListener(this);
        frame.setJMenuBar(menuBar);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(updateButton)){
            // update code in here

        }
    }
}
