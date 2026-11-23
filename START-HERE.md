# 👋 Start Here — CSC110 Final Project Starter Packet

This folder is the starting point for your final project. It already has:
- **`CLAUDE.md`**: instructions Claude reads automatically, so it knows you're a CSC110 student, which
  Java to use, and how to teach you as it codes.
- **Skills** (`.claude/skills/`): slash commands that walk you and Claude through each step of the project.
- **Templates** (`docs/`): the documents you'll turn in. The skills fill them in with you.
- **Scripts**: `run.bat` / `run.sh` (run your app) and `test.bat` / `test.sh` (run your tests).

## First time (do these in order)
1. Follow the **Claude Code Setup Guide** on Canvas (you install Claude Code; Claude installs Java, Git, and the GitHub CLI).
2. Open a terminal in this folder (the one with `START-HERE.md`).
3. Type `claude` and press Enter.
4. Type **`/setup-repo`** (or just "setup repo"). Claude checks your tools and puts your project on GitHub.
5. Type **`/proposal`** to pick your project.

## The workflow
```
/setup-repo → /proposal → /project-plan → /class-design → /ui-prototype
      → repeat: /build-feature (or /fix-bug) → /checkpoint → /clear
      → /final-writeup (concept map + write-up) → /demo-prep
Anytime: /grill-me (stuck on a decision) · /explain-it (understand code) · /coach (how to use AI well, what next?)
```

## Three habits that make this go well
1. **One task per chat.** When a task is done, run `/checkpoint` (saves + uploads your work), then `/clear`
   (fresh chat). You'll get a reminder. Fresh chats are faster, smarter, and use less of your Claude limit.
2. **Never keep code you can't explain.** Ask "explain this" or run `/explain-it`. You'll be asked about your
   code in the demo.
3. **Steer, don't just accept.** If Claude's idea is too complicated, wrong, or not what you want, say so.
   Those moments go in your steering log and earn you points.

## Folder map
```
src/        your Java code (Main.java is the start)
test/       JUnit tests
data/       saved files + sample data for your instructor
docs/       your deliverables (proposal, plan, design, prototype, write-up...)
lib/        JUnit (don't touch)
.claude/    Claude's skills and settings (don't touch unless you know why)
```
