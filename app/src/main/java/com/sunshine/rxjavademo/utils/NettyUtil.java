package com.sunshine.rxjavademo.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NettyUtil {


    // 校验位的低位，data的倒数
    private static int checkSumByteLow = 2;
    // 校验位的高位，data的倒数
    private static int checkSumByteHigh = 1;

    private NettyUtil() {
        // 防止初始化
    }

    /**
     * 设置数据最尾两位累积和校验
     * 
     * @param outData
     */
    public static void setCheckSum(byte[] outData) {
        int checkSum = NettyUtil.getActuallyCheckSum(outData);
        outData[outData.length - checkSumByteHigh] = intToBytes(checkSum)[2];
        outData[outData.length - checkSumByteLow] = intToBytes(checkSum)[3];
    }

    public static boolean checkSum(byte[] data, int lenghtIn) {
        int actuallySum = getActuallyCheckSum(data, lenghtIn);
        int targetSum = getTargetCheckSum(data, lenghtIn);
        return actuallySum == targetSum;
    }

    public static void copyHead(byte[] source, byte[] target) {
        for (int i = 0; i < source.length; i++) {
            if (i < target.length) {
                target[i] = source[i];
            }
        }
    }

    private static int getTargetCheckSum(byte[] data, int lenghtIn) {
        int targetSum;
        targetSum = unsignedByteToInt(data[lenghtIn - checkSumByteLow])
                + unsignedByteToInt(data[lenghtIn - checkSumByteHigh]) * 256;
        return targetSum;
    }

    private static int getActuallyCheckSum(byte[] data) {
        int actuallySum = 0;
        for (int i = 0; i < (data.length - checkSumByteLow); i++) {
            actuallySum = actuallySum + unsignedByteToInt(data[i]);
        }
        return actuallySum;
    }

    private static int getActuallyCheckSum(byte[] data, int lenghtIn) {
        int actuallySum = 0;
        for (int i = 0; i < (lenghtIn - checkSumByteLow); i++) {
            actuallySum = actuallySum + unsignedByteToInt(data[i]);
        }
        return actuallySum;
    }

    public static int unsignedByteToInt(byte b) {
        return (int) b & 0xFF;
    }

    public static byte[] intToBytes(int n) {
        byte[] b = new byte[4];

        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
        }
        return b;
    }

    public static byte intToByte(int n) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (n >> (24 - i * 8));
        }
        return b[3];
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String byteToHexString(byte src) {
        byte[] srcs = new byte[1];
        srcs[0] = src;
        return bytesToHexString(srcs);
    }

    /**
     * 假如不是hex的字符，自动跳过
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toLowerCase();
        int length = getBtyeDataLen(hexString);
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        
        for (int pos = 0,i = 0; i < hexString.length();) {
            if (isHex(hexChars[i])) {
                d[pos] = (byte) (charToByte(hexChars[i]) << 4 | charToByte(hexChars[i + 1]));
                i=i+2;
                pos=pos+1;
            }else {
                i=i+1;
            }   
        }
        return d;
    }

    private static int getBtyeDataLen(String hexString) {
        char[] hexChars = hexString.toCharArray();
        int len = 0;
        for (int i = 0; i < hexString.length();i++) {
            if (isHex(hexChars[i])) {
                len=len+1;
            }else {
                
            }
        }
        return len/2;
    }

    public static boolean isHex(char c) {
        if ("0123456789abcdef".indexOf(c) != -1) {
            return true;
        }else {
            return false;
        }
    }
    
    @SuppressWarnings("unused")
    private static byte asciiToByte(char c) {
        return (byte) c;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789abcdef".indexOf(c);
    }

    public static String RedisKey(String macId, String key) {
        StringBuffer sb = new StringBuffer();
        sb.append(key).append(macId);
        return sb.toString();
    }

    // byte 数组与 long 的相互转换
    public static byte[] longToBytes(long x) {
        byte[] b = new byte[8];
        for (int i = 0; i < 8; i++) {
            b[i] = (byte) (x >>> (56 - (i * 8)));
        }
        return b;
    }

    public static long bytesToLong(byte[] bytes) {
        long temp = 0;
        long res = 0;
        for (int i = 0; i < 8; i++) {
            res <<= 8;
            temp = bytes[i] & 0xff;
            res |= temp;
        }
        return res;
    }

    public static Long decodeTime(byte[] lowToHigh) {
        long time = getLongTime(lowToHigh);
        int second = getIntFromLong(time, 0, (byte) 0x3f);
        int minute = getIntFromLong(time, 6, (byte) 0x3f);
        int hour = getIntFromLong(time, 12, (byte) 0x1f);
        int day = getIntFromLong(time, 17, (byte) 0x1f);
        int mon = getIntFromLong(time, 22, (byte) 0x0f);
        int year = getIntFromLong(time, 26, (byte) 0x3f);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year + 2000, mon - 1, day, hour, minute, second);
        return calendar.getTimeInMillis();
    }

    public static byte[] codeTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        long second = new Long(calendar.get(Calendar.SECOND));
        long minute = new Long(calendar.get(Calendar.MINUTE));
        long hour = new Long(calendar.get(Calendar.HOUR_OF_DAY));
        long day = new Long(calendar.get(Calendar.DAY_OF_MONTH));
        long mon = new Long(calendar.get(Calendar.MONTH) + 1);
        long year = new Long(calendar.get(Calendar.YEAR) - 2000);
        long data = year << 26 | mon << 22 | day << 17 | hour << 12 | minute << 6 | second << 0;
        byte[] reverse = new byte[4];
        byte[] normal = longToBytes(data);
        reverse[0] = normal[7];
        reverse[1] = normal[6];
        reverse[2] = normal[5];
        reverse[3] = normal[4];
        return reverse;
    }

    public static int getIntFromLong(long data, int move, byte and) {

        byte[] dataBytes = longToBytes(data >> move);
        byte ret = (byte) (dataBytes[7] & and);
        return unsignedByteToInt(ret);
    }

    public static byte[] reverseBytes(byte[] data) {
        byte[] reverse = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
        reverse[7] = data[0];
        reverse[6] = data[1];
        reverse[5] = data[2];
        reverse[4] = data[3];
        return reverse;
    }

    public static long getLongTime(byte[] data) {
        return bytesToLong(reverseBytes(data));
    }

    public static short crc16Byte(byte data, short p_crc) {
        short crc = p_crc;
        short highToLow = 0x0000;
        highToLow = (short) (highToLow | ((crc & 0xff00) >>> 8));
        short lowToHigh = 0x0000;
        lowToHigh = (short) (lowToHigh | (crc << 8));
        crc = (short) (highToLow | lowToHigh);
        crc = (short) ((crc) ^ ((short) (data & 0x00ff)));
        crc = (short) (crc ^ ((short) (crc & 0x00FF) >>> 4));
        crc = (short) (crc ^ ((crc << 8) << 4));
        crc = (short) (crc ^ ((crc & 0x00ff) << 4) << 1);
        return crc;
    }

    public static byte[] crc16Bytes(byte data[]) {
        short p_crc = (short) 0x1021;
        for (int i = 0; i < data.length; i++) {
            p_crc = crc16Byte(data[i], p_crc);
        }
        byte[] outData = new byte[2];
        outData[0] = (byte) ((p_crc & 0xff00) >> 8);
        outData[1] = (byte) ((p_crc & 0x00ff));
        return outData;
    }
    
    public static void main(String[] args){
//    	String byString = "cd0000000020000200002000020000200000ce26e000002ffff2ffff2ffff2ffff2ffff0";
//    	System.out.println("###:"+bytesToHexString(crc16Bytes(hexStringToBytes(byString))));
    }
   

    public static void setMacByteToData(List<String> macIds, byte[] resultByte, int position) {
        for (int i = 0; i < macIds.size(); i++) {
            byte[] mac = hexStringToBytes(macIds.get(i));
            int start = position + i * 6;
            for (int j = 0; j < mac.length; j++) {
                resultByte[start + j] = mac[(mac.length - 1) - j];
            }
        }
    }
}
