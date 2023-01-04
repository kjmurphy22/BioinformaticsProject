package Fasta;

import java.util.HashMap;

public class OptimalAlignment extends Alignment {

    public OptimalAlignment(HashMap<String, String> genomes){
        super(genomes);
    }

    public SNPAlignment createSNPAlignment(String referenceGenome){
        System.out.println("Creating SNP alignment...");
        HashMap<String, String> snp = new HashMap<>();
        String referenceSequence = getGenomes().get(referenceGenome);
        char[] referenceNucleotides = referenceSequence.toCharArray();
        for (String s : getGenomes().keySet()){
            StringBuilder toAdd = new StringBuilder();
            String sequence = getGenomes().get(s);
            char[] nucleotides = sequence.toCharArray();
            for (int i = 0; i < referenceSequence.length(); i++){
                if (referenceNucleotides[i] == nucleotides[i]){
                    toAdd.append(".");
                } else {
                    toAdd.append(nucleotides[i]);
                }
            }
            snp.put(s, String.valueOf(toAdd));
        }
        snp.put(referenceGenome, referenceSequence);
        SNPAlignment SNPAlignment = new SNPAlignment(snp, referenceGenome);
        System.out.println("New SNP alignment created using " + referenceGenome + ".\n");
        return SNPAlignment;
    }
}
