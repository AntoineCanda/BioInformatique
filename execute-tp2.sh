#! /usr/bin/env bash

TEST0=files/test0.fasta
TEST1=files/test1.fasta
TEST2=files/test2.fasta

FILE0=files/phage-lambda.fasta
FILE1=files/ebola-t.fasta
FILE2=files/ebola-z.fasta
FILE3=files/read1.fasta
FILE4=files/read2.fasta

bioseq='java -jar bioseq.jar'

echo "Exemple des fonctions reprenant les exemples de la partie I"
echo 
# Exemple fonction windows
$bioseq "windows" 80 40 $FILE0
read -p "Appuyer sur une touche pour continuer ..."
echo 

# Question 2 : Formule donnant le nombre de fenetres en fonction de la longueur n de la sequence de l la longueur de la fenetre et s le shift.
# J'ai l'impression a premiere vue que la longueur l de la fenetre importe peu c'est le shift ici qui importe car c'est lui qui finalement represente en quelque sorte l'avancement du decoupage de la sequence en fenetre.
# On aurait ainsi (n/s)+1 fenetres. Si on teste avec phage-lambda on a : n = 48 502, l = 80 et s = 40 par exemple : n/s = 1212 + 1 = 1213 fenêtres. Si on a n = 100 et s = 30 on a 100/30 +1 = 4 fenetres (1- l /31 - l+30 / 61 - l + 60 / 91 - 100).
# En revanche sauf coincidence la dernière fenêtre aura une taille < l. 

# Test et analyse de données de la partie I
echo "Test et analyse de données de la partie I"

