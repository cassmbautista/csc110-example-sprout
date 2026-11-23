---
name: setup-repo
description: First-time setup. Checks that Java, Git, and the GitHub CLI are installed and logged in, then turns this folder into a Git repository and publishes it as a PUBLIC GitHub repo. Use when the student says "setup repo", "set up github", "connect to github", or is starting the project.
---

# Setup Repo

The student has probably never used Git. Go one step at a time, explain each step in one sentence, and
check that each step worked before moving on. Detect the OS first (`uname -s`; on Windows you are in Git Bash).

## Step 1 — Check the tools
Run these and report a ✅/❌ checklist:
- `java -version` and `javac -version` (need 21 or newer)
- `git --version`
- `gh --version`
- `gh auth status`

For anything missing, give the exact install step from the guide and stop until they're done:
| Missing | Windows | Mac |
|---|---|---|
| Java JDK | `winget install EclipseAdoptium.Temurin.21.JDK` (then close & reopen the terminal and Claude) | Download the **macOS .pkg** for JDK 21 from https://adoptium.net and run it |
| Git | `winget install --id Git.Git -e` | `xcode-select --install` |
| GitHub CLI | `winget install --id GitHub.cli -e` | Download the macOS installer from https://cli.github.com |

You may run the `winget` commands for them if they say yes. After installing, they must restart the
terminal and Claude Code (`/exit`, then `claude`) so the new commands are found.

## Step 2 — Log in to GitHub (the student does this part)
If `gh auth status` says not logged in, tell them:
> Open a **second** terminal window (leave Claude running), and type: `gh auth login`
> Choose: **GitHub.com** → **HTTPS** → **Yes** (authenticate Git) → **Login with a web browser**.
> Copy the code it shows, press Enter, paste the code in the browser, and approve.
> Come back here and say "done".

No GitHub account? Tell them to sign up at https://github.com first (use a username they'd be OK
showing an employer).

Then re-run `gh auth status` to confirm.

## Step 3 — Set their Git identity (privately)
Check `git config --global user.name`. If empty, ask for the name they want on their commits. For email, use
their GitHub no-reply address so their real email isn't public:
`gh api user --jq '"\(.id)+\(.login)@users.noreply.github.com"'`
Then set both with `git config --global user.name "..."` and `git config --global user.email "..."`.

## Step 4 — Create the local repo
- If `.git` already exists, skip `git init` and say so.
- Otherwise `git init -b main`.
- Make sure `.gitignore` exists (it ships with the packet).
- `git add .` then `git commit -m "Start project from CSC110 starter packet"`.
Explain: "A commit is a saved snapshot you can always return to."

## Step 5 — Publish to GitHub
Ask what to name the repo. Recommend `csc110-final-project` (lowercase, dashes, no spaces).
Run: `gh repo create <name> --public --source=. --remote=origin --push`
The repo must be **public** (the instructor grades it). If the name is taken, suggest `<name>-<username>`.

## Step 6 — Confirm and teach the loop
- Run `gh repo view --web` (opens it in the browser), or print the URL from `gh repo view --json url`.
- Tell them to paste that URL into `docs/01-proposal.md` under "Repo link" (offer to do it).
- Teach the 3-command loop they'll use (or let `/checkpoint` do it for them):
  `git add .` → `git commit -m "what changed"` → `git push`
- Run `bash run.sh` together: a window saying "Setup works!" should appear. Ask them to confirm.

Finish with: "Setup done ✅. Submit your repo link on Canvas. Next: type `/clear` (fresh chat), then `/proposal`."
