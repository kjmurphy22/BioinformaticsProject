package Team;

import Fasta.StandardAlignment;
import Interfaces.Controls;
import Interfaces.Writable;

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
    public StandardAlignment promote() {
        return null;
    }

    @Override
    public StandardAlignment overwrite() {
        return null;
    }
}
