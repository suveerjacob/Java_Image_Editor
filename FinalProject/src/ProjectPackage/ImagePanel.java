package ProjectPackage;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ImagePanel extends JPanel {
	
	int imagePanelWidth = 600;
	int imagePanelHeight = 500;
	
	public BufferedImage image;
	private BufferedImage tempImage;
	private BufferedImage originalImage;
	
	Dimension imageSize;
	Dimension frameSize;
	Dimension newImageSize;
	
	public ImagePanel() {
	}
	
	public void changeImage(File f){
		try {
            image = ImageIO.read(f);
            imageSize = new Dimension(image.getWidth(),image.getHeight());
            frameSize = new Dimension(imagePanelWidth, imagePanelHeight);
            newImageSize = getScaledDimension(imageSize, frameSize);
            tempImage = image;
            originalImage = image;
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void saveImage(String destinationPath, String format ){
		
		if(image == null){
			JOptionPane.showMessageDialog(this, "First choose a proper image");
			return;
		}
		try{
			System.out.println(destinationPath+"."+format);
			ImageIO.write(image, format, new File(destinationPath+"."+format));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {
		/*
		 * Logic taken from following link:
		 * http://stackoverflow.com/questions/10245220/java-image-resize-maintain-aspect-ratio 
		 */

	    int originalW = imgSize.width;
	    int originalH = imgSize.height;
	    int boundW = boundary.width;
	    int boundH = boundary.height;
	    int newW = originalW;
	    int newH = originalH;

	    // first check if we need to scale width
	    if (originalW > boundW) {
	        //scale width to fit
	        newW = boundW;
	        //scale height to maintain aspect ratio
	        newH = (newW * originalH) / originalW;
	    }

	    // then check if we need to scale even with the new height
	    if (newH > boundH) {
	        //scale height to fit instead
	        newH = boundH;
	        //scale width to maintain aspect ratio
	        newW = (newH * originalW) / originalH;
	    }

	    return new Dimension(newW, newH);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(image != null){
			g.drawImage(image, getWidth()/2 - newImageSize.width/2, getHeight()/2 - newImageSize.height/2, newImageSize.width, newImageSize.height, this);
			//g.drawImage(image, 50, 50, image.getWidth(), image.getHeight(), this);
		}else{
			g.drawString("Pick Image from Gallery.", getWidth()/2, getHeight()/2);
			
		}
			
		
	}
	
	@Override
	// Specify preferred size
	public Dimension getPreferredSize() {
		return new Dimension(imagePanelWidth, imagePanelHeight);
	}
	
	public void applyEffect(Effect effect){
		
		if(image == null){
			JOptionPane.showMessageDialog(this, "First choose a proper image");
			return;
		}
		image = tempImage; //resets the image
		switch(effect){
		case FLIP_H:
			image = ImageEffects.flipHorizontal(image);
			break;
		case FLIP_V:
			image = ImageEffects.flipVertical(image);
			break;
		case FLIP_HV:
			image = ImageEffects.flipVerticalHorizontal(image);
			break;
		case GRAYSCALE:
			image = ImageEffects.greyScale(image);
			break;
		case SEPIA:
			image = ImageEffects.sepia(image);
			break;
		case INVERT:
			image = ImageEffects.invert(image);
			break;
		case SOLARIZE:
			image = ImageEffects.solarize(image);
			break;
		case POSTERIZE:
			image = ImageEffects.posterize(image);
			break;
		}
		repaint();
	}
	
	public void resetImage(){
		image = originalImage; //resets the image
		tempImage = originalImage;
		repaint();
	}
	
	public void applyPermanentEffect(){
		tempImage = image;
		repaint();
	}

}
