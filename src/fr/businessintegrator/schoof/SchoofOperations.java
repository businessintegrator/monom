/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.schoof;

import java.math.BigInteger;
import java.util.Map;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class SchoofOperations {

    private final static boolean DEB = false;
    public final static BigInteger DEUX = new BigInteger("2");
    public final static BigInteger TROIS = new BigInteger("3");
    public final static BigInteger QUATRE  = new BigInteger("4");
    static int RADIX = 36;

    public SchoofOperations() {
    }

    public BigInteger inv(BigInteger b, BigInteger n) {
        BigInteger n0 = n;
        BigInteger b0 = b;
        BigInteger t0 = BigInteger.ZERO;
        BigInteger t = BigInteger.ONE;
        BigInteger q = n0.divide(b0);
        BigInteger r = n0.subtract(q.multiply(b0));

        if (DEB) {
            System.out.println(n0 + "=" + q + " x" + b0 + " + " + r);
        }

        while (r.compareTo(BigInteger.ZERO) > 0) {
            BigInteger temp = t0.subtract(q.multiply(t));
            if (temp.compareTo(BigInteger.ZERO) >= 0) {
                temp = temp.mod(n);
            } else {
                temp = n.subtract((BigInteger.ZERO.subtract(temp)).mod(n));
            }
            t0 = t;
            t = temp;
            n0 = b0;
            b0 = r;
            q = n0.divide(b0);
            r = n0.subtract(q.multiply(b0));

            if (DEB) {
                System.out.println(n0 + "=" + q + " x" + b0 + " + " + r);
            }
        }

        if (b0.compareTo(BigInteger.ONE) == 0) {
            return t;
        } else {
            return null;
        }
    }

     public BigInteger racine(BigInteger xi, BigInteger n) {
        if (xi.multiply(xi).compareTo(n) >= 0) {
            return xi;
        }
        return (xi.add(n.divide(xi))).divide(SchoofOperations.DEUX);
    }

     public boolean quadraticResidue(BigInteger a, BigInteger p){
         return a.modPow((p.subtract(BigInteger.ONE)).divide(DEUX), p).compareTo(BigInteger.ONE) == 0;
     }

}
