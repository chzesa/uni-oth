# Shakkipeli
Sovellus on kehitetty Helsingin Yliopiston ohjelmistotekniikka kurssille.

Peli on shakkivariantti jossa shakkinappuloilla on hahmoluokat.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/arkkitehtuuri.md)

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
mvn compile exec:java -Dexec.mainClass=oht.chess.Main
```

Aikaisemmin tallennetun pelin voi ladata syöttämällä tiedoston polun ensimmäisenä parametrina

```sh
mvn compile exec:java -Dexec.mainClass=oht.chess.Main -Dexec.args="/path/to/file"
```
