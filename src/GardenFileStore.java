import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

// Saves a Garden to a text file and loads it back. One record per line, fields separated by "|":
//   TODAY|12
//   PLANT|name|species|room|waterEveryDays|lastWateredDay
//   EVENT|day|type|note        (belongs to the PLANT line above it)
public class GardenFileStore {

    public static void save(Garden garden, String fileName) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("TODAY|" + garden.getToday());
        for (Plant plant : garden.getPlants()) {
            lines.add("PLANT|" + plant.getName() + "|" + plant.getSpecies() + "|" + plant.getRoom()
                    + "|" + plant.getWaterEveryDays() + "|" + plant.getLastWateredDay());
            for (CareEvent event : plant.getHistory()) {
                lines.add("EVENT|" + event.getDay() + "|" + event.getType() + "|" + event.getNote());
            }
        }
        // BEYOND CSC110: Files.write saves a list of lines to a file. try/catch handles the error
        // if the file can't be written (for example, the folder is missing).
        try {
            Files.write(Path.of(fileName), lines);
        } catch (IOException e) {
            System.out.println("Could not save " + fileName + ": " + e.getMessage());
        }
    }

    // Returns the loaded garden, or a new empty garden on Day 1 if the file can't be read.
    public static Garden load(String fileName) {
        List<String> lines;
        // BEYOND CSC110: Files.readAllLines reads every line of a text file into a list.
        try {
            lines = Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            return new Garden(1);
        }

        Garden garden = new Garden(1);
        Plant current = null;
        for (String line : lines) {
            // BEYOND CSC110: split("\\|", -1) cuts the line at each "|"; -1 keeps empty fields like a blank note.
            String[] parts = line.split("\\|", -1);
            // BEYOND CSC110: if a line was hand-edited and is broken, skip it instead of crashing.
            try {
                if (parts[0].equals("TODAY")) {
                    garden = new Garden(Integer.parseInt(parts[1]));
                } else if (parts[0].equals("PLANT")) {
                    current = new Plant(parts[1], parts[2], parts[3],
                            Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                    garden.getPlants().add(current);
                } else if (parts[0].equals("EVENT") && current != null) {
                    current.getHistory().add(new CareEvent(Integer.parseInt(parts[1]), parts[2], parts[3]));
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Skipping a broken line in " + fileName + ": " + line);
            }
        }
        return garden;
    }

    // True if the file exists (used to decide whether to load the sample data).
    public static boolean exists(String fileName) {
        return Files.exists(Path.of(fileName));
    }
}
