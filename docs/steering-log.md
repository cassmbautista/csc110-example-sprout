# Steering Log
<!-- How YOU directed Claude. /checkpoint drafts an entry at the end of each task and asks you to correct it.
Be honest and specific. The best entries show a moment you changed Claude's direction.
You'll use these in your write-up and slides. Template:

## <date> — <task>
- **Goal:**
- **What I asked for:**
- **How I steered / corrected Claude:** (rejected an idea, caught a bug, asked for simpler code, gave an example...)
- **What I learned:**
- **Next:**
-->

> ℹ️ Example entries, written during the instructor's dogfood run of the starter packet. Yours should describe your real sessions.

## Nov 23 — Proposal
- **Goal:** Pick a project and write the proposal.
- **What I asked for:** Help shaping a Dealer's Choice idea about houseplants.
- **How I steered / corrected Claude:** Claude first suggested real calendar dates with reminders. I asked for something
  I could explain and demo quickly, so we switched to a "Day N" counter with a Next Day button (decision D1).
  I also cut "photo of each plant" because it's mostly graphics with no rules behind it.
- **What I learned:** Dealer's Choice ideas need rules that keep data consistent, not just a list of things.
- **Next:** /project-plan

## Nov 24 — Project plan + class design
- **Goal:** Plan the features and design the classes.
- **What I asked for:** /project-plan, then /class-design.
- **How I steered / corrected Claude:** Claude's first design had a separate `CareLog` class holding every event for
  every plant. I asked what happens when you rename a plant, since the events would point at the old name. We moved
  history inside `Plant` instead (D4). I also said no to exceptions for validation, since we haven't learned them, and we
  used returned messages (D3).
- **What I learned:** Where data lives decides how hard the consistency rules are.
- **Next:** /ui-prototype

## Nov 25 — UI prototype
- **Goal:** A clickable prototype to test my plan before writing any Java UI.
- **What I asked for:** /ui-prototype with one main window, not a bunch of pop-ups.
- **How I steered / corrected Claude:** Claude made an HTML version of the app with fake plants, and I clicked through
  my whole test checklist in it. Logging an old watering moved "Last watered" backward, so we added a rule that it only
  moves forward (D7). Claude's first version also had a confirmation pop-up after every watering; I asked for a
  Water Today button and a status-line message instead.
- **What I learned:** Clicking through a fake version found two rule problems my plan missed, and fixing them in
  HTML took minutes instead of rewriting Java.
- **Next:** /build-feature for the Garden rules.

## Nov 30 — Garden rules (F2, F3, F4, F8)
- **Goal:** Build `Plant`, `CareEvent`, `Garden` with tests first.
- **What I asked for:** /build-feature for the rules, tests first.
- **How I steered / corrected Claude:** While reading the tests I asked "what if I log an old watering from last week?" Claude's
  plan would have moved last-watered *backward*. We added the rule "watering only moves forward" and a test for it
  (`oldWateringDoesNotMoveLastWateredBackward`). Claude also wanted 15+ tests. I asked for one or two per rule to save usage, and we ended up with 7.
- **What I learned:** `existing != editing` compares *objects*, not names. That's how editing "Monty" doesn't count as a duplicate of itself.
- **Next:** save/load

## Dec 2 — Save/load + real UI (F1, F5, F6, F7)
- **Goal:** Save/load, then build the Swing main window from the prototype and wire it to `Garden`.
- **What I asked for:** /build-feature for save/load (with a round-trip test), then for the main window.
- **How I steered / corrected Claude:** I asked "what happens if someone edits garden.txt in Notepad and breaks a line?"
  The first version would crash on load, so now it skips broken lines. In the UI, Claude first validated the interval
  inside `PlantForm`. I pointed out that rules belong in `Garden` (our design rule), so the form now only reads text
  and `Garden` decides. I also caught "Most overdue: Basil (1 days)" and asked for correct grammar.
- **What I learned:** The UI loop "show form → ask Garden → show error → show form again" keeps what the user typed.
- **Next:** concept map, write-up, demo prep
