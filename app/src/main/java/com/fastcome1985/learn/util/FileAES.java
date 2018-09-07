//package com.fastcome1985.learn.util;
//
//import android.text.TextUtils;
//
//import com.didi.dr.locallog.LocalLogManager;
//import com.didi.dr.util.SystemInfoUtils;
//import com.didi.drivingrecorder.consts.LocalLogTag;
//import com.didi.drivingrecorder.storage.file.fileStorage.FileStorage;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.RandomAccessFile;
//import java.nio.MappedByteBuffer;
//import java.nio.channels.FileChannel;
//import java.security.SecureRandom;
//
//import javax.crypto.Cipher;
//import javax.crypto.CipherInputStream;
//import javax.crypto.CipherOutputStream;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//
///**
// * Created by luojianxiang on 2017/6/30.
// */
//
//public class FileAES {
//
//    private static final String SHA1PRNG = "SHA1PRNG";
//    private static final String AES = "AES";
//    private static final String AES_NOPADDING = "AES/ECB/NOPADDING";
//    public static final String SECRETKEY = "didi" + SystemInfoUtils.getDeviceId() + "aes";
//    private static final int BLOCK_SIZE = 1024;
//
//
//    /**
//     * 获取密钥
//     *
//     * @return
//     */
//    public String getSecretkeyNew() {
//        String sn = SystemInfoUtils.getDeviceId();
//        if (TextUtils.isEmpty(sn) || sn.length() < 12) {
//            return "didi20170802aes0";
//        } else {
//            return "didi" + sn.substring(sn.length() - 12);
//        }
//    }
//
//
//    /**
//     * 加密文件
//     *
//     * @param sourceFile 需要加密文件
//     * @param key        密钥
//     */
//    public void encryptFilePart(String sourceFile, String key) throws Exception {
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "部分加密开始");
//        FileChannel fileChannel = null;
//        MappedByteBuffer out = null;
//        long time0 = System.currentTimeMillis();
//        try {
//            Cipher cipher = Cipher.getInstance(AES_NOPADDING);
//            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), AES);
//            cipher.init(Cipher.ENCRYPT_MODE, secret);
//            fileChannel = new RandomAccessFile(sourceFile, "rw").getChannel();
//            out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, BLOCK_SIZE);
//            byte[] data;
//            if (fileChannel.size() < BLOCK_SIZE) {
//                LocalLogManager.getInstance().log(LocalLogTag.AES, "部分加密大小不足1024");
//                data = new byte[(int) fileChannel.size()];
//            } else {
//                data = new byte[BLOCK_SIZE];
//            }
//            out.get(data);
//            byte[] encrypted = cipher.doFinal(data);
//            for (int i = 0; i < encrypted.length; i++) {
//                out.put(i, encrypted[i]);
//            }
//        } catch (Exception e) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, e);
//            throw e;
//        } finally {
//            if (null != out) {
//                out.force();
//            }
//            if (null != fileChannel) {
//                fileChannel.close();
//            }
//            long time1 = System.currentTimeMillis();
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "部分加密成功,耗时：" + (time1 - time0) + "毫秒  " + sourceFile + "   " + FileStorage.getInstance().getExternalStorage().getSize(new File(sourceFile), SizeUnit.MB) + "M");
//        }
//    }
//
//    /**
//     * 加密文件
//     *
//     * @param inputStream 需要加密文件
//     * @param key         密钥
//     * @param destPath    目标路径
//     */
//    public void encryptFilePart(InputStream inputStream, String key, String destPath) throws Exception {
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "部分加密流加密开始");
//        long time0 = System.currentTimeMillis();
//        FileOutputStream outputStream = null;
//        try {
//            Cipher cipher = Cipher.getInstance(AES_NOPADDING);
//            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), AES);
//            cipher.init(Cipher.ENCRYPT_MODE, secret);
//            outputStream = new FileOutputStream(destPath);
//            int len = -1;
//            boolean hasdecrypted = false;
//            byte[] bytes = new byte[BLOCK_SIZE];
//            while ((len = inputStream.read(bytes)) > 0) {
//                if (!hasdecrypted) {
//                    hasdecrypted = true;
//                    byte[] encrypted = cipher.doFinal(bytes);
//                    outputStream.write(encrypted, 0, len);
//                    outputStream.flush();
//                } else {
//                    outputStream.write(bytes, 0, len);
//                    outputStream.flush();
//                }
//            }
//
//        } catch (Exception e) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, e);
//            throw e;
//        } finally {
//            if (outputStream != null) {
//                outputStream.close();
//            }
//            if (inputStream == null) {
//                inputStream.close();
//            }
//            long time1 = System.currentTimeMillis();
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "部分加密流加密成功,耗时：" + (time1 - time0) + "毫秒  " + "destPath  " + FileStorage.getInstance().getExternalStorage().getSize(new File(destPath), SizeUnit.MB) + "M");
//        }
//    }
//
//
//    /**
//     * 加密文件
//     *
//     * @param sourceFile 需要加密文件
//     * @param key        密钥
//     */
//    public void decryptFilePart(String sourceFile, String key) throws Exception {
//        LocalLogManager.getInstance().log("加密解密", "部分解密到原文件开始");
//        FileChannel fileChannel = null;
//        MappedByteBuffer out = null;
//        long time0 = System.currentTimeMillis();
//        try {
//            Cipher cipher = Cipher.getInstance(AES_NOPADDING);
//            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), AES);
//            cipher.init(Cipher.DECRYPT_MODE, secret);
//            fileChannel = new RandomAccessFile(sourceFile, "rw").getChannel();
//            out = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, BLOCK_SIZE);
//            byte[] data;
//            if (fileChannel.size() < BLOCK_SIZE) {
//                LocalLogManager.getInstance().log("加密解密", "部分解密大小不足1024");
//                data = new byte[(int) fileChannel.size()];
//            } else {
//                data = new byte[BLOCK_SIZE];
//            }
//            out.get(data);
//            byte[] encrypted = cipher.doFinal(data);
//            for (int i = 0; i < encrypted.length; i++) {
//                out.put(i, encrypted[i]);
//            }
//        } catch (Exception e) {
//            LocalLogManager.getInstance().log("加密解密", e);
//            throw e;
//        } finally {
//            if (null != out) {
//                out.force();
//            }
//            if (null != fileChannel) {
//                fileChannel.close();
//            }
//            long time1 = System.currentTimeMillis();
//            LocalLogManager.getInstance().log("加密解密", "部分解密成功,耗时：" + (time1 - time0) + "毫秒  " + sourceFile + "   " + FileStorage.getInstance().getExternalStorage().getSize(new File(sourceFile), SizeUnit.MB) + "M");
//        }
//    }
//
//
//    /**
//     * 部分解密：边输出边解密
//     *
//     * @param sourceFile 需要解密文件的路径
//     * @param key        密钥
//     * @param newPath    新文件保存路径
//     */
//    public void decryptFilePartNewFile(String sourceFile, String key, String newPath) throws Exception {
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "部分解密到新文件开始");
//        InputStream inputStream = null;
//        FileOutputStream outputStream = null;
//        long time0 = System.currentTimeMillis();
//        try {
//            inputStream = new FileInputStream(sourceFile);
//            outputStream = new FileOutputStream(newPath);
//            Cipher cipher = Cipher.getInstance(AES_NOPADDING);
//            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), AES);
//            cipher.init(Cipher.DECRYPT_MODE, secret);
//            int len = -1;
//            boolean hasdecrypted = false;
//            byte[] bytes = new byte[BLOCK_SIZE];
//            while ((len = inputStream.read(bytes)) > 0) {
//                if (!hasdecrypted) {
//                    hasdecrypted = true;
//                    byte[] encrypted = cipher.doFinal(bytes);
//                    outputStream.write(encrypted, 0, len);
//                    outputStream.flush();
//                } else {
//                    outputStream.write(bytes, 0, len);
//                    outputStream.flush();
//                }
//            }
//        } catch (Exception e) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, e);
//            throw e;
//        } finally {
//            if (outputStream != null) {
//                outputStream.close();
//            }
//            if (inputStream == null) {
//                inputStream.close();
//            }
//            long time1 = System.currentTimeMillis();
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "部分解密成功,耗时：" + (time1 - time0) + "毫秒");
//        }
//    }
//
//
//    /**
//     * 采用部分加密，此方法废弃
//     *
//     * @param inputStream 需要加密文件流
//     * @param key         密钥
//     * @param newPath     加密后问价保存路劲
//     */
//    public void encryptFile(InputStream inputStream, String key, String newPath) throws Exception {
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "全文件加密开始");
//        CipherOutputStream cipherOutputStream = null;
//        OutputStream os = null;
//        long time0 = System.currentTimeMillis();
//        try {
//            Cipher cipher = Cipher.getInstance(AES);
//            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), AES);
//            cipher.init(Cipher.ENCRYPT_MODE, secret);
//            os = new FileOutputStream(newPath);
//            cipherOutputStream = new CipherOutputStream(os, cipher);
//            byte[] bytes = new byte[1024];
//            int len = -1;
//            while ((len = inputStream.read(bytes)) > 0) {
//                cipherOutputStream.write(bytes, 0, len);
//                cipherOutputStream.flush();
//            }
//        } catch (Exception e) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, e);
//            throw e;
//        } finally {
//            if (cipherOutputStream != null) {
//                cipherOutputStream.close();
//            }
//            if (os != null) {
//                os.close();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//            long time1 = System.currentTimeMillis();
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "全文件加密成功,耗时：" + (time1 - time0) + "毫秒");
//        }
//    }
//
//    /**
//     * 解密文件
//     *
//     * @param sourceFile 需要解密文件
//     * @param key        密钥
//     * @param newPath    新文件保存路径
//     */
//    public void decryptFile(String sourceFile, String key, String newPath) throws Exception {
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "全文件解密开始");
//        CipherInputStream cin = null;
//        OutputStream os = null;
//        InputStream inputStream = null;
//        long time0 = System.currentTimeMillis();
//        try {
//            Cipher cipher = Cipher.getInstance(AES);
//            SecretKeySpec secret = new SecretKeySpec(key.getBytes("UTF-8"), AES);
//            cipher.init(Cipher.DECRYPT_MODE, secret);
//            inputStream = new FileInputStream(sourceFile);
//            cin = new CipherInputStream(inputStream, cipher);
//            os = new FileOutputStream(newPath);
//            byte[] bytes = new byte[1024];
//            int len = -1;
//            while ((len = cin.read(bytes)) > 0) {
//                os.write(bytes, 0, len);
//                os.flush();
//            }
//        } catch (Exception e) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, e);
//            throw e;
//        } finally {
//            if (os != null) {
//                os.close();
//            }
//            if (cin != null) {
//                cin.close();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//            long time1 = System.currentTimeMillis();
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "全文件加密成功,耗时：" + (time1 - time0) + "毫秒");
//        }
//    }
//
//
//    /**
//     * 解密文件
//     *
//     * @param inputStream 需要解密文件的输入流
//     * @param key         密钥
//     * @param newPath     新文件保存路径
//     */
//    public void decryptFileCrypto(InputStream inputStream, String key, String newPath) throws Exception {
//        CipherInputStream cin = null;
//        OutputStream os = null;
//        try {
//            Cipher cipher = Cipher.getInstance(AES);
//            byte[] raw = getRawKey(key.getBytes());
//            SecretKeySpec secret = new SecretKeySpec(raw, AES);
//            cipher.init(Cipher.DECRYPT_MODE, secret);
//            cin = new CipherInputStream(inputStream, cipher);
//            os = new FileOutputStream(newPath);
//            byte[] bytes = new byte[1024];
//            int len = -1;
//            while ((len = cin.read(bytes)) > 0) {
//                os.write(bytes, 0, len);
//                os.flush();
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (os != null) {
//                os.close();
//            }
//            if (cin != null) {
//                cin.close();
//            }
//            if (inputStream != null) {
//                inputStream.close();
//            }
//        }
//    }
//
//    /**
//     * 该方法在java 端无法解密，删除
//     *
//     * @param seed
//     * @return
//     * @throws Exception
//     */
//    private static byte[] getRawKey(byte[] seed) throws Exception {
//        KeyGenerator kgen = KeyGenerator.getInstance(AES);
//        SecureRandom sr = null;
//        if (android.os.Build.VERSION.SDK_INT >= 17) {
//            sr = SecureRandom.getInstance(SHA1PRNG, "Crypto");
//        } else {
//            sr = SecureRandom.getInstance(SHA1PRNG);
//        }
//        sr.setSeed(seed);
//        kgen.init(128, sr);
//        SecretKey skey = kgen.generateKey();
//        byte[] raw = skey.getEncoded();
//        return raw;
//    }
//
//
//}
