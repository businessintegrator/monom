/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class PolynomProduct extends Polynom {

    public static PolynomProduct createZERO(BigInteger p) {
        return new PolynomProduct(p, new MonomProduct(BigInteger.ZERO, 0.0, p));
    }

    public static PolynomProduct createONE(BigInteger p) {
        return new PolynomProduct(p, new MonomProduct(BigInteger.ONE, 0.0, p));
    }

    public PolynomProduct(BigInteger p, MonomProduct... monoms) {
        super(p);
        this.monoms = new TreeMap(new MonomComparator());
        Double max = 0.0;
        Monom monomProduct = null;
        setCoef(BigInteger.ZERO);
        if (monoms != null) {
            for (Monom monom : monoms) {
                max = max + monom.getDegre();

                if (monomProduct == null) {
                    monomProduct = new Monom(monom.getCoef(), monom.getDegre(), monom.getP());
                } else {
                    monomProduct = new Monom(monom.getCoef().multiply(monomProduct.getCoef()), max, monom.getP());
                }
            }
            setDegre(max);
            this.monoms.put(max, monomProduct);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.monoms);
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
        final PolynomProduct other = (PolynomProduct) obj;
        if (this.compareTo(other) != 0) {
            return false;
        }
        return true;
    }

    public BigInteger f(BigInteger x) {
        BigInteger result = null;
        for (Map.Entry<Double, Monom> entry : monoms.entrySet()) {
            Double key = entry.getKey();
            MonomProduct value = (MonomProduct) entry.getValue();
            BigInteger cof = value.getCoef();
            if (result == null) {
                result = cof.multiply(x.modPow(new BigInteger("" + key), getP()));
            } else {
                result = result.add(cof.multiply(x.modPow(new BigInteger("" + key), getP())));
            }
        }
        return result.mod(getP());
    }

    @Override
    public int compareTo(Polynom o) {
        if (o == null) {
            return -1;
        }
        if (o == this) {
            return 0;
        }
        if (monoms.size() == o.monoms.size()) {
            for (Map.Entry<Double, Monom> entry : monoms.entrySet()) {
                Double key = entry.getKey();
                MonomProduct value = (MonomProduct) entry.getValue();
                MonomProduct value2 = (MonomProduct) o.monoms.get(key);
                int r = value.compareToInternal(value2);
                if (r != 0) {
                    return r;
                }
            }
        } else {
            return -1;
        }
        return 0;
    }

}
