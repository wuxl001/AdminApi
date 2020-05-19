package cn.szag.oms.manager.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

public class EncryptUtil {
	private static final String KEY = "fe88505b91c9db3e";  
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";  
    public static String base64Encode(byte[] bytes){  
        return Base64.encodeBase64String(bytes);  
    }  
	public static byte[] base64Decode(String base64Code) throws Exception{  
        return new BASE64Decoder().decodeBuffer(base64Code);  
    }  
    public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));  

        return cipher.doFinal(content.getBytes("utf-8"));  
    }  
    public static String aesEncrypt(String content, String encryptKey) throws Exception {  
        return base64Encode(aesEncryptToBytes(content, encryptKey));  
    }  
    public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {  
        KeyGenerator kgen = KeyGenerator.getInstance("AES");  
        kgen.init(128);  

        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);  
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));  
        byte[] decryptBytes = cipher.doFinal(encryptBytes);  

        return new String(decryptBytes);  
    }  
    public static String getKey(){
    	return KEY;
    }
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {  
        return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);  
    }  
    
    public static String aesDecrypt(String encryptStr) throws Exception {  
    	return aesDecryptByBytes(base64Decode(encryptStr), KEY);  
    }  

    public static String bytesToHexString(int src) {
    	  StringBuilder stringBuilder = new StringBuilder("");

    	  String hv;
    	  hv = Integer.toHexString(src & 0xFF);
    	  if (hv.length() < 2) {
    	   stringBuilder.append(0);
    	  }
    	  stringBuilder.append(hv);
    	  return stringBuilder.toString();
    	 }
    
    public static String getRandomString(int length){

    	Random random=new Random();

    	StringBuffer sb=new StringBuffer();

    	for(int i=0;i<length;i++){

    	int number=random.nextInt(3);

    	long result=0;

    	switch(number){

    	case 0:

    	result=Math.round(Math.random()*25+65);

    	sb.append(String.valueOf((char)result));

    	break;

    	case 1:

    	result=Math.round(Math.random()*25+97);

    	sb.append(String.valueOf((char)result));

    	break;

    	case 2:    

    	sb.append(String.valueOf(new Random().nextInt(10)));

    	break;

    	}

    	}

    	return sb.toString();

    }

    /**
     * 判断是哪个系�?
     * @param requestHeader
     * @return
     */
    public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : �?有android设备,
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手�?
         */
        String[] deviceArray = new String[]{"android"};
        String[] deviceArrayd = new String[]{"mac os"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return false;
            }
        }
        for(int i=0;i<deviceArrayd.length;i++){
            if(requestHeader.indexOf(deviceArrayd[i])>0){
                return true;
            }
        }
        return false;
}


    /**
     * Java后台访问url链接，返回JSON格式的数�?
     * @return
     */
	
    public static JSONObject getAllEmployee(String url) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>() {
                // 成功调用连接后，对返回数据进行的操作
                public JSONObject handleResponse(final HttpResponse response)
                        throws ClientProtocolException, IOException {
                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        // 获得调用成功�? 返回的数�?
                        HttpEntity entity = response.getEntity();
                        if (null != entity) {
                            String result = EntityUtils.toString(entity);
                            // 根据字符串生成JSON对象
                            JSONObject resultObj = JSONObject.fromObject(result);
                            return resultObj;
                        } else {
                            return null;
                        }
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };
            // 返回的json对象
            JSONObject responseBody = httpclient.execute(httpPost, responseHandler);
            return responseBody;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
 
    /**
     * Base64解码
     * @param str
     * @return
     */
    public static String decode(String str) {
        byte[] bt = null;
        String s= "";
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
            s = new String(bt, "GB2312");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
    
    
    /**
	 * md5加密方法
	 * @param password
	 * @return
	 */
	public static String md5Password(String password) {
 
		try {
			// 得到�?个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把没�?个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运�?
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}
 
			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
 
	}
	
	static final byte CRC8_TAB[] =
        {
                (byte) 0x00, (byte) 0x07, (byte) 0x0E, (byte) 0x09, (byte) 0x1C, (byte) 0x1B, (byte) 0x12, (byte) 0x15, (byte) 0x38, (byte) 0x3F, (byte) 0x36, (byte) 0x31, (byte) 0x24, (byte) 0x23, (byte) 0x2A, (byte) 0x2D,
                (byte) 0x70, (byte) 0x77, (byte) 0x7E, (byte) 0x79, (byte) 0x6C, (byte) 0x6B, (byte) 0x62, (byte) 0x65, (byte) 0x48, (byte) 0x4F, (byte) 0x46, (byte) 0x41, (byte) 0x54, (byte) 0x53, (byte) 0x5A, (byte) 0x5D,
                (byte) 0xE0, (byte) 0xE7, (byte) 0xEE, (byte) 0xE9, (byte) 0xFC, (byte) 0xFB, (byte) 0xF2, (byte) 0xF5, (byte) 0xD8, (byte) 0xDF, (byte) 0xD6, (byte) 0xD1, (byte) 0xC4, (byte) 0xC3, (byte) 0xCA, (byte) 0xCD,
                (byte) 0x90, (byte) 0x97, (byte) 0x9E, (byte) 0x99, (byte) 0x8C, (byte) 0x8B, (byte) 0x82, (byte) 0x85, (byte) 0xA8, (byte) 0xAF, (byte) 0xA6, (byte) 0xA1, (byte) 0xB4, (byte) 0xB3, (byte) 0xBA, (byte) 0xBD,
                (byte) 0xC7, (byte) 0xC0, (byte) 0xC9, (byte) 0xCE, (byte) 0xDB, (byte) 0xDC, (byte) 0xD5, (byte) 0xD2, (byte) 0xFF, (byte) 0xF8, (byte) 0xF1, (byte) 0xF6, (byte) 0xE3, (byte) 0xE4, (byte) 0xED, (byte) 0xEA,
                (byte) 0xB7, (byte) 0xB0, (byte) 0xB9, (byte) 0xBE, (byte) 0xAB, (byte) 0xAC, (byte) 0xA5, (byte) 0xA2, (byte) 0x8F, (byte) 0x88, (byte) 0x81, (byte) 0x86, (byte) 0x93, (byte) 0x94, (byte) 0x9D, (byte) 0x9A,
                (byte) 0x27, (byte) 0x20, (byte) 0x29, (byte) 0x2E, (byte) 0x3B, (byte) 0x3C, (byte) 0x35, (byte) 0x32, (byte) 0x1F, (byte) 0x18, (byte) 0x11, (byte) 0x16, (byte) 0x03, (byte) 0x04, (byte) 0x0D, (byte) 0x0A,
                (byte) 0x57, (byte) 0x50, (byte) 0x59, (byte) 0x5E, (byte) 0x4B, (byte) 0x4C, (byte) 0x45, (byte) 0x42, (byte) 0x6F, (byte) 0x68, (byte) 0x61, (byte) 0x66, (byte) 0x73, (byte) 0x74, (byte) 0x7D, (byte) 0x7A,
                (byte) 0x89, (byte) 0x8E, (byte) 0x87, (byte) 0x80, (byte) 0x95, (byte) 0x92, (byte) 0x9B, (byte) 0x9C, (byte) 0xB1, (byte) 0xB6, (byte) 0xBF, (byte) 0xB8, (byte) 0xAD, (byte) 0xAA, (byte) 0xA3, (byte) 0xA4,
                (byte) 0xF9, (byte) 0xFE, (byte) 0xF7, (byte) 0xF0, (byte) 0xE5, (byte) 0xE2, (byte) 0xEB, (byte) 0xEC, (byte) 0xC1, (byte) 0xC6, (byte) 0xCF, (byte) 0xC8, (byte) 0xDD, (byte) 0xDA, (byte) 0xD3, (byte) 0xD4,
                (byte) 0x69, (byte) 0x6E, (byte) 0x67, (byte) 0x60, (byte) 0x75, (byte) 0x72, (byte) 0x7B, (byte) 0x7C, (byte) 0x51, (byte) 0x56, (byte) 0x5F, (byte) 0x58, (byte) 0x4D, (byte) 0x4A, (byte) 0x43, (byte) 0x44,
                (byte) 0x19, (byte) 0x1E, (byte) 0x17, (byte) 0x10, (byte) 0x05, (byte) 0x02, (byte) 0x0B, (byte) 0x0C, (byte) 0x21, (byte) 0x26, (byte) 0x2F, (byte) 0x28, (byte) 0x3D, (byte) 0x3A, (byte) 0x33, (byte) 0x34,
                (byte) 0x4E, (byte) 0x49, (byte) 0x40, (byte) 0x47, (byte) 0x52, (byte) 0x55, (byte) 0x5C, (byte) 0x5B, (byte) 0x76, (byte) 0x71, (byte) 0x78, (byte) 0x7F, (byte) 0x6A, (byte) 0x6D, (byte) 0x64, (byte) 0x63,
                (byte) 0x3E, (byte) 0x39, (byte) 0x30, (byte) 0x37, (byte) 0x22, (byte) 0x25, (byte) 0x2C, (byte) 0x2B, (byte) 0x06, (byte) 0x01, (byte) 0x08, (byte) 0x0F, (byte) 0x1A, (byte) 0x1D, (byte) 0x14, (byte) 0x13,
                (byte) 0xAE, (byte) 0xA9, (byte) 0xA0, (byte) 0xA7, (byte) 0xB2, (byte) 0xB5, (byte) 0xBC, (byte) 0xBB, (byte) 0x96, (byte) 0x91, (byte) 0x98, (byte) 0x9F, (byte) 0x8A, (byte) 0x8D, (byte) 0x84, (byte) 0x83,
                (byte) 0xDE, (byte) 0xD9, (byte) 0xD0, (byte) 0xD7, (byte) 0xC2, (byte) 0xC5, (byte) 0xCC, (byte) 0xCB, (byte) 0xE6, (byte) 0xE1, (byte) 0xE8, (byte) 0xEF, (byte) 0xFA, (byte) 0xFD, (byte) 0xF4, (byte) 0xF3};

public static byte crc8(byte[] msg_ptr, int len) {
    int x;
    int index;
    byte xorResult;

    xorResult = 0x00;

    for (x = 0; x < len; x++) {
        index = ((xorResult ^ (msg_ptr[x])) & 0x00ff);
        xorResult = CRC8_TAB[index];
    }

    return (byte) ~xorResult;
}
public String createFile(String text) throws IOException {
    String filePath = "D:/a/b";
    File dir = new File(filePath);
    // 一、检查放置文件的文件夹路径是否存在，不存在则创建
    if (!dir.exists()) {
        dir.mkdirs();// mkdirs创建多级目录
    }
    String run = filePath + "/"+UuidUtil.get32UUID()+".html";
    File checkFile = new File(run);
    FileWriter writer = null;
    try {
        // 二、检查目标文件是否存在，不存在则创建
        if (!checkFile.exists()) {
            checkFile.createNewFile();// 创建目标文件
        }
        // 三、向目标文件中写入内容
        // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
        writer = new FileWriter(checkFile, true);
        writer.append(text);
        writer.flush();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (null != writer)
            writer.close();
    }
	return run;
}
	public static void main(String[] args) {
		EncryptUtil e = new EncryptUtil();
		try {
			//e.createFile("11");
			FileInputStream in=new FileInputStream("C://Users//123//Desktop//002.txt");
			FileOutputStream out=new FileOutputStream("C://Users//123//Desktop//b.txt");
			//创建一个小卡车，循环来回转载货物，从仓库（源头）到达商场（目的地）
			byte[]buffer=new byte[1024];
			int readLength;
			while((readLength=in.read(buffer))>0){//这里的in.read(buffer);就是把输入流中的东西，写入到内存中（buffer）。
				//System.out.println(new String(buffer,0,readLength));//这里顺利将字节数组转化为了字符串
				out.write(buffer);//这里就是把内存中（buffer)的内容写出到输出流中，也就写出到了指定文件中
				String imgSrc = new String(buffer,0,readLength);
				 if (!"".equals(imgSrc) && (imgSrc.startsWith("data:image/png;base64") || imgSrc.startsWith("data:image/jpng;base64"))) {
		                // 判断imgSrc是否为空且是否以"http://"开头
		                System.out.println("正在下载的图片的地址：" + imgSrc);
		            }
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
    /**
     * 测试
     * 
     */
//    public static void main(String[] args) throws Exception {
//    	Random r =  new Random();
//    	int src=r.nextInt(255);
//		String id=EncryptUtil.bytesToHexString(src);
//		id+=EncryptUtil.bytesToHexString(r.nextInt(255));
//		String msg=id;
//		byte bt;
//		  List<Byte> List =  new ArrayList<>();
//		  byte[] List = new byte[84];
//		  char[] ch = msg.trim().toCharArray();
//		  for (int i = 0; i < ch.length; i++) {
//		   bt = (byte) ch[i];
//		   if (bt == '0') {
//		    bt = (byte) 0x30;
//		    
//		   }
//		   List.add(bt);
//		   List[i]=(byte)Integer.parseInt(Integer.toHexString(bt));
//		   System.out.println(List[i]);
//		  }
//		  byte crc = (byte) CRC8.GetCRC8(List);
//		  String pa1="0x"+Integer.toHexString(r.nextInt(255));
//		  String pa2="0x"+Integer.toHexString(r.nextInt(255));
//		  System.out.println(CRC8.CRC(r.nextInt(255),r.nextInt(255)));
//		  msg=msg+Integer.toHexString(0x00ff & crc);
//		  System.out.println("crc8:" + Integer.toHexString(0x00ff & crc));
//		  System.out.println(msg);
//		  System.out.println(Long.parseLong(id, 16));
//    	1547538851000
//		1547542782000
//      String content = "abc123";  //0gqIDaFNAAmwvv3tKsFOFf9P9m/6MWlmtB8SspgxqpWKYnELb/lXkyXm7P4sMf3e
//        System.out.println("加密前：" + md5Password(content));  
//        
//        System.out.println("加密密钥和解密密钥：" + KEY);  
//        String gh="";
//		gh=content.substring(0, 2);
//		System.out.println(gh);
//		gh+="****"+content.substring(content.length()-2,content.length());
//		System.out.println(gh);
//        //加密
//        String encrypt = aesEncrypt(content, KEY);  
//        System.out.println(encrypt.length()+":加密后：" + encrypt);  
//        
//        //解密
//        String decrypt = aesDecrypt(encrypt, KEY);  
//        System.out.println("解密后：" + decrypt);  
//        
//        System.out.println(getRandomString(16));
//         try {
//            //这里只有�?条数据，有多条数据的话可以用循环，然后拼接url字符�?
//            String url = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x=114.018628333333340&y=22.537186666666667";
//            JSONObject json = getAllEmployee(url);
//            //将经纬度解码后进行打�?
//            String latitude = decode(json.getString("x"));
//            String longitude = decode(json.getString("y"));
//            System.out.println("经度为：" + latitude);
//            System.out.println("纬度为：" + longitude);
//        } catch (Exception e) {  
//            e.printStackTrace();
//        } 
//    }
}
