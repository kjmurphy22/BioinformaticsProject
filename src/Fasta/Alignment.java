package Fasta;

import java.util.HashMap;

import static java.lang.Integer.MAX_VALUE;

public abstract class Alignment {

    protected HashMap<String, String> genomes;
    protected int score = MAX_VALUE;

    public Alignment(HashMap<String, String> genomes){
        this.genomes = genomes;
    }

    public void setGenomes(HashMap<String, String> genomes) {
        this.genomes = genomes;
    }

    public HashMap<String, String> getGenomes() {
        return genomes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void computeAlignmentScore(String referenceGenome){
        System.out.println("Calculating alignment score using " + referenceGenome + " as reference...");
        int score = 0;
        String referenceSequence = getGenomes().get(referenceGenome);
        char[] referenceNucleotides = referenceSequence.toCharArray();
        for (String s : getGenomes().keySet()){
            String sequence = getGenomes().get(s);
            char[] nucleotides = sequence.toCharArray();
            for (int i = 0; i < referenceSequence.length(); i++){
                if (referenceNucleotides[i] != nucleotides[i]){
                    score++;
                }
            }
        }
        System.out.println("Alignment score is " + score + ".");
        setScore(score);
    }
}
