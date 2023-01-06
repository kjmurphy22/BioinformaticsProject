package Reader;

import Interfaces.Readable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/*
FastaReader class is used to read in .fasta files. It has two private final variables.
fileName is a string containing the name of the .fasta file to be read.
genomes is a HashMap<String, String> object that will contain the genome names as keys and genome sequences as values.
*/
public class FastaReader implements Readable {

    private final String fileName;
    private final HashMap<String, String> genomes;

    /*
    Class constructor takes one argument fileName and creates FastaReader object with fileName
    and genomes (null) variables
    */
    public FastaReader(String fileName) {
        this.fileName = fileName;
        this.genomes = new HashMap<String, String>();
    }

    /* Readable method implementation. Called in readFile to check if fileName has a .fasta extension */
    @Override
    public boolean checkExtension(){
        String extension = fileName.substring(fileName.length()-6);
        return extension.equals(".fasta");
    }

    /*
    The most important method available to FastaReader is readFile. This method attempts to read in the file fileName
    line by line and puts the genome name and sequence in the genomes HashMap variable.
    */
    public HashMap<String, String> readFile() {
        /* Check file extension before trying to read. Returns null if extension is not .fasta */
        if (!checkExtension()){
            System.out.println("File does not have .fasta extension. Aborting read process.");
            return null;
        }
        System.out.println("Reading fasta file...");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            /* Read first line (which should be the first genome name */
            String line = br.readLine();
            while (line != null) {
                /* Use substring to strip the leading ">" from genome names. readLine is called
                again to move on to the sequence of the corresponding genome name. */
                genomes.put(line.substring(1), br.readLine());
                /* Go to next genome name. */
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fasta file read complete.\n");
        return genomes;
    }
}
