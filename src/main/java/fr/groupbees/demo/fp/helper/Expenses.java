package fr.groupbees.demo.fp.helper;

public class Expenses {

    public static double getTransportExpenses(final double turnover) {
        return turnover - 2400;
    }

    public static double getOperatingExpenses(final double turnover) {
        return turnover - 15000;
    }

    public static double getDeductibleTaxes(final double turnover) {
        return turnover - 3000;
    }

    public static double getRemuneration(final double turnover) {
        return turnover - 45000;
    }

    public static double getExceptionalExpenses(final double turnover) {
        return turnover - 2000;
    }
}
