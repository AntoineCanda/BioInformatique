#! /usr/bin/env bash

TEST0=files/test0.fasta
TEST1=files/test1.fasta
TEST2=files/test2.fasta

FILE0=files/phage-lambda.fasta
FILE1=files/ebola-t.fasta
FILE2=files/ebola-z.fasta

bioseq='java -jar bioseq.jar'

echo "Exemple des fonctions reprenant les exemples de la partie I"
echo 
# Exemple fonction print-fasta-sequences
$bioseq "print-fasta-sequences" $TEST0
echo 
# Exemple fonction print-fasta-stats
$bioseq "print-fasta-stats" $TEST0
echo 
echo "Commande de la partie Test et analyse de donnees de la partie I"
echo 
# Commande Test et analyse de donnees partie I
$bioseq "print-fasta-stats" $FILE0
echo 
$bioseq "print-fasta-stats" $FILE2
echo 
$bioseq "print-fasta-stats" $FILE1
echo 

# Les valeurs obtenus sont bien celles presentes dans le fichier. 
# On remarque egalement que les identifiants des sequences sont bien imprimes et qu'ils sont tous formes de manieres similaires selon un protocole precis.

read -p "Appuyer sur une touche pour continuer ..."

echo 
echo "Exemple des fonctions de la partie II "
echo 
# Exemple fonction list-kmers
$bioseq "list-kmers" 4 $TEST1
echo 
# Exemple fonction common-kmers 
$bioseq "common-kmers" 4 $TEST2 $TEST1
echo 
# Exemple fonction ratio-common-kmers
$bioseq "ratio-common-kmers" 4 $TEST2 $TEST1
echo 
read -p "Appuyer sur une touche pour continuer ..."

echo 
echo "Commande de la partie Test et analyse de données de la partie II"
# Commande Test et analyse de donnees partie II
echo 
echo "Ratio entre ebola-z et phage-lambda avec respectivement des kmers de taille 4, 8, 12, 16 et 20 nucleotides."
echo 
$bioseq "ratio-common-kmers" 4 $FILE2 $FILE0
echo 
$bioseq "ratio-common-kmers" 8 $FILE2 $FILE0
echo 
$bioseq "ratio-common-kmers" 12 $FILE2 $FILE0
echo 
$bioseq "ratio-common-kmers" 16 $FILE2 $FILE0
echo 
$bioseq "ratio-common-kmers" 20 $FILE2 $FILE0
echo 
echo "Ratio entre ebola-z et ebola-t avec respectivement des kmers de taille 4, 8, 12, 16 et 20 nucleotides."
echo 
$bioseq "ratio-common-kmers" 4 $FILE2 $FILE1
echo 
$bioseq "ratio-common-kmers" 8 $FILE2 $FILE1
echo 
$bioseq "ratio-common-kmers" 12 $FILE2 $FILE1
echo 
$bioseq "ratio-common-kmers" 16 $FILE2 $FILE1
echo 
$bioseq "ratio-common-kmers" 20 $FILE2 $FILE1

# On remarque dans les deux cas que plus la taille des kmers est grande plus le ratio entre les kmers des deux séquences sera faible. 
# Si on regarde plus en détail on s'apercoit qu'avec des kmers de taille 4 on a un ratio de 1.0 : on retrouve tout les kmers de la séquence au moins une fois dans la séquence 2.
# En revanche quand on prend une taille un peu plus grande : par exemple 8 : on a des différences plus marqués ! Entre ebola-z et phage-lambda on a plus qu'environ 51% des kmers communs. 
# Entre ebola-z et ebola-t on a à ce stade seulement 38% environ. Donc on pourrait supposer que ebola-z est plus proche de phage-lambda que de ebola-t ce qui n'est pas à priori ce qu'on pourrait penser.
# On teste donc avec des kmers de taille 12 et là les résultats sont très intéressants : on a 0.3% de kmers en commun entre ebola-z et phage-lambda alors qu'entre ebola-z et ebola-t on est tout de même à 2.8% environ.
# Si on prend des kmers de taille 16 et 20: on a 0% en commun entre ebola-z et phage-lambda alors qu'entre ebola-z et ebola-t on a 1.2% et 0.6% de kmers commun. On se rend compte de plusieurs choses ici :
# On peut supposer que ebola-z et ebola-t sont plus proche que ebola-z et phage-lambda car avec des suites de 20 nucleotides on a deux fois plus de kmers commun que ebola-z et phage-lambda n'en possede avec une taille de 8.
# Des kmers plus petits donnent du coup plus de kmers probablement dupliques, et donnent des ratios eleves, avec une perte de differenciation et donc d'information sur par exemple la filiation entre deux sequences analysees.
# A contrario des sequences plus importantes auront des valeurs plus faible parcequ'il a plus de possibilite de tomber sur un kmers unique du fait du nombre de kmers diffents possible. On a en effet 256 kmers de taille 4 differents
# alors que pour une taille 8 on en a 65 536. Des tailles de kmers plus grandes pourront egalement servir a d'autres analyses plus interessantes sur les mutations qui se sont produites en analysant par exemple le nombre de nucleotides commun entre 
# deux kmers donnes...
# En revanche si on doit stocker les kmers le gros inconvenient d'avoir des kmers de grande taille est la taille nécessaire pour stocker l'information qui peut exploser. 
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
#Exemple fonction random-mutations 
echo "Exemple de la fonction random-mutations. Attention un seul fichier peut etre creer pour un nombre de mutation donnees, si jamais on repete l'operation le premier fichier est ecrase."
echo 
$bioseq "random-mutations" 2 $TEST1
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
# Commande Test et analyse de donnees partie III
# Creation des sequences du virus ebola avec n mutations aleatoires 
echo "Creation des sequences mutes a partir de ebola-z"
echo 
$bioseq "random-mutations" 0 $FILE2
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
$bioseq "random-mutations" 10 $FILE2
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
$bioseq "random-mutations" 100 $FILE2
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
$bioseq "random-mutations" 1000 $FILE2
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
$bioseq "random-mutations" 10000 $FILE2
echo 
read -p "Appuyer sur une touche pour continuer ..."
echo 
# Comparaison avec des kmers de taille k entre les virus mutes et le virus ebola-z

