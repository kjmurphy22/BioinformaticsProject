package Fasta;

import java.util.HashMap;

/*
Alignment is an abstract class used to represent a multiple sequence alignment (MSA). It has three private variables.
genomes is a HashMap<String, String> with genome names as keys and genome sequences as values.
referenceGenome is a string storing the current referenceGenome of the MSA. The referenceGenome is used to calculate
the alignment score of an MSA and its corresponding SNP alignment.
score is an int that stores the score of an MSA.
*/

public abstract class Alignment {

    private HashMap<String, String> genomes;
    private String referenceGenome;
    private int score = 0;

    /*
    Alignment has two constructors. This constructor is called by subclasses of Alignment to create a new subclass
    object using a HashMap<String, String> object and a String object.
    */
    public Alignment(HashMap<String, String> genomes, String referenceGenome){
        HashMap<String, String> newGenomes = new HashMap<String, String>();
        for (String s : genomes.keySet()){
            newGenomes.put(s, genomes.get(s));
        }
        this.genomes = newGenomes;
        this.referenceGenome = referenceGenome;
    }

    /*
    Alignment has two constructors. This constructor is only called by the StandardAlignment subclass to create a
    new StandardAlignment object using an OptimalAlignment object.
    */
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

    /*
    The setReferenceGenome method also calls setScore() to compute a new alignment score for the Alignment subclass
    object calling the method, based on the new reference genome.
    */
    public void setReferenceGenome(String referenceGenome) {
        this.referenceGenome = referenceGenome;
        setScore();
    }

    public String getReferenceGenome() {
        return referenceGenome;
    }

    /*
    Alignment has two setScore methods. This method calls the computeAlignmentScore method on the Alignment subclass
    object's referenceGenome variable to calculate the alignment score for the Alignment subclass object calling the
    method.
    */
    public void setScore() {
        this.score = computeAlignmentScore(getReferenceGenome());
    }

    /*
    Alignment has two setScore methods. This method simply sets the score of the Alignment subclass object to an
    int n. This method is called in the createSNPAlignment method to copy the score from the OptimalAlignment object
    that called createSNPAlignment to the new SNPAlignment object being created, bypassing the need to call
    computeAlignmentScore. The implementation of this method therefore implies that OptimalAlignment objects and
    their corresponding SNPAlignment objects have the same score.
    */
    public void setScore(int n){
        this.score = n;
    }

    public int getScore() {
        return score;
    }

    /*
    The computeAlignmentScore method calculates a simple alignment score for an Alignment subclass object. It is only
    called by the setScore() method. The alignment score is calculated by counting the number of nucleotides in each
    sequence in the alignment that are different from the reference genome sequence at each position of the reference
    sequence. If the reference sequence is a different length to sequences in the alignment, only the differences
    along the length of the shorter sequence are counted.
    */
    public int computeAlignmentScore(String referenceGenome){
        System.out.println("Calculating alignment score using " + referenceGenome + " as reference...");
        /* Initialise score as 0 */
        int score = 0;
        /* Get reference genome sequence */
        String referenceSequence = getGenomes().get(referenceGenome);
        /* Create character array where each element is a nucleotide in the reference sequence */
        char[] referenceNucleotides = referenceSequence.toCharArray();
        /* For each genome s in the alignment */
        for (String s : getGenomes().keySet()){
            /* Get the genome sequence corresponding to s to comapre it to the reference sequence */
            String sequence = getGenomes().get(s);
            /*
            Create character array where each element is a nucleotide in the sequence to compare to reference
            nucleotides
            */
            char[] nucleotides = sequence.toCharArray();
            /* Check if reference sequence length is longer then genome s sequence length. */
            if (referenceSequence.length() > sequence.length()) {
                /*
                If the reference sequence is longer, only count differences along the length of genome s sequence.
                For each nucleotide in genome s sequence, compare to the reference sequence.
                */
                for (int i = 0; i < sequence.length(); i++) {
                    /*
                    Check if the nucleotides at position i are equal using the != operator as characters are
                    a primitive type.
                    */
                    if (referenceNucleotides[i] != nucleotides[i]) {
                        /* If the nucleotides at position i are different, increment score by 1 */
                        score++;
                    }
                }
            /*
            Else the reference sequence is the same length or shorter than genome s sequence, so only count
            the differences along the length of the reference sequence.
            */
            }
            else {
                /* For each nucleotide in the reference sequence, compare to genome s sequence. */
                for (int i = 0; i < referenceSequence.length(); i++){
                    /*
                    Check if the nucleotides at position i are equal using the != operator as characters are
                    a primitive type.
                    */
                    if (referenceNucleotides[i] != nucleotides[i]){
                        /* If the nucleotides at position i are different, increment score by 1 */
                        score++;
                    }
                }
            }
        }
        /* Print and return score */
        System.out.println("Alignment score is " + score + ".\n");
        return score;
    }
}
