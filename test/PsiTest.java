
import fr.businessintegrator.polynom.MonomX;
import fr.businessintegrator.polynom.MonomY;
import fr.businessintegrator.polynom.NoCoefficientException;
import fr.businessintegrator.polynom.PolynomX;
import fr.businessintegrator.polynom.PolynomY;
import fr.businessintegrator.polynom.Psi;
import fr.businessintegrator.polynom.RationalPolynomX;
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
public class PsiTest {


    @Test
   public void test0(){
       
       BigInteger a =  new BigInteger("46");
       BigInteger b = new BigInteger("74");
       BigInteger p = new BigInteger("97");
       Psi psi = new Psi(a, b, p);
       BigInteger x =  new BigInteger("4");
       BigInteger y =  new BigInteger("15");
        System.out.println("PsiTest.test0() 1");
        RationalPolynomX one =null;
         RationalPolynomX two=null;
         RationalPolynomX three  = null;
          RationalPolynomX foury4 = null;
         
       for (BigInteger i = BigInteger.ONE; i.compareTo(p) < 0; i = i.add(BigInteger.ONE)) {
           try {
               RationalPolynomX poli = psi.pside(i, x, y);
               
               if(i.compareTo(BigInteger.ONE) == 0){
                   System.out.println("PsiTest.test0() 2");
                   Assert.assertTrue("1", true);
                   one = new RationalPolynomX(PolynomX.createONE(p), PolynomX.createONE(p),p);
                  Assert.assertEquals("Pside 1", one, poli);
               }
               if(i.compareTo(new BigInteger("2")) == 0){
                   System.out.println("PsiTest.test0() 3");
                   Assert.assertTrue("2", true);
                   two  = new RationalPolynomX(new PolynomX(p,new MonomX(y.multiply(new BigInteger("2")), 0.0, p)), PolynomX.createONE(p),p);
                  Assert.assertEquals("Pside 2", two, poli);
               }
               if(i.compareTo(new BigInteger("3")) == 0){
                   System.out.println("PsiTest.test0() 4");
                   Assert.assertTrue("3", true);
                   three  = new RationalPolynomX(new PolynomX(p,
                          new MonomX((new BigInteger("18")), 0.0, p),
                          new MonomX((new BigInteger("15")), 1.0, p),
                          new MonomX((new BigInteger("82")), 2.0, p),
                          new MonomX((new BigInteger("3")), 4.0, p))
                          
                          , PolynomX.createONE(p),p);
                  Assert.assertEquals("Pside 3", three, poli);
               }
               if(i.compareTo(new BigInteger("4")) == 0){
                   System.out.println("PsiTest.test0() 4");
                   Assert.assertTrue("4", true);
                   PolynomX y4 = new PolynomX(p, new MonomX(new BigInteger("4").multiply(y),0.0 , p));
                  RationalPolynomX four  = new RationalPolynomX(new PolynomX(p,
                          new MonomX(new BigInteger("-8").multiply(b.pow(2)).subtract(a.pow(3)), 0.0, p),
                          new MonomX((new BigInteger("-4").multiply(a).multiply(b)), 1.0, p),
                          new MonomX((new BigInteger("-5")).multiply(a.pow(2)), 2.0, p),
                          new MonomX((new BigInteger("20")).multiply(b), 3.0, p),
                          new MonomX((new BigInteger("5")).multiply(a), 4.0, p),
                          new MonomX((new BigInteger("1")), 6.0, p))
                          , PolynomX.createONE(p),p);
                  
                   PolynomX nzwnum = four.getNumerateur().multiply(y4);
                   foury4 = new RationalPolynomX(nzwnum, PolynomX.createONE(p),p);
                  Assert.assertEquals("Pside 4"+poli, foury4, poli);
               }
               if(i.compareTo(new BigInteger("5")) == 0){
                   System.out.println("PsiTest.test0() 5");
                   Assert.assertTrue("5", true);
                   RationalPolynomX five5 = foury4.multiply(two);
                   RationalPolynomX five55 = five5.subtract(three.pow(3, p));
                   Assert.assertEquals("Pside 5", five55, poli);
                  RationalPolynomX five  = new RationalPolynomX(new PolynomX(p,
                          new MonomX((new BigInteger("23")), 0.0, p),
                          new MonomX((new BigInteger("67")), 1.0, p),
                          new MonomX((new BigInteger("11")), 2.0, p),
                          new MonomX((new BigInteger("38")), 3.0, p),
                          new MonomX((new BigInteger("77")), 4.0, p),
                          new MonomX((new BigInteger("43")), 5.0, p),
                          new MonomX((new BigInteger("93")), 6.0, p),
                          new MonomX((new BigInteger("26")), 7.0, p),
                          new MonomX((new BigInteger("47")), 8.0, p),
                          new MonomX((new BigInteger("87")), 9.0, p),
                          new MonomX((new BigInteger("39")), 10.0, p),
                          new MonomX((new BigInteger("5")), 12.0, p))
                          , PolynomX.createONE(p),p);
                  Assert.assertEquals("Pside 5", five, poli);
               }
               if(i.compareTo(new BigInteger("5")) > 0){
                   break;
               }
               
            } catch (NoCoefficientException ex) {
               ex.printStackTrace();
           } catch (CloneNotSupportedException ex) {
               ex.printStackTrace();
           }
           
           
       }
   }    
}
