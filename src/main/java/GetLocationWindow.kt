package CurrentWeather;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

class GetLocationWindow : ActionListener
{
    private val frame = JFrame()
    private val latEntry = JTextField(7)
    private val lngEntry = JTextField(7)
    private val locEntry = JTextField(7)
    private val okButton = JButton("OK")
    private val box = Box.createVerticalBox()

    init {
        frame.layout = FlowLayout(FlowLayout.CENTER, 5, 5)
        box.add(JLabel("Latitude: "));
        box.add(latEntry);
        box.add(JLabel("Longitude: "));
        box.add(lngEntry);
        box.add(JLabel("Location: "));
        box.add(locEntry);
        box.add(okButton);

        frame.add(box);
        okButton.addActionListener(this)
        frame.setSize(250,250)
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
        frame.isVisible = true
    }

    override fun actionPerformed(e: ActionEvent){
        val lat = latEntry.getText()
        val lng = lngEntry.getText()
        val loc = locEntry.getText()

        try {
            val fileWriter = FileWriter("loc.txt")
            fileWriter.write(lat + '\n' + lng + '\n' + loc + '\n')
            fileWriter.close()
        } catch (i: IOException) {
            throw RuntimeException("Error writing to loc.txt")
        }
        frame.dispose();
    }
}