# Test avec le fichier read1.fa 
echo "Test avec le fichier read1.fa"
# Test avec kmers de taille 4 
echo
echo "Test avec kmers de taille 4 et seuil 0.1 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 4 0.1 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 4 et seuil 0.2 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 4 0.2 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 4 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 4 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 4 et seuil 1.0 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 4 1.0 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 8  
echo
echo "Test avec kmers de taille 8 et seuil 0.1 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 8 0.1 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 8 et seuil 0.2 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 8 0.2 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 8 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 8 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 8 et seuil 1.0 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 8 1.0 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 12
echo
echo "Test avec kmers de taille 12 et seuil 0.1 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.1 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 12 et seuil 0.2 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.2 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 12 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 12 et seuil 1.0 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 12 1.0 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 16  
echo
echo "Test avec kmers de taille 16 et seuil 0.1 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.1 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 16 et seuil 0.2 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.2 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 16 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 16 et seuil 1.0 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 16 1.0 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 20  
echo
echo "Test avec kmers de taille 20 et seuil 0.1 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 20 0.1 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 20 et seuil 0.2 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 20 0.2 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 20 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 20 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 20 et seuil 1.0 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 20 1.0 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec le fichier read2.fa 
echo "Test avec le fichier read2.fa"
# Test avec kmers de taille 4 
echo
echo "Test avec kmers de taille 4 et seuil 0.1 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 4 0.1 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 4 et seuil 0.2 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 4 0.2 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 4 et seuil 0.5 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 4 0.5 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 4 et seuil 1.0 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 4 1.0 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 8  
echo
echo "Test avec kmers de taille 8 et seuil 0.1 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 8 0.1 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 8 et seuil 0.2 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 8 0.2 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 8 et seuil 0.5 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 8 0.5 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 8 et seuil 1.0 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 8 1.0 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 12
echo
echo "Test avec kmers de taille 12 et seuil 0.1 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.1 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 12 et seuil 0.2 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.2 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 12 et seuil 0.5 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.5 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 12 et seuil 1.0 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 12 1.0 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 16  
echo
echo "Test avec kmers de taille 16 et seuil 0.1 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.1 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 16 et seuil 0.2 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.2 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 16 et seuil 0.5 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.5 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 16 et seuil 1.0 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 16 1.0 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 20  
echo
echo "Test avec kmers de taille 20 et seuil 0.1 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 20 0.1 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 20 et seuil 0.2 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 20 0.2 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 20 et seuil 0.5 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 20 0.5 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec kmers de taille 20 et seuil 1.0 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 20 1.0 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Analyse des données de la partie I
# On voit clairement que les kmers de taille 4 n'apporte que très peu d'informations pertinentes et c'etait attendu : il y a trop peu de kmers de taille 4 différents (256) pour ne pas être fort présent même dans des fenêtres de taille 80.
# En revanche a partir de kmers de taille 8: on a pour le fichier read1 que deux windows qui correspondent : celle de 27 841 à 27 920 et celle 27 881 à 27 960. On a donc cette fenêtre de 120 kmers avec plus de 50% de kmers de taille 8 présents.
# Les kmers de taille 12 nous apporte une information supplémentaire: il y a entre 20 et 50% de kmers communs dans cette tranche. Les kmers de taille 16 avec au moins 20% de similitude nous réduise cette fois la fenêtre à 27841 à 27920.
# On voit par la suite que les kmers de taille 20 ont également entre 20 et 50% de similitude. On en déduit donc que dans cette tranche de 80 kmers on a une forte ressemblance avec la sequence de read1. Des kmers de 20 (environ 1000 10^9) sont suffissamment unique 
# (et la taile de la fenêtre suffissamment petite (80)) pour qu'on puisse affirmer que dans ce cas présent c'est vraiment la similitude qui prime.
#
# Pour le fichier read2 on en deduit les faits suivants : 
# Les kmers de taille 4 nous indique à partir de 50% trois zones de ressemblance: 24 201 à 24 280, 28 241 à 28 320 et 34 841 à 34 960. Ce n'est pas suprenant encore une fois. 
# Les kmers de taille 8 réduise à la tranche 3 de 34 841 à 34960 sans en dire plus entre 20 et 50% de ressemblance. Les kmers de taille 12 nous montre qu'il y a entre 10 et 20% de similitude pour cette taille de kmers.
# On a plus de similitude à partir de kmers de taille 16 même à moins de 10%. On en déduit donc que la ressemblance entre phage-lambda et read2 est moins elevé. Il faudrait probablement essayer de jouer avec la taille 
# de la longueur et du switch des fenetres pour affiner les résultats. 
#
# Les fenêtres permettent de pouvoir isoler des zones de similitude entre deux séquences et ainsi pouvoir analyser quelle mutation sont arrivés et pourquoi pas de retracer un peu l'histoire de celles ci. 
# On remarque egalement que le ratio de 1.0 est inutile dans ce cas précis : il est plus intéressant d'avoir des tailles de kmers un peu plus important (notamment que 4 ou 8 si on prend des fenetres de l'ordre des dizaines de milliers de nucleotides (voir même millions)) qu'un ratio elevé.
# On pourrait par exemple mettre 0.8 au lieu de 1.0 pour affiner eventuellement les resultats.
# Des kmers de taille 12 ou 16 commencent à devenir intéressant quand on cherche dans des volumes assez important de données (4^12 ~ 16.7*10^6 possibilités et 4^16~2^32~4*10^9.). 
 
echo
echo "Test list-spaced-kmers"
echo
$bioseq "list-spaced-kmers" "##-##" $TEST1
echo

echo "Les fonctions common-spaced-kmers et ratio-common-spaced-kmers ont ete adaptés."
echo "En revanche quand une graine est utilisé j'ai fais le choix d'utiliser la graine utilisé en parametre sur le fichier 1 mais de calculer le poids de la graine et d'utiliser une graine de poids equivalent 'pleine'"

# Test et analyse de données pour la partie II

echo "Test et analyse de données pour la partie II"
echo 
echo "Creation des fichiers des sequences d'ebola-z aleatoirement mutes avec 0 10 100 1000 et 10000 mutations aleatoires"
echo

$bioseq "random-mutations" 0 $FILE2
$bioseq "random-mutations" 10 $FILE2
$bioseq "random-mutations" 100 $FILE2
$bioseq "random-mutations" 1000 $FILE2
$bioseq "random-mutations" 10000 $FILE2

