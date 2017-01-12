#! /usr/bin/env bash

READ=files/reads-TRG.fasta
GERMLINEV=files/TRGV.fasta
GERMLINEJ=files/TRGJ.fasta

bioseq='java -jar bioseq.jar'

echo "Test de la partie I "
echo
echo "Test de best-matches"
echo
echo "Test avec des graines contigues"
echo
echo "Test avec graines ####"
$bioseq best-matches 5 "####" $READ $GERMLINEV
echo
echo "Test avec graines ####"
$bioseq best-matches 5 "####" $READ $GERMLINEJ
echo
echo "Test avec graines ########"
$bioseq best-matches 5 "########" $READ $GERMLINEV
echo
echo "Test avec graines ########"
$bioseq best-matches 5 "########" $READ $GERMLINEJ
echo
echo "Test avec graines ############"
$bioseq best-matches 5 "############" $READ $GERMLINEV
echo
echo "Test avec graines ############"
$bioseq best-matches 5 "############" $READ $GERMLINEJ
echo
echo "Test avec graines ################"
$bioseq best-matches 5 "################" $READ $GERMLINEV
echo
echo "Test avec graines ################"
$bioseq best-matches 5 "################" $READ $GERMLINEJ
echo
echo "Test avec graines ####################"
$bioseq best-matches 5 "####################" $READ $GERMLINEV
echo
echo "Test avec graines ####################"
$bioseq best-matches 5 "####################" $READ $GERMLINEJ
echo
read -p "appuyer sur une touche pour continuer ..."

echo "Test avec des graines espacés"
echo
echo "Test avec graines ##-##"
$bioseq best-matches 5 "##-##" $READ $GERMLINEV
echo
echo "Test avec graines ##-##"
$bioseq best-matches 5 "##-##" $READ $GERMLINEJ
echo
echo "Test avec graines ####-####"
$bioseq best-matches 5 "####-####" $READ $GERMLINEV
echo
echo "Test avec graines ####-####"
$bioseq best-matches 5 "####-####" $READ $GERMLINEJ
echo
echo "Test avec graines ####-####-####"
$bioseq best-matches 5 "####-####-####" $READ $GERMLINEV
echo
echo "Test avec graines ####-####-####"
$bioseq best-matches 5 "####-####-####" $READ $GERMLINEJ
echo
echo "Test avec graines ####-####-####-####"
$bioseq best-matches 5 "####-####-####-####" $READ $GERMLINEV
echo
echo "Test avec graines ####-####-####-####"
$bioseq best-matches 5 "####-####-####-####" $READ $GERMLINEJ
echo
echo "Test avec graines ####-####-####-####-####"
$bioseq best-matches 5 "####-####-####-####-####" $READ $GERMLINEV
echo
echo "Test avec graines ####-####-####-####-####"
$bioseq best-matches 5 "####-####-####-####-####" $READ $GERMLINEJ
echo 
read -p "appuyer sur une touche pour continuer ..."

echo
echo "Test de VJ-Discover"
echo
echo "Test avec des graines contigues"
echo
echo "Test avec graines ####"
$bioseq VJ-discover "####" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ########"
$bioseq VJ-discover "########" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ############"
$bioseq VJ-discover "############" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ################"
$bioseq VJ-discover "################" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ####################"
$bioseq VJ-discover "####################" $READ $GERMLINEV $GERMLINEJ
echo

read -p "appuyer sur une touche pour continuer ..."

echo "Test avec des graines espacés"
echo
echo "Test avec graines ##-##"
$bioseq VJ-discover "##-##" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ####-####"
$bioseq VJ-discover "####-####" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ####-####-####"
$bioseq VJ-discover "####-####-####" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ####-####-####-####"
$bioseq VJ-discover "####-####-####-####" $READ $GERMLINEV $GERMLINEJ
echo
echo "Test avec graines ####-####-####-####-####"
$bioseq VJ-discover "####-####-####-####-####" $READ $GERMLINEV $GERMLINEJ
echo

read -p "appuyer sur une touche pour continuer ..."

