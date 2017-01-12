#! /usr/bin/env bash

EBOLA=files/ebola-z.fasta
SEQ2=files/seq2.fasta
SEQ3=files/seq3.fasta

bioseq='java -Xmx4096M -jar bioseq.jar'

echo "Partie 1: BWT"
echo
echo "Test avec fichier contenant la sequence TCGA"
$bioseq "bwt-naive" $SEQ2 
echo
echo "Test avec fichier contenant la sequence GGCCATCCTTCCTGACCATTTCCATCATTCCAGTCGAACT"
$bioseq "bwt-naive" $SEQ3 
echo
echo "Test avec la sequence contenant la sequence du virus ebola-z"
$bioseq "bwt-naive" $EBOLA 
echo

echo "Creation des fichiers .bwt"
$bioseq "bwt-naive" $SEQ2 > seq2.bwt
$bioseq "bwt-naive" $SEQ3 > seq3.bwt
$bioseq "bwt-naive" $EBOLA > ebola-z.bwt
echo

read -p "Appuyez sur une touche pour continuer ..."

echo "Partie 2: Inversion de la bwt"
echo
echo "Test avec fichier contenant la sequence TCGA ayant subi une BWT"
$bioseq "unbwt-naive" seq2.bwt
echo
echo "Test avec fichier contenant la sequence GGCCATCCTTCCTGACCATTTCCATCATTCCAGTCGAACT ayant subi une BWT"
$bioseq "unbwt-naive" seq3.bwt
echo
echo "Test avec la sequence contenant la sequence du virus ebola-z ayant subit une BWT"
$bioseq "unbwt" ebola-z.bwt
echo

echo "En utilisant gzip sur ebola-z.bwt et ebola-z.fasta on remarque que la version compresse de la bwt de la sequence a une taille plus restreinte que la version fasta (5838 octets contre 6335)."
echo "En utilisant une compression a base de bzip2 on obtient des fichiers avec des tailles plus restreintes : le fichier fasta a une taille de 5915 octets alors que le fichier fasta a une taille de 5418 octets ce qui est la meilleure compression obtenue des 4."
echo "Ce n'est pas surprenant d'obtenir ce resultat quand on sait qu'il y a utilisation de bwt algorithme Move to Front, rle et codage de huffman pour ameliorer la compression."

read -p "Appuyer sur une touche pour continuer..."