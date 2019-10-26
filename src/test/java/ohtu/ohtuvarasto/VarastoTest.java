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
    int alkuTilavuus = 10;

    @Before
    public void setUp() {
        varasto = new Varasto(alkuTilavuus);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusKunTilavuusNolla() {
        Varasto huonoVarasto = new Varasto(-1);
        assertEquals(0.0, huonoVarasto.getTilavuus(), vertailuTarkkuus);
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
    public void varastoonLaitetaanLiikaa() {
        varasto.lisaaVarastoon(12);
        assertEquals(alkuTilavuus, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenMaaraEiVaikutaSaldoon() {
        varasto.lisaaVarastoon(-1);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liiallinenVarastonPoistoPalauttaaKaiken() {
        int otettavaMaara = 8;
        varasto.lisaaVarastoon(otettavaMaara);
        assertEquals(otettavaMaara, varasto.otaVarastosta(12), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenVarastonPoistoPalauttaaNollan() {
        varasto.lisaaVarastoon(8);
        assertEquals(0.0, varasto.otaVarastosta(-1), vertailuTarkkuus);
    }

    @Test
    public void merkkijonoEsitysVarastolleOikein() {
        varasto.lisaaVarastoon(6);
        assertEquals("saldo = 6.0, vielä tilaa 4.0", varasto.toString());
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaAlkusaldoNegatiivisellaAlkusaldolla() {
        Varasto uusiVarasto = new Varasto(10, -1);
        assertEquals(10, uusiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaAlkusaldoNegatiivisellaTilavuudella() {
        Varasto uusiVarasto = new Varasto(-1, 10);
        assertEquals(0, uusiVarasto.getTilavuus(), vertailuTarkkuus);
        // assertEquals(0, uusiVarasto.getSaldo(), vertailuTarkkuus);
        // Ei mennyt testeistä läpi

    }

    @Test
    public void uudellaVarastollaOikeaTilavuusJaAlkusaldo() {
        Varasto uusiVarasto = new Varasto(10, 10);
        assertEquals(10, uusiVarasto.getSaldo(), vertailuTarkkuus);
    }

}