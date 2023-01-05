package Team;

import Fasta.OptimalAlignment;
import Fasta.StandardAlignment;
import Interfaces.Controllable;
import Interfaces.Writable;
import Repo.Repository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class TeamLead extends TeamMember implements Writable, Controllable {

    public TeamLead(String name, int years){
        super(RoleType.TEAMLEAD, name, years);
    }

    public StandardAlignment getUserAlignment(Bioinformatician bioinformatician){
        return  bioinformatician.getAlignment();
    }

    public HashMap<Bioinformatician, StandardAlignment> getAllUserAlignments(Repository repository){
        return repository.getUserAlignments();
    }

    @Override
    public void writeAlignment() {
        System.out.println("Team Lead has no alignment to write.");
    }

    @Override
    public void writeScore() {
        System.out.println("Team Lead has no score to write.");
    }

    public void writeAllAlignments(Repository repository) {
        System.out.println("Writing all user alignments to file...");
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".alignment.txt";
        HashMap<Bioinformatician, StandardAlignment> users = getAllUserAlignments(repository);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (Bioinformatician b : users.keySet()){
                bw.write(b.getName() + " alignment");
                HashMap<String, String> temp = users.get(b).getGenomes();
                for (String s : temp.keySet()){
                    bw.write("\n>" + s + "\n" + temp.get(s));
                }
                bw.write("\n\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User alignments written to file: " + fileName);
    }

    public void writeAllScores(Repository repository) {
        System.out.println("Writing all user scores to file...");
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".score.txt";
        HashMap<Bioinformatician, StandardAlignment> users = getAllUserAlignments(repository);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (Bioinformatician b : users.keySet()){
                String toWrite = b.getName() + " score: " + users.get(b).getScore() + "\n";
                bw.write(toWrite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User scores written to file: " + fileName);
    }

    @Override
    public void promote(Bioinformatician bioinformatician, Repository repository) {
        System.out.println("Promoting " + bioinformatician.getName() + "'s alignment to optimal alignment...");
        OptimalAlignment newOptimalAlignment = new OptimalAlignment(bioinformatician.getAlignment().getGenomes());
        repository.setOptimalAlignment(newOptimalAlignment);
        System.out.println("New optimal alignment set.");
    }

    @Override
    public void overwrite(Bioinformatician bioinformatician, Repository repository) {
        System.out.println("Overwriting " + bioinformatician.getName() + "'s alignment with current optimal alignment...");
        StandardAlignment newAlignment = new StandardAlignment(repository.getOptimalAlignment(), bioinformatician.getName());
        bioinformatician.setAlignment(newAlignment);
        System.out.println("Alignment overwritten");
    }
}
