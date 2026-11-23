# Demo Script (about 4 minutes)

**Reset before demo:** delete `data/garden.txt`, then launch → sample garden, Day 12.

| # | Click / do | Say (one sentence) | Requirement shown |
|---|---|---|---|
| 1 | Launch app | "This is Sprout. Red rows are overdue, yellow is due today, and the bottom bar is my whole collection at a glance." | Summary |
| 2 | Status → Overdue | "If I just want the thirsty ones, I filter by status: Basil and Fernando." | Filter |
| 3 | Select Basil → Water Today | "Watering Basil makes it OK, overdue drops to 1, and waterings this week goes up." | Care + summary updates |
| 4 | Status → All, Room → Office, type "pi" | "Search and filters work together. Here's Pip in the Office." | Search + filter |
| 5 | Clear filters → Add: "monty" | "If I try to add another Monty, even lowercase, it tells me why instead of crashing." | Validation (error case) |
| 6 | Fix it → "Ivy", English Ivy, Kitchen, 5, 12 → OK | "A valid plant shows up right away." | Add |
| 7 | Select Ivy → Log Care → Fertilize, Day 20 | "It also won't let me log care in the future." | Validation (error case) |
| 8 | Select Monty → Edit → rename "Big Monty" → History | "Renaming keeps the history because the history lives inside the plant object." | Consistency rule |
| 9 | Next Day ×3 | "Time passes. More plants go overdue and the summary updates." | Day counter |
| 10 | Close the app → `run.bat` again | "Everything auto-saved. It's still Day 15, with Ivy and Big Monty." | Save + reopen |
| 11 | Switch to slides → `Garden.validatePlant` | "Here's the rule code I'm proudest of..." | Code |

## Backup plan
Screenshots of steps 1, 3, 5, and 10 are in the slides. If the app won't start, show those and walk through `Garden.java`.
