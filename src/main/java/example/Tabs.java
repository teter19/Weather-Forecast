package example;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Tabs {

    public static void createAndShowGUI() throws IOException {
        JFrame frame = new JFrame("Weather Forecast");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(1300, 1030);      //1024x1024 image
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(final JFrame frame) throws IOException {
        final Logger log = LogManager.getLogger();
        Configurator.initialize(new DefaultConfiguration());
        Configurator.setRootLevel(Level.INFO);

        Towns listOfTowns=new Towns();

        JTabbedPane tabbedPane = new JTabbedPane(); //tworze obiekt zakladka, i potem sobie go uzwyam
        SpringLayout layout = new SpringLayout();
        Document doc = Jsoup.connect("https://pogoda.interia.pl/").timeout(6000).get();     //glowny link do pobrania reszty osobnych linkow

        //pobieram linki do kazdego dnia
        Elements links = doc.select("ul li a.weather-menu-days-item-link");
        int n=0;
        for (Element element : links) {
            n++;
            if (n==15) break;

            log.info("Connecting to site "+"https://pogoda.interia.pl"+element.attr("href"));
            Document doc2 = Jsoup.connect("https://pogoda.interia.pl"+element.attr("href")).timeout(6000).get();
            JPanel panel = new JPanel(layout);

            for (int i=0; i<15;i++) {
                Elements title1 = doc2.select(listOfTowns.towns.get(i) +" "+ listOfTowns.data.get(2));      //misato
                Elements title2 = doc2.select(listOfTowns.towns.get(i) +" "+ listOfTowns.data.get(1));      //temperatura
                Elements title3 = doc2.select(listOfTowns.towns.get(i) +" "+ listOfTowns.data.get(0));      //zachmurzenie
                JTextField textField = new JTextField(title1.text());
                JTextField textField2 = new JTextField("Temperatura: "+ title2.text() + " °C");
                JTextField textField3 = new JTextField(title3.text());
                textField.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                textField2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
                textField3.setFont(new Font("TimesRoman", Font.PLAIN, 12));

                //textField.setForeground(Color.WHITE);       //ustawiam kolor
                textField.setBackground(Color.WHITE);

                layout.putConstraint(SpringLayout.WEST, textField, Integer.parseInt(listOfTowns.coordiatesX.get(i)), SpringLayout.WEST, panel);        //ustawiam textField na wspolrzedna X
                layout.putConstraint(SpringLayout.NORTH, textField, Integer.parseInt(listOfTowns.coordiatesY.get(i)), SpringLayout.NORTH, panel);      //ustawiam textField na wspolrzedna Y
                //temperatura
                layout.putConstraint(SpringLayout.WEST, textField2, Integer.parseInt(listOfTowns.coordiatesX.get(i)), SpringLayout.WEST, panel);        //ustawiam textField na wspolrzedna X
                layout.putConstraint(SpringLayout.NORTH, textField2, Integer.parseInt(listOfTowns.coordiatesY.get(i))+30, SpringLayout.NORTH, panel);      //ustawiam textField na wspolrzedna Y
                //zachmurzenie
                layout.putConstraint(SpringLayout.WEST, textField3, Integer.parseInt(listOfTowns.coordiatesX.get(i)), SpringLayout.WEST, panel);        //ustawiam textField na wspolrzedna X
                layout.putConstraint(SpringLayout.NORTH, textField3, Integer.parseInt(listOfTowns.coordiatesY.get(i))+50, SpringLayout.NORTH, panel);      //ustawiam textField na wspolrzedna Y
                //textField.setToolTipText("Temperatura: "+title2.text() + " °C, "+title3.text());
                textField.setVisible(true);
                textField.setEditable(false);

                textField2.setVisible(true);
                textField2.setEditable(false);

                textField3.setVisible(true);
                textField3.setEditable(false);

                panel.add(textField);
                panel.add(textField2);
                panel.add(textField3);


            }
            //Add backround image
            JLabel backround;
            ImageIcon img = new ImageIcon("mapa4.jpg");
            backround = new JLabel("",img, JLabel.CENTER);
            //backround.setBounds(0,0,400,381);
            panel.add(backround);

            //dodaje taby
            tabbedPane.addTab(element.text(), null, panel,element.text());
            log.info("Created Tab: " + element.text());
            tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        }
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.pack();
        }

}








//System.out.format("%s %s %s\n", element.attr("title"), element.attr("href"), element.text());
//System.out.format("%s %s \n", element.attr("href"), element.text());
//System.out.format("%s \n", element.text());       //wypisuje same dni i daty
//System.out.format("%s \n", element.attr("href"));