package SAM;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class WeatherData {
    private String id;
    private String city;
    private Date date;
    private Double temperature;
    private String humidity;

    public WeatherData() {}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getCity(){ return city;}

    public void setCity(String city){ this.city = city;}

    public Date getDate(){ return date;}

    public void setDate(Date date){ this.date = date;}

    public Double getTemperature(){ return temperature;}

    public void setTemperature(Double temperature){ this.temperature = temperature;}

    public String getHumidity(){ return humidity;}

    public void setHumidity(String humidity){ this.humidity = humidity;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherData book = (WeatherData) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(city, book.city) &&
                Objects.equals(date, book.date) &&
                Objects.equals(temperature, book.temperature) &&
                Objects.equals(humidity, book.humidity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, date, temperature, humidity);
    }

    @Override
    public String toString() {
        return """ 
           
           ╔═══════════════════════════════════╗ 
           ║      Информация о погоде          ║ 
           ╚═══════════════════════════════════╝ 
           ID:               %s 
           Город:            %s 
           Дата:             %s 
           Температура:      %s 
           Влажность:        %s 
           """.formatted(
                id != null ? id : "N/A",
                city != null ? city : "N/A",
                date != null ? new SimpleDateFormat("dd/MM/yyyy").format(date) : "N/A",
                temperature != null ? String.format("%.2f°C", temperature) : "N/A",
                humidity != null ? humidity : "N/A"
        );
    }
}