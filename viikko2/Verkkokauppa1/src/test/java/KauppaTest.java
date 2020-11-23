package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa kauppa;

    // Olioiden alustus
    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        kauppa = new Kauppa(varasto, pankki, viite);
    }

    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // Viitegeneraattori palauttaa viitteen 42
        when(viite.uusi()).thenReturn(42);

        // Tuote id 1 = maito, hinta=5, saldo=10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1); // ostetaan maitoa
        kauppa.tilimaksu("pekka", "12345");


        // nimi, viite, tilinro, kaupantili, summa
        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void ostoksetToimiiUseammallaTuotteella() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "mehu", 7));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(2);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 12);
    }

    @Test
    public void samanTuotteenUseampiOstosToimii() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(2);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 10);
    }

    @Test
    public void loppunuttaTuotettaEiVoiOstaa() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(3);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(342)).thenReturn(0);
        when(varasto.haeTuote(342)).thenReturn(new Tuote(342, "kasvomaski", 10));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(342);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);
    }

    @Test
    public void aloitaAsiointiEiSäilytäAiempienOstotapahtumienTietoja() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(3);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 5);

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("beni", "54321");

        verify(pankki).tilisiirto("beni", 42, "54321", "33333-44455", 5);
    }

    @Test
    public void jokaiselleTapahtumalleOmaViitekutsu() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(3);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("pekka", "12345");

        verify(viite, times(1)).uusi();

        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.tilimaksu("beni", "54321");

        verify(viite, times(2)).uusi();
    }

    @Test
    public void eiOstaTuotettaJokaPoistettuKorista() {
        when(viite.uusi()).thenReturn(42);

        when(varasto.saldo(1)).thenReturn(5);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        when(varasto.saldo(7)).thenReturn(3);
        when(varasto.haeTuote(7)).thenReturn(new Tuote(7, "olut", 8));

        kauppa.aloitaAsiointi();

        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(7);
        kauppa.lisaaKoriin(7);
        kauppa.lisaaKoriin(7);

        kauppa.poistaKorista(1);

        kauppa.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto("pekka", 42, "12345", "33333-44455", 24);
    }
}