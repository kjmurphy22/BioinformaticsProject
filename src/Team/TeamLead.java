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

/*
TeamLead is a concrete class that extends TeamMember and implements the Controllable and Writable interfaces.
It has one private static subclass variable and one private instance subclass variable.
numberOfTeamLeads counts the number of TeamLead objects created after TeamReader object calls createTeam in MainMethod.
assignedBioinformaticians is a HashMap<String, StandardAlignment> of bioinformaticians who have been assigned a
StandardAlignment. This assignment happens in MainMethod.
*/

public class TeamLead extends TeamMember implements Controllable, Writable {

    private static int numberOfTeamLeads = 0;

    static {
        numberOfTeamLeads++;
    }

    private HashMap<String, StandardAlignment> assignedBioinformaticans;

    /* TeamLead only has one constructor and uses the super constructor from TeamMember */
    public TeamLead(String name, int years){
        super(RoleType.TEAMLEAD, name, years);
    }

    public static int getNumberOfTeamLeads() {
        return numberOfTeamLeads;
    }

    public void setAssignedBioinformaticans(HashMap<String, StandardAlignment> assignedBioinformaticans) {
        this.assignedBioinformaticans = assignedBioinformaticans;
    }

    public HashMap<String, StandardAlignment> getAssignedBioinformaticians() {
        return assignedBioinformaticans;
    }

    /* getCurrentUserStandardAlignment gets the StandardAlignment object of a specific user */
    public StandardAlignment getCurrentUserStandardAlignment(String userName){
        return getAssignedBioinformaticians().get(userName);
    }

    /* getCurrentStandardAlignmentOwner gets the owner of a specific StandardAlignment */
    public String getCurrentStandardAlignmentOwner(StandardAlignment standardAlignment){
        return standardAlignment.getUser();
    }

    /* getRepositoryUserStandardAlignment retrieves the StandardAlignment of a specific user in the repository */
    public StandardAlignment getRepositoryUserStandardAlignment(String name, Repository repository){
        return repository.getUserAlignments().get(name);
    }

    /* getAllRepositoryUserStandardAlignments retrieves all StandardAlignments in the repository */
    public HashMap<String, StandardAlignment> getAllRepositoryUserStandardAlignments(Repository repository){
        return repository.getUserAlignments();
    }

    /*
    Controllable promote implementation. Promotes bioinformatician's alignment to the optimal alignment in repository.
    */
    @Override
    public void promote(Bioinformatician bioinformatician, Repository repository) {
        /* Name of bioinformatician */
        System.out.println("Promoting " + bioinformatician.getName() + "'s alignment to optimal alignment...");
        /*
        Call to OptimalAlignment constructor to create new OptimalAlignment object as deep copy of
        bioinformatician's alignment.
        */
        OptimalAlignment newOptimalAlignment = new OptimalAlignment(bioinformatician.getAlignment().getGenomes(),
                bioinformatician.getAlignment().getReferenceGenome());
        /* Replaces current optimal alignment in repository with new OptimalAlignment object */
        repository.setOptimalAlignment(newOptimalAlignment);
        System.out.println("New optimal alignment set.\n");
        /* Replaces current SNP alignment in repository with new SNPAlignment object based on newOptimalAlignment */
        repository.setSNPAlignment(newOptimalAlignment.createSNPAlignment());
        System.out.println("New SNP alignment created.\n");
    }
    /*
    Controllable overwrite implementation. Overwrites bioinformatician's alignment with the optimal alignment in repository
    */

    @Override
    public void overwrite(Bioinformatician bioinformatician, Repository repository) {
        System.out.println("Overwriting " + bioinformatician.getName() + "'s alignment with current optimal alignment...");
        /*
        Call to StandardAlignment constructor to create new StandardAlignment object as deep copy of current optimal alignment
        */
        StandardAlignment newAlignment = new StandardAlignment(repository.getOptimalAlignment(), bioinformatician.getName());
        /* Set new StandardAlignment object as bioinformatician's StandardAlignment */
        bioinformatician.setAlignment(newAlignment);
        System.out.println("Alignment overwritten");
    }

    /*
    Writable writeAlignment implementation. Writes all bioinformatician's StandardAlignments to file in source.
    DOES NOT write the repository StandardAlignments to file!
    */
    @Override
    public void writeAlignment() {
        System.out.println("Writing all user alignments to file...");
        /* Replace whitespace with underscore for writing file name */
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".alignment.txt";
        /* Gets all assigned bioinformaticians and their StandardAlignments */
        HashMap<String, StandardAlignment> users = getAssignedBioinformaticians();
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            /* For each bioinformatician b */
            for (String b : users.keySet()){
                /* First write bioinformatician's name to separate different alignments in output file */
                bw.write(b + " alignment");
                /*
                Create temp HashMap for clearer code.
                users.get(b) returns the StandardAlignment object of the bioinformatician b.
                users.get(b).getGenomes() returns a HashMap<String, String> of genome names and sequences in the
                StandardAlignment object of bioinformatician b.
                */
                HashMap<String, String> temp = users.get(b).getGenomes();
                /*
                Loop through each genome name s in the StandardAlignment object, returning the sequence temp.get(s),
                writing both to file.
                */
                for (String s : temp.keySet()){
                    bw.write("\n>" + s + "\n" + temp.get(s));
                }
                /* newline character to separate the alignments */
                bw.write("\n\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User alignments written to file: " + fileName + "\n");
    }

    /*
    Writable writeScore implementation. Writes all bioinformatician's StandardAlignment scores to file in source.
    DOES NOT write the repository StandardAlignment scores to file!
    Functions similarly to writeAlignment.
    */
    @Override
    public void writeScore() {
        System.out.println("Writing all user scores to file...");
        /* Replace whitespace with underscore for writing file name */
        String fileOwner = getName().replaceAll("\\s","_");
        String fileName = "src/" + fileOwner + ".score.txt";
        /* Gets all assigned bioinformaticians and their StandardAlignments */
        HashMap<String, StandardAlignment> users = assignedBioinformaticans;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))){
            /* For each bioinformatician b */
            for (String b : users.keySet()){
                /*
                users.get(b).getScore() returns the score of bioinformatician b's alignment.
                */
                String toWrite = b + " score: " + users.get(b).getScore() + "\n";
                bw.write(toWrite);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User scores written to file: " + fileName + "\n");
    }
}
