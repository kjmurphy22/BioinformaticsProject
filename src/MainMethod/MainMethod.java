package MainMethod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class MainMethod {

    public static void main(String[] args) {
        for (String files : args){
            System.out.println("Filename: " + files);
        }
    }
}
