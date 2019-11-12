package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinAlkusaldoOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(5);
        assertEquals(15, kortti.saldo());
    }
    
//    @Test
//    public void kortilleEiVoiLadataNegatiivistaArvoa() {
//        kortti.lataaRahaa(-1);
//        assertEquals(10, kortti.saldo());
//    }
    
    @Test
    public void kortiltaVoiOttaaRahaa() {
        kortti.otaRahaa(3);
        assertEquals(7, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaPalauttaaOikeanArvonOnnistuessa() {
        assertTrue(kortti.otaRahaa(3));
    }
    
    @Test
    public void kortiltaEiVoiOttaaSaldonYlittavaaSummaa() {
        kortti.otaRahaa(11);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaPalauttaaOikeanArvonEpaonnistuessa() {
        assertFalse(kortti.otaRahaa(11));
    }
    
    @Test
    public void korttiTulostaaArvonOikein() {
        kortti.lataaRahaa(200);
        assertEquals("saldo: 2.10", kortti.toString());
    }
}
