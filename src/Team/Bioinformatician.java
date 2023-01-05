package Team;

import Fasta.StandardAlignment;
import Interfaces.Editable;
import Interfaces.Writable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Bioinformatician extends TeamMember implements Editable, Writable {

    StandardAlignment alignment = null;

    public Bioinformatician(String name, int years) {
        super(RoleType.BIOINFORMATICIAN, name, years);
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

    @Override
    public void addGenome(String fileName){
        String toAdd = "src/" + fileName;
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(toAdd))){
            String line = br.readLine();
            while(line != null){
                System.out.println("Adding genome " + line.substring(1) + "...");
                getAlignmentGenomes().put(line.substring(1), br.readLine());
                counter++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter + " new genomes added. This alignment now contains " + getAlignmentGenomes().size() + " genomes.\n");
    }

    @Override
    public void replaceGenome() {

    }

    @Override
    public void replaceGenomeSequence() {

    }

    @Override
    public void replaceAllSequences() {

    }

    @Override
    public void removeGenome(String name){
        System.out.println("Removing genome " + name + " from the alignment.");
        getAlignmentGenomes().remove(name);
        System.out.println(name + " genome removed. " + getAlignmentGenomes().size() + " genomes remaining.\n");
    }

    @Override
    public void removeGenomeList(String fileName){
        String toRemove = "src/" + fileName;
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(toRemove))){
            String line = br.readLine();
            while(line != null){
                System.out.println("Removing genome " + line + "...");
                getAlignmentGenomes().remove(line);
                counter++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter + " genomes removed. This alignment now contains " + getAlignmentGenomes().size() + " genomes.\n");
    }

    @Override
    public ArrayList<String> genomeSearch(String sequence){
        System.out.println("Searching for sequence hits...");
        ArrayList<String> hits = new ArrayList<String>();
        for (String s : getAlignmentGenomes().keySet()){
            if (getAlignmentGenomes().get(s).contains(sequence)){
                hits.add(s);
            }
        }
        System.out.println(hits.size() + " genomes contain the sequence " + sequence + ".\nThe genomes containing the sequence are " + hits);
        return hits;
    }

    @Override
    public void writeAlignment() {
        System.out.println("Writing " + getName() + "'s alignment to file...");
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".alignment.txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            getAlignmentGenomes().forEach((k, v) -> {
                try {
                    bw.write(">" + k + "\n" + v + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Alignment written to file: " + fileName);
    }

    @Override
    public void writeScore() {
        System.out.println("Writing " + getName() + "'s score to file...");
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".score.txt";
        int scoreToWrite = this.getAlignment().getScore();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            bw.write(scoreToWrite);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Score written to file: " + fileName);
    }
}
