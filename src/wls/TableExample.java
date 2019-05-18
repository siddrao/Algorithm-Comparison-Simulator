package wls;
//https://stackoverflow.com/questions/6849151/bar-chart-in-java
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class TableExample extends JPanel {
  private double[] values;
  private String[] text;
  private String title;
  public Image bg;

  public TableExample(double[] v, String[] txt, String t) {
   text = txt;
   values = v;
   title = t;
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(bg, 100, 100, null);
    if (values == null || values.length == 0)
      return;
    double low = 0,high = 0;
    int v=0,i=0;
    while ( i < values.length) {
     if (high < values[i])
        high = values[i];
     if (low > values[i])
        low = values[i];
      i++;
    }

    Dimension dim = getSize();
    Font titleFont = new Font("SansSerif", Font.BOLD, 20);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("SansSerif", Font.BOLD, 14);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

    int titleWidth;
    titleWidth = titleFontMetrics.stringWidth(title);
    int y = titleFontMetrics.getAscent();
    int x = (dim.width - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, x, y);
    if (high == low)
      return;
    double scale = (dim.height - titleFontMetrics.getHeight() - labelFontMetrics.getHeight()) / (high - low);
    y = dim.height - labelFontMetrics.getDescent();
    g.setFont(labelFont);
     v=v+75;
     int j=0;
     int p=0;
     //
    while( p <4 ) 
    {
      int q = p * dim.width / values.length + 1;
      int w = titleFontMetrics.getHeight();
      int height = (int) (values[p] * scale);
      if (values[p] < 0){
        w += (int) (high * scale);
        height = -height;
    }
      else {
        w += (int) ((high - values[p]) * scale);
      }
      if(p==0)
        g.setColor(Color.red);
      if(p==1)
        g.setColor(Color.blue);
      if(p==2)
        g.setColor(Color.red);
      if(p==3)
        g.setColor(Color.blue);
      q=q+1;
      w=w+5;
      g.fillRect(q, w, (dim.width / values.length - 4)/4, height);
      g.setColor(Color.black);
      g.drawRect(q, w, (dim.width / values.length - 4)/4, height);
      String a=Double.toString(values[p]);
      g.drawString(a, q, w-2);
      x = p * dim.width / values.length + (dim.width / values.length - labelFontMetrics.stringWidth(text[p])) / 2;
      g.drawString(text[p], x+v, y+1);//item position
       v+=40;
       j++;
       p++;
    }
  } 

  
}