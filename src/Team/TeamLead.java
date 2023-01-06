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

public class TeamLead extends TeamMember implements Controllable, Writable {

    private static int numberOfTeamLeads = 0;

    {
        numberOfTeamLeads++;
    }

    public TeamLead(String name, int years){
        super(RoleType.TEAMLEAD, name, years);
    }

    public static int getNumberOfTeamLeads() {
        return numberOfTeamLeads;
    }

    public StandardAlignment getLiveUserAlignment(Bioinformatician bioinformatician){
        return bioinformatician.getAlignment();
    }

    public String getStandardAlignmentOwner(StandardAlignment standardAlignment){
        return standardAlignment.getUser();
    }

    public StandardAlignment getRepositoryUserAlignment(String name, Repository repository){
        return repository.getUserAlignments().get(name);
    }

    public HashMap<String, StandardAlignment> getAllUserAlignments(Repository repository){
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
        HashMap<String, StandardAlignment> users = getAllUserAlignments(repository);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (String b : users.keySet()){
                bw.write(b + " alignment");
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
        HashMap<String, StandardAlignment> users = getAllUserAlignments(repository);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            for (String b : users.keySet()){
                String toWrite = b + " score: " + users.get(b).getScore() + "\n";
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
        OptimalAlignment newOptimalAlignment = new OptimalAlignment(bioinformatician.getAlignment().getGenomes(), bioinformatician.getAlignment().getReferenceGenome());
        repository.setOptimalAlignment(newOptimalAlignment);
        System.out.println("New optimal alignment set.\n");
        repository.setSNPAlignment(newOptimalAlignment.createSNPAlignment());
        System.out.println("New SNP alignment created.\n");
    }

    @Override
    public void overwrite(Bioinformatician bioinformatician, Repository repository) {
        System.out.println("Overwriting " + bioinformatician.getName() + "'s alignment with current optimal alignment...");
        StandardAlignment newAlignment = new StandardAlignment(repository.getOptimalAlignment(), bioinformatician.getName());
        bioinformatician.setAlignment(newAlignment);
        System.out.println("Alignment overwritten");
    }
}
