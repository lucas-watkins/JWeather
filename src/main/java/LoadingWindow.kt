package CurrentWeather

import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.JLabel

class LoadingWindow : Runnable {
    private var frame: JFrame? = null

     override fun run() {
        frame = JFrame()
        frame!!.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
        frame!!.title = "JWeather"
        frame!!.setSize(250, 250)
        frame!!.add(JLabel("Fetching Weather..."), BorderLayout.CENTER)
        frame!!.isVisible = true
    }

    fun dispose() {
        println("Disposing frame")
        frame!!.dispose()
    }
}

