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
public class Monom {

    protected BigInteger coef = BigInteger.ZERO;
    protected Double degre = 0.0;
    protected BigInteger p = BigInteger.ONE;

    protected Monom(BigInteger p) {
        this.p = p;
    }

    protected Monom(BigInteger coef, Double degre, BigInteger p) {
        this(p);
        if(coef.compareTo(p)>0 || coef.compareTo(BigInteger.ZERO)<0){
        this.coef = coef.mod(p);
        } else {
           this.coef = coef;
        }
        this.degre = degre;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public BigInteger getCoef() {
        return coef;
    }

    public Double getDegre() {
        return degre;
    }

    public BigInteger getP() {
        return p;
    }

    public void setCoef(BigInteger coef) {
        this.coef = coef;
    }

    public void setDegre(Double degre) {
        this.degre = degre;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    boolean isNegative() {
        return getCoef().signum() == -1;
    }

    public int compareToInternal(Monom o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        if (o.getCoef() == null) {
            return getCoef() == null ? 0 : -1;
        }
        int r = getCoef().compareTo(o.getCoef());
        if (r == 0) {
            return getDegre().compareTo(o.getDegre());
        }
        return r;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.coef);
        hash = 59 * hash + Objects.hashCode(this.degre);
        hash = 59 * hash + Objects.hashCode(this.p);
        return hash;
    }

}
