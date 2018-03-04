package cn.exrick.xcloud.common.utils;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加解密
 * @author Exrickx
 */
public class AESUtil {

    /**
     * 默认key 请保管好您的密钥
     */
    public final static String KEY = "XCloudIsANiceFramePresentedByExrick";

    public static String aesEncrypt(String value){

        String result = null;
        try {
            if(value != null && !"".equals(value.trim())){
                result = base64Encode(aesEncryptToBytes(value, KEY));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String aesEncrypt(String value, String key){

        String result = null;
        key = StrUtil.isBlank(key) ? KEY : key;
        try {
            if(value != null && !"".equals(value.trim())){
                result = base64Encode(aesEncryptToBytes(value, key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String aesDecrypt(String value){

        String result = null;
        try {
            if(value != null && !"".equals(value.trim())){
                result = aesDecryptByBytes(base64Decode(value), KEY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String aesDecrypt(String value, String key){

        String result = null;
        key = StrUtil.isBlank(key) ? KEY : key;
        try {
            if(value != null && !"".equals(value.trim())){
                result = aesDecryptByBytes(base64Decode(value), key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    private static String base64Encode(byte[] bytes){
        return Base64.encode(bytes);
    }

    @SuppressWarnings("static-access")
    private static byte[] base64Decode(String base64Code) throws Exception{
        return base64Code == null ? null : Base64.decode((base64Code));
    }

    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

        return cipher.doFinal(content.getBytes("utf-8"));
    }

    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }

    public static void main(String[] args){

        //加密
        System.out.println(aesEncrypt("123456",KEY));
        //解密 默认AESUtil.KEY
        System.out.println(aesDecrypt("Vv7lts9JW2/mR0DEaylI1w=="));
    }
}
