package Fasta;

import Team.Bioinformatician;

import java.util.HashMap;

public class SNPAlignment extends Alignment{

    protected Bioinformatician user;
    public SNPAlignment(HashMap<String, String> genomes, Bioinformatician user) {
        super(genomes);
        this.user = user;
    }

    public void setUser(Bioinformatician user) {
        this.user = user;
    }

    public Bioinformatician getUser() {
        return user;
    }
}
