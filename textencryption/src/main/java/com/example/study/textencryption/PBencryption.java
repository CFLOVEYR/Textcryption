package com.example.study.textencryption;

import android.util.Base64;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class PBencryption {
    private String mod = "101135391169380975888313359539585667957017107504198369172068657107792479137615224346518381472688963742319160978574569992384908939522194055396760889865073182129383643304231512201667253482861428868489518558363226916557323061644694060254592931537134014739133191997600452544997222109159244367816410608593116951449";
    private String publicKey = "65537";
    private String privateKey = "205242947121895567284826537967329811225464627585308804185195101932288626658266701833879255014230620530821496408905165158417274043005505430028368682607606855708130968163551742165053180417139435712199272213075142246893247857957525198949188430831719450756208942343947389942691890276260606382089608182090055297";
    private Cipher cipher;
    private PublicKey publicKey1;
    private PrivateKey privateKey1;
    private RSAPrivateKeySpec spec1;

    /**
     *   数据的初始化
     */ {
        try {
            KeyFactory rsa = KeyFactory.getInstance("RSA");
            RSAPublicKeySpec spec = new RSAPublicKeySpec(new BigInteger(mod), new BigInteger(publicKey));
            publicKey1 = rsa.generatePublic(spec);
            spec1 = new RSAPrivateKeySpec(new BigInteger(mod), new BigInteger(privateKey));
            privateKey1 = rsa.generatePrivate(spec1);
            cipher = Cipher.getInstance("RSA");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //加密的算法
    public String encryption(String plain) {
        byte[] doFinal = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey1);
            doFinal = cipher.doFinal(plain.getBytes("UTF-8"));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(doFinal, Base64.DEFAULT);
    }


    //解密的算法
    public String decryption(String plain) throws Exception {
        byte[] doFinal = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, privateKey1);
            doFinal = cipher.doFinal(Base64.decode(plain, Base64.DEFAULT));
        } catch (Exception e) {

        }
        return new String(doFinal, "UTF-8");
    }


}