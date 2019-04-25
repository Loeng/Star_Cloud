package cn.com.bonc.sce.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class HexUtil {
	public static String bytes2HexString(byte[] b) {
		String ret = "";
		String val = "";
		for (int i = 0; i < b.length; ++i) {
			val = Integer.toHexString(b[i] & 0xFF);
			if (val.length() == 1)
				val = "0" + val;
			ret = ret + val;
		}
		return ret;
	}

	public static byte bytes2Hex(byte b) {
		if (b >= 97) {
			return (byte) (b - 97 + 10);
		}
		return (byte) (b - 48);
	}

	public static String byteToString(byte[] bByte, int bLen) {
		String temp = "";
		String str = "";
		for (int i = 0; i < bLen; ++i) {
			temp = String.format("%02X",
					new Object[] { Byte.valueOf(bByte[i]) });

			str = str + temp;
		}
		return str;
	}

	public static String byte2String(byte[] bByte) {
		String temp = "";
		String str = "";
		for (int i = 0; i < bByte.length; ++i) {
			temp = String.format("%02X",
					new Object[] { Byte.valueOf(bByte[i]) });

			str = str + temp;
		}
		return str;
	}

	public static byte[] stringToByte(String str, byte[] bByte) {
		int bLen = str.length() / 2;

		for (int i = 0; i < bLen; ++i) {
			bByte[i] = (byte) hexToDec(str.substring(i * 2, i * 2 + 2));
		}

		return bByte;
	}

	public static int hexToDec(String str) {
		int iResult = 0;
		int iTemp = 0;

		str = str.toUpperCase();
		int len = str.length();

		for (int i = len; i > 0; --i) {
			iTemp = str.charAt(i - 1);
			if ((iTemp <= 57) && (iTemp >= 48))
				iTemp -= 48;
			if ((iTemp <= 70) && (iTemp >= 65)) {
				iTemp -= 55;
			}
			iResult += iTemp * (int) Math.pow(16.0D, str.length() - i);
		}
		return iResult;
	}

	public static long npf(int d, int n) {
		long m = 1L;
		for (int i = 0; i < n; ++i)
			m *= d;
		return m;
	}

	public static boolean isRightData(char c, int jz) {
		if ((jz < 2) || (jz > 16))
			return false;
		String t = "00112233445566778899aAbBcCdDeEfF";
		char[] ch = t.toCharArray();
		int i = 0;
		for (i = 0; i < jz; ++i) {
			if (c == ch[(i * 2)])
				return true;
			if (c == ch[(i * 2 + 1)])
				return true;
		}
		return true;
	}

	public static boolean isHex(String InStr) {
		if (InStr == "")
			return false;
		for (int i = 0; i < InStr.length(); ++i)
			if ((((InStr.charAt(i) < '0') || (InStr.charAt(i) > '9')))
					&& (((InStr.charAt(i) < 'a') || (InStr.charAt(i) > 'f')))
					&& (((InStr.charAt(i) < 'A') || (InStr.charAt(i) > 'F')))) {
				return false;
			}
		return true;
	}

	public static boolean isDec(String InStr) {
		if (InStr == "")
			return false;
		for (int i = 0; i < InStr.length(); ++i) {
			if ((InStr.charAt(i) < '0') || (InStr.charAt(i) > '9'))
				return false;
		}
		return true;
	}

	public static String byteArrayToHexString(byte[] b) {
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; ++i) {
			int v = b[i] & 0xFF;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[(i / 2)] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}

		return data;
	}

	public static String getClassesPath() {
		String path = "";
		path = HexUtil.class.getResource("/").toString();

		Properties sysProperties = System.getProperties();
		Object object = sysProperties.get("os.name");
		String operation = String.valueOf(object);
		if (operation.substring(0, 4).equals("Wind")) {
			if (path.startsWith("file"))
				path = path.substring(6);
			else if (path.startsWith("jar"))
				path = path.substring(10);
		} else if (path.startsWith("file"))
			path = path.substring(5);
		else if (path.startsWith("jar")) {
			path = path.substring(9);
		}

		if ((path.endsWith("/")) || (path.endsWith("\\")))
			path = path.substring(0, path.length() - 1);
		try {
			path = URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return path;
	}

}
