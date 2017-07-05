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
public class Binom {

    
    private BigInteger coef = BigInteger.ZERO;
    private BinomDegre binomDegre = BinomDegre.ZERO;
    protected BigInteger p = BigInteger.ONE;

    protected Binom(BigInteger p) {
        this.p = p;
    }

    public Binom(BigInteger coef, BinomDegre pDegres, BigInteger p) {
        this(p);
        this.coef = coef.mod(p);
        this.binomDegre = pDegres;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public BigInteger getCoef() {
        return coef;
    }

    public void setCoef(BigInteger coef) {
        this.coef = coef;
    }

    public BinomDegre getBinomDegre() {
        return binomDegre;
    }

    public void setBinomDegre(BinomDegre binomDegre) {
        this.binomDegre = binomDegre;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    void addInternal(Binom value) {
        if(value != null){
            BigInteger co = value.getCoef();
            if(co != null){
                this.coef = coef.add(co);
            }
        }
    }

    void subtractInternal(Binom value) {
        if(value != null){
            BigInteger co = value.getCoef();
            if(co != null){
                this.coef = coef.subtract(co);
            }
        }
    }

    boolean isNegative() {
         return getCoef().signum() == -1;
    }

    BiPolynomXY toPolynom() {
        return new BiPolynomXY(p, this);
    }

    @Override
    public String toString() {
        return  coef + "." + binomDegre ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.coef);
        hash = 59 * hash + Objects.hashCode(this.binomDegre);
        hash = 59 * hash + Objects.hashCode(this.p);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Binom other = (Binom) obj;
        if (!Objects.equals(this.coef, other.coef)) {
            return false;
        }
        if (!Objects.equals(this.binomDegre, other.binomDegre)) {
            return false;
        }
        if (!Objects.equals(this.p, other.p)) {
            return false;
        }
        return true;
    }
    
    
    
}
