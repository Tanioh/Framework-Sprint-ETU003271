#!/bin/bash

# Script de build pour le Mini Framework - Sprint 1
# Auteur: Framework Sprint 1
# Version: 1.0

echo "=== Build du Mini Framework - Sprint 1 ==="

# Configuration
FRAMEWORK_JAR="mini-framework-sprint1.jar"
WEBAPP_WAR="test-webapp.war"
CLASSES_DIR="classes"
LIB_DIR="lib"
SRC_DIR="src"
WEBAPP_DIR="test-webapp"

# Nettoyage des anciens builds
echo "🧹 Nettoyage des anciens builds..."
rm -rf $CLASSES_DIR
rm -f $FRAMEWORK_JAR
rm -f $WEBAPP_WAR

# Création du répertoire classes
mkdir -p $CLASSES_DIR

# Compilation des sources Java
echo "🔨 Compilation des sources Java..."
if [ -f "$LIB_DIR/servlet-api.jar" ]; then
    javac -cp "$LIB_DIR/servlet-api.jar" -d $CLASSES_DIR $SRC_DIR/framework/*.java
    
    if [ $? -eq 0 ]; then
        echo "✅ Compilation réussie"
    else
        echo "❌ Erreur de compilation"
        exit 1
    fi
else
    echo "❌ Erreur: servlet-api.jar non trouvé dans $LIB_DIR/"
    exit 1
fi

# Création du JAR du framework
echo "📦 Création du JAR du framework..."
jar cf $FRAMEWORK_JAR -C $CLASSES_DIR .
echo "✅ JAR créé: $FRAMEWORK_JAR"

# Création du WAR de test (optionnel)
if [ -d "$WEBAPP_DIR" ]; then
    echo "📦 Création du WAR de test..."
    
    # Copie du JAR dans WEB-INF/lib
    mkdir -p $WEBAPP_DIR/WEB-INF/lib
    cp $FRAMEWORK_JAR $WEBAPP_DIR/WEB-INF/lib/
    
    # Création du WAR
    cd $WEBAPP_DIR
    jar cf ../$WEBAPP_WAR .
    cd ..
    
    echo "✅ WAR créé: $WEBAPP_WAR"
fi

echo ""
echo "🎉 Build terminé avec succès!"
echo "📁 Fichiers générés:"
echo "   - $FRAMEWORK_JAR (Framework)"
if [ -f "$WEBAPP_WAR" ]; then
    echo "   - $WEBAPP_WAR (Application de test)"
fi
echo ""
echo "🚀 Pour déployer: copiez $WEBAPP_WAR dans votre serveur d'applications"