package Interfaces;

import java.util.ArrayList;

/*
Editable interface is implemented by Bioinformatician subclass of TeamMember.
Blueprint for functions of Bioinformatician to edit their  current StandardAlignment
in a number of ways - adding and removing genomes, editing genome sequences etc.
*/

public interface Editable {

    /*
    Blueprint for addGenome method
    */
    void addGenome(String fileName);

    /*
   Blueprint for replaceGenome method
   */
    void replaceGenome(String genomeName, String replacementSequence);

    /*
   Blueprint for replaceGenomeSequence method
   */
    void replaceGenomeSequence(String toBeReplaced, String replacement, String genomeName);

    /*
   Blueprint for replaceAllSequences method
   */
    void replaceAllSequences(String toBeReplace, String replacement);

    /*
   Blueprint for removeGenome method
   */
    void removeGenome(String name);

    /*
   Blueprint for removeGenomeList method
   */
    void removeGenomeList(String fileName);

    /*
   Blueprint for genomeSearch method
   */
    ArrayList<String> genomeSearch(String sequence);
}
