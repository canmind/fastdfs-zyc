package cn.me.fdfs.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhanghf
 *
 */

public class DigestUtil
{
	 private static char[] base64EncodeChars = new char[] {
	        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
	        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
	        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
	        'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
	        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
	        'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	        'w', 'x', 'y', 'z', '0', '1', '2', '3',
	        '4', '5', '6', '7', '8', '9', '+', '/' };

	    private static byte[] base64DecodeChars = new byte[] {
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
	    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
	    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
	    -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
	    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
	    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
	    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1 };
	    
	    static char hexdigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String getMd5Digest(String cleanText)
    {
        MessageDigest digest = null;
        try
        {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            // failed to load the MD5 algorithm
            return null;
        }

        byte[] hashBytes = null;
        try
        {
      
            digest.update( cleanText.getBytes());
            hashBytes = digest.digest();
        }
        catch (Exception e)
        {
            // failed to compute hash
            return null;
        }

        String result = encodeHex(hashBytes);

        return result;
    }
    
    public static String getMd5DigestFileByReadFile(String filePath)
    {
        MessageDigest digest = null;
    	String str = "", data = "";
    	
        try
        {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            // failed to load the MD5 algorithm
            return null;
        }

        byte[] hashBytes = null;
        try
        {
        	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));  
			while((data = br.readLine())!=null)  
			{  
			str = str + data;
		
			}
      
            digest.update( str.getBytes());
            hashBytes = digest.digest();
        }
        catch (Exception e)
        {
            // failed to compute hash
            return null;
        }

        String result = encodeHex(hashBytes);

        return result;
    }
    
	/**
	 * 对文件全文生成MD5摘要
	 * 
	 * @param file
	 *            要加密的文件
	 * @return MD5摘要码
	 */

	

	public static String getMd5DigestFile(String filePath) {

		
		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			fis = new FileInputStream(new File(filePath));
			byte[] buffer = new byte[2048];
			int length = -1;
			long s = System.currentTimeMillis();
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			byte[] b = md.digest();
			return byteToHexString(b);
			// 16位加密
			// return buf.toString().substring(8, 24);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				fis.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * 把byte[]数组转换成十六进制字符串表示形式
	 * @param tmp    要转换的byte[]
	 * @return 十六进制字符串表示形式
	 */
	private static String byteToHexString(byte[] tmp) {
		String s;
		// 用字节表示就是 16 个字节
		char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
		// 所以表示成 16 进制需要 32 个字符
		int k = 0; // 表示转换结果中对应的字符位置
		for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
			// 转换成 16 进制字符的转换
			byte byte0 = tmp[i]; // 取第 i 个字节
			str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, 
			// >>> 为逻辑右移，将符号位一起右移
			str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
		}
		s = new String(str); // 换后的结果转换为字符串
		return s;
	}

    private static String encodeHex(byte[] bytes)
    {
        StringBuffer hex = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++)
        {
            if (((int) bytes[i] & 0xff) < 0x10)
            {
                hex.append("0");
            }
            hex.append(Integer.toString((int) bytes[i] & 0xff, 16));
        }

        return hex.toString().toUpperCase();
    }

    
    //����
    public static String encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }
    //����
    public static byte[] decode(String str) throws UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        byte[] data = str.getBytes("US-ASCII");
        int len = data.length;
        int i = 0;
        int b1, b2, b3, b4;
        while (i < len) {
            /* b1 */
            do {
                b1 = base64DecodeChars[data[i++]];
            } while (i < len && b1 == -1);
            if (b1 == -1) break;
            /* b2 */
            do {
                b2 = base64DecodeChars[data[i++]];
            } while (i < len && b2 == -1);
            if (b2 == -1) break;
            sb.append((char)((b1 << 2) | ((b2 & 0x30) >>> 4)));
            /* b3 */
            do {
                b3 = data[i++];
                if (b3 == 61) return sb.toString().getBytes("iso8859-1");
                b3 = base64DecodeChars[b3];
            } while (i < len && b3 == -1);
            if (b3 == -1) break;
            sb.append((char)(((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));
            /* b4 */
            do {
                b4 = data[i++];
                if (b4 == 61) return sb.toString().getBytes("iso8859-1");
                b4 = base64DecodeChars[b4];
            } while (i < len && b4 == -1);
            if (b4 == -1) break;
            sb.append((char)(((b3 & 0x03) << 6) | b4));
        }
        return sb.toString().getBytes("iso8859-1");
    }
    
      
    public static void main(String[] args) {
		// TODO Auto-generated method stub
    	String abc = getMd5DigestFile("d:/1.vx2");
    	System.out.print(abc);

    }
      
}
