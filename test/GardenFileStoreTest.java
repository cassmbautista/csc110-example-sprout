import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.junit.jupiter.api.Test;

// Saving then loading must give back the same day, plants, and history.
public class GardenFileStoreTest {

    @Test
    void saveThenLoadRestoresEverything() {
        Garden garden = new Garden(12);
        garden.addPlant("Monty", "Monstera", "Living Room", 7, 6);
        garden.logCare("Monty", CareEvent.FERTILIZE, 8, "half strength");
        garden.addPlant("Spike", "Snake Plant", "Bedroom", 14, 1);

        String fileName = "out/test-garden.txt";
        GardenFileStore.save(garden, fileName);
        Garden loaded = GardenFileStore.load(fileName);
        new File(fileName).delete();

        assertEquals(12, loaded.getToday());
        assertEquals(2, loaded.getPlants().size());
        Plant monty = loaded.findPlant("Monty");
        assertEquals(6, monty.getLastWateredDay());
        assertEquals(1, monty.getHistory().size());
        assertEquals("half strength", monty.getHistory().get(0).getNote());
    }

    @Test
    void sampleFileLoads() {
        Garden sample = GardenFileStore.load("data/sample-garden.txt");
        assertEquals(12, sample.getToday());
        assertEquals(6, sample.getPlants().size());
    }
}
