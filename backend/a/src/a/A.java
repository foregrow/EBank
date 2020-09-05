package a;

import java.util.concurrent.ThreadLocalRandom;

public class A {

	public static void main(String[] args) {
		long min = (long) Math.pow(10, 13 - 1);
		long brojj = ThreadLocalRandom.current().nextLong(min, min * 10);
	    String broj = Long.toString(brojj);
	    StringBuilder str = new StringBuilder();
	    str.append(broj);
	    int suma = 0;
	    System.out.println(broj);
	    for(int i=0;i<broj.length();i++) {
			//System.out.println(broj.charAt(i));
			int br=Character.getNumericValue(broj.charAt(i));
			suma+=br;
		}
	    int checksum = suma % 97;
	    System.out.println("check"+checksum);
	    str.append(checksum);
	    System.out.println("str"+str);
	    /*
	     * int suma = 0;
		for(int i=0;i<brojRacuna.length();i++) {
			int cifra = Character.getNumericValue(brojRacuna.charAt(i));
			suma+=cifra;
		}
		int checksum = suma % 97;
	     */

	}

}
