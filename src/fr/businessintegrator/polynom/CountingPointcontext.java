/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class CountingPointcontext {
    private final static boolean DEBUG= false;
    TreeMap zeroList = null;
    HashMap<BigInteger,RationalPolynomX> h = null;

    public CountingPointcontext() {
        zeroList = new TreeMap();
        h = new HashMap();
    }
    
    void addOrder(BigInteger x, BigInteger y, BigInteger i) {
        if(DEBUG){
        System.out.println("fr.businessintegrator.polynom.CountingPointcontext.addOrder() "+i+" "+x+" "+y);
        }
        zeroList.put(i,new InternalPoint(x,y) );
    }
    
    public int howManyZero(){
        return zeroList.size();
    }

    RationalPolynomX get(BigInteger i) {
        RationalPolynomX r = h.get(i);
        if(DEBUG){
        System.out.println("fr.businessintegrator.polynom.CountingPointcontext.get("+i+")"+r);
        }
      return r;
    }

    void put(BigInteger i, RationalPolynomX one) {
        h.put(i, one);
    }
    
    
}
