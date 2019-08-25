package com.xingyu.util.image;

import com.xingyu.util.adb.AdbUtil;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

import javax.swing.*;
import java.awt.*;

import static com.xingyu.util.adb.AdbUtil.SCREENSHOTS;

public class ImageDisplayUtil {
    private static ConvertCmd cmd = new ConvertCmd();
    private static JLabel label;
    private static JTextArea text;

    static {
        cmd.setSearchPath("E:\\apple\\programme_java\\tools\\ImageMagick-7.0.8-Q16");
    }

    public static void setLabel(JLabel label) {
        ImageDisplayUtil.label = label;
    }
    public static void setText(JTextArea text) {
        ImageDisplayUtil.text = text;
    }

    public static void showImage() throws Exception {
        text.append("正在获取手机实时截图......  ");
        AdbUtil.getScreenshots();

        IMOperation operation = new IMOperation();
        operation.addImage(SCREENSHOTS.getAbsolutePath());
        operation.rotate(90d);
        operation.addImage(SCREENSHOTS.getAbsolutePath());
        cmd.run(operation);

        ImageIcon imageIcon = new ImageIcon(SCREENSHOTS.getAbsolutePath());
        ScaleIcon scaleIcon = new ScaleIcon(imageIcon);
        label.setIcon(scaleIcon);
        label.repaint();
        text.append("获取成功,已显示\n");
    }

    private static class ScaleIcon implements Icon {
        private Icon icon;

        ScaleIcon(Icon icon) {
            this.icon = icon;
        }

        @Override
        public int getIconHeight() {
            return icon.getIconHeight();
        }

        @Override
        public int getIconWidth() {
            return icon.getIconWidth();
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            float wid = c.getWidth();
            float hei = c.getHeight();
            int iconWid = icon.getIconWidth();
            int iconHei = icon.getIconHeight();

            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.scale(wid / iconWid, hei / iconHei);
            icon.paintIcon(c, g2d, 0, 0);
        }
    }
}