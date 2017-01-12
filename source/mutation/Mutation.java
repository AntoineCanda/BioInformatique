package mutation;
/**
 * La classe mutation simule n mutations et cree un fichier contenant la sequence mutee
 * @author Antoine
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import sequence.Sequence;

public class Mutation extends Sequence {

	private String id;
	private String nomFichier;

	public Mutation(String f) {
		super(f);
		this.id = new String(">");
		this.nomFichier = new String();
	}

	/**
	 * Fonction prenant un nucleotide c et le change aleatoirement dans un des 3 autres nucleotides possible
	 * @param c le nucleotide a changer
	 * @return un caractere correspondant au nucleotide apres mutation
	 */
	public char mutationNucleotide(char c) {
		Random rand = new Random();
		int tmp = rand.nextInt(2);
		char mutation = ' ';

		switch (c) {
		case 'G':
			char tabG[] = { 'A', 'T', 'C' };
			mutation = tabG[tmp];
			break;
		case 'C':
			char tabC[] = { 'A', 'T', 'G' };
			mutation = tabC[tmp];
			break;
		case 'A':
			char tabA[] = { 'T', 'G', 'C' };
			mutation = tabA[tmp];
			break;
		case 'T':
			char tabT[] = { 'A', 'G', 'C' };
			mutation = tabT[tmp];
			break;
		}

		return mutation;
	}

	/**
	 * Methode qui effectue un nombre de mutation egale a numberMutation a des positions aleatoires 
	 * @param file le fichier dont on extrait la chaine sur lequel on souhaite effectuer des mutations
	 * @param numberMutation le nombre de mutation a effectuer
	 * @return une chaine de caractere correspondant a la sequence apres avoir subi les mutations 
	 */
	public String mutationSequence(String file, int numberMutation) {
		Random rand = new Random();
		String seq = new String();
		seq = this.getSequence(0);
		this.id = id.concat(this.getIdentifiant(0)); // On en profite pour extraire l'identifiant pour la copier dans le nouveau fichier
		char[] sequence = seq.toCharArray();

		for (int i = 0; i < numberMutation; i++) {
			int nucleotideToChange = rand.nextInt(seq.length() - 1);
			sequence[nucleotideToChange] = mutationNucleotide(seq.charAt(nucleotideToChange));
		}
		return (new String(sequence));
	}

	/**
	 * Fonction qui gere la creation du fichier contenant la sequence mut�e
	 * @param nomFile le nom du fichier a creer
	 * @param identifiant la chaine correspondant a la ligne de l'identifiant
	 * @param seqMut la chaine correspondant a la sequence mut�e
	 */
	public void ecrireFichier(String nomFile, String identifiant, String seqMut) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFile)));

			writer.write(identifiant);
			writer.newLine();
			writer.write(seqMut);

			writer.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Methode qui va mettre en forme le nom du fichier et l'identifiant
	 * @param file le nom du fichier contenant la sequence initiale
	 * @param numberMutation le nombre de mutation a effectuer
	 */
	public void formatStringFichier(String file, int numberMutation){
		this.id = id.concat("-mutated");
		this.nomFichier = file.substring(0, (file.length() - 6));
		this.nomFichier = nomFichier.concat("-mutated-");
		this.nomFichier = nomFichier.concat(String.valueOf(numberMutation));
		this.nomFichier = nomFichier.concat(".fasta");
	}

	/**
	 * Methode qui affiche la sequence mut�e et creer le fichier contenant l'identifiant et la sequence mut�e.
	 * @param file
	 * @param numberMutation
	 */
	public void randomMutations(String file, int numberMutation) {
		String res = new String();
		res = this.mutationSequence(file, numberMutation);
		this.formatStringFichier(file, numberMutation);

	
		this.ecrireFichier(this.nomFichier, this.id, res);
		System.out.println(res);
	}

}
