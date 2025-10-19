# Framework Sprint 2 - Routage avec Annotations

## 🎯 Objectif du Sprint 2

Le Sprint 2 étend le framework développé dans le Sprint 1 en ajoutant un système de routage basé sur les annotations. Cette évolution permet de mapper automatiquement des URLs à des méthodes Java en utilisant l'annotation `@Url`.

## 🚀 Nouvelles Fonctionnalités

### 1. Annotation @Url
- **Localisation** : `jframework.annotation.Url`
- **Utilisation** : Permet de mapper une méthode à une URL spécifique
- **Exemple** :
```java
@Url("/oui")
public void afficherInfos() {
    // Logique métier
}
```

### 2. RooterServlet Amélioré
- **Localisation** : `jframework.servlet.RooterServlet`
- **Fonctionnalités** :
  - Scan automatique des classes pour les annotations `@Url`
  - Routage intelligent des requêtes vers les bonnes méthodes
  - Interface web moderne avec CSS intégré
  - Gestion des erreurs et affichage des URLs disponibles

### 3. Contrôleur de Démonstration
- **Localisation** : `project.controller.Personne`
- **URLs mappées** :
  - `/oui` → `afficherInfos()`
  - `/personne` → `afficherPersonne()`
  - `/info` → `afficherInformationsGenerales()`

### 4. Page de Test des Annotations
- **Localisation** : `annotation.jsp`
- **Fonctionnalité** : Démontre le scan des annotations et l'appel dynamique des méthodes

## 📁 Structure du Projet

```
sprint-2/
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

## 🔧 Compilation et Déploiement

### Prérequis
- Java Development Kit (JDK) 8 ou supérieur
- Apache Tomcat 10.x
- Variables d'environnement JAVA_HOME et PATH configurées

### Compilation
```bash
# Exécuter le script de build
build.bat
```

Le script génère :
- `jframework-sprint2.jar` : Le framework compilé
- `build/framework-sprint2-test.war` : L'application web de test

### Déploiement sur Tomcat
1. Copiez `build/framework-sprint2-test.war` vers `TOMCAT_HOME/webapps/`
2. Démarrez Tomcat
3. Accédez à `http://localhost:8080/framework-sprint2-test/`

## 🧪 Test du Framework

### URLs de Test
- **Page d'accueil** : `http://localhost:8080/framework-sprint2-test/`
- **Test annotation** : `http://localhost:8080/framework-sprint2-test/annotation.jsp`
- **Route /oui** : `http://localhost:8080/framework-sprint2-test/oui`
- **Route /personne** : `http://localhost:8080/framework-sprint2-test/personne`
- **Route /info** : `http://localhost:8080/framework-sprint2-test/info`

### Comportement Attendu
- Les URLs mappées affichent une page de confirmation avec les détails de la méthode appelée
- Les URLs non mappées affichent la liste des URLs disponibles
- La page `annotation.jsp` montre le scan des annotations en temps réel

## 🔄 Évolution par rapport au Sprint 1

| Aspect | Sprint 1 | Sprint 2 |
|--------|----------|----------|
| **Routage** | Affichage simple de l'URL | Routage intelligent avec annotations |
| **Architecture** | Servlet basique | Framework avec annotations |
| **Extensibilité** | Limitée | Facilement extensible |
| **Configuration** | Manuelle dans web.xml | Automatique via annotations |

## 🛠️ Architecture Technique

### Flux de Traitement
1. **Initialisation** : Le `RooterServlet` scanne les classes au démarrage
2. **Mapping** : Les méthodes annotées sont mappées aux URLs
3. **Requête** : Une requête HTTP arrive
4. **Routage** : Le servlet trouve la méthode correspondante
5. **Exécution** : La méthode est invoquée par réflexion
6. **Réponse** : Une page HTML est générée avec les informations

### Technologies Utilisées
- **Jakarta EE** : Servlets et annotations
- **Réflexion Java** : Scan des classes et invocation des méthodes
- **HTML/CSS** : Interface utilisateur moderne
- **JSP** : Pages dynamiques pour les tests

## 🔮 Prochaines Étapes (Sprint 3)

- Gestion des paramètres de requête
- Support des différentes méthodes HTTP (GET, POST, PUT, DELETE)
- Injection de dépendances
- Gestion des sessions
- Templates et vues
- Validation des données
- Gestion des exceptions

## 📝 Notes de Développement

- Le framework utilise la réflexion pour découvrir et invoquer les méthodes
- Les instances des contrôleurs sont créées automatiquement
- Le système est extensible pour ajouter de nouvelles annotations
- La configuration se fait principalement par annotations, réduisant la configuration XML