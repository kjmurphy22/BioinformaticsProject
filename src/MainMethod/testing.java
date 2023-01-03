package MainMethod;

import Fasta.FastaReader;

import java.util.HashMap;

public class testing {

    public static void main(String[] args) {
        String teamFile = "src/" + args[0];
        String fastaFile = "src/" + args[1];
        FastaReader fastaInput = new FastaReader(fastaFile);
        HashMap<String, String> genomes = fastaInput.readFile();

    }
}
