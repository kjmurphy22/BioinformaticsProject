package Team;

import Fasta.StandardAlignment;

import java.util.HashMap;

public class Bioinformatician extends TeamMember{

    StandardAlignment alignment = null;

    public Bioinformatician(String name, int years) {
        super(RoleType.BIOINFORMATICIAN, name, years);
    }

    public void setAlignment(StandardAlignment alignment) {
        this.alignment = alignment;
    }

    public HashMap<String, String> getAlignment() {
        return alignment.getGenomes();
    }
}
