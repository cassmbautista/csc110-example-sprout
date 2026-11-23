# Concept Map

## CSC110 concepts in my code
| Concept | Where (File → method) | How it's used here |
|---|---|---|
| ⭐ Classes & objects | `Plant.java`, `CareEvent.java`, `Garden.java` | Each plant is a `Plant` object; each watering is a `CareEvent` object stored inside its plant. |
| Constructors | `Plant.java:17` → `Plant(...)` | Sets all fields and starts an empty history list. |
| Encapsulation (`private` + getters/setters) | `Plant.java` fields + setters | Fields are private, so only `Garden.editPlant` changes a plant, and only after validating. |
| `toString` | `CareEvent.java:35` | Turns an event into "Day 8 - Fertilize (half strength)" for the History window. |
| Methods with parameters & return values | `Plant.java:31` → `getStatus(int today)` | Takes today's day, returns "Overdue", "Due Today", or "OK". |
| ⭐ `if / else` validation | `Garden.java:72` → `validatePlant(...)` | A chain of checks; the first broken rule returns its error message. |
| Loops (for-each, `while`) | `Garden.java:185` (nested for-each); `MainWindow.java:153` (`while`) | Nested loop counts waterings across all plants; the `while` reopens the form until the input is valid. |
| `ArrayList` (add / remove / get / size) | `Garden.java:10` `plants`; `Plant.java:15` `history` | The whole garden is an `ArrayList<Plant>`; each plant has an `ArrayList<CareEvent>`. |
| ⭐ Searching / filtering a list | `Garden.java:130` → `findPlant`; `Garden.java:144` → `search` | `findPlant` loops until a name matches; `search` builds a **new** list of plants passing all 3 filters. |
| Arrays | `GardenFileStore.java:46` `String[] parts` | Each saved line is split into an array of fields. |
| `String` methods | `Garden.java:135,146,148` | `equalsIgnoreCase` for duplicates, `toLowerCase` + `contains` for search, `trim` to clean input. |
| Calculations / accumulators | `Garden.java:171` `countWithStatus`; `Garden.java:195` `mostOverduePlant` | A counter adds 1 per matching plant; "most overdue" keeps track of the biggest value so far. |
| `static final` constants | `Plant.java:6-8`, `CareEvent.java:3-5` | Status and care-type names are constants, so a typo can't create a fake status. |

## Beyond CSC110 (Claude wrote these; I can describe what they do)
| Topic | Where | What it does |
|---|---|---|
| Swing GUI | `MainWindow.java`, `PlantForm.java` | Windows, buttons, text boxes, dropdowns, and a table. |
| Event listeners & lambdas | `MainWindow.java:76,118` | Code that runs when you click a button, type in search, or pick from a dropdown. |
| Inheritance (`extends`) | `MainWindow extends JFrame`, `StatusColorRenderer` (`MainWindow.java:304`) | Borrows everything a window/cell already does and changes a few things (like row colors). |
| File saving / loading | `GardenFileStore.java:23,35` | `Files.write` / `Files.readAllLines` turn the garden into lines of text and back. |
| Exceptions (`try/catch`) | `GardenFileStore.java:47`, `PlantForm.java:78` | Handle a missing file, a broken line, or "abc" typed where a number goes, without crashing. |

## My top 3 ⭐ and why they matter (in my words)
1. **Classes & objects:** splitting the app into `Plant`, `CareEvent`, and `Garden` made every rule have one home. When something broke, I knew which file to look in.
2. **`if / else` validation:** `validatePlant` is just a list of `if`s, but it's what keeps bad data out of the whole app, and it's shared by Add and Edit.
3. **Searching / filtering:** `search` is the same "loop and keep the matches" pattern from class, and it powers the search box *and* both dropdowns at once.

_(Example note: in a real project, the student writes this section themselves.)_
