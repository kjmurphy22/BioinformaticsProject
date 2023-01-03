package Fasta;

import java.util.HashMap;

public abstract class Alignment {

    protected HashMap<String, String> genomes;

    public Alignment(HashMap<String, String> genomes){
        this.genomes = genomes;
    }

    public void setGenomes(HashMap<String, String> genomes) {
        this.genomes = genomes;
    }

    public HashMap<String, String> getGenomes() {
        return genomes;
    }

    public int computeAlignmentScore(){
        int score = 123456;
        return score;
    }
}
