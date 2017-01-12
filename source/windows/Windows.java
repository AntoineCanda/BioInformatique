package windows;

import java.util.ArrayList;
import sequence.Sequence;


/**
 * la classe windows cree des fenetres afin de faciliter la recherche de
 * sequence dans le genome
 * 
 * @author Antoine
 * 
 */
public class Windows extends Sequence {

	private ArrayList<String> windows;

	public Windows(String f) {
		super(f);
		this.windows = new ArrayList<String>(2000);
	}
	
	public Windows(){
		super();
		this.windows = new ArrayList<String>();
	}

	/**
	 * La fonction cree une liste de fenetre qu'elle retourne 
	 * @param l la taille d'une fenetre
	 * @param p le pas a appliquer enter deux fenetres
	 * @param f le fichier sur lequel on applique la methode
	 * @param pos la sequence que l'on traite
	 * @return 
	 * 		une liste de fenetre de la taille associee
	 */
	public ArrayList<String> createWindows(int l, int p, String f, int pos) {
		String sequence = this.getSequence(pos);
		int i = 0;
		while (i < (sequence.length() / p)) {
			String win = new String();
			win = win.concat(String.valueOf((1 + i * p)));
			win = win.concat("  ");
			if ((p * i + l) < sequence.length()) {
				win = win.concat(sequence.substring(p * i, l + p * i));
				win = win.concat("  ");
				win = win.concat(String.valueOf((l + p * i)));
			} else {
				win = win.concat(sequence.substring((p * i)));
				win = win.concat("  ");
				win = win.concat(String.valueOf(sequence.length()));
			}
			this.windows.add(win);
			i++;
		}
		return this.windows;
	}
	
	/**
	 * La methode recupere la sequences d'ADN de la fenetre en retirant la position de la fenetre dans la sequence globale 
	 * @param seq la fenetre de base 
	 * @return la fenetre sans les positions
	 */
	public String getWindows(String seq){
		String res = new String();
		int indexBegin = 0;
		int indexEnd = seq.length()-1;
		while(seq.charAt(indexBegin) != ' '){
			indexBegin++;
		}
		indexBegin += 2;
		while(seq.charAt(indexEnd) != ' '){
			indexEnd--;
		}
		indexEnd -= 1;
		res = res.concat(seq.substring(indexBegin,indexEnd));
		return res;
	}

	/**
	 * Affiche les fenetre avec les positions du fichier pass� en parametre de la taille souhait�s 
	 * @param l la longueur de la fenetre
	 * @param p le pas � appliquer
	 * @param file le fichier dont on extrait la sequence
	 */
	public void windows(int l, int p, String file) {
		for(int i=0; i < getListSequence().size();i++){
			this.createWindows(l, p, file, i);
			for (String win : this.windows) {
				System.out.println(win);
			}
			this.windows.clear();
		}
	}
	
}
