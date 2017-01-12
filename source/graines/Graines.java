package graines;

/**
 * La classe represente les fonctions de base sur les kmers en utilisant le concept de graines
 * @author Antoine 
 */
import java.util.*;
import sequence.Sequence;
import windows.Windows;


public class Graines extends Windows {
	private ArrayList<ArrayList<String>> ssq1;
	private ArrayList<ArrayList<String>> ssq2;
	private ArrayList<ArrayList<String>> ssq3;
	private ArrayList<ArrayList<ArrayList<String>>> res;
	private ArrayList<ArrayList<ArrayList<String>>> res2;

	public Graines() {
		super();
		ssq1 = new ArrayList<ArrayList<String>>(50);
		ssq2 = new ArrayList<ArrayList<String>>(50);
		ssq3 = new ArrayList<ArrayList<String>>(50);
		res = new ArrayList<ArrayList<ArrayList<String>>>(50);
		res2 = new ArrayList<ArrayList<ArrayList<String>>>(50);
	}

	/**
	 * La fonction cree une graine contigue de la taille passee en param
	 * 
	 * @param taille
	 *            la taille de la graine
	 * @return un String representant la graine contigue de la taille voulue
	 */
	public String createGraine(int taille) {
		String graine = new String();
		for (int i = 0; i < taille; i++) {
			graine = graine.concat("#");
		}
		return graine;
	}

	/*
	 * Le principe est le suivant : On obtient la taille du kmers en calculant
	 * la longueur de la graine. On utilise une boucle for de l'indice 0 a
	 * l'indice = taille de la sequence - taille graine +1. On utilise la
	 * methode subString commencant a l'indice i de la boucle et pour les j
	 * caracteres la suivant (j = taille graine) on test si le caractere
	 * correpondant de la graine = '#' ou pas si oui on ajoute a la sous chaine
	 * sinon non. On ajoute la sequence obtenue dans une liste.
	 */

	/**
	 * Methode qui cree les sous sequences d'une sequence donn�e � partir de la
	 * graine format
	 * 
	 * @param sequence
	 *            la sequence d'adn que l'on souhaite traite
	 * @param format
	 *            la graine formate que l'on souhaite utilise
	 * @return une liste contenant les sous sequences d'adn
	 */
	public ArrayList<String> traitementSubSequence(String sequence,
			String format) {
		int taille = format.length();
		ArrayList<String> resultat = new ArrayList<String>(50000);
		for (int i = 0; i < (sequence.length() - taille + 1); i++) {
			String subseq = new String();
			for (int j = 0; j < taille; j++) {
				if (format.charAt(j) == '#') {
					subseq = subseq.concat(String.valueOf(sequence
							.charAt(i + j)));
				} else if (format.charAt(j) != '#') {
					subseq = subseq.concat("");
				}
			}
			resultat.add(subseq);
		}
		return resultat;
	}

	/**
	 * La fonction creer les sous sequences des differentes sequences d'adn d'un
	 * fichier
	 * 
	 * @param f1
	 *            le fichier dont on tire la sequence d'adn
	 * @param format
	 *            la graine representant un kmer
	 * @return une liste de liste de kmers
	 */
	public ArrayList<ArrayList<String>> createSubSequenceGraines(String f1,
			String format) {
		String sequence = new String();
		ArrayList<ArrayList<String>> listResultat = new ArrayList<ArrayList<String>>(
				50);

		if (!f1.endsWith(".fasta")) {

			sequence = sequence.concat(f1);
			listResultat.add(this.traitementSubSequence(sequence, format));
		}

		else if (f1.endsWith(".fasta")) {

			Sequence seq = new Sequence(f1);

			for (int pos = 0; pos < seq.getListSequence().size(); pos++) {

				sequence = seq.getSequence(pos);
				listResultat.add(this.traitementSubSequence(sequence, format));
			}
		}
		return listResultat;
	}

