# Testausdokumentti


## Yksikkö- ja integraatiotestaus
### Sovellus
Suuri osa sovelluksesta pohjautuu pelilautaan ja koordinaattien tallettamiseen erilaisissa yhdistelmissä, eli `Targeter`, `TargetSet` ja `Board` joten näiden luokkien yksikkötestaaminen on erittäin tärkeää.

Integraatiotestejä suoritetaan esimerkiksi `AbilityUseCheckerTest` -luokassa, joka pohjimmillaan testaa pattitilanteen selvitystyökalua, sekä `GameplayTest` luokassa.

### DummyController
Luokassa `GameplayTest` on pelaajarajapinnan toteuttava `DummyController` -luokka jolle voi etukäteen syöttää parametreja joiden mukaan luokka vastaa pelin kyselyihin pelaajan toiminnoista. Tällä testataan muutama kokonainen peliskenaario.

### Testikattavuus
Käyttöliittymää lukuunottamatta testikattavuus on 84% ja haarautuvuuskattavuus 70%.
<img src="https://raw.githubusercontent.com/chzesa/uni-oht/master/dokumentaatio/img/coverage.png">

Testaamatta on jäänyt mm. toString metodit, yksinkertaiset getterit ja setterit sekä constructorit.

Haaroista on jäänyt testaamatta mm. joitakin mahdottomia haaroja, kuten esimerkiksi virheitä heittävät haarat jotka voivat tapahtua vain sovellusta laajennettaessa jos kehittäjä unohtaa luoda vaadittavat overridet tai täydentää switch-statementit.

### Käyttöliittymä
Käyttöliittymää on testattu manuaalisesti erilaisilla syötteillä, kuitenkin esimerkiksi valikot joissa navigoidaan numeroilla perivytyvät yhdestä luokasta joten ne ovat melko yksinkertaisia. Pelitilanteen tallennus ja lataus on myös testattu, tosin vain tiedostojen lukemisen ja kirjoittamisen vuoksi, sillä serialisaatioon on automatisoitu testi.