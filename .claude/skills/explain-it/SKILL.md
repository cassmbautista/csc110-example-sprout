---
name: explain-it
description: Explain a class, method, feature, or line of the student's code at a CSC110 level, then quiz them so they can explain it themselves in the demo and write-up. Use for "explain", "what does this do", "I don't understand", "quiz me", or "help me get ready for questions".
---

# Explain It

Rule of the course: the student must be able to explain every class. This skill builds that skill.

1. Ask what to explain if they didn't say (offer: a class, a feature end-to-end, or "the part I'm most
   worried about").
2. Read only that code. Explain in this order:
   - **Big picture** (2 sentences): what it's responsible for and who calls it.
   - **Walk-through**: trace one real example with real values (e.g. "when you click Add with name
     'Denver', `addStop("Denver", 2, ...)` runs... the list goes from 3 to 4 stops").
   - **Connect to CSC110**: name the concepts (constructor, `private` field, `ArrayList` traversal,
     return value, `if` validation...) with the exact method names.
   - **Beyond CSC110** parts: what it does, in one sentence. They don't need to master it, only describe it.
3. **Quiz:** ask 3 questions, one at a time, from easy to hard (predict the output, why is this field private,
   what breaks if we remove this line). Give honest feedback and a hint before revealing answers.
4. Offer to add a short "In my words" note the student writes to `docs/final-writeup.md` notes section.
   They must write it; you can fix grammar only.
