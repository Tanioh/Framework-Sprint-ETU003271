# Framework Java - Projet de Développement par Sprints

## 📋 Description du Projet

Ce projet implémente un framework Java léger utilisant Jakarta EE Servlets, développé de manière itérative à travers plusieurs sprints. Chaque sprint ajoute de nouvelles fonctionnalités et améliore l'architecture du framework.

## 🏗️ Structure du Projet

```
framework-project/
├── .gitignore
├── README.md
├── sprint-1/
│   ├── src/
│   │   └── framework/
│   │       └── FrontServlet.java
│   ├── lib/
│   │   └── servlet-api.jar
│   ├── test-webapp/
│   │   └── WEB-INF/
│   │       └── web.xml
│   ├── build.sh
│   ├── build.bat
│   └── README.md
└── sprint-2/
    ├── src/
    │   └── main/
    │       ├── java/
    │       │   ├── jframework/
    │       │   │   ├── annotation/
    │       │   │   │   └── Url.java
    │       │   │   └── servlet/
    │       │   │       └── RooterServlet.java
    │       │   └── project/
    │       │       └── controller/
    │       │           └── Personne.java
    │       └── webapp/
    │           ├── WEB-INF/
    │           │   └── web.xml
    │           └── annotation.jsp
    ├── lib/
    │   ├── jframework.jar
    │   ├── servlet-api.jar
    │   ├── mysql-connector-j-9.1.0.jar
    │   └── postgresql-42.6.0.jar
    ├── build.bat
    └── README.md
```

## 🚀 Sprint 1 - Fondations du Framework

### Objectifs
- Créer un servlet de base pour intercepter les requêtes HTTP
- Mettre en place une structure de projet organisée
- Implémenter un système de build simple
- Créer une interface web moderne

### Fonctionnalités Implémentées
- **FrontServlet** : Servlet principal qui intercepte toutes les requêtes
- **Interface Web Moderne** : Page HTML avec CSS intégré et design responsive
- **Configuration Complète** : Fichier web.xml pour la configuration des servlets
- **Scripts de Build** : Scripts pour compiler et packager le framework (Linux/Windows)

### Utilisation
```bash
# Compilation (Linux/Mac)
cd sprint-1
./build.sh

# Compilation (Windows)
cd sprint-1
build.bat
```

## 🎯 Sprint 2 - Routage avec Annotations

### Objectifs
- Implémenter un système de routage basé sur les annotations
- Créer l'annotation @Url pour mapper les méthodes aux URLs
- Développer un RooterServlet intelligent
- Ajouter la réflexion Java pour l'invocation dynamique des méthodes

### Fonctionnalités Implémentées
- **Annotation @Url** : Permet de mapper une méthode à une URL spécifique
- **RooterServlet Amélioré** : Scan automatique des classes et routage intelligent
- **Contrôleur de Démonstration** : Classe Personne avec plusieurs URLs mappées
- **Page de Test** : Interface JSP pour tester le scan des annotations
- **Support Base de Données** : Connecteurs MySQL et PostgreSQL inclus

### Utilisation
```bash
# Compilation
cd sprint-2
build.bat

# URLs de test après déploiement
http://localhost:8080/framework-sprint2-test/oui
http://localhost:8080/framework-sprint2-test/personne
http://localhost:8080/framework-sprint2-test/info
http://localhost:8080/framework-sprint2-test/annotation.jsp
```

### Évolution par rapport au Sprint 1
| Aspect | Sprint 1 | Sprint 2 |
|--------|----------|----------|
| **Routage** | Affichage simple de l'URL | Routage intelligent avec annotations |
| **Architecture** | Servlet basique | Framework avec annotations |
| **Extensibilité** | Limitée | Facilement extensible |
| **Configuration** | Manuelle dans web.xml | Automatique via annotations |


## 🛠️ Technologies Utilisées
- **Java** : Langage principal
- **Jakarta EE** : Servlets et API web
- **Réflexion Java** : Scan des classes et invocation dynamique
- **HTML/CSS/JSP** : Interface utilisateur
- **MySQL/PostgreSQL** : Support base de données

## 📝 Notes de Développement
- Chaque sprint est développé de manière incrémentale
- La compatibilité ascendante est maintenue entre les sprints
- Le code suit les bonnes pratiques Java et les conventions de nommage
- Documentation complète pour chaque sprint
- Architecture modulaire permettant l'extension facile du framework
