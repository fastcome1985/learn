//package com.fastcome1985.learn.util;
//
//import android.text.TextUtils;
//
//import com.didi.dr.locallog.LocalLogManager;
//import com.didi.drivingrecorder.consts.LocalLogTag;
//import com.didi.drivingrecorder.storage.db.dbmodel.StorageModel;
//import com.didi.drivingrecorder.storage.file.RecordFileStorage;
//import com.didi.drivingrecorder.storage.file.fileStorage.FileStorage;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//
///**
// * Created by luojianxiang on 2018/9/3.
// */
//
//public class FileSafeHelper {
//
//
//    private static FileAES fileAES;
//
//    private static FileAES getFileAES() {
//        if (fileAES == null) {
//            fileAES = new FileAES();
//        }
//        return fileAES;
//    }
//
//    /**
//     * 加密文件
//     *
//     * @param inputStream 原始流
//     * @param destPath    目标保存路径
//     * @return 加密后的文件路径  如果原始文件路径为空或者原始文件不存在  返回null
//     * @throws Exception
//     */
//    public static String encryptFilePart(InputStream inputStream, String destPath) throws Exception {
//        if (inputStream == null || TextUtils.isEmpty(destPath)) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "原始文件路径为空或者原始文件不存在");
//            return null;
//        }
//        getFileAES().encryptFilePart(inputStream, getFileAES().getSecretkeyNew(), destPath);
//        return destPath;
//    }
//
//
//    /**
//     * 加密文件
//     *
//     * @param originPath 原始路径
//     * @return 加密后的文件路径  如果原始文件路径为空或者原始文件不存在  返回null
//     * @throws Exception
//     */
//    public static String encryptFilePart(String originPath) throws Exception {
//        if (TextUtils.isEmpty(originPath) || !new File(originPath).exists()) {
//            LocalLogManager.getInstance().log(LocalLogTag.AES, "原始文件路径为空或者原始文件不存在");
//            return null;
//        }
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "加密" + originPath + "  " + FileStorage.getInstance().getExternalStorage().getSize(new File(originPath), SizeUnit.MB) + "M");
//        getFileAES().encryptFilePart(originPath, getFileAES().getSecretkeyNew());
//        return originPath;
//    }
//
//    /**
//     * 解密文件
//     *
//     * @param storageModel
//     * @return  解密后的文件路径
//     * @throws Exception
//     */
//    public static String decryptFile(final StorageModel storageModel) throws Exception {
//        if (!storageModel.isEncrypted()) {
//            return storageModel.getUrl();
//        }
//        String decryptPath = RecordFileStorage.buildTempPath(storageModel.getUrl(), storageModel.getFileType());
//        return decryptFile(storageModel, decryptPath);
//    }
//
//    /**
//     * 解密文件
//     *
//     * @param storageModel
//     * @param destPath     解密后文件保存路径,方法外自己保证文件不存在，否则会覆盖
//     * @return  解密后的文件路径
//     * @throws Exception
//     */
//    public static String decryptFile(final StorageModel storageModel, final String destPath) throws Exception {
//        if (!storageModel.isEncrypted()) {
//            return storageModel.getUrl();
//        }
//        LocalLogManager.getInstance().log(LocalLogTag.AES, "解密  " + storageModel.getUrl() + "   " + FileStorage.getInstance().getExternalStorage().getSize(new File(storageModel.getUrl()), SizeUnit.MB) + "M");
//        if (storageModel.getEncrypted() == StorageModel.ENCRYPTED_AES_CRYPTO) {
//            InputStream inputStream = new FileInputStream(storageModel.getUrl());
//            getFileAES().decryptFileCrypto(inputStream, FileAES.SECRETKEY, destPath);
//        } else if (storageModel.getEncrypted() == StorageModel.ENCRYPTED_AES_NORMAL) {
//            getFileAES().decryptFile(storageModel.getUrl(), getFileAES().getSecretkeyNew(), destPath);
//        } else if (storageModel.getEncrypted() == StorageModel.ENCRYPTED_AES_PART) {
//            getFileAES().decryptFilePartNewFile(storageModel.getUrl(), getFileAES().getSecretkeyNew(), destPath);
//        }
//        return destPath;
//    }
//
//}
