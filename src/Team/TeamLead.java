package Team;

import Fasta.StandardAlignment;
import Interfaces.Controls;

public class TeamLead extends TeamMember implements Controls {

    public  TeamLead(String name, int years){
        super(RoleType.TEAMLEAD, name, years);
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
