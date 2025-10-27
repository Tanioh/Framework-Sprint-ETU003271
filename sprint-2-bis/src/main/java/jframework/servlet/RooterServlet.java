package jframework.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jframework.annotation.Controller;
import jframework.annotation.Url;

/**
 * Servlet principal du framework qui gère le routage des URLs
 * Version améliorée avec auto-découverte des contrôleurs via @Controller
 * et mapping des URLs via @Url
 */
public class RooterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Map<String, Method> urlMappings = new HashMap<>();
    private Map<String, Object> controllerInstances = new HashMap<>();

    @Override
    public void init() throws ServletException {
        super.init();
        // Scanner automatiquement les classes pour les annotations
        scanForControllers();
    }

    /**
     * Scanne automatiquement les classes annotées avec @Controller
     * et leurs méthodes annotées avec @Url
     */
    private void scanForControllers() {
        try {
            // Scanner le package project.controller pour les contrôleurs
            List<Class<?>> controllerClasses = findClassesWithAnnotation("project.controller", Controller.class);
            
            for (Class<?> controllerClass : controllerClasses) {
                scanControllerClass(controllerClass);
            }
            
            // Scanner aussi les classes sans annotation pour compatibilité
            scanClass("project.controller.Personne");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Trouve toutes les classes annotées avec une annotation spécifique dans un package
     */
    public List<Class<?>> findClassesWithAnnotation(String packageName, Class<Controller> annotationClass) throws Exception {
        List<Class<?>> result = new ArrayList<>();
        String path = packageName.replace('.', '/');
        URL packageURL = Thread.currentThread().getContextClassLoader().getResource(path);
        if (packageURL == null) return result;
        
        File folder = new File(packageURL.toURI());
        File[] files = folder.listFiles();
        if (files == null) return result;

        for (File file : files) {
            if (file.isDirectory()) {
                result.addAll(findClassesWithAnnotation(packageName + "." + file.getName(), annotationClass));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");
                try {
                    Class<?> cls = Class.forName(className);
                    if (cls.isAnnotationPresent(annotationClass)) {
                        result.add(cls);
                    }
                } catch (ClassNotFoundException e) {
                    // Ignorer les classes qui ne peuvent pas être chargées
                }
            }
        }
        return result;
    }

    /**
     * Scanne une classe contrôleur pour les méthodes annotées avec @Url
     */
    private void scanControllerClass(Class<?> controllerClass) {
        try {
            Object instance = controllerClass.getDeclaredConstructor().newInstance();
            
            for (Method method : controllerClass.getDeclaredMethods()) {
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

    /**
     * Scanne une classe spécifique pour les annotations @Url (pour compatibilité)
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
                    Object result = method.invoke(controller);
                    
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Framework Sprint 2-bis</title>");
                    out.println("<style>");
                    out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
                    out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; backdrop-filter: blur(10px); }");
                    out.println("h1 { color: #fff; text-shadow: 2px 2px 4px rgba(0,0,0,0.3); }");
                    out.println(".info { background: rgba(255,255,255,0.2); padding: 15px; border-radius: 8px; margin: 10px 0; }");
                    out.println("</style>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<div class='container'>");
                    out.println("<h1>🚀 Framework Sprint 2-bis - Routage Avancé</h1>");
                    out.println("<div class='info'>");
                    out.println("<p><strong>URL mappée :</strong> " + path + "</p>");
                    out.println("<p><strong>Contrôleur :</strong> " + controller.getClass().getSimpleName() + "</p>");
                    out.println("<p><strong>Méthode :</strong> " + method.getName() + "</p>");
                    if (result != null) {
                        out.println("<p><strong>Résultat :</strong> " + result.toString() + "</p>");
                    }
                    out.println("</div>");
                    out.println("<p>✅ Méthode exécutée avec succès via le framework Sprint 2-bis !</p>");
                    out.println("</div>");
                    out.println("</body>");
                    out.println("</html>");
                    
                } catch (Exception e) {
                    out.println("<h2>Erreur lors de l'exécution de la méthode</h2>");
                    out.println("<p>" + e.getMessage() + "</p>");
                }
            } else {
                // Afficher les URLs disponibles
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Framework Sprint 2-bis</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 40px; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); color: white; }");
                out.println(".container { background: rgba(255,255,255,0.1); padding: 30px; border-radius: 15px; backdrop-filter: blur(10px); }");
                out.println("h1 { color: #fff; text-shadow: 2px 2px 4px rgba(0,0,0,0.3); }");
                out.println(".url-list { background: rgba(255,255,255,0.2); padding: 15px; border-radius: 8px; margin: 10px 0; }");
                out.println("a { color: #fff; text-decoration: none; display: block; padding: 5px 0; }");
                out.println("a:hover { text-decoration: underline; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container'>");
                out.println("<h1>🚀 Framework Sprint 2-bis</h1>");
                out.println("<p><strong>URL demandée :</strong> " + path + "</p>");
                out.println("<p>❌ Aucune méthode mappée pour cette URL.</p>");
                out.println("<div class='url-list'>");
                out.println("<h3>URLs disponibles :</h3>");
                for (String url : urlMappings.keySet()) {
                    out.println("<a href='" + contextPath + url + "'>" + url + "</a>");
                }
                if (urlMappings.isEmpty()) {
                    out.println("<p>Aucune URL mappée trouvée.</p>");
                }
                out.println("</div>");
                out.println("</div>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    }
}