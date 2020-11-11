package fr.groupbees.demo.fp.legacy.decorator;

import fr.groupbees.demo.fp.helper.Expenses;

public class ExceptionalExpensesDecorator extends AbstractProfitDecorator {

    public ExceptionalExpensesDecorator(ProfitCalculator profitCalculator) {
        super(profitCalculator);
    }

    @Override
    protected double applyExpense(double turnover) {
        return Expenses.getExceptionalExpenses(turnover);
    }
}
