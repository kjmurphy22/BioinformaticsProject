package Interfaces;

import Repo.Repository;

public interface Supportable {

    Repository backup(Repository repository);

    Repository restore(Repository repository);

    void clearRepository(Repository repository);
}
