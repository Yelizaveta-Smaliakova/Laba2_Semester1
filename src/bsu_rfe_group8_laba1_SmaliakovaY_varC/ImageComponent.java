package bsu_rfe_group8_laba1_SmaliakovaY_varC;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ImageComponent extends JComponent
{
    private Image img;
    public ImageComponent(int formulaId)
    {
        try {
            if (formulaId == 1) {
                img = ImageIO.read(new File("src/bsu_rfe_group8_laba1_SmaliakovaY_varC/formula1.bmp"));
            } else {
                img = ImageIO.read(new File("src/bsu_rfe_group8_laba1_SmaliakovaY_varC/formula2.bmp"));
            }
        }catch (IOException ex)
        {
            System.out.println(1);
        }
    }
    public void paintComponent(Graphics g)
    {
        if (img == null) return;
        g.drawImage(img, 130, 0, null);
    }
}