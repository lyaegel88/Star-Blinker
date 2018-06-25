
package starblinker;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;

/**
 *
 * @author Luke Yaegel
 */
public class DrawPath extends JComponent{
    
    private Color lastColor = Color.YELLOW;
    // For telling the panel to be repainted at regular intervals
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    
  @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        int[] xPoints = {10};
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD,
                xPoints.length);
        
        g.setColor(Color.white);
        g.drawString("Wish upon a star!", 90,70);
        
        path.moveTo(0, 0);
        super.paintComponents(g);
        if(lastColor.equals(Color.YELLOW))
        {
            lastColor = Color.GRAY;
        }
        else
        {
            lastColor = Color.YELLOW;
        }
        g2.setColor(lastColor);
        g2.setStroke(new BasicStroke(4.0f));
        path = new GeneralPath(GeneralPath.WIND_NON_ZERO);
        path.moveTo(40, 40);
        path.lineTo(60, 100);
        path.lineTo(10, 60);
        path.lineTo(70, 60);
        path.lineTo(20, 100);
        path.closePath();
        g2.draw(path);     
       
    }
    
    public DrawPath(){
        JFrame jf = new JFrame("Star Blinker");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = jf.getContentPane();
        cp.add(this);
        jf.setSize(245, 200);
        cp.setBackground(Color.black);
        jf.setVisible(true);
        ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("resources/star.png"));
        jf.setIconImage(img.getImage());
        service.scheduleAtFixedRate(() -> {
            repaint();
        }, 0, 1, TimeUnit.SECONDS);
    }
    
   
    
    
}
