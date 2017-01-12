package suffixe;

import java.util.Comparator;
import suffixe.Suffixes;

public class ComparatorSuffixes implements Comparator<Suffixes>{
	
	/**
	 * On definie une fonction de comparaison des suffixes. Deux suffixes sont egaux si ils ont un meme caractere et meme rank.
	 */
	@Override
	public int compare(Suffixes s1, Suffixes s2){
		int res = 0;
		res = s1.getCaract().compareTo(s2.getCaract());
		if(res == 0){
			res = s1.getRank() < s2.getRank()? -1 : 
				s1.getRank() == s2.getRank()? 0 :1;
		}

		return res;
	}
}