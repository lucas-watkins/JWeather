package CurrentWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import CurrentWeather.weather;
import CurrentWeather.GetLocationWindow;
import CurrentWeather.LoadingWindow;

public class gui implements ActionListener {

    private weather weather;
    private final JButton updateButton = new JButton("Update");
    private final JMenuItem about = new JMenuItem("About");
    private final JMenuItem location = new JMenuItem("Location");
    private final JMenuItem setLocation = new JMenuItem("Set Location");

    private final JLabel weatherPicture = new JLabel();

    private final JLabel temp = new JLabel();
    private final JLabel rain = new JLabel();
    private final JFrame frame = new JFrame();

    private final Class clazz = this.getClass();
    private final ImageIcon sunny = scaleImage(64,64, clazz.getResource("/sunny.jpg"));
    private final ImageIcon rainy = scaleImage(64,64, clazz.getResource("/rainy.jpg"));
    private LoadingWindow loadingWindow;

    public gui() throws IOException{
        try {

            new Thread(() -> {
                boolean loadingWindowCreated = false;
                while(weather == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (!loadingWindowCreated){
                        loadingWindowCreated = true;
                        loadingWindow = new LoadingWindow();
                        loadingWindow.run();

                    }

                }
                loadingWindow.dispose();

            }).start();

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

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        file.add(about);
        file.add(location);
        file.add(setLocation);
        about.addActionListener(this);
        location.addActionListener(this);
        setLocation.addActionListener(this);
        frame.setJMenuBar(menuBar);

        updateButton.setAlignmentX(0.15f);
        Box lefthandBox = Box.createVerticalBox();
        lefthandBox.add(weatherPicture);
        lefthandBox.add(Box.createVerticalStrut(10));
        lefthandBox.add(updateButton, BorderLayout.WEST);
        frame.add(lefthandBox);

        Box wInfoBox = Box.createVerticalBox();
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
        if (e.getSource().equals(location)){
            try {
                displayLocationBanner();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
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
        if (e.getSource().equals(setLocation)){
            new GetLocationWindow();
        }
    }

    private void displayLocationBanner() throws FileNotFoundException {
        JOptionPane.showMessageDialog(null, CurrentWeather.LocParser.locationAsString(),
                "JWeather" , JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateWeather() {
        try {
            weatherPicture.setIcon(getImage());
            temp.setText("Temperature: " + weather.getTemperature(0) + " °F\n");
            rain.setText("Rain: " + weather.inOfRain(0) + " in\n");
        } catch (IOException ex){
            JOptionPane.showMessageDialog(null, "Can't Fetch Weather",
                    "JWeather", JOptionPane.ERROR_MESSAGE);
        }
    }

    private ImageIcon getImage() throws IOException{
        if (weather.inOfRain(0) > 0){
            return rainy;
        }
        else {return sunny;}
    }

    private ImageIcon scaleImage(int x, int y, URL img){
        return new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
    }
}
