@echo off
echo ========================================
echo    Framework Sprint 2 - Build Script
echo ========================================

set APP_NAME=framework-sprint2-test
set SRC_DIR=src\main\java
set WEB_DIR=src\main\webapp
set BUILD_DIR=build
set LIB_DIR=lib
set CLASSES_DIR=%BUILD_DIR%\WEB-INF\classes
set FRAMEWORK_JAR=jframework-sprint2.jar

REM Nettoyage des anciens builds
echo [1/6] Nettoyage des anciens builds...
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
if exist %FRAMEWORK_JAR% del %FRAMEWORK_JAR%

REM Création des répertoires de build
echo [2/6] Création des répertoires de build...
mkdir %CLASSES_DIR%
mkdir %BUILD_DIR%\WEB-INF\lib

REM Copie des bibliothèques
echo [3/6] Copie des bibliothèques...
xcopy "%LIB_DIR%\*" "%BUILD_DIR%\WEB-INF\lib\" /Y

REM Compilation des sources Java
echo [4/6] Compilation des sources Java...
dir /s /b %SRC_DIR%\*.java > sources.txt

REM Vérification de l'existence de javac
javac -version >nul 2>&1
if errorlevel 1 (
    echo ERREUR: javac n'est pas trouvé dans le PATH
    echo Veuillez installer le JDK et configurer la variable PATH
    pause
    exit /b 1
)

REM Compilation avec toutes les dépendances
for /f "usebackq delims=" %%f in ("sources.txt") do (
    javac -cp "%LIB_DIR%\*" -d "%CLASSES_DIR%" "%%f"
    if errorlevel 1 (
        echo ERREUR: Échec de la compilation de %%f
        pause
        exit /b 1
    )
)

REM Nettoyage du fichier temporaire
del sources.txt

REM Création du JAR du framework
echo [5/6] Création du JAR du framework...
cd %CLASSES_DIR%
jar -cf ..\..\%FRAMEWORK_JAR% jframework\*
cd ..\..

REM Copie des fichiers web
echo [6/6] Copie des fichiers web et création du WAR...
xcopy %WEB_DIR% %BUILD_DIR% /E /I /Y

REM Création du fichier WAR
cd %BUILD_DIR%
jar -cvf %APP_NAME%.war *
cd ..

echo.
echo ========================================
echo           BUILD TERMINÉ AVEC SUCCÈS!
echo ========================================
echo.
echo Fichiers générés:
echo - %FRAMEWORK_JAR% (Framework JAR)
echo - %BUILD_DIR%\%APP_NAME%.war (Application WAR)
echo.
echo Pour déployer sur Tomcat:
echo 1. Copiez %BUILD_DIR%\%APP_NAME%.war vers le répertoire webapps de Tomcat
echo 2. Démarrez Tomcat
echo 3. Accédez à http://localhost:8080/%APP_NAME%
echo.
pause