package Interfaces;

import Fasta.StandardAlignment;
import Repo.Repository;
import Team.Bioinformatician;

public interface Controls {

    void promote(StandardAlignment alignment, Repository repository);

    void overwrite(Bioinformatician bioinformatician);
}
