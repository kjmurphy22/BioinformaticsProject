package Team;

public abstract class TeamMember {

    protected String role;
    protected String name;

    public TeamMember(String role, String name){
        this.name = name;
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
