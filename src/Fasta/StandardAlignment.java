package Fasta;

import java.util.HashMap;

/*
StandardAlignment is a concrete class that extends Alignment. It has one private String variable.
user is a String that contains the first and second name of the bioinformatician to which a
StandardAlignment object has been assigned.
*/

public class StandardAlignment extends Alignment{

    private String user;

    /*
    StandardAlignment has two constructors. This constructor is called to create a new StandardAlignment object using
    an OptimalAlignment object. It is used to assign the initial StandardAlignment objects to bioinformaticians in the
    main method and to overwrite bioinformatician's StandardAlignment objects with the optimal alignment in the repository.
    */
    public StandardAlignment(OptimalAlignment optimal, String user){
        super(optimal);
        this.user = user;
    }

    /*
    StandardAlignment has two constructors. This constructor is called to create a new StandardAlignment object using
    a HashMap<String, String> object and two String objects. This constructor is called in the
    Repository(OptimalAlignment, HashMap<String, StandardAlignment>) constructor to create deep
    copies of StandardAlignments when creating a new Repository.
    */
    public StandardAlignment(HashMap<String, String> genomes, String referenceGenome, String user){
        super(genomes, referenceGenome);
        this.user = user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}
