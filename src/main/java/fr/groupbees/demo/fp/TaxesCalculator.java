package fr.groupbees.demo.fp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

interface TaxData {
}

record TaxData1(
        double income,
        double deductions
) implements TaxData {
}

record TaxData2(
        double revenue,
        double expenses,
        double taxRate
) implements TaxData {
}

interface TaxesHelper {
    static double calculateTax1(final TaxData1 data) {
        var taxableIncome = data.income() - data.deductions();
        return taxableIncome * 0.3;
    }

    static double calculateTax2(final TaxData2 data) {
        var profit = data.revenue() - data.expenses();
        return profit * data.taxRate();
    }
}

public class TaxesCalculator {
    final Map<String, Double> taxCalculations;

    private TaxesCalculator(Map<String, Double> taxCalculations) {
        this.taxCalculations = taxCalculations;
    }

    public static TaxesCalculator of() {
        return new TaxesCalculator(new HashMap<>());
    }

    public <T extends TaxData> TaxesCalculator calculate(String calculationType,
                                                         Supplier<T> calculationData,
                                                         Function<T, Double> taxCalculation) {
        taxCalculations.put(calculationType, taxCalculation.apply(calculationData.get()));

        return this;
    }

    public Map<String, Double> get() {
        return taxCalculations;
    }

    public static void main(String[] args) {
        var result = TaxesCalculator.of()
                .calculate("Tax1", () -> new TaxData1(100000, 20000), TaxesHelper::calculateTax1)
                .calculate("Tax2", () -> new TaxData2(500000, 300000, 0.25), TaxesHelper::calculateTax2)
                .calculate("FakeTax1", () -> new TaxData1(100, 20), t -> t.income() + 5)
                .get();

        System.out.println(result);
    }
}
