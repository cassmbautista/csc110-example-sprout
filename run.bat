@echo off
REM Compiles everything in src\ and launches the app (Windows). Double-click or run: run.bat
setlocal enabledelayedexpansion
cd /d "%~dp0"
if exist out rmdir /s /q out
mkdir out
(for /r src %%f in (*.java) do (set "p=%%f" & echo "!p:\=/!")) > out\sources.txt
javac -d out @out\sources.txt
if errorlevel 1 (
  echo.
  echo Compile failed. Read the error above, or paste it into Claude with /fix-bug.
  pause
  exit /b 1
)
java -cp out Main
