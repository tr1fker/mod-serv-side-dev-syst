package XML;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherDataXMLHandler extends DefaultHandler {
    private Catalog catalog = null;
    private WeatherData weatherData = null;
    private StringBuilder currentCharacters = new StringBuilder();


    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes
            attributes) {
        currentCharacters.setLength(0);

        if (qName.equalsIgnoreCase("catalog")) {
            catalog = new Catalog();
        } else if (qName.equalsIgnoreCase("weatherData")) {
            weatherData = new WeatherData();
            String id = attributes.getValue("id");
            if (id != null) {
                weatherData.setId(id);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String content = currentCharacters.toString().trim();

        if (qName.equalsIgnoreCase("weatherData")) {
            catalog.push(weatherData);
        } else if (qName.equalsIgnoreCase("city")) {
            weatherData.setCity(content);
        } else if (qName.equalsIgnoreCase("date")) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = dateFormat.parse(content);
                weatherData.setDate(date);
            } catch (ParseException e) {
                System.err.println("Ошибка парсинга даты: " + content);
                weatherData.setDate(null);
            }
        } else if (qName.equalsIgnoreCase("temperature")) {
            weatherData.setTemperature(Double.parseDouble(content.replace("°C", "").trim()));
        } else if (qName.equalsIgnoreCase("humidity")) {
            weatherData.setHumidity(content);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        currentCharacters.append(ch, start, length);
    }
}