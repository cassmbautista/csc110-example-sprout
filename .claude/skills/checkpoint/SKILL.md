---
name: checkpoint
description: End-of-task save point. Appends an honest entry to docs/steering-log.md, commits with a clear message, pushes to GitHub, optionally tags a deliverable, then tells the student to /clear. Use for "checkpoint", "save my work", "commit", "push", "I'm done for today".
---

# Checkpoint

1. **See what changed:** `git status --short` and `git diff --stat` (not full diffs, to save usage).
   If nothing changed, say so and skip to step 5.
2. **Steering log entry.** Draft an entry for `docs/steering-log.md` from this chat using its template:
   goal, what the student asked for, where they **steered or corrected** you (changed your idea, rejected
   code, caught a bug, asked for simpler code), what they learned, and what's next. Then ask ONE question:
   "Anything to add or fix about how you steered me?" Use their answer. Never invent steering that didn't happen.
   If nothing notable happened (tiny change), a one-line entry is fine.
3. **Commit:** `git add .` then `git commit -m "<message>"`. The message is present-tense and specific:
   "Add stop reordering with up/down buttons", not "updates". Check no secrets or junk are staged.
4. **Push:** `git push` (first push on a new branch: `git push -u origin main`). If it fails, explain the
   error plainly and fix it with the student. Never force-push.
5. **Deliverable tag (only when a milestone is done):** ask if this checkpoint completes a deliverable.
   If yes, tag it and push the tag: `git tag <name>` then `git push origin <name>`. Tag names:
   `proposal`, `plan-design-prototype`, `final`. If the tag exists already, ask before moving it
   (`git tag -f <name>` + `git push origin <name> --force` is the ONLY allowed force, and only for tags).
6. Print the repo URL and say: "Saved ✅. Type `/clear` to start a fresh chat for your next task."