# virus avec 0 mutations et kmers de taille 4 8 12 16 et 20
echo "ratio entre le virus ebola avec 0 mutation et le virus d'origine pour des kmers de taille 4" 
echo 
$bioseq "ratio-common-kmers" 4 "files/ebola-z-mutated-0.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 0 mutation et le virus d'origine pour des kmers de taille 8" 
echo 
$bioseq "ratio-common-kmers" 8 "files/ebola-z-mutated-0.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 0 mutation et le virus d'origine pour des kmers de taille 12" 
echo 
$bioseq "ratio-common-kmers" 12 "files/ebola-z-mutated-0.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 0 mutation et le virus d'origine pour des kmers de taille 16" 
echo 
$bioseq "ratio-common-kmers" 16 "files/ebola-z-mutated-0.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 0 mutation et le virus d'origine pour des kmers de taille 20" 
echo 
$bioseq "ratio-common-kmers" 20 "files/ebola-z-mutated-0.fasta" $FILE2
echo 

# virus avec 10 mutations et kmers de taille 4 8 12 16 et 20 
echo "ratio entre le virus ebola avec 10 mutation et le virus d'origine pour des kmers de taille 4" 
echo 
$bioseq "ratio-common-kmers" 4 "files/ebola-z-mutated-10.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10 mutation et le virus d'origine pour des kmers de taille 8" 
echo 
$bioseq "ratio-common-kmers" 8 "files/ebola-z-mutated-10.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10 mutation et le virus d'origine pour des kmers de taille 12" 
echo 
$bioseq "ratio-common-kmers" 12 "files/ebola-z-mutated-10.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10 mutation et le virus d'origine pour des kmers de taille 16" 
echo 
$bioseq "ratio-common-kmers" 16 "files/ebola-z-mutated-10.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10 mutation et le virus d'origine pour des kmers de taille 20" 
echo 
$bioseq "ratio-common-kmers" 20 "files/ebola-z-mutated-10.fasta" $FILE2
echo 
# virus avec 100 mutations et kmers de taille 4 8 12 16 et 20 
echo "ratio entre le virus ebola avec 100 mutation et le virus d'origine pour des kmers de taille 4" 
echo 
$bioseq "ratio-common-kmers" 4 "files/ebola-z-mutated-100.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 100 mutation et le virus d'origine pour des kmers de taille 8" 
echo 
$bioseq "ratio-common-kmers" 8 "files/ebola-z-mutated-100.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 100 mutation et le virus d'origine pour des kmers de taille 12" 
echo 
$bioseq "ratio-common-kmers" 12 "files/ebola-z-mutated-100.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 100 mutation et le virus d'origine pour des kmers de taille 16" 
echo 
$bioseq "ratio-common-kmers" 16 "files/ebola-z-mutated-100.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 100 mutation et le virus d'origine pour des kmers de taille 20" 
echo 
$bioseq "ratio-common-kmers" 20 "files/ebola-z-mutated-100.fasta" $FILE2
echo 
# virus avec 1000 mutations et kmers de taille 4 8 12 16 et 20 
echo "ratio entre le virus ebola avec 1000 mutation et le virus d'origine pour des kmers de taille 4" 
echo 
$bioseq "ratio-common-kmers" 4 "files/ebola-z-mutated-1000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 1000 mutation et le virus d'origine pour des kmers de taille 8" 
echo 
$bioseq "ratio-common-kmers" 8 "files/ebola-z-mutated-1000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 1000 mutation et le virus d'origine pour des kmers de taille 12 " 
echo 
$bioseq "ratio-common-kmers" 12 "files/ebola-z-mutated-1000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 1000 mutation et le virus d'origine pour des kmers de taille 16" 
echo 
$bioseq "ratio-common-kmers" 16 "files/ebola-z-mutated-1000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 1000 mutation et le virus d'origine pour des kmers de taille 20" 
echo 
$bioseq "ratio-common-kmers" 20 "files/ebola-z-mutated-1000.fasta" $FILE2
echo 

