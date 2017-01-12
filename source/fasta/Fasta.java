package fasta;
import sequence.Sequence;

public class Fasta extends Sequence {

	public Fasta(String f) {
		super(f);
	}
	
	/**
	 * Methode qui affiche les sequences d'ADN du fichier f
	 * @param f le fichier dont on extrait la sequence
	 */
	public void printFastaSequences(String f) {
		for(String seq: this.getListSequence()){
			System.out.println(seq+"\n");
		}
	}
	
	/**
	 * Methode qui affiche les identifiant et la longueur des sequences d'ADN  
	 * @param f le fichier dont on extrait la sequence
	 */
	public void printFastaStats(String f) {
		for(int i=0; i < this.getListIdentifiant().size(); i++){
			System.out.println("L'identifiant de la sequence est " + this.getListIdentifiant().get(i)+". La sequence a une longueur de "+ this.getListSequence().get(i).length()+ " nucleotides.\n");
		}	
	}
	
}
