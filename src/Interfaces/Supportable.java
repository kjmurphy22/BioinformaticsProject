package Interfaces;

import Repo.Repository;

/*
Supportable interface is implemented by TechnicalSupport subclass of TeamMember.
Blueprint for functions of TechnicalSupport to backup, restore and clear the
repository.
*/

public interface Supportable {

    /*
    Blueprint for backup method
    */
    Repository backup(Repository repository);

    /*
    Blueprint for restore method
    */
    Repository restore(Repository repository);

    /*
    Blueprint for clearRepository method*/
    void clearRepository(Repository repository);
}
