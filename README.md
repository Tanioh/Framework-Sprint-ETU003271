# Framework Sprint ETU003271

## Description
Ce projet implémente un mini-framework Java basé sur les servlets Jakarta EE, développé par sprints successifs.

## Structure du projet

```
Framework-Sprint-ETU003271/
├── sprint-1/                    # Sprint 1 - Capture d'URLs
│   ├── src/
│   │   └── framework/
│   │       └── FrontServlet.java
│   ├── lib/
│   │   └── servlet-api.jar
│   ├── test-webapp/
│   │   └── WEB-INF/
│   │       └── web.xml
│   ├── build.sh                 # Script de build Unix/Linux
│   ├── build.bat                # Script de build Windows
│   └── README.md
├── sprint-2/                    # À venir
├── .gitignore
└── README.md
```

## Sprint 1 - Capture et affichage des URLs

### Objectifs
- ✅ Créer un FrontServlet qui intercepte toutes les requêtes
- ✅ Afficher l'URL demandée dans une page HTML formatée
- ✅ Configuration web.xml pour le mapping des servlets
- ✅ Scripts de build automatisés

### Fonctionnalités implémentées
- **FrontServlet** : Servlet principal avec interface HTML moderne
- **Configuration** : Mapping de toutes les URLs vers le FrontServlet
- **Build System** : Scripts de compilation pour Windows et Unix/Linux
- **Structure modulaire** : Préparation pour les sprints suivants

### Utilisation

#### Compilation
```bash
# Unix/Linux/Mac
cd sprint-1
./build.sh

# Windows
cd sprint-1
build.bat
```

#### Déploiement
1. Copier le fichier `test-webapp.war` généré dans votre serveur d'applications
2. Accéder à l'application via votre navigateur
3. Tester différentes URLs pour voir le FrontServlet en action

## Prochains sprints

### Sprint 2 (À venir)
- Système de routage avancé
- Gestion des contrôleurs
- Système d'annotations
- Injection de dépendances

### Sprint 3 (À venir)
- Gestion des vues (templates)
- Système de sessions
- Validation des données

## Prérequis
- Java 11 ou supérieur
- Serveur d'applications compatible Jakarta EE (Tomcat 10+, etc.)
- Maven ou Gradle (optionnel)

## Auteur
ETU003271 - Framework Sprint Project

## Licence
Projet éducatif - Université
