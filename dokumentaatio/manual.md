# Käyttöohje
[Lataa](https://github.com/chzesa/uni-oht/releases) ja suorita uusin release.

## Valikoissa navigointi
Navigointi valikoissa tapahtuu syöttämällä seuraavan valikon numero.
Tallennuksen lataus valikossa käyttäjää pyydetään syöttämään tiedostopolku aiemmin tallennettuun peliin. Esimerkiksi `/home/linuxuser/savedgame`. Valikosta voi poistua syöttämällä tyhjän merkkijonon.

## Pelaaminen
### Hahmon ja kyvyn valinta
Pelaaja valitsee haluamansa hahmon syöttämällä shakista tutun koordinaatin ohjelmaan, esimerkiksi `a1`. Valintaa voi vaihtaa syöttämällä uuden koordinaatin.

Kun yksikkö on valittu, pelaaja valitsee haluamansa kyvyn syöttämällä kyvyn indeksin.

Syöttämällä `forfeit` pelaaja voi luovuttaa pelin.

Syöttämällä `save` ja välilyönnillä erotetun tiedostopolun voi tallentaa pelitilanteen. Esim `save /home/linuxuser/savedgame`

### Kykyjen kohdistaminen
Kykyjen kohdistaminen tapahtuu syöttämällä yhden tai useamman shakkikoordinaatin. Valittavissa olevat koordinaatit on maalattu sinivihreällä, valitut purppuralla. Syöttämällä saman koordinaatin kahdesti voi poistaa koordinaatin valinnasta. Pelaajalta pyydetään koordinaatteja kunnes vaadittu minimiraja täyttyy, jolloin kohdistaminen siirtyy seuraavaan vaiheeseen tai kyky suoritetaan.

Kyvyn kohdistamisen voi peruuttaaa syöttämällä `cancel`.