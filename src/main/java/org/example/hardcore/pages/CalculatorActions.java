package org.example.hardcore.pages;

import org.example.hardcore.model.Estimate;

public interface CalculatorActions {
    PricingCalculatorPage fill(Estimate e);
    String getTotalCostText();
    PricingCalculatorPage emailEstimate(String email);
}
