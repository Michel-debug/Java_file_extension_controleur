package subClasses;
import java.io.*;
import java.util.HashMap;
/**
 *@Description: a class to deal the file
 *@name console
 *@Author: echo
 *@Date: 21:11 2021/1/4
**/
public class Console {
    public static final HashMap<String, String> mFileTypes = new HashMap<String, String>();
    //use keymap to store the right extension
    static {
    mFileTypes.put("FFD8FFE0", "jpg");
    mFileTypes.put("52494646","jpg");
    mFileTypes.put("FFD8FFE1", "jpg");
    mFileTypes.put("FFD8FFDB", "jpg");
    mFileTypes.put("036A6C7A", "pptx");
    mFileTypes.put("04656368", "pptx");
    mFileTypes.put("4D5A5000", "exe");
    mFileTypes.put("5B544F43", "md");
    mFileTypes.put("23232323", "md");
    mFileTypes.put("5B313232", "log");
    mFileTypes.put("89504E47", "png");
    mFileTypes.put("47494638 ", "gif");
    mFileTypes.put("49492A00", "tif");
    mFileTypes.put("424D", "bmp");
    mFileTypes.put("41433130", "dwg");
    mFileTypes.put("38425053 ", "psd");
    mFileTypes.put("7B5C7274", "rtf");
    mFileTypes.put("3C3F786D", "xml");
    mFileTypes.put("3C636F6D", "xml");
    mFileTypes.put("68746D6C ", "html");
    mFileTypes.put("44656C69", "eml");
    mFileTypes.put("CFAD12FE ", "dbx");
    mFileTypes.put("2142444E", "pst");
    mFileTypes.put("D0CF11E0", "xls/doc");
    mFileTypes.put("5374616E", "mdb");
    mFileTypes.put("FF575043", "wpd");
    mFileTypes.put("25215053", "eps/ps");
    mFileTypes.put("25504446", "pdf");
    mFileTypes.put("E3828596", "pwl");
    mFileTypes.put("504B0304", "zip");
    mFileTypes.put("504B0304","pptx");
    mFileTypes.put("52617221", "rar");
    mFileTypes.put("57415645", "wav");
    mFileTypes.put("41564920", "avi");
    mFileTypes.put("2E7261FD", "ram");
    mFileTypes.put("2E524D46", "rm");
    mFileTypes.put("000001BA", "mpg");
    mFileTypes.put("000001B3", "mpg");
    mFileTypes.put("6D6F6F76", "mov");
    mFileTypes.put("3026B275", "asf");
    mFileTypes.put("4D546864", "mid");
    mFileTypes.put("377ABCAF","7z");
    mFileTypes.put("3C254020","jsp");
    //txt 是文本文件，没有文件头
    }
    /**
    *@Description: get the real MyFile type
    *@name getFileType
    *@Author: echo
    *@Date: 11:14 2021/1/3
    **/
     public static String getFileType(String filePath) {
    //		System.out.println(getFileHeader(filePath));
    //		System.out.println(mFileTypes.get(getFileHeader(filePath)));
        //获取文件路径
        String temp[]=filePath.split("\\.");
        String extension =temp[temp.length-1];
        //获取后缀名
        String fileHeader=getFileHeader(filePath);
        if(!mFileTypes.containsKey(fileHeader)){
            System.out.println("\r\nno such file or it's txt");
            return "no file";
        }else {
          //  System.out.println("\nthe name of extension is: " + extension);
          //  System.out.println("the real extension is: [" + fileHeader + "] indicates " + mFileTypes.get(fileHeader));
          //  System.out.println(mFileTypes.get(getFileHeader(filePath)).indexOf(extension));
            if (mFileTypes.get(getFileHeader(filePath)).indexOf(extension) == -1) {
                //search in real MyFile name to see if it can match to extension,
                // use one of the compare method in String
                //if can't find this extension, return -1
               // return false;
            } else {
               // System.out.println("        --right");
                //return true;
            }
        }
        //return false;
        return mFileTypes.get(getFileHeader(filePath));

    }
    /**
     *@Description: get MyFile header by inputStream and return Hex
     *@name getFileHeader
     *@Author: echo
     *@Date: 11:16 2021/1/3
    **/
    public static String getFileHeader(String filePath) {
        //文件字节输入流，以字节形式读取
        FileInputStream is = null;
        String value = "null";
        try {
            is = new FileInputStream(filePath);
            byte[] b = new byte[4];
            /*
             * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
             * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
             * 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
             */
            is.read(b, 0, b.length);
            //转十六进制
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }
    /**
    *@Description: return format hex
    *@name
    *@Author: echo
    *@Date: 11:13 2021/1/3
    **/
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
    //		System.out.println(builder.toString());
        return builder.toString();
    }
    /**
    *@Description: calculate the size of the MyFile B/KB...
    *@name
    *@Author: echo
    *@Date: 11:20 2021/1/3
    **/
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }

}