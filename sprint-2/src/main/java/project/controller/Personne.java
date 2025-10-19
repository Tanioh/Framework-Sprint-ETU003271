package project.controller;

import jframework.annotation.Url;

/**
 * Contrôleur de démonstration pour le Sprint 2
 * Utilise l'annotation @Url pour mapper les méthodes aux URLs
 */
public class Personne {
    
    /**
     * Méthode mappée à l'URL /oui
     * Affiche les informations d'une personne
     */
    @Url("/oui")
    public void afficherInfos() {
        System.out.println("Méthode afficherInfos() appelée pour l'URL /oui");
        // Cette méthode sera appelée quand l'URL /oui est demandée
    }

    /**
     * Méthode mappée à l'URL /personne
     * Affiche les détails d'une personne
     */
    @Url("/personne")
    public void afficherPersonne() {
        System.out.println("Méthode afficherPersonne() appelée pour l'URL /personne");
        // Cette méthode sera appelée quand l'URL /personne est demandée
    }

    /**
     * Méthode mappée à l'URL /info
     * Affiche des informations générales
     */
    @Url("/info")
    public void afficherInformationsGenerales() {
        System.out.println("Méthode afficherInformationsGenerales() appelée pour l'URL /info");
        // Cette méthode sera appelée quand l'URL /info est demandée
    }

    /**
     * Constructeur par défaut
     */
    public Personne() {
        System.out.println("Instance de Personne créée");
    }
}