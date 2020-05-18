/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exthread;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author LUUQUAN-PC
 */
public class MainQuayso {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a=sc.nextLine();
        Quayso aQuayso = new Quayso();
        aQuayso.start();
        aQuayso.end();
       // String b=sc.nextLiyne();
       // aQuayso.end();
    //    System.out.print(aQuayso.getcount());
        
        
    }
}
