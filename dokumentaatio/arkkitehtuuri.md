# Arkkitehtuurikuvas
## Rakenne
Korkealla tasolla ohjelman pakkausrakenne on seuraavanlainen:

<img src="https://raw.githubusercontent.com/chzesa/uni-oht/master/dokumentaatio/img/packagearch.png">

## Käyttöliittymä
Ohjelman käyttöliittymä jakautuu valikoihin, joissa navigoidaan pääasiallisesti syöttämällä numeroita.
Jokainen valikko on oma olionsa ja ohjelman suoritus jatkuu kunnes käyttöliittymä ei palauta uutta valikkoa.
Pelitilanteessa tominnot suoritetaan pääasiallisesti syöttämällä laudan koordinaatteja tai numeroita.
Käyttöliittymän rajapintaa ei ole toistaiseksi rajoitettu mutta se ei ole vastuussa pelitapahtumien suorittamisesta ja ohjelmisto on kehitetty siten, että käyttöliittymä on helppo eriyttää tulevaisuudessa.

## Sovelluslogiikka
Sovelluslogiikka jakautuu pääosin seuraaviin paketteihin:

`unit` sisältää shakkinappuloihin liittyvän tiedon

`ability` paketissa toteutetaan nappuloden eri kyvyt ja käyttää tähän tarkoitukseen muiden pakettien rajapintoja.

`game` paketti yhdistää `unit` ja `ability` pakettien rajapinnat yhteen luokkaan luoden itse pelissä käytettävän yksikön. Paketissa on myös pelilaudan ja pelivuorojen hallintaan tarvittavat luokat.

`shared` paketti sisältää mm. yleisesti käytössä olevat rajapinnat, ja `util` sisältää apuluokkia.

Ruutupohjaisena pelinä itse ruutujen koordinaatit tarjoavat työkalun riippuvuuksien vähentämiseen ja sovellus on suunniteltu tämän ympärille.

Alla on kuvattu pelitilanteen kontrolli:

<img src="https://raw.githubusercontent.com/chzesa/uni-oht/master/dokumentaatio/img/mainsequence.png">

## Tiedostot
Sovellus ei käytä pysyviä tiedostoja mutta käyttäjällä on mahdollisuus tallentaa pelitilanne ja palauttaa pelitilanne tallennetun tiedon avulla.

Pelitilanne tallennetaan tekstitiedostona jossa yksiköt ja pelin kentät on jokainen omalla rivillään. Rivijärjestyksellä ei ole väliä ja sovellus jättää virheelliset rivit huomiotta. Tiedoston pystyy lataamaan kunhan se kuvaa oikein vuoronumeron ja laudan koon, tosin on mahdollista että ladattu peli päättyy ennen ensimmäistä siirtoa.