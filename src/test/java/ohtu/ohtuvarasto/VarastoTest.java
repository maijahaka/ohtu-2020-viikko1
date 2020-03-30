package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void kuormitetunKonstruktorinLuomallaVarastollaOikeaSaldo() {
        Varasto kuormitettuVarasto = new Varasto(10,4);

        assertEquals(4, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianSuuriAlkusaldoTayttaaVaraston() {
        Varasto kuormitettuVarasto = new Varasto(10,12);

        assertEquals(10, kuormitettuVarasto.getSaldo(), vertailuTarkkuus);        
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    public void kuormitetullaUudellaVarastollaOikeaTilavuus() {
        Varasto kuormitettuVarasto = new Varasto(10,4);

        assertEquals(10, kuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void liianLisaaminenSaaVarastonTayteen() {
        varasto.lisaaVarastoon(12);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianOttaminenTyhjentaaVaraston() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(6);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liianOttaminenPalauttaaSaldon() {
        varasto.lisaaVarastoon(5);

        assertEquals(5, varasto.otaVarastosta(6), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranLisaysEiMuutaMitaan() {
        varasto.lisaaVarastoon(-5);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisenMaaranOttoPalauttaaNollan() {

        assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }

    @Test
    public void toStringToimiOikein() {
        varasto.lisaaVarastoon(4);

        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

    @Test
    public void negatiivinenTilavuusLuoVarastonJonkaTilavuusOnNolla() {
        Varasto epakelpoVarasto = new Varasto(-10);

        assertEquals(0, epakelpoVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenTilavuusLuoKuormitetunVarastonJonkaTilavuusOnNolla() {
        Varasto epakelpoKuormitettuVarasto = new Varasto (-10,6);

        assertEquals(0, epakelpoKuormitettuVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenAlkusaldoLuoKuormitetunVarastonJonkaSaldoOnNolla() {
        Varasto epakelpoKuormitettuVarasto = new Varasto(10,-6);

        assertEquals(0, epakelpoKuormitettuVarasto.getSaldo(), vertailuTarkkuus);
    }

}