package fr.groupbees.demo.fp.pojo;

public record Person(
        String firstName,
        String lastName,
        int age,
        Address address) {

    public Person(String firstName, String lastName, int age) {
        this(firstName, lastName, age, null);
    }
}
