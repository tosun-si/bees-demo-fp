package fr.groupbees.demo.fp.legacy.decorator;

import fr.groupbees.demo.fp.helper.Expenses;

public class DefaultProfitCalculator implements ProfitCalculator {

    @Override
    public double calculate(double turnover) {
        return Expenses.getTransportExpenses(turnover);
    }
}
