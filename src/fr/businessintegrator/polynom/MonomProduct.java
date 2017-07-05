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
public class MonomProduct extends Monom implements Cloneable {
    
   
    public MonomProduct(BigInteger coef, Double degre, BigInteger p) {
        super(coef,degre,p);
    }
    
    @Override
    public String toString() {
        if (getDegre() == 0) {
            return "" + getCoef();
        } else if (getDegre() == 1) {

            return ((getCoef().compareTo(BigInteger.ONE) == 0) ? "" : getCoef().toString() + ".") + "y";
        } else {
            return ((getCoef().compareTo(BigInteger.ONE) == 0) ? "" : getCoef().toString() + ".") + "y^" + getDegre() + " ";
        }

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
        if (obj instanceof MonomX){
        final MonomProduct other = (MonomProduct) obj;
        if (!Objects.equals(this.coef, other.coef)) {
            return false;
        }
        if (!Objects.equals(this.degre, other.degre)) {
            return false;
        }
        if (!Objects.equals(this.p, other.p)) {
            return false;
        }
        return true;
        }
        return false;
    }
  
 
    
}
