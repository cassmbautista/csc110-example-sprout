@echo off
REM Compiles src\ and test\ and runs every JUnit test (Windows). Run: test.bat
setlocal enabledelayedexpansion
cd /d "%~dp0"
set "JUNIT=lib\junit-platform-console-standalone-1.11.4.jar"
if exist out rmdir /s /q out
mkdir out
(for /r src %%f in (*.java) do (set "p=%%f" & echo "!p:\=/!")) > out\sources.txt
(for /r test %%f in (*.java) do (set "p=%%f" & echo "!p:\=/!")) >> out\sources.txt
javac -cp "%JUNIT%" -d out @out\sources.txt
if errorlevel 1 (
  echo Compile failed.
  pause
  exit /b 1
)
java -jar "%JUNIT%" execute --class-path out --scan-class-path --disable-banner --details=summary
pause
