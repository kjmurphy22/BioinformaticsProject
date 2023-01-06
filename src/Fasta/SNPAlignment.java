package Fasta;

import java.util.HashMap;

/*
SNPAlignment is a concrete class that extends Alignment. It has no subclass variables.
*/

public class SNPAlignment extends Alignment{

    /*
    SNPAlignment only has one method: its constructor that calls the super method. This constructor is only called
    by OptimalAlignment objects in the createSNPAlignment method to create new SNPAlignment objects based on the
    OptimalAlignment object that is calling createSNPAlignment.
    */
    public SNPAlignment(HashMap<String, String> genomes, String referenceGenome) {
        super(genomes, referenceGenome);
    }
}