echo "Test avec graine ####-#### et 0 mutation"
echo
$bioseq "ratio-common-spaced-kmers" "####-####" files/ebola-z-mutated-0.fasta $FILE2
echo 
echo "Test avec graine ######## et 0 mutation"
echo
$bioseq "ratio-common-spaced-kmers" "########" files/ebola-z-mutated-0.fasta $FILE2
echo 

echo "Test avec graine ####-#### et 10 mutations"
echo
$bioseq "ratio-common-spaced-kmers" "####-####" files/ebola-z-mutated-10.fasta $FILE2
echo 
echo "Test avec graine ######## et 10 mutations"
echo
$bioseq "ratio-common-spaced-kmers" "########" files/ebola-z-mutated-10.fasta $FILE2
echo 

echo "Test avec graine ####-#### et 100 mutations "
echo
$bioseq "ratio-common-spaced-kmers" "####-####" files/files/ebola-z-mutated-100.fasta $FILE2
echo 

echo "Test avec graine ######## et 100 mutations "
echo
$bioseq "ratio-common-spaced-kmers" "########" files/ebola-z-mutated-100.fasta $FILE2
echo 

echo "Test avec graine ####-#### et 1000 mutations "
echo
$bioseq "ratio-common-spaced-kmers" "####-####" files/ebola-z-mutated-1000.fasta $FILE2
echo 

echo "Test avec graine ######## et 1000 mutations "
echo
$bioseq "ratio-common-spaced-kmers" "########" files/ebola-z-mutated-1000.fasta $FILE2
echo 

echo "Test avec graine ######## et 10000 mutations"
echo
$bioseq "ratio-common-spaced-kmers" "########" files/ebola-z-mutated-10000.fasta $FILE2
echo 

echo "Test avec graine ####-#### et 10000 mutations"
echo
$bioseq "ratio-common-spaced-kmers" "####-####" files/ebola-z-mutated-10000.fasta $FILE2
echo 

echo "Test avec graine ##-##-##-## et 10000 mutations"
echo
$bioseq "ratio-common-spaced-kmers" "##-##-##-##" files/ebola-z-mutated-10000.fasta $FILE2
echo

echo "Test avec graine #-#-##-##-# et 10000 mutations"
echo
$bioseq "ratio-common-spaced-kmers" "#-#-##-##-#-#" files/ebola-z-mutated-10000.fasta $FILE2
echo

echo "Test avec graine ######## et ebola-t"
echo
$bioseq "ratio-common-spaced-kmers" "########" $FILE1 $FILE2
echo 

echo "Test avec graine ####-#### et ebola-t"
echo
$bioseq "ratio-common-spaced-kmers" "####-####" $FILE1 $FILE2
echo 

echo "Test avec graine ##-##-##-## et ebola-t"
echo
$bioseq "ratio-common-spaced-kmers" "##-##-##-##" $FILE1 $FILE2
echo

echo "Test avec graine #-#-##-##-# et ebola-t"
echo
$bioseq "ratio-common-spaced-kmers" "#-#-##-##-#-#" $FILE1 $FILE2
echo


read -p "Appuyer sur une touche pour continuer ..."

# On remarque que l'on garde des résultats équivalents ou très légèrement supérieur avec les graines espacés que les graines contigues. 
# L'intérêt de ces graines est qu'on base les comparaisons sur certains nucléotides en faisant le choix d'en omettre d'auters. On peut ainsi retrouver des rapports entre séquences 
# alors qu'il y a eu des mutations sur l'une des deux. Les comparaisons entre ebola-t et ebola-z mettent déjà plus en valeur l'intérêt des graines espacées et on peut evoquer l'hypothèse
# que l'intérêt des graines espacés concernent pas seulement des mutations de type substutions comme on a crée mais aussi des mutations de type addition ou deletion de nucleotides comme 
# ça se passe dans la "realité".

# Test et analyse de données partie II - 2
echo "Test et analyse de données partie II - 2"
echo 
echo "Je n'ai repris que quelques exemples permettant d'illustrer le benefice que peut apporter les graines espacées."
echo

# Test avec kmers de taille 12

echo
echo "Test avec kmers de taille 12 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec graines espacées de taille 12 et seuil 0.5 avec read1.fa"
$bioseq "mapper-windows-spaced-kmers" 80 40 "####-####-####" 0.5 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."


