package Team;

import Fasta.StandardAlignment;
import Interfaces.Editable;
import Interfaces.Writable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/*
Bioinformatician is a concrete class that extends TeamMember and implements the Editable and Writable interfaces.
It has one private static subclass variable and one private instance subclass variable.
numberOfBioinformaticians counts the number of Bioinformatician objects created after TeamReader object calls createTeam in MainMethod.
alignment is a StandardAlignment object that is null upon creation of a Bioinformatician object. It needs to be
set to a StandardAlignment in MainMethod before a bioinformatician can start working.
*/

public class Bioinformatician extends TeamMember implements Editable, Writable {

    private static int numberOfBioinformaticians = 0;

    static {
        numberOfBioinformaticians++;
    }

    private StandardAlignment alignment = null;

    /* Bioinformatician only has one constructor and uses the super constructor from TeamMember */
    public Bioinformatician(String name, int years) {
        super(RoleType.BIOINFORMATICIAN, name, years);
    }

    public static int getNumberOfBioinformaticians() {
        return numberOfBioinformaticians;
    }

    public void setAlignment(StandardAlignment alignment) {
        this.alignment = alignment;
    }

    public StandardAlignment getAlignment() {
        return alignment;
    }

    public HashMap<String, String> getAlignmentGenomes() {
        return alignment.getGenomes();
    }

    /*
    Editable genomeSearch implementation. Allows user to search all alignments for a specific sequence and returns the
    genome names that contain the sequence as an ArrayList<String>.
    */
    @Override
    public ArrayList<String> genomeSearch(String sequence){
        System.out.println("Searching for sequence hits...");
        /* Create empty ArrayList<String> to store genome names containing search sequence. */
        ArrayList<String> hits = new ArrayList<String>();
        /* For each genome s */
        for (String s : getAlignmentGenomes().keySet()){
            /*
            getAlignmentGenomes returns a HashMap<String, String> with keys genome names and values genome sequences.
            Use .contains to check if sequences then contain the search sequence.
            */
            if (getAlignmentGenomes().get(s).contains(sequence)){
                /*
                .contains returns true if the genome sequence contains the search sequence. The genome name s then
                gets added to the ArrayList<String> hits.
                */
                hits.add(s);
            }
        }
        /* hits.size() returns amount of genomes containing the search sequence. */
        System.out.println(hits.size() + " genomes contain the target sequence. The genomes containing the sequence are " + hits + ".\n");
        return hits;
    }

    /*
    Editable replaceGenome implementation. Allows user to replace the genome sequence of a specific genome genomeName
    with a new sequence replacementSequence, regardless of length.
    */
    @Override
    public void replaceGenome(String genomeName, String replacementSequence) {
        /* Checks if sequence associated with genomeName and replacement sequence are the same and aborts if they are. */
        if(getAlignmentGenomes().get(genomeName).equals(replacementSequence)){
            System.out.println(genomeName + " sequence and replacement sequence are the same. Aborting.\n");
        }
        /* Prints a warning if sequence associated with genomeName and replacement sequence are not the same length. */
        else if (getAlignmentGenomes().get(genomeName).length() != replacementSequence.length()){
            System.out.println("Warning: replacement sequence is different length to " + genomeName + " sequence.");
            /* HashMap put method overwrites key value with new value replacementSequence */
            getAlignmentGenomes().put(genomeName, replacementSequence);
            System.out.println(genomeName + " sequence replaced.\n");
        } else {
            /* HashMap put method overwrites key value with new value replacementSequence */
            getAlignmentGenomes().put(genomeName, replacementSequence);
            System.out.println(genomeName + " sequence replaced.\n");
        }
    }

    /*
    Editable replaceGenomeSequence implementation. Allows user to replace all occurrences of a target sequence
    in a specific genome with a new sequence (of equal length).
    */
    @Override
    public void replaceGenomeSequence(String toBeReplaced, String replacement, String genomeName) {
        /* Gets the genome sequence associated with genomeName */
        String genomeSequence = getAlignmentGenomes().get(genomeName);
        /* Checks if target sequence and replacement sequence are the same and aborts if they are. */
        if (toBeReplaced.equals(replacement)) {
            System.out.println("Replacement sequence and sequence to be replaced are the same. Aborting");
        }
        /* Checks if genome sequence contains target sequence and aborts if it does not. */
        else if (!genomeSequence.contains(toBeReplaced)) {
            System.out.println(genomeName + " does not contain target sequence. Aborting.");
        }
        /* Check if target sequence and replacement sequence are same length and aborts if they are not. */
        else if (toBeReplaced.length() != replacement.length()){
            System.out.println("Replacement and sequence to be replaced are different lengths. Aborting.");
        } else {
            /*
            After these checks, the genomeSequence is cut into two parts, start and end, either side of the
            first instance of the target sequence using substring and the length of the target sequence.
            */
            int n = genomeSequence.indexOf(toBeReplaced);
            String start = genomeSequence.substring(0, n);
            String end = genomeSequence.substring(n + toBeReplaced.length());
            /*
            The replacement sequence is joined to the start and end strings to create a new String of equal length
            to the original genome sequence.
            */
            String newSequence = start + replacement + end;
            /*
            .put overwrites the original genome sequence of genomeName with the new sequence containing the
            replacement sequence.
            */
            getAlignmentGenomes().put(genomeName, newSequence);
            /*
            The end of the new sequence has to be checked for any more instances of the target sequence as they
            also have to be replaced. Only the end is checked to stop replacement of any "fake" target sequences
            that could arise from creating newSequence. This could happen if start or end contain a substring of the
            target sequence and replacement contains the rest of the string, so that they would make a "fake"
            target sequence when appended that didn't exist in the original genome sequence.
            */
            if (end.contains(toBeReplaced)){
                /* replaceGenomeSequence is called recursively to replace any more instances of the target sequence */
                replaceGenomeSequence(toBeReplaced, replacement, genomeName);
            } else {
                System.out.println("All instances of target sequence in " + genomeName + " replaced.\n");
            }
        }
    }

