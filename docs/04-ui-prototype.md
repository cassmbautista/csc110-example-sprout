# UI Prototype

**Open the prototype:** [`docs/prototype/index.html`](prototype/index.html). Download the repo and open the file in a
browser. It uses fake data and nothing is really saved. The real app will be Java Swing.

## Screens
| Screen | What's on it | Requirements |
|---|---|---|
| Main window | Day counter + Next Day, search box, Room and Status filters, plant table (overdue rows red, due-today rows yellow), action buttons, summary bar, status line | Search/filter, summary, simulated days, statuses |
| Add / Edit Plant | Name, species, room, water every (days), last watered (day) | Add/edit with validation |
| Log Care | Type (Water / Fertilize / Repot), day, note | Log care events |
| History | One line per care event, oldest first | History per plant |
| Remove confirm | "Remove Monty and its 2 care events?" | Remove deletes history |

## Navigation flow
- Main → **Add** → Add Plant form → OK → table updates, status line says "Added Ivy."
- Main → select a row → **Edit** / **Log Care...** → form → OK → back to Main
- Main → select a row → **Water Today** → no pop-up; status line confirms
- Main → select a row → **History** → History dialog
- Main → select a row → **Remove** → confirm → gone
- **Next Day** → Day + 1 → statuses and summary update
- Typing in Search or changing a filter → table filters right away
- Every change auto-saves (no Save button, D5)

## Errors and messages
- Form errors show **inside the form in red**, and the form keeps what you typed:
  "Name must be 1–30 characters." · "There's already a plant named Monty." · "Watering interval must be 1–60 days." ·
  "Last watered must be between Day 1 and today (Day 12)." · "Care day must be between Day 1 and today (Day 12)." ·
  "Names and rooms can't contain |."
- Edit / Remove / Water / Log Care / History with no row selected: **"Select a plant first."** in the status line.

## Screenshots
![Prototype main window](images/prototype-main.png)

## What I found clicking through
I clicked through my demo story and all 12 checks in my manual test checklist.
- **Logging an old watering made a plant look overdue.** I logged a Water for Monty on Day 3 (I forgot to log it
  that day), and "Last watered" jumped back to Day 3. Now an older watering goes into the history but doesn't move
  "Last watered" backward. I added this rule to my plan (D7).
- **"monty" and "Monty" were both allowed.** That would be confusing in search and in the save file. Names are now
  unique ignoring capitals (added to "Rules I decided").
- **Remove deleted the whole history with no warning.** Added a confirm that says how many care events will go.
- **Too many pop-ups.** The first version had an "Are you sure?" after every watering. Watering is the most common
  thing you do, so it's now its own **Water Today** button with a status-line message instead.
- **The table felt cramped.** Moved the summary to a bar at the bottom.
