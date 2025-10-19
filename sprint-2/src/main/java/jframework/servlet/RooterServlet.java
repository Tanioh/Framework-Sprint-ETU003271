package jframework.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jframework.annotation.Url;

/**
 * Servlet principal du framework qui gère le routage des URLs
 * Utilise les annotations @Url pour mapper les méthodes aux URLs
 */
public class RooterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Method> urlMappings = new HashMap<>();
    private Map<String, Object> controllerInstances = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        // Scanner les classes pour les annotations @Url
        scanForUrlMappings();
    }

    /**
     * Scanne les classes pour trouver les méthodes annotées avec @Url
     */
    private void scanForUrlMappings() {
        try {
            // Pour l'instant, on scanne manuellement les classes connues
            // Dans une version plus avancée, on pourrait utiliser la réflexion pour scanner automatiquement
            scanClass("project.controller.Personne");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Scanne une classe spécifique pour les annotations @Url
     */
    private void scanClass(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Url.class)) {
                    Url urlAnnotation = method.getAnnotation(Url.class);
                    String url = urlAnnotation.value();
                    urlMappings.put(url, method);
                    controllerInstances.put(url, instance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Traite les requêtes HTTP en cherchant la méthode correspondante
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = requestURI.substring(contextPath.length());

        try (PrintWriter out = response.getWriter()) {
            // Chercher une méthode mappée pour cette URL
            Method method = urlMappings.get(path);
            Object controller = controllerInstances.get(path);

            if (method != null && controller != null) {
                try {
                    // Appeler la méthode du contrôleur
                    method.invoke(controller);
                    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Framework Sprint 2</title>");
                    out.println("<style>");
                    out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }");
                    out.println(".container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
                    out.println("h1 { color: #2c3e50; border-bottom: 3px solid #3498db; padding-bottom: 10px; }");
                    out.println(".info { background: #e8f4fd; padding: 15px; border-left: 4px solid #3498db; margin: 20px 0; }");
                    out.println(".success { background: #d4edda; padding: 15px; border-left: 4px solid #28a745; margin: 20px 0; }");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='container'>");
                    out.println("<h1>🚀 Framework Sprint 2 - Routage avec Annotations</h1>");
                    out.println("<div class='success'>");
                    out.println("<strong>✅ Méthode exécutée avec succès!</strong>");
                    out.println("</div>");
                    out.println("<div class='info'>");
                    out.println("<strong>URL demandée:</strong> " + path + "<br>");
                    out.println("<strong>Méthode appelée:</strong> " + method.getName() + "<br>");
                    out.println("<strong>Classe contrôleur:</strong> " + controller.getClass().getSimpleName());
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                    
                } catch (Exception e) {
                    out.println("<h1>Erreur lors de l'exécution de la méthode</h1>");
                    out.println("<p>Erreur: " + e.getMessage() + "</p>");
                }
            } else {
                // Aucune méthode trouvée, afficher les informations de base
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Framework Sprint 2</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }");
                out.println(".container { background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
                out.println("h1 { color: #2c3e50; border-bottom: 3px solid #e74c3c; padding-bottom: 10px; }");
                out.println(".info { background: #fff3cd; padding: 15px; border-left: 4px solid #ffc107; margin: 20px 0; }");
                out.println(".mappings { background: #f8f9fa; padding: 15px; border-radius: 5px; margin: 20px 0; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>🔍 Framework Sprint 2 - URL non mappée</h1>");
                out.println("<div class='info'>");
                out.println("<strong>URL demandée:</strong> " + path + "<br>");
                out.println("<strong>Méthode HTTP:</strong> " + request.getMethod() + "<br>");
                out.println("<strong>Contexte:</strong> " + contextPath);
                out.println("</div>");
                out.println("<div class='mappings'>");
                out.println("<h3>URLs disponibles:</h3>");
                if (urlMappings.isEmpty()) {
                    out.println("<p>Aucune URL mappée trouvée.</p>");
                } else {
                    for (String url : urlMappings.keySet()) {
                        Method mappedMethod = urlMappings.get(url);
                        out.println("<p><strong>" + url + "</strong> → " + mappedMethod.getDeclaringClass().getSimpleName() + "." + mappedMethod.getName() + "()</p>");
                    }
                }
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}