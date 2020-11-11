package fr.groupbees.demo.fp.legacy;

import fr.groupbees.demo.fp.pojo.team.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LegacyTeamFactory {

    private final TeamType type;

    public static LegacyTeamFactory fromType(final TeamType type) {
        return new LegacyTeamFactory(type);
    }

    public Team get() {
        if (TeamType.PSG.equals(type)) {
            return new Psg("PSG", "Revons plus grand");
        } else if (TeamType.REAL.equals(type)) {
            return new Real("Real", "Real");
        } else if (TeamType.BAYERN.equals(type)) {
            return new Bayern("Bayern", "Bayern");
        } else if (TeamType.BARCA.equals(type)) {
            return new Barca("Barca", "Barca");
        } else if (TeamType.MANCHESTER.equals(type)) {
            return new Manchester("Manchester", "Old Trafford");
        } else if (TeamType.CHELSEA.equals(type)) {
            return new Chelsea("Chelsea", "Strandford Bridge");
        } else if (TeamType.CITY.equals(type)) {
            return new City("Man city", "Ethiad stadium");
        } else if (TeamType.DORTMUND.equals(type)) {
            return new Dortmund("Dortmund", "Stade");
        } else {
            throw new IllegalStateException("Error !!");
        }
    }
}
