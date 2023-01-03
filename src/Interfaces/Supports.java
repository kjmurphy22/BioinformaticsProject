package Interfaces;

import Repo.Repository;

public interface Supports {

    public Repository backup();

    public Repository restore();

    public void clearRepository();
}
