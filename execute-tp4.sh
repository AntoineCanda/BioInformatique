#! /usr/bin/env bash

PHAGE=files/phage-lambda.fasta
SEQ1=files/seq1.fasta
bioseq='java -Xmx4096M -jar bioseq.jar'

echo "Partie 1 : Recherche naive"
echo
$bioseq "search-fasta-naive" $PHAGE "GGCCATCCTTCCTGACCATTTCCATCATTCCAGTCGAACT"
echo
$bioseq "search-fasta-naive" $PHAGE "ATAGTG"
echo
read -p "appuyer sur une touche pour continuer ..."

echo
echo "Partie 2: Tableau de suffixe"
echo

echo "Tableau de suffixe de la sequence GGCCATCCTTCCTGACCATTTCCATCATTCCAGTCGAACT"
echo
$bioseq "suffix-array-naive" $SEQ1
echo

echo
echo "Tableau de suffixe de phage-lambda"
echo
$bioseq "suffix-array-naive" $PHAGE
echo
read -p "appuyer sur une touche pour continuer ..."

echo 
echo "Partie optionnelle recherche avec search-fasta-sa. Ne marche pas correctement. Affiche une seule position si presente."
echo

$bioseq "search-fasta-sa" $PHAGE "GGCCATCCTTCCTGACCATTTCCATCATTCCAGTCGAACT"
echo
$bioseq "search-fasta-sa" $PHAGE "ATAGTG"

$SHELL