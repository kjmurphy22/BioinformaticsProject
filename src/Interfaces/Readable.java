package Interfaces;

/*
Readable interface is implemented by FastaReader and TeamReader classes.
Blueprint for functions to check the file extension of the file trying to be read.
*/

public interface Readable {

    /*
    Blueprint for checkExtension method
    */
    boolean checkExtension();
}
