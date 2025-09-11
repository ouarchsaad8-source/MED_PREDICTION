@echo off
echo Testing MedPrediction Backend...
echo.

REM Check if Maven is available
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven not found in PATH. Please install Maven or add it to your PATH.
    pause
    exit /b 1
)

echo Cleaning and compiling...
mvn clean compile
if %errorlevel% neq 0 (
    echo Compilation failed. Check errors above.
    pause
    exit /b 1
)

echo.
echo Compilation successful!
echo Starting backend server...
echo Backend will be available at: http://localhost:8080/api
echo.

mvn spring-boot:run
