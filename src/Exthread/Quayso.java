/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exthread;

import java.util.Scanner;
    public class Quayso extends Thread{
    private int count=0;
    private boolean a=false;
   @Override
    public void run(){

        while (!a) {            
            count++;
            if (count>100){
                count=0;
            }
           //    System.out.println(count+"\t");
        }
     
  }
    public int getcount(){
        return count;
    }
    public void end(){
            try {
            Thread.sleep(3000);
            a=true;
       } catch (Exception e) {
       }       
        System.out.println(count);
    }   
}
    


