package Fasta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class FastaReader {

    String fileName;
    HashMap<String, String> genomes;

    public FastaReader(String fileName) {
        this.fileName = fileName;
        this.genomes = new HashMap<String, String>();
    }

    public HashMap<String, String> readFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine();
            while (line != null) {
                genomes.put(line.substring(1), br.readLine());
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return genomes;
    }

    public static void main(String[] args) {
        String fileName = "src/hiv_test.fasta";
        FastaReader fastaInput = new FastaReader(fileName);
        HashMap<String, String> fastaFile = fastaInput.readFile();
        System.out.println("Contents of fasta file: " + fastaFile);
    }
}
