/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package envoidonnee;

import static java.awt.SystemColor.window;
   import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Fort
 */
public class POST {
    
String num;
private int compteur =0;
      

public POST()  {
 

   
}
public boolean comptage()
{
    
    compteur++;
    if(compteur ==10)
    {
 
        
               System.out.println("Valeur envoyée");
               compteur = 0;
                return true;
           }
    else
    { 
        return false;
    }
}

public void envoi(int num)
{
    BufferedReader reader = null;
    URL url = null;
    try {
        url = new URL("http://128.79.177.130/ppe/welcome.php?num="+num);//LOCAL : http://192.168.1.28/ppe/welcome.php?num= EN LIGNE http://128.79.177.130/ppe/welcome.php?num=
    } catch (MalformedURLException ex) {
        Logger.getLogger(POST.class.getName()).log(Level.SEVERE, null, ex);
    }
    URLConnection conn = null;
    try {
        conn = url.openConnection();
        conn.connect();
        reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        reader.close();
    } catch (IOException ex) {
        Logger.getLogger(POST.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}

}
