package fr.groupbees.demo.fp.legacy;

import fr.groupbees.demo.fp.pojo.Person;
import fr.groupbees.demo.fp.pojo.team.Real;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LegacyPatternGuards {

    private final Person person;

    public static LegacyPatternGuards from(final Person person) {
        return new LegacyPatternGuards(person);
    }

    public String get() {
        if (person.firstName().equals("Zizou")) {
            return "Zidane";
        } else if (person.firstName().equals("Roni")) {
            return "Ronaldinho";
        } else if (person.firstName().equals("Mohamed")) {
            return "Salah";
        } else if (person.firstName().equals("Kilyan")) {
            return "Zidane";
        } else {
            throw new IllegalStateException("Error !!");
        }
    }

    public Real getRealTeam() {
        if (person.firstName().equals("Benzema")) {
            return new Real("Benzema", "Benzema");
        } else if (isStartWithCasemir(person)) {
            return new Real("Casemiro", "Casemiro");
        } else if (person.firstName().equals("Roni")) {
            return new Real("Roni", "Bernabeu");
        } else if (person.firstName().equals("Mohamed")) {
            return new Real("Mohamed", "Mohamed");
        } else if (person.firstName().equals("Ramos")) {
            return new Real("Ramos", "Ramos");
        } else {
            throw new IllegalStateException("Error !!");
        }
    }

    private boolean isStartWithCasemir(final Person person) {
        return person.firstName().startsWith("Benz");
    }
}
