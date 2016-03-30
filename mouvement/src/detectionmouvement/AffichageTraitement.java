/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectionmouvement;

import envoidonnee.POST;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Fort
 */
public class AffichageTraitement {
    public JFrame jframe = new JFrame();
    public JLabel vidpanel = new JLabel();
    public JFrame jframe2 = new JFrame();
    public JLabel vidpanel2 = new JLabel();
     public JFrame jframe3 = new JFrame();
    public JLabel vidpanel3 = new JLabel();
    public Mat frame;

    public Size sz = new Size(640, 480);

    public VideoCapture camera;
    public int numero_camera;
    public POST send ; 
    FrameProcess Pro1;
    FrameProcess Pro2;
    FrameProcess Pro3;
    
    
   Mat imag = null;
    private ArrayList<Rect> array;
    
    public AffichageTraitement()
    {
        
    }
     private void InitializeFrame(JFrame jframe, JLabel vidpanel,String nom){
        jframe.setTitle(nom);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setContentPane(vidpanel);
        jframe.setSize(640, 480);
        jframe.setVisible(true);
     } 
    public AffichageTraitement(String nom, VideoCapture camera, int numero_camera)
    {   
        InitializeFrame(jframe,vidpanel,nom);
        InitializeFrame(jframe2,vidpanel2,nom+" "+2);
        InitializeFrame(jframe3,vidpanel3,nom+" "+3);
        
        
        frame = new Mat();
        sz = new Size(640, 480);
        this.camera = camera ;
        this.numero_camera = numero_camera;
      //  send = new POST();
        Pro1 = new FrameProcess(jframe,vidpanel);
        Pro2 = new FrameProcess(jframe2,vidpanel2);
        Pro3 = new FrameProcess(jframe3,vidpanel3);
        
        
    }
    
    public void gestionCamera(){
        if (camera.read(frame)) {
                Imgproc.resize(frame, frame, sz);
                //System.out.println(frame.size().toString());
                Mat frame1 = frame.submat(0,160, 0, 640);
                Mat frame2 = frame.submat(160,320, 0, 640);
                Mat frame3 = frame.submat(320,480, 0, 640);
                
                Pro1.traitementCam(frame1,numero_camera*10+1);
                Pro2.traitementCam(frame2,numero_camera*10+2);
                Pro3.traitementCam(frame3,numero_camera*10+3);
               
               // this.traitementCam(frame1,1);
               //this.traitementCam(frame2,2);
               
                
            }
    }
}
    
  
        
        
      
