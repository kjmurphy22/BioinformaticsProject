package Team;

import Fasta.StandardAlignment;
import Interfaces.Supportable;
import Repo.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/*
TechnicalSupport is a concrete class that extends TeamMember and implements the Supportable interface.
*/

public class TechnicalSupport extends TeamMember implements Supportable {

    private static int numberOfTechnicalSupports = 0;

    static {
        numberOfTechnicalSupports++;
    }

    protected String lastBackupDateTime = "No existing repository backup.\n";

    public TechnicalSupport(String name, int years) {
        super(RoleType.TECHNICALSUPPORT, name, years);
    }

    public static int getNumberOfTechnicalSupports() {
        return numberOfTechnicalSupports;
    }

    public void setLastBackupDateTime(String lastBackupDateTime) {
        this.lastBackupDateTime = lastBackupDateTime;
    }

    public String getLastBackupDateTime() {
        return lastBackupDateTime;
    }

    public HashMap<String, StandardAlignment> getAllUserAlignments(Repository repository){
        return repository.getUserAlignments();
    }

    @Override
    public Repository backup(Repository repository) {
        System.out.println("Backing up repository...");
        Repository backupRepo = new Repository(repository);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String backupDateTime = dtf.format(now);
        setLastBackupDateTime(backupDateTime);
        System.out.println("New repository backup created on " + backupDateTime + ".\n");
        return backupRepo;
    }

    @Override
    public Repository restore(Repository repository) {
        System.out.println("Restoring repository from " + getLastBackupDateTime() + " backup");
        Repository newRepository = new Repository(repository);
        System.out.println("Repository restored from " + getLastBackupDateTime() + ".\n");
        return newRepository;
    }

    @Override
    public void clearRepository(Repository repository) {
        System.out.println("Clearing current repository...");
        repository.setOptimalAlignment(null);
        repository.setSNPAlignment(null);
        repository.setUserAlignments(null);
        System.out.println("Repository cleared.\n");
    }
}
