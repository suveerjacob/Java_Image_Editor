package ProjectPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.rmi.CORBA.Util;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import FileChooserPackage.ImageFileView;
import FileChooserPackage.ImageFilter;
import FileChooserPackage.ImagePreview;
import FileChooserPackage.Utils;

public class ButtonPanel extends JPanel implements ActionListener {

	JButton flipH;
	JButton flipV;
	JButton flipHV;
	JButton grayScale;
	JButton invert;
	JButton sepia;
	JButton solarize;
	JButton posterize;
	JButton reset;
	JButton apply;

	JButton open;
	JButton save;
	JButton close;

	ImagePanel imagePanel;
	MainClass mainClass;

	private JFileChooser fileChooser;

	public ButtonPanel(ImagePanel _imagePanel, MainClass _mainClass) {
		imagePanel = _imagePanel;
		mainClass = _mainClass;
		makeObjects();
		doTheLayout();

		flipH.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.FLIP_H);
			}
		});

		flipV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.FLIP_V);
			}
		});

		flipHV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.FLIP_HV);
			}
		});

		grayScale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.GRAYSCALE);
			}
		});

		invert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.INVERT);
			}
		});

		sepia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.SEPIA);
			}
		});

		solarize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.SOLARIZE);
			}
		});

		posterize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyEffect(Effect.POSTERIZE);
			}
		});
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.resetImage();
			}
		});

		apply.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				imagePanel.applyPermanentEffect();
			}
		});

		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Set up the file chooser.
				if (fileChooser == null) {
					fileChooser = new JFileChooser();
					// Add a custom file filter and disable the default
					// (Accept All) file filter.
					fileChooser.addChoosableFileFilter(new ImageFilter());
					fileChooser.setAcceptAllFileFilterUsed(false);
					// Add custom icons for file types.
					fileChooser.setFileView(new ImageFileView());
					// Add the preview pane.
					fileChooser.setAccessory(new ImagePreview(fileChooser));
				}
				// Show it.
				int returnVal = fileChooser.showDialog(mainClass, "Attach");
				// Process the results.
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					imagePanel.changeImage(file);
				} else {

				}
				// Reset the file chooser for the next time it's shown.
				fileChooser.setSelectedFile(null);
			}
		});

		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(imagePanel.image == null){
					JOptionPane.showMessageDialog(mainClass, "First choose a proper image");
					return;
				}
				
				fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", ".png"));
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", ".jpg"));
				fileChooser.setSelectedFile(new File("image"));
				// fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showSaveDialog(mainClass);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					System.out.println(fileChooser.getFileFilter().getDescription());
					System.out.println(Utils.getExtension(file));
					String path = "";
					String format = "";
					try {
						if (Utils.getExtension(file) != null) {
							path = file.getCanonicalPath();
							int index = path.lastIndexOf('.');
							path = path.substring(0, index);
						} else {
							path = file.getCanonicalPath();
						}

						if (fileChooser.getFileFilter().getDescription().equals("PNG")) {
							format = "png";
						} else {
							format = "jpg";
						}
						
						System.out.println(path + "---" + format);
						imagePanel.saveImage(path,format);
					} catch (IOException e1) {

						e1.printStackTrace();
					}

				} else {
				}

			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void makeObjects() {
		flipH = new JButton("Flip H");
		flipV = new JButton("Flip V");
		flipHV = new JButton("Flip HV");
		grayScale = new JButton("Grayscale");
		invert = new JButton("Invert");
		sepia = new JButton("Sepia");
		solarize = new JButton("Solarize");
		posterize = new JButton("Posterize");
		reset = new JButton("Reset Image");
		apply = new JButton("Apply Effect");

		open = new JButton("Open Image");
		save = new JButton("Save Image");
		close = new JButton("Close");

	}

	private void doTheLayout() {
		setLayout(new BorderLayout());

		JPanel topPanel_1 = new JPanel();
		topPanel_1.setLayout(new FlowLayout(FlowLayout.CENTER,0,120));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(0, 2, 20, 15));
		topPanel.add(flipH);
		topPanel.add(flipV);
		topPanel.add(flipHV);
		topPanel.add(grayScale);
		topPanel.add(invert);
		topPanel.add(sepia);
		topPanel.add(solarize);
		topPanel.add(posterize);
		topPanel.add(apply);
		topPanel.add(reset);
		topPanel_1.add(topPanel);
		add(topPanel_1, BorderLayout.CENTER);

		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(open);
		bottomPanel.add(save);
		bottomPanel.add(close);

		add(bottomPanel, BorderLayout.SOUTH);

	}

	@Override
	// Specify preferred size
	public Dimension getPreferredSize() {
		return new Dimension(300, 500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
