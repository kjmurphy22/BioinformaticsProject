package Fasta;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StandardAlignment extends Alignment{

    protected Alignment alignment;
    protected String user;

    public StandardAlignment(OptimalAlignment optimal, String user){
        super(optimal);
        this.user = user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void addGenome(String fileName){
        String toAdd = "src/" + fileName;
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(toAdd))){
            String line = br.readLine();
            while(line != null){
                System.out.println("Adding genome " + line.substring(1) + "...");
                genomes.put(line.substring(1), br.readLine());
                counter++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter + " new genomes added. This alignment now contains " + genomes.size() + " genomes.\n");
    }

    public void removeGenome(String name){
        System.out.println("Removing genome " + name + " from the alignment.");
        genomes.remove(name);
        System.out.println(name + " genome removed. " + genomes.size() + " genomes remaining.\n");
    }

    public void removeGenomeList(String fileName){
        String toRemove = "src/" + fileName;
        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(toRemove))){
            String line = br.readLine();
            while(line != null){
                System.out.println("Removing genome " + line + "...");
                genomes.remove(line);
                counter++;
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter + " genomes removed. This alignment now contains " + genomes.size() + " genomes.\n");
    }

    public ArrayList<String> genomeSearch(String sequence){
        System.out.println("Searching for sequence hits...");
        ArrayList<String> hits = new ArrayList<String>();
        for (String s : getGenomes().keySet()){
            if (getGenomes().get(s).contains(sequence)){
                hits.add(s);
            }
        }
        System.out.println(hits.size() + " genomes contain the sequence " + sequence + ".\nThe genomes containing the sequence are " + hits);
        return hits;
    }
}
