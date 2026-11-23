# Glossary
<!-- Your project's own words, agreed once so you and Claude mean the same thing. Claude adds terms
during /grill-me. Format:

**Term**: one or two sentences saying what it IS.
_Avoid_: other words that mean the same thing (so we always use one name).
-->

**Plant**: one houseplant the user owns, identified by its unique name (e.g. "Monty").
_Avoid_: item, entry

**Room**: the place a plant lives (Kitchen, Office...). Plain text typed by the user; used for filtering.
_Avoid_: location, area

**Day**: the app's simulated date, a whole number starting at Day 1. "Today" is the current Day; **Next Day** adds 1.
_Avoid_: date, time

**Care Event**: one thing done to a plant on a Day: Water, Fertilize, or Repot, with an optional note.
_Avoid_: log entry, action, task

**Watering Interval**: how many days a plant can go between waterings (1–60).
_Avoid_: frequency, schedule

**Status**: computed, never stored. **Overdue** if the next watering day is before today, **Due Today** if it is today, otherwise **OK**.
