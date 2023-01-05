package MainMethod;

import Fasta.FastaReader;
import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;
import Fasta.StandardAlignment;
import Repo.Repository;
import Team.*;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.System.identityHashCode;

public class testing {

    public static void main(String[] args) {
        String teamFile = "src/" + args[0];
        String fastaFile = "src/" + args[1];

        TeamReader teamInput = new TeamReader(teamFile);
        ArrayList<TeamMember> team = teamInput.createTeam();

        TeamLead teamLead = (TeamLead) team.get(0);
        Bioinformatician bioinformatician1 = (Bioinformatician) team.get(1);
        Bioinformatician bioinformatician2 = (Bioinformatician) team.get(2);
        Bioinformatician bioinformatician3 = (Bioinformatician) team.get(3);
        TechnicalSupport techSupport = (TechnicalSupport) team.get(4);

        FastaReader fastaInput = new FastaReader(fastaFile);
        HashMap<String, String> genomes = fastaInput.readFile();

        OptimalAlignment optimal = new OptimalAlignment(genomes);
        SNPAlignment snp = optimal.createSNPAlignment("1993.F1.BR.93.93BR020");

        StandardAlignment standardAlignment1 = new StandardAlignment(optimal, bioinformatician1.getName());
        StandardAlignment standardAlignment2 = new StandardAlignment(optimal, bioinformatician2.getName());
        bioinformatician1.setAlignment(standardAlignment1);
        bioinformatician2.setAlignment(standardAlignment2);

        HashMap<Bioinformatician, StandardAlignment> userAlignments = new HashMap<Bioinformatician, StandardAlignment>();
        userAlignments.put(bioinformatician1, bioinformatician1.getAlignment());
        userAlignments.put(bioinformatician2, bioinformatician2.getAlignment());

        Repository repository = new Repository(optimal, snp, userAlignments);

        System.out.println("*********\n");
        System.out.println(standardAlignment1.getClass());
        System.out.println(standardAlignment2.getClass());
        System.out.println("********\n");
        System.out.println(bioinformatician1.getAlignment().getGenomes().getClass());
        System.out.println(bioinformatician2.getAlignment().getGenomes().getClass());

        System.out.println("#########\nHash code 1: " + identityHashCode(bioinformatician1.getAlignment().getGenomes()));
        System.out.println("Hash code 2: " + identityHashCode(bioinformatician2.getAlignment().getGenomes()));

//        System.out.println("Start: " + standardAlignment1.getGenomes()+"\n*********");
//        bioinformatician1.removeGenome("1990.U.CD.90.90CD121E12");
//        System.out.println("********\n");
//        System.out.println(bioinformatician1.getAlignment().getGenomes());
//        System.out.println(bioinformatician2.getAlignment().getGenomes());
//        System.out.println(optimal.getGenomes());
//        standardAlignment1.addGenome("test_genomes.fasta");
//        System.out.println("Removing: " + standardAlignment1.getGenomes()+"\n*********");
//        System.out.println("Adding: " + standardAlignment1.getGenomes()+"\n*********");
//        standardAlignment1.removeGenomeList("test_genomes_remove.txt");
//        System.out.println("Removing list: " + standardAlignment1.getGenomes()+"\n*********");
//        ArrayList<String> hits = standardAlignment1.genomeSearch("GTCCTGGGG");
//        System.out.println("Hits for GTCCTGGGG: " + hits+"\n*********");
//        teamLead.overwrite(bioinformatician1, repository);
//        Repository backup = techSupport.backup(repository);
//        techSupport.clearRepository(repository);
//        Repository newRepo = techSupport.restore(backup);

//        bioinformatician1.getAlignment().computeAlignmentScore("1993.F1.BR.93.93BR020");


//        System.out.println("Score: " + bioinformatician1.getAlignment().getScore());
//        System.out.println(snp.getGenomes());

//        bioinformatician1.writeAlignment();
    }
}
