package bioseq;
/**
 * Classe contenant la fonction main du programme
 * @author Antoine
 */

import fasta.Fasta;
import suffixe.Huffman;
import suffixe.Suffixe;
import mutation.Mutation;
import windows.Windows;
import graines.Graines;

public class Bioseq {

	public static void main(String[] args) {
		String usage = new String();
		usage = "|---------------------------------------------------------------\n|Erreur\n|Usage: - args[0] correspond a la fonction souhaite\n|args[1] a args[n] aux arguments de args[0]\n|---------------------------------------------------------------\n|Fonction disponibles:\n|---------------------------------------------------------------\n|Fonctions sur fichier Fasta basique:\n|---------------------------------------------------------------\n|print-fasta-sequences <genome.fasta>\n";
		usage = usage.concat("|print-fasta-stats <genome.fasta>\n|---------------------------------------------------------------\n|Fonctions sur les graines et kmers:\n|---------------------------------------------------------------\n|list-kmers <taille kmers> <genome.fasta>\n|common-kmers <taille kmers> <genome1.fasta> <genome2.fasta>\n");
		usage = usage.concat("|ratio-common-kmers <taille kmers> <genome1.fasta> <genome2.fasta>\n|random-mutations <nbre mutations> <genome a muter>\n|list-spaced-kmers <graine> <genome1.fasta> \n" );
		usage = usage.concat("|common-spaced-kmers <graine> <genome1.fasta> <genome2.fasta>\n|ratio-common-spaced-kmers <graine> <genome1.fasta> <genome2.fasta>\n|windows <longueur fenetre> <pas fenetre> <genome.fasta>\n");
		usage = usage.concat("|mapper-windows-kmers <longueur fenetre> <pas fenetre> <graine> <ratio minimale> <genome1.fasta> <genome2.fasta>\n|best-matches <nombre de matches a afficher> <graine> <read.fasta> <Germline.fasta>\n");
		usage = usage.concat("|VJDiscover <graine> <germlineV.fasta> <germlineJ.fasta> <reads.fasta>\n|---------------------------------------------------------------\n|Fonction utilisant les suffixes:\n|---------------------------------------------------------------\n|Version naive:\n|---------------------------------------------------------------\n|search-fasta-naive <genome.fasta> <sequence a chercher>\n|suffix-array-naive <genome.fasta>\n|bwt-naive <genome.fasta> \n|unbwt-naive <genome.bwt>\n");
		usage = usage.concat("|---------------------------------------------------------------\n|Eviter les methodes xxx-naive, preferez les methodes:\n|---------------------------------------------------------------\n|suffix-array <genome.fasta>\n|bwt <genome.fasta>\n|unbwt <genome.bwt>\n|match-bwt <genome.bwt> <sequence a chercher>\n|---------------------------------------------------------------\n");
		switch (args[0]) {
		case "print-fasta-sequences":
			Fasta fas = new Fasta(args[1]);
			fas.printFastaSequences(args[1]);
			break;
		case "print-fasta-stats":
			Fasta fas1 = new Fasta(args[1]);
			fas1.printFastaStats(args[1]);
			break;
		case "list-kmers":
			Graines graines1 = new Graines();
			graines1.listKmers(args[2], Integer.parseInt(args[1]));
			break;
		case "common-kmers":
			Graines graines2 = new Graines();
			graines2.commonKmers(args[2], args[3], Integer.parseInt(args[1]));
			break;
		case "ratio-common-kmers":
			Graines graines3 = new Graines();
			graines3.ratioCommonKmers(args[2], args[3], Integer.parseInt(args[1]));
			break;
		case "random-mutations":
			Mutation mutation = new Mutation(args[2]);
			mutation.randomMutations(args[2], Integer.parseInt(args[1]));
			break;
		case "list-spaced-kmers":
			Graines graine1 = new Graines();
			graine1.listSpacedKmers(args[2], args[1]);
			break;
		case "common-spaced-kmers":
			Graines graine2 = new Graines();
			graine2.commonSpacedKmers(args[2], args[3], args[1]);
			break;
		case "ratio-common-spaced-kmers":
			Graines graine3 = new Graines();
			graine3.ratioCommonSpacedKmers(args[2], args[3], args[1]);
			break;
		case "windows":
			Windows win1 = new Windows(args[3]);
			win1.windows(Integer.parseInt(args[1]),Integer.parseInt(args[2]) , args[3]);
			break;
		case "mapper-windows-kmers":
			Graines graine4 = new Graines();
			graine4.mapperWindowsKmers(Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]),Double.parseDouble(args[4]), args[5], args[6]);
			break;
		case "mapper-windows-spaced-kmers":
			Graines graine5 = new Graines();
			graine5.mapperWindowsSpacedKmers(Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3],Double.parseDouble(args[4]), args[5], args[6]);
			break;
		case "best-matches":
			Graines graine6 = new Graines();
			graine6.bestMatches(Integer.parseInt(args[1]),args[2],args[3],args[4]);
			break; 
		case "VJ-discover":
			Graines graine7 = new Graines();
			graine7.VJDiscover(args[1],args[2],args[3], args[4]);
			break; 
		case "search-fasta-naive":
			Suffixe suffixe = new Suffixe(args[1]);
			suffixe.searchFastaNaive(args[1], args[2]);
			break;
		case "suffix-array-naive":
			Suffixe suffixe1 = new Suffixe(args[1]);
			suffixe1.suffixeArrayNaive(args[1]);
			break;
		case "search-fasta-sa":
			Suffixe suffixe2 = new Suffixe(args[1]);
			suffixe2.searchFastaSa(args[1], args[2]);
			break;
		case "bwt-naive":
			Suffixe suffixe3 = new Suffixe(args[1]);
			suffixe3.bwtNaive(args[1]);
			break;
		case "unbwt-naive":
			Suffixe suffixe4 = new Suffixe(args[1]);
			suffixe4.unbwtNaive(args[1]);
			break;
		case "suffix-array":
			Suffixe suffixe5 = new Suffixe(args[1]);
			suffixe5.suffixeArray(args[1]);
			break;
		case "bwt":
			Suffixe suffixe6 = new Suffixe(args[1]);
			suffixe6.bwt(args[1]);
			break;
		case "unbwt":
			Suffixe suffixe7 = new Suffixe(args[1]);
			suffixe7.unbwt(args[1]);
			break;
		case "match-bwt":
			Suffixe suffixe8 = new Suffixe(args[1]);
			suffixe8.matchBWT(args[1], args[2]);
			break;
		case "compress-fasta":
			Suffixe suffixe9 = new Suffixe(args[1]);
			suffixe9.compressFasta(args[1]);
			break;
		case "decompress-fasta":
			Suffixe suffixe10 = new Suffixe(args[1]);
			suffixe10.decompressFasta(args[1]);
			break;
		case "compress-huffman":
			Huffman.compress();
			break;
		case "decompress-huffman":
			Huffman.expand();
			break;
		default:
			System.out.println(usage);
		}

	}

}
