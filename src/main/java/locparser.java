package CurrentWeather;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
}
