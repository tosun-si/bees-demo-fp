package fr.groupbees.demo.fp;

import fr.groupbees.demo.fp.pojo.Person;
import fr.groupbees.demo.fp.pojo.team.Real;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PatternGuards<T, R> {

    private final T object;
    private final Map<Predicate<T>, Supplier<R>> cases = new LinkedHashMap<>();

    public static <T> PatternGuards<T, ?> from(final T fromObject) {
        return new PatternGuards<>(fromObject);
    }

    public <U> PatternGuards<T, U> to(final Class<U> toObject) {
        return new PatternGuards<>(object);
    }

    public PatternGuards<T, R> with(final Predicate<T> predicateOnObject, final Supplier<R> caseValue) {
        cases.put(predicateOnObject, caseValue);

        return this;
    }

    public PatternGuards<T, R> with(final Predicate<T> predicateOnObject, final Function<T, R> caseFunction) {
        cases.put(predicateOnObject, () -> caseFunction.apply(object));

        return this;
    }

    public R get() {
        return cases.entrySet()
                .stream()
                .filter(e -> e.getKey().test(object))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No true case proposed in the pattern guards"))
                .getValue()
                .get();
    }

    public static <T> Predicate<T> getDefault() {
        return t -> true;
    }

    public static void main(String[] args) {
        var person = new Person("Mohamed", "Mazizou", 45);

        final String playerName = PatternGuards
                .from(person)
                .to(String.class)
                .with(p -> p.firstName().equals("Zizou"), () -> "Zidane")
                .with(p -> p.firstName().equals("Roni"), () -> "Ronaldinho")
                .with(p -> p.firstName().equals("Mohamed"), p -> p.firstName() + " Salah")
                .with(PatternGuards.getDefault(), () -> "")
                .get();

        final Real playerName2 = PatternGuards
                .from(person)
                .to(Real.class)
                .with(p -> p.firstName().equals("Zizou"), () -> new Real("Zizou", "Zizou"))
                .with(p -> p.firstName().equals("Roni"), () -> new Real("Roni", "Roni"))
                .with(PatternGuards::startWithMohamed, () -> new Real("Mohamed", "Mohamed"))
                .get();
    }

    private static boolean startWithMohamed(final Person person) {
        return person.firstName().startsWith("Mohamed");
    }

    private static String test(final int value) {
        return "toto";
    }
}
