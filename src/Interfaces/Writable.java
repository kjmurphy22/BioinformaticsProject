package Interfaces;

/*
Writable interface is implemented by TeamLead and Bioinformatician subclasses of TeamMember.
Blueprint for functions to write alignment and score output, with different functionality
dependent on subclass type.
*/

public interface Writable {

    /*
    Blueprint for writeAlignment method
    */
    void writeAlignment();

    /*
    Blueprint for writeScore method
    */
    void writeScore();
}
