import java.util.ArrayList;

// The manager: owns every plant and the current day, and enforces every rule.
// Methods that change data return Garden.OK when they work, or an error message the UI can show.
public class Garden {
    public static final String OK = "OK";
    public static final String ALL = "All";
    public static final int MAX_NAME_LENGTH = 30;

    private ArrayList<Plant> plants;
    private int today;

    public Garden(int today) {
        this.plants = new ArrayList<Plant>();
        this.today = today;
    }

    public int getToday() {
        return today;
    }

    public ArrayList<Plant> getPlants() {
        return plants;
    }

    public void advanceDay() {
        today++;
    }

    // ---------- Add / edit / remove ----------

    public String addPlant(String name, String species, String room, int waterEveryDays, int lastWateredDay) {
        String error = validatePlant(null, name, species, room, waterEveryDays, lastWateredDay);
        if (!error.equals(OK)) {
            return error;
        }
        plants.add(new Plant(name.trim(), species.trim(), room.trim(), waterEveryDays, lastWateredDay));
        return OK;
    }

    // Updates the plant called oldName. Its history stays with it, even if the name changes.
    public String editPlant(String oldName, String name, String species, String room,
                            int waterEveryDays, int lastWateredDay) {
        Plant plant = findPlant(oldName);
        if (plant == null) {
            return "No plant named " + oldName + ".";
        }
        String error = validatePlant(plant, name, species, room, waterEveryDays, lastWateredDay);
        if (!error.equals(OK)) {
            return error;
        }
        plant.setName(name.trim());
        plant.setSpecies(species.trim());
        plant.setRoom(room.trim());
        plant.setWaterEveryDays(waterEveryDays);
        plant.setLastWateredDay(lastWateredDay);
        return OK;
    }

    // Removing the plant also removes its history, because the history lives inside the plant.
    public String removePlant(String name) {
        Plant plant = findPlant(name);
        if (plant == null) {
            return "No plant named " + name + ".";
        }
        plants.remove(plant);
        return OK;
    }

    // Shared rules for add and edit. "editing" is the plant being edited (null when adding),
    // so a plant doesn't count as a duplicate of itself.
    private String validatePlant(Plant editing, String name, String species, String room,
                                 int waterEveryDays, int lastWateredDay) {
        if (name == null || name.trim().isEmpty()) {
            return "Name is required.";
        }
        if (name.trim().length() > MAX_NAME_LENGTH) {
            return "Name must be " + MAX_NAME_LENGTH + " characters or less.";
        }
        if (hasBar(name) || hasBar(species) || hasBar(room)) {
            return "Please don't use the | character.";
        }
        Plant existing = findPlant(name);
        if (existing != null && existing != editing) {
            return "A plant named " + name.trim() + " already exists.";
        }
        if (waterEveryDays < 1 || waterEveryDays > 60) {
            return "Watering interval must be 1-60 days.";
        }
        if (lastWateredDay < 1 || lastWateredDay > today) {
            return "Last watered must be between Day 1 and Day " + today + ".";
        }
        return OK;
    }

    private boolean hasBar(String text) {
        return text != null && text.contains("|");
    }

    // ---------- Care events ----------

    public String logCare(String name, String type, int day, String note) {
        Plant plant = findPlant(name);
        if (plant == null) {
            return "No plant named " + name + ".";
        }
        if (!CareEvent.isValidType(type)) {
            return "Care type must be Water, Fertilize, or Repot.";
        }
        if (day > today) {
            return "Care day can't be in the future (today is Day " + today + ").";
        }
        if (day < 1) {
            return "Care day must be Day 1 or later.";
        }
        if (hasBar(note)) {
            return "Please don't use the | character.";
        }
        plant.addEvent(new CareEvent(day, type, note.trim()));
        return OK;
    }

    public String waterToday(String name) {
        return logCare(name, CareEvent.WATER, today, "");
    }

    // ---------- Finding, searching, filtering ----------

    // Returns the plant with this name (ignoring capitals), or null if there isn't one.
    public Plant findPlant(String name) {
        if (name == null) {
            return null;
        }
        for (Plant plant : plants) {
            if (plant.getName().equalsIgnoreCase(name.trim())) {
                return plant;
            }
        }
        return null;
    }

    // Builds a new list of plants that match all three filters.
    // Empty text means "any name"; ALL means "any room" / "any status".
    public ArrayList<Plant> search(String text, String room, String status) {
        ArrayList<Plant> matches = new ArrayList<Plant>();
        String lowerText = text.trim().toLowerCase();
        for (Plant plant : plants) {
            boolean nameMatches = plant.getName().toLowerCase().contains(lowerText);
            boolean roomMatches = room.equals(ALL) || plant.getRoom().equalsIgnoreCase(room);
            boolean statusMatches = status.equals(ALL) || plant.getStatus(today).equals(status);
            if (nameMatches && roomMatches && statusMatches) {
                matches.add(plant);
            }
        }
        return matches;
    }

    // Every different room name, in the order first seen (for the Room dropdown).
    public ArrayList<String> getRooms() {
        ArrayList<String> rooms = new ArrayList<String>();
        for (Plant plant : plants) {
            if (!rooms.contains(plant.getRoom())) {
                rooms.add(plant.getRoom());
            }
        }
        return rooms;
    }

    // ---------- Summary ----------

    public int countWithStatus(String status) {
        int count = 0;
        for (Plant plant : plants) {
            if (plant.getStatus(today).equals(status)) {
                count++;
            }
        }
        return count;
    }

    // Counts Water events in the last 7 days (today and the 6 days before it).
    public int wateringsInLastWeek() {
        int count = 0;
        for (Plant plant : plants) {
            for (CareEvent event : plant.getHistory()) {
                if (event.getType().equals(CareEvent.WATER) && event.getDay() > today - 7) {
                    count++;
                }
            }
        }
        return count;
    }

    // The plant with the most days overdue (first one wins a tie), or null if none are overdue.
    public Plant mostOverduePlant() {
        Plant worst = null;
        for (Plant plant : plants) {
            int overdue = plant.daysOverdue(today);
            if (overdue > 0 && (worst == null || overdue > worst.daysOverdue(today))) {
                worst = plant;
            }
        }
        return worst;
    }
}
