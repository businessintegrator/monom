/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.util.Comparator;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class BinomComparator implements Comparator<BinomDegre> {

    @Override
    public int compare(BinomDegre d1, BinomDegre d2) {
        if (d1 == d2) {
            return 0;
        }
        if (d1 == null) {
            return -1;
        }
        if (d2 == null) {
            return 1;
        }
        int result1 = d1.getDegreX().compareTo(d2.getDegreX());
        int result2 = d1.getDegreY().compareTo(d2.getDegreY());

        if (result2 == 0) {
            return result1;
        }
        if (result1 == 0) {
            return result2;
        } else {
            return result1;
        }

    }

}
