package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class BarraVitaBollicine {
	
	private Cassiopea cassiopea;
	
	private BufferedImage image;
	private Font font;
	
	public BarraVitaBollicine(Cassiopea p) {
		cassiopea = p;
		try {
			image = ImageIO.read(
				getClass().getResourceAsStream(
					"/BarraVita/hudBoll1.gif"
				)
			);
			font = new Font("Arial", Font.PLAIN, 14);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(image, 0, 28, null);
		g.setFont(font);
		g.setColor(Color.WHITE);
//		g.drawString(
//			cassiopea.getHealth() + "/" + cassiopea.getMaxHealth(),
//			30,
//			25
//		);
		g.drawString(
		Bollicina.contaCibo+"",
			30,
			63
		);
		
	}
	
}












