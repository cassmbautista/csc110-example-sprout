import java.util.ArrayList;

// One houseplant: its info, its care history, and the math for when it needs water next.
// Plant does not check rules like "names must be unique"; Garden does that before calling the setters.
public class Plant {
    public static final String OVERDUE = "Overdue";
    public static final String DUE_TODAY = "Due Today";
    public static final String OK = "OK";

    private String name;
    private String species;
    private String room;
    private int waterEveryDays;
    private int lastWateredDay;
    private ArrayList<CareEvent> history;

    public Plant(String name, String species, String room, int waterEveryDays, int lastWateredDay) {
        this.name = name;
        this.species = species;
        this.room = room;
        this.waterEveryDays = waterEveryDays;
        this.lastWateredDay = lastWateredDay;
        this.history = new ArrayList<CareEvent>();
    }

    // The day this plant should be watered next.
    public int nextWaterDay() {
        return lastWateredDay + waterEveryDays;
    }

    public String getStatus(int today) {
        if (nextWaterDay() < today) {
            return OVERDUE;
        } else if (nextWaterDay() == today) {
            return DUE_TODAY;
        } else {
            return OK;
        }
    }

    // How many days late the watering is (0 if not overdue).
    public int daysOverdue(int today) {
        if (nextWaterDay() < today) {
            return today - nextWaterDay();
        }
        return 0;
    }

    // Adds an event to the history. A Water event only moves lastWateredDay forward, never backward.
    public void addEvent(CareEvent event) {
        history.add(event);
        if (event.getType().equals(CareEvent.WATER) && event.getDay() > lastWateredDay) {
            lastWateredDay = event.getDay();
        }
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getRoom() {
        return room;
    }

    public int getWaterEveryDays() {
        return waterEveryDays;
    }

    public int getLastWateredDay() {
        return lastWateredDay;
    }

    public ArrayList<CareEvent> getHistory() {
        return history;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setWaterEveryDays(int waterEveryDays) {
        this.waterEveryDays = waterEveryDays;
    }

    public void setLastWateredDay(int lastWateredDay) {
        this.lastWateredDay = lastWateredDay;
    }

    @Override
    public String toString() {
        return name + " (" + species + ", " + room + ")";
    }
}
