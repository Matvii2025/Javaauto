package org.example.hardcore.pages.decorators;

import org.example.hardcore.logging.Log;
import org.example.hardcore.model.Estimate;
import org.example.hardcore.pages.CalculatorActions;
import org.example.hardcore.pages.PricingCalculatorPage;

public class LoggingCalculator extends CalculatorDecorator {
    private final Log log;

    public LoggingCalculator(CalculatorActions delegate, Log log) {
        super(delegate);
        this.log = log;
    }

    @Override public PricingCalculatorPage fill(Estimate e) {
        long t = System.currentTimeMillis();
        log.action("[DECORATOR] Filling calculator with %s", e);
        PricingCalculatorPage page = super.fill(e);
        log.info("[DECORATOR] Fill done in %d ms", (System.currentTimeMillis() - t));
        return page;
    }

    @Override public String getTotalCostText() {
        long t = System.currentTimeMillis();
        String text = super.getTotalCostText();
        log.debug("[DECORATOR] Total cost text fetched in %d ms", (System.currentTimeMillis() - t));
        return text;
    }

    @Override public PricingCalculatorPage emailEstimate(String email) {
        log.action("[DECORATOR] Emailing estimate to %s", email);
        return super.emailEstimate(email);
    }
}