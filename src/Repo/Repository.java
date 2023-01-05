package Repo;

import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;
import Fasta.StandardAlignment;

import java.util.HashMap;

public class Repository {

    OptimalAlignment optimalAlignment;
    SNPAlignment SNPAlignment;
    HashMap<String, StandardAlignment> userAlignments;

    public Repository(OptimalAlignment optimalAlignment, SNPAlignment SNPAlignment, HashMap<String, StandardAlignment> userAlignments){
        System.out.println("Creating repository...");
        this.optimalAlignment = optimalAlignment;
        this.SNPAlignment = SNPAlignment;
        this.userAlignments = userAlignments;
        System.out.println("Repository created.\n");
    }

    public Repository(Repository repository) {
        this.optimalAlignment = repository.getOptimalAlignment();
        this.SNPAlignment = repository.getSNPAlignment();
        this.userAlignments = repository.getUserAlignments();
    }

    public void setOptimalAlignment(OptimalAlignment optimalAlignment) {
        this.optimalAlignment = optimalAlignment;
    }

    public OptimalAlignment getOptimalAlignment() {
        return optimalAlignment;
    }

    public void setSNPAlignment(SNPAlignment SNPAlignment) {
        this.SNPAlignment = SNPAlignment;
    }

    public SNPAlignment getSNPAlignment() {
        return SNPAlignment;
    }

    public void setUserAlignments(HashMap<String, StandardAlignment> userAlignments) {
        this.userAlignments = userAlignments;
    }

    public HashMap<String, StandardAlignment> getUserAlignments() {
        return userAlignments;
    }
}
