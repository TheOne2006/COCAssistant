package com.xingyu.util.OCR;

import com.baidu.aip.ocr.AipOcr;
import com.java4less.ocr.tess3.OCRFacade;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class NumOCRUtil {
    private boolean flag;

    public NumOCRUtil() {
        flag = false;
    }

    public String getVerifyText(File file) throws IOException {
        return flag ? getBaiduVerify(file) : getJ4lessVerifyText(file);
    }


    private String getJ4lessVerifyText(File file) throws IOException {
        OCRFacade facade = new OCRFacade();
        BufferedImage bi = ImageIO.read(file);

        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", bao);
        return facade.recognizeImage(bao.toByteArray(), "png", "eng");
    }

    private AipOcr aipOcr;
    private HashMap<String, String> options = new HashMap<>();

    public NumOCRUtil(String appId, String apiKey, String secretKey) {
        flag = true;
        aipOcr = new AipOcr(appId, apiKey, secretKey);
        options.put("recognize_granularity", "big");
        options.put("detect_direction", "true");
    }

    private String getBaiduVerify(File file) {
        String image = file.getAbsolutePath();
        return aipOcr.numbers(image, options).toString();
    }
}
