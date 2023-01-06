package Interfaces;

import Repo.Repository;
import Team.Bioinformatician;

/*
Controllable interface is implemented by TeamLead subclass of TeamMember.
Blueprint for functions of TeamLead for promoting user alignments to a repository
and for overwriting user alignments with the repository alignment.
*/

public interface Controllable {

    /*
    Blueprint for promote method
    */
    void promote(Bioinformatician bioinformatician, Repository repository);

    /*
    Blueprint for overwrite method
    */
    void overwrite(Bioinformatician bioinformatician, Repository repository);
}
