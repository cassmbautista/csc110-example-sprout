# Project Proposal

**Name:** Example Student (instructor example)
**Project:** Dealer's Choice
**App name:** Sprout — Plant Care Tracker
**Repo link:** _(added after `/setup-repo` publishes the repo)_

## My idea in two sentences
Sprout helps someone with a lot of houseplants keep track of which plants need water today and which ones they've
been ignoring. You add your plants, log care like watering or fertilizing, and Sprout tells you what's overdue.

## My twist / theme
Instead of real calendar dates, the app has a **"Today is Day N"** counter with a **Next Day** button. That makes
it easy to demo: click Next Day a few times and watch plants turn overdue. Plants are grouped by **room**
(Kitchen, Bedroom, Office...), because that's how I actually walk around and water mine.

## Requirements checklist
- [ ] Add, edit, and remove plants. Each plant has a name, species, room, watering interval (days), and the day it was last watered.
- [ ] Reject blank names, duplicate plant names, intervals outside 1–60 days, and "last watered" days in the future.
- [ ] Log care events (water, fertilize, repot) with a day and an optional note. Reject events for missing plants or future days. Watering updates the plant's last-watered day.
- [ ] Removing a plant also removes its care history; renaming a plant keeps its history.
- [ ] Search plants by name and filter by room and by status (overdue / due today / OK).
- [ ] Show a summary: total plants, overdue count, due-today count, waterings in the last 7 days, and the most overdue plant. The summary updates whenever anything changes.
- [ ] Save and reopen all plants, their care history, and the current day exactly as they were.

## The 60-second demo I imagine
Open Sprout with sample plants. Filter to "Overdue" and see two plants. Water one, and it disappears from the
overdue list and the summary updates. Try to add a plant called "Monty" again and get "A plant named Monty already
exists." Click Next Day three times and watch more plants turn overdue. Close the app, reopen it, and it's still Day 15.

## One stretch goal (if time allows)
Color the rows: red for overdue, yellow for due today.

## My biggest worry
Saving and loading. We haven't learned files, so I need to make sure I understand what Claude writes.

---

## Dealer's Choice only
- **Data objects (at least 3) and how they link:** `Plant` (has an `ArrayList<CareEvent>` history), `CareEvent`,
  and `Garden` (has an `ArrayList<Plant>` and the current day).
- **Add / edit / remove + validation:** plants (blank or duplicate names, interval 1–60, no future last-watered day).
- **Consistency rules (at least 2):** (1) watering updates last-watered, and events can't be in the future;
  (2) removing a plant removes its history, and renaming keeps it; (3) names stay unique, even when editing.
- **Search + filter:** search by name; filter by room and by status.
- **Calculated summaries:** total, overdue, due today, waterings in the last 7 days, most overdue plant.
- **What gets saved and reopened:** every plant, its full care history, and the current day.
- **Instructor approval:** ☐ pending ☑ approved (date: Nov 23, example)
