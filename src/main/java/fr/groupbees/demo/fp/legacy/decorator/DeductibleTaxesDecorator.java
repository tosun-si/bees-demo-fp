package fr.groupbees.demo.fp.legacy.decorator;

import fr.groupbees.demo.fp.helper.Expenses;

public class DeductibleTaxesDecorator extends AbstractProfitDecorator {

    public DeductibleTaxesDecorator(ProfitCalculator profitCalculator) {
        super(profitCalculator);
    }

    @Override
    protected double applyExpense(double turnover) {
        return Expenses.getDeductibleTaxes(turnover);
    }
}
