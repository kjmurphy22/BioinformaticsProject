package Fasta;

public class Genome {

    String genomeName;
    String genomeSequence;

    public Genome(String genomeName, String genomeSequence){
        this.genomeName = genomeName;
        this.genomeSequence = genomeSequence;
    }

    public void setGenomeName(String genomeName) {
        this.genomeName = genomeName;
    }

    public String getGenomeName() {
        return genomeName;
    }

    public void setGenomeSequence(String genomeSequence) {
        this.genomeSequence = genomeSequence;
    }

    public String getGenomeSequence() {
        return genomeSequence;
    }

}
