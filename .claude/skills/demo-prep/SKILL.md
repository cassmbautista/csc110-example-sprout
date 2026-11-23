---
name: demo-prep
description: Prepare the live demo and slide deck by writing a timed demo script (docs/demo-script.md), slide-by-slide content following the required outline, and a practice Q&A. Use for "demo", "presentation", "slides", "practice", or "get ready to present".
---

# Demo Prep

Inputs: `docs/reference/slide-deck-outline.md`, `docs/02-project-plan.md` (manual test checklist),
`docs/concept-map.md`, `docs/steering-log.md`, `docs/final-writeup.md`.

1. **Demo script** → `docs/demo-script.md`: a 3–5 minute story through the app that hits every major
   requirement, including at least one *error case* (show it rejecting bad input) and *save → close →
   reopen* to prove persistence. Each step = what to click + what to say (1 sentence). Include a
   "reset" step: which sample file to load so the demo starts the same every time.
2. **Slides content**: for each slide in the outline, write the bullet points and suggest what screenshot,
   diagram, or code snippet goes on it. Keep bullets short (≤ 6 words each); the student talks, the slide
   supports. Pull real material: class diagram, ⭐ concept map examples, real steering-log moments.
   The student builds the actual deck in Google Slides / PowerPoint / Canva.
3. **Practice Q&A (worth 15 points, so take it seriously)**: the instructor asks ~3 questions, at least one per area:
   - **Their code**: pick a real class/method: "What is it responsible for?", "Walk me through clicking ___",
     "Why is this field `private`?", "What does this BEYOND CSC110 part do?"
   - **Their approach/design**: from `docs/decisions.md`: "Why is this rule in this class?", "What else did you
     consider?", "What happens to ___ when ___ is deleted?"
   - **Working with Claude**: from `docs/steering-log.md`: "When was Claude wrong and how did you notice?",
     "How did you check its code worked?"
   Ask 6 questions (2 per area), one at a time. Score each 0–5 (5 = clear, correct, own words, can point
   to the code; 3–4 = mostly right or needed a hint; 1–2 = partial/memorized; 0 = couldn't answer), with one tip to improve. For any score under 4, suggest `/explain-it` on that code.
4. Remind them: rehearse once out loud with a timer, and have a backup (screenshots in the slides) in case
   the app misbehaves live.
