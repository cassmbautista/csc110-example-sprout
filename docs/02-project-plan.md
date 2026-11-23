# Project Plan

## Requirements → features
| Requirement (from my approved proposal) | Feature(s) | Priority |
|---|---|---|
| Add, edit, remove plants | F1 Plant list + Add/Edit/Remove buttons | Must |
| Reject bad plant input | F2 Plant validation rules in `Garden` | Must |
| Log care events; watering updates last-watered | F3 Water button + Log Care dialog | Must |
| Removing deletes history, renaming keeps it | F4 History stored inside each `Plant` | Must |
| Search by name; filter by room and status | F5 Search box + Room and Status dropdowns | Must |
| Summary that updates | F6 Summary bar | Must |
| Save and reopen everything | F7 Auto-save + load on start | Must |
| (twist) simulated days | F8 Day counter + Next Day button | Must |

## Rules I decided
- **Next water day** = last watered day + watering interval.
- **Status:** Overdue if next water day < today · Due Today if = today · OK otherwise.
- **Days overdue** = today − next water day (only when overdue).
- **Plant names** are unique **ignoring capitals** ("monty" = "Monty"), trimmed, 1–30 characters, no `|`.
- **Watering interval:** 1–60 days. **Last watered:** between Day 1 and today.
- **Care event day:** between Day 1 and today (no future events). Only **Water** changes last-watered,
  and only if the event day is later than the current last-watered day (logging an old watering doesn't move it backward).
- **"Waterings in the last 7 days"** counts Water events with day > today − 7.
- **Most overdue plant:** the one with the largest days overdue; ties go to the first in the list. "None" if nothing is overdue.
- **Saving:** auto-save to `data/garden.txt` after every change. If that file doesn't exist, load `data/sample-garden.txt`.

## Features by priority
### Must
- [x] F1 Plant table with Add / Edit / Remove
- [x] F2 Validation with clear messages
- [x] F3 Water (today) button + Log Care dialog
- [x] F4 History per plant + History view
- [x] F5 Search + room filter + status filter
- [x] F6 Summary bar
- [x] F7 Auto-save / load, with sample data
- [x] F8 Day counter + Next Day
### Should
- [x] Confirm before Remove ("This deletes 4 care events")
- [x] Error messages in a status line instead of lots of pop-ups
### Could
- [x] Row colors for Overdue / Due Today (stretch goal)
- [ ] Undo last care event

## Build order
| When | Build | Done? |
|---|---|---|
| by Mon Nov 30 | Plan, design, clickable HTML prototype | ☐ |
| Nov 30 – Dec 2 | `Plant`, `CareEvent`, `Garden` rules + tests (F2, F3, F4, F8) | ☐ |
| Dec 2 – Dec 7 | Wire UI (F1, F5, F6), then save/load (F7) | ☐ |
| Dec 7 – Dec 9 | Row colors, bug fixes, write-up, slides, demo practice | ☐ |

## Risks
| Risk | What I'll do about it |
|---|---|
| I don't understand the save/load code | Run `/explain-it` on `GardenFileStore`; keep the format simple enough to read in Notepad |
| Running out of Claude usage near the deadline | One feature per chat, `/clear` after each, finish the Musts by Dec 7 |
| Swing table code gets confusing | Keep all table code in `MainWindow`; the model never touches Swing |

## Manual test checklist
| # | Do this | Expect this | Pass? |
|---|---|---|---|
| 1 | Start the app with no `data/garden.txt` | Sample plants load, Day 12 | ☐ |
| 2 | Add plant "Fern", Fern, Bathroom, every 3 days, last watered 12 | Fern appears with status OK | ☐ |
| 3 | Add another plant named "fern" | Status line: "A plant named fern already exists." | ☐ |
| 4 | Add a plant with interval 0 or 99 | "Watering interval must be 1–60 days." | ☐ |
| 5 | Edit Fern → rename to "Boston Fern" | Name changes; History still shows its events | ☐ |
| 6 | Select an overdue plant → Water | Status becomes OK; overdue count drops by 1; waterings (7 days) goes up by 1 | ☐ |
| 7 | Log Care: Fertilize on Day 20 (future) | "Care day can't be in the future (today is Day 12)." | ☐ |
| 8 | Type "mon" in search | Only plants with "mon" in the name show | ☐ |
| 9 | Room = Office, Status = Overdue | Only overdue Office plants | ☐ |
| 10 | Next Day ×3 | Day 15; more plants overdue; summary updates | ☐ |
| 11 | Remove a plant → confirm | It's gone, and so is its history | ☐ |
| 12 | Close and reopen the app | Same day, plants, and history | ☐ |
