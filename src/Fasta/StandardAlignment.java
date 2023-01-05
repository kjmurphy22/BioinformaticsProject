package Fasta;

public class StandardAlignment extends Alignment{

    protected String user;

    public StandardAlignment(OptimalAlignment optimal, String user){
        super(optimal);
        this.user = user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

}
