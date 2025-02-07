package fr.groupbees.demo.fp.legacy.decorator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LegacyDecoratorTest {

    @Test
    public void givenTurnover_whenComposingAllDecorators_thenCorrectResult() {

        // Given.
        final double turnover = 100000;

        // When.
        final double profit = new ExceptionalExpensesDecorator(
                new RemunerationDecorator(
                        new DeductibleTaxesDecorator(
                                new OperatingExpensesDecorator(
                                        new DefaultProfitCalculator())))).calculate(turnover);

        // Then.
        assertThat(profit).isNotNull().isEqualTo(32600);
    }

    @Test
    public void givenTurnover_whenNoComposingAllDecorators_thenCorrectResult() {

        // Given.
        final double turnover = 100000;

        // When.
        final double profit = new RemunerationDecorator(
                new DeductibleTaxesDecorator(new OperatingExpensesDecorator(new DefaultProfitCalculator())))
                .calculate(turnover);

        // Then.
        assertThat(profit).isNotNull().isEqualTo(34600);
    }
}
