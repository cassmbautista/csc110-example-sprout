#!/usr/bin/env bash
# Reminds the student to start a fresh chat (/clear) at good stopping points.
#   after-commit : runs after Claude uses Bash; fires when the command was a git commit/push
#   prompt-count : runs on every prompt; nudges when one chat gets long
# Shows a message to the student only (costs no Claude usage). Never blocks anything.

MODE="$1"
INPUT="$(cat)"

say() {
  # Print a JSON message that Claude Code shows to the student.
  printf '{"systemMessage": "%s"}\n' "$1"
}

if [ "$MODE" = "after-commit" ]; then
  if printf '%s' "$INPUT" | grep -Eq '"command" *: *"[^"]*git (commit|push)'; then
    say "Checkpoint saved. If this task is done, type /clear to start a fresh chat for the next task. Fresh chats are faster, smarter, and use less of your Claude Pro limit."
  fi
  exit 0
fi

if [ "$MODE" = "prompt-count" ]; then
  SESSION="$(printf '%s' "$INPUT" | sed -n 's/.*"session_id" *: *"\([^"]*\)".*/\1/p' | head -n 1)"
  [ -z "$SESSION" ] && exit 0
  DIR="${CLAUDE_PROJECT_DIR:-.}/.claude"
  FILE="$DIR/.session-$SESSION"
  COUNT=0
  [ -f "$FILE" ] && COUNT="$(cat "$FILE")"
  COUNT=$((COUNT + 1))
  printf '%s' "$COUNT" > "$FILE" 2>/dev/null
  if [ "$COUNT" -ge 25 ] && [ $((COUNT % 15)) -eq 10 ]; then
    say "This chat has $COUNT messages. Long chats get slower, forget early details, and burn your usage. Finish this step, run /checkpoint, then /clear."
  fi
  exit 0
fi

exit 0
