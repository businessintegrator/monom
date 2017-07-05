
import fr.businessintegrator.polynom.MonomX;
import fr.businessintegrator.polynom.NoCoefficientException;
import fr.businessintegrator.polynom.PolynomX;
import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class DivideTest {


    @Test
    public void testbig(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monomsA = new MonomX[]{
                new MonomX(new BigInteger("1").mod(p), 4.0, p),
                new MonomX(new BigInteger("-1").mod(p), 2.0, p),
                new MonomX(new BigInteger("1").mod(p), 1.0, p),
                new MonomX(new BigInteger("-4").mod(p), 0.0, p)
            };
            PolynomX a = new PolynomX(p, monomsA);
            
            MonomX[] monomsB = new MonomX[]{
                new MonomX(new BigInteger("1").mod(p), 2.0, p),
                new MonomX(new BigInteger("-2").mod(p), 1.0, p),
                new MonomX(new BigInteger("5").mod(p), 0.0, p)
            };
            PolynomX b = new PolynomX(p, monomsB);
            
            PolynomX [] qr = a.divide(b);
            
            MonomX[] expectedR = new MonomX[]{
                new MonomX(new BigInteger("-13").mod(p), 1.0, p),
                new MonomX(new BigInteger("6").mod(p), 0.0, p)
            };
            PolynomX expR = new PolynomX(p, expectedR);
            
            MonomX[] expectedM = new MonomX[]{
                new MonomX(new BigInteger("1").mod(p), 2.0, p),
                new MonomX(new BigInteger("2").mod(p), 1.0, p),
                new MonomX(new BigInteger("-2").mod(p), 0.0, p)
            };
            PolynomX expM = new PolynomX(p, expectedM);
            
            Assert.assertEquals("quotien b/a", qr[0], expM);
            Assert.assertEquals("reste b/a", qr[1], expR);
             
            
        } catch (NoCoefficientException ex) {
            Assert.fail(ex.getMessage());
            
        } catch (CloneNotSupportedException ex) {
              Assert.fail(ex.getMessage());
        }
    }
   
  @Test
    public void test0(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monomsA = new MonomX[]{
                new MonomX(new BigInteger("3").mod(p), 4.0, p),
                new MonomX(new BigInteger("2").mod(p), 3.0, p),
                new MonomX(new BigInteger("1").mod(p), 0.0, p)
            };
            PolynomX a = new PolynomX(p, monomsA);
            
            MonomX[] monomsB = new MonomX[]{
                new MonomX(new BigInteger("5").mod(p), 3.0, p),
                new MonomX(new BigInteger("1").mod(p), 0.0, p)
            };
            PolynomX b = new PolynomX(p, monomsB);
            
            PolynomX [] qr = a.divide(b);
            
            MonomX[] expectedR = new MonomX[]{
                new MonomX(new BigInteger("-27").mod(p), 1.0, p),
                new MonomX(new BigInteger("27").mod(p), 0.0, p)
            };
            PolynomX expR = new PolynomX(p, expectedR);
            
            MonomX[] expectedM = new MonomX[]{
                new MonomX(new BigInteger("27").mod(p), 1.0, p),
                new MonomX(new BigInteger("18").mod(p), 0.0, p)
            };
            PolynomX expM = new PolynomX(p, expectedM);
            
            Assert.assertEquals("quotien b/a", qr[0], expM);
            Assert.assertEquals("reste b/a", qr[1], expR);
             
            
        } catch (NoCoefficientException ex) {
            Assert.fail(ex.getMessage());
            
        } catch (CloneNotSupportedException ex) {
              Assert.fail(ex.getMessage());
        }
    }
    
    @Test
    public void test1(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monomsA = new MonomX[]{
                new MonomX(new BigInteger("10").mod(p), 7.0, p),
                new MonomX(new BigInteger("9").mod(p), 6.0, p),
                new MonomX(new BigInteger("8").mod(p), 4.0, p), 
                new MonomX(new BigInteger("7").mod(p), 3.0, p),
                new MonomX(new BigInteger("6").mod(p), 2.0, p)
            };
            PolynomX a = new PolynomX(p, monomsA);
            PolynomX a2 = a.multiply(a);
            PolynomX a3 = a2.multiply(a);
            
            PolynomX [] qr = a3.divide(a);
            Assert.assertEquals("quotien a3/a", qr[0], a2);
            Assert.assertEquals("reste a3/a", qr[1], PolynomX.createZERO(p));
             PolynomX [] qr2 = a3.divide(a2);
             Assert.assertEquals("quotien a3/a2", qr2[0], a);
            Assert.assertEquals("reste a3/a", qr2[1], PolynomX.createZERO(p));
            
            PolynomX [] qr3 = a2.divide(a);
             Assert.assertEquals("quotien a3/a", qr3[0], a);
            Assert.assertEquals("reste a3/a", qr3[1], PolynomX.createZERO(p));
            
        } catch (NoCoefficientException ex) {
            Assert.fail(ex.getMessage());
            
        } catch (CloneNotSupportedException ex) {
              Assert.fail(ex.getMessage());
        }
    }    
}
