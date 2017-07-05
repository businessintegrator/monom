
import fr.businessintegrator.polynom.BiPolynomXY;
import fr.businessintegrator.polynom.Binom;
import fr.businessintegrator.polynom.BinomDegre;
import fr.businessintegrator.polynom.NoCoefficientException;
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
 * @author Tiaray RAFARALAHITSIMBA
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class BiPolynomTester {
    
    @Test
    public void create(){
        BigInteger p = new BigInteger("11");
        BiPolynomXY xy = new BiPolynomXY(p,new Binom(BigInteger.ZERO, new BinomDegre(0.0, 0.0), p));
        Assert.assertEquals(xy, BiPolynomXY.createZERO(p));
        
    
    }
    
    @Test
    public void createOne(){
        BigInteger p = new BigInteger("11");
        BiPolynomXY xy = new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(1.0, 0.0), p));
        Assert.assertNotEquals(xy, BiPolynomXY.createONE(p));
        BiPolynomXY xy1 = new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(1.0, 5.0), p));
        Assert.assertNotEquals(xy1, xy);
        BiPolynomXY xy2 = new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(1.0, 0.1), p));
        Assert.assertNotEquals(xy2, xy);
        BiPolynomXY xy3 = new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(1.0, 4.0), p));
        Assert.assertNotEquals(xy3, xy);
    
    }
    
     @Test
    public void createTwo(){
        try {
            BigInteger p = new BigInteger("11");
            BiPolynomXY xy = new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(3.0, 4.0), p));
            BiPolynomXY.createONE(p).multiply(new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(3.0, 4.0), p)));
            Assert.assertEquals(xy, new BiPolynomXY(p,new Binom(BigInteger.ONE, new BinomDegre(3.0, 4.0), p)));
        } catch (NoCoefficientException ex) {
            Assert.fail(ex.getMessage());
        } catch (CloneNotSupportedException ex) {
            Assert.fail(ex.getMessage());
        }
        
    
    }
}
