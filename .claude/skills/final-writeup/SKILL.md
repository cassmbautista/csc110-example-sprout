---
name: final-writeup
description: Final submission prep. First builds docs/concept-map.md, linking each CSC110 concept to the exact file and method in the student's code. Then assembles docs/final-writeup.md and polishes README.md. Claude drafts the factual parts; the student writes the reflections. Use for "concept map", "connect class concepts to my code", "write-up", "final report", "README", or "get ready to submit".
---

# Final Write-up (includes the concept map)

Work in two parts. If the student only asked for the concept map, do Part 1 and stop.

## Part 1 — Concept map → `docs/concept-map.md`
Concept list: `docs/reference/csc110-concepts.md`.
1. Use cheap searches (Grep for `class `, `private `, `new ArrayList`, `for (`, `while (`, `if (`, `return `,
   `BEYOND CSC110`) instead of reading every file in full. Open files only to pick the best example.
2. For each concept, pick the **1–2 best examples**, the ones the student would most want to show.
   Row: concept · `File.java:line` → `methodName()` · one plain-English sentence on how it's used here.
   If a concept isn't used, write "Not used" with a short honest note.
3. **Beyond CSC110** section: each `// BEYOND CSC110:` area grouped by topic (Swing, file I/O, exceptions,
   lambdas, `extends`) with one sentence each. Don't list beyond-CSC110 code as a CSC110 concept.
4. Ask the student to pick their **top 3** (mark ⭐) and say in their own words why each matters.
   They write it; you fix grammar only.

## Part 2 — Write-up → `docs/final-writeup.md`
Inputs: all of `docs/` plus a quick look at `src/` file names.

**Claude drafts (factual, from the docs and code):**
- Overview: what the app does and who it's for (3–4 sentences)
- Requirements table: each official requirement → how it's met → how to test it
- Architecture summary (link `docs/03-class-design.md`). If the code drifted from the design, update the design doc
  and say what changed and why.
- How to run (Windows / Mac) and where the sample data is
- Testing: JUnit tests by name + manual checklist results
- Known issues / not finished (honest)
- Concept connections: summary of the ⭐ top 3 from Part 1

**Student writes (Claude only asks prompting questions and fixes grammar):**
- How I steered Claude: 2–3 specific moments, using `docs/steering-log.md`
- What I learned, and the concept I understand better now
- What I'd do next with one more week
Do **not** write these for them; the instructor grades them as the student's own voice.

**README.md:** project name, 1-line description, screenshot, how to run, features, links to the docs.

## Final checks (run them, report ✅/❌)
- `bash test.sh` passes · app compiles from a clean `out/` · sample data in `data/` loads
- every requirement in the table has a "how to test"
- docs 01–04 present · concept map filled · steering log has entries across the project ·
  repo is public (`gh repo view --json visibility`)

End: suggest `/checkpoint` with tag `final`, then `/demo-prep`.
