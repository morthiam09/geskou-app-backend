package com.morth.geskou.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

/**
 * Classe représentant l'association entre un produit et ses matières premières.
 * Chaque produit est lié à plusieurs matières premières avec un pourcentage défini.
 */
@Entity
public class PercentageRawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 🔹 Identifiant unique de l'association

    @ManyToOne
    @JoinColumn(name = "raw_material_id", nullable = false) // 🔹 Chaque association doit avoir une matière première
    private RawMaterial rawMaterial;

    @Min(0) @Max(100) // 🔹 S'assure que le pourcentage est toujours entre 0 et 100.
    private double percentage; // 🔹 Pourcentage de la matière première dans le produit

    // 🔹 Constructeur par défaut nécessaire pour JPA
    public PercentageRawMaterial() {
    }

    /**
     * Constructeur permettant d'initialiser l'association entre un produit et une matière première.
     *
     * @param product       Le produit auquel la matière première est associée.
     * @param rawMaterial   La matière première utilisée dans le produit.
     * @param percentage    Le pourcentage de la matière première dans la composition du produit (entre 0 et 100).
     */
    public PercentageRawMaterial( RawMaterial rawMaterial, double percentage) {
        this.rawMaterial = rawMaterial;
        this.setPercentage(percentage); // 🔹 Utilisation du setter pour validation
    }

    // 🔹 Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RawMaterial getRawMaterial() {
        return rawMaterial;
    }

    public void setRawMaterial(RawMaterial rawMaterial) {
        this.rawMaterial = rawMaterial;
    }

    public double getPercentage() {
        return percentage;
    }

    /**
     * Définit le pourcentage de la matière première dans le produit.
     * Vérifie que la valeur est entre 0 et 100 avant de l'attribuer.
     *
     * @param percentage Le pourcentage de la matière première dans la recette du produit.
     */
    public void setPercentage(double percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Le pourcentage doit être entre 0 et 100");
        }
        this.percentage = percentage;
    }

    /**
     * Représentation textuelle de l'objet pour faciliter le débogage.
     *
     * @return Chaîne de caractères contenant les détails du produit et de la matière première associée.
     */
    @Override
    public String toString() {
        return "ProductRawMaterial [id=" + id + ", product=" + 
               ", rawMaterial=" + rawMaterial.getName() + ", percentage=" + percentage + "]";
    }
}
