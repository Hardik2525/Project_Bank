package BankingApp;

public class Class1 {

	  public static long ReturnAcc(){
		long max = 1000000000;
        long min = 1;
        long range = max - min + 1;
        long rand;
 
        // generate random numbers within 1 to 10
        
             rand = (long) ((Math.random() * range) + min);
 
            // Output is different everytime this code is executed
            return rand;

	}

}

