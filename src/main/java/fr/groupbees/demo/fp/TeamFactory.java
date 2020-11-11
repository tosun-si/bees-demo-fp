package fr.groupbees.demo.fp;

import fr.groupbees.demo.fp.pojo.team.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamFactory {

    private final TeamType type;
    private final Map<TeamType, Supplier<Team>> teams;

    public static TeamFactory fromType(final TeamType type) {
        return new TeamFactory(type, new HashMap<>());
    }

    public TeamFactory register(final TeamType type, final Supplier<Team> team) {
        teams.put(type, team);

        return this;
    }

    public Team get() {
        return teams.getOrDefault(type, () -> {
            throw new IllegalStateException("Error !!!!");
        }).get();
    }

    public static void main(String[] args) {
        var type = TeamType.REAL;

        final Team team = TeamFactory.fromType(type)
                .register(TeamType.PSG, () -> new Psg("PSG", "Revons plus grand"))
                .register(TeamType.REAL, () -> new Real("Real", "Vamos"))
                .register(TeamType.BAYERN, () -> new Bayern("Real", "Vamos"))
                .register(TeamType.BARCA, () -> new Barca("Real", "Vamos"))
                .register(TeamType.MANCHESTER, () -> new Manchester("Real", "Vamos"))
                .register(TeamType.CHELSEA, () -> new Chelsea("Real", "Vamos"))
                .register(TeamType.CITY, () -> new City("Real", "Vamos"))
                .register(TeamType.DORTMUND, () -> new Dortmund("Real", "Vamos"))
                .get();
    }
}