	/*
	 * Fonction qui recree une graine de meme taille que la graine en parametre
	 * sans les jokers Cette fonction a ete cree uniquement pour assouvir ma
	 * curioiste en souhaitant compare des graines contigues et non contigue
	 * 
	 * public String createGraineBis(String graine){ String graineBis = new
	 * String(); int poidsGraine = 0; for(int i=0; i<graine.length(); i++){ if
	 * (graine.charAt(i)== '#'){ poidsGraine++; } } for(int i=0; i<poidsGraine;
	 * i++){ graineBis = graineBis.concat("#"); } return graineBis; }
	 */
	/**
	 * La fonction initialise les deux listes de kmers des fichiers pour
	 * preparer le traitement
	 * 
	 * @param f1
	 *            fichier 1 dont on extrait la sequence 1
	 * @param f2
	 *            fichier 2 dont on extrait la sequence 2
	 * @param graine
	 *            presentant le format des kmers
	 */
	public void initializeGraine(String f1, String f2, String graine) {
		// String graineBis = this.createGraineBis(graine);
		if (this.ssq1.isEmpty()) {
			this.ssq1 = this.createSubSequenceGraines(f1, graine);
		}
		if (this.ssq2.isEmpty()) {
			// this.ssq2 = this.createSubSequenceGraines(f2, graineBis);
			this.ssq2 = this.createSubSequenceGraines(f2, graine);
		}
	}

	public void initializeGraine(String f1, String f2, String f3, String graine) {
		// String graineBis = this.createGraineBis(graine);
		if (this.ssq1.isEmpty()) {
			this.ssq1 = this.createSubSequenceGraines(f1, graine);
		}
		if (this.ssq2.isEmpty()) {
			// this.ssq2 = this.createSubSequenceGraines(f2, graineBis);
			this.ssq2 = this.createSubSequenceGraines(f2, graine);
		}
		if (this.ssq3.isEmpty()) {
			// this.ssq3 = this.createSubSequenceGraines(f3, graineBis);
			this.ssq3 = this.createSubSequenceGraines(f3, graine);
		}
	}

	/**
	 * La fonction compare les kmers des deux sequences obtenues et ajoute les
	 * communs a la liste res
	 * 
	 * @param f1
	 *            fichier1 dont on extrait la sequence 1
	 * @param f2
	 *            fichier2 dont on extrait la sequence 2
	 * @param graine
	 *            le format de la graine
	 */
	public void compareKmersGraines(String f1, String f2, String graine) {
		this.initializeGraine(f1, f2, graine);
		for (int i = 0; i < this.ssq1.size(); i++) {
			ArrayList<String> subList1 = this.ssq1.get(i);
			ArrayList<ArrayList<String>> subRes = new ArrayList<ArrayList<String>>(
					50);

			for (int j = 0; j < this.ssq2.size(); j++) {
				ArrayList<String> subList2 = this.ssq2.get(j);
				ArrayList<String> subSubRes = new ArrayList<String>(50000);

				for (String str : subList1) {
					if (subList2.contains(str)) {
						subSubRes.add(str);
					}
				}
				subRes.add(subSubRes);
			}
			this.res.add(subRes);
		}
	}

	public void compareKmersGraines(String f1, String f2, String f3,
			String graine) {
		this.initializeGraine(f1, f2, f3, graine);
		for (int i = 0; i < this.ssq1.size(); i++) {
			ArrayList<String> subList1 = this.ssq1.get(i);
			ArrayList<ArrayList<String>> subRes = new ArrayList<ArrayList<String>>(
					50);

			for (int j = 0; j < this.ssq2.size(); j++) {
				ArrayList<String> subList2 = this.ssq2.get(j);
				ArrayList<String> subSubRes = new ArrayList<String>(50000);

				for (String str : subList1) {
					if (subList2.contains(str)) {
						subSubRes.add(str);
					}
				}
				subRes.add(subSubRes);
			}
			this.res.add(subRes);
		}
		for (int i = 0; i < this.ssq1.size(); i++) {
			ArrayList<String> subList1 = this.ssq1.get(i);
			ArrayList<ArrayList<String>> subRes = new ArrayList<ArrayList<String>>(
					50);

			for (int j = 0; j < this.ssq3.size(); j++) {
				ArrayList<String> subList3 = this.ssq3.get(j);
				ArrayList<String> subSubRes = new ArrayList<String>(50000);

				for (String str : subList1) {
					if (subList3.contains(str)) {
						subSubRes.add(str);
					}
				}
				subRes.add(subSubRes);
			}
			this.res2.add(subRes);
		}
	}

