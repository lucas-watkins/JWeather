package CurrentWeather;

import org.apache.http.client.methods.HttpGet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class locparser{
    public locparser() throws IOException {
        if (!new File("loc.txt").exists()){
            new File("loc.txt").createNewFile();
        }
    }

    public String[] getLocation() throws FileNotFoundException {
        File file = new File("loc.txt");
        Scanner scanner = new Scanner(file);
        return new String[] {scanner.nextLine(), scanner.nextLine()};
    }

    public static String getLocationAsString() throws FileNotFoundException {
        File file = new File("loc.txt");
        Scanner scanner = new Scanner(file);

        return "Latitude: " + scanner.nextDouble() + "\nLongitude: " + scanner.nextDouble();
    }
}
