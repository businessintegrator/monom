/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.businessintegrator.polynom;

import java.math.BigInteger;

/**
 *
 * @author Tiaray RAFARALAHITSIMBA
 */
public class Psi {

    private final static boolean DEBUG = false;
    private final static BigInteger ZERO = BigInteger.ZERO;
    private final static BigInteger UN = BigInteger.ONE;
    private final static BigInteger DEUX = new BigInteger("2");
    private final static BigInteger TROIS = new BigInteger("3");
    private final static BigInteger QUATRE = new BigInteger("4");
    private final static BigInteger CINQ = new BigInteger("5");
    private final static BigInteger SIX = new BigInteger("6");
    private final static BigInteger HUIT = new BigInteger("8");
    private final static BigInteger DOUZE = new BigInteger("12");
    private final static BigInteger VINGT = new BigInteger("20");
    private BigInteger a;
    private BigInteger b;
    private BigInteger p;

    public Psi(BigInteger a, BigInteger b, BigInteger p) {
        this.a = a;
        this.b = b;
        this.p = p;
    }

    public RationalPolynomX pside(BigInteger i, BigInteger x, BigInteger y) throws NoCoefficientException, CloneNotSupportedException {

        if (ZERO.compareTo(i) == 0) {
            PolynomX result = PolynomX.createZERO(this.p);
            if (DEBUG) {
                System.out.println("pside(" + i + ") " + result);
            }
            return new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
        }
        if (UN.compareTo(i) == 0) {
            if (DEBUG) {
                System.out.println("pside(" + i + ") ");
            }
            PolynomX result = PolynomX.createONE(this.p);
            if (DEBUG) {
                System.out.println("pside(" + i + ") " + result);
            }
            return new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
        }
        if (DEUX.compareTo(i) == 0) {
            PolynomX y2 = new PolynomX(p, new MonomX(DEUX.multiply(y), 0.0, p));

            PolynomX result = new PolynomX(p, new MonomX(UN, 0.0, p));
            if (DEBUG) {
                System.out.println("pside(" + i + ") " + result);
            }
            return new RationalPolynomX(result.multiply(y2), PolynomX.createONE(this.p),this.p);

        }

        if (TROIS.compareTo(i) == 0) {
            PolynomX result = new PolynomX(p,
                    new MonomX(TROIS, 4.0, p),
                    new MonomX(SIX.multiply(a), 2.0, p),
                    new MonomX(DOUZE.multiply(b), 1.0, p),
                    new MonomX(ZERO.subtract(a.modPow(DEUX,p)), 0.0, p)
            );
            if (DEBUG) {
                System.out.println("pside(" + i + ") " + result);
            }
            return new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);

        }

