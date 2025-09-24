package org.example.hardcore.pages.decorators;

import org.example.hardcore.model.Estimate;
import org.example.hardcore.pages.CalculatorActions;
import org.example.hardcore.pages.PricingCalculatorPage;

public abstract class CalculatorDecorator implements CalculatorActions {
    protected final CalculatorActions delegate;

    protected CalculatorDecorator(CalculatorActions delegate) { this.delegate = delegate; }

    @Override public PricingCalculatorPage fill(Estimate e) { return delegate.fill(e); }
    @Override public String getTotalCostText() { return delegate.getTotalCostText(); }
    @Override public PricingCalculatorPage emailEstimate(String email) { return delegate.emailEstimate(email); }
}
