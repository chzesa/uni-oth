# Vaatimusmaarittely

## Sovelluksen tarkoitus
Sovellus on shakkiin pohjautuva vuoropohjainen videopeli, jossa pelaajan 'joukkue' kasataan yhdistamalla shakkinappula hahmoluokkaan. Hahmoluokat antavat shakkinapeille erilaisia kykyja joiden toiminta pohjautuu valittuun shakkinappulaan, esimerkiksi lahetti liikkuu ja hyokkaa edelleen vinosuuntaan, mutta hyokkayksen ominaisuudet saattavat muuttua. Pelaaja pelaa joko tekoalyvastustajaa vastaan tai samalla laitteella vuorotellen toisen pelaajan kanssa.

Ohjelmistossa on kaksi paatilaa: peli ja joukkueen kasaus.

### Shakkipeli
Normishakista poiketen yksikoilla on hp- ja hyokkays-arvot, ja siina missa normishakissa nappi syo toisen ja siirtyy sen paikalle, tassa pelissa yksikot voivat hyokata niita vastustajia kohti jonka se pystyisi normaalisti syomaan. Hyokkaykset vahentavat puolustajan hp-arvoa ja jos jonkin yksikon hp tippuu nollaan tai alle se poistetaan pelista. Hyokkaaminen ei liikuta hyokkaajaa.

Shakkinappulan tyyppi (esim. lahetti, sotilas) maaraa sen hp- ja hyokkays-arvot seka asettaa pistekertoimen hahmoluokan hinnalle.

Hahmoluokat antavat yksikoille lisakykyja joita yksikko voi kayttaa hyokkaamisen tai liikkumisen sijaan. Lisakyvyilla voi olla rajoite kuinka usein tietty yksikko pystyy niita kayttamaan.

Peli jatkuu kunnes toisen pelaajan johtaja (huom. ei kuningas) poistetaan pelista tai kumpikaan pelaaja ei pysty suorittamaan laillista siirtoa.

Pelitilanteen voi tallentaa ja ladata levylta.

### Joukkueen kasaus
Joukkueelle asetetaan pistemaara, johon asti joukkueeseen voi lisata yksikkoja. Yksikolle valitaan seka shakkinappula etta hahmoluokka. Jokaisella shakkinappulalla on pistekerroin joka vaikuttaa luokan hintaan. Nappulalle voi valita myos pohjaluokan jolloin se toimii normaaliin tapaan.

Joukkueessa voi olla maksimissaan 16 yksikkoa joista yksi korotetaan johtajaksi kunkin pelaajan ensimmaisella vuorolla. Ennen pelin alkua kumpikin pelaaja asettaa joukkueensa aloitusalueelleen (8x2 alue laudan laidassa) haluamallaan tavalla. Aloitusasetelmat naytetaan toiselle pelaajalle vasta ensimmaisen pelajaan ensimmaisen vuoron alussa.

Joukkuekokoonpanon voi tallentaa ja ladata levylta.

