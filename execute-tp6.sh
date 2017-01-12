#! /usr/bin/env bash

PHAGE=files/phage-lambda.fasta
SEQ1=files/seq1.fasta
bioseq='java -jar bioseq.jar'

echo "Partie 1: Redefinir les methodes suffix-array bwt (j'ai aussi fait unbwt) de maniere plus efficace"
$bioseq suffix-array $PHAGE | cut -d\  -f 1-10
echo
$bioseq bwt $PHAGE > lambda_phage.bwt
cat lambda_phage.bwt | cut -b 1-10
echo
read -p "Appuyer sur une touche pour continuer..."
echo
echo "J'ai egalement realise les methodes sur la bacterie S. Thermophilus les resultats retournes sont: 982352 982353 982354 982355 982356 982357 1455457 982358 870589 213850"
echo "L'execution a duree environ 1 minute sur mon ordinateur."
echo "10 premiers nucleotides de la bwt de la bacterie S. Thermophilus : GCAAAAATAC. Temps d'execution : environ 9min30. Pour information l'inversion de la bwt de S. Thermophilus a duree environ 19 min."
echo "Plus d'information present dans le README."
echo

read -p "Appuyer sur une touche pour continuer..."

echo
echo "Partie 2"
echo
echo "Recherche de sequence dans la bwt"
echo
$bioseq match-bwt phage-lambda.bwt GGCGCGCAGTGACACTGCGCTGGATCGTCTGATG
echo
$bioseq match-bwt phage-lambda.bwt AGGCGCGCAGTGACACTGCCGCTGGGATCGTCTGATG
echo
read -p "Appuyer sur une touche pour continuer..."