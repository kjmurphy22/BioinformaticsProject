package Fasta;

import java.util.HashMap;

public abstract class Alignment {

    protected HashMap<String, String> genomes;
    protected int score;

    public Alignment(HashMap<String, String> genomes){
        this.genomes = genomes;
    }

    public void setGenomes(HashMap<String, String> genomes) {
        this.genomes = genomes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public HashMap<String, String> getGenomes() {
        return genomes;
    }

    public void computeAlignmentScore(){
        int score = 123456;
        setScore(score);
    }
}
