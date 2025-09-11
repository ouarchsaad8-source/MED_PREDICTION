@echo off
echo Starting MedPrediction Backend...
echo.

REM Check if Maven is available
where mvn >nul 2>&1
if %errorlevel% neq 0 (
    echo Maven not found in PATH. Please install Maven or add it to your PATH.
    echo You can download Maven from: https://maven.apache.org/download.cgi
    pause
    exit /b 1
)

REM Check if Java is available
where java >nul 2>&1
if %errorlevel% neq 0 (
    echo Java not found in PATH. Please install Java 17+ or add it to your PATH.
    echo You can download Java from: https://adoptium.net/
    pause
    exit /b 1
)

echo Compiling the application...
mvn clean compile
if %errorlevel% neq 0 (
    echo Compilation failed. Please check the error messages above.
    pause
    exit /b 1
)

echo.
echo Starting Spring Boot application...
echo Backend will be available at: http://localhost:8080/api
echo Swagger UI will be available at: http://localhost:8080/swagger-ui.html
echo H2 Console will be available at: http://localhost:8080/h2-console
echo.
echo Press Ctrl+C to stop the server
echo.

mvn spring-boot:run
