package MainMethod;

import Fasta.SNPAlignment;
import Reader.FastaReader;
import Fasta.OptimalAlignment;
import Fasta.StandardAlignment;
import Reader.TeamReader;
import Repo.Repository;
import Team.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainMethod {

    public static void main(String[] args) {
        /* Create filepaths for the team and fasta files */
        String teamFile = "src/" + args[0];
        String fastaFile = "src/" + args[1];

        /* Call constructors to create a new TeamReadr object and FastaReader object */
        TeamReader teamInput = new TeamReader(teamFile);
        FastaReader fastaInput = new FastaReader(fastaFile);

        /* Call createTeam to create team from the teamFile */
        ArrayList<TeamMember> team = teamInput.createTeam();

        /*
        Cast subclass type and create references to the
        TeamMember objects created by the createTeam call above
        */
        TeamLead teamLead = (TeamLead) team.get(0);
        Bioinformatician bioinformatician1 = (Bioinformatician) team.get(1);
        Bioinformatician bioinformatician2 = (Bioinformatician) team.get(2);
        Bioinformatician bioinformatician3 = (Bioinformatician) team.get(3);
        TechnicalSupport techSupport = (TechnicalSupport) team.get(4);

        /* Set first genome in fastaFile as reference genome */
        String referenceGenome;
        try (BufferedReader br = new BufferedReader(new FileReader(fastaFile))){
            referenceGenome = br.readLine().substring(1);
            System.out.println("First genome is " + referenceGenome + ". Setting as default reference genome.\n");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* Call readFile to extract genomes from fastaFile */
        HashMap<String, String> genomes = fastaInput.readFile();
        /* Create initial OptimalAlignment object optimal from genomes*/
        OptimalAlignment optimal = new OptimalAlignment(genomes, referenceGenome);
        /* Create initial SNPAlignment object snp from initial optimal alignment */
        SNPAlignment snp = optimal.createSNPAlignment();
//        System.out.println(snp.getGenomes() + "\n");

        /* Create a StandardAlignment object for each bioinformatician in the team and assign their name to it */
        StandardAlignment standardAlignment1 = new StandardAlignment(optimal, bioinformatician1.getName());
        StandardAlignment standardAlignment2 = new StandardAlignment(optimal, bioinformatician2.getName());
        StandardAlignment standardAlignment3 = new StandardAlignment(optimal, bioinformatician3.getName());

        /* Assign each bioinformatician to their respective StandardAlignment object */
        bioinformatician1.setAlignment(standardAlignment1);
        bioinformatician2.setAlignment(standardAlignment2);
        bioinformatician3.setAlignment(standardAlignment3);

        /* Create a HashMap object to store all bioinformatician's names and their StandardAlignment objects */
        HashMap<String, StandardAlignment> userAlignments = new HashMap<String, StandardAlignment>();
        /*
        Set userAlignments object to TeamLead object so TeamLead
        can access all bioinformatician's alignments.
        */
        teamLead.setAssignedBioinformaticans(userAlignments);

        /* Add each bioinformatician to TeamLead's list of bioinformaticians and their alignments */
        userAlignments.put(bioinformatician1.getName(), bioinformatician1.getAlignment());
        userAlignments.put(bioinformatician2.getName(), bioinformatician2.getAlignment());
        userAlignments.put(bioinformatician3.getName(), bioinformatician3.getAlignment());

        /* Create repository based on the initial optimal alignment and assigned bioinformaticians */
        Repository repository = new Repository(optimal, teamLead.getAssignedBioinformaticians());
        /* Create backup repository based on initial repository */
        Repository backup = techSupport.backup(repository);

        /* Search for sequence CCGTGT */
        ArrayList<String> hits = bioinformatician1.genomeSearch("CCGTG");

        /* Remove all genomes containing CCGTGT */
        for (String s: hits){
            bioinformatician1.removeGenome(s);
        }

        /* Replace sequence CCGTGT with CGCGCG in whole alignment */
        bioinformatician2.replaceAllSequences("CCGTGT", "CGCGCG");

        /* Remove genome 2002.A.CD.02.KTB035 */
        bioinformatician3.removeGenome("2002.A.CD.02.KTB035");

        /* Write bioinformatician1's alignment to file */
        bioinformatician1.writeAlignment();
        /* Compute bioinformatician1's alignment score after above changes */
        bioinformatician1.getAlignment().setScore();
        /* Write bioinformatician1's score to file */
        bioinformatician1.writeScore();

        /* Write bioinformatician2's alignment to file */
        bioinformatician2.writeAlignment();
        /* Compute bioinformatician2's alignment score after above changes */
        bioinformatician2.getAlignment().setScore();
        /* Write bioinformatician2's score to file */
        bioinformatician2.writeScore();

        /* Write all current standard alignments to file */
        teamLead.writeAlignment();
        /* Write all current alignment scores to file */
        teamLead.writeScore();

        /* Promote bioinformatician1's alignment to the optimal alignment in the repository */
        teamLead.promote(bioinformatician1, repository);
        /* Overwrite bioinformatician2's alignment with the repository optimal alignment */
        teamLead.overwrite(bioinformatician2, repository);

        /* techSupport clears repository */
        techSupport.clearRepository(repository);

        /* Create new repository from backup repository */
        Repository newRepo = techSupport.restore(backup);

        /* Overwrite bioinformatician2's alignment with the new repository optimal alignment */
        teamLead.overwrite(bioinformatician2, newRepo);

//        bioinformatician1.addGenome("test_genomes.fasta");

//        bioinformatician1.getAlignment().setReferenceGenome("1989.F1.BR.89.BZ163");

//        bioinformatician1.removeGenomeList("test_genomes_remove.txt");

//        System.out.println(fastaInput.checkExtension());

//        Repository backup = techSupport.backup(repository);

//        bioinformatician1.replaceGenomeSequence("TTTCCTGCGGACAG", "TTTCCT________", referenceGenome);

//        bioinformatician1.replaceGenome(bioinformatician1.getAlignment().getReferenceGenome(),"TGTCCTGGGGACAG");
//
//        teamLead.promote(bioinformatician1, repository);

//        bioinformatician1.getAlignment().computeAlignmentScore(referenceGenome);
//
//        teamLead.promote(bioinformatician1, repository);
//
//        teamLead.promote(bioinformatician1, repository);


//        teamLead.writeAllAlignments(repository);

//        bioinformatician2.replaceAllSequences("TGTCCTGGGGACAGACCAACGCTAAA", "__________TEST____________");
//        System.out.println(bioinformatician1.getAlignmentGenomes());
//        System.out.println(bioinformatician2.getAlignmentGenomes());

//        bioinformatician1.removeGenome("1990.U.CD.90.90CD121E12");
//        bioinformatician2.addGenome("test_genomes.fasta");

//        bioinformatician2.removeGenomeList("test_genomes_remove.txt");
//        ArrayList<String> hits = bioinformatician1.genomeSearch("GTCCTGGGG");
//        System.out.println("Hits for GTCCTGGGG: " + hits+"\n*********");
//
//        bioinformatician1.getAlignment().computeAlignmentScore("1993.F1.BR.93.93BR020");
//        System.out.println("Score: " + bioinformatician1.getAlignment().getScore());
//
//        bioinformatician1.writeAlignment();
//        bioinformatician1.writeScore();
//
//        teamLead.overwrite(bioinformatician1, repository);
//
//        Repository backup = techSupport.backup(repository);
//        techSupport.clearRepository(repository);
//        Repository restoredRepository = techSupport.restore(backup);
    }
}
