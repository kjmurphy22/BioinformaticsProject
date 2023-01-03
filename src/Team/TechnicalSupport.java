package Team;

import Interfaces.Supports;
import Repo.Repository;

public class TechnicalSupport extends TeamMember implements Supports {

    public TechnicalSupport(String name, int years) {
        super(RoleType.TECHNICALSUPPORT, name, years);
    }

//    @Override
//    public Repository backup(Repository repository) {
//        return new Repository(Repository repository);
//    }

    @Override
    public Repository backup() {
        return null;
    }

    @Override
    public Repository restore() {
        return null;
    }

    @Override
    public void clearRepository() {

    }
}
