package Fasta;

import java.util.HashMap;

public class OptimalAlignment extends Alignment {

    public OptimalAlignment(HashMap<String, String> genomes, String referenceGenome){
        super(genomes, referenceGenome);
        System.out.println("Creating optimal alignment...");
        setScore();
        System.out.println("New optimal alignment created.\n");
    }

    public SNPAlignment createSNPAlignment(){
        System.out.println("Creating SNP alignment...");
        HashMap<String, String> snp = new HashMap<>();
        String referenceGenome = getReferenceGenome();
        String referenceSequence = getGenomes().get(referenceGenome);
        char[] referenceNucleotides = referenceSequence.toCharArray();
        for (String s : getGenomes().keySet()){
            StringBuilder toAdd = new StringBuilder();
            String sequence = getGenomes().get(s);
            char[] nucleotides = sequence.toCharArray();
            if (referenceSequence.length() > sequence.length()) {
                System.out.println("Note: reference sequence is longer than " + s + " genome.");
                for (int i = 0; i < sequence.length(); i++){
                    if (referenceNucleotides[i] == nucleotides[i]){
                        toAdd.append(".");
                    } else {
                        toAdd.append(nucleotides[i]);
                    }
                }
            } else {
                for (int i = 0; i < referenceSequence.length(); i++){
                    if (referenceNucleotides[i] == nucleotides[i]){
                        toAdd.append(".");
                    } else {
                        toAdd.append(nucleotides[i]);
                    }
                }
            }
            if (referenceSequence.length() < sequence.length()) {
                System.out.println("Note: reference sequence is shorter than " + s + " genome.");
                toAdd.append(sequence.substring(referenceSequence.length()));
                snp.put(s, String.valueOf(toAdd));
            } else {
                snp.put(s, String.valueOf(toAdd));
            }
        }
        snp.put(referenceGenome, referenceSequence);
        SNPAlignment SNPAlignment = new SNPAlignment(snp, referenceGenome);
        SNPAlignment.setScore(getScore());
        System.out.println("New SNP alignment created using " + referenceGenome + " as reference.\n");
        return SNPAlignment;
    }
}
