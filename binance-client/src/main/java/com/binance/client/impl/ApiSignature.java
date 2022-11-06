package com.binance.client.impl;

import com.binance.client.exception.BinanceApiException;
import com.binance.client.constant.BinanceApiConstants;
import com.binance.client.impl.utils.UrlParamsBuilder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 * api签名
 */
class ApiSignature {

    static final String op = "op";
    static final String opValue = "auth";
    private static final String signatureMethodValue = "HmacSHA256";
    public static final String signatureVersionValue = "2";

    /**
     * 创建签名
     * @param accessKey
     * @param secretKey
     * @param builder UrlParamsBuilder builder
     */
    void createSignature(String accessKey, String secretKey, UrlParamsBuilder builder) {

        if (accessKey == null || "".equals(accessKey) || secretKey == null || "".equals(secretKey)) {
            throw new BinanceApiException(BinanceApiException.KEY_MISSING, "API key and secret key are required 需要 API 密钥和密钥");
        }

        builder.putToUrl("recvWindow", Long.toString(BinanceApiConstants.DEFAULT_RECEIVING_WINDOW))
                .putToUrl("timestamp", Long.toString(System.currentTimeMillis()));

        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance(signatureMethodValue);
            SecretKeySpec secKey = new SecretKeySpec(secretKey.getBytes(), signatureMethodValue);
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Signature] No such algorithm [签名] 没有这样的算法: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Signature] Invalid key:[签名] 无效密钥 " + e.getMessage());
        }
        String payload = builder.buildSignature();
        String actualSign = new String(Hex.encodeHex(hmacSha256.doFinal(payload.getBytes())));

        builder.putToUrl("signature", actualSign);

    }

}
