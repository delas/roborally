package dtu.roborally.view.widgets;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import dtu.roborally.view.CardinalPoints;
import dtu.roborally.view.TyleType;

public class Tile extends JPanel {

	public static final int PIXEL_SIZE = 66;
	
	private static final long serialVersionUID = 8749974136051364514L;
	private TyleType type;
	private BufferedImage image;
	private BufferedImage imageRobot;
	private boolean containsRobot = false;
	private CardinalPoints direction;
	
	public Tile(TyleType type) {
		super(true);
		
		this.type = type;
		try {
			this.image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(this.type.getPictureFile()));
			this.imageRobot = ImageIO.read(getClass().getClassLoader().getResourceAsStream("tiles/robot.png"));
		} catch (IOException e) {
			this.image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
			this.imageRobot = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
		}
		setMinimumSize(new Dimension(PIXEL_SIZE, PIXEL_SIZE));
		setMaximumSize(getMinimumSize());
		setPreferredSize(getMinimumSize());
	}
	
	public void setRobot(CardinalPoints direction) {
		this.containsRobot = true;
		this.direction = direction;
	}
	
	public void unsetRobot() {
		this.containsRobot = false;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(image, 0, 0, null);
        
        if (containsRobot) {
			AffineTransform old2 = g2d.getTransform();
			g2d.rotate(Math.toRadians(direction.getAngle()), 33, 33);
			g2d.drawImage(imageRobot, 0, 0, null);
			g2d.setTransform(old2);
        }
	}
}
