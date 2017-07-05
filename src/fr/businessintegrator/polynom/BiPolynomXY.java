/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import static fr.businessintegrator.polynom.Polynom.DEB;
import fr.businessintegrator.schoof.SchoofOperations;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class BiPolynomXY extends Binom {

    private SchoofOperations schoofOps = new SchoofOperations();
    private Map<BinomDegre, Binom> binoms = new TreeMap<BinomDegre, Binom>();

    public static BiPolynomXY createZERO(BigInteger charasteristic) {
        BinomDegre b = new BinomDegre(0.0, 0.0);
        return new BiPolynomXY(charasteristic, new Binom(BigInteger.ZERO, new BinomDegre(0.0, 0.0), charasteristic));
    }

    public static BiPolynomXY createONE(BigInteger charasteristic) {
        BinomDegre b = new BinomDegre(0.0, 0.0);
        return new BiPolynomXY(charasteristic, new Binom(BigInteger.ONE, new BinomDegre(0.0, 0.0), charasteristic));
    }

    public Collection<Binom> values() {
        return binoms.values();
    }
    private Double degre;

    private BiPolynomXY(BigInteger p) {
        super(p);
    }

    public BiPolynomXY(BigInteger p, Binom... monoms) {
        this(p);
        Double max = 0.0;
        setCoef(BigInteger.ZERO);
        if (monoms != null) {
            for (Binom monom : monoms) {
                put(monom.getBinomDegre(), monom);
                if (max < monom.getBinomDegre().getDegreX()) {
                    max = monom.getBinomDegre().getDegreX();
                }
                setCoef(BigInteger.ONE);
            }
            setDegre(max);
        }
    }

    /**
     *
     * @param other XY polynom to add
     * @return
     */
    public BiPolynomXY add(BiPolynomXY other) throws CloneNotSupportedException {
        BiPolynomXY result = new BiPolynomXY(getP());
        for (Map.Entry<BinomDegre, Binom> entry : binoms.entrySet()) {
            BinomDegre key = entry.getKey();
            Binom value = entry.getValue();
            result.put((BinomDegre) key.clone(), (Binom) value.clone());
        }
        if (other.isEmpty()) {
            for (Map.Entry<BinomDegre, Binom> entry : other.binoms.entrySet()) {
                BinomDegre key = entry.getKey();
                Binom value = entry.getValue();
                Binom ref = result.get(key);
                if (ref != null) {
                    ref.addInternal(value);
                } else {
                    result.put((BinomDegre) key.clone(), (Binom) value.clone());
                }
            }
        }

        return result;
    }

    public BiPolynomXY subtract(BiPolynomXY other) throws CloneNotSupportedException {
        BiPolynomXY result = new BiPolynomXY(getP());
        for (Map.Entry<BinomDegre, Binom> entry : binoms.entrySet()) {
            BinomDegre key = entry.getKey();
            Binom value = entry.getValue();
            result.put((BinomDegre) key.clone(), (Binom) value.clone());
        }
        if (other.isEmpty()) {
            for (Map.Entry<BinomDegre, Binom> entry : other.binoms.entrySet()) {
                BinomDegre key = entry.getKey();
                Binom value = entry.getValue();
                Binom ref = result.get(key);
                if (ref != null) {
                    ref.subtractInternal(value);
                } else {
                    Binom inverse = new Binom(BigInteger.ZERO.subtract(value.getCoef()), key, value.getP());
                    result.put((BinomDegre) key.clone(), inverse);
                }
            }
        }

        return result;
    }

    public Set<Map.Entry<BinomDegre, Binom>> entrySet() {
        return binoms.entrySet();
    }

    public Binom put(BinomDegre key, Binom value) {
        return binoms.put(key, value);
    }

    public Binom get(BinomDegre key) {
        return binoms.get(key);
    }

    public boolean isEmpty() {
        if (binoms != null) {
            return binoms.isEmpty();
        }
        return true;
    }

    public BiPolynomXY multiply(BiPolynomXY other) throws NoCoefficientException, CloneNotSupportedException {

        BiPolynomXY result = new BiPolynomXY(getP());
        for (Map.Entry<BinomDegre, Binom> entry : binoms.entrySet()) {
            BinomDegre degre = entry.getKey();
            Binom monom = entry.getValue();
            BigInteger coefficient = monom.getCoef();
            if (BigInteger.ZERO.compareTo(coefficient) == 0) {
                continue;
            }
            for (Map.Entry<BinomDegre, Binom> currentEntry : other.entrySet()) {
                BinomDegre currentDegre = currentEntry.getKey();
                Binom currentMonomX = currentEntry.getValue();
                BigInteger currentCoef = currentMonomX.getCoef();
                if (BigInteger.ZERO.compareTo(coefficient) == 0) {
                    continue;
                }
                BinomDegre newdegre = degre.add(currentDegre);
                Binom olderMonomX = result.get(newdegre);
                if (olderMonomX != null) {
                    BigInteger co1 = (currentCoef.multiply(coefficient).add(olderMonomX.getCoef())).mod(getP());
                    result.put(newdegre, new Binom(co1, degre.add(currentDegre), getP()));
                } else {
                    BigInteger co1 = (currentCoef.multiply(coefficient)).mod(getP());
                    result.put(newdegre, new Binom(co1, degre.add(currentDegre), getP()));
                }
            }
        }

        result.reBold();
        return result;
    }

    public void reBold() {
        Double maxDegre = 0.0;
        Map<BinomDegre, Binom> monoms2del = new TreeMap(new BinomComparator());
        for (Map.Entry<BinomDegre, Binom> entry : entrySet()) {
            BinomDegre degre = entry.getKey();
            Binom monom = entry.getValue();
            BigInteger coef = monom.getCoef();
            if (coef != null) {
                if (coef.compareTo(BigInteger.ZERO) == 0) {
                    monoms2del.put(degre, monom);
                }
                if (maxDegre < monom.getBinomDegre().getDegreX()) {
                    maxDegre = monom.getBinomDegre().getDegreX();
                }
            }
        }
        setDegre(maxDegre);
        for (Iterator iterator = monoms2del.keySet().iterator(); iterator.hasNext();) {
            Double next = (Double) iterator.next();
            remove(next);
        }
        if (binoms == null || binoms.size() == 0) {
            put(BinomDegre.ZERO, new Binom(BigInteger.ZERO, BinomDegre.ZERO, getP()));
        }
    }

    public Binom remove(Object key) {
        return binoms.remove(key);
    }

    private void setDegre(Double maxDegre) {
        this.degre = maxDegre;
    }

    public Double getDegre() {
        return degre;
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        boolean first = true;
        Double k = 0.0;
        for (Map.Entry<BinomDegre, Binom> entry : entrySet()) {
            BinomDegre key = entry.getKey();
            Binom value = entry.getValue();
            if (value == null) {
                continue;
            }
            if (k != key.getDegreX()) {
                s.append("  ");
            }
            if (first) {
                first = false;
                s.append(value + " ");
            } else {

                s.append(" " + (value.isNegative() ? "" : "+") + value + " ");

            }

            k++;
        }
        s.append(" mod " + getP());

        return s.toString();
    }

    public BiPolynomXY[] divideOnX(BiPolynomXY a) throws NoCoefficientException, CloneNotSupportedException {

        BiPolynomXY quotien = BiPolynomXY.createZERO(getP());
        return divide(a, quotien);
    }

    private BiPolynomXY[] divide(BiPolynomXY a, final BiPolynomXY quotien) throws NoCoefficientException, CloneNotSupportedException {
        if (DEB) {
            System.out.println("a= " + a + "\nq1= " + quotien);
        }
        Binom current = values().iterator().next();
        Binom aMonomX = a.values().iterator().next();
        if (DEB) {
            System.out.println("aMonom=" + aMonomX + "  " + aMonomX.getCoef());
        }
        //MonomX q1 = new MonomX((current.getCoef().divideAndRemainder(aMonom.getCoef()))[0], current.getDegre() - aMonom.getDegre(), getP());
        Binom q1 = new Binom((current.getCoef().multiply(schoofOps.inv(aMonomX.getCoef(), getP()))).mod(getP()), new BinomDegre(current.getBinomDegre().getDegreX() - aMonomX.getBinomDegre().getDegreX(), 0.0), getP());
        if (DEB) {
            System.out.println("q1= " + q1);
        }
        BiPolynomXY a1 = this.subtract(a.multiply(q1.toPolynom()));
        if (DEB) {
            System.out.println("a1= " + a1);
            System.out.println("aMonom.getDegre()= " + aMonomX.getBinomDegre().getDegreX());
            System.out.println("a1.getMonoms().values().iterator().next().getDegre()= " + a1.values().iterator().next().getBinomDegre().getDegreX());
        }
        if (a1.values().iterator().next().getBinomDegre().getDegreX() >= aMonomX.getBinomDegre().getDegreX()) {
            quotien.addInternal(q1.toPolynom());
            quotien.reBold();
            if (DEB) {
                System.out.println("quotien= " + quotien + "\n reste " + a1);
            }
            return a1.divide(a, quotien);
        } else {
            BiPolynomXY currentQuotien = q1.toPolynom();
            if (DEB) {
                System.out.println("currentQuotien= " + currentQuotien);
            }
            quotien.addInternal(currentQuotien);
            if (DEB) {
                System.out.println("quotien = quotien.add(currentQuotien);= " + quotien);
            }
        }
        BiPolynomXY reste = a1;
        if (DEB) {
            System.out.println("quotien= " + quotien + "\n rest-->" + reste);
        }
        quotien.reBold();
        reste.reBold();
        BiPolynomXY[] result = new BiPolynomXY[]{quotien, reste};
        return result;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.binoms);
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
        final BiPolynomXY other = (BiPolynomXY) obj;
        if(!this.p.equals(other.p) ){
           return false;
        }
        
        if(isEmpty()){
            return other.isEmpty();
        }
        if(this.binoms.size() != other.binoms.size()){
            return false;
        }
        for (Map.Entry<BinomDegre, Binom> entry : binoms.entrySet()) {
            BinomDegre key = entry.getKey();
            Binom value = entry.getValue();
            Binom otherValue =other.get(key);
            if(!value.equals(otherValue)){
                return false;
            }
        }
        
       
        return true;
    }
    
    
}
