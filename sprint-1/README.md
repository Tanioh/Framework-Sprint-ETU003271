# Sprint 1 - Mini Framework Java

## Objectif
Créer un mini-framework Java basé sur les servlets qui capture toutes les requêtes URL et affiche l'URL demandée.

## Structure du projet

```
sprint-1/
├── src/
│   └── framework/
│       └── FrontServlet.java
├── lib/
│   └── servlet-api.jar
├── test-webapp/
│   └── WEB-INF/
│       └── web.xml
├── build.sh
└── README.md
```

## Fonctionnalités implémentées

### 1. FrontServlet
- Servlet principal qui intercepte toutes les requêtes
- Affiche l'URL demandée dans une page HTML simple
- Utilise Jakarta Servlet API

### 2. Configuration
- Mapping de toutes les URLs vers le FrontServlet
- Configuration web.xml pour le déploiement

### 3. Build System
- Script de compilation automatisé
- Génération de JAR pour le framework

## Utilisation

1. Compiler le framework :
```bash
./build.sh
```

2. Déployer dans un serveur de servlet compatible Jakarta EE

## Prochaines étapes (Sprint 2)
- Ajout du système de routage
- Gestion des contrôleurs
- Système d'annotations