        if (QUATRE.compareTo(i) == 0) {
            PolynomX y4 = new PolynomX(p, new MonomX(QUATRE.multiply(y), 0.0, p));
            PolynomX result = new PolynomX(p,
                    new MonomX(UN, 6.0, p), //x^6
                    new MonomX(CINQ.multiply(a), 4.0, p), // 5a x^4 
                    new MonomX(VINGT.multiply(b), 3.0, p),//20 b x^3
                    new MonomX(ZERO.subtract(CINQ.multiply(a.modPow(DEUX,p))), 2.0, p), //-5 a² x^2
                    new MonomX(ZERO.subtract(QUATRE.multiply(a).multiply(b)), 1.0, p), // -4 abx
                    new MonomX(ZERO.subtract(HUIT.multiply(b.modPow(DEUX,p))).subtract(a.modPow(TROIS,p)), 0.0, p) //-8 b² -a^3
            ).multiply(y4);

            if (DEBUG) {
                System.out.println("pside(" + i + ") " + result);
            }
            return new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);

        }
        if (QUATRE.compareTo(i) < 0) {
            BigInteger[] dividing = i.divideAndRemainder(DEUX);
            BigInteger n = dividing[0];
            BigInteger r = dividing[1];
            if (DEBUG) {
                System.out.println("\\\\u03a8 pside(" + i + ") n=" + n + " " + r);
            }
            if (r.compareTo(ZERO) == 0 && n.compareTo(DEUX) > 0) {
                //i = 2n
                if (DEBUG) {
                    System.out.println("pside(" + i + ") 2.n= 2." + n + " ");
                }

                RationalPolynomX psidenmoins2 = pside(n.subtract(DEUX), x, y);
                if (DEBUG) {
                    System.out.println("pside n-2 =" + psidenmoins2);
                }
                RationalPolynomX psidenmoins1 = pside(n.subtract(UN), x, y);
                if (DEBUG) {
                    System.out.println("pside n-1 =" + psidenmoins1);
                }
                RationalPolynomX psiden = pside(n, x, y);
                if (DEBUG) {
                    System.out.println("pside n =" + psidenmoins1);
                }
                RationalPolynomX psidenplus1 = pside(n.add(UN), x, y);
                if (DEBUG) {
                    System.out.println("pside n+1 =" + psidenplus1);
                }
                RationalPolynomX psidenplus2 = pside(n.add(DEUX), x, y);
                if (DEBUG) {
                    System.out.println("pside n+2 =" + psidenplus2);
                }

                RationalPolynomX psidenmoins1Carre = psidenmoins1.pow(2, this.p);
                RationalPolynomX psidenplus1Carre = psidenplus1.pow(2, this.p);
                RationalPolynomX left = psidenplus2.multiply(psidenmoins1Carre);
                RationalPolynomX right = psidenmoins2.multiply(psidenplus1Carre);

                //pair y2 n = ynHyn+2 yn-12 - yn-2 yn+12 L
                RationalPolynomX result = left.subtract(right);
                //PolynomX y2 = new PolynomX(p, new MonomX(DEUX.multiply(y), 0, p));
                result = psiden.multiply(result);
                //result = result.divideCoefficient(DEUX.multiply(y));
                PolynomX y2 = new PolynomX(p, new MonomX(DEUX.multiply(y), 0.0, p));
                return new RationalPolynomX(result, y2,this.p);
                
                //return result;
            } else if (r.compareTo(ZERO) != 0 && n.compareTo(UN) > 0) {

                //impair i = 2n +1
                if (DEBUG) {
                    System.out.println("pside(" + i + ") 2.n= 2." + n + "+1 ");
                }
                RationalPolynomX nMoins1 = pside(n.subtract(UN), x, y);
                RationalPolynomX psidn = pside(n, x, y);
                RationalPolynomX nPlus1 = pside(n.add(UN), x, y);
                RationalPolynomX nplus2 = pside(n.add(DEUX), x, y);
                
                if (DEBUG) {
                    System.out.println("psi(n=" + n + ") =" + psidn);
                }
                RationalPolynomX nCube = psidn.pow(3, this.p);
                if (DEBUG) {
                    System.out.println("psi(n)^3 =" + nCube);
                }
                if (DEBUG) {
                    System.out.println("psi(n+2)" + n.add(DEUX) + "=" + nplus2);
                }
                RationalPolynomX other = nplus2.multiply(psidn);

               
                if (DEBUG) {
                    System.out.println("psi(n+2)" + n.add(UN) + "=" + nPlus1);
                }
                RationalPolynomX nPlus1Aucube = nPlus1.pow(3, this.p);
                if (DEBUG) {
                    System.out.println("psi(n+1=" + n.add(UN) + ")^3 =" + nPlus1Aucube);
                }
                if (DEBUG) {
                    System.out.println("psi(n-1)" + n.subtract(UN) + " =" + nMoins1);
                }
                RationalPolynomX other2 = nMoins1.multiply(nPlus1Aucube);

                //pair y2 n = ynHyn+2 yn-12 - yn-2 yn+12 L
                RationalPolynomX result = other.subtract(other2);

                if (DEBUG) {
                    System.out.println("pside(" + i + ") " + result);
                }
                return new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);

            } else {
                throw new RuntimeException("111");
            }
        }
        return null;
    }

    public RationalPolynomX psidFi(BigInteger i, BigInteger x, BigInteger y, CountingPointcontext ct) throws NoCoefficientException, CloneNotSupportedException {
        if (ct.howManyZero() > 1) {
            throw new RuntimeException(x + "," + y + " has low order" + i);
        }
        if (ZERO.compareTo(i) == 0) {
            RationalPolynomX one = ct.get(i);
            if (one == null) {
                PolynomX result = PolynomX.createZERO(this.p);
                if (DEBUG) {
                    System.out.println("pside(" + i + ") " + result);
                }

                one = new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
                ct.put(i, one);
            }

            BigInteger resOne[] = one.f(x);
            if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                ct.addOrder(x, y, i);
            }
            return one;
        }
        if (UN.compareTo(i) == 0) {
            RationalPolynomX one = ct.get(i);
            if (one == null) {
                if (DEBUG) {
                    System.out.println("pside(" + i + ") ");
                }
                PolynomX result = PolynomX.createONE(this.p);
                if (DEBUG) {
                    System.out.println("pside(" + i + ") " + result);
                }

                one = new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
                ct.put(i, one);
            }

            BigInteger resOne[] = one.f(x);
            if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                ct.addOrder(x, y, i);
            }
            return one;
        }
        if (DEUX.compareTo(i) == 0) {
            RationalPolynomX one = ct.get(i);
            if (one == null) {
                PolynomX y2 = new PolynomX(p, new MonomX(DEUX.multiply(y), 0.0, p));

                PolynomX result = new PolynomX(p, new MonomX(UN, 0.0, p));
                if (DEBUG) {
                    System.out.println("pside(" + i + ") " + result);
                }

                one = new RationalPolynomX(result.multiply(y2), PolynomX.createONE(this.p),this.p);
                ct.put(i, one);
            }

            BigInteger resOne[] = one.f(x);
            if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                ct.addOrder(x, y, i);
            }
            return one;
        }

        if (TROIS.compareTo(i) == 0) {
            RationalPolynomX one = ct.get(i);
            if (one == null) {
                PolynomX result = new PolynomX(p,
                        new MonomX(TROIS, 4.0, p),
                        new MonomX(SIX.multiply(a), 2.0, p),
                        new MonomX(DOUZE.multiply(b), 1.0, p),
                        new MonomX(ZERO.subtract(a.modPow(DEUX,p)), 0.0, p)
                );
                if (DEBUG) {
                    System.out.println("pside(" + i + ") " + result);
                }

                one = new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
                ct.put(i, one);
            }

            BigInteger resOne[] = one.f(x);
            if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                ct.addOrder(x, y, i);
            }
            return one;

        }

        if (QUATRE.compareTo(i)
                == 0) {
            RationalPolynomX one = ct.get(i);
            if (one == null) {
                PolynomX y4 = new PolynomX(p, new MonomX(QUATRE.multiply(y), 0.0, p));
                PolynomX result = new PolynomX(p,
                        new MonomX(UN, 6.0, p), //x^6
                        new MonomX(CINQ.multiply(a), 4.0, p), // 5a x^4 
                        new MonomX(VINGT.multiply(b), 3.0, p),//20 b x^3
                        new MonomX(ZERO.subtract(CINQ.multiply(a.modPow(DEUX,p))), 2.0, p), //-5 a² x^2
                        new MonomX(ZERO.subtract(QUATRE.multiply(a).multiply(b)), 1.0, p), // -4 abx
                        new MonomX(ZERO.subtract(HUIT.multiply(b.modPow(DEUX,p))).subtract(a.modPow(TROIS,p)), 0.0, p) //-8 b² -a^3
                ).multiply(y4);

                if (DEBUG) {
                    System.out.println("pside(" + i + ") " + result);
                }

                one = new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
                ct.put(i, one);
            }
            BigInteger resOne[] = one.f(x);
            if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                ct.addOrder(x, y, i);
            }
            return one;

        }

        if (QUATRE.compareTo(i) < 0) {
            BigInteger[] dividing = i.divideAndRemainder(DEUX);
            BigInteger n = dividing[0];
            BigInteger r = dividing[1];
            if (DEBUG) {
                System.out.println("\\\\u03a8 pside(" + i + ") n=" + n + " " + r);
            }
            if (r.compareTo(ZERO) == 0 && n.compareTo(DEUX) > 0) {
                RationalPolynomX one = ct.get(i);
                if (one == null) {
                    //i = 2n
                    if (DEBUG) {
                        System.out.println("pside(" + i + ") 2.n= 2." + n + " ");
                    }

                    RationalPolynomX psidenmoins2 = psidFi(n.subtract(DEUX), x, y, ct);
                    if (DEBUG) {
                        System.out.println("pside n-2 =" + psidenmoins2);
                    }
                    RationalPolynomX psidenmoins1 = psidFi(n.subtract(UN), x, y, ct);
                    if (DEBUG) {
                        System.out.println("pside n-1 =" + psidenmoins1);
                    }
                    RationalPolynomX psiden = psidFi(n, x, y, ct);
                    if (DEBUG) {
                        System.out.println("pside n =" + psidenmoins1);
                    }
                    RationalPolynomX psidenplus1 = psidFi(n.add(UN), x, y, ct);
                    if (DEBUG) {
                        System.out.println("pside n+1 =" + psidenplus1);
                    }
                    RationalPolynomX psidenplus2 = psidFi(n.add(DEUX), x, y, ct);
                    if (DEBUG) {
                        System.out.println("pside n+2 =" + psidenplus2);
                    }

                    RationalPolynomX psidenmoins1Carre = psidenmoins1.pow(2, this.p);
                    RationalPolynomX psidenplus1Carre = psidenplus1.pow(2, this.p);

                    RationalPolynomX left = psidenplus2.multiply(psidenmoins1Carre);
                    RationalPolynomX right = psidenmoins2.multiply(psidenplus1Carre);

                    //pair y2 n = ynHyn+2 yn-12 - yn-2 yn+12 L
                    RationalPolynomX result = left.subtract(right);
                    //PolynomX y2 = new PolynomX(p, new MonomX(DEUX.multiply(y), 0, p));
                    result = psiden.multiply(result);
                    //result = result.divideCoefficient(DEUX.multiply(y));
                    PolynomX y2 = new PolynomX(p, new MonomX(DEUX.multiply(y), 0.0, p));

                    one = new RationalPolynomX(result, y2,this.p);
                    ct.put(i, one);
                }

                BigInteger resOne[] = one.f(x);
                if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                    ct.addOrder(x, y, i);
                }
                return one;
                //return result;
            } else if (r.compareTo(ZERO) != 0 && n.compareTo(UN) > 0) {
                RationalPolynomX one = ct.get(i);
                if (one == null) {
                    //impair i = 2n +1
                    if (DEBUG) {
                        System.out.println("pside(" + i + ") 2.n= 2." + n + "+1 ");
                    }
                    RationalPolynomX psidn = psidFi(n, x, y, ct);
                    if (DEBUG) {
                        System.out.println("psi(n=" + n + ") =" + psidn);
                    }
                    RationalPolynomX nCube = psidn.pow(3, this.p);
                    if (DEBUG) {
                        System.out.println("psi(n)^3 =" + nCube);
                    }
                    RationalPolynomX nplus2 = psidFi(n.add(DEUX), x, y, ct);
                    if (DEBUG) {
                        System.out.println("psi(n+2)" + n.add(DEUX) + "=" + nplus2);
                    }
                    RationalPolynomX other = nplus2.multiply(nCube);

                    RationalPolynomX nPlus1 = psidFi(n.add(UN), x, y, ct);
                    if (DEBUG) {
                        System.out.println("psi(n+2)" + n.add(UN) + "=" + nPlus1);
                    }
                    RationalPolynomX nPlus1Aucube = nPlus1.pow(3, this.p);
                    if (DEBUG) {
                        System.out.println("psi(n+1=" + n.add(UN) + ")^3 =" + nPlus1Aucube);
                    }
                    RationalPolynomX nMoins1 = psidFi(n.subtract(UN), x, y, ct);
                    if (DEBUG) {
                        System.out.println("psi(n-1)" + n.subtract(UN) + " =" + nMoins1);
                    }
                    RationalPolynomX other2 = nMoins1.multiply(nPlus1Aucube);

                    //pair y2 n = ynHyn+2 yn-12 - yn-2 yn+12 L
                    RationalPolynomX result = other.subtract(other2);

                    if (DEBUG) {
                        System.out.println("pside(" + i + ") " + result);
                    }

                    one = new RationalPolynomX(result, PolynomX.createONE(this.p),this.p);
                    ct.put(i, one);
                }
                BigInteger resOne[] = one.f(x);
                if (resOne[0].compareTo(BigInteger.ZERO) == 0 && resOne[1].compareTo(BigInteger.ZERO) == 0) {
                    ct.addOrder(x, y, i);
                }
                return one;

            } else {
                throw new RuntimeException("111");
            }
        }

        return null;
    }

    /**
     * Multiplication scalaire n.Point(x,y) modulo p
     *
     * @param x coordonnés x
     * @param y coordonnés y
     * @param p caractéristique
     * @param n multiplicteur 
     * @return Numérateur et dénominateur du polynôme RationalPolynomX psi.
     */
    public RationalPolynomX[] scalar(BigInteger x, BigInteger y, BigInteger n) throws NoCoefficientException, CloneNotSupportedException {

        RationalPolynomX psidn = pside(n, x, y);
        RationalPolynomX psidnplus1 = pside(n.add(UN), x, y);
        RationalPolynomX psidnmoins1 = pside(n.subtract(UN), x, y);
        RationalPolynomX psidnmoins2 = pside(n.subtract(DEUX), x, y);
       // PolynomX polynomX = new PolynomX(p, new MonomX(UN, 1.0, p));
        RationalPolynomX nx = new RationalPolynomX((psidn.pow(2, this.p).multiplyX(p).subtract(psidnmoins1.multiply(psidnplus1))), psidn.pow(2, this.p),p);
        //RationalPolynomX right = new RationalPolynomX(, psidn.pow(2, this.p),p);

        //RationalPolynomX nx = left.subtract(right);

        RationalPolynomX other = pside(n.add(DEUX), x, y).multiply(psidnmoins1.pow(2, this.p));
        RationalPolynomX other2 = psidnmoins2.multiply(psidnplus1.pow(2, this.p));

        RationalPolynomX psidnCube = pside(n, x, y).pow(3, this.p);
        RationalPolynomX denominateur = psidnCube.multiply(new PolynomX(p, new MonomX(QUATRE.multiply(y), 0.0, p)));
        RationalPolynomX ny = new RationalPolynomX(other.subtract(other2), denominateur,p);

        //return new RationalPolynomX[]{ny, nx};
        return new RationalPolynomX[]{nx, ny};
    }

    public BigInteger[] fn(BigInteger x, BigInteger y, BigInteger n) throws NoCoefficientException, CloneNotSupportedException {
        BigInteger[] qr = n.divideAndRemainder(DEUX);
        final CountingPointcontext ct = new CountingPointcontext();
        RationalPolynomX psiden = ct.get(n);
        if (psiden == null) {
            psiden = psidFi(n, x, y, ct);
            ct.put(n, psiden);
        }
        if (qr[1].compareTo(BigInteger.ZERO) == 0) {

            return psiden.fEven(x, y);
        } else {
            return  psidFi(n, x, y, ct).f(x);

        }
    }
}
