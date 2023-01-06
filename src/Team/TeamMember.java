package Team;

/*
TeamMember is an abstract class used to represent members of the team. It has three private variables
role is the role of the team member.
name is the first and second name of the team member.
yearsExperience is the years of experience of the team member.
*/

public abstract class TeamMember {

    private RoleType role;
    private String name;
    private int yearsExperience;

    /*
    The only methods unique to the TeamMember class are its constructor and its getters and setters.
    */

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
