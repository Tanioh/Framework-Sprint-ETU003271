# Sprint 3 — TFramework (Jakarta Servlets)

Ce sprint ajoute un routage via annotations et un listener de démarrage.

## Structure

```
sprint-3/
  ├── build.bat
  ├── lib/
  │   ├── servlet-api.jar
  │   ├── mysql-connector-j-9.1.0.jar
  │   └── postgresql-42.6.0.jar
  ├── src/
  │   ├── main/
  │   │   └── java/
  │   │       └── jframework/
  │   │           ├── annotation/
  │   │           ├── qutils/
  │   │           ├── servlet/
  │   │           └── slistener/
  │   └── webapp/
  │       └── WEB-INF/web.xml
  └── build/
```

## Build (Windows)

```
cd sprint-3
build.bat
```

Le JAR `tframework.jar` sera placé dans `sprint-3/lib`.

## Déploiement Tomcat

- Copier le contenu de `src/webapp` vers votre webapp.
- Assurez-vous que `WEB-INF/web.xml` référence le listener et le servlet du framework.
- Déposer `lib/tframework.jar` et les dépendances dans `WEB-INF/lib` de la webapp.

## Notes

- Les packages Java sont `jframework.*` (conformes au code source).
- L’artefact est nommé `tframework.jar` pour ce sprint.