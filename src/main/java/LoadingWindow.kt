package CurrentWeather;
import javax.swing.*;
import java.awt.*;

public class LoadingWindow implements Runnable
{
    private JFrame frame;

    public void run() {
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

