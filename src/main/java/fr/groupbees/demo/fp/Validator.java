package fr.groupbees.demo.fp;

import fr.groupbees.demo.fp.pojo.Address;
import fr.groupbees.demo.fp.pojo.Person;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static java.util.function.Predicate.not;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Validator<T> {

    private final T object;
    private final List<String> errorMessages;

    public static <T> Validator<T> of(final T object) {
        requireNonNull(object);
        return new Validator<>(object, new ArrayList<>());
    }

    public <R> Validator<T> validate(final Function<T, R> projection,
                                     final Predicate<R> predicate,
                                     final String message) {

        final Predicate<T> resultPredicate = projection.andThen(predicate::test)::apply;

        Optional.of(object)
                .filter(not(resultPredicate))
                .ifPresent(obj -> errorMessages.add(message));

        return this;
    }

    public <U> Validator<U> thenTo(final Function<T, U> toOtherObject) {
        return new Validator<>(toOtherObject.apply(object), errorMessages);
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public T getOrElseThrow() {
        if (errorMessages.isEmpty()) {
            return object;
        }

        var exception = new IllegalArgumentException();
        errorMessages.forEach(m -> exception.addSuppressed(new IllegalArgumentException(m)));

        throw exception;
    }

    public static void main(String[] args) {
        var address = new Address("", "75150");
        var person = new Person(null, "Ibra", 45, address);

        final Predicate<String> isNonNull = Objects::nonNull;

        final List<String> errorMessage = Validator.of(person)
                .validate(Person::firstName, isNonNull, "The first name should not be null")
                .validate(Person::firstName, isNonNull.and(not(String::isEmpty)), "The first should not be empty")
                .validate(Person::lastName, isNonNull, "The last name should not be null")
                .thenTo(Person::address)
                .validate(Address::street, not(String::isEmpty), "The street should not be empty")
                .getErrorMessages();
    }
}
