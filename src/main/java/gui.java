package CurrentWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import CurrentWeather.weather;

public class gui implements ActionListener {

    private weather weather;
    private JButton updateButton = new JButton("Update");
    private JMenuBar menuBar = new JMenuBar();
    private JMenu file = new JMenu("File");
    private JMenuItem about = new JMenuItem("About");
    private JFrame frame = new JFrame();
    private Box lefthandBox = Box.createVerticalBox();
    private JLabel weatherPicture = new JLabel();

    private Box wInfoBox = Box.createVerticalBox();
    private JLabel temp = new JLabel();
    private JLabel rain = new JLabel();

    private Class clazz = this.getClass();
    private ImageIcon sunny = scaleImage(64,64, clazz.getResource("/sunny.jpg"));

    public gui() throws IOException{
        try {
            weather = new weather();
        } catch(UnknownHostException ex){
            JOptionPane.showMessageDialog(null,
                    "Failed to get weather! Check internet Connectivity?", "JWeather",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        frame.setTitle("JWeather");

        menuBar.add(file);
        file.add(about);
        about.addActionListener(this);
        frame.setJMenuBar(menuBar);

        updateButton.setAlignmentX(0.15f);
        lefthandBox.add(weatherPicture);
        lefthandBox.add(Box.createVerticalStrut(10));
        lefthandBox.add(updateButton, BorderLayout.WEST);
        frame.add(lefthandBox);

        wInfoBox.add(temp);
        wInfoBox.add(rain);
        frame.add(wInfoBox);

        updateWeather();
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

    private void updateWeather() {
        try {
            weatherPicture.setIcon(sunny);
            temp.setText("Temperature: " + weather.getTemperature(0) + " °F\n");
            rain.setText("Rain: " + weather.inOfRain(0) + " in\n");
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Can't Fetch Weather",
                    "JWeather", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ImageIcon scaleImage(int x, int y, URL img){
        return new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
    }
}
