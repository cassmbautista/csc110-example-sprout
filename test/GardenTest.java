import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

// Tests for the rules in Garden and Plant (validation, watering, status, summaries).
public class GardenTest {

    // A garden on Day 12 with Monty (due Day 13) and Basil (due Day 11, so overdue).
    private Garden sampleGarden() {
        Garden garden = new Garden(12);
        garden.addPlant("Monty", "Monstera", "Living Room", 7, 6);
        garden.addPlant("Basil", "Sweet Basil", "Kitchen", 2, 9);
        return garden;
    }

    @Test
    void rejectsDuplicateNameIgnoringCase() {
        Garden garden = sampleGarden();
        assertEquals("A plant named monty already exists.",
                garden.addPlant("monty", "Pothos", "Office", 5, 10));
        assertEquals(2, garden.getPlants().size());
    }

    @Test
    void rejectsIntervalOutsideRangeAndFutureLastWatered() {
        Garden garden = sampleGarden();
        assertEquals("Watering interval must be 1-60 days.", garden.addPlant("Fern", "Fern", "Bath", 0, 10));
        assertEquals("Last watered must be between Day 1 and Day 12.", garden.addPlant("Fern", "Fern", "Bath", 3, 13));
    }

    @Test
    void editingKeepsHistoryAndStillBlocksDuplicates() {
        Garden garden = sampleGarden();
        garden.logCare("Monty", CareEvent.FERTILIZE, 8, "");
        assertEquals(Garden.OK, garden.editPlant("Monty", "Big Monty", "Monstera", "Living Room", 7, 6));
        assertEquals(1, garden.findPlant("Big Monty").getHistory().size());
        assertEquals("A plant named Basil already exists.",
                garden.editPlant("Big Monty", "Basil", "Monstera", "Living Room", 7, 6));
    }

    @Test
    void wateringFixesOverdueAndCountsInSummary() {
        Garden garden = sampleGarden();
        assertEquals(Plant.OVERDUE, garden.findPlant("Basil").getStatus(12));
        assertEquals(1, garden.countWithStatus(Plant.OVERDUE));

        assertEquals(Garden.OK, garden.waterToday("Basil"));
        assertEquals(Plant.OK, garden.findPlant("Basil").getStatus(12));
        assertEquals(0, garden.countWithStatus(Plant.OVERDUE));
        assertEquals(1, garden.wateringsInLastWeek());
    }

    @Test
    void rejectsCareForMissingPlantOrFutureDay() {
        Garden garden = sampleGarden();
        assertEquals("No plant named Cactus.", garden.logCare("Cactus", CareEvent.WATER, 10, ""));
        assertEquals("Care day can't be in the future (today is Day 12).",
                garden.logCare("Monty", CareEvent.WATER, 20, ""));
    }

    @Test
    void oldWateringDoesNotMoveLastWateredBackward() {
        Garden garden = sampleGarden();
        garden.logCare("Basil", CareEvent.WATER, 3, "late entry");
        assertEquals(9, garden.findPlant("Basil").getLastWateredDay());
    }

    @Test
    void mostOverduePicksLargestAndRemoveDeletesPlant() {
        Garden garden = sampleGarden();
        garden.advanceDay();
        garden.advanceDay(); // Day 14: Basil 3 days overdue, Monty 1 day overdue
        assertEquals("Basil", garden.mostOverduePlant().getName());
        assertEquals(Garden.OK, garden.removePlant("Basil"));
        assertNull(garden.findPlant("Basil"));
        assertEquals("Monty", garden.mostOverduePlant().getName());
    }
}