# virus avec 10000 mutations et kmers de taille 4 8 12 16 et 20 
echo "ratio entre le virus ebola avec 10000 mutation et le virus d'origine pour des kmers de taille 4" 
echo 
$bioseq "ratio-common-kmers" 4 "files/ebola-z-mutated-10000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10000 mutation et le virus d'origine pour des kmers de taille 8" 
echo 
$bioseq "ratio-common-kmers" 8 "files/ebola-z-mutated-10000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10000 mutation et le virus d'origine pour des kmers de taille 12" 
echo 
$bioseq "ratio-common-kmers" 12 "files/ebola-z-mutated-10000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10000 mutation et le virus d'origine pour des kmers de taille 16" 
echo 
$bioseq "ratio-common-kmers" 16 "files/ebola-z-mutated-10000.fasta" $FILE2
echo 
echo "ratio entre le virus ebola avec 10000 mutation et le virus d'origine pour des kmers de taille 20 " 
echo 
$bioseq "ratio-common-kmers" 20 "files/ebola-z-mutated-10000.fasta" $FILE2
echo 

# J'ai choisi de reprendre des tests sur des kmers de taille 4, 8, 12, 16 et 20 pour chaque sequence de genome qu'on a aleatoirement modifie. 
# La premiere chose que l'on remarque c'est que pour avoir de reelles differences il faut avoir un nombre de mutations assez importants. Avec 0 mutations on a l'identite stricte ce n'est pas surprenant.
# Par contre avec 10 mutations meme avec des kmers de taille 20, on a toujours presque 99% de similitudes. Avec 100 mutations on a un peu moins de similitude mais on est egalement a quasiment 90% de similitude pour des kmers de taille 20.
# Par contre a partir de 1000 mutations (la sequence est compose pour rappel de 18 952 nucleotides soit environ 19 000) on commence a voir des differences plus marques. 
# En effet on a en resultat pour des kmers de taille 4 8 12 16 et 20 : 100% 77% 52% 42% et 34% de kmers communs. C'est fort notable ! On a deux sequences qui sont deja plus differencies, voir specifique a deux virus voisins mais differents.
# On remarque qu'a ce stade les deux sont plus proche ensemble qu'ebola-z et ebola-t. 
# C'est en regardant le resultat avec 10 000 mutations (soit plus d'une mutation pour deux nucleotides) que deux organismes peuvent avoir au cours du temps subis un nombre de mutations extrement important.
# En effet si on reprend la comparaison des kmers de taille 4 8 12 16 et 20: on a en resultat: 100% 34% 0.3% 0.01% et 0%. On a des resultats ici assez proche en terme de pourcentage a ceux obtenus entre ebola-z et phage-lambda
# On a deux sequences plus differentes ici qu'entre ebola-z et ebola-t. On peut donc penser qu'entre les deux organismes il y a plus de 1000 mutations qui se sont déroulés mais moins de 10000.
# On pourrait affiner ces ratios en creant des moyennes de ratio pour n mutations afin de fiabiliser un minimum parce qu'on a ici les resultats d'une modification aleatoire mais on peut deja voir qu'il y a entre deux especes assez proches un nombre 
# importants de mutations qui sont vraiment au centre de l'evolution. 

read -p "Appuyer sur une touche pour continuer ..."

# Exemple fonction list-spaced-kmers 
echo 
echo "exemple fonction list-spaced kmers"
echo 
$bioseq "list-spaced-kmers" "##-##" $TEST1
echo 
$bioseq "list-spaced-kmers" "####" $TEST1
echo 
$bioseq "list-kmers" 4 $TEST1
echo 
echo "Les fonctions common-spaced-kmers et ratio-common-spaced-kmers fonctionnent mais je n'ai pas compris la derniere partie test et analyse."
echo 
# Commande Test et analyse de donnees partie V 
# je n'ai pas compris la question : il etait demande de compare une liste de kmers de taille 8 avec une de taille 11 alors que les fonctions codes prenait une seule taille de kmers
# je n'ai pas eu le temps de readapter le code pour effectuer ces tests.


$SHELL
