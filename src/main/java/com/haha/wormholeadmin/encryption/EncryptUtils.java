package com.haha.wormholeadmin.encryption;

import com.baomidou.mybatisplus.core.toolkit.AES;

public interface EncryptUtils {

    static void main(String[] args) {
        // 生成 16 位随机 AES 密钥
        // 79124d563af24ce3
        String randomKey = AES.generateRandomKey();
        System.out.println(randomKey);
        String username = "root";
        String password = "123456";
        // 随机密钥加密
        String result1 = AES.encrypt(username, randomKey);
        String result2 = AES.encrypt(password, randomKey);
        System.out.println(result1);
        System.out.println(result2);
    }
}
