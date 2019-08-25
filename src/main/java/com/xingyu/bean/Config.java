package com.xingyu.bean;

import com.xingyu.mode.FishMode;
import com.xingyu.mode.OCRMode;
import com.xingyu.util.OCR.NumOCRUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Config {
    private OCRMode ocrMode;
    private FishMode fishMode;
    private OCRInfo ocrInfo;
    private boolean kingUp;
    private boolean queenUp;
    private int lowHolyWater;
    private int lowGoldCoin;
    private int lowBlackOil;
    private int lowSum;

    @Data
    public static class OCRInfo {
        private String appId;
        private String apiKey;
        private String secretKey;

        public OCRInfo(String appId, String apiKey, String secretKey) {
            this.appId = appId;
            this.apiKey = apiKey;
            this.secretKey = secretKey;
        }
    }
}