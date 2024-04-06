package CurrentWeather;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalTime;
import java.util.Scanner;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import java.time.Instant;
import CurrentWeather.gui;


public class weather {
    final static URI uri;
    private JSONObject hourlyForecast;
    private long lastFetchedForecast;

    static {
        try {
            // put lat and long here later
            uri = new URI("https://api.open-meteo.com/v1/forecast?latitude=&" +
                    "longitude=&hourly=temperature_2m,rain,snowfall");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public weather() throws IOException{
        lastFetchedForecast = 0;
        getHourly();
    }

    public JSONObject getHourly() throws IOException{
        HttpGet request = new HttpGet(uri);
        HttpClient client = HttpClients.createDefault();
        HttpResponse response = client.execute(request);
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(response.getEntity().getContent());

        while (scanner.hasNextLine()){
            sb.append(scanner.nextLine());
        }

        JSONObject jsonObj = new JSONObject(sb.toString());
        return (JSONObject) jsonObj.get("hourly");
    }

    public int getTemperature (int hoursFromNow) throws IOException{
        reloadWeather();
        JSONArray now = (JSONArray) hourlyForecast.get("temperature_2m");
        // 0 is currently midnight fix this
        BigDecimal tempBD = (BigDecimal) now.get(hoursFromNow + getHoursFromMidnight());
        double temp = (tempBD.doubleValue() * (9d/5d)) + 32;
        if ((temp - (int) temp) >= 0.5d){
            return (int) temp + 1;
        }
        else {return (int) temp;}

    }

    public int inOfRain(int hoursFromNow) throws IOException{
        reloadWeather();
        JSONArray now = (JSONArray) hourlyForecast.get("rain");
        BigDecimal mmOfRain = (BigDecimal) now.get(getHoursFromMidnight() + hoursFromNow);
        return (int) (mmOfRain.doubleValue() / 25.4d);
    }

    public static void main(String[] args) throws IOException {
        new gui();
    }

    public void reloadWeather() throws IOException{
        if ((Instant.now().getEpochSecond() - lastFetchedForecast) > 120) {
            hourlyForecast = getHourly();
        }
    }
    public String getTime() throws IOException{
        reloadWeather();
        JSONArray times = (JSONArray) hourlyForecast.get("time");
        return times.getString(getHoursFromMidnight());
    }

    private int getHoursFromMidnight(){
        LocalTime time = LocalTime.now();
        if (time.getMinute() >= 30){
            return time.getHour() + 1;
        }
        else {return time.getHour();}
    }
}
