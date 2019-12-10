# Shakkipeli
Sovellus on kehitetty Helsingin Yliopiston ohjelmistotekniikka kurssille.

Peli on shakkivariantti jossa shakkinappuloilla on hahmoluokat.

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/chzesa/uni-oth/blob/master/dokumentaatio/manual.md)

## Releaset

[Viikko 5](https://github.com/chzesa/uni-oht/releases/tag/week5)

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

### Checkstyle
```sh
mvn jxr:jxr checkstyle:checkstyle
```

### Javadoc
```sh
mvn javadoc:javadoc
```

### Suorittaminen
```sh
mvn compile exec:java -Dexec.mainClass=oht.chess.Main
```

### Jarin generointi
```sh
mvn package
```
