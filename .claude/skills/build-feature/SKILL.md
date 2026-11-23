---
name: build-feature
description: Build the next feature from the project plan (or one the student names) in small steps. Writes a few focused tests first for model rules, then implements the model and wires the Swing UI, then explains the code. Use for "build", "add feature", "next feature", "implement", "make X work".
---

# Build Feature

One feature per chat. Keep it small enough to finish in one sitting.

## 1. Pick and confirm (cheap)
- Read `docs/02-project-plan.md` (build order + checkboxes) and `docs/03-class-design.md`.
  Only open the Java files this feature touches.
- If the student didn't name a feature, pick the next unchecked **Must** item.
- Reply with a **mini-plan** of 2–4 lines: what the feature does, the files you'll touch, and the rule(s)
  you'll test. Ask at most 2 questions if a rule is ambiguous. Wait for "go".

## 2. Test first, for model rules only
If the feature has a rule (validation, calculation, state change, save/load):
- Write **1–3** small JUnit 5 tests in `test/<ModelClass>Test.java` that describe the rule in plain names,
  e.g. `rejectsNegativeDistance()`, `correctingScoreDoesNotDoubleCount()`.
- Run `bash test.sh` and show they fail for the right reason. This proves the test checks something real.
- Skip tests for getters/setters, Swing, and trivial code. **Never** write big test suites. The student's
  usage limit matters more than coverage.

## 3. Implement
- Model/manager first (follow the class design and `CLAUDE.md` code style), then wire the UI. If there is no
  Swing window yet, build a simple one that matches `docs/prototype/index.html` (same screens and labels). Listeners
  call manager methods, show results, and show errors in plain language (e.g. a status label or `JOptionPane`).
- Run `bash test.sh` until green. If something fails twice, stop and explain what you think is wrong
  instead of thrashing.

## 4. Check with the student
- Ask them to run the app (`bash run.sh` / `run.bat`) and do the matching steps from the manual test checklist.
- If the design changed, update `docs/03-class-design.md` (keep it true) and add a `docs/decisions.md`
  entry if it's a "why?" kind of change.
- Tick the feature's checkbox in `docs/02-project-plan.md`.

## 5. Explain (required, keep it short)
**What I changed** · **CSC110 concepts used** (point to class/method names) · **Beyond CSC110 bits** ·
**Try it** · **Check yourself**: one question about the new code. Wait for their answer and give feedback.

End: "Feature done. Run `/checkpoint`, then `/clear` before the next feature."
