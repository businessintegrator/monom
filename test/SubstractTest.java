
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

public class SubstractTest {
      @Test
    public void substrPsi(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monomsA = new MonomX[]{
                new MonomX(new BigInteger("27").mod(p), 7.0, p),
                new MonomX(new BigInteger("-3").mod(p), 6.0, p),
                new MonomX(new BigInteger("-9").mod(p), 4.0, p), 
                new MonomX(new BigInteger("10").mod(p), 3.0, p),
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX a = new PolynomX(p, monomsA);
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("27").mod(p), 7.0, p),
                new MonomX(new BigInteger("-3").mod(p), 6.0, p),
                new MonomX(new BigInteger("-9").mod(p), 4.0, p),
                new MonomX(new BigInteger("10").mod(p), 3.0, p), 
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX b = new PolynomX(p, monomsRes);
            
            PolynomX res = b.subtract(a);
            
            
            Assert.assertEquals("sous limit", res, PolynomX.createZERO(p));
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        }
    }
  
    


    @Test
    public void substractZERO(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monomsA = new MonomX[]{
                new MonomX(new BigInteger("27").mod(p), 7.0, p),
                new MonomX(new BigInteger("-3").mod(p), 6.0, p),
                new MonomX(new BigInteger("-9").mod(p), 4.0, p), 
                new MonomX(new BigInteger("10").mod(p), 3.0, p),
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX a = new PolynomX(p, monomsA);
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("27").mod(p), 7.0, p),
                new MonomX(new BigInteger("-3").mod(p), 6.0, p),
                new MonomX(new BigInteger("-9").mod(p), 4.0, p),
                new MonomX(new BigInteger("10").mod(p), 3.0, p), 
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX b = new PolynomX(p, monomsRes);
            
            PolynomX res = b.subtract(a);
            
            
            Assert.assertEquals("sous limit", res, PolynomX.createZERO(p));
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        }
    }
    
    
    @Test
    public void addInternal(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = new MonomX[]{new MonomX(new BigInteger("3"), 4.0, p), new MonomX(new BigInteger("-1"),1.0, p), new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX b = new PolynomX(p, monoms);
            MonomX[] monomsA = new MonomX[]{new MonomX(new BigInteger("9"), 3.0, p), new MonomX(new BigInteger("-1"),2.0, p)};
            PolynomX a = new PolynomX(p, monomsA);
            System.out.println("b(x)= " + b);
            b.addInternal(a);
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("3").mod(p), 4.0, p),
                new MonomX(new BigInteger("-1").mod(p), 1.0, p), 
                new MonomX(new BigInteger("1").mod(p), 0.0, p), 
                new MonomX(new BigInteger("9").mod(p), 3.0, p), 
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX badd = new PolynomX(p, monomsRes);
              Assert.assertEquals("add internal", b, badd);
            
           
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        }
            
        
    }
    
    @Test
    public void addInternal11(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = new MonomX[]{new MonomX(new BigInteger("3"), 4.0, p), new MonomX(new BigInteger("-1"), 1.0, p), new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX b = new PolynomX(p, monoms);
            MonomX[] monomsA = new MonomX[]{new MonomX(new BigInteger("9"), 3.0, p), new MonomX(new BigInteger("-1"), 2.0, p)};
            PolynomX a = new PolynomX(p, monomsA);
            System.out.println("b(x)= " + b);
            b.addInternal(a);
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("3").mod(p), 4.0, p),
                new MonomX(new BigInteger("-1").mod(p), 1.0, p), 
                new MonomX(new BigInteger("1").mod(p), 0.0, p), 
                new MonomX(new BigInteger("9").mod(p), 3.0, p), 
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX badd = new PolynomX(p, monomsRes);
              Assert.assertEquals("add internal", b, badd);
            
           
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        }
            
        
    }
    @Test
    public void soustraction(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = new MonomX[]{new MonomX(new BigInteger("3"), 4.0, p), new MonomX(new BigInteger("-1"), 1.0, p), new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX b = new PolynomX(p, monoms);
            MonomX[] monomsA = new MonomX[]{new MonomX(new BigInteger("9"), 3.0, p), new MonomX(new BigInteger("-1"), 2.0, p)};
            PolynomX a = new PolynomX(p, monomsA);
            System.out.println("b(x)= " + b);
            PolynomX b2 = b.subtract(a);
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("3").mod(p), 4.0, p),
                new MonomX(new BigInteger("-1").mod(p), 1.0, p), 
                new MonomX(new BigInteger("1").mod(p), 0.0, p), 
                new MonomX(new BigInteger("-9").mod(p), 3.0, p), 
                new MonomX(new BigInteger("1").mod(p), 2.0, p)
            };
            PolynomX badd = new PolynomX(p, monomsRes);
            Assert.assertEquals("soustraction", b2, badd);
           
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        
        }
            
            
    }
    @Test
    public void multiplication1(){
    try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = new MonomX[]{new MonomX(new BigInteger("3"), 4.0, p), new MonomX(new BigInteger("-1"), 1.0, p), new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX b = new PolynomX(p, monoms);
            MonomX[] monomsA = new MonomX[]{new MonomX(new BigInteger("9"), 3.0, p), new MonomX(new BigInteger("-1"), 2.0, p)};
            PolynomX a = new PolynomX(p, monomsA);
            System.out.println("b(x)= " + b);
            PolynomX b2 = b.multiply(a);
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("27").mod(p), 7.0, p),
                new MonomX(new BigInteger("-3").mod(p), 6.0, p), 
                new MonomX(new BigInteger("-9").mod(p), 4.0, p), 
                new MonomX(new BigInteger("10").mod(p), 3.0, p), 
                new MonomX(new BigInteger("-1").mod(p), 2.0, p)
            };
            PolynomX bcarre = new PolynomX(p, monomsRes);
            
            Assert.assertEquals("multiplication", bcarre, b2);
            
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
          }
    }
    @Test
    public void multiplication(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = 
                    new MonomX[]{
                        new MonomX(new BigInteger("1"), 1.0, p), 
                        new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX b = new PolynomX(p, monoms);
            System.out.println("b(x)= " + b);
            PolynomX b2 = b.multiply(b);
            
            MonomX[] monomsRes = 
                    new MonomX[]{new MonomX(new BigInteger("1"), 2.0, p),
                        new MonomX(new BigInteger("2"), 1.0, p), 
                        new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX bcarre = new PolynomX(p, monomsRes);
            
            Assert.assertEquals("carre", bcarre, b2);
            
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        
          }
        
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = new MonomX[]{new MonomX(new BigInteger("1"), 1.0, p), new MonomX(new BigInteger("-1"), 0.0, p)};
            PolynomX b = new PolynomX(p, monoms);
            System.out.println("b(x)= " + b);
            PolynomX b2 = b.multiply(b);
            
            MonomX[] monomsRes = new MonomX[]{new MonomX(new BigInteger("1"), 2.0, p),new MonomX(new BigInteger("-2").mod(p), 1.0, p), new MonomX(new BigInteger("1"), 0.0, p)};
            PolynomX bcarre = new PolynomX(p, monomsRes);
            
            
            Assert.assertEquals("carre", bcarre, b2);
            
        } catch (Exception ex) {
              Assert.fail(ex.getMessage());
          }
        
    }
    //@Test
    public void s1(){
    
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monoms = new MonomX[]{
                new MonomX(new BigInteger("3"), 4.0, p),
                new MonomX(new BigInteger("2"), 3.0, p),
                new MonomX(new BigInteger("1"), 0.0, p)
            };
            PolynomX b = new PolynomX(p, monoms);
            
            MonomX[] monoms2 = new MonomX[]{
                new MonomX(new BigInteger("5"), 3.0, p),
                new MonomX(new BigInteger("1"), 0.0, p)
            };
            PolynomX a = new PolynomX(p, monoms2);
            
            PolynomX[] results = b.divide(a);
            
            
            MonomX[] expectedQuotien = new MonomX[]{
                new MonomX(new BigInteger("5"), 3.0, p),
                new MonomX(new BigInteger("1"), 0.0, p)
            };
            PolynomX q = new PolynomX(p, expectedQuotien);
            
            MonomX[] expectedRest = new MonomX[]{
                new MonomX(new BigInteger("5"), 3.0, p),
                new MonomX(new BigInteger("1"), 0.0, p)
            };
            PolynomX r = new PolynomX(p, expectedRest);
            
            Assert.assertEquals("reste", results[1], r);
            Assert.assertEquals("qurien", results[0], q);
        } catch (Exception ex) {
            
             Assert.fail(ex.getMessage());
        }
     
    }
    
    @Test
    public void subRec(){
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
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("4").mod(p), 7.0, p),
                new MonomX(new BigInteger("9").mod(p), 6.0, p),
                new MonomX(new BigInteger("8").mod(p), 4.0, p),
                new MonomX(new BigInteger("7").mod(p), 3.0, p), 
                new MonomX(new BigInteger("6").mod(p), 2.0, p)
            };
            PolynomX b = new PolynomX(p, monomsRes);
            PolynomX res = a.subtract(b);
            //Polynom res = b.subtract(a);
            
            PolynomX exp = new PolynomX(p, new MonomX[]{new MonomX(new BigInteger("6").mod(p), 7.0, p)});
            
            
            Assert.assertEquals("soustraction", exp, res);
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        }
    }
    
    @Test
    public void subRec1(){
        try {
            BigInteger p = new BigInteger("11");
            MonomX[] monomsA = new MonomX[]{
                new MonomX(new BigInteger("-10").mod(p), 7.0, p),
                new MonomX(new BigInteger("9").mod(p), 6.0, p),
                new MonomX(new BigInteger("8").mod(p), 4.0, p), 
                new MonomX(new BigInteger("7").mod(p), 3.0, p),
                new MonomX(new BigInteger("6").mod(p), 2.0, p)
            };
            PolynomX a = new PolynomX(p, monomsA);
            
            
            MonomX[] monomsRes = new MonomX[]{
                new MonomX(new BigInteger("4").mod(p), 7.0, p),
                new MonomX(new BigInteger("9").mod(p), 6.0, p),
                new MonomX(new BigInteger("8").mod(p), 4.0, p),
                new MonomX(new BigInteger("7").mod(p), 3.0, p), 
                new MonomX(new BigInteger("6").mod(p), 2.0, p)
            };
            PolynomX b = new PolynomX(p, monomsRes);
            PolynomX res = a.subtract(b);
            //Polynom res = b.subtract(a);
            
            PolynomX exp = new PolynomX(p, new MonomX[]{new MonomX(new BigInteger("-14").mod(p), 7.0, p)});
            
            
            Assert.assertEquals("soustraction", exp, res);
        } catch (Exception ex) {
             Assert.fail(ex.getMessage());
        }
    }
}
