<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Framework Sprint 2 - Test des Annotations</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            margin: 40px; 
            background-color: #f5f5f5; 
        }
        .container { 
            background: white; 
            padding: 30px; 
            border-radius: 8px; 
            box-shadow: 0 2px 10px rgba(0,0,0,0.1); 
        }
        h1 { 
            color: #2c3e50; 
            border-bottom: 3px solid #3498db; 
            padding-bottom: 10px; 
        }
        .method-info { 
            background: #e8f4fd; 
            padding: 15px; 
            border-left: 4px solid #3498db; 
            margin: 15px 0; 
            border-radius: 4px;
        }
        .method-name { 
            font-weight: bold; 
            color: #2980b9; 
        }
        .url-mapping { 
            color: #27ae60; 
            font-family: monospace; 
        }
    </style>
</head>
<body>
    <%@ page import="java.lang.reflect.Method" %>
    <%@ page import="project.controller.*" %>
    <%@ page import="jframework.annotation.Url" %>

    <div class="container">
        <h1>🔍 Framework Sprint 2 - Scan des Annotations @Url</h1>
        
        <p>Cette page démontre la capacité du framework à scanner et identifier les méthodes annotées avec @Url.</p>
        
        <h2>Méthodes trouvées dans la classe Personne :</h2>
        
        <%
            try {
                Personne p = new Personne();
                Class<?> clazz = p.getClass();
                boolean foundMethods = false;
                
                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(Url.class)) {
                        foundMethods = true;
                        Url urlAnnotation = method.getAnnotation(Url.class);
                        out.println("<div class='method-info'>");
                        out.println("<div class='method-name'>Méthode : " + method.getName() + "()</div>");
                        out.println("<div class='url-mapping'>URL mappée : " + urlAnnotation.value() + "</div>");
                        out.println("<div>Type de retour : " + method.getReturnType().getSimpleName() + "</div>");
                        out.println("</div>");
                        
                        // Appeler la méthode pour démonstration
                        try {
                            method.invoke(p);
                        } catch (Exception e) {
                            out.println("<p style='color: red;'>Erreur lors de l'appel de la méthode : " + e.getMessage() + "</p>");
                        }
                    }
                }
                
                if (!foundMethods) {
                    out.println("<p style='color: orange;'>Aucune méthode annotée avec @Url trouvée dans la classe Personne.</p>");
                }
                
            } catch (Exception e) {
                out.println("<p style='color: red;'>Erreur lors du scan des annotations : " + e.getMessage() + "</p>");
                e.printStackTrace();
            }
        %>
        
        <h2>Comment tester le routage :</h2>
        <div class="method-info">
            <p>Pour tester le routage automatique, visitez les URLs suivantes :</p>
            <ul>
                <li><a href="oui" target="_blank">/oui</a> - Appelle la méthode afficherInfos()</li>
                <li><a href="personne" target="_blank">/personne</a> - Appelle la méthode afficherPersonne()</li>
                <li><a href="info" target="_blank">/info</a> - Appelle la méthode afficherInformationsGenerales()</li>
            </ul>
        </div>
    </div>
</body>
</html>