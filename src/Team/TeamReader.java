package Team;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static Team.Bioinformatician.getNumberOfBioinformaticians;
import static Team.TeamLead.getNumberOfTeamLeads;
import static Team.TechnicalSupport.getNumberOfTechnicalSupports;

public class TeamReader {

    String fileName;

    ArrayList<TeamMember> team;
    public TeamReader(String fileName){
        this.fileName = fileName;
        this.team = new ArrayList<TeamMember>();
    }

    public ArrayList<TeamMember> createTeam() {
        System.out.println("\nCreating team...\n");
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String name;
            String role;
            int yearsExperience;

            String line = br.readLine();
            while(line != null){
                StringTokenizer tokenizer = new StringTokenizer(line);
                role = tokenizer.nextToken();
                RoleType teamRole = RoleType.valueOf(role.toUpperCase());
                name = tokenizer.nextToken() + " " + tokenizer.nextToken();
                yearsExperience = Integer.parseInt(tokenizer.nextToken());
                switch (teamRole){
                    case TEAMLEAD -> {
                        System.out.println("Creating user: " + name + "\nRole: " + role + "\nYears experience: " + yearsExperience);
                        TeamLead leader = new TeamLead(name, yearsExperience);
                        team.add(leader);
                        System.out.println("Finished creating user: " + name +"\n");
                    }
                    case BIOINFORMATICIAN -> {
                        System.out.println("Creating user: " + name + "\nRole: " + role + "\nYears experience: " + yearsExperience);
                        Bioinformatician bioinformatician = new Bioinformatician(name, yearsExperience);
                        team.add(bioinformatician);
                        System.out.println("Finished creating user: " + name +"\n");
                    }
                    case TECHNICALSUPPORT -> {
                        System.out.println("Creating user: " + name + "\nRole: " + role + "\nYears experience: " + yearsExperience);
                        TechnicalSupport tech = new TechnicalSupport(name, yearsExperience);
                        team.add(tech);
                        System.out.println("Finished creating user: " + name +"\n");
                    }
                }
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int teamLeads = getNumberOfTeamLeads();
        int bioinfo = getNumberOfBioinformaticians();
        int tech = getNumberOfTechnicalSupports();
        System.out.println("Finished creating team.\nTeam consists of:\n" + teamLeads + " team lead(s)\n" +
                bioinfo + " bioinformatician(s)\n" + tech + " technical support(s)\n");
        return team;
    }
}
