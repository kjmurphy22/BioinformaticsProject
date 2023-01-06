package Fasta;

import java.util.HashMap;

public abstract class Alignment {

    private HashMap<String, String> genomes;
    private String referenceGenome;
    private int score = 0;

    public Alignment(HashMap<String, String> genomes, String referenceGenome){
        HashMap<String, String> newGenomes = new HashMap<String, String>();
        for (String s : genomes.keySet()){
            newGenomes.put(s, genomes.get(s));
        }
        this.genomes = newGenomes;
        this.referenceGenome = referenceGenome;
    }

    public Alignment(OptimalAlignment optimal){
        HashMap<String, String> newGenomes = new HashMap<String, String>();
        for (String s : optimal.getGenomes().keySet()){
            newGenomes.put(s, optimal.getGenomes().get(s));
        }
        this.genomes = newGenomes;
        this.referenceGenome = optimal.getReferenceGenome();
        this.score = optimal.getScore();
    }

    public void setGenomes(HashMap<String, String> genomes) {
        this.genomes = genomes;
    }

    public HashMap<String, String> getGenomes() {
        return genomes;
    }

    public void setReferenceGenome(String referenceGenome) {
        this.referenceGenome = referenceGenome;
        this.score = computeAlignmentScore(referenceGenome);
    }

    public String getReferenceGenome() {
        return referenceGenome;
    }

    public void setScore() {
        this.score = computeAlignmentScore(getReferenceGenome());
    }

    public void setScore(int n){
        this.score = n;
    }

    public int getScore() {
        return score;
    }

    public int computeAlignmentScore(String referenceGenome){
        System.out.println("Calculating alignment score using " + referenceGenome + " as reference...");
        int score = 0;
        String referenceSequence = getGenomes().get(referenceGenome);
        char[] referenceNucleotides = referenceSequence.toCharArray();
        for (String s : getGenomes().keySet()){
            String sequence = getGenomes().get(s);
            char[] nucleotides = sequence.toCharArray();
            if (referenceSequence.length() > sequence.length()) {
                for (int i = 0; i < sequence.length(); i++) {
                    if (referenceNucleotides[i] != nucleotides[i]) {
                        score++;
                    }
                }
            } else {
                for (int i = 0; i < referenceSequence.length(); i++){
                    if (referenceNucleotides[i] != nucleotides[i]){
                        score++;
                    }
                }
            }
        }
        System.out.println("Alignment score is " + score + ".");
        return score;
    }
}
