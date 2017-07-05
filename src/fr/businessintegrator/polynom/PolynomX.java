/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import static fr.businessintegrator.polynom.Polynom.DEB;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * PolynomX
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class PolynomX extends Polynom implements Cloneable {

    public static PolynomX createZERO(BigInteger p) {
        return new PolynomX(p, new MonomX(BigInteger.ZERO, 0.0, p));
    }

    public static PolynomX createONE(BigInteger p) {
        return new PolynomX(p, new MonomX(BigInteger.ONE, 0.0, p));
    }
    private LinkedList<PolynomY> products;

    public PolynomX(BigInteger p) {
        super(p);

    }

    /**
     * Creates Sum of polynoms with monoms.
     *
     * @param p
     * @param monoms
     */
    public PolynomX(BigInteger p, MonomX... monoms) {
        super(p, monoms);

    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer();
        boolean first = true;
        int k = 0;
        for (Map.Entry<Double, Monom> entry : getMonoms().entrySet()) {
            Double key = entry.getKey();
            MonomX value = (MonomX) entry.getValue();
            if (value == null) {
                continue;
            }
            if (k != key) {
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

        final LinkedList<PolynomY> products1 = getProducts();
        if (products1 != null) {
            for (PolynomY product : products1) {
                s.append("(" + product + ")");
            }
        }
        return s.toString();
    }

    public void reBold() {
        Double maxDegre = 0.0;
        Map<Double, Monom> monoms2del = new TreeMap(new MonomComparator());
        for (Map.Entry<Double, Monom> entry : getMonoms().entrySet()) {
            Double degre = entry.getKey();
            Monom monom = (Monom) entry.getValue();
            BigInteger coef = monom.getCoef();
            if (coef != null) {
                if (coef.compareTo(BigInteger.ZERO) == 0) {
                    monoms2del.put(degre, monom);
                }
                if (maxDegre < monom.getDegre()) {
                    maxDegre = monom.getDegre();
                }
            }
        }
        setDegre(maxDegre);
        for (Iterator iterator = monoms2del.keySet().iterator(); iterator.hasNext();) {
            Double next = (Double) iterator.next();
            monoms.remove(next);
        }
        if (monoms == null || monoms.size() == 0) {
            monoms.put(0.0, new MonomX(BigInteger.ZERO, 0.0, getP()));
        }
    }

    /**
     * Addition of the group
     *
     * @param other other PolynomX
     * @throws NoCoefficientException
     */
    /**
     * Soustraction of the group
     *
     * @param other other PolynomX
     * @throws NoCoefficientException
     */
    public PolynomX add(PolynomX other) throws NoCoefficientException, CloneNotSupportedException {

        PolynomX result = new PolynomX(getP(), null);
        for (Map.Entry<Double, Monom> entry : other.getMonoms().entrySet()) {
            Double degre = entry.getKey();
            MonomX monom = (MonomX) entry.getValue();
            result.monoms.put(degre, (Monom) monom.clone());
        }
        for (Map.Entry<Double, Monom> entry : getMonoms().entrySet()) {
            Double degre = entry.getKey();
            MonomX monom = (MonomX) entry.getValue();
            MonomX currentMonomX = (MonomX) result.monoms.get(degre);
            if (currentMonomX == null) {
                result.monoms.put(degre, (Monom) monom.clone());
            } else {
                BigInteger co = (monom.getCoef().add(currentMonomX.getCoef())).mod(getP());
                if (co.compareTo(BigInteger.ZERO) == 0) {
                    result.monoms.remove(degre);
                } else {
                    result.monoms.put(degre, new MonomX(co, degre, getP()));
                }
            }
        }

        result.reBold();
        return result;
    }

    /**
     * Soustraction of the group
     *
     * @param other other PolynomX
     * @throws NoCoefficientException
     */
    public PolynomX subtract(PolynomX other) throws NoCoefficientException, CloneNotSupportedException {
        PolynomX result = new PolynomX(this.getP(), null);
        for (Map.Entry<Double, Monom> entry : getMonoms().entrySet()) {
            Double degre = entry.getKey();
            MonomX monom = (MonomX) entry.getValue();
            result.monoms.put(degre, (MonomX) monom.clone());
        }
        for (Map.Entry<Double, Monom> entry : other.getMonoms().entrySet()) {
            Double degre = entry.getKey();
            Monom value = entry.getValue();
            if (value instanceof MonomX) {
                MonomX monom = (MonomX) value;
                BigInteger coef = monom.getCoef();
                MonomX currentMonomX = (MonomX) result.getMonoms().get(degre);
                if (currentMonomX == null) {
                    result.monoms.put(degre, new MonomX(BigInteger.ZERO.subtract(coef), degre, getP()));
                } else {
                    result.monoms.put(degre, new MonomX(currentMonomX.getCoef().subtract(coef), degre, getP()));
                }
            }
        }
        result.reBold();
        return result;
    }

    public PolynomX multiply(PolynomX other) throws NoCoefficientException, CloneNotSupportedException {
        if (other instanceof PolynomY) {
            PolynomX result = (PolynomX)clone();
            if (result.products == null) {
                result.products = new LinkedList<PolynomY>();
            }
             result.products.add((PolynomY) other);
             return result;
        }
        
        PolynomX result = new PolynomX(this.getP(), null);
        for (Map.Entry<Double, Monom> entry : other.getMonoms().entrySet()) {
            Double degre = entry.getKey();
            Monom value = entry.getValue();
            if (value instanceof MonomX) {
                MonomX monom = (MonomX) value;
                BigInteger coefficient = monom.getCoef();
                if (BigInteger.ZERO.compareTo(coefficient) == 0) {
                    continue;
                }
                for (Map.Entry<Double, Monom> currentEntry : getMonoms().entrySet()) {
                    Double currentDegre = currentEntry.getKey();
                    MonomX currentMonomX = (MonomX) currentEntry.getValue();
                    BigInteger currentCoef = currentMonomX.getCoef();
                    if (BigInteger.ZERO.compareTo(coefficient) == 0) {
                        continue;
                    }
                    Double newdegre = degre + currentDegre;
                    MonomX olderMonomX = (MonomX) result.getMonoms().get(newdegre);
                    if (olderMonomX != null) {
                        BigInteger co1 = (currentCoef.multiply(coefficient).add(olderMonomX.getCoef())).mod(getP());
                        result.monoms.put(newdegre, new MonomX(co1, degre + currentDegre, getP()));
                    } else {
                        BigInteger co1 = (currentCoef.multiply(coefficient)).mod(getP());
                        result.monoms.put(newdegre, new MonomX(co1, degre + currentDegre, getP()));
                    }
                }
            }
        }
        result.reBold();
        return result;
    }

    public PolynomX[] divide(PolynomX a) throws NoCoefficientException, CloneNotSupportedException {

        PolynomX quotien = PolynomX.createZERO(getP());
        return divide(a, quotien);
        /*MonomX current = monoms.values().iterator().next();
        MonomX aMonomX = a.getMonoms().values().iterator().next();
        if (DEB) {
        System.out.println("current=" + current + " aMonom=" + aMonomX + " currentdegre=" + current.getDegre() + " degre=" + aMonom.getDegre());
        }
        //MonomX q1 = new MonomX(current.getCoef().divideAndRemainder(aMonom.getCoef())[0], current.getDegre() - aMonom.getDegre(), getP());
        MonomX q1 = new MonomX((current.getCoef().multiply(schoofOps.inv( aMonom.getCoef(),getP()))).mod(getP()), current.getDegre() - aMonom.getDegre(), getP());
        if (DEB) {
        System.out.println("q1...= " + q1);
        }
        PolynomX reste = this.subtract(a.multiply(q1.toPolynomX()));
        if (DEB) {
        System.out.println("reste= " + reste);
        }
        if (DEB) {
        System.out.println("reste.getMonoms().values().iterator().next().getDegre() >= a.getDegre() " + (reste.getMonoms().values().iterator().next().getDegre() >= aMonom.getDegre()));
        System.out.println(reste.getMonoms().values().iterator().next().getDegre() + ">= " + a.getDegre());
        }
        if (reste.getMonoms().values().iterator().next().getDegre() >= a.getDegre()) {
        PolynomX q1PolynomX = q1.toPolynomX();
        if (DEB) {
        System.out.println("q1PolynomX= " + q1PolynomX);
        }
        if (quotien == null) {
        quotien = q1PolynomX;
        } else {
        if (DEB) {
        System.out.println("quotien..1= " + quotien);
        }
        quotien = quotien.add(q1PolynomX);
        if (DEB) {
        System.out.println("quotien..2= " + quotien);
        }
        }
        if (DEB) {
        System.out.println("reste.divide(a, quotien) = " + reste + ".divide(" + a + "," + quotien + ")");
        }
        // quotien.reBold();
        return reste.divide(a, quotien);
        }
        if (DEB) {
        System.out.println("quotien= " + quotien + " restye..." + reste);
        }
        quotien.reBold();
        reste.reBold();
        return new PolynomX[]{quotien, reste};*/
    }

    public PolynomX[] divide(PolynomX a, final PolynomX quotien) throws NoCoefficientException, CloneNotSupportedException {
        if (DEB) {
            System.out.println("a= " + a + "\nq1= " + quotien);
        }
        MonomX current = (MonomX) getMonoms().values().iterator().next();
        MonomX aMonomX = (MonomX) a.getMonoms().values().iterator().next();
        if (DEB) {
            System.out.println("aMonom=" + aMonomX + "  " + aMonomX.getCoef());
        }
        //MonomX q1 = new MonomX((current.getCoef().divideAndRemainder(aMonom.getCoef()))[0], current.getDegre() - aMonom.getDegre(), getP());
        MonomX q1 = new MonomX((current.getCoef().multiply(schoofOps.inv(aMonomX.getCoef(), getP()))).mod(getP()), current.getDegre() - aMonomX.getDegre(), getP());
        if (DEB) {
            System.out.println("q1= " + q1);
        }
        PolynomX a1 = this.subtract(a.multiply(q1.toPolynom()));
        if (DEB) {
            System.out.println("a1= " + a1);
            System.out.println("aMonom.getDegre()= " + aMonomX.getDegre());
            System.out.println("a1.getMonoms().values().iterator().next().getDegre()= " + a1.getMonoms().values().iterator().next().getDegre());
        }
        if (a1.getMonoms().values().iterator().next().getDegre() >= aMonomX.getDegre()) {
            quotien.addInternal(q1.toPolynom());
            quotien.reBold();
            if (DEB) {
                System.out.println("quotien= " + quotien + "\n reste " + a1);
            }
            return a1.divide(a, quotien);
        } else {
            PolynomX currentQuotien = q1.toPolynom();
            if (DEB) {
                System.out.println("currentQuotien= " + currentQuotien);
            }
            quotien.addInternal(currentQuotien);
            if (DEB) {
                System.out.println("quotien = quotien.add(currentQuotien);= " + quotien);
            }
        }
        PolynomX reste = a1;
        if (DEB) {
            System.out.println("quotien= " + quotien + "\n rest-->" + reste);
        }
        quotien.reBold();
        reste.reBold();
        PolynomX[] result = new PolynomX[]{quotien, reste};
        return result;
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

    public void addInternal(PolynomX toPolynomX) throws CloneNotSupportedException {
        for (Map.Entry<Double, Monom> entry : toPolynomX.getMonoms().entrySet()) {
            Double degre = entry.getKey();
            MonomX monom = (MonomX) entry.getValue();
            if (monom == null) {
                continue;
            }
            BigInteger coef = monom.getCoef();
            MonomX currentMonomX = (MonomX) getMonoms().get(monom.getDegre());
            if (currentMonomX != null) {
                BigInteger currentCoef = currentMonomX.getCoef();
                if (currentCoef != null && currentCoef.compareTo(BigInteger.ZERO) != 0) {
                    monoms.put(degre, new MonomX(currentCoef.add(coef), degre, getP()));
                } else {
                    //throw new NoCoefficientException(this + " at " + degre);
                    monoms.put(degre, new MonomX(BigInteger.ZERO, degre, getP()));
                }
            } else {
                monoms.put(degre, (Monom) monom.clone());
            }
        }
        reBold();
    }

    protected void subtractInternal(PolynomX other) throws NoCoefficientException, CloneNotSupportedException {
        for (Map.Entry<Double, Monom> entry : other.getMonoms().entrySet()) {
            Double degre = entry.getKey();
            MonomX currentMonomX = (MonomX) entry.getValue();
            MonomX monom = (MonomX) getMonoms().get(degre);
            if (currentMonomX != null) {
                BigInteger coef1 = monom.getCoef();
                BigInteger currentCoef = currentMonomX.getCoef();
                if (coef1 != null && coef1.compareTo(BigInteger.ZERO) != 0) {
                    BigInteger ez = coef1.subtract(currentCoef);
                    monoms.put(degre, new MonomX(ez, degre, getP()));
                } else {
                    monoms.put(degre, new MonomX(BigInteger.ZERO.subtract(currentCoef), degre, getP()));
                }
            } else {
                monoms.put(degre, monom);
            }
        }
        for (Map.Entry<Double, Monom> entry : other.getMonoms().entrySet()) {
            Double degre = entry.getKey();
            MonomX monom = (MonomX) entry.getValue();
            BigInteger coef = monom.getCoef();
            MonomX currentMonomX = (MonomX) getMonoms().get(degre);
            if (currentMonomX == null) {
                monoms.put(degre, new MonomX(BigInteger.ZERO.subtract(coef), degre, getP()));
            }
        }
        reBold();
    }

    public BigInteger fSmall(BigInteger x) {
        BigInteger fdex = null;
        for (Map.Entry<Double, Monom> entry : monoms.entrySet()) {
            Double key = entry.getKey();
            MonomX value = (MonomX) entry.getValue();
            if (fdex != null) {
                fdex = fdex.add(value.getCoef().multiply(x.pow(value.getDegre().intValue()))).mod(getP());
            } else {
                fdex = value.getCoef().multiply(x.pow(value.getDegre().intValue())).mod(getP());
            }
        }
        return fdex;
    }

    public PolynomX pow(int exponent) throws NoCoefficientException, CloneNotSupportedException {
        PolynomX result = createONE(getP());
        if (exponent == 0) {
            return result;
        }

        for (int i = 1; i <= exponent; i++) {
            result = result.multiply((PolynomX) clone());
        }
        result.reBold();
        return result;
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
                MonomX value = (MonomX) entry.getValue();
                MonomX value2 = (MonomX) o.monoms.get(key);
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
        final PolynomX other = (PolynomX) obj;
        if (getMonoms().size() != other.getMonoms().size()) {
            return false;
        }
        for (Map.Entry<Double, Monom> entry : getMonoms().entrySet()) {
            Double key = entry.getKey();
            Monom value = entry.getValue();
            Monom other2 = other.getMonoms().get(key);
            if (other2 != null) {
                if (!value.equals(other2)) {
                    return false;
                }
            }

        }

        return true;
    }

    public BigInteger f(BigInteger x) {
        BigInteger result = null;
        for (Map.Entry<Double, Monom> entry : monoms.entrySet()) {
            Double key = entry.getKey();
            MonomX value = (MonomX) entry.getValue();
            BigInteger cof = value.getCoef();
            if (result == null) {
                result = cof.multiply(x.modPow(new BigInteger("" + key.intValue()), getP()));
            } else {
                result = result.add(cof.multiply(x.modPow(new BigInteger("" + key.intValue()), getP())));
            }
        }
        return result.mod(getP());
    }

    PolynomX divideByScalar(BigInteger multiply) {
        BigInteger coef = schoofOps.inv(multiply, getP());
        PolynomX result = new PolynomX(this.getP(), null);
        for (Map.Entry<Double, Monom> entry : getMonoms().entrySet()) {
            Double degre = entry.getKey();
            Monom value = entry.getValue();
            if (value instanceof MonomX) {
                MonomX monom = (MonomX) value;
                BigInteger coefficient = monom.getCoef();
                if (BigInteger.ZERO.compareTo(coefficient) == 0) {
                    continue;
                }
                monoms.put(degre, new MonomX(coef.multiply(coefficient), degre, getP()));
            }
        }
        result.reBold();
        return result;
    }

    PolynomX multiplyCoef(BigInteger coef) {
        PolynomX result = new PolynomX(getP(), null);
        for (Map.Entry<Double, Monom> entry : monoms.entrySet()) {
            Double key = entry.getKey();
            MonomX value = (MonomX) entry.getValue();
            result.monoms.put(key, new MonomX(coef.multiply(value.getCoef()), key, getP()));
        }
        return result;

    }

    public LinkedList<PolynomY> getProducts() {
        return products;
    }

    public void setProducts(LinkedList<PolynomY> products) {
        this.products = products;
    }

}
