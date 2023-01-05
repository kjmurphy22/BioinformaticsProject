package MainMethod;

import Fasta.FastaReader;
import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;
import Fasta.StandardAlignment;
import Repo.Repository;
import Team.*;

import java.util.ArrayList;
import java.util.HashMap;

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

        teamLead.writeAllAlignments(repository);

//        bioinformatician2.addGenome("test_genomes.fasta");
//        bioinformatician1.removeGenome("1990.U.CD.90.90CD121E12");
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