    /*
    Editable replaceAllSequences implementation. Allows user to replace all occurrences of a target sequence
    in the alignment with a new sequence (of equal length).
    */
    @Override
    public void replaceAllSequences(String toBeReplaced, String replacement) {
        /* For genome s */
        for (String s : getAlignmentGenomes().keySet()){
            /* Check if genome s contains the target sequence and print if it does not. */
            if (!getAlignmentGenomes().get(s).contains(toBeReplaced)){
                System.out.println(s + " does not contain any instances of target sequence.\n");
            }
            /* If genome s contains the target sequence, replace call replaceGenomeSequence to replace it. */
            else {
                replaceGenomeSequence(toBeReplaced, replacement, s);
            }
        }
    }

    /*
    Editable addGenome implementation. Allows user to add new genomes to their alignment from source file (file must
    be stored in same fasta format as input to FastaReader).
    */
    @Override
    public void addGenome(String fileName){
        /* Create filepath string */
        String toAdd = "src/" + fileName;
        /* Initalise counter to count number of new genomes added */
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(toAdd))){
            /* Read first line of input file (should be >genome name) */
            String line = br.readLine();
            while(line != null){
                System.out.println("Adding genome " + line.substring(1) + "...");
                /* Strip leading > from genome name and use .put method to add it to bioinformatician's alignment */
                getAlignmentGenomes().put(line.substring(1), br.readLine());
                /* Increment counter */
                counter++;
                /* Go to next line */
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* Print number of genomes added and size of alignment. */
        System.out.println(counter + " genomes added. This alignment now contains " + getAlignmentGenomes().size() + " genomes.\n");
    }

    /* Editable removeGenome implementation. Allows user to remove a specific genome from their alignment */
    @Override
    public void removeGenome(String name){
        /* Check if the genome to be removed is the current reference genome and remove it if it is not. */
        if (!getAlignment().getReferenceGenome().equals(name)){
            System.out.println("Removing genome " + name + " from the alignment...");
            /* Use .remove method to remove key value pair from alignment HashMap */
            getAlignmentGenomes().remove(name);
            System.out.println(name + " genome removed. " + getAlignmentGenomes().size() + " genomes remaining.\n");
        }
        /* If genome to remove is current reference genome, do not remove and print */
        else {
            System.out.println(name + " is current reference genome for this alignment. Cannot remove.");
        }
    }

    /* Editable removeGenomeList implementation. Allows user to remove a list (from file) of genomes from their alignment */
    @Override
    public void removeGenomeList(String fileName){
        /* Create filepath for file containing list of genomes to remove */
        String toRemove = "src/" + fileName;
        /* Initialise counter for number of genomes removed */
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(toRemove))){
            /* Read first line */
            String line = br.readLine();
            while(line != null){
                /* If the genome in the list is not the current reference genome, call removeGenome to remove it. */
                if (!getAlignment().getReferenceGenome().equals(line)){
                    removeGenome(line);
                    /* Increment counter */
                    counter++;
                    /* Read next line */
                    line = br.readLine();
                } else {
                    /* If a genome in the removal list is the current reference genome, print and do not remove it */
                    System.out.println(line + " is current reference genome for this alignment. Cannot remove.");
                    /* Read next line */
                    line = br.readLine();
                }

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter + " genomes removed. This alignment now contains " + getAlignmentGenomes().size() + " genomes.\n");
    }

    /* Writable writeAlignment implementation. Writes the bioinformatician's alignment to file. */
    @Override
    public void writeAlignment() {
        System.out.println("Writing " + getName() + "'s alignment to file...");
        /* Replace whitespace with underscore for writing file name */
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".alignment.txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            /* For each genome s in the alignment */
            for (String s : getAlignmentGenomes().keySet()){
                /* Write ">" and genome name s and write the genome sequence on a new line */
                bw.write(">" + s + "\n" + getAlignmentGenomes().get(s) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Alignment written to file: " + fileName + "\n");
    }

    /* Writable writeScore implementation. Writes the bioinformatician's score to file. Functions similarly to writeAlignment */
    @Override
    public void writeScore() {
        /* Replace whitespace with underscore for writing file name */
        System.out.println("Writing " + getName() + "'s score to file...");
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".score.txt";
        /* Get alignment score */
        int scoreToWrite = getAlignment().getScore();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            /* Write alignment score to file */
            bw.write(String.valueOf(scoreToWrite));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Score written to file: " + fileName + "\n");
    }
}
