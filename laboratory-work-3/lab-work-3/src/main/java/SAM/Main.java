package SAM;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        String path = new
                File("D:/Study/Семестр5/MSSDS/laboratory-work-3/lab-work-3/src/main/java/XML/weatherData.xml").getAbsolutePath();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            WeatherDataXMLHandler handler = new WeatherDataXMLHandler();
            parser.parse(new File(path), handler);

            System.out.println("Done with catalog");
            System.out.println(handler.getCatalog().toString());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date date1 = format.parse("14/01/2024");
                Date date2 = format.parse("16/01/2024");
                String city = "Москва";

                double avgTemp = handler.getCatalog().calculateAverageTemperature(city, date1, date2);
                System.out.printf("Средняя температура в %s с %s по %s: %.1f°C%n",
                        city, format.format(date1), format.format(date2), avgTemp);

            }catch (ParseException e){
                System.out.println("Ошибка парсинга: " + e.getMessage());
            }

        } catch (IOException x) {
            System.err.println(x);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}