	/**
	 * Calculate the ratio of kmers between two given sequences
	 * 
	 * @param n
	 *            index of the sequence of the first file
	 * @param m
	 *            index of the sequence of the second file
	 * @return the ratio : a double
	 */
	public double calculateRatio(int n, int m) {
		double ratio = (double) this.res.get(n).get(m).size()
				/ this.ssq1.get(n).size();
		return ratio;
	}

	/**
	 * la fonction cree une liste qu'elle renvoye avec les fenetres dont le
	 * ratio de kmers moyen obtenus via la graine est superieur au ratio passe
	 * pour les 2 fichiers
	 * 
	 * @param lw
	 *            entier representant la longueur de la fenetre
	 * @param sw
	 *            entier representant le shift de la fenetre
	 * @param graine
	 *            format de la graines pour obtenir les kmers
	 * @param rk
	 *            double representant le ratio minimale de kmers que la fenetre
	 *            doit avoir pour etre conserve
	 * @param f1
	 *            fichier dont on extrait la premiere sequence
	 * @param f2
	 *            fichier dont on extrait la seconde sequence
	 * @return ArrayList<ArrayList<String>> une liste de liste de fenetre
	 */
	public ArrayList<ArrayList<String>> calculateMapperWindows(int lw, int sw,
			String graine, double rk, String f1, String f2) {
		Windows windows = new Windows(f2);
		ArrayList<ArrayList<String>> listResWin = new ArrayList<ArrayList<String>>(
				50);

		for (int pos = 0; pos < windows.getListSequence().size(); pos++) {
			ArrayList<String> winList = windows.createWindows(lw, sw, f2, pos);
			ArrayList<String> resWin = new ArrayList<String>(2000);
			for (int i = 0; i < winList.size(); i++) {
				String realWin = this.getWindows(winList.get(i));
				this.compareKmersGraines(f1, realWin, graine);
				double ratioWin = this.calculateRatio(0, 0);
				if (ratioWin >= rk) {
					resWin.add(winList.get(i));
				}
				this.ssq2.clear();
				this.res.clear();
			}
			listResWin.add(resWin);
		}
		return listResWin;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(
			Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public ArrayList<String> calculateMatches(String graine, String f1,
			String f2, int pos) {

		Sequence seq = new Sequence(f2);
		ArrayList<String> listMatch = new ArrayList<String>(50);
		Map<String, Integer> hm = new HashMap<String, Integer>(this.ssq2.size());

		for (int i = 0; i < this.ssq2.size(); i++) {
			hm.put(seq.getIdentifiant(i), this.res.get(pos).get(i).size());
		}
		hm = Graines.sortByValue(hm);

		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			String res = new String();
			res = res.concat(entry.getKey() + " " + entry.getValue());
			listMatch.add(res);
		}
		return listMatch;
	}

	public ArrayList<String> calculateMatchesBis(String graine, String f1,
			String f2, int pos) {

		Sequence seq = new Sequence(f2);
		ArrayList<String> listMatch = new ArrayList<String>(50);
		Map<String, Integer> hm = new HashMap<String, Integer>(this.ssq3.size());

		for (int i = 0; i < this.ssq3.size(); i++) {
			hm.put(seq.getIdentifiant(i), this.res2.get(pos).get(i).size());
		}
		hm = Graines.sortByValue(hm);

		for (Map.Entry<String, Integer> entry : hm.entrySet()) {
			String res = new String();
			res = res.concat(entry.getKey() + " " + entry.getValue());
			listMatch.add(res);
		}
		return listMatch;
	}

	public ArrayList<String> calculateVJDiscover(String graine, String f1,
			String f2, String f3) {
		Sequence seq = new Sequence(f1);
		ArrayList<String> listRes = new ArrayList<String>(50);
		this.compareKmersGraines(f1, f2, f3, graine);
		for (int i = 0; i < seq.getListIdentifiant().size(); i++) {
			String res = new String();

			res = res.concat(seq.getIdentifiant(i) + "\t");
			String TRGV = new String();
			TRGV = TRGV.concat(this.calculateMatches(graine, f1, f2, i).get(0));
			int indexTRGV = TRGV.indexOf(' ');
			res = res.concat(TRGV.substring(0, indexTRGV) + "\t");
			String TRGJ = new String();
			TRGJ = TRGJ.concat(this.calculateMatchesBis(graine, f1, f3, i).get(
					0));
			int indexTRGJ = TRGJ.indexOf(' ');
			res = res.concat(TRGJ.substring(0, indexTRGJ) + "\t");

			listRes.add(res);
		}

		return listRes;
	}

	/**
	 * La methode qui affiche la liste des kmers de la sequence
	 * 
	 * @param f
	 *            le fichier dont on extrait la sequence
	 * @param n
	 *            la taille des kmers souhaites
	 */

	public void listKmers(String f, int taille) {
		String graine = this.createGraine(taille);
		ArrayList<ArrayList<String>> sseq = this.createSubSequenceGraines(f,
				graine);
		for (ArrayList<String> listRes : sseq) {
			for (String res : listRes) {
				System.out.println(res);
			}
			System.out.println("\n");
		}
	}

	/**
	 * methode qui va afficher les kmers communs entre les deux sequences
	 * 
	 * @param f1
	 *            le fichier dont on extrait la premiere sequence
	 * @param f2
	 *            le fichier dont on extrait la seconde sequence
	 * @param taille
	 *            la taille des kmers souhaites
	 */
	public void commonKmers(String f1, String f2, int taille) {
		String graine = this.createGraine(taille);

		this.compareKmersGraines(f1, f2, graine);

		for (ArrayList<ArrayList<String>> listListRes : this.res) {
			for (ArrayList<String> listRes : listListRes) {
				for (String res : listRes) {
					System.out.println(res);
				}
				System.out.println("\n");
			}
			System.out.println("\n");
		}
	}

	/**
	 * methode qui va afficher le ratio des kmers communs entre les differentes
	 * sequences Elle retourne la liste de liste des ratios
	 * 
	 * @param f1
	 *            le fichier dont on extrait la premiere sequence
	 * @param f2
	 *            le fichier dont on extrait la seconde sequence
	 * @param taille
	 *            la taille des kmers souhaites
	 * @return La liste de listes des ratios obtenus
	 */
	public ArrayList<ArrayList<Double>> ratioCommonKmers(String f1, String f2,
			int taille) {
		String graine = this.createGraine(taille);
		ArrayList<ArrayList<Double>> listListRatio = new ArrayList<ArrayList<Double>>(
				50);
		this.compareKmersGraines(f1, f2, graine);
		for (int i = 0; i < this.ssq1.size(); i++) {
			ArrayList<Double> listRatio = new ArrayList<Double>(50);
			for (int j = 0; j < this.ssq2.size(); j++) {
				double ratio = calculateRatio(i, j);
				System.out.println("Ratio de la sequence " + i
						+ " du fichier 1 et de la sequence " + j
						+ " du fichier 2 : " + ratio);
				listRatio.add(ratio);
			}
			System.out.println("\n");
			listListRatio.add(listRatio);
		}
		return listListRatio;
	}

	/**
	 * La methode qui gere l'affichage des kmers obtenus d'apres le format de la
	 * graine
	 * 
	 * @param f
	 *            le fichier dont on souhaite obtenir la sequence d'ADN a
	 *            traiter
	 * @param graine
	 *            le format des kmers
	 */
	public void listSpacedKmers(String f, String graine) {
		ArrayList<ArrayList<String>> sseq = this.createSubSequenceGraines(f,
				graine);
		for (ArrayList<String> listRes : sseq) {
			for (String res : listRes) {
				System.out.println(res);
			}
			System.out.println("\n");
		}
	}

	/**
	 * La methode qui gere l'affichage des kmers en commun entre les deux
	 * sequences
	 * 
	 * @param f1
	 *            le fichier 1 dont on obtient la sequence d'ADN 1
	 * @param f2
	 *            le fichier 2 dont on obtient la sequence d'ADN 2
	 * @param graine
	 *            le format des kmers
	 */
	public void commonSpacedKmers(String f1, String f2, String graine) {
		this.compareKmersGraines(f1, f2, graine);
		for (ArrayList<ArrayList<String>> listListRes : this.res) {
			for (ArrayList<String> listRes : listListRes) {
				for (String res : listRes) {
					System.out.println(res);
				}
				System.out.println("\n");
			}
			System.out.println("\n");
		}
	}

	/**
	 * La methode affiche le ratio obtenu entre les deux sequences pour un
	 * format de kmers donne.
	 * 
	 * @param f1
	 *            le fichier 1 dont on obtient la sequence d'ADN 1
	 * @param f2
	 *            le fichier 2 dont on obtient la sequence d'ADN 2
	 * @param graine
	 *            le format des kmers
	 */
	public ArrayList<ArrayList<Double>> ratioCommonSpacedKmers(String f1,
			String f2, String graine) {
		ArrayList<ArrayList<Double>> listListRatio = new ArrayList<ArrayList<Double>>(
				50);
		this.compareKmersGraines(f1, f2, graine);
		for (int i = 0; i < this.ssq1.size(); i++) {
			ArrayList<Double> listRatio = new ArrayList<Double>(50);
			for (int j = 0; j < this.ssq2.size(); j++) {
				double ratio = calculateRatio(i, j);
				System.out.println("Ratio de la sequence " + i
						+ " du fichier 1 et de la sequence " + j
						+ " du fichier 2 : " + ratio);
				listRatio.add(ratio);
			}
			System.out.println("\n");
			listListRatio.add(listRatio);
		}
		return listListRatio;
	}

	/**
	 * la fonction affiche les fenetres dont le ratio de kmers de taille lk est
	 * superieur au ratio passe pour les 2 fichiers
	 * 
	 * @param lw
	 *            entier representant la longueur de la fenetre
	 * @param sw
	 *            entier representant le shift de la fenetre
	 * @param lk
	 *            entier representant la taille des kmers
	 * @param rk
	 *            double representant le ratio minimale de kmers que la fenetre
	 *            doit avoir pour etre conserve
	 * @param f1
	 *            fichier dont on extrait la premiere sequence
	 * @param f2
	 *            fichier dont on extrait la seconde sequence
	 */
	public void mapperWindowsKmers(int lw, int sw, int lk, double rk,
			String f1, String f2) {
		String graine = createGraine(lk);
		ArrayList<ArrayList<String>> resWin = this.calculateMapperWindows(lw,
				sw, graine, rk, f1, f2);
		for (ArrayList<String> listResWin : resWin) {
			for (String win : listResWin) {
				System.out.println(win);
			}
			System.out.println("\n");
		}
	}

	/**
	 * la fonction affiche les fenetres dont le ratio de kmers moyen obtenus via
	 * la graine est superieur au ratio passe pour les 2 fichiers
	 * 
	 * @param lw
	 *            entier representant la longueur de la fenetre
	 * @param sw
	 *            entier representant le shift de la fenetre
	 * @param graine
	 *            format de la graines pour obtenir les kmers
	 * @param rk
	 *            double representant le ratio minimale de kmers que la fenetre
	 *            doit avoir pour etre conserve
	 * @param f1
	 *            fichier dont on extrait la premiere sequence
	 * @param f2
	 *            fichier dont on extrait la seconde sequence
	 */
	public void mapperWindowsSpacedKmers(int lw, int sw, String graine,
			double rk, String f1, String f2) {
		ArrayList<ArrayList<String>> resWin = this.calculateMapperWindows(lw,
				sw, graine, rk, f1, f2);
		for (ArrayList<String> listResWin : resWin) {
			for (String win : listResWin) {
				System.out.println(win);
			}
			System.out.println("\n");
		}
	}

	/**
	 * 
	 * @param n
	 * @param graine
	 * @param f1
	 * @param f2
	 */
	public void bestMatches(int n, String graine, String f1, String f2) {
		this.compareKmersGraines(f1, f2, graine);
		ArrayList<String> listMatch = this.calculateMatches(graine, f1, f2, 0);

		for (int i = 0; i < n; i++) {
			System.out.println(listMatch.get(i));
		}

	}

	/**
	 * La fonction VJDiscover affiche la meilleure sequence V et la meilleure
	 * sequence J pour chaque read du fichier 1
	 * 
	 * @param graine
	 *            le format de la graine utilise pour les comparaisons de
	 *            sequences
	 * @param f1
	 *            le fichier reads contenant les sequences que l'on souhaite
	 *            analyses
	 * @param f2
	 *            le fichier contenant les sequences de V
	 * @param f3
	 *            le fichier contenant les sequences de J
	 */
	public void VJDiscover(String graine, String f1, String f2, String f3) {
		ArrayList<String> listRes = this
				.calculateVJDiscover(graine, f1, f2, f3);
		for (String res : listRes) {
			System.out.println(res);
		}
	}

}
