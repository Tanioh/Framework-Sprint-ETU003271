# Sprint 6 bis

Ce sprint ajoute un routage par annotations et un listener de démarrage.

- Paquets: `framework.annotation`, `framework.hutils`, `framework.qutils`, `framework.servlet`, `framework.slistener`
- Build: `build.bat` compile les classes et produit `build\framework.jar`
- Dépendances: placer les JAR nécessaires dans `sprint-6-bis\lib` (servlet-api, mysql/postgresql si besoin)

Pour compiler:
1. Ouvrir un terminal dans `sprint-6-bis`
2. Exécuter `build.bat`

Notes:
- Le déploiement dans `build.bat` utilise une variable `PROJECT_DEPLOY`; ajustez-la selon votre environnement.
- Le nom `jframework` a été remplacé partout par `framework`.