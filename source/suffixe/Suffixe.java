package suffixe;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import suffixe.ComparatorSuffixe;
import suffixe.ComparatorSuffixes;
import sequence.Sequence;
import suffixe.Suffixes;

/**
 * Classe Suffixe qui comprend des outils de base pour la recherche de sequence
 * et de compression en se basant sur la BWT notamment. Attention la classe
 * contient des methodes naive qu'il ne vaut mieux pas utiliser.
 * 
 * @author Antoine
 *
 */
public class Suffixe extends Sequence {
	private TreeMap<String, Integer> res;
	private ArrayList<String> listBWT;


	public Suffixe(String f) {
		super(f);
		this.res = new TreeMap<String, Integer>();
		this.listBWT = new ArrayList<String>(2000000);
	}

	/**
	 * Methode qui cherche de maniere naive les positions dans le genome de la
	 * sequence passer en parametre.
	 * 
	 * @param f1
	 *            la sequence a chercher dans le genome.
	 * @return une chaine vide si la sequence n'est pas presente dans le genome
	 *         sinon les positions de debut de la sequence pour chaque
	 *         occurence.
	 */
	public String searchRead(String f1) {
		String res = new String();
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));

		int lengthSeq = f1.length();
		int lengthGen = sequence.length();

		for (int i = 0; i <= (lengthGen - lengthSeq); i++) {
			String seqComp = new String();
			seqComp = seqComp.concat(sequence.substring(i, i + lengthSeq));
			if (f1.equals(seqComp)) {
				res = res.concat(i + " ");
			}

		}
		return res;
	}

	/**
	 * La methode calcule une position de la sous chaine en utilisant la
	 * recherche dichotomique
	 * 
	 * @param f1
	 *            le fichier dont on extrait la sequence du genome
	 * @param seq
	 *            la sequence recherche
	 * @return la position de la sous chaine trouve
	 */
	public String searchReadSA(String f1, String seq) {
		String res = new String();
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));
		ArrayList<Integer> suffixArray = this.createSuffixList(sequence);

		int debut = 0;
		int fin = suffixArray.size() - 1;
		int milieu = debut + fin / 2;
		int n = seq.length();
		boolean found = false;
		do {
			int indice = suffixArray.get(milieu);
			String temp = sequence.substring(indice);
			if (n <= temp.length()) {
				temp = sequence.substring(indice).substring(0, n);
			} else {
				temp = sequence.substring(indice).substring(0);
			}
			if (temp.compareTo(seq) > 0) {
				fin = milieu - 1;
				milieu = ((debut + milieu) / 2);
			}

			else if (temp.compareTo(seq) < 0) {
				debut = milieu + 1;
				milieu = ((debut + fin) / 2);
			} else if (temp.compareTo(seq) == 0) {
				found = true;
				res = res.concat(indice + " ");
			}
		} while (!found && debut <= fin);
		return res;
	}

	/**
	 * Methode qui cree le tableau des suffixes de la sequence presente dans le
	 * fichier de maniere naive. A eviter.
	 * 
	 * @param f
	 *            fichier dont on extrait la sequence d'adn.
	 */
	public void createSuffixeListNaive(String f) {
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));

		int sequenceLength = sequence.length();
		String seqTemp = new String();
		seqTemp = seqTemp.concat(sequence);
		for (int i = 0; i < sequenceLength; i++) {
			this.res.put(seqTemp, i);
			seqTemp = seqTemp.substring(1);
		}
	}

	/**
	 * Methode qui cree la BWT d'une sequence presente dans le fichier f de
	 * maniere naive. A eviter
	 * 
	 * @param f
	 *            fichier dont on extrait la sequence pour laquelle on souhaite
	 *            avoir la BWT
	 * @return Chaine de caractere representant la BWT de la sequence
	 */
	public String createBWTNaive(String f) {

		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));
		sequence = sequence.concat("$");
		int sequenceLength = sequence.length();

		for (int i = 0; i < sequenceLength; i++) {
			String rot = new String();
			rot = rot.concat(sequence.substring(sequenceLength - i - 1));
			rot = rot.concat(sequence.substring(0, sequenceLength - i - 1));
			this.listBWT.add(rot);
		}
		Collections.sort(this.listBWT);

		String BWT = new String();
		for (String seq : this.listBWT) {
			BWT = BWT.concat(seq.substring(sequenceLength - 1));
		}
		return BWT;
	}

	/**
	 * Methode qui inverse la BWT de maniere naive. A eviter
	 * 
	 * @param f
	 *            le fichier dont on extrait la BWT a inverse.
	 */
	public void operateUNBWTNaive(String f) {
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));

		int length = sequence.length();
		ArrayList<String> charBWT = new ArrayList<String>(20000);

		for (int i = 0; i < length; i++) {
			charBWT.add(sequence.substring(i, i + 1));
			this.listBWT.add(sequence.substring(i, i + 1));
		}

		for (int i = 0; i < length - 1; i++) {
			Collections.sort(this.listBWT);
			for (int j = 0; j < length; j++) {
				String temp = new String();
				temp = temp.concat(charBWT.get(j));
				temp = temp.concat(this.listBWT.get(j));
				this.listBWT.remove(j);
				this.listBWT.add(j, temp);
			}
		}
	}

	/**
	 * Methode qui cree le tableau des suffixes de la sequence en param de
	 * maniere efficace.
	 * 
	 * @param seq
	 *            la sequence pour lequel on veut creer le tableau des suffixes.
	 * @return Le tableau des suffixes
	 */
	public ArrayList<Integer> createSuffixList(String seq) {
		ArrayList<Integer> list = new ArrayList<Integer>(seq.length());

		for (int i = 0; i < seq.length(); i++) {
			list.add(i);
		}

		list.sort(new ComparatorSuffixe(seq));
		return list;
	}

	/**
	 * Methode qui cree la BWT d'une sequence en utilisant les correspondance
	 * avec le tableau des suffixes.
	 * 
	 * @param f
	 *            fichier dont on extrait la sequence sur lequel on applique la
	 *            BWT.
	 * @return Une chaine de caractere representant la BWT.
	 */
	public String createBWT(String f) {
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));
		sequence = sequence.concat("$");

		StringBuilder res = new StringBuilder();
		ArrayList<Integer> suffixArray = this.createSuffixList(sequence);

		for (Integer indice : suffixArray) {
			if (indice == 0) {
				res = res.append("$");
			} else {
				res = res.append(sequence.substring(indice - 1, indice));
			}
		}

		return res.toString();
	}

	/**
	 * Calcule le nombre d'occurence de chaque caractere dans la sequence d'ADN
	 * et renvoie une liste contenant les resultat. L'ordre de la liste est le
	 * suivant : #$ puis #A puis #C puis #G puis #T
	 * 
	 * @param seq
	 *            la sequence d'ADN a partir duquel on calcule le nombre
	 *            d'occurence
	 * @return la liste des occurences de chaque caracteres
	 */
	public ArrayList<Integer> calculateOccurence(String seq, int length) {
		ArrayList<Integer> listOccurence = new ArrayList<Integer>(5);
		int nbA = 0, nbC = 0, nbG = 0, nbT = 0, nb$ = 0;
		for (int i = 0; i < length; i++) {
			String x = seq.substring(i, i + 1);
			switch (x) {
			case "$":
				nb$++;
				break;
			case "A":
				nbA++;
				break;
			case "C":
				nbC++;
				break;
			case "G":
				nbG++;
				break;
			case "T":
				nbT++;
				break;
			default:
				System.out.println("Caractere non pris en compte par la methode");
			}
		}
		listOccurence.add(nb$);
		listOccurence.add(nbA);
		listOccurence.add(nbC);
		listOccurence.add(nbG);
		listOccurence.add(nbT);
		return listOccurence;
	}

	/**
	 * Retourne le nombre d'occurence du caractere c dans la s�quence
	 * BWT[0..taille]
	 * 
	 * @param taille
	 *            limite superieure de la borne de recherche (inclus)
	 * @param c
	 *            : le caractere dont on recherche le nombre d'occurence
	 * @return le nombre d'occurence du caractere c
	 */
	public int rank(int taille, String c) {
		String sequence = this.getSequence(0);
		int res = 0;
		ArrayList<Integer> list = this.calculateOccurence(sequence, taille);
		switch (c) {
		case "$":
			res = list.get(0);
			break;
		case "A":
			res = list.get(1);
			break;
		case "C":
			res = list.get(2);
			break;
		case "G":
			res = list.get(3);
			break;
		case "T":
			res = list.get(4);
			break;
		default:
			System.out.println("Caractere non pris en compte par la methode");
		}
		return res;
	}

	/**
	 * Retourne le nombre d'occurence de caractere strictement plus petit que x
	 * dans la sequence
	 * 
	 * @param x
	 *            le caractere dont on veut connaitre le nombre d'occurence plus
	 *            petit
	 * @return le nombre d'occurence des caracteres strictement plus petit que x
	 */
	public int C(String x) {
		String sequence = this.getSequence(0);
		int res = 0;
		ArrayList<Integer> list = this.calculateOccurence(sequence, sequence.length());
		switch (x) {
		case "$":
			break;
		case "A":
			res = list.get(0);
			break;
		case "C":
			res = list.get(0) + list.get(1);
			break;
		case "G":
			res = list.get(0) + list.get(1) + list.get(2);
			break;
		case "T":
			res = list.get(0) + list.get(1) + list.get(2) + list.get(3);
			break;
		default:
			System.out.println("Caractere non pris en compte par la methode");
		}
		return res;
	}

	/**
	 * La methode teste si la chaine seq est pr�sente dans la sequence du
	 * fichier f
	 * 
	 * @param f
	 *            le fichier dont on extrait la chaine du genome
	 * @param seq
	 *            la sequence que l'on recherche
	 * @return un booleen true si la chaine est presente false sinon
	 */
	public boolean rechercheBWT(String f, String seq) {
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));

		int s = 1;
		int t = sequence.length();
		int n = seq.length() - 1;
		for (int i = n; i >= 0; i--) {
			s = C(seq.substring(i, i + 1)) + rank(s - 1, seq.substring(i, i + 1)) + 1;
			t = C(seq.substring(i, i + 1)) + rank(t, seq.substring(i, i + 1));
		}
		return s <= t;
	}

	/**
	 * Methode qui inverse la BWT d'une sequence en utilisant la propriete LF.
	 * 
	 * @param f
	 *            fichier dont on extrait la bwt
	 * @return une chaine de caractere correspondant a la sequence apres
	 *         inversion de la bwt.
	 */
	public String operateUNBWT(String seq) {
		StringBuilder res = new StringBuilder();

		int nbA = 1, nbC = 1, nbG = 1, nbT = 1, nb$ = 1;

		StringBuilder BWT = new StringBuilder(seq);
		
		int n = BWT.length() - 1;

		ArrayList<Suffixes> unsortedList = new ArrayList<Suffixes>(BWT.length());

		for (int i = 0; i < BWT.length(); i++) {
			String x = new String();
			x = BWT.substring(i, i + 1);

			switch (x) {
			case "$":
				Suffixes suff$ = new Suffixes(x, nb$);
				unsortedList.add(suff$);
				nb$++;
				break;
			case "A":
				Suffixes suffA = new Suffixes(x, nbA);
				unsortedList.add(suffA);
				nbA++;
				break;
			case "C":
				Suffixes suffC = new Suffixes(x, nbC);
				unsortedList.add(suffC);
				nbC++;
				break;
			case "G":
				Suffixes suffG = new Suffixes(x, nbG);
				unsortedList.add(suffG);
				nbG++;
				break;
			case "T":
				Suffixes suffT = new Suffixes(x, nbT);
				unsortedList.add(suffT);
				nbT++;
				break;
			default:
				System.out.println("Caractere non pris en compte par la methode");
			}
		}

		ArrayList<Suffixes> sortedList = new ArrayList<Suffixes>(unsortedList);
		sortedList.sort(new ComparatorSuffixes());

		int indice = 0;
		for (int i = 0; i < n; i++) {

			res = res.insert(0, BWT.charAt(indice));
			Suffixes s1 = unsortedList.get(indice);
			int debut, fin, j;
			debut = 0;
			fin = n;
			j = (debut + fin) / 2;

			do {
				int test = sortedList.get(j).compareWith(s1);
				if (test == (1)) {
					fin = j - 1;
					j = (debut + fin) / 2;
				} else if (test == -1) {
					debut = j + 1;
					j = ((debut + fin) / 2);
				}
			} while (sortedList.get(j).compareWith(s1) != 0 && debut <= fin);
			indice = j;
		}
		return res.toString();
	}

	public static String encodeMTF(String msg) {
		StringBuilder output = new StringBuilder();
		StringBuilder s = new StringBuilder("$ACGT");
		for (char c : msg.toCharArray()) {
			int idx = s.indexOf("" + c);
			output = output.append(idx);
			s = s.deleteCharAt(idx).insert(0, c);
		}
		return output.toString();
	}

	public static String decodeMTF(String idxs) {
		StringBuilder output = new StringBuilder();
		StringBuilder s = new StringBuilder("$ACGT");

		for (int i = 0; i < idxs.length(); i++) {
			int idx = Integer.parseInt(idxs.substring(i, i + 1));

			char c = s.charAt(idx);
			output = output.append(c);

			s = s.deleteCharAt(idx).insert(0, c);
		}
		return output.toString();
	}
	  /**
	   * La methode implemente l'algorithme de codage RLE.
	   * Comme on travaille sur des entiers on limite la valeur de l'indice a 9 suivi par l'indice correspondant au caractere associe.
	   * Ensuite on indique le nombre de 0 necessaire. Il y aura une perte sur la compression finale car on aura a representer le caractere 0 dans le codage de Huffman.
	   * @param source une chaine de caractere ayant subi le codage MTF.
	   * @return Une chaine de caractere ayant subi l'encodage RLE.
	   */
	  public static String encodeRLE(String source) {
	        StringBuffer dest = new StringBuffer();
	        for (int i = 0; i < source.length(); i++) {
	            int runLength = 1;
	            int indice = i;
	            while (i+1 < source.length() && '0' == source.charAt(i+1)) {
	                runLength++;
	                i++;
	            }
	            if(runLength>9){
	            	boolean premierIndice = true;
	            	while(runLength>9){
	            		runLength -= 9;
	            		dest.append("9");
	            		if(premierIndice){
	            			dest.append(source.charAt(indice));
	            			premierIndice = false;
	            		} else {
	            			dest.append('0');
	            		}
	            	}
	            	dest.append(runLength);
		            dest.append('0');
	            }
	            else {
	            dest.append(runLength);
	            dest.append(source.charAt(indice));
	            }
	        }
	        return dest.toString();
	    }
	 


	public static String decodeRLE(String source) {
		StringBuffer dest = new StringBuffer();
		int n = source.length();
		for(int i=0; i<n-1; i+=2){
			int nbCaract = Integer.parseInt(source.substring(i,i+1));
			if(nbCaract == 1){
				dest.append(source.charAt(i+1));
			}else{
				dest.append(source.charAt(i+1));
				nbCaract--;
				for(int k = nbCaract; k > 0; k--){
					dest.append('0');
				}
			}
		}
		return dest.toString();
	}

	
	public void ecrireFichier(String nomFile, String seq) {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(nomFile)));

			writer.write(seq);

			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode qui effectue une recherche de sequence naive. On cherche les
	 * positions ou debute f1 dans la sequence obtenu du fichier f.
	 * 
	 * @param f
	 *            fichier dont on extrait la sequence genome
	 * @param f1
	 *            sequence dont on cherche le debut dans f
	 */
	public void searchFastaNaive(String f, String f1) {
		String res = searchRead(f1);
		System.out.println(res);
	}

	/**
	 * Fonction qui affiche le tableau des suffixes de la sequence du fichier en
	 * parametre. Methode naive. Ne pas utiliser.
	 * 
	 * @param f
	 *            le fichier ou on obtient la sequence
	 */
	public void suffixeArrayNaive(String f) {
		this.createSuffixeListNaive(f);
		for (Integer indice : this.res.values()) {
			System.out.print(indice + " ");
		}
	}

	/**
	 * La fonction affiche une position de la sequence recherchee en recherchant
	 * a partir du tableau des suffixes et en utilisant la recherche
	 * dichotomique
	 * 
	 * @param f
	 * @param seq
	 */
	public void searchFastaSa(String f, String seq) {
		String res = this.searchReadSA(f, seq);
		System.out.println(res);
	}

	/**
	 * Fonction qui affiche le tableau des suffixes de la sequence du fichier en
	 * parametre. Methode efficcace.
	 * 
	 * @param f
	 *            le fichier ou on obtient la sequence
	 */
	public void suffixeArray(String f) {
		String sequence = new String();
		sequence = sequence.concat(this.getSequence(0));
		ArrayList<Integer> list = this.createSuffixList(sequence);
		for (Integer indice : list) {
			System.out.print(indice + " ");
		}
	}

	/**
	 * Fonction qui affiche la bwt de la sequence passe en param de maniere
	 * naive. Ne pas utiliser
	 * 
	 * @param f
	 *            la sequence dont on obtient la sequence a transformer
	 */
	public void bwtNaive(String f) {
		String bwt = this.createBWTNaive(f);
		System.out.println(bwt);
	}

	/**
	 * Fonction qui affiche la bwt de la sequence passe en param de maniere
	 * efficace
	 * 
	 * @param f
	 *            la sequence dont on obtient la sequence a transformer
	 */
	public void bwt(String f) {
		String bwt = this.createBWT(f);
		System.out.println(bwt);
	}

	/**
	 * Fonction qui affiche l'inversion de la bwt de maniere naive. Ne pas
	 * utiliser
	 * 
	 * @param f
	 *            le fichier dont on obtient la sequence representant la bwt
	 */
	public void unbwtNaive(String f) {
		this.operateUNBWTNaive(f);
		String word = new String();
		int i = 0;
		while (!(this.listBWT.get(i).endsWith("$"))) {
			i++;
		}
		word = this.listBWT.get(i).substring(0, this.listBWT.size() - 1);
		System.out.println(word);
	}

	/**
	 * Fonction qui affiche l'inversion de la bwt de maniere efficace
	 * 
	 * @param f
	 *            le fichier dont on obtient la sequence representant la bwt
	 */
	public void unbwt(String f) {
		String BWT = new String();
		BWT = BWT.concat(this.getSequence(0));
		String res = this.operateUNBWT(BWT);
		System.out.println(res);
	}

	/**
	 * La methode affiche le resultat de la recherche de la chaine seq dans le
	 * genome issue du fichier f
	 * 
	 * @param f
	 *            le fichier contenant le genome
	 * @param seq
	 *            la sequence que l'on recherche
	 */
	public void matchBWT(String f, String seq) {
		boolean found = this.rechercheBWT(f, seq);
		if (found == true) {
			System.out.println("found");
		} else {
			System.out.println("not found");
		}
	}
	
	public void compressFasta(String f) {
		String nomFichier = f.substring(0, (f.length() - 6));
		nomFichier = nomFichier.concat(".txt");

		String sequence = this.getSequence(0);
		String bwt = createBWT(sequence);
		String mtf = encodeMTF(bwt);
		String rle = encodeRLE(mtf);

		this.ecrireFichier(nomFichier, rle);

	}

	/*
	 * public void decompressFasta(String f, String code){ String nomFile =
	 * f.substring(0, (f.length()-9)); nomFile.concat(".fasta");
	 * 
	 * this.getCodage(code);
	 * 
	 * String seq = this.getSequence(0); String huffman = decodeHuffman(seq,
	 * this.code); String rle = decodeRLE(huffman); String mtf = decodeMTF(rle);
	 * String res = this.operateUNBWT(mtf);
	 * 
	 * this.ecrireFichier(nomFile, seq); }
	 */

	public void decompressFasta(String f) {
		String nomFile = f.substring(0, (f.length() - 4));
		nomFile = nomFile.concat("-bis.fasta");

		String seq = this.getSequence(0);
		String rle = decodeRLE(seq);
		String mtf = decodeMTF(rle);
		String res = this.operateUNBWT(mtf);

		this.ecrireFichier(nomFile, res);
	}
}