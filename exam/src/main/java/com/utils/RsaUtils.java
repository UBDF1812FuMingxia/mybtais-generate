package com.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @ClassName : RsaUtils
 * @Description : RSA加解密的测试
 * @Author : fmx
 * @Date: 2021-07-06 14:12
 */
public class RsaUtils {
    /**
     * 算法名称
     */
    //private static final String ALGORITHOM = "RSA";
    private static final String ALGORITHOM = "RSA/ECB/PKCS1Padding";

    /**
     * 密钥大小
     */
    private static final int KEY_SIZE = 1024;
    /**
     * 默认的安全服务提供者
     */
    private static final Provider DEFAULT_PROVIDER = new BouncyCastleProvider();

    private static KeyPairGenerator keyPairGen = null;
    private static KeyFactory keyFactory = null;
    /**
     * 缓存的密钥对
     */
    private static KeyPair oneKeyPair = null;

    private static String radamKey = "";

    static {
        try {
            keyPairGen = KeyPairGenerator
                    .getInstance(ALGORITHOM,DEFAULT_PROVIDER);
            keyFactory = KeyFactory
                    .getInstance(ALGORITHOM,DEFAULT_PROVIDER);
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 生成并返回RSA密钥对
     */
    private static synchronized KeyPair generateKeypair() {
        try {
            keyPairGen.initialize(KEY_SIZE,
                    new SecureRandom(radamKey.getBytes()));
            oneKeyPair = keyPairGen.generateKeyPair();
            return oneKeyPair;
        } catch (InvalidParameterException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 返回已初始化的默认的公钥
     */
    public static RSAPublicKey getDefaultPublicKey() {
        KeyPair keyPair = generateKeypair();
        if (keyPair != null) {
            return (RSAPublicKey) keyPair.getPublic();
        }
        return null;
    }

    /**
     * 使用指定的私钥解密数据
     * @param privateKey 给定的私钥
     * @param data 要解密的数据
     * @return 原数据
     * @throws Exception
     */
    public static byte[] decrybt(PrivateKey privateKey ,byte[] data) throws Exception {
        Cipher ci = Cipher.getInstance(ALGORITHOM,DEFAULT_PROVIDER);
        ci.init(Cipher.DECRYPT_MODE,privateKey);
        return ci.doFinal(data);
    }

    /**
     * 使用默认的私钥解密给定的字符串
     * <p/>
     * 若{@code encryptText} 为{@code null}或空字符串则返回{@code null}
     * 私钥不匹配时，返回{@code null}
     *
     * @param encryptText 密文
     * @return 原文字符串
     */
    public static String decryptString(String encryptText) {
        if (StringUtils.isBlank(encryptText)) {
            return null;
        }
        KeyPair keyPair = generateKeypair();
        try {
            byte[] en_data = Hex.decodeHex(encryptText);
            byte[] data = decrybt((RSAPrivateKey) keyPair.getPrivate(), en_data);
            return new String(data);
        } catch (NullPointerException ex){
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 使用默认的私钥解密由JS加密（使用此类提供的公钥加密）的字符串
     * @param encryptText
     * @return
     */
    public static String decryptStringByJs(String encryptText) {
        String text = decryptString(encryptText);
        if (text == null) {
            return null;
        }
        return StringUtils.reverse(text);
    }

    public static void main(String[] args) {
        //密码种子，一个密码种子生成一组RSA密码
        RsaUtils.radamKey = "1111";

        //获取公钥，分发公钥
        RSAPublicKey publicKey = RsaUtils.getDefaultPublicKey();

        //公钥-系数（n）
        System.out.println("public key modulus:"
                + new String(Hex.encodeHex(publicKey.getModulus().toByteArray())));

        //公钥-指数（e1）
        System.out.println("publi key exponent:"
                + new String(Hex.encodeHex(publicKey.getPublicExponent()
                .toByteArray())));

        //JS加密后的字符串
        String ppp = "6e269bdf7f7670ffaff669d86d93e63fd91f" +
                "2d6dd4f7e57ca58c614de65c0828e608bdd0f3e81d" +
                "3630f8533f5286e0f55caa005ebe6d3faf3b9cc130" +
                "d9ecff51d1f77eeed48156431cbf5de62cf6899084" +
                "a4e92890827edbebbf506ecbb022de4a0e3131f2ff" +
                "efe3368c7b7f929362edb4af419b837456c275f643" +
                "a5c760a4ff";

        //解密后的字符串
        String kkk = RsaUtils.decryptStringByJs(ppp);

        System.out.println("解密后文件：" + kkk);
    }
}
