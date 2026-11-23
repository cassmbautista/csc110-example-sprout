---
name: project-plan
description: Turn the student's proposal into docs/02-project-plan.md, with features mapped to every official requirement, must/should/could priorities, a build order fitted to the class calendar, risks, and a manual test checklist. Use for "project plan", "plan my project", or the second deliverable.
---

# Project Plan

Inputs: `docs/01-proposal.md`, `docs/reference/project-ideas.md`, `docs/reference/deliverables.md`.
Output: fill in `docs/02-project-plan.md` (the template has the sections).

## Steps
1. Read the inputs. Build a draft list of **features** that together cover **every** official requirement
   for their project. Each requirement must map to at least one feature. Nothing silently dropped.
2. Grill the student using `.claude/skills/grill-me/SKILL.md` (read it). Good questions for this stage:
   - Scope: which features are **Must** (needed for requirements), **Should**, **Could** (stretch)?
   - Rules: the exact rules the requirements leave open (e.g. league points per win/draw, what "over budget"
     means, coffee order status transitions, Snake grid size). These rules must be written down.
   - Edge cases per feature: empty input, duplicates, negative numbers, missing items, deleting something
     other things depend on.
   - The demo: what 3–5 minute story will show it off?
3. Build order: order the Must features so the app works early and grows. Models and rules first, then UI
   wiring, then save/load, then polish. Fit it to the class calendar in `deliverables.md`, and leave the last
   2 days for bug fixing, slides, write-up, and demo practice.
4. Manual test checklist: for each requirement, one or two "Do X → expect Y" checks the student or instructor
   can do in the running app. These double as demo steps.
5. Write the file. Keep it scannable: checkboxes, tables, short lines. Record decisions about rules in
   `docs/decisions.md` (they're exactly the "why did you do it that way?" kind).

End: "Plan done. `/checkpoint`, `/clear`, then `/class-design`."
