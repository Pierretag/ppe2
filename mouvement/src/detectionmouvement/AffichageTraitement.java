/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detectionmouvement;

import envoidonnee.POST;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Fort
 */
public class AffichageTraitement {
    public JFrame jframe;
    public JLabel vidpanel;
    public Mat frame;
    public Mat outerBox;
    public Mat diff_frame;
    public Mat tempon_frame;
    public ArrayList<Rect> array;
    public Size sz = new Size(640, 480);
    public int i;
    public VideoCapture camera;
    public int numero_camera;
    public POST send ; 
    
   Mat imag = null;
    
    public AffichageTraitement()
    {
        
    }
    
    public AffichageTraitement(String nom, VideoCapture camera, int numero_camera)
    {
        jframe = new JFrame(nom);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vidpanel = new JLabel();
        jframe.setContentPane(vidpanel);
        jframe.setSize(640, 480);
        jframe.setVisible(true);
        frame = new Mat();
        outerBox = new Mat();
        diff_frame = null;
        tempon_frame = null;
        array = new ArrayList<Rect>();
        sz = new Size(640, 480);
        i = 0;
        this.camera = camera ;
        this.numero_camera = numero_camera;
        send = new POST();
    }
    
    public void gestionCamera(){
        if (camera.read(frame)) {
                Imgproc.resize(frame, frame, sz);
                imag = frame.clone();
                outerBox = new Mat(frame.size(), CvType.CV_8UC1);
                Imgproc.cvtColor(frame, outerBox, Imgproc.COLOR_BGR2GRAY);
                Imgproc.GaussianBlur(outerBox, outerBox, new Size(3, 3), 0);
 
                if (i == 0) {
                    jframe.setSize(frame.width(), frame.height());
                    diff_frame = new Mat(outerBox.size(), CvType.CV_8UC1);
                    tempon_frame = new Mat(outerBox.size(), CvType.CV_8UC1);
                    diff_frame = outerBox.clone();
                }
 
                if (i == 1) {
                    Core.subtract(outerBox, tempon_frame, diff_frame);
                    Imgproc.adaptiveThreshold(diff_frame, diff_frame, 255,
                            Imgproc.ADAPTIVE_THRESH_MEAN_C,
                            Imgproc.THRESH_BINARY_INV, 5, 2);
                    array = detection_contours(diff_frame);
                    if (array.size() > 0) {
 
                        Iterator<Rect> it2 = array.iterator();
                        while (it2.hasNext()) {
                            Rect obj = it2.next();
                            Imgproc.rectangle(imag, obj.br(), obj.tl(),
                                    new Scalar(0, 255, 0), 1);
                            
                        }
 
                    }
                }
 
                i = 1;
 
                ImageIcon image = new ImageIcon(Mat2bufferedImage(imag));
                vidpanel.setIcon(image);
                vidpanel.repaint();
                tempon_frame = outerBox.clone();
 
            }
    }
    
      public  BufferedImage Mat2bufferedImage(Mat image) {
        MatOfByte bytemat = new MatOfByte();
        Imgcodecs.imencode(".jpg", image, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage img = null;
        try {
            img = ImageIO.read(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return img;
    }
 
        public  ArrayList<Rect> detection_contours(Mat outmat) {
        
        Mat v = new Mat();
        Mat vv = outmat.clone();
        
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
        Imgproc.findContours(vv, contours, v, Imgproc.RETR_LIST,
                Imgproc.CHAIN_APPROX_SIMPLE);
 
        double maxArea = 100;
        int maxAreaIdx = -1;
        Rect r = null;
        ArrayList<Rect> rect_array = new ArrayList<Rect>();
 
        for (int idx = 0; idx < contours.size(); idx++) {
            Mat contour = contours.get(idx);
            double contourarea = Imgproc.contourArea(contour);
            if (contourarea > maxArea) {
                // maxArea = contourarea;
                maxAreaIdx = idx;
                r = Imgproc.boundingRect(contours.get(maxAreaIdx));
                rect_array.add(r);
                Imgproc.drawContours(imag, contours, maxAreaIdx, new Scalar(0,0, 255));
            }
 
        }
    
     
        if(rect_array.isEmpty()){
            System.out.println("vide");
            
        }
        else 
        {
    
            System.out.println("Mouvement détecté");
            
            if(send.comptage())
            {
            send.envoi(numero_camera);
            }
           
        }
         v.release();
        return rect_array;
 
    } 
}
