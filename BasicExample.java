/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.bytedeco.javacpp.*;
import org.bytedeco.leptonica.*;
import org.bytedeco.leptonica.global.lept;
import org.bytedeco.tesseract.*;
import static org.bytedeco.leptonica.global.lept.*;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class BasicExample implements NativeKeyListener{
        static java.io.FileWriter fw;
        int a=0;
       
        static File f;
        int j =1;
    public static void main(String[] args) throws NativeHookException {
        JFrame jf = new JFrame("The Bot");
        jf.setSize(600,180);
        
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(null);
        jf.getContentPane().setBackground(Color.BLACK);
        JLabel lb= new JLabel("Press Enter when countdown of game finishes");
        lb.setForeground(Color.green);
        lb.setFont(new Font("Sans Serif", Font.BOLD, 20));
        lb.setBounds(50, 40, 450, 30);
        jf.add(lb);
        

       
        //JOptionPane.showMessageDialog(null,"Started");
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
          logger.setLevel(Level.OFF);
       //  logger.setUseParentHandlers(false);
        GlobalScreen.registerNativeHook();        
        GlobalScreen.addNativeKeyListener(new BasicExample());
         
    }
    public String ocr(String file){
        BytePointer outText;

        TessBaseAPI api = new TessBaseAPI();
        // Initialize tesseract-ocr with English, without specifying tessdata path
        api.Init(System.getProperty("user.dir"),"eng");

        // Open input image with leptonica library
        PIX image = lept.pixRead(file);
        api.SetImage(image);
        // Get OCR result
        
        outText = api.GetUTF8Text();
        
        System.out.println("Text Into the Image is :\n" + outText.getString());
       /* int i = Integer.parseInt(outText.getString());
        if(i<1000)  
            System.out.println("Two Blocks");*/
      
        // Destroy used object and release memory
        api.End();
        outText.deallocate();
        pixDestroy(image);
        return outText.getString().trim();
    }

    public void nativeKeyPressed(final NativeKeyEvent nke) {
        Runnable R1 = new Runnable() {
            
            public void run() {
                
                int j=checkScore();
                String s= NativeKeyEvent.getKeyText(nke.getKeyCode());
                if(s.equalsIgnoreCase("enter")){
               
                try {
                    Robot robot = new Robot();
                    while(true) {
                        int score = checkScore();
                        if(score<=1500){      
                            Rectangle r = new Rectangle(999,516,105,110);
                            Rectangle r2 = new Rectangle(1112,516,105,110);
                           
                            int i=0;
                            int k=0;
                                 
                            BufferedImage screenShot = robot.createScreenCapture(r);
                            ImageIO.write(screenShot,"png",new File("two blocks\\abc.png"));
                            String a = ocr("two blocks\\abc.png");
                            if(a.equals("1O") || a.equals("1o")){
                                i=10;
                            }
                            else{
                                i = Integer.parseInt(a.trim());
                            }
                            BufferedImage screenShot2 = robot.createScreenCapture(r2);
                            ImageIO.write(screenShot2,"png",new File("two blocks\\abc2.png"));
                            a= ocr("two blocks\\abc2.png");
                            if(a.equals("1O") || a.equals("1o")){
                                k=10;
                            }
                            else{
                                k = Integer.parseInt(a.trim());
                            }
                            if(i>k)
                                autoClick(999,516);
                            else if(i<k)
                                autoClick(1112,516);
                            
                        }
                        else if(score<=4000){
                            Rectangle r = new Rectangle(999,461,105,105);
                            Rectangle r2 = new Rectangle(1112,461,105,105);
                            Rectangle r3 = new Rectangle(999,575,105,105);
                            Rectangle r4 = new Rectangle(1112,575,105,105);
                        
                            BufferedImage img1 =robot.createScreenCapture(r);
                            BufferedImage img2 = robot.createScreenCapture(r2);
                            BufferedImage img3 = robot.createScreenCapture(r3);
                            BufferedImage img4 = robot.createScreenCapture(r4);
                            ImageFilter filter = new GrayFilter(true, 50);  
                            ImageProducer producer = new FilteredImageSource(img1.getSource(), filter);  
                            Image mage = Toolkit.getDefaultToolkit().createImage(producer);  
                            BufferedImage age = (BufferedImage)mage;
                            ImageIO.write(age,"png",new File("four blocks\\img1.png"));
                            ImageIO.write(img2,"png",new File("four blocks\\img2.png"));
                            ImageIO.write(img3,"png",new File("four blocks\\img3.png"));
                            ImageIO.write(img4,"png",new File("four blocks\\img4.png"));
                        
                            int num1 = Integer.parseInt(ocr("four blocks\\img1.png"));
                            int num2 = Integer.parseInt(ocr("four blocks\\img2.png"));
                            int num3 = Integer.parseInt(ocr("four blocks\\img3.png"));
                            int num4 = Integer.parseInt(ocr("four blocks\\img4.png"));
                            
                        
                            if(num1 >num2 && num1 >num3&& num1 >num4)
                               autoClick(999,461);
                            else if(num2>num1 && num2>num3 && num2 >num4)
                                autoClick(1115,461);
                            else if(num3>num1 && num3 >num2 && num3>num3)
                                autoClick(1003,575);
                            else if(num4>num1 && num4 >num2 && num4 >num3)
                                autoClick(1115,575);
                        }
                       
                        else {
                            Rectangle r = new Rectangle(945,461,105,105);
                            Rectangle r2 = new Rectangle(1055,461,105,105);
                            Rectangle r3 = new Rectangle(1168,461,105,105);
                            Rectangle r4 = new Rectangle(945,575,105,105);
                            Rectangle r5 = new Rectangle(1055,575,105,105);
                            Rectangle r6 = new Rectangle(1168,575,105,105);
                        
                            BufferedImage img1 =robot.createScreenCapture(r);
                            BufferedImage img2 = robot.createScreenCapture(r2);
                            BufferedImage img3 = robot.createScreenCapture(r3);
                            BufferedImage img4 = robot.createScreenCapture(r4);
                            BufferedImage img5 = robot.createScreenCapture(r5);
                            BufferedImage img6 = robot.createScreenCapture(r6);
                            
                        
                            ImageIO.write(img1,"png",new File("six blocks\\img1.png"));
                            ImageIO.write(img2,"png",new File("six blocks\\img2.png"));
                            ImageIO.write(img3,"png",new File("six blocks\\img3.png"));
                            ImageIO.write(img4,"png",new File("six blocks\\img4.png"));
                            ImageIO.write(img5,"png",new File("six blocks\\img5.png"));
                            ImageIO.write(img6,"png",new File("six blocks\\img6.png"));
                        
                        
                            int num1 = Integer.parseInt(ocr("six blocks\\img1.png"));
                            int num2 = Integer.parseInt(ocr("six blocks\\img2.png"));
                            int num3 = Integer.parseInt(ocr("six blocks\\img3.png"));
                            int num4 = Integer.parseInt(ocr("six blocks\\img4.png"));
                            int num5 = Integer.parseInt(ocr("six blocks\\img5.png"));
                            int num6 = Integer.parseInt(ocr("six blocks\\img6.png"));
                        
                        
                            if(num1 >num2 && num1 >num3&& num1 >num4 && num1>num5 && num1>num6)
                               autoClick(950,461);
                            else if(num2>num1 && num2>num3 && num2 >num4 && num2>num5 && num2>num6)
                                autoClick(1055,461);
                            else if(num3>num1 && num3 >num2 && num3>num4 && num3>num5 && num3>num6)
                                autoClick(1168,461);
                            else if(num4>num1 && num4 >num2 && num4 >num3 && num4>num5 && num4>num6)
                                autoClick(950,575);
                            else if(num5>num1 && num5>num2 && num5>num3 && num5>num4 && num5>num6)
                                autoClick(1055,575);
                            else if(num6>num1 && num6>num2 && num6>num3 && num6>num4 && num6>num5)
                                autoClick(1168,575);
                        }       
                    }
                
                  /* Rectangle r = new Rectangle(585,112,55,15);
                   Robot robot = new Robot();
                   BufferedImage img = robot.createScreenCapture(r);
                   ImageIO.write(img,"png",new File("score.png"));
                   File file = new File("score.png");
                
                    ocr("score2.png");*/
  
                 } 
                catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                    }
               } 
            }
        };
        
        R1.run();
    }

    public void nativeKeyReleased(final NativeKeyEvent nke) {
        
    }

    public void nativeKeyTyped(final NativeKeyEvent nke) {
        
     }
    public void autoClick(int x , int y) throws AWTException{
        Robot robot = new Robot();
        robot.mouseMove(x, y);
        robot.mousePress(MouseEvent.BUTTON1_MASK);
        robot.mouseRelease(MouseEvent.BUTTON1_MASK);
        robot.mouseMove(500,200);
    }
    public int checkScore() {
            int i=0;
            try {
                Robot robot = new Robot();
                Rectangle r = new Rectangle(969,159,68,18);
                BufferedImage screenShot = robot.createScreenCapture(r);
                ImageFilter filter = new GrayFilter(true, 30); 
                ImageProducer producer = new FilteredImageSource( screenShot.getSource(), filter);  
                Image mage =  Toolkit.getDefaultToolkit().createImage(producer);
                BufferedImage buf = toBufferedImage(mage);
                buf = resizeImg(buf, 80, 60);
                ImageIO.write(buf,"jpg",new File("abc.jpg"));
                i = Integer.parseInt(ocr("abc.jpg"));
                return i;
            } catch (AWTException ex) {
                Logger.getLogger(BasicExample.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(BasicExample.class.getName()).log(Level.SEVERE, null, ex);
            }
            return i;
    }
     public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image

        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    public static BufferedImage resizeImg(BufferedImage img, int newW, int newH)
    {
    int w = img.getWidth();
    int h = img.getHeight();
    BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
    Graphics2D g = dimg.createGraphics();
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
    g.dispose();
    return dimg;      
   }
    	
   
}