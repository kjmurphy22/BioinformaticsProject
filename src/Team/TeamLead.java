package Team;

import Fasta.OptimalAlignment;
import Fasta.StandardAlignment;
import Interfaces.Controls;
import Interfaces.Writable;
import Repo.Repository;

public class TeamLead extends TeamMember implements Writable, Controls {

    public  TeamLead(String name, int years){
        super(RoleType.TEAMLEAD, name, years);
    }

    @Override
    public void writeAlignment() {

    }

    @Override
    public void writeScore() {

    }

    @Override
    public void promote(StandardAlignment alignment, Repository repository) {
        System.out.println("Promoting " + alignment.getUser().getName() + "'s alignment to the optimal alignment.");
        OptimalAlignment newOptimalAlignment = new OptimalAlignment(alignment.getGenomes());
        repository.setOptimalAlignment(newOptimalAlignment);
        System.out.println("New optimal alignment set.");
    }

    @Override
    public void overwrite(Bioinformatician bioinformatician) {

    }
}
