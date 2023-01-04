package Team;

import Fasta.StandardAlignment;
import Interfaces.Writable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Bioinformatician extends TeamMember implements Writable {

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
