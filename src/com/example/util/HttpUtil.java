package com.example.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//从服务器端获取省市县的数据,请求链接服务器
public class HttpUtil {
	//用static是因为可以不用创建实例，即可调用该方法。开子线程是因为网络获取数据非常耗时，防止堵塞
	//还用到了HttpCallbackListener接口为了实现回调接收服务器返回的结果
	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				//TODO自动生成的方法存根 
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					//GET表示从服务器中获取数据，下面设置的是连接超时，读取超时毫秒数
					connection.setRequestMethod("GET"); 
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					//还要定义一个输入流，用于读取connection中的信息
                    //下面是读取信息的代码
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {//按行读取
						response.append(line);// 一行行的读取内容并追加到builder中去
					}
					if (listener != null) {
						// 回调onFinish()方法
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					//TODO:处理异常 
					if (listener != null) {
						// 回调onError()方法
						listener.onError(e);
					}
				} finally {
					if (connection != null) {
						connection.disconnect();//连接不为空就关闭连接
					}
				}
			}
		}).start();//运行线程
	}

}