# Test avec kmers de taille 16  

echo
echo "Test avec kmers de taille 16 et seuil 0.375 avec read1.fa"
$bioseq "mapper-windows-kmers" 80 40 16 0.375 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec graine espacée de taille 16 et seuil 0.375 avec read1.fa"
$bioseq "mapper-windows-spaced-kmers" 80 40 "####-####-####-####" 0.375 $FILE3 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

# Test avec kmers de taille 12

echo
echo "Test avec kmers de taille 12 et seuil 0.125 avec read2.fa"
$bioseq "mapper-windows-kmers" 80 40 12 0.125 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

echo
echo "Test avec graines espacées de taille 12 et seuil 0.125 avec read2.fa"
$bioseq "mapper-windows-spaced-kmers" 80 40 "###-###-###-###" 0.125 $FILE4 $FILE0
echo
read -p "Appuyer sur une touche pour continuer ..."

#Analyse:
# On remarque que les graines espacées nous permettent de gagner en précision dans les retours des fenêtres. On peut gagner quelques % de précisions de manières plus ou moins 
# importantes (ici test avec des kmers de taille 12 et 16) où on a des résultats grâce aux graines espacée que l'on n'obtenait pas avec des graines contigues.
# Ici les résultats sont faibles car on ne recherche que dans des séquences assez restreintes des fenêtres de taille elles aussi faible mais l'intérêt est plus marqués si jamais
# la recherche se fait dans des séquences bien plus importantes en taille. Plus les séquences sont de tailles importantes plus on aura de chance d'avoir des résultats similaire qui
# ne seront pas forcément ceux souhaités comme on a pu voir en utilisant des kmers de petites tailles c'est à dire simplement parce qu'on a tellement de possibilités qu'on couvre une 
# part des kmers possibles non négligeables. 
# Les graines espacées permettent de pouvoir avoir un joker finalement sur quelques nucléotides qui ont été mutées et trouver ainsi des rapports entre séquences plus facilement.
# Une autre remarque a été que des graines formées de manière cyclique (ex : ##-##-##-## ou ####-####) donnent généralement de meilleurs résultats que des graines faites complétement 
# aléatoirement.

############################################################# 
echo "Je n'ai pas fait la partie 3 mais j'ai répondu à la question bonus: le nombre de graines de poids k et taille max z est egale a coefficient binomial (z-1,k-1). Plus d'information dans le script."
# Question 10 (Bonus) 
# Calcule nombre de graines de poids k et longueur maximale z.
# J'ai trouvé par experience qu'on pouvait obtenir ce resultat en suivant le schema suivant: 
# On a forcement sur nos graines la position 0 et z-1 qui sont des #. Il reste donc k-2 # a positionne dans la graine. Appellons n le nombre de # restant a placé. 
# On a (z - k + 1) tailles de graines et pour chaque tailles de graines on a l la taille de la graine et t = l-2 places disponible pour placer les # restants. 
# En utilisant donc la formule du coefficient binomial avec c(t,n) on a le nombre de graines possibles pour la taille donnée.
# Le nombre de graines possibles pour un poids et une longueur maximale données est donc la somme des coefficients binomiaux du nombre de graines possible par taille.
# Exemple pour k = 3 et z = 5. On a 5 - 3 + 1 = 3 tailles possibles de graines (ici de tailles 3, 4 et 5). On a donc pour t=3 1 place disponible pour placer notre # = on a donc C(1,1) = 1.
# Pour t = 4, on a 2 places possibles et toujours # à placer donc C(2,1) = 2. 
# Pour t = 5, on a 3 places disponibles et 1 # à placer donc C(3,1) = 3. 
# On a donc 1 + 2 + 3 = 6 graines différentes possibles de poids = 3 et de longueurs maximales 5. 
# J'ai vérifié également de manière manuel ce protocole pour k=4 et z=7 : j'ai obtenu 20 graines différentes (C(2,2) + C(3,2) + C(4,2)+ C(5,2) = 1 + 3 + 6 + 10 = 20). 
# La somme est egale à C(z-1,k-1).

$SHELL
