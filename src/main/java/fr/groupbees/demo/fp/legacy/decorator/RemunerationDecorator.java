package fr.groupbees.demo.fp.legacy.decorator;


import fr.groupbees.demo.fp.helper.Expenses;

public class RemunerationDecorator extends AbstractProfitDecorator {

    public RemunerationDecorator(ProfitCalculator profitCalculator) {
        super(profitCalculator);
    }

    @Override
    protected double applyExpense(double turnover) {
        return Expenses.getRemuneration(turnover);
    }
}
