package com.wechat.util;

import com.wechat.factory.MenuFactory;
import com.wechat.thread.TokenThread;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wang_ on 2016-08-22.
 */
public class MenuUtil {
    public static void createMenu(String params, String accessToken) {
        StringBuffer bufferRes = new StringBuffer();
        try {

            URL realUrl = new URL("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken);

            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();

            // 连接超时
            conn.setConnectTimeout(25000);

            // 读取超时 --服务器响应比较慢,增大时间
            conn.setReadTimeout(25000);
            HttpURLConnection.setFollowRedirects(true);

            // 请求方式
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:21.0) Gecko/20100101 Firefox/21.0");
            conn.setRequestProperty("Referer", "https://api.weixin.qq.com/");
            conn.connect();

            // 获取URLConnection对象对应的输出流
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());

            // 发送请求参数
            //out.write(URLEncoder.encode(params,"UTF-8"));

            out.write(params);
            out.flush();
            out.close();

            InputStream in = conn.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            String valueString = null;
            while ((valueString = read.readLine()) != null) {
                bufferRes.append(valueString);
            }

            System.out.println(bufferRes.toString());
            in.close();
            if (conn != null) {
                // 关闭连接
                conn.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建自定义菜单(每天限制1000次)
     * */
    public static int createMenu(){
        String jsonMenu= MenuFactory.getMenu();
        int status=0;

        System.out.println("菜单："+jsonMenu);
        String path="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+ TokenThread.access_token.getAccess_token();
        HttpURLConnection conn = null;
        try {
            URL url=new URL(path);
            conn = (HttpURLConnection)url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.connect();
            OutputStream out = conn.getOutputStream();
            out.write(jsonMenu.getBytes("UTF-8"));
            out.flush();
            out.close();

            InputStream is = conn.getInputStream();
            int size = is.available();
            byte[] bt = new byte[size];
            is.read(bt);
            String message=new String(bt,"UTF-8");
            System.out.println("message: " + message);
            JSONObject jsonMsg = JSONObject.fromObject(message);
            status = Integer.parseInt(jsonMsg.getString("errcode"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return status;
    }
}
