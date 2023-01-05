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
    public void promote(Bioinformatician bioinformatician, Repository repository) {
        System.out.println("Promoting " + bioinformatician.getName() + "'s alignment to optimal alignment...");
        OptimalAlignment newOptimalAlignment = new OptimalAlignment(bioinformatician.getAlignment().getGenomes());
        repository.setOptimalAlignment(newOptimalAlignment);
        System.out.println("New optimal alignment set.");
    }

    @Override
    public void overwrite(Bioinformatician bioinformatician, Repository repository) {
        System.out.println("Overwriting " + bioinformatician.getName() + "'s alignment with current optimal alignment...");
        StandardAlignment newAlignment = new StandardAlignment(repository.getOptimalAlignment(), bioinformatician.getName());
        bioinformatician.setAlignment(newAlignment);
    }
}
