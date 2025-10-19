@echo off
REM Script de build pour le Mini Framework - Sprint 1 (Windows)
REM Auteur: Framework Sprint 1
REM Version: 1.0

echo === Build du Mini Framework - Sprint 1 ===

REM Configuration
set FRAMEWORK_JAR=mini-framework-sprint1.jar
set WEBAPP_WAR=test-webapp.war
set CLASSES_DIR=classes
set LIB_DIR=lib
set SRC_DIR=src
set WEBAPP_DIR=test-webapp

REM Nettoyage des anciens builds
echo 🧹 Nettoyage des anciens builds...
if exist %CLASSES_DIR% rmdir /s /q %CLASSES_DIR%
if exist %FRAMEWORK_JAR% del %FRAMEWORK_JAR%
if exist %WEBAPP_WAR% del %WEBAPP_WAR%

REM Création du répertoire classes
mkdir %CLASSES_DIR%

REM Compilation des sources Java
echo 🔨 Compilation des sources Java...
if exist "%LIB_DIR%\servlet-api.jar" (
    javac -cp "%LIB_DIR%\servlet-api.jar" -d %CLASSES_DIR% %SRC_DIR%\framework\*.java
    
    if %ERRORLEVEL% equ 0 (
        echo ✅ Compilation réussie
    ) else (
        echo ❌ Erreur de compilation
        exit /b 1
    )
) else (
    echo ❌ Erreur: servlet-api.jar non trouvé dans %LIB_DIR%\
    exit /b 1
)

REM Création du JAR du framework
echo 📦 Création du JAR du framework...
jar cf %FRAMEWORK_JAR% -C %CLASSES_DIR% .
echo ✅ JAR créé: %FRAMEWORK_JAR%

REM Création du WAR de test (optionnel)
if exist "%WEBAPP_DIR%" (
    echo 📦 Création du WAR de test...
    
    REM Copie du JAR dans WEB-INF/lib
    if not exist "%WEBAPP_DIR%\WEB-INF\lib" mkdir "%WEBAPP_DIR%\WEB-INF\lib"
    copy %FRAMEWORK_JAR% "%WEBAPP_DIR%\WEB-INF\lib\"
    
    REM Création du WAR
    cd %WEBAPP_DIR%
    jar cf ..\%WEBAPP_WAR% .
    cd ..
    
    echo ✅ WAR créé: %WEBAPP_WAR%
)

echo.
echo 🎉 Build terminé avec succès!
echo 📁 Fichiers générés:
echo    - %FRAMEWORK_JAR% (Framework)
if exist "%WEBAPP_WAR%" echo    - %WEBAPP_WAR% (Application de test)
echo.
echo 🚀 Pour déployer: copiez %WEBAPP_WAR% dans votre serveur d'applications

pause