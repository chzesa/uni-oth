# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus on shakkiin pohjautuva vuoropohjainen videopeli, jossa pelaajan 'joukkue' kasataan yhdistämälla shakkinappuloita hahmoluokkiin. Hahmoluokat antavat shakkinapeille erilaisia kykyjä joiden toiminta pohjautuu valittuun shakkinappulaan, esimerkiksi lähetti liikkuu ja hyökkaa edelleen vinottain, mutta hyökkäyksen ominaisuudet saattavat muuttua. Pelaaja pelaa joko tekoälyvastustajaa vastaan tai samalla laitteella vuorotellen toisen pelaajan kanssa.

Sovelluksessa on kaksi päätilaa: peli ja joukkueen kasaus.

### Shakkipeli
Normishakista poiketen yksiköilla on hp- ja hyökkays-arvot, ja siinä missä normishakissa nappi syö toisen ja siirtyy sen paikalle, tässa pelissä yksikot voivat hyökätä niitä vastustajia kohti jotka se pystyisi normaalisti syomään. Hyökkäykset vähentävät puolustajan hp-arvoa ja jos jonkin yksikön hp tippuu nollaan tai alle se poistetaan pelistä. Hyökkääminen ei liikuta hyökkääjää.

Shakkinappulan tyyppi (esim. lähetti, sotilas) määrää sen hp- ja hyökkäys-arvot sekä asettaa pistekertoimen hahmoluokan hinnalle.

Hahmoluokat antavat yksiköille lisäkykyjä joita yksikkö voi käyttää hyökkäämisen tai liikkumisen sijaan. Lisäkyvyillä voi olla rajoite kuinka usein tietty yksikkö pystyy niitä käyttämään.

Peli jatkuu kunnes toisen pelaajan johtaja (huom. ei kuningas) poistetaan pelistä tai kumpikaan pelaaja ei pysty suorittamaan laillista siirtoa.

Pelitilanteen voi tallentaa ja ladata levyltä.

### Joukkueen kasaus
Joukkueelle asetetaan pistemäärä, johon asti joukkueeseen voi lisätä yksikköjä. Yksikölle valitaan sekä shakkinappula että hahmoluokka. Jokaisella shakkinappulalla on pistekerroin joka vaikuttaa luokan hintaan. Nappulalle voi valita myos pohjaluokan jolloin se toimii normaaliin tapaan.

Joukkueessa voi olla maksimissaan 16 yksikköä joista yksi korotetaan johtajaksi kunkin pelaajan ensimmäisella vuorolla. Ennen pelin alkua kumpikin pelaaja asettaa joukkueensa aloitusalueelleen (8x2 alue laudan laidassa) haluamallaan tavalla. Aloitusasetelmat näytetaan toiselle pelaajalle vasta ensimmäisen pelajaan ensimmäisen vuoron alussa.

Joukkuekokoonpanon voi tallentaa ja ladata levyltä.

## Kehitys
* [x] Ydintoiminnallisuus
* [x] Pelin lataus, tallennus
* [x] Pelin päättymistilanne, pattitilanteen havaitseminen
* [x] Valikot
* [ ] Joukkueen kasaus
* [ ] Joukkueen lataus, tallennus