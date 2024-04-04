package CurrentWeather;

import CurrentWeather.weather;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

public class gui implements ActionListener {
    static {
        try {
            weather weather = new weather();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JButton updateButton = new JButton("Update");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem about = new JMenuItem("About");
    private JFrame frame = new JFrame();
    private Box lefthandBox = Box.createVerticalBox();
    private JLabel weatherPicture = new JLabel("Weather Picture");
    public gui(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frame.setTitle("JWeather");

        menuBar.add(file);
        file.add(about);
        about.addActionListener(this);
        frame.setJMenuBar(menuBar);

        lefthandBox.add(weatherPicture);
        lefthandBox.add(updateButton);
        frame.add(lefthandBox);

        frame.setSize(500, 200);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(updateButton)){
            // update code in here
            updateWeather();
        }
        if (e.getSource().equals(about)){
            // show about screen
            if (JOptionPane.showConfirmDialog(null, "JWeather\n\nJava Vendor: " +
                    System.getProperty("java.vendor") + "\nJava Version: " + System.getProperty("java.version") +
                    "\n\n\tOpen GitHub repository?", "JWeather", JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE) == 0){
                try {
                    Desktop.getDesktop().browse(URI.create("https://github.com/lucas-watkins/JWeather"));
                } catch (IOException ex){
                    JOptionPane.showMessageDialog(null,
                            "Unable to show GitHub Repository");
                }
            }
        }
    }

    private void updateWeather(){

    }
}
