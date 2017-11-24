/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arthmeticcoding;

/**
 *
 * @author amral
 */
public class ArthmeticCoding {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Arithmetic x= new Arithmetic() ;

        x.compress("abcdefghmnlpqioz");
        x.printChars();
        System.out.println(x.high);
        x.decompress();
    }
    
}
