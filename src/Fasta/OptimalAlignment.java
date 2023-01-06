package Fasta;

import java.util.HashMap;

/*
OptimalAlignment is a concrete class that extends Alignment. It has no subclass variables.
*/

public class OptimalAlignment extends Alignment {

    /*
    OptimalAlignment only has one constructor. It uses the super constructor from Alignment and also calls
    setScore() to compute and set the score of the OptimalAlignment object being created.
    */
    public OptimalAlignment(HashMap<String, String> genomes, String referenceGenome){
        super(genomes, referenceGenome);
        System.out.println("Creating optimal alignment...");
        /* Call setScore() to call computeAlignmentScore and set the score of the new OptimalAlignment object. */
        setScore();
        System.out.println("New optimal alignment created.\n");
    }

    /*
    The only subclass method in OptimalAlignment is createSNPAlignment. This method returns a SNPAlignment object
    based on the OptimalAlignment object calling the method. The SNP alignment is created by comparing all
    sequences in the MSA to a reference sequence. If a position in a sequence differs from the reference sequence,
    the nucleotide of the sequence is printed at that position. If there is no difference, a "." is printed at
    that position.
    */
    public SNPAlignment createSNPAlignment(){
        System.out.println("Creating SNP alignment...");
        /* Create HashMap<String, String> object to store genome names as keys and sequences as values */
        HashMap<String, String> snp = new HashMap<>();
        /*
        Get the reference genome and its sequence from the OptimalAlignment object calling the method.
        The SNP alignment will be based on the reference genome.
        */
        String referenceGenome = getReferenceGenome();
        String referenceSequence = getGenomes().get(referenceGenome);
        /* Create a character array of the nucleotides in the reference sequence. */
        char[] referenceNucleotides = referenceSequence.toCharArray();
        /* For each genome s in the alignment */
        for (String s : getGenomes().keySet()){
            /* Initialise a StringBuilder object to create the SNP sequence. */
            StringBuilder toAdd = new StringBuilder();
            /* Get the genome sequence corresponding to genome s */
            String sequence = getGenomes().get(s);
            /* Convert genome s sequence to a character array to compare nucleotides one by one */
            char[] nucleotides = sequence.toCharArray();
            /*
            Check which of the reference sequence and genome s sequence are shorter and only compare
            along the length of the shorter sequence.
            */
            if (referenceSequence.length() > sequence.length()) {
                System.out.println("Note: reference sequence is longer than " + s + " genome.");
                /* For each nucleotide in the sequence */
                for (int i = 0; i < sequence.length(); i++){
                    /* If the nucleotides are the same, append a "." character to the StringBuilder object */
                    if (referenceNucleotides[i] == nucleotides[i]){
                        toAdd.append(".");
                    }
                    /*
                    If the nucleotides are different, append the nucleotide from genome s sequence to the
                    StringBuilder object.
                    */
                    else {
                        toAdd.append(nucleotides[i]);
                    }
                }
            }
            /* Else compare along the length of the reference sequence. */
            else {
                for (int i = 0; i < referenceSequence.length(); i++){
                    /* If the nucleotides are the same, append a "." character to the StringBuilder object */
                    if (referenceNucleotides[i] == nucleotides[i]){
                        toAdd.append(".");
                    }
                    /*
                    If the nucleotides are different, append the nucleotide from genome s sequence to the
                    StringBuilder object.
                    */
                    else {
                        toAdd.append(nucleotides[i]);
                    }
                }
            }
            /* Check if the reference sequence is shorter than genome s sequence */
            if (referenceSequence.length() < sequence.length()) {
                /*
                If the reference sequence is shorter, the part of genome s sequence that was not compared is
                appended to the end of the SNP alignment.
                */
                System.out.println("Note: reference sequence is shorter than " + s + " genome.");
                /*
                sequence.substring(referenceSequence.length()) returns the substring of the part of the
                sequence that was not compared to the reference sequence. This substring is appended
                to the StringBuilder object.
                */
                toAdd.append(sequence.substring(referenceSequence.length()));
                /* Add the genome name s and the SNP alignment of s to the snp HashMap */
                snp.put(s, String.valueOf(toAdd));
            } else {
                /*
                Else if the sequences are the same length, add the genome
                name s and the SNP alignment of s to the snp HashMap.
                */
                snp.put(s, String.valueOf(toAdd));
            }
        }
        /* Add the reference genome and sequence to the SNP HashMap */
        snp.put(referenceGenome, referenceSequence);
        /* Call the SNPAlignment constructor to create a new SNPAlignment object using snp and referenceGenome */
        SNPAlignment SNPAlignment = new SNPAlignment(snp, referenceGenome);
        /*
        Set the score of the new SNPAlignment object to be the same as the OptimalAlignment object that called
        the createSNPAlignment method.
        */
        SNPAlignment.setScore(getScore());
        System.out.println("New SNP alignment created using " + referenceGenome + " as reference.\n");
        /* Return the SNPAlignment object. */
        return SNPAlignment;
    }
}
