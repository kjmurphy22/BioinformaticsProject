package Team;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ReadTeamFile {

    public ReadTeamFile(String fileName){

    }

    public static void main(String[] args) {
        String fileName = "src/team.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            String name;
            String role;
            HashMap<String, String> team = new HashMap<>();

            String line = br.readLine();
            while(line != null){
                StringTokenizer tokenizer = new StringTokenizer(line);
                role = tokenizer.nextToken();
                name = tokenizer.nextToken() + " " + tokenizer.nextToken();
                team.put(name, role);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
