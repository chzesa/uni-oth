# Vaatimusmäärittely

## Sovelluksen tarkoitus
Sovellus on shakkiin pohjautuva vuoropohjainen videopeli, jossa pelaajan 'joukkue' kasataan yhdistämälla shakkinappuloita hahmoluokkiin. Hahmoluokat antavat shakkinapeille erilaisia kykyjä joiden toiminta pohjautuu valittuun shakkinappulaan, esimerkiksi lähetti liikkuu ja hyökkaa edelleen vinottain, mutta hyökkäyksen ominaisuudet saattavat muuttua. Pelaaja pelaa joko tekoälyvastustajaa vastaan tai samalla laitteella vuorotellen toisen pelaajan kanssa.

Sovelluksessa on kaksi päätilaa: peli ja joukkueen kasaus.

### Shakkipeli
Normishakista poiketen yksiköilla on hp- ja hyökkays-arvot, ja siinä missä normishakissa nappi syö toisen ja siirtyy sen paikalle, tässa pelissä yksikot voivat hyökätä niitä vastustajia kohti jotka se pystyisi normaalisti syomään. Hyökkäykset vähentävät puolustajan hp-arvoa ja jos jonkin yksikön hp tippuu nollaan tai alle se poistetaan pelistä. Hyökkääminen ei liikuta hyökkääjää.

Shakkinappulan tyyppi (esim. lähetti, sotilas) määrää sen hp- ja hyökkäys-arvot sekä asettavat hahmolle pistearvon.

Hahmoluokat antavat yksiköille lisäkykyjä joita yksikkö voi käyttää hyökkäämisen tai liikkumisen sijaan.

Peli jatkuu kunnes toisen pelaajan johtaja (vapaavalintainen yksikkö pelin alussa) poistetaan pelistä tai kumpikaan pelaaja ei pysty suorittamaan laillista siirtoa.

Pelitilanteen voi tallentaa ja ladata levyltä.

### Joukkueen kasaus
Joukkueelle asetetaan pistemäärä, johon asti joukkueeseen voi lisätä yksikköjä. Yksikölle valitaan sekä shakkinappula että hahmoluokka. Jokaisella shakkinappulalla on pistekerroin joka vaikuttaa pistearvoon. Nappulalle voi valita myos pohjaluokan jolloin se toimii normaaliin tapaan.

Joukkueessa voi olla maksimissaan 16 yksikköä joista yksi korotetaan johtajaksi ennen pelin ensimmäistä vuoroa. Joukkue määrittelee yksiköiden aloitusasetelmat.

## Kehitys
* [x] Ydintoiminnallisuus
* [x] Pelin lataus, tallennus
* [x] Pelin päättymistilanne, pattitilanteen havaitseminen
* [x] Valikot
* [x] Joukkueen kasaus

## Jatkokehitys
* Graafinen käyttöliittymä
* Joukkuekokoonpanojen hallinta levylle tallennettavan tiedoston avulla