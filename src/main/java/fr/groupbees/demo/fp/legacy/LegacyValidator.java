package fr.groupbees.demo.fp.legacy;

import fr.groupbees.demo.fp.pojo.Person;

import static java.util.Objects.nonNull;

public class LegacyValidator {

    public void validate(final Person person) {
        final IllegalArgumentException exception = new IllegalArgumentException();
        if (nonNull(person.firstName())) {
            exception.addSuppressed(new IllegalArgumentException("The first name should not be null"));
        }
        if (nonNull(person.firstName()) && !person.firstName().isEmpty()) {
            exception.addSuppressed(new IllegalArgumentException("The first name should not be empty"));
        }
        if (nonNull(person.lastName())) {
            exception.addSuppressed(new IllegalArgumentException("The last name should not be null"));
        }
        if (nonNull(person.lastName()) && !person.lastName().isEmpty()) {
            exception.addSuppressed(new IllegalArgumentException("The last name should not be empty"));
        }
        if (person.age() != 0) {
            exception.addSuppressed(new IllegalArgumentException("The age should not be equals to 0"));
        }
        if (nonNull(person.address())) {
            if (nonNull(person.address().street())) {
                exception.addSuppressed(new IllegalArgumentException("The address street should not be null"));
            }
            if (nonNull(person.address().street()) && !person.address().street().isEmpty()) {
                exception.addSuppressed(new IllegalArgumentException("The address street should not be empty"));
            }
            if (nonNull(person.address().zipCode())) {
                exception.addSuppressed(new IllegalArgumentException("The address zip code should not be null"));
            }
            if (nonNull(person.address().zipCode()) && !person.address().zipCode().isEmpty()) {
                exception.addSuppressed(new IllegalArgumentException("The address zip code should not be empty"));
            }
        }

        if (exception.getSuppressed().length > 0) {
            throw exception;
        }
    }
}
