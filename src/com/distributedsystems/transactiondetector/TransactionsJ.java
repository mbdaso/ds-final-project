package com.distributedsystems.transactiondetector;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class TransactionsJ {
    public static final String[] elements={"0","1","2","3","4","5","6","7","8","9" ,"a",
"b","c","d","e","f","g","h","i","j","k","l","m","n ","o","p","q","r","s","t",
"u","v","w","x","y","z"};
    public static final String[] coins= {"EUR","$","POUNDS"};

    public TransactionsJ(){
        
    }
    public static void main(String[] args){
    	TransactionsJ caca = new TransactionsJ();
    	caca.transactions();
    	System.out.println(caca.transactions());
        
    }

    /*public String random_account_id(){
        //Return a random account number made of 12 characters.
        for (int i = 0; i < 12; i++) {
            Random random = new Random();
            int index = random.nextInt(elements.length);
            System.out.println(elements[index]);

        }
        return randomElement;
    }*/
    
    public String random_account_id(){
        String s="";
        //Return a random account number made of 12 characters.
        for (int i = 0; i < 12; i++) {
            Random random = new Random();
            int index = random.nextInt(elements.length);
            System.out.println(elements[index]);
            s+=elements[index];
        }
        //System.out.println(+randomElement);
        System.out.println(s);
        return s;
    }

    public String random_ammount(){
        //Return a random amount between 1.00 and 1000.00.
        Random randomGenerator = new Random();
        float randint = (randomGenerator.nextInt(100000) + 100)/100;
        System.out.println(+randint);
        String srandint = Float.toString(randint);
        return srandint;
    }
    public String random_coin() {
    	String c="";
        //Return a random COIN.

        Random random = new Random();
        int index = random.nextInt(coins.length);
        c+=coins[index];
     
        //System.out.println(+randomElement);
        System.out.println(c);
        return c;
    }
   
    //Dictionary
    public HashMap<String,String> transactions(){
    	HashMap<String,String> randomTransaction = new HashMap<>();
    	
    	randomTransaction.put(random_account_id(),random_account_id());
    	randomTransaction.put(random_ammount(),random_coin());
    	
    	return randomTransaction;
    }
    
}
