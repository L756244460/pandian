package cn.datasset.yipandian.client.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import cn.datasset.yipandian.client.constant.Constants;

/**
 * @author chendong
 * @date 2020/9/9 20:53
 * @description des对称加密工具累
 */
public class DESUtils {
	
	/**
	 * 偏移变量，固定8位字节
	 */
	private static final String IV_PARAMETER = "12345678";
	
	/**
	 * 秘钥算法
	 */
	private static final String ALGORITHM = "DES";
	
	/**
	 * 加密/解密算法-工作模式-填充模式
	 */
	private static final String CIPHER_ALGORITHM = "DES/CBC/PKCS5Padding";
	
	/**
	 * 加密编码
	 */
	private static final String CHARSET = Charset.defaultCharset().name();
	
	/**
	 * 生成des key
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static Key generateKey(String password) throws Exception {
		DESKeySpec dks = new DESKeySpec(password.getBytes(CHARSET));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		return keyFactory.generateSecret(dks);
	}
	
	/**
	 * DES加密字符串
	 * @param password
	 * @param data
	 * @return
	 */
	public static String encrpt(String password, String data) {
		if (password == null || password.length() < 8) throw new RuntimeException(Constants.DES_ERROR);
		if (data == null) return null;
		try {
			Key secretKey = generateKey(password);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			byte[] bytes = cipher.doFinal(data.getBytes(CHARSET));
			return new String(Base64.getEncoder().encode(bytes));
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}
	
	/**
	 * DES解密字符串
	 * @param password 解密密码，长度不能够小于8位
	 * @param data     待解密字符串
	 * @return 解密后内容
	 */
	public static String decrypt(String password, String data) {
		if (password == null || password.length() < 8) throw new RuntimeException(Constants.DES_ERROR);
		if (data == null) return null;
		try {
			Key secretKey = generateKey(password);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			return new String(cipher.doFinal(Base64.getDecoder().decode(data.getBytes(CHARSET))), CHARSET);
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}
	
	/**
	 * DES加密文件
	 * @param srcFile  待加密的文件
	 * @param destFile 加密后存放的文件路径
	 * @return 加密后的文件路径
	 */
	public static String encryptFile(String password, String srcFile, String destFile) {
		if (password == null || password.length() < 8) throw new RuntimeException(Constants.DES_ERROR);
		try {
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, generateKey(password), iv);
			InputStream is = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(destFile);
			CipherInputStream cis = new CipherInputStream(is, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = cis.read(buffer)) > 0) {
				out.write(buffer, 0, r);
			}
			cis.close();
			is.close();
			out.close();
			return destFile;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	/**
	 * DES解密文件
	 * @param srcFile  已加密的文件
	 * @param destFile 解密后存放的文件路径
	 * @return 解密后的文件路径
	 */
	public static String decryptFile(String password, String srcFile, String destFile) {
		if (password == null || password.length() < 8) throw new RuntimeException(Constants.DES_ERROR);
		try {
			File file = new File(destFile);
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes(CHARSET));
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, generateKey(password), iv);
			InputStream is = new FileInputStream(srcFile);
			OutputStream out = new FileOutputStream(destFile);
			CipherOutputStream cos = new CipherOutputStream(out, cipher);
			byte[] buffer = new byte[1024];
			int r;
			while ((r = is.read(buffer)) >= 0) {
				cos.write(buffer, 0, r);
			}
			cos.close();
			is.close();
			out.close();
			return destFile;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(encrpt("chd-salt", "abc@2020"));
		System.out.println(decrypt("4MWlJTDESnw=", "YKP2greVefoyFfieecbOyw=="));
		System.out.println(decrypt("4MWlJTDESnw=", "Zc8hzcYQ5RLFijfOIIR4+A=="));
	}
}
