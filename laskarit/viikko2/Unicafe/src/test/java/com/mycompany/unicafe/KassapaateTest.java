package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
    }
    
    @Test
    public void kassapaatteellaOnOikeatAlkuarvot() {
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKateisostoKasvattaaRahamaaraa() {
        paate.syoEdullisesti(1000);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiPalauttaaVaihtorahanOikein() {
        int change = paate.syoEdullisesti(1000);
        assertEquals(760, change);
    }
    
    @Test
    public void syoEdullisestiKasvattaaMyytyjenLounaidenMaaraa() {
        paate.syoEdullisesti(1000);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiPalauttaaRahatEpaonnistuessa() {
        int change = paate.syoEdullisesti(239);
        assertEquals(239, change);
    }
    
    @Test
    public void  syoEdullisestiEiKasvataLounaidenMaaraaEpaonnistuessa() {
        paate.syoEdullisesti(239);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiEiKasvataRahamaaraaEpaonnistuessa() {
        paate.syoEdullisesti(239);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiKateisostoKasvattaaRahamaaraa() {
        paate.syoMaukkaasti(1000);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaVaihtorahanOikein() {
        int change = paate.syoMaukkaasti(1000);
        assertEquals(600, change);
    }
    
    @Test
    public void syoMaukkaastiKasvattaaMyytyjenLounaidenMaaraa() {
        paate.syoMaukkaasti(1000);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiPalauttaaRahatEpaonnistuessa() {
        int change = paate.syoMaukkaasti(399);
        assertEquals(399, change);
    }
    
    @Test
    public void  syoMaukkaastiEiKasvataLounaidenMaaraaEpaonnistuessa() {
        paate.syoMaukkaasti(399);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiEiKasvataRahamaaraaEpaonnistuessa() {
        paate.syoMaukkaasti(399);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    
    @Test
    public void syoEdullisestiKortilla() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(paate.syoEdullisesti(kortti));
        assertEquals(760, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiKortillaEpaonnistuminen() {
        Maksukortti kortti = new Maksukortti(239);
        assertFalse(paate.syoEdullisesti(kortti));
        assertEquals(239, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortilla() {
        Maksukortti kortti = new Maksukortti(1000);
        assertTrue(paate.syoMaukkaasti(kortti));
        assertEquals(600, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukkaastiKortillaEpaonnistuminen() {
        Maksukortti kortti = new Maksukortti(399);
        assertFalse(paate.syoMaukkaasti(kortti));
        assertEquals(399, kortti.saldo());
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(0, paate.edullisiaLounaitaMyyty());
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void lataaRahaaKassalla() {
        Maksukortti kortti = new Maksukortti(1000);
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKassallaEpaonnistuuNegatiivisellaSummalla() {
        Maksukortti kortti = new Maksukortti(1000);
        paate.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, paate.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
    }
}