# Analyse des resultats
# La premiere chose que l'on remarque c'est qu'avec les graines contigues, on a toujours le meme gene V qui a le plus gros matchs entre notre premere sequence du fichier et notre fichier 
# germlineV à savoir le gene TRGV3*01. En revanche ce qui est plus interessant c'est le nombre de kmers communs qu'ils ont ! Avec une graine de taille 4 il y en a 214 alors qu'avec une graine de taile 
# 20 (bien plus importante donc !!) il en reste encore 160 ! Il est important de souligner que la différence la plus marque est le passage d'une graine de taille 4 (214) a une graine de taille 8 (172). 
# Cela montre qu'il n'y a à priori que très peu de mutations entre les deux. On remarque également que le gene TRGV3*02 a lui aussi d'assez grande similitude avec cette read (155 pour graine taille 12, 147 pour 16 et 139 pour 20).
# On peut donc en deduire qu'il y a à priori un gene commun entre les deux variation de TRGV3*01 et TRGV3*02 et assez recent vu qu'il y a finalement assez peu de difference. Il y a egalement un gene commun un peu plus ancien avec 
# TRGV5 qui s'en tire avec des similitudes assez importante (12: 132 16: 120 20: 108). Les autres genes de TRGV ont des similitudes moins marques avec ce gene. L'ancetre commun est donc plus ancien. 

# Pour le gene issu de TRGJ les resultats sont plus net. En effet sur les 5 meilleurs matchs que l'on a, si on prend des tailles de kmers un minimum significatif (8 (et encore ça reste trop faible) 12 16 ou 20) on a des resultats que pour 
# 2 gene : TRGJP1 (8:32 12:28 16:24 20:20) et TRGJP2 (8:15 12:9 16:5 20:1). On en conclue qu'il y a une similitude plus importante entre le gene recherche et TRGJP1(environ 33% avec graine de taille 20 et quasiment 50% avec taille 12) et 
# assez peu avec TRGJP2. Ils ont probablement un ancetre commun moins recent. 

# L'utilisation des graines espacés donnent des resultats similaire et tende donc à confirmer ces impressions. 
# Une fois de plus l'utilisation de kmers de petite tailles (4 et 8) tend à donner des resultats n'ayant pas grand interet car la probabilité d'obtenir un kmers parmi ceux possibles (256 possibilites pour 4 et 65536 pour 8) ne donnet pas de resultats 
# vraiment exploitable. En revanche les kmers de grande taille sont des indicateurs plus pertinents de la ressemblance surtout 16 et 20.

# La fonction VJ-discover nous donne des resultats globalement similaire quand on utilise des kmers de taille 8 et +. On remarque qu'on a tres souvent les mêmes genes qui reviennent a savoir TRGV3*01 (9 fois sur 12 read) suivi par TRGV2*02 (2 fois (read 6 et 8) et la read 10 avec TRGV4*02).
# Pour les genes de TRGJ on a 6 fois TRGJP1 et 6 fois TRGJ1*01. 
# L'utilisation de graine espacees donnent des resultats legerement differents parfois notamment sur les genes de J. En effet on trouve parfois TRGJ1*02 alors qu'on avait TRGJ1*01.

# Analyse des resultats avec Vidjil
# On retrouve globalement des resultats similaires sauf pour les TRGJ ou au lieu d'avoir TRGJ1*01 on trouve TRGJ1*02.
# On peut expliquer ces differences par la taille des genes assez faibles et la tres forte ressemblance des deux genes.
# En effet a premiere vue il semblerait que TRGJ1*01 soit TRGJ1*02 avec 3 nucleotides supplémentaires en tout debut de sequences (GAA). Notre programme n'étant pas tres precis il est normal que l'on puisse avoir des resultats qui changent de l'un à l'autre. De même la faible taille des sequences (47 et 50)
# peut avoir un impact sur le nombre de kmers en commun et donc le resultat affiché.
# Vidjil confirme donc mon impression entre ceux deux genes : le manque de precision de notre programme ainsi que le peu de difference et la faible taille de kmers a cet impact sur l'affichage obtenu.
# La methode de calcul base sur le nombre de kmers commun a aussi cet effet un peu pervers quand deux genes sont tres proche l'un de l'autre et que les mutations sont des insertions ou deletion : le gene un peu plus long aura plus de kmers a teste
# et donc plus de chance de ne pas en avoir autant en commun ou un ratio plus faible. Il est donc important de savoir et pouvoir associer les genes entre deux dans un schema plus global pour en determiner les filiations plus ou moins importantes et en cas de besoin aller comparer de maniere plus specifique 
# les resultats obtenus. Meme avec un programme aussi basique que le notre.
