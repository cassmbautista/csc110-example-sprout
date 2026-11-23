---
name: ui-prototype
description: Build a clickable HTML prototype of the app (docs/prototype/index.html) from the plan and class design, then have the student click through it as a rough draft of their project, record what they find in docs/04-ui-prototype.md, and fix the plan/design before any Java UI is written. Use for "UI prototype", "design the screens", "mockup", "wireframe", "clickable prototype", or the UI prototype deliverable.
---

# UI Prototype (clickable HTML rough draft)

Inputs: `docs/02-project-plan.md`, `docs/03-class-design.md`. Outputs: `docs/prototype/index.html` and
`docs/04-ui-prototype.md`.

The prototype is a **rough draft of the whole app**, made in HTML because it's fast and cheap to change.
The student clicks through it to find problems (missing screens, unclear rules, confusing steps) *before*
any Swing code exists. It is throwaway: it is not Java, it is not graded as code, and the student does not
need to explain its HTML/JavaScript.

## 1. Agree on the screens (quick)
List the screens needed for every **Must** feature and say in one line what's on each. Prefer **one main
window** with tabs or a list + detail layout, like a desktop app. Ask at most 3 questions (first screen?
where do errors show? Save button or auto-save?). Wait for "go".

## 2. Build `docs/prototype/index.html`
- **One self-contained file**: plain HTML, a `<style>` block, a small `<script>`. No libraries, no CDN links,
  no internet needed. Keep it under ~400 lines.
- Make it look like a simple desktop window (title bar with the app name, tabs/buttons, tables, forms) so it
  maps onto Swing later. Use the class design's names on screen (same fields, same statuses).
- **Fake but realistic data** from the student's project (5–8 rows).
- **Clickable**: tabs and buttons switch screens; Add/Edit forms add to the list **in memory**; search and
  filters work on the fake data; summaries update.
- **Show the rules**: for each rule in the plan's "Rules I decided", make bad input show the exact error
  message the real app will use. Save/reopen can be a button that says "Saved (not really — prototype)".
- Open it for the student: Mac `open docs/prototype/index.html`, Windows `start docs\prototype\index.html`.

## 3. Student works through it (the point of this step)
Ask the student to click through their **demo story** and their **manual test checklist** from the plan,
and tell you what's missing, confusing, or wrong. Prompt them: "Which requirement can't you do yet?",
"Which error message would confuse a user?", "What did you expect that didn't happen?"
Fix the prototype (max 2 rounds; it's a draft). If they find a missing feature or rule, **update
`docs/02-project-plan.md` / `docs/03-class-design.md`** and add a line to `docs/decisions.md`.

## 4. Fill in `docs/04-ui-prototype.md`
Screens (one line each + which requirements they cover), navigation flow, where errors appear, and
**"What I found clicking through"**: the problems found and what changed in the plan or design. Write the
findings in the student's words. Ask them for 1–2 screenshots of the prototype for `docs/images/`
(Windows: Win+Shift+S; Mac: Cmd+Shift+4) and link them.

End: "Prototype done. If your plan and class design are done too, run `/checkpoint` and tag it
`plan-design-prototype` when asked. Then `/clear`. Next: `/build-feature` (the first one builds the real Swing window from this prototype)."
