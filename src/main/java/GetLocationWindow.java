package CurrentWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class GetLocationWindow implements ActionListener
{
    private final JFrame frame = new JFrame();
    private final JTextField latEntry = new JTextField(7);
    private final JTextField lngEntry = new JTextField(7);
    private final JTextField locEntry = new JTextField(7);
    private final JButton okButton = new JButton("OK");

    public GetLocationWindow(){
        Box box = Box.createVerticalBox();
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        box.add(new JLabel("Latitude: "));
        box.add(latEntry);
        box.add(new JLabel("Longitude: "));
        box.add(lngEntry);
        box.add(new JLabel("Location: "));
        box.add(locEntry);
        box.add(okButton);

        frame.add(box);
        okButton.addActionListener(this);
        frame.setSize(250,250);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        String lat = latEntry.getText();
        String lng = lngEntry.getText();
        String loc = locEntry.getText();

        try {
            FileWriter fileWriter = new FileWriter("loc.txt");
            fileWriter.write(lat + '\n' + lng + '\n' + loc + '\n');
        } catch (IOException i){
            throw new RuntimeException("Error writing to loc.txt");
        }
        frame.dispose();
    }
}
