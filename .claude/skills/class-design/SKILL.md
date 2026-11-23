---
name: class-design
description: Design the project's data and class architecture with the student and write docs/03-class-design.md, covering classes, fields, constructors, key methods, relationships, a Mermaid class diagram, where each rule lives, and the save-file format. Use for "class design", "data design", "architecture", "what classes do I need", or the data design deliverable.
---

# Class Design (data design)

Inputs: `docs/02-project-plan.md`, `docs/glossary.md`. Output: `docs/03-class-design.md`.
This document becomes the map Claude reads before every feature, so it must be accurate and short.

## Design rules for CSC110
- Nouns in the glossary are candidate classes. Usually 3–6 **model** classes + 1 **manager** class that holds
  the `ArrayList`s and enforces rules + 1 **file store** class for save/load + a few **UI** classes.
- All fields `private`; getters always; setters only where editing is allowed (and they validate).
- Rules live in the model/manager, never in the UI. Model classes never import Swing.
- Link objects the simple way: an object holds a reference, or an `ArrayList` of references. When something
  must survive save/load, give it a unique `id` or name so the file can refer to it.
- Avoid inheritance and interfaces unless the student has a real reason and agrees (note it in decisions.md).
- Statuses/modes as `String` constants (e.g. `public static final String READY = "Ready";`).

## Steps
1. Read inputs and draft the classes silently.
2. Show the student a one-screen draft: class names with a one-line responsibility each, and how they connect.
   Then grill (rules in `.claude/skills/grill-me/SKILL.md`, read it) on the hard parts, such as:
   - "Who is responsible for checking X?" (one owner per rule)
   - what happens to links when something is deleted or edited (missing stop, renamed team, removed menu item)
   - what must be saved so the app reopens exactly where it was
   - which numbers are `int` vs `double` (money: recommend `int` cents or a `double` rounded for display; record the decision)
3. Write `docs/03-class-design.md` using its template:
   - Mermaid `classDiagram` (GitHub draws it automatically)
   - a table per class: fields (type), constructor, key methods (signature + one-line purpose)
   - "Where the rules live" table: rule → class.method
   - save file format with 2–3 example lines
   - CSC110 concepts each class shows off (helps the concept map later)
4. Ask: "Can you explain to me in your own words what `<manager class>` does?" If they struggle, simplify.

Do NOT write Java files in this step unless the student asks; the design is the deliverable.
End: "Design done. `/checkpoint`, `/clear`, then `/ui-prototype`."
