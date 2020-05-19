package cn.szag.oms.manager.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Http请求工具�?
 * @author snowfigure
 * @since 2014-8-24 13:30:56
 * @version v1.0.1
 */
public class HttpRequestUtil {
    static boolean proxySet = false;
    static String proxyHost = "127.0.0.1";
    static int proxyPort = 8087;
    /**
     * 编码
     * @param source
     * @return
     */
    public static String urlEncode(String source,String encode) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source,encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }
    public static String urlEncodeGBK(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "0";
        }
        return result;
    }
    /**
     * 发起http请求获取返回结果
     * @param req_url 请求地址
     * @return
     */
    public static String httpRequest(String req_url) {
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(req_url);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符�?
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return buffer.toString();
    }

    /**
     * 发�?�http请求取得返回的输入流
     * @param requestUrl 请求地址
     * @return InputStream
     */
    public static InputStream httpRequestIO(String requestUrl) {
        InputStream inputStream = null;
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            // 获得返回的输入流
            inputStream = httpUrlConn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }


    /**
     * 向指定URL发�?�GET方法的请�?
     *
     * @param url
     *            发�?�请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式�??
     * @return URL �?代表远程资源的响应结�?
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连�?
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属�?
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连�?
            connection.connect();
            // 获取�?有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历�?有的响应头字�?
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响�?
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            result = new String(result.getBytes("GBK"),"UTF-8");
        } catch (Exception e) {
            System.out.println("发�?�GET请求出现异常�?" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入�?
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指�? URL 发�?�POST方法的请�?
     *
     * @param url
     *            发�?�请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式�??
     * @param isproxy
     *               是否使用代理模式
     * @return �?代表远程资源的响应结�?
     */
    public static String sendPost(String url, String param,boolean isproxy) throws Exception{
        System.out.println(param);
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            if(isproxy){//使用代理模式
                @SuppressWarnings("static-access")
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            }else{
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 打开和URL之间的连�?

            // 发�?�POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法


            // 设置通用的请求属�?

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发�?�请求参�?
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响�?
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            //result = new String(result.getBytes("GBK"),"UTF-8");
        } catch (Exception e) {
            if(result.indexOf("Connection timed out: connect") > -1){
                result = "连接超时�?";
            }else{
                System.out.println("发�?? POST 请求出现异常�?"+e);
                result = e.toString();
            }
            throw new Exception(result);
        }
        //使用finally块来关闭输出流�?�输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
                throw new Exception(ex);
            }
        }
        return result;
    }
    
    /**
     * 向指�? URL 发�?�POST方法的请�?
     *
     * @param url
     *            发�?�请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式�??
     * @param isproxy
     *               是否使用代理模式
     * @return �?代表远程资源的响应结�?
     */
    public static String sendPost(String url, String param,boolean isproxy,String head) throws Exception{
        System.out.println(param);
        OutputStreamWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn = null;
            if(isproxy){//使用代理模式
                @SuppressWarnings("static-access")
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) realUrl.openConnection(proxy);
            }else{
                conn = (HttpURLConnection) realUrl.openConnection();
            }
            // 打开和URL之间的连�?

            // 发�?�POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");    // POST方法


            // 设置通用的请求属�?

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("api-key", "cqnKbx858oxvk2yF1oOYVQV9Urs=");
            conn.connect();

            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发�?�请求参�?
            out.write(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响�?
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            result = new String(result.getBytes("GBK"),"UTF-8");
        } catch (Exception e) {
            if(result.indexOf("Connection timed out: connect") > -1){
                result = "连接超时�?";
            }else{
                System.out.println("发�?? POST 请求出现异常�?"+e);
                result = e.toString();
            }
            throw new Exception(result);
        }
        //使用finally块来关闭输出流�?�输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
                throw new Exception(ex);
            }
        }
        return result;
    }
    
    /**
     * 向指定URL发�?�GET方法的请�?
     *
     * @param url
     *            发�?�请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式�??
     * @return URL �?代表远程资源的响应结�?
     */
    public static String sendGet(String url, String param,String head) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连�?
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属�?
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("api-key", "cqnKbx858oxvk2yF1oOYVQV9Urs=");
            // 建立实际的连�?
            connection.connect();
            // 获取�?有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历�?有的响应头字�?
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响�?
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            result = new String(result.getBytes("GBK"),"UTF-8");
        } catch (Exception e) {
            System.out.println("发�?�GET请求出现异常�?" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入�?
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        //demo:代理访问
//        String url = "http://api.adf.ly/api.php";
//        String para = "key=youkeyid&youuid=uid&advert_type=int&domain=adf.ly&url=http://somewebsite.com";
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("aaa","999");
            param.put("bbb",null);
            param.put("ccc","null");

            String result = HttpRequestUtil.paramsConvert("key",param);
            System.out.println(result);
//            String sr=HttpRequestUtil.sendPost(url,para,true);
//            System.out.println(sr);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendData(){

//            //POST请求
//            DataOutputStream out = new DataOutputStream(
//                    connection.getOutputStream());
//            Map<String, String> transMap = new HashMap<String, String>();
//            /**派单�?**/
//            transMap.put("sendby", "邢斌");
//            /**派单时间**/
//            transMap.put("senddate", "2018-01-01 00:00:00");
//
//            String paras = "order="+(transMap as JSON).toString()
//            //用这个方法可以指定参数编码集,不然会乱�?,
//            out.write(paras.getBytes("UTF-8"));
//            out.flush();
//            out.close();
//
//            //读取响应,只有执行到getInputStream的时�?,服务器才有响�?
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    connection.getInputStream()));
//            String lines;
//            StringBuffer sb = new StringBuffer("");
//            while ((lines = reader.readLine()) != null) {
//                lines = new String(lines.getBytes(), "utf-8");
//                sb.append(lines);
//            }
//            System.out.println(sb);
//            reader.close();
//            // 断开连接
//            connection.disconnect();

    }

    public static String paramsConvert(String paramsKey, Map<String, String> params) throws Exception{
        String parameters = paramsKey +"={";
        boolean hasParams = false;
        //将参数集合拼接成特定格式，如name=zhangsan&age=24
        for (String key : params.keySet()) {
            String val = params.get(key);
            if(val == null || "".equals(val) || "null".equals(val)){
                continue;
            }
            String value = URLEncoder.encode(params.get(key), "UTF-8");
            parameters += "\""+ key + "\":\"" + value + "\",";
            hasParams = true;
        }
        if (hasParams) {
            parameters = parameters.substring(0, parameters.length() - 1);
        }
        return parameters +"}";
    }

    public static String paramsConvert(Map<String, String> params) {
        String parameters = "";
        boolean hasParams = false;
        try {
	        //将参数集合拼接成特定格式，如name=zhangsan&age=24
	        for (String key : params.keySet()) {
	            String val = params.get(key);
	            if(val == null || "".equals(val) || "null".equals(val)){
	                continue;
	            }
//	            String value = URLEncoder.encode(params.get(key), "UTF-8");
	            String value = params.get(key);
				
	            parameters +=  key + "=" + value;
	            hasParams = true;
	            if (hasParams) {
	            	 parameters +=  "&";
	            }
	        }
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return parameters;
    }
}
