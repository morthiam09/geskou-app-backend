package com.morth.geskou.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.morth.geskou.model.CostCategory;
import com.morth.geskou.model.CostParameter;

@Service
public class CostCalculationService {

    public double calculateCategoryCost(CostCategory category) {
        double total = 0;
        List<CostParameter> params = category.getParameters();

        switch (category.getName()) {
            case "Matières premières":
                total = (getValue(params, "Quantité utilisée") * getValue(params, "Prix unitaire")) +
                        getValue(params, "Frais de transport") +
                        getValue(params, "Pertes");
                break;

            case "Main-d'œuvre":
                total = (getValue(params, "Nombre d'heures") * getValue(params, "Taux horaire")) +
                        getValue(params, "Charges sociales") +
                        getValue(params, "Heures supplémentaires");
                break;

            case "Packaging":
                total = (getValue(params, "Coût des matériaux") + getValue(params, "Coût de production")) *
                        getValue(params, "Volume d'emballage");
                break;

            case "Publicité":
                total = getValue(params, "Coût des campagnes") +
                        getValue(params, "Coût des créatifs") +
                        getValue(params, "Coût des médias");
                break;

            case "Énergie":
                total = getValue(params, "Consommation d'énergie") * getValue(params, "Prix unitaire") +
                        getValue(params, "Coûts fixes");
                break;

            case "Amortissement":
                total = (getValue(params, "Valeur d'acquisition") - getValue(params, "Valeur résiduelle")) /
                        getValue(params, "Durée de vie");
                break;

            case "Frais indirects":
                total = getValue(params, "Loyer") + getValue(params, "Entretien") +
                        getValue(params, "Assurances") + getValue(params, "Services publics") +
                        getValue(params, "Administration");
                break;
        }

        return total;
    }

    private double getValue(List<CostParameter> params, String paramName) {
        return params.stream()
                     .filter(p -> p.getName().equals(paramName))
                     .mapToDouble(CostParameter::getValue)
                     .findFirst()
                     .orElse(0);
    }
}
