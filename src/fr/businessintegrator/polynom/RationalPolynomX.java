/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.math.BigInteger;
import java.util.Objects;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class RationalPolynomX {
    private PolynomX numerateur;
    private PolynomX denominateur;
    

    public static RationalPolynomX createZERO(BigInteger p) throws NoCoefficientException{
        return new RationalPolynomX(PolynomX.createZERO(p), PolynomX.createONE(p),p);
    }
    public static RationalPolynomX createONE(BigInteger p) throws NoCoefficientException{
        PolynomX one = PolynomX.createONE(p);
        return new RationalPolynomX(one, one,p);
    } 
    private boolean DEBUG = false;
    private final BigInteger p;

    public RationalPolynomX(RationalPolynomX result, PolynomX newdenominateur,BigInteger p) throws NoCoefficientException, CloneNotSupportedException {
        this(result.getNumerateur(),result.getDenominateur().multiply(newdenominateur),p);
        
                
    }

    RationalPolynomX(RationalPolynomX first, RationalPolynomX second, BigInteger p) throws NoCoefficientException, CloneNotSupportedException {
        this(first.getNumerateur().multiply(second.getDenominateur()),second.getNumerateur().multiply(first.getDenominateur()),p);
      
    }
     
    public PolynomX[] resolve() throws NoCoefficientException, CloneNotSupportedException{
        return getNumerateur().divide(getDenominateur());
    }
    /**
     * 
     * @param pNumerateur
     * @param pDenominateur 
     * @param p 
     * 
     */
    public RationalPolynomX(PolynomX pNumerateur, PolynomX pDenominateur, BigInteger p) throws NoCoefficientException {
        this.p = p;
        if(this.p.equals(pNumerateur.p) && this.p.equals(pDenominateur.p)){
            this.numerateur = pNumerateur;
            this.denominateur = pDenominateur;
        } else {
            throw new NoCoefficientException("Bad characteristic");
        }
        
      
    }

    public PolynomX getNumerateur() {
        return numerateur;
    }

    public PolynomX getDenominateur() {
        return denominateur;
    }

    /**
     * 
     * @param other
     * @return
     * @throws NoCoefficientException
     * @throws CloneNotSupportedException 
     */
        
    public RationalPolynomX add(RationalPolynomX other) throws NoCoefficientException, CloneNotSupportedException{
        PolynomX newMerator1 = getNumerateur().multiply(other.getDenominateur());
        PolynomX newMerator2 = other.getNumerateur().multiply(getDenominateur());
        PolynomX newMerator = newMerator1.add(newMerator2);
        PolynomX newDenoMinator = other.getDenominateur().multiply(getDenominateur());
        return new RationalPolynomX(newMerator, newDenoMinator,newMerator1.getP());
    }
    /**
     * 
     * @param other
     * @return
     * @throws NoCoefficientException
     * @throws CloneNotSupportedException 
     */
     public RationalPolynomX subtract(RationalPolynomX other) throws NoCoefficientException, CloneNotSupportedException{
        PolynomX newMerator1 = getNumerateur().multiply(other.getDenominateur());
        if(DEBUG ) {
            System.out.println("fr.businessintegrator.polynom.RationalPolynomX.subtract()");
         System.out.println(""+getNumerateur());
         System.out.println("-------------------------------------------------");
         System.out.println(""+getDenominateur());
         System.out.println(""+other.getNumerateur());
         System.out.println("-------------------------------------------------");
         System.out.println(""+other.getDenominateur());
        }
        PolynomX newMerator2 = other.getNumerateur().multiply(getDenominateur());
        PolynomX newMerator = newMerator1.subtract(newMerator2);
        PolynomX newDenoMinator = other.getDenominateur().multiply(getDenominateur());
        if(DEBUG){
         System.out.println("fr.businessintegrator.polynom.RationalPolynomX.subtract()");
         System.out.println(""+newMerator);
         System.out.println("-------------------------------------------------");
         System.out.println(""+newDenoMinator);
        }
        return new RationalPolynomX(newMerator, newDenoMinator,newMerator.getP());
    }
     
     /**
     * 
     * @param other
     * @return
     * @throws NoCoefficientException
     * @throws CloneNotSupportedException 
     */
     public RationalPolynomX multiply(RationalPolynomX other) throws NoCoefficientException, CloneNotSupportedException{
        if (other == null) return null;
         PolynomX newMerator = getNumerateur().multiply(other.getNumerateur());
        PolynomX newDenoMinator = getDenominateur().multiply(other.getDenominateur());
        return new RationalPolynomX(newMerator, newDenoMinator,getNumerateur().getP());
    }

      public BigInteger [] fEven(BigInteger x,BigInteger y) {
        BigInteger num = getNumerateur().f(x);
        BigInteger denom = getDenominateur().f(x).multiply(y);
        
        if(denom.compareTo(BigInteger.ZERO) == 0){
            return null;
        }
        return num.divideAndRemainder(denom);
    }
    public BigInteger [] f(BigInteger x) {
        BigInteger num = getNumerateur().f(x);
        BigInteger denom = getDenominateur().f(x);
        if(denom.compareTo(BigInteger.ZERO) == 0){
            return null;
        }
        return num.divideAndRemainder(denom);
    }
    
    @Override
    public String toString() {
        return   getNumerateur() + "/" + getDenominateur() ;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            System.out.println("fr.businessintegrator.polynom.RationalPolynomX.equals() INST");
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            System.out.println("fr.businessintegrator.polynom.RationalPolynomX.equals() CLAZEZE");
            return false;
        }
        final RationalPolynomX other = (RationalPolynomX) obj;
        if (this.numerateur.equals(other.numerateur) && this.denominateur.equals(other.denominateur) ) {
                    System.out.println("fr.businessintegrator.polynom.RationalPolynomX.equals() EQ");
            return true;
        }
        if(this.numerateur.equals(this.denominateur)){
            System.out.println("fr.businessintegrator.polynom.RationalPolynomX.equals() SAME");
            return other.numerateur.equals(other.denominateur);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.numerateur);
        hash = 97 * hash + Objects.hashCode(this.denominateur);
        return hash;
    }

    public RationalPolynomX pow(int exponent,BigInteger p) throws NoCoefficientException, CloneNotSupportedException {
       RationalPolynomX result = null;
       RationalPolynomX current = new RationalPolynomX(getNumerateur(), getDenominateur(),p);
       if(exponent == 0) return createONE(p);
        for (int i = 1; i <= exponent; i++) {
            if(result == null){
                result = current;
            } else {
                result = result.multiply(current);
            }
        }
       return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }

    RationalPolynomX multiply(PolynomX polynomX) throws NoCoefficientException, CloneNotSupportedException {
      RationalPolynomX result = new RationalPolynomX(getNumerateur().multiply(polynomX), getDenominateur(),polynomX.getP());
      return result;
    }

    RationalPolynomX divideCoefficient(BigInteger coef) throws NoCoefficientException {
        PolynomX polynomD =  getNumerateur().divideByScalar(coef);
        return new RationalPolynomX(polynomD, getDenominateur(),polynomD.getP());
    }

    RationalPolynomX multiplyX(BigInteger p) throws NoCoefficientException, CloneNotSupportedException {

       RationalPolynomX result = new RationalPolynomX(getNumerateur().multiplyX(), getDenominateur(),p);
       return result;
    }

    public BigInteger getP() {
        return p;
    }

    
    
    
    
     
}
