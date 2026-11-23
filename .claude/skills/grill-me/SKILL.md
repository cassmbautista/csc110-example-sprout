---
name: grill-me
description: Interview the student, in rounds of questions with recommended answers, to stress-test a plan, feature, or design decision, and write what gets decided into docs/decisions.md and docs/glossary.md as it happens. Use when the student says "grill me", "help me decide", "I'm not sure how to...", or when a plan is still fuzzy. Other planning skills reuse these interview rules.
---

# Grill Me (with docs)

Adapted from AI Hero's `grill-with-docs` for CSC110 students. Your job: interview the student until
the two of you share **one clear understanding** of the decision, and leave a paper trail on disk.

## Interview rules
1. **Map the decision tree first, silently.** Every decision branches into decisions that depend on it
   (e.g. "what is a Stop?" must be settled before "how do we reorder stops?").
2. **Ask in rounds.** A round = the questions whose prerequisites are already settled (the *frontier*).
   Ask **at most 4 questions per round** (students are beginners, and smaller rounds save usage).
   Then STOP and wait for answers.
3. **Every question gets your recommended answer and a one-line reason.** Offer lettered choices when
   possible so the student can reply fast ("1A, 2C, 3: my own idea...").
4. **Explain jargon** in a few plain words the first time you use it.
5. **Facts are your job, decisions are theirs.** If a question needs a fact you can look up (a file, the
   requirements in `docs/reference/project-ideas.md`, the deadline), look it up yourself. Never ask the
   student for something you could read.
6. **Push back kindly.** If an answer breaks a requirement, adds scope that won't fit before the deadline
   (see `docs/reference/deliverables.md`), or needs Java they haven't learned, say so and offer a simpler path.
7. **Edge cases are the good stuff.** Invent concrete scenarios: "What happens if a player is added to two
   teams?" "What if the file is missing when the app starts?"
8. Recompute the frontier after each round. Finished when every branch is visited and nothing is silently
   assumed. Then summarize the decisions in a short list and ask: "Did I get this right?"

## Round format
```
❓ **Q1 – <short title>**: <question, with choices A/B/C if useful>
➡️ **My recommendation:** <answer> — <why, one line>

---

❓ **Q2 – ...**
```

## Paper trail (write as you go, not at the end)
- **A term gets settled** (the project's own word for a thing, like "Stop", "Order Line", "Slot"):
  add it to `docs/glossary.md` right away. One or two sentences: what it IS. List words to avoid.
- **A decision gets settled that a teacher or future you would ask "why?" about**: add an entry to
  `docs/decisions.md` using its template (decision, why, other options considered). Skip tiny or obvious
  choices. Most rounds produce zero or one entry. That's fine.
- Everything else lives in the conversation, so if this grilling feeds a deliverable (plan, class design),
  write the deliverable before the student clears the chat.

## Ending
Tell the student what you wrote to which files, and the next sensible step (often the skill that called
this one, or `/checkpoint`).
