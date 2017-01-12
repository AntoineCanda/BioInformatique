package suffixe;
/**
 * Definition de la classe Suffixes qui definit un suffixes et les methodes de base.
 * @author Antoine
 *
 */
public class Suffixes {
	
	private String caract;
	private int rank;
	
	public Suffixes(String caract, int rank){
		this.caract = caract;
		this.rank = rank;
	}

	/**
	 * Methode qui retourne le caractere (chaine de taille 1) du suffixes : 5 possibilites dans notre programme $, A, C, G et T
	 * @return la chaine de taille 1 representant le caractere
	 */
	public String getCaract(){
		return this.caract;
	}
	
	/**
	 * Methode qui retourne le rank associe au caractere passe en parametre. 
	 * @return int representant le rank du suffixes.
	 */
	public int getRank(){
		return this.rank;
	}
	
	/**
	 * Methode equals qui retourne un booleen definissant legalite entre deux suffixes. Deux suffixes sont egaux si ils ont même caractere et meme rank.
	 * @param Object o representant un suffixes a comparer.
	 * @return boolean true si les suffixes sont egaux, false sinon
	 */
	public boolean equals(Object o){
		if(o instanceof Suffixes){
			Suffixes other = (Suffixes) o;
			return ((this.getCaract().equals(other.getCaract())) && (this.getRank() == other.getRank()));
		}
		else {
			return false;
		}
	}
	
	/**
	 * Methode qui compare deux suffixes et retourne un entier. Si s2 est plus petit que le suffixes sur lequel on applique la methode retourne 1, si ils sont egaux on retourne 0 sinon -1.
	 * @param s2 le suffixes que l'on compare
	 * @return un entier qui sera -1 0 ou 1 en fonction de la comparaison des deux suffixes
	 */
	public int compareWith ( Suffixes s2){
		int res = 0;
		res = this.getCaract().compareTo(s2.getCaract());
		if(res == 0){
			res = this.getRank() < s2.getRank()? -1 : 
				this.getRank() == s2.getRank()? 0 :1;
		}
		else if(res>0){
			res =1;
		}
		else if(res<0){
			res =(-1);
		}

		return res;
	}
}
