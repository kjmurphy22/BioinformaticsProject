package Reader;

import Interfaces.Readable;
import Team.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import static Team.Bioinformatician.getNumberOfBioinformaticians;
import static Team.TeamLead.getNumberOfTeamLeads;
import static Team.TechnicalSupport.getNumberOfTechnicalSupports;

/*
TeamReader class is used to read in .txt files. It has two private final variables.
fileName is a string containing the name of the .txt file to be read.
team is an ArrayList<TeamMember> object that will contain the TeamMember classes created
by the createTeam method.
*/
public class TeamReader implements Readable {

    private final String fileName;

    private final ArrayList<TeamMember> team;

    /*
    Class constructor takes one argument fileName and creates TeamReader object with fileName
    and team (null) variables
    */
    public TeamReader(String fileName){
        this.fileName = fileName;
        this.team = new ArrayList<TeamMember>();
    }

    /* Readable method implementation. Called in readFile to check if fileName has a .txt extension */
    @Override
    public boolean checkExtension(){
        String extension = fileName.substring(fileName.length()-4);
        return extension.equals(".txt");
    }

    /*
    The most important method available to TeamReader is createTeam. This method attempts to read in the file fileName
    line by line. It uses a tokenizer object to extract the role, name and yearsExperience for each person listed in
    fileName.
    */
    public ArrayList<TeamMember> createTeam() {
        if (!checkExtension()){
            System.out.println("File does not have .txt extension. Aborting read process.");
            return null;
        }
        System.out.println("\nCreating team...\n");
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String name;
            String role;
            int yearsExperience;

            String line = br.readLine();
            while(line != null){
                StringTokenizer tokenizer = new StringTokenizer(line);
                /* First token is role */
                role = tokenizer.nextToken();
                /* Need to use toUpperCase for enum */
                RoleType teamRole = RoleType.valueOf(role.toUpperCase());
                /* Next two tokens are first and second name */
                name = tokenizer.nextToken() + " " + tokenizer.nextToken();
                /* Next token is years of experience (integer value). Parsed with parseInt */
                yearsExperience = Integer.parseInt(tokenizer.nextToken());
                /*
                teamRole is used to match enum types in RoleType to create the corresponding class. The switch is
                used to create classes based on RoleType and add them to the team variable.
                */
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
                /* After the class for the line has been made, go to next line */
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* These static methods allow the printing of the number of each role created. */
        int teamLeads = getNumberOfTeamLeads();
        int bioinfo = getNumberOfBioinformaticians();
        int tech = getNumberOfTechnicalSupports();
        System.out.println("Finished creating team.\nTeam consists of:\n" + teamLeads + " team lead(s)\n" +
                bioinfo + " bioinformatician(s)\n" + tech + " technical support(s)\n");
        return team;
    }
}
