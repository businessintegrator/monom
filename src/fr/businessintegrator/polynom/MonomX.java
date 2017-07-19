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
public class MonomX extends Monom implements Cloneable,Comparable<MonomX> {

  


    public MonomX(BigInteger coef, Double degre, BigInteger p) {
        super(coef,degre,p);
    }
    


    @Override
    public String toString() {
        if (getDegre() == 0) {
            return "" + getCoef();
        } else if (getDegre() == 1) {

            return ((getCoef().compareTo(BigInteger.ONE) == 0) ? "" : getCoef().toString() + ".") + "x";
        } else {
            return ((getCoef().compareTo(BigInteger.ONE) == 0) ? "" : getCoef().toString() + ".") + "x^" + getDegre() + " ";
        }

    }


    

     public PolynomX add(MonomX other) {
        if (this.degre == other.degre) {
            return new PolynomX(p, this, new MonomX(coef.add(other.coef).mod(p), this.degre, p));
        } else {
            return new PolynomX(p, new MonomX(coef, degre, p), other);
            
        }
    }

    PolynomX toPolynom() {
        PolynomX result = new PolynomX(p, this);
        return result;
    }

    @Override
    public int compareTo(MonomX o) {
        if (o == null){return -1;}
        if(o == this) {return 0;}
        if(o.coef.compareTo(coef) == 0 && o.degre.compareTo(degre)== 0 && o.p.compareTo(p)== 0){
            return 0;
        }
        return -1;
        
    }
    

    
    
    
}
