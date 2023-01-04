package Interfaces;

import Repo.Repository;

public interface Supports {

    Repository backup();

    Repository restore();

    void clearRepository();
}
