@echo off
set APP_NAME=framework
set SRC_DIR=src
set BUILD_DIR=build
set LIB_DIR=lib
set PROJECT_DEPLOY="C:\Users\Mana\Desktop\sprint\Creation-FrameWork-sprint-8-bis"
set "PLACE_JAR=%PROJECT_DEPLOY%\lib"
set "PATH_JAR_PROJECT=%PLACE_JAR%\%APP_NAME%.jar"

REM Suppression et recreation du dossier temporaire
if exist %BUILD_DIR% rmdir /s /q %BUILD_DIR%
if not exist %BUILD_DIR% mkdir %BUILD_DIR%


REM Compilation des fichiers Java avec le JAR des Servlets et autres dependances
dir /s /b %SRC_DIR%\*.java > sources.txt

for /f "usebackq delims=" %%f in ("sources.txt") do (
    javac -cp "%BUILD_DIR%;%LIB_DIR%\*"; -d "%BUILD_DIR%" -parameters "%%f"
)
@REM del sources.txt

REM Copier les fichiers web 

REM Creation du fichier .jar dans le dossier build
jar -cvf %BUILD_DIR%\%APP_NAME%.jar -C %BUILD_DIR% .

REM Deploiement vers Tomcat
if exist %PATH_JAR_PROJECT% del %PATH_JAR_PROJECT%
copy %BUILD_DIR%\%APP_NAME%.jar %PROJECT_DEPLOY%\lib

echo Creation framework termine avec succes.
