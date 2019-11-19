# Shakkipeli
Sovellus on kehitetty Helsingin Yliopiston ohjelmistotekniikka kurssille.

Peli on shakkivariantti jossa shakkinappuloilla on hahmoluokat. Peli loppuu kun jommalla kummalla pelaajalla ei ole enään johtohahmoa pelissä.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/tuntikirjanpito.md)

## Komennot
### Testaus
Testien suorittaminen:
```sh
mvn test
```

Testikattavuusraportin generointi:
```sh
mvn test jacoco:report
```

### Suorittaminen
```sh
mvn compile exec:java -Dexec.mainClass=oht.chess.Chess
```