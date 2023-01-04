package Interfaces;

import Repo.Repository;

public interface Supports {

    Repository backup(Repository repository);

    Repository restore(Repository repository);

    void clearRepository(Repository repository);
}
