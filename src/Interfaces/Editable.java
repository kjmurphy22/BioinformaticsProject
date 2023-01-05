package Interfaces;

import java.util.ArrayList;

public interface Editable {

    void addGenome(String fileName);

    void replaceGenome(String genomeName, String replacementSequence);

    void replaceGenomeSequence(String toBeReplaced, String replacement, String genomeName);

    void replaceAllSequences(String toBeReplace, String replacement);

    void removeGenome(String name);

    void removeGenomeList(String fileName);

    ArrayList<String> genomeSearch(String sequence);
}
