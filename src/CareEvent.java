// One thing done to a plant on a given day: watering, fertilizing, or repotting.
public class CareEvent {
    public static final String WATER = "Water";
    public static final String FERTILIZE = "Fertilize";
    public static final String REPOT = "Repot";

    private int day;
    private String type;
    private String note;

    public CareEvent(int day, String type, String note) {
        this.day = day;
        this.type = type;
        this.note = note;
    }

    public int getDay() {
        return day;
    }

    public String getType() {
        return type;
    }

    public String getNote() {
        return note;
    }

    // Returns true if the type is one of the three allowed care types.
    public static boolean isValidType(String type) {
        return type.equals(WATER) || type.equals(FERTILIZE) || type.equals(REPOT);
    }

    @Override
    public String toString() {
        String text = "Day " + day + " - " + type;
        if (!note.isEmpty()) {
            text = text + " (" + note + ")";
        }
        return text;
    }
}
