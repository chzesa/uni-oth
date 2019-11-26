# Arkkitehtuurikuvas
## Rakenne
Korkealla tasolla ohjelman pakkausrakenne on seuraavanlainen:

<img src="https://raw.githubusercontent.com/chzesa/uni-oht/master/dokumentaatio/img/packagearch.png">

Pakkausten `game`, `ability`, ja `unit` välillä on lisäksi rajapinta- ja enum-riippuvuuksia ja jokainen paketti hyödyntää muista paketeista riippumattoman `util` paketin tarjoamia yleisluokkia.

## Käyttöliittymä
Ohjelmassa on vain yksi näkymä, jossa pelataan varsinaista peliä. Käyttöliittymä on tekstipohjainen ja kaikki tominnot suoritetaan syöttämällä laudan koordinaatteja.
Käyttöliittymän rajapintaa ei ole toistaiseksi rajoitettu mutta se ei ole vastuussa pelitapahtumien suorittamisesta ja ohjelmisto on kehitetty siten, että käyttöliittymä on helppo eriyttää tulevaisuudessa.

## Sovelluslogiikka
Itse sovelluslogiikka jakautuu neljän paketin kesken:
`app` on vastuussa sovelluksen käynnistämisestä sekä pelin ja käyttöliittymän suorituksesta

`unit` sisältää shakkinappuloihin liittyvän tiedon ja tarjoaa rajapinnan näiden tietojen lukemiseen ja muuttamiseen

`ability` paketissa toteutetaan nappuloden eri kyvyt ja käyttää tähän tarkoitukseen muiden pakettien rajapintoja. Paketti sisältää myös käyttöliittymän tarvitseman tietorakenteen hahmojen kykyjen käyttämiseen

`game` paketti yhdistää `unit` ja `ability` pakettien rajapinnat yhteen luokkaan luoden itse pelissä käytettävän yksikön. Paketissa on myös pelilaudan ja pelivuorojen hallintaan tarvittavat luokat ja tarjoaa rajoitetun rajapinnat josta `ability` luokka voi lukea pelilaudan tietoa nappuloiden kykyjä suorittaessa.

Ruutupohjaisena pelinä itse ruutujen koordinaatit tarjoavat työkalun riippuvuusten vähentämiseen ja sovellus on suunniteltu tämän ympärille.

Alla on kuvattu sovelluksen kontrolli:

<img src="https://raw.githubusercontent.com/chzesa/uni-oht/master/dokumentaatio/img/mainsequence.png">

## Tiedostot
Sovellus ei tarvitse pysyviä tiedostoja mutta käyttäjällä on mahdollisuus tallentaa pelitilanne ja palauttaa pelitilanne tallennetun tiedon avulla.