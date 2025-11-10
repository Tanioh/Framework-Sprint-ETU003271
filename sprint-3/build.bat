@echo off
set APP_NAME=tframework
set SRC_DIR=src\main\java
set BUILD_DIR=build
set LIB_DIR=lib
set "PROJECT_DEPLOY=%cd%\lib"
set "PLACE_JAR=%PROJECT_DEPLOY%"
set "PATH_JAR_PROJECT=%PLACE_JAR%\%APP_NAME%.jar"

REM Nettoyage du build
if exist "%BUILD_DIR%" rmdir /s /q "%BUILD_DIR%"
mkdir "%BUILD_DIR%"

REM Collecte des sources
dir /s /b "%SRC_DIR%\*.java" > sources.txt

REM Compilation
javac -cp "%LIB_DIR%\*;%BUILD_DIR%" -d "%BUILD_DIR%" @sources.txt
del sources.txt

REM Packaging du JAR
pushd "%BUILD_DIR%"
jar -cvf "%APP_NAME%.jar" *
popd

REM Copie vers lib
if exist "%PATH_JAR_PROJECT%" del "%PATH_JAR_PROJECT%"
copy "%BUILD_DIR%\%APP_NAME%.jar" "%PROJECT_DEPLOY%\"

echo Sprint-3 build termine avec succes.