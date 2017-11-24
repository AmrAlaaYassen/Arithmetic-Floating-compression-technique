/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arthmeticcoding;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author amral
 */
public class Arithmetic {
     double low , high , range , code;
    int counter = 0 ;
    ArrayList <CharTag> chars = new ArrayList<CharTag>() ;
    Arithmetic () {
        low =code =  0 ;
        range = high = 1 ;

    }

    public  getProbability (String line) {
        int [] arr = new int [256] ;
        for (int i =0 ; i<line.length() ; i++ ) {
            arr[line.charAt(i)] ++ ;
        }

        double sum = 0 ;
        for (int i =0 ; i<arr.length ; i++ ) {
            if (arr[i] >0) {
                CharTag temp = new CharTag() ;
                temp.c = (char)i ;
                temp.probability  = (double)arr[i]/line.length() ;
                temp.lowest = sum ;
                temp.highest = sum+temp.probability ;
                sum += temp.probability ;
                chars.add(temp) ;
            }
        }
    }
    public String compress (String line)  {
        counter = line.length() ;
        double perviouslow = 0 ;
        this.getProbability(line);
        for (int i = 0 ; i <line.length() ; i++) {
            CharTag temp = new CharTag() ;

            for (int j=0 ; j<chars.size() ; j++ ) {
                if (line.charAt(i) == chars.get(j).c) {
                    temp = chars.get(j)  ;
                    break;
                }
            }

            low = perviouslow + (range*temp.lowest) ;
            high = perviouslow + (range*temp.highest) ;
            range = high - low ;
            perviouslow = low ;
            code = (low+high)/2 ;
        }
        String s = String.valueOf(code) ;

        try {
            PrintWriter out = new PrintWriter("C:\\Users\\amral\\Desktop\\com.txt");
            out.println(s);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return s ; 
    }

    public String decompress () {
        String s = null , output = "" ;
        try {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\amral\\Desktop\\com.txt"));
             s = in.readLine() ;
        } catch (Exception e) {
            e.printStackTrace();
        }

        code = Double.parseDouble(s) ;

        CharTag temp = null ;

        while (counter >0) {
            for (int i=0 ; i<chars.size() ; i ++ ) {
                if (code >= chars.get(i).lowest && code<=chars.get(i).highest) {
                    temp = chars.get(i) ;
                    output += temp.c ;
                    break;
                }
            }
            range = temp.highest - temp.lowest ;
            code = (double) ((code-temp.lowest)/range) ;
            counter-- ;
        }

        System.out.println(output);
        return output ; 
    }

    public void printChars () {
        for (int i=0 ; i<chars.size() ; i++ ) {
            System.out.println("char : " + chars.get(i).c + " probability : " + chars.get(i).probability+" lowst : "+chars.get(i).lowest +" highest : "+ chars.get(i).highest );
        }
    }
}


class CharTag {
    char c  ;
    double lowest , highest , probability ;
    CharTag () {
        c= 0 ;
        highest = lowest = probability = 0 ;
    }
}

    
}
