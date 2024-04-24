package CurrentWeather;
import javax.swing.*;
import java.awt.*;

public class LoadingWindow
{
    private JFrame frame;

    public LoadingWindow() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("JWeather");
        frame.setSize(250, 250);
        frame.add(new JLabel("Fetching Weather..."), BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public void dispose(){
        System.out.println("Disposing frame");
        frame.dispose();
    }
}

