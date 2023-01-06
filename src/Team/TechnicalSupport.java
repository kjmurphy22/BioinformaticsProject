package Team;

import Fasta.StandardAlignment;
import Interfaces.Supportable;
import Repo.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/*
TechnicalSupport is a concrete class that extends TeamMember and implements the Supportable interface.
It has one private static subclass variable and one private instance subclass variable.
numberOfTechnicalSupports counts the number of TechnicalSupport objects created after TeamReader object calls createTeam in MainMethod.
lastBackupDateTime is a String that stores the date and time of the last backup of the repository.
*/

public class TechnicalSupport extends TeamMember implements Supportable {

    private static int numberOfTechnicalSupports = 0;

    /* Increments every time a TechnicalSupport object is created */
    {
        numberOfTechnicalSupports++;
    }

    private String lastBackupDateTime = "No existing repository backup.\n";

    /* TechnicalSupport only has one constructor and uses the super constructor from TeamMember */
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

    /* getRepositoryUserStandardAlignment retrieves the StandardAlignment of a specific user in the repository */
    public StandardAlignment getRepositoryUserStandardAlignment(String name, Repository repository){
        return repository.getUserAlignments().get(name);
    }

    /* getAllRepositoryUserStandardAlignments retrieves all StandardAlignments in the repository */
    public HashMap<String, StandardAlignment> getAllRepositoryUserStandardAlignments(Repository repository){
        return repository.getUserAlignments();
    }

    /*
    Supportable backup implementation. Creates a backup of the current repository and sets backup date and time
    of the TechnicalSupport object who called the method.
    */
    @Override
    public Repository backup(Repository repository) {
        System.out.println("Backing up repository...");
        /* Call Repository constructor to create new Repository object with the same properties as repository */
        Repository backupRepo = new Repository(repository);
        /* Set format for string that will store backup repository time and date */
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");
        /* Get current time and date */
        LocalDateTime now = LocalDateTime.now();
        /* Apply format to LocalDateTime object now */
        String backupDateTime = dtf.format(now);
        /* Set TechnicalSupport lastBackupDateTime to backupDateTime */
        setLastBackupDateTime(backupDateTime);
        System.out.println("New repository backup created on " + backupDateTime + ".\n");
        /* Return Repository object backupRepo */
        return backupRepo;
    }

    /*
    Supportable restore implementation. Creates a new Repository object based on another Repository object. Allows the
    technical support team member to restore a repository from a backup by using backup repository as the method argument.
    */
    @Override
    public Repository restore(Repository backup) {
        /* Print backup time and date of backup repository (stored in TechnicalSupport object calling restore) */
        System.out.println("Restoring repository from " + getLastBackupDateTime() + " backup");
        /* Call Repository constructor to create new Repository object based on backup */
        Repository newRepository = new Repository(backup);
        System.out.println("Repository restored from " + getLastBackupDateTime() + ".\n");
        /* return Repository object newRepository */
        return newRepository;
    }

    /* Supportable clearRepository implementation. Clears the current repository contents. */
    @Override
    public void clearRepository(Repository repository) {
        System.out.println("Clearing current repository...");
        /* Use setters to set all repository variables to null */
        repository.setOptimalAlignment(null);
        repository.setSNPAlignment(null);
        repository.setUserAlignments(null);
        System.out.println("Repository cleared.\n");
    }
}
