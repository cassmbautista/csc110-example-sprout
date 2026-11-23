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
