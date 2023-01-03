package Repo;

import Fasta.SNPAlignment;

import java.util.ArrayList;
import java.util.HashMap;

public class Repository {

    HashMap<String, String> optimalAlignment;
    HashMap<String, String> SNPAlignment;
    ArrayList<HashMap<String, String>> userAlignments;

    public Repository(HashMap<String, String> optimalAlignment,
                      HashMap<String, String> SNPAlignment,
                      ArrayList<HashMap<String, String>> userAlignments){
        this.optimalAlignment = optimalAlignment;
        this.SNPAlignment = SNPAlignment;
        this.userAlignments = userAlignments;
    }
}
