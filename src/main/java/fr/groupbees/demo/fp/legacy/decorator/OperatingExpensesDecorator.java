package fr.groupbees.demo.fp.legacy.decorator;

import fr.groupbees.demo.fp.helper.Expenses;

public class OperatingExpensesDecorator extends AbstractProfitDecorator {

    public OperatingExpensesDecorator(ProfitCalculator profitCalculator) {
        super(profitCalculator);
    }

    @Override
    protected double applyExpense(double turnover) {
        return Expenses.getOperatingExpenses(turnover);
    }
}
