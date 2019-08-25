/*
 * Created by JFormDesigner on Thu Aug 15 11:13:48 CST 2019
 */

package com.xingyu.gui;

import com.xingyu.bean.Config;
import com.xingyu.mode.FishMode;
import com.xingyu.mode.OCRMode;
import com.xingyu.util.adb.AdbUtil;
import com.xingyu.util.persistence.ConfigPersistenceUtil;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Objects;
import javax.swing.*;

/**
 * @author The_One
 */
public class MainGUI extends JFrame {
    private MainGUI() {
        initComponents();

        ButtonGroup group = new ButtonGroup();
        group.add(baiduNumOCRRadio);
        group.add(java4lessNumOCRRadio);

        mode.addItem("巨蛮弓");
        mode.addItem("蛮人弓箭手");
        mode.addItem("哥布林");
        mode.addItem("狗球(冲杯)");

        checkBox1.setSelected(true);
        checkBox2.setSelected(true);

        midPanel.setVisible(false);
        downPanel.setVisible(false);
        button2.setVisible(false);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private OCRMode ocrMode = OCRMode.BAIDU;

    private void java4lessNumOCRRadioMouseClicked(MouseEvent e) {
        // TODO add your code here
        panel1.setVisible(false);
        ocrMode = OCRMode.JAVA4LESS;
    }

    private void baiduNumOCRRadioMouseClicked(MouseEvent e) {
        // TODO add your code here
        panel1.setVisible(true);
        ocrMode = OCRMode.BAIDU;
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
        String sPort = port.getText();
        String sAddress = address.getText();
        if (!StringUtils.isBlank(sPort)) {
            try {
                // 判断是否连接
                if (AdbUtil.isConnect(sPort, sAddress)) {
                    midPanel.setVisible(true);
                    downPanel.setVisible(true);
                    button2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "请正确填写port和address", "错误信息", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //上次设置信息读取
                Config config = ConfigPersistenceUtil.rePersistence();
                //OcrMode 读取
                switch (config.getOcrMode()) {
                    case JAVA4LESS:
                        java4lessNumOCRRadio.setSelected(true);
                        break;
                    case BAIDU:
                        baiduNumOCRRadio.setSelected(true);
                        Config.OCRInfo ocrInfo = config.getOcrInfo();
                        appID.setText(ocrInfo.getAppId());
                        apiKey.setText(ocrInfo.getApiKey());
                        secretKey.setText(ocrInfo.getSecretKey());
                        break;
                }

                textField1.setText(String.valueOf(config.getLowGoldCoin()));
                textField2.setText(String.valueOf(config.getLowHolyWater()));
                textField3.setText(String.valueOf(config.getLowBlackOil()));
                textField4.setText(String.valueOf(config.getLowSum()));

                switch (config.getFishMode()) {
                    case GOBLIN:
                        mode.setSelectedIndex(2);
                        break;
                    case DOGANDCIRCLE:
                        mode.setSelectedIndex(3);
                        break;
                    case PRETTYMAN_ARCHER:
                        mode.setSelectedIndex(1);
                        break;
                    case GIANT_PRETTYMAN_ARCHER:
                        mode.setSelectedIndex(0);
                }

                checkBox1.setSelected(config.isKingUp());
                checkBox2.setSelected(config.isQueenUp());
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "请正确填写port和address", "错误信息", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void button2MouseClicked(MouseEvent e) {
        // TODO add your code here
        Config config = new Config();

        if (OCRMode.BAIDU.equals(ocrMode)) {
            String sAppID = appID.getText();
            String sApiKey = apiKey.getText();
            String sSecretKey = new String(secretKey.getPassword());
            if (!StringUtils.isAnyBlank(sAppID, sApiKey, sSecretKey)) {
                config.setOcrInfo(new Config.OCRInfo(sAppID, sApiKey, sSecretKey));
            } else {
                JOptionPane.showMessageDialog(null, "请正确填写AppID,ApiKey,SecretKey", "错误信息", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            config.setOcrInfo(null);
        }
        config.setOcrMode(ocrMode);
        config.setLowGoldCoin(Integer.valueOf(textField1.getText()));
        config.setLowHolyWater(Integer.valueOf(textField2.getText()));
        config.setLowBlackOil(Integer.valueOf(textField3.getText()));
        config.setLowSum(Integer.valueOf(textField4.getText()));
        config.setKingUp(checkBox1.isSelected());
        config.setQueenUp(checkBox2.isSelected());
        switch ((String) Objects.requireNonNull(mode.getSelectedItem())) {
            case "巨蛮弓":
                config.setFishMode(FishMode.GIANT_PRETTYMAN_ARCHER);
                break;
            case "蛮人弓箭手":
                config.setFishMode(FishMode.PRETTYMAN_ARCHER);
                break;
            case "哥布林":
                config.setFishMode(FishMode.GOBLIN);
                break;
            case "狗球(冲杯)":
                config.setFishMode(FishMode.DOGANDCIRCLE);
                break;
        }
        this.setVisible(false);
        FishGUI fishGUI = new FishGUI(config);
        fishGUI.setVisible(true);
        fishGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        topPanel = new JPanel();
        tip = new JLabel();
        label1 = new JLabel();
        port = new JTextField();
        label2 = new JLabel();
        address = new JTextField();
        button1 = new JButton();
        midPanel = new JPanel();
        label4 = new JLabel();
        baiduNumOCRRadio = new JRadioButton();
        panel1 = new JPanel();
        appID = new JTextField();
        label3 = new JLabel();
        apiKey = new JTextField();
        label5 = new JLabel();
        label6 = new JLabel();
        secretKey = new JPasswordField();
        java4lessNumOCRRadio = new JRadioButton();
        downPanel = new JPanel();
        label7 = new JLabel();
        label8 = new JLabel();
        mode = new JComboBox();
        label9 = new JLabel();
        checkBox1 = new JCheckBox();
        label10 = new JLabel();
        checkBox2 = new JCheckBox();
        label11 = new JLabel();
        textField1 = new JTextField();
        label12 = new JLabel();
        textField2 = new JTextField();
        label13 = new JLabel();
        textField3 = new JTextField();
        label14 = new JLabel();
        textField4 = new JTextField();
        button2 = new JButton();

        //======== this ========
        setBackground(Color.black);
        setForeground(Color.white);
        setTitle("COCAssistant1.0 by The_One");
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        //======== topPanel ========
        {
            topPanel.setBackground(Color.white);
            topPanel.setLayout(null);

            //---- tip ----
            tip.setText("\u8fde\u63a5\u8bbe\u7f6e:");
            tip.setForeground(Color.black);
            tip.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            topPanel.add(tip);
            tip.setBounds(new Rectangle(new Point(0, 0), tip.getPreferredSize()));

            //---- label1 ----
            label1.setText("\u7aef\u53e3:");
            label1.setForeground(new Color(51, 51, 51));
            topPanel.add(label1);
            label1.setBounds(5, 25, label1.getPreferredSize().width, 30);
            topPanel.add(port);
            port.setBounds(35, 25, 120, port.getPreferredSize().height);

            //---- label2 ----
            label2.setText("\u5730\u5740:");
            label2.setForeground(new Color(51, 51, 51));
            topPanel.add(label2);
            label2.setBounds(195, 25, label2.getPreferredSize().width, 30);
            topPanel.add(address);
            address.setBounds(245, 25, 155, address.getPreferredSize().height);

            //---- button1 ----
            button1.setText("\u5f00\u59cb\u68c0\u6d4b");
            button1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            topPanel.add(button1);
            button1.setBounds(400, 10, 105, button1.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < topPanel.getComponentCount(); i++) {
                    Rectangle bounds = topPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = topPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                topPanel.setMinimumSize(preferredSize);
                topPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(topPanel);
        topPanel.setBounds(new Rectangle(new Point(10, 10), topPanel.getPreferredSize()));

        //======== midPanel ========
        {
            midPanel.setBackground(Color.white);
            midPanel.setLayout(null);

            //---- label4 ----
            label4.setText("\u8bc6\u522b\u8bbe\u7f6e:");
            label4.setForeground(Color.black);
            label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 2f));
            midPanel.add(label4);
            label4.setBounds(new Rectangle(new Point(0, 5), label4.getPreferredSize()));

            //---- baiduNumOCRRadio ----
            baiduNumOCRRadio.setText("\u767e\u5ea6\u8bc6\u522b");
            baiduNumOCRRadio.setBackground(new Color(204, 204, 204));
            baiduNumOCRRadio.setForeground(Color.black);
            baiduNumOCRRadio.setMultiClickThreshhold(10L);
            baiduNumOCRRadio.setAutoscrolls(true);
            baiduNumOCRRadio.setVerifyInputWhenFocusTarget(false);
            baiduNumOCRRadio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    baiduNumOCRRadioMouseClicked(e);
                }
            });
            midPanel.add(baiduNumOCRRadio);
            baiduNumOCRRadio.setBounds(300, 5, 80, 20);

            //======== panel1 ========
            {
                panel1.setBackground(Color.white);
                panel1.setLayout(null);

                //---- appID ----
                appID.setBackground(Color.white);
                appID.setForeground(new Color(51, 51, 51));
                panel1.add(appID);
                appID.setBounds(50, 5, 85, appID.getPreferredSize().height);

                //---- label3 ----
                label3.setText("API Key:");
                label3.setForeground(Color.cyan);
                label3.setBackground(Color.white);
                panel1.add(label3);
                label3.setBounds(new Rectangle(new Point(140, 10), label3.getPreferredSize()));

                //---- apiKey ----
                apiKey.setBackground(Color.white);
                apiKey.setForeground(new Color(51, 51, 51));
                panel1.add(apiKey);
                apiKey.setBounds(185, 5, 115, apiKey.getPreferredSize().height);

                //---- label5 ----
                label5.setText("AppID:");
                label5.setBackground(Color.white);
                label5.setForeground(Color.cyan);
                panel1.add(label5);
                label5.setBounds(5, 10, 55, label5.getPreferredSize().height);

                //---- label6 ----
                label6.setText("Secret Key");
                label6.setBackground(Color.white);
                label6.setForeground(Color.cyan);
                panel1.add(label6);
                label6.setBounds(new Rectangle(new Point(295, 10), label6.getPreferredSize()));

                //---- secretKey ----
                secretKey.setBackground(Color.white);
                panel1.add(secretKey);
                secretKey.setBounds(360, 5, 110, secretKey.getPreferredSize().height);

                {
                    // compute preferred size
                    Dimension preferredSize = new Dimension();
                    for (int i = 0; i < panel1.getComponentCount(); i++) {
                        Rectangle bounds = panel1.getComponent(i).getBounds();
                        preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                        preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                    }
                    Insets insets = panel1.getInsets();
                    preferredSize.width += insets.right;
                    preferredSize.height += insets.bottom;
                    panel1.setMinimumSize(preferredSize);
                    panel1.setPreferredSize(preferredSize);
                }
            }
            midPanel.add(panel1);
            panel1.setBounds(new Rectangle(new Point(0, 30), panel1.getPreferredSize()));

            //---- java4lessNumOCRRadio ----
            java4lessNumOCRRadio.setText("java4less\u8bc6\u522b");
            java4lessNumOCRRadio.setBackground(new Color(204, 204, 204));
            java4lessNumOCRRadio.setForeground(Color.black);
            java4lessNumOCRRadio.setMultiClickThreshhold(10L);
            java4lessNumOCRRadio.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    java4lessNumOCRRadioMouseClicked(e);
                }
            });
            midPanel.add(java4lessNumOCRRadio);
            java4lessNumOCRRadio.setBounds(390, 5, 115, 20);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < midPanel.getComponentCount(); i++) {
                    Rectangle bounds = midPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = midPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                midPanel.setMinimumSize(preferredSize);
                midPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(midPanel);
        midPanel.setBounds(new Rectangle(new Point(10, 75), midPanel.getPreferredSize()));

        //======== downPanel ========
        {
            downPanel.setBackground(Color.white);
            downPanel.setForeground(Color.black);
            downPanel.setLayout(null);

            //---- label7 ----
            label7.setText("\u6253\u9c7c\u8bbe\u7f6e:");
            label7.setBackground(Color.white);
            label7.setForeground(Color.black);
            label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 2f));
            downPanel.add(label7);
            label7.setBounds(new Rectangle(new Point(5, 5), label7.getPreferredSize()));

            //---- label8 ----
            label8.setText("\u6a21\u5f0f:");
            label8.setBackground(Color.white);
            label8.setForeground(Color.black);
            downPanel.add(label8);
            label8.setBounds(new Rectangle(new Point(5, 30), label8.getPreferredSize()));

            //---- mode ----
            mode.setBackground(Color.white);
            mode.setForeground(Color.black);
            downPanel.add(mode);
            mode.setBounds(40, 25, 110, 25);

            //---- label9 ----
            label9.setText("\u86ee\u738b\u51fa\u6218");
            label9.setBackground(Color.white);
            label9.setForeground(Color.black);
            downPanel.add(label9);
            label9.setBounds(new Rectangle(new Point(160, 30), label9.getPreferredSize()));

            //---- checkBox1 ----
            checkBox1.setBackground(Color.white);
            downPanel.add(checkBox1);
            checkBox1.setBounds(new Rectangle(new Point(210, 25), checkBox1.getPreferredSize()));

            //---- label10 ----
            label10.setText("\u5973\u738b\u51fa\u6218");
            label10.setBackground(Color.white);
            label10.setForeground(Color.black);
            downPanel.add(label10);
            label10.setBounds(new Rectangle(new Point(235, 30), label10.getPreferredSize()));

            //---- checkBox2 ----
            checkBox2.setBackground(Color.white);
            downPanel.add(checkBox2);
            checkBox2.setBounds(new Rectangle(new Point(290, 25), checkBox2.getPreferredSize()));

            //---- label11 ----
            label11.setText("\u91d1 >= ");
            label11.setBackground(Color.white);
            label11.setForeground(Color.black);
            downPanel.add(label11);
            label11.setBounds(5, 70, label11.getPreferredSize().width, 25);

            //---- textField1 ----
            textField1.setBackground(Color.white);
            textField1.setForeground(Color.black);
            textField1.setText("150000");
            downPanel.add(textField1);
            textField1.setBounds(40, 70, 64, textField1.getPreferredSize().height);

            //---- label12 ----
            label12.setText("\u6c34 >= ");
            label12.setBackground(Color.white);
            label12.setForeground(Color.black);
            downPanel.add(label12);
            label12.setBounds(115, 70, 38, 25);

            //---- textField2 ----
            textField2.setText("150000");
            textField2.setForeground(Color.black);
            textField2.setBackground(Color.white);
            downPanel.add(textField2);
            textField2.setBounds(165, 70, 64, textField2.getPreferredSize().height);

            //---- label13 ----
            label13.setText("\u9ed1\u6cb9 >= ");
            label13.setBackground(Color.white);
            label13.setForeground(Color.black);
            downPanel.add(label13);
            label13.setBounds(230, 70, label13.getPreferredSize().width, 25);

            //---- textField3 ----
            textField3.setForeground(Color.black);
            textField3.setBackground(Color.white);
            textField3.setText("600");
            downPanel.add(textField3);
            textField3.setBounds(285, 70, 64, textField3.getPreferredSize().height);

            //---- label14 ----
            label14.setText("\u6216 \u91d1\u6c34\u603b\u91cf >=");
            label14.setForeground(Color.black);
            label14.setBackground(Color.white);
            downPanel.add(label14);
            label14.setBounds(350, 70, label14.getPreferredSize().width, 25);

            //---- textField4 ----
            textField4.setText("400000");
            textField4.setForeground(Color.black);
            textField4.setBackground(Color.white);
            downPanel.add(textField4);
            textField4.setBounds(435, 70, 64, textField4.getPreferredSize().height);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for (int i = 0; i < downPanel.getComponentCount(); i++) {
                    Rectangle bounds = downPanel.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = downPanel.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                downPanel.setMinimumSize(preferredSize);
                downPanel.setPreferredSize(preferredSize);
            }
        }
        contentPane.add(downPanel);
        downPanel.setBounds(10, 150, 505, 100);

        //---- button2 ----
        button2.setText("\u5f00\u59cb\u6253\u9c7c");
        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button2MouseClicked(e);
            }
        });
        contentPane.add(button2);
        button2.setBounds(410, 255, 105, button2.getPreferredSize().height);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for (int i = 0; i < contentPane.getComponentCount(); i++) {
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
    private JPanel topPanel;
    private JLabel tip;
    private JLabel label1;
    private JTextField port;
    private JLabel label2;
    private JTextField address;
    private JButton button1;
    private JPanel midPanel;
    private JLabel label4;
    private JRadioButton baiduNumOCRRadio;
    private JPanel panel1;
    private JTextField appID;
    private JLabel label3;
    private JTextField apiKey;
    private JLabel label5;
    private JLabel label6;
    private JPasswordField secretKey;
    private JRadioButton java4lessNumOCRRadio;
    private JPanel downPanel;
    private JLabel label7;
    private JLabel label8;
    private JComboBox mode;
    private JLabel label9;
    private JCheckBox checkBox1;
    private JLabel label10;
    private JCheckBox checkBox2;
    private JLabel label11;
    private JTextField textField1;
    private JLabel label12;
    private JTextField textField2;
    private JLabel label13;
    private JTextField textField3;
    private JLabel label14;
    private JTextField textField4;
    private JButton button2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
    }
}
