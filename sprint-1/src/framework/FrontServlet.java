package framework;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * FrontServlet - Servlet principal du mini-framework
 * 
 * Ce servlet intercepte toutes les requêtes HTTP et affiche
 * l'URL demandée dans une page HTML formatée.
 * 
 * @author Framework Sprint 1
 * @version 1.0
 */
public class FrontServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Traite toutes les requêtes HTTP (GET, POST, etc.)
     * 
     * @param req La requête HTTP
     * @param resp La réponse HTTP
     * @throws ServletException En cas d'erreur de servlet
     * @throws IOException En cas d'erreur d'entrée/sortie
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        // Configuration de la réponse
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        
        // Récupération des informations de la requête
        String uri = req.getRequestURI();
        String method = req.getMethod();
        String contextPath = req.getContextPath();
        
        // Génération de la réponse HTML
        PrintWriter out = resp.getWriter();
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("    <title>Mini Framework - Sprint 1</title>");
        out.println("    <style>");
        out.println("        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f5f5f5; }");
        out.println("        .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }");
        out.println("        h1 { color: #333; border-bottom: 2px solid #007bff; padding-bottom: 10px; }");
        out.println("        .info { background: #e9ecef; padding: 15px; border-radius: 5px; margin: 15px 0; }");
        out.println("        .label { font-weight: bold; color: #495057; }");
        out.println("        .value { color: #007bff; font-family: monospace; }");
        out.println("    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div class='container'>");
        out.println("        <h1>🚀 Mini Framework Java - Sprint 1</h1>");
        out.println("        <p>Bienvenue dans le mini-framework ! Cette page confirme que le FrontServlet fonctionne correctement.</p>");
        out.println("        <div class='info'>");
        out.println("            <p><span class='label'>URL demandée :</span> <span class='value'>" + uri + "</span></p>");
        out.println("            <p><span class='label'>Méthode HTTP :</span> <span class='value'>" + method + "</span></p>");
        out.println("            <p><span class='label'>Context Path :</span> <span class='value'>" + contextPath + "</span></p>");
        out.println("        </div>");
        out.println("        <p><em>Sprint 1 - Capture et affichage des URLs</em></p>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");
    }
}