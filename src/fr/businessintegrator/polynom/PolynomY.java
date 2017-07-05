/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class PolynomY extends PolynomX{
    
    public PolynomY(BigInteger p, MonomY... monoms) {
        super(p, monoms);
    }
    
    public PolynomY multiply(PolynomY other) throws NoCoefficientException, CloneNotSupportedException {
        PolynomY result = new PolynomY(getP(), null);
        result.setProducts(getProducts());
        result.getProducts().add(other);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        LinkedList<PolynomY> products = getProducts();
        if(products != null){
        for (PolynomY product : products) {
            s.append("("+product+")");
        }
        }
        return s.toString();
        
    }

   
    
}
