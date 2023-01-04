package Repo;

import Fasta.Alignment;
import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;

import java.util.ArrayList;

public class Repository {

    OptimalAlignment optimalAlignment;
    SNPAlignment SNPAlignment;
    ArrayList<Alignment> userAlignments;

    public Repository(OptimalAlignment optimalAlignment,
                      SNPAlignment SNPAlignment,
                      ArrayList<Alignment> userAlignments){
        this.optimalAlignment = optimalAlignment;
        this.SNPAlignment = SNPAlignment;
        this.userAlignments = userAlignments;
    }

    public Repository(Repository repository) {
        this.optimalAlignment = repository.optimalAlignment;
        this.SNPAlignment = repository.SNPAlignment;
        this.userAlignments = repository.userAlignments;
    }
}
