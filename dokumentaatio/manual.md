# Käyttöohje
[Lataa](https://github.com/chzesa/uni-oht/releases) ja suorita uusin release.

Ohjelman voi suorittaa komennolla `java -jar chess.java`

## Valikoissa navigointi
Navigointi valikoissa tapahtuu syöttämällä seuraavan valikon numero.
Tallennuksen lataus valikossa käyttäjää pyydetään syöttämään tiedostopolku aiemmin tallennettuun peliin. Esimerkiksi `/home/linuxuser/savedgame`. Valikosta voi poistua syöttämällä tyhjän merkkijonon.

## Composition editor
Composition editorissa voi muokata sessiossa olevia compositioneita. Editorissa näkyy nykyisen compositionin tilantnne ja siinä on 7 komentoa
* `rename <newname>`: muuttaa compositionin nimen, kunhan uudessa nimessä on 1 tai enemmän merkkiä. Nimien ei tarvitse olla ainutlaatuisia
* `add <coord> <role> <piece>`: lisää koordinaattiin roolin ja piecen määrittelemän yksikön, jos koordinaatin ruutu on tyhjä.
* `remove <coord>`: poistaa koordinaatisas olean yksikön
* `help`: tulostaa komennot ja mahdolliset roolit ja shakkinappuloiden tyypt
* `<coord>`: tulostaa tiedon syötetyssä ruudussa olevasta yksiköstä.
* `return`: palaa päävalikkoon
* `delete`: poistaa compositionin muistista.

## Pelaaminen
### Aloitus
Pelaajan tulee koota ainakin yksi joukkuekokoonpano ennen kuin pelin voi aloittaa. Kummallekin pelaajalle asetetaan jokin kokoonpano.

### Hahmon ja kyvyn valinta
Pelaaja valitsee haluamansa hahmon syöttämällä shakista tutun koordinaatin ohjelmaan, esimerkiksi `a1`. Valintaa voi vaihtaa syöttämällä uuden koordinaatin.

Kun yksikkö on valittu, pelaaja valitsee haluamansa kyvyn syöttämällä kyvyn indeksin.

Syöttämällä `forfeit` pelaaja voi luovuttaa pelin.

Syöttämällä `save` ja välilyönnillä erotetun tiedostopolun voi tallentaa pelitilanteen. Esim `save /home/linuxuser/savedgame`

### Kykyjen kohdistaminen
Kykyjen kohdistaminen tapahtuu syöttämällä yhden tai useamman shakkikoordinaatin. Valittavissa olevat koordinaatit on maalattu sinivihreällä, valitut purppuralla. Syöttämällä saman koordinaatin kahdesti voi poistaa koordinaatin valinnasta. Pelaajalta pyydetään koordinaatteja kunnes vaadittu minimiraja täyttyy, jolloin kohdistaminen siirtyy seuraavaan vaiheeseen tai kyky suoritetaan.

Kyvyn kohdistamisen voi peruuttaaa syöttämällä `cancel`.