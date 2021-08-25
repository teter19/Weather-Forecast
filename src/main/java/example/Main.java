package example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;

import java.awt.event.ActionEvent;
import java.io.IOException;
import static example.Tabs.createAndShowGUI;

public class Main{

    static final Logger log = LogManager.getLogger();
    public static void main(String[] args) throws IOException {

        // initializes the Logging Context, DefaultConfiguration() - if config file not found, ERROR messages will be printed to console
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);

        log.info("Launching Weather Forecast!");
        log.info("Connecting to site https://pogoda.interia.pl/");

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }       //tworze sobie watek metoda run()
        });
    }
}