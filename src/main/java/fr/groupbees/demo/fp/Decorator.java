package fr.groupbees.demo.fp;

import fr.groupbees.demo.fp.helper.Expenses;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Decorator<T> {

    private final T value;
    private final Function<T, T> function;

    public static <T> Decorator<T> from(final T value) {
        return new Decorator<>(value, Function.identity());
    }

    public Decorator<T> with(final Function<T, T> functionToCompose) {
        return new Decorator<>(value, function.andThen(functionToCompose));
    }

    public T calculate() {
        return function.apply(value);
    }

    public static void main(String[] args) {
        final double turnover = 100000;

        final double profit = Decorator.from(turnover)
                .with(Expenses::getRemuneration)
                .with(Expenses::getDeductibleTaxes)
                .with(Expenses::getOperatingExpenses)
                .with(Expenses::getTransportExpenses)
                .with(Expenses::getExceptionalExpenses)
                .calculate();
    }
}

