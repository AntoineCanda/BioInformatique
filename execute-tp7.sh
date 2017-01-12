#! /usr/bin/env bash

PHAGE=files/phage-lambda
bioseq='java -jar bioseq.jar'

echo "Compression d'un fichier fasta"

$bioseq compress-fasta $PHAGE.fasta
$bioseq compress-huffman <$PHAGE.txt >$PHAGE.compressed
rm $PHAGE.txt

read -p "Appuyer sur une touche pour continuer ..."
echo
echo "Decompression d'un fichier compresse pour obtenir un fichier fasta"
$bioseq decompress-huffman < $PHAGE.compressed > $PHAGE.txt
$bioseq decompress-fasta $PHAGE.txt
rm $PHAGE.txt
read -p "Appuyer sur une touche pour continuer ..."

echo "Notre fonction est legerement moins bonne que bzip2 et legerement meilleure que gzip sur phage-lambda en considerant que notre methode ne compresse que la sequence et pas l'identifiant"
echo "Le temps d'execution pour phage lambda est de moins d'une seconde pour phage-lambda. La decompression dure aussi moins d'une seconde."
echo "Par curiosite j'ai effectue les operations sur thermophylus. Compression dure moins de 15s. Decompression dure environ 2 min 30. Le fichier compresse fait une taille de 514 ko contre 1780ko non comrpesse."
