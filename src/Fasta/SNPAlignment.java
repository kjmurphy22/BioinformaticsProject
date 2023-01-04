package Fasta;

import java.util.HashMap;

public class SNPAlignment extends Alignment{

    String reference;

    public SNPAlignment(HashMap<String, String> genomes, String reference) {
        super(genomes);
        this.reference = reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }
}
