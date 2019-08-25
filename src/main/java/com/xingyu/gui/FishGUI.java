/*
 * Created by JFormDesigner on Sat Aug 17 13:45:45 CST 2019
 */

package com.xingyu.gui;

import java.awt.event.*;

import com.xingyu.bean.Config;
import com.xingyu.util.OCR.NumOCRUtil;
import com.xingyu.util.assistant.COCAssistant;
import com.xingyu.util.image.ImageDisplayUtil;
import com.xingyu.util.persistence.ConfigPersistenceUtil;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

/**
 * @author The_One
 */
public class FishGUI extends JFrame {
    private Config config;
    private NumOCRUtil numOCRUtil;

    public FishGUI(Config config) {
        initComponents();
        textArea.append("����������������......  ");
        this.config = config;
        switch (config.getOcrMode()) {
            case BAIDU:
                Config.OCRInfo ocrInfo = config.getOcrInfo();
                numOCRUtil = new NumOCRUtil(ocrInfo.getAppId(), ocrInfo.getApiKey(), ocrInfo.getSecretKey());
                break;
            case JAVA4LESS:
                numOCRUtil = new NumOCRUtil();
                break;
        }
        textArea.append("�ɹ�\n");
        textArea.append("���ڱ�����������......  ");

        ImageDisplayUtil.setLabel(this.displayLabel);
        ImageDisplayUtil.setText(textArea);
        try {
            ConfigPersistenceUtil.persistence(config);
            textArea.append("�ɹ�\n");
            textArea.append("���������ֻ�......  �ɹ�\n");
            textArea.append("----------------------------------------\n");
            textArea.append("��ӭ���� COC���� by The_One\n");
            textArea.append("����:�ø���Ϊ���԰汾,��֧��16:9(��1920 : 1080)�ֱ��ʵ��ֻ�\n");
            textArea.append("----------------------------------------\n");
            textArea.append("���½�����ͻ��Ϸ......  ");
            while (!COCAssistant.isLogin()) {
                Thread.sleep(1000);
            }
            textArea.append("�ɹ�\n���ڻ�ȡ���Ĵ�Ӫ��Ϣ  ");
            textArea.append("�ɹ�\nһ��׼������,��ʼ����\n");
            textArea.append("----------------------------------------\n");
        } catch (Exception ignored) {
        }
    }

    private void button1MousePressed(MouseEvent e) {
        // TODO add your code here
        try {
            ImageDisplayUtil.showImage();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        displayLabel = new JLabel();
        textArea = new JTextArea();
        button1 = new JButton();

        //======== this ========
        setBackground(Color.darkGray);
        setTitle("COCAssistant1.0 by The_One");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.add(displayLabel);
        displayLabel.setBounds(5, 10, 560, 315);

        //---- textArea ----
        textArea.setBackground(Color.darkGray);
        textArea.setForeground(Color.green);
        textArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        textArea.setEditable(false);
        contentPane.add(textArea);
        textArea.setBounds(5, 365, 560, 195);

        //---- button1 ----
        button1.setText("\u70b9\u51fb\u83b7\u53d6\u622a\u56fe");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                button1MousePressed(e);
            }
        });
        contentPane.add(button1);
        button1.setBounds(new Rectangle(new Point(440, 330), button1.getPreferredSize()));

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JLabel displayLabel;
    private JTextArea textArea;
    private JButton button1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
