package Repo;

import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;
import Fasta.StandardAlignment;

import java.util.HashMap;

public class Repository {

    OptimalAlignment optimalAlignment;
    SNPAlignment SNPAlignment;
    HashMap<String, StandardAlignment> userAlignments;

    public Repository(OptimalAlignment optimalAlignment, HashMap<String, StandardAlignment> userAlignments){
        System.out.println("Creating repository...");
        OptimalAlignment optimal = new OptimalAlignment(optimalAlignment.getGenomes());
        SNPAlignment snp = optimal.createSNPAlignment("1990.U.CD.90.90CD121E12");
        HashMap<String, StandardAlignment> standardAlignments = new HashMap<String, StandardAlignment>();
        for (String s : userAlignments.keySet()){
            HashMap<String, String> temp = new HashMap<String, String>();
            for (String g : userAlignments.get(s).getGenomes().keySet()){
                temp.put(s, userAlignments.get(s).getGenomes().get(g));
            }
            StandardAlignment standardAlignment = new StandardAlignment(temp, s);
            standardAlignments.put(s, standardAlignment);
        }
        this.optimalAlignment = optimal;
        this.SNPAlignment = snp;
        this.userAlignments = standardAlignments;
        System.out.println("Repository created.\n");
    }

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
