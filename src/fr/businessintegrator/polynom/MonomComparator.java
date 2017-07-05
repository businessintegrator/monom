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
public class MonomComparator implements Comparator<Double> {

    public MonomComparator() {
    }

    @Override
    public int compare(Double o1, Double o2) {
        if (o1 == o2) {
            return 0;
        }
        if (o2 == null) {
            return -1;
        }
        return o2.compareTo(o1);
    }

}
