package Interfaces;

import java.util.ArrayList;

public interface Editable {

    void addGenome(String fileName);

    void replaceGenome();

    void replaceGenomeSequence();

    void replaceAllSequences();

    void removeGenome(String name);

    void removeGenomeList(String fileName);

    ArrayList<String> genomeSearch(String sequence);
}
