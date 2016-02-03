/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouvement;
import static java.awt.SystemColor.window;
   import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
/**
 *
 * @author Fort
 */
public class JavaApplication11 {





  public static void main(String[] args) throws Exception {
       Scanner sc = new Scanner(System.in);

    System.out.println("Saisissez une chaîne : ");

    

    String num ;
    num = Integer.toString(sc.nextInt());
    
    URL url = new URL("http://www.ece.fr/~pneveu/welcome.php?num="+num);
    URLConnection conn = url.openConnection();
    conn.setDoOutput(true);
    //OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

    //writer.write("welcome.php?num=6");
    //writer.flush();
    String line;
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    while ((line = reader.readLine()) != null) {
      System.out.println(line);
    
      
    }
    //writer.close();
    reader.close();

  }
}
    

