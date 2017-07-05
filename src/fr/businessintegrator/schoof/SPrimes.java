/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.schoof;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class SPrimes {
    private final static String filePrimes = "C:/primes/512/all.txt" ;
    private BigInteger p;
    private BigInteger q;
    private BigInteger N;
    private List<BigInteger> productOf_l;
    private List<BigInteger> S;
    SchoofOperations ops = null;
    BufferedReader reader = null;
    private final static boolean DEBUG = false;

    public SPrimes(BigInteger p, int n) throws FileNotFoundException {
        this.p = p;
        this.q = p.pow(n);
        S = new ArrayList<>();
        ops = new SchoofOperations();
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePrimes)));
    }


   private void initialize() throws IOException{
       
       N = BigInteger.ONE;
       BigInteger r = SchoofOperations.QUATRE.multiply(ops.racine(q, q.add(BigInteger.ONE)));
       while(r.compareTo(N) < 0 ){
           BigInteger l = loadNext();
           N = N.multiply(l);
       }
       
   }    

    private BigInteger loadNext() throws IOException {
        String line = reader.readLine();
        while(line!= null ){
            int idx = line.indexOf("kcss");
            if(idx != 0){
                String thepr_me =  line.substring(idx);
                if(DEBUG){
                    System.out.println("The new file is "+thepr_me);
                }
                return new BigInteger(thepr_me,SchoofOperations.RADIX);
            }
           line = reader.readLine();
        }
        return null;
    }



   public void terminate() throws IOException {
       if(reader != null){
         reader.close();
       }
   }
}
