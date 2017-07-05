/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import fr.businessintegrator.schoof.SchoofOperations;
import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public abstract class Polynom extends Monom  implements Comparable<Polynom> {
    
    protected static boolean DEB = false;
 
    SchoofOperations schoofOps = new SchoofOperations();
    protected Map<Double, Monom> monoms = null;
    /**
     * Creates Sum of polynoms with monoms.
     *
     * @param p
     * @param monoms
     */
    public Polynom(BigInteger p, Monom... monoms) {
        super(p);
        this.monoms = new TreeMap(new MonomComparator());
        Double max = 0.0;
        setCoef(BigInteger.ZERO);
        if (monoms != null) {
            for (Monom monom : monoms) {
                this.monoms.put(monom.getDegre(), monom);
                if (max < monom.getDegre()) {
                    max = monom.getDegre();
                }
                setCoef(BigInteger.ONE);
            }
            setDegre(max);
        }
    }


   

    protected Polynom(BigInteger p) {
        super(p);
    }

   public Map<Double, Monom> getMonoms() {
        return monoms;
    }
       
}
