package Fasta;

import java.util.HashMap;

public class StandardAlignment extends Alignment{

    protected String user;

    public StandardAlignment(OptimalAlignment optimal, String user){
        super(optimal);
        this.user = user;
    }

    public StandardAlignment(HashMap<String, String> genomes, String referenceGenome, String user){
        super(genomes, referenceGenome);
        this.user = user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}
