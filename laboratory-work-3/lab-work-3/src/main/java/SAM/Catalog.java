package SAM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Catalog {
    private List<WeatherData> weatherData;

    public Catalog(){
        weatherData = new ArrayList<WeatherData>();
    }


    public void push(WeatherData weatherData2){
        weatherData.add(weatherData2);
    }

    public double calculateAverageTemperature(String city, Date startDate, Date endDate) {
        double sum = 0.0;
        int count = 0;

        for (WeatherData data : weatherData) {
            if (data.getCity() != null && data.getCity().equalsIgnoreCase(city) &&
                    data.getDate() != null && data.getTemperature() != null) {
                if (!data.getDate().before(startDate) && !data.getDate().after(endDate)) {
                    try {
                        double temperature = data.getTemperature();
                        sum += temperature;
                        ++count;
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка парсинга температуры: " + data.getTemperature());
                    }
                }
            }
        }

        return count > 0 ? sum / count : 0.0;
    }

    @Override
    public String toString() {
        return this.weatherData.toString();
    }
}
