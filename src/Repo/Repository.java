package Repo;

import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;
import Fasta.StandardAlignment;

import java.util.HashMap;

/*
Repository is a class that stores Alignment objects. It has three private variables.
optimalAlignment is an OptimalAlignment object
SNPAlignment is a SNPAlignment object.
userAlignments is a HashMap<String, StandardAlignment> object storing the names of bioinformaticians who have
been assigned an alignment as keys and their respective StandardAlignment object as values.
*/

public class Repository {

    private OptimalAlignment optimalAlignment;
    private SNPAlignment SNPAlignment;
    private HashMap<String, StandardAlignment> userAlignments;

    /*
    Repository objects only have constructor and getter and setter methods.
    */

    /*
    Repository has two constructors. This constructor creates a new repository from an OptimalAlignment object and
    a HashMap<String, StandardAlignment> object. It's called in the main method to create the initial repository.
    It is also called in the Repository(repository) constructor to create new Repository objects from other
    Repository objects.
    */
    public Repository(OptimalAlignment optimalAlignment, HashMap<String, StandardAlignment> userAlignments){
        System.out.println("Creating repository...\n");
        OptimalAlignment optimal = new OptimalAlignment(optimalAlignment.getGenomes(), optimalAlignment.getReferenceGenome());
        SNPAlignment snp = optimal.createSNPAlignment();
        HashMap<String, StandardAlignment> standardAlignments = new HashMap<String, StandardAlignment>();
        for (String s : userAlignments.keySet()){
            HashMap<String, String> temp = new HashMap<String, String>();
            for (String g : userAlignments.get(s).getGenomes().keySet()){
                temp.put(s, userAlignments.get(s).getGenomes().get(g));
            }
            StandardAlignment standardAlignment = new StandardAlignment(temp, userAlignments.get(s).getReferenceGenome(), s);
            standardAlignments.put(s, standardAlignment);
        }
        this.optimalAlignment = optimal;
        this.SNPAlignment = snp;
        this.userAlignments = standardAlignments;
        System.out.println("Repository created.\n");
    }

    /*
    Repository has two constructors. This constructor creates a new Repository object from an existing Repository
    object. It is used by TechnicalSupport objects to create backups from current repositories and restore
    repositories from backup repositories.
    */

    public Repository(Repository repository) {
        Repository repo = new Repository(repository.getOptimalAlignment(), repository.getUserAlignments());
        this.optimalAlignment = repo.getOptimalAlignment();
        this.SNPAlignment = repo.getSNPAlignment();
        this.userAlignments = repo.getUserAlignments();
    }

    public void setOptimalAlignment(OptimalAlignment optimalAlignment) {
        this.optimalAlignment = optimalAlignment;
    }

    public OptimalAlignment getOptimalAlignment() {
        return optimalAlignment;
    }

    public void setSNPAlignment(SNPAlignment SNPAlignment) {
        this.SNPAlignment = SNPAlignment;
    }

    public SNPAlignment getSNPAlignment() {
        return SNPAlignment;
    }

    public void setUserAlignments(HashMap<String, StandardAlignment> userAlignments) {
        this.userAlignments = userAlignments;
    }

    public HashMap<String, StandardAlignment> getUserAlignments() {
        return userAlignments;
    }
}
