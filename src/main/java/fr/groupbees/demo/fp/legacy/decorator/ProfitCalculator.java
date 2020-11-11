package fr.groupbees.demo.fp.legacy.decorator;

public interface ProfitCalculator {

    /**
     * Calculates profit by turnover.
     *
     * @param turnover profit
     * @return result profit
     */
    double calculate(double turnover);
}
