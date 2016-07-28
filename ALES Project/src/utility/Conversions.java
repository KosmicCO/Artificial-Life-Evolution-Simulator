/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utility;

/**
 *
 * @author bhargav
 */
public class Conversions {
    
    public static int byteToInt(boolean[] b){
        
        int sum = 0;
        
        if(b.length == 8){
            
            for (int i = 0; i < 8; i++) {
                
                sum += b[i] ? Math.pow(2, 7 - i) : 0;
            }
        }
        
        return sum;
    }
}
