package Team;

public abstract class TeamMember {

    private RoleType role;
    private String name;
    private int yearsExperience;

    public TeamMember(RoleType role, String name, int yearsExperience){
        this.name = name;
        this.role = role;
        this.yearsExperience = yearsExperience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public RoleType getRole() {
        return role;
    }

    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    public int getYearsExperience() {
        return yearsExperience;
    }
}
