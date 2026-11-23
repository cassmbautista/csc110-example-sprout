# Decisions
<!-- Claude adds entries during /grill-me and planning. Only the "why did you do it that way?" decisions.
Newest at the bottom. Template:

## D<number>: <short title>
**Decided:** what we chose.
**Why:** the reason, in 1-2 sentences.
**Other options:** what we didn't pick (optional).
-->

## D1: Simulated "Day N" instead of real dates
**Decided:** The app tracks time as a whole-number Day with a Next Day button.
**Why:** Real dates need `LocalDate`, which we haven't learned, and a day counter makes overdue plants easy to demo.
**Other options:** Real calendar dates (more realistic, harder to demo and test).

## D2: Status is calculated, not saved
**Decided:** Overdue / Due Today / OK is computed from last-watered day + interval vs today.
**Why:** If status were saved, it could go out of date when the day changes. Computing it means it's always right.

## D3: Rule methods return a message instead of throwing errors
**Decided:** `Garden` methods like `addPlant` return `Garden.OK` or a plain-English error message.
**Why:** We haven't learned exceptions. A returned String is easy to test and easy for the UI to show.
**Other options:** Throwing `IllegalArgumentException` (the "professional" way, but beyond CSC110).

## D4: Care history lives inside each Plant
**Decided:** Each `Plant` has its own `ArrayList<CareEvent>` instead of `Garden` keeping one big event list.
**Why:** Removing a plant automatically removes its history, and renaming keeps it, without matching events by name.

## D5: Auto-save after every change
**Decided:** No Save button. Every successful change saves `data/garden.txt` right away.
**Why:** Nobody loses work by forgetting to save. If the file is missing, the sample file loads so the app is never empty for testing.

## D6: Names can't contain "|"
**Decided:** Plant names, species, rooms, and notes can't contain the `|` character.
**Why:** `|` separates fields in the save file, so allowing it would break loading.

## D7: Older waterings don't move "last watered" backward
**Decided:** Logging a Water event adds it to the history, but only changes last-watered if its day is later.
**Why:** Found while clicking through the prototype: logging a forgotten Day 3 watering made Monty look overdue.
