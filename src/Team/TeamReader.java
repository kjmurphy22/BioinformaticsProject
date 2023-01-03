package Team;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TeamReader {

    String fileName;

    ArrayList<TeamMember> team;
    public TeamReader(String fileName){
        this.fileName = fileName;
        this.team = new ArrayList<TeamMember>();
    }

    public ArrayList<TeamMember> createTeam() {
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
        return team;
    }


//    public static void main(String[] args) {
//        String fileName = "src/team.txt";
//
//        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
//            String name;
//            String role;
//            int yearsExperience;
//            HashMap<String, String> team = new HashMap<>();
//
//            String line = br.readLine();
//            while(line != null){
//                StringTokenizer tokenizer = new StringTokenizer(line);
//                role = tokenizer.nextToken();
//                if (role.toUpperCase().equals("BIOINFORMATICIAN")){
//                    System.out.println(role.toUpperCase());
//                    System.out.println("HIT");
//                }
//                name = tokenizer.nextToken() + " " + tokenizer.nextToken();
//                yearsExperience = Integer.parseInt(tokenizer.nextToken());
//                team.put(name, role);
//                line = br.readLine();
//            }
//            System.out.println(team);
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
