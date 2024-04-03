package CurrentWeather;

import CurrentWeather.weather;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class gui {
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
        menuBar.add(file);
        file.add()
        frame.setJMenuBar(menuBar);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }

}
