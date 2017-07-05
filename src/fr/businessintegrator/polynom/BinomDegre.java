/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.util.Objects;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 *
 */
public class BinomDegre implements Comparable<BinomDegre> {

    static BinomDegre ZERO = new BinomDegre(0.0, 0.0);
    private Double degreX = 0.0;
    private Double degreY = 0.0;

    public BinomDegre(Double x, Double y) {
        this.degreX = x;
        this.degreY = y;
    }

    public Double getDegreX() {
        return degreX;
    }

    public void setDegreX(Double degreX) {
        this.degreX = degreX;
    }

    public Double getDegreY() {
        return degreY;
    }

    public void setDegreY(Double degreY) {
        this.degreY = degreY;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.degreX);
        hash = 11 * hash + Objects.hashCode(this.degreY);
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
        final BinomDegre other = (BinomDegre) obj;
        if (!this.degreX.equals(other.degreX)) {
            return false;
        }
        if (!this.degreY.equals(other.degreY)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " (x^" + degreX + "). (y^" + degreY + ")";
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    BinomDegre add(BinomDegre currentDegre) {
        return new BinomDegre(degreX + currentDegre.getDegreX(), degreY + currentDegre.getDegreY());
    }

    @Override
    public int compareTo(BinomDegre o) {
        int cx = this.degreX.compareTo(o.degreX);
        int cy = this.degreY.compareTo(o.degreY);
        if (cx == 0) {
            return cy;
        } else {
            return cx;
        }

    }

}
