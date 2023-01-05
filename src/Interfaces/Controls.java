package Interfaces;

import Repo.Repository;
import Team.Bioinformatician;

public interface Controls {

    void promote(Bioinformatician bioinformatician, Repository repository);

    void overwrite(Bioinformatician bioinformatician, Repository repository);
}
