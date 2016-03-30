/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 package detectionmouvement;
 
import org.opencv.core.Core;
import org.opencv.videoio.VideoCapture;
 
public class JavaCVPrjt01 {
   static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
   
   
 
    
    
 
    public static void main(String[] args) {
   
        //OUVERTURE DES FLUX VIDEOS//
        VideoCapture camera1 = new VideoCapture(1);
        //VideoCapture camera2 = new VideoCapture(1);
        //VideoCapture camera3 = new VideoCapture(2);
        //VideoCapture camera4 = new VideoCapture(3);
        ///////////////////////////////
        
        AffichageTraitement fenetre  = new AffichageTraitement("CAM1",camera1,1);
        //AffichageTraitement fenetre2 = new AffichageTraitement("CAM2",camera2,2);
        //AffichageTraitement fenetre3 = new AffichageTraitement("CAM3",camera3,3);
     //   AffichageTraitement fenetre4 = new AffichageTraitement("CAM4",camera4,4);
   
 
        while (true) {
            fenetre.gestionCamera();
         //   fenetre2.gestionCamera();
           // fenetre3.gestionCamera();
         //   fenetre4.gestionCamera();
        }
    }
 
  
} 