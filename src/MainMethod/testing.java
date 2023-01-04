package MainMethod;

import Fasta.FastaReader;
import Fasta.OptimalAlignment;
import Fasta.SNPAlignment;
import Fasta.StandardAlignment;
import Team.*;

import java.util.ArrayList;
import java.util.HashMap;

public class testing {

    public static void main(String[] args) {
        String teamFile = "src/" + args[0];
        String fastaFile = "src/" + args[1];

        TeamReader teamInput = new TeamReader(teamFile);
        ArrayList<TeamMember> team = teamInput.createTeam();

        TeamLead lead = (TeamLead) team.get(0);
        Bioinformatician bioinformatician1 = (Bioinformatician) team.get(1);
        Bioinformatician bioinformatician2 = (Bioinformatician) team.get(2);
        Bioinformatician bioinformatician3 = (Bioinformatician) team.get(3);
        TechnicalSupport techSupport = (TechnicalSupport) team.get(4);

        FastaReader fastaInput = new FastaReader(fastaFile);
        HashMap<String, String> genomes = fastaInput.readFile();

        OptimalAlignment optimal = new OptimalAlignment(genomes);
        SNPAlignment snp = optimal.createSNPAlignment("1993.F1.BR.93.93BR020");

        StandardAlignment alignment1 = new StandardAlignment(genomes, bioinformatician1);
        StandardAlignment alignment2 = new StandardAlignment(genomes, bioinformatician2);

        bioinformatician1.setAlignment(alignment1);
        bioinformatician1.getAlignment().computeAlignmentScore("1993.F1.BR.93.93BR020");
        System.out.println("Score: " + bioinformatician1.getAlignment().getScore());
//        System.out.println(snp.getGenomes());

//        alignment1.removeGenome("1990.U.CD.90.90CD121E12");
//        alignment1.addGenome("test_genomes.fasta");
//        alignment1.removeGenomeList("test_genomes_remove.txt");
//        ArrayList<String> hits = alignment1.genomeSearch("GTCCTGGGG");
//        bioinformatician1.writeAlignment();
    }
}
