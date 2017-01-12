package sequence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * La classe Sequence contient les methodes qui cree les sequences de
 * nucleotides.
 * 
 * @author Antoine
 *
 */
public class Sequence {

	private ArrayList<String> listID;
	private ArrayList<String> listSequence;

	public Sequence(String file) {
		this.listID = new ArrayList<String>(50);
		this.listSequence = new ArrayList<String>(50);
		this.createSequences(file);
	}

	public Sequence() {
		this.listID = new ArrayList<String>(50);
		this.listSequence = new ArrayList<String>(50);
	}

	/**
	 * Methode qui a partir d'un fichier cree les sequences d'adn et les identifiants associés dans les listes adéquates 
	 * @param file le fichier contenant l'identifiant et la sequence souhaite
	 */
	public void createSequences(String file) {
		File f = new File(file);
		FileReader freader;
		String sequence = "";
		String identifiant = "";
		int idFini = 0; // Variable permettant de mieux gerer un fichier contenant plusieurs sequences 
	
		try {
			freader = new FileReader(f);
			BufferedReader buffer = new BufferedReader(freader);
			String tmp = new String();

			while ((tmp = buffer.readLine()) != null) {
				if (tmp.length() > 0) {
					if (tmp.charAt(0) == '>') {
						if(idFini == 1){ // Indique qu'on a traite une sequence precedemment : on l'ajoute a sa liste et on recree un mot vide pour la suivante
							this.listSequence.add(sequence);
							sequence = "";
							idFini = 0; // On traite l'identifiant qui est sur une ligne : on repasse idFini a 0
						}
						identifiant = identifiant.concat(tmp.substring(1));
					}
					else if(tmp.charAt(0)== ' '){

					}
					else {
						if (idFini == 0){
							idFini = 1; // On traite la sequence d'ADN : on ajoute l'identifiant fini dans sa liste et on recree un mot vide
							this.listID.add(identifiant);
							identifiant = "";
						}
						sequence = sequence.concat(tmp);
					}
				}
			}
			this.listSequence.add(sequence); // On finit le fichier sur une sequence : il faut l'ajouter a sa liste !
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}

	/**
	 * Methode qui retourne la liste contenant les sequences d'adn
	 * 
	 * @return la liste contenant les sequences d'adn
	 */
	public ArrayList<String> getListSequence() {
		return this.listSequence;
	}

	/**
	 * Methode qui retourne la liste contenant les identifiants des sequences
	 * 
	 * @return la liste contenant les identifiants des sequences d'adn
	 */
	public ArrayList<String> getListIdentifiant() {
		return this.listID;
	}

	/**
	 * Methode qui retourne la sequence d'adn souhaite
	 * 
	 * @param pos
	 *            l'indice dans la liste de la sequence souhaite. Commence a 0.
	 * @return la sequence voulue sous la forme d'un String
	 */
	public String getSequence(int pos) {
		String res = new String();
		if (this.listSequence.size() > pos) {
			res = res.concat(this.listSequence.get(pos));
		}
		return res.toUpperCase();
	}

	/**
	 * Methode qui retourne l'identifiant de la sequence d'adn souhaite
	 * 
	 * @param pos
	 *            l'indice dans la liste de l'identifiant de la sequence
	 *            souhaite. Commence a 0.
	 * @return l'identifiant de la sequence voulue sous la forme d'un String
	 */
	public String getIdentifiant(int pos) {
		String res = new String();
		if (this.listID.size() > pos) {
			res = res.concat(this.listID.get(pos));
		}
		return res;
	}
}
