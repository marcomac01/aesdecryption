# AES Decryption

!PRESENTAZIONE INCOMPLETA!
Decifrazione di un file in AES (chiave a 16 caratteri fino ad Integer.MAX_VALUE)
Per maggiori info aprire il file Esercizio.pdf

## Descrizione

L’idea è di suddividere la ricerca della chiave su n thread scelti dal chiamante del metodo di bruteforcing. 
Siccome si opera in un intervallo specifico, quello che ho pensato di fare è di progettare un modo per assegnare i sotto intervalli di ricerca ai thread.

## Per cominciare

### Dipendenze

* IntelliJ IDEA
* JDK 15 or OpenJDK 15 or newer

### Installazione

* è sufficiente clonare la repository dal VCS di IntelliJ

### Esecuzione

* tenere a portata di mano il percorso del file da decifrare e una parola (sottostringa) che aiuti il programma nella ricerca della chiave
* adattare il file di Test.java
* eseguire il suo main

## Autori

@marcomac01 (Marco Macrì MAT 220070 UniCal)

