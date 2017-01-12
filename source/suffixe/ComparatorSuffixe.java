package suffixe;

import java.util.Comparator;

public class ComparatorSuffixe implements Comparator<Integer>{
	private String seq;
	private int length;
	public ComparatorSuffixe(String seq){
		this.seq = seq;
		this.length = seq.length();
		
	}
	
	/**
	 * La methode compare que l'on a implemente dans le but d'ameliorer la creation du tableau des suffixes
	 * On cree deux prefixes de taille 100 des suffixes que l'on compare ensemble pour commencer. En cas d'egalite on cree les deux suffixes.
	 * La comparaison est une comparaison a partir des indices et une comparaison lexicographique sur les chaines.
	 */
	public int compare(Integer i, Integer j){
		int n = Math.min(length-i, length-j);

		for (int k =0; k<n; k++){
			if( seq.charAt(k+j) < seq.charAt(k+i)){
				return 1;
			} else if (seq.charAt(k+j) > seq.charAt(k+i)){
				return -1;
			}
		}
		return j-i;
	}
}