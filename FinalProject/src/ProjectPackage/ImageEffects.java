package ProjectPackage;
import java.awt.image.BufferedImage;

public class ImageEffects {
	public static BufferedImage flipHorizontal(BufferedImage image) {
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// Flip horizontal
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int currentPixel = image.getRGB(x, y);
				processedImage.setRGB(image.getWidth() - x - 1, y, currentPixel);
			}
		}

		return processedImage;
	}

	public static BufferedImage flipVertical(BufferedImage image) {
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// Flip vertical and horizontal
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int currentPixel = image.getRGB(x, y);
				processedImage.setRGB(x, image.getHeight() - y - 1, currentPixel);
			}
		}

		return processedImage;
	}

	public static BufferedImage flipVerticalHorizontal(BufferedImage image) {
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		// Flip vertical and horizontal
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int currentPixel = image.getRGB(x, y);
				int destX = image.getWidth() - x - 1;
				int destY = image.getHeight() - y - 1;
				processedImage.setRGB(destX, destY, currentPixel);
			}
		}

		return processedImage;
	}

	public static BufferedImage solarize(BufferedImage image) {
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int currentPixel = image.getRGB(x, y);

				int a = (currentPixel >> 24) & 0xFF;
				int r = (currentPixel >> 16) & 0xFF;
				int g = (currentPixel >> 8) & 0xFF;
				int b = currentPixel & 0xFF;

				if (r > 127) {
					r = 255 - r;
				}

				if (g > 127) {
					g = 255 - g;
				}

				if (b > 127) {
					b = 255 - b;
				}

				int newColor = (a << 24) + (r << 16) + (g << 8) + b;
				processedImage.setRGB(x, y, newColor);
			}
		}

		return processedImage;
	}

	public static BufferedImage posterize(BufferedImage image) {
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int currentPixel = image.getRGB(x, y);

				int a = (currentPixel >> 24) & 0xFF;
				int r = (currentPixel >> 16) & 0xFF;
				int g = (currentPixel >> 8) & 0xFF;
				int b = currentPixel & 0xFF;
				
				r = (r - (r % 45));
				g = (g - (g % 45));
				b = (b - (b % 45));
				if (r > 255) {
					r = 255;
				}
				if (r < 0) {
					r = 0;
				}
				if (g > 255) {
					g = 255;
				}
				if (g < 0) {
					g = 0;
				}
				if (b > 255) {
					b = 255;
				}
				if (b < 0) {
					b = 0;
				}

				int newColor = (a << 24) + (r << 16) + (g << 8) + b;
				processedImage.setRGB(x, y, newColor);
			}
		}
		return processedImage;
	}

	public static BufferedImage sepia(BufferedImage image) {
		/*
		 * Algorithm Taken from following link:
		 * http://stackoverflow.com/questions/21899824/java-convert-a-greyscale-and-sepia-version-of-an-image-with-bufferedimage
		 */
		int sepiaDepth = 20; // sepia intensity
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int currentPixel = image.getRGB(x, y);

				int a = (currentPixel >> 24) & 0xFF;
				int r = (currentPixel >> 16) & 0xFF;
				int g = (currentPixel >> 8) & 0xFF;
				int b = currentPixel & 0xFF;

				int gry = (r + g + b) / 3;
				r = g = b = gry;
				r = r + (sepiaDepth * 2);
				g = g + sepiaDepth;
				if (r > 255) {
					r = 255;
				}
				if (r < 0) {
					r = 0;
				}
				if (g > 255) {
					g = 255;
				}
				if (g < 0) {
					g = 0;
				}
				if (b > 255) {
					b = 255;
				}
				if (b < 0) {
					b = 0;
				}

				int newColor = (a << 24) + (r << 16) + (g << 8) + b;
				processedImage.setRGB(x, y, newColor);
			}
		}

		return processedImage;
	}
	
	public static BufferedImage greyScale(BufferedImage image) {
		/*32 bits - >
		00000000 00000000 00000000 00000000
		    A       R        B        G 
		*/
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {

				int currentPixel = image.getRGB(x, y);

				int a = (currentPixel >> 24) & 0xFF;
				int r = (currentPixel >> 16) & 0xFF;
				int g = (currentPixel >> 8) & 0xFF;
				int b = currentPixel & 0xFF;

				// average of RGB
				int avg = (r + b + g) / 3;

				// set R, G & B with avg color
				int grey = (a << 24) + (avg << 16) + (avg << 8) + avg;

				processedImage.setRGB(x, y, grey);
			}
		}

		return processedImage;
	}

	public static BufferedImage invert(BufferedImage image) {
		BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {

				int currentPixel = image.getRGB(x, y);

				int a = (currentPixel >> 24) & 0xFF;
				int r = (currentPixel >> 16) & 0xFF;
				int g = (currentPixel >> 8) & 0xFF;
				int b = currentPixel & 0xFF;

				r = 255 - r;
				g = 255 - g;
				b = 255 - b;

				// set R, G & B with avg color
				int newColor = (a << 24) + (r << 16) + (g << 8) + b;

				processedImage.setRGB(x, y, newColor);
			}
		}

		return processedImage;
	}
}
