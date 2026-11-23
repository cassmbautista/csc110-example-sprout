---
name: fix-bug
description: Help the student find and fix a bug, compile error, crash, or wrong behavior, and teach them how to read the error as you go. Use when they paste an error, say "it's broken", "doesn't work", "crash", "exception", or "wrong answer".
---

# Fix Bug

The goal is a fix **and** a student who understands what went wrong.

1. **Get the facts.** Ask for (only what's missing): the exact error text or what they saw, what they
   expected, and the steps that cause it. Compile errors: run `bash test.sh` yourself to see them.
2. **Teach the error.** Translate it into plain English, and point at the line number and what it means
   (e.g. "`NullPointerException` at `Trip.java:42` means we used an object variable that was never
   given an object").
3. **Find the cause before changing code.** Read the relevant method(s) only. State your hypothesis in
   one sentence. If you're not sure, add a temporary `System.out.println` or write one failing test that
   reproduces it. Don't guess-and-edit repeatedly.
4. **Minimal fix.** Change as little as possible. If it was a logic bug in a model rule, keep the
   reproducing test as a regression test (just 1). Remove temporary prints.
5. **Verify.** `bash test.sh`; ask the student to retry the steps in the app.
6. **Explain:** cause → fix → how to spot this kind of bug next time (one line).

If a bug reveals a design flaw, say so and suggest `/grill-me` on that decision instead of patching around it.
