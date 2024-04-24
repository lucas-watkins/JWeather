package CurrentWeather;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class LocParser {
    static{
        if (!new File("loc.txt").exists()){
            try {
                if (!new File("loc.txt").createNewFile()){
                    throw new FileNotFoundException("Could not create file");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String[] getLocation() throws FileNotFoundException {
        try {
            File file = new File("loc.txt");
            Scanner scanner = new Scanner(file);
            return new String[] {scanner.nextLine(), scanner.nextLine()};
        } catch (NoSuchElementException e) {
            return new String[]{"0", "0"};
        }
    }

    public static String getLocationAsString() throws FileNotFoundException {
        File file = new File("loc.txt");
        Scanner scanner = new Scanner(file);

        return "Latitude: " + scanner.nextDouble() + "\nLongitude: " + scanner.nextDouble() + "\nLocation: "
                + scanner.nextLine();
    }

    
}
