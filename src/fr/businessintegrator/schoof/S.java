/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.schoof;

import java.math.BigInteger;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class S {

    private BigInteger t;
    private BigInteger p;

    public S(BigInteger t, BigInteger p) {
        this.t = t;
        this.p = p;
    }

    BigInteger s(BigInteger i) {
        if (BigInteger.ZERO.compareTo(i) == 0) {
            return SchoofOperations.DEUX;
        }
        if (BigInteger.ONE.compareTo(i) == 0) {
            return t;
        }

        return t.multiply(s(i.subtract(BigInteger.ONE))).subtract(p.multiply(s(i.subtract(SchoofOperations.DEUX))));
    }
}
