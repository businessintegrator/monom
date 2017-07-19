
import fr.businessintegrator.polynom.MonomX;
import fr.businessintegrator.polynom.PolynomX;
import fr.businessintegrator.polynom.RationalPolynomX;
import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author o
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class RationalTest {

    public static final String BAD = "Bad characteristic";

    @Test
    public void test6Diff() {
        try {

            BigInteger p = new BigInteger("11");
            BigInteger p1 = new BigInteger("17");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(BigInteger.ONE, 1.0, p1));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p1));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p);
            RationalPolynomX one = RationalPolynomX.createONE(p);
            Assert.assertFalse(one.equals(polynom));
            Assert.assertNotEquals("Note equals", one, polynom);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), BAD);
        }
    }

    @Test
    public void test5Diff() {
        try {
            BigInteger p = new BigInteger("11");
            BigInteger p1 = new BigInteger("17");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(BigInteger.ONE, 1.0, p));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p1);
            RationalPolynomX one = RationalPolynomX.createONE(p);

            Assert.assertNotEquals("Note equals", one, polynom);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), BAD);
        }
    }

    @Test
    public void test4Diff() {
        try {
            BigInteger p = new BigInteger("11");
            BigInteger p1 = new BigInteger("17");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(BigInteger.ONE, 1.0, p));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p1));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p);
            RationalPolynomX one = RationalPolynomX.createONE(p);
            Assert.assertNotEquals("Note equals", one, polynom);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), BAD);
        }
    }

    @Test
    public void test3Diff() {
        try {
            BigInteger p = new BigInteger("11");
            BigInteger p1 = new BigInteger("17");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(BigInteger.ONE, 1.0, p));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p1));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p1);
            RationalPolynomX one = RationalPolynomX.createONE(p);

            Assert.assertNotEquals("Note equals", one, polynom);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), BAD);
        }
    }

    @Test
    public void testDiff() {
        try {
            BigInteger p = new BigInteger("11");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(BigInteger.ONE, 1.0, p));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p);
            RationalPolynomX one = RationalPolynomX.createONE(p);

            Assert.assertNotEquals("Note equals", one, polynom);
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), BAD);
        }
    }

    @Test
    public void test2Diff() {
        try {
            BigInteger p = new BigInteger("11");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(new BigInteger("8"), 1.0, p));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p);
            RationalPolynomX one = RationalPolynomX.createONE(p);
            Assert.assertNotEquals(one, polynom);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testRational() {
        try {
            BigInteger p = new BigInteger("11");
            PolynomX pNumerateur, pDenominateur;
            pNumerateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p));
            pDenominateur = new PolynomX(p, new MonomX(BigInteger.TEN, 1.0, p));
            RationalPolynomX polynom = new RationalPolynomX(pNumerateur, pDenominateur, p);
            RationalPolynomX one = RationalPolynomX.createONE(p);
            Assert.assertEquals(one, polynom);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
