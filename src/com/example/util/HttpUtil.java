package com.example.test.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//�ӷ������˻�ȡʡ���ص�����,�������ӷ�����
public class HttpUtil {
	//��static����Ϊ���Բ��ô���ʵ�������ɵ��ø÷����������߳�����Ϊ�����ȡ���ݷǳ���ʱ����ֹ����
	//���õ���HttpCallbackListener�ӿ�Ϊ��ʵ�ֻص����շ��������صĽ��
	public static void sendHttpRequest(final String address,
			final HttpCallbackListener listener) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				//TODO�Զ����ɵķ������ 
				HttpURLConnection connection = null;
				try {
					URL url = new URL(address);
					connection = (HttpURLConnection) url.openConnection();
					//GET��ʾ�ӷ������л�ȡ���ݣ��������õ������ӳ�ʱ����ȡ��ʱ������
					connection.setRequestMethod("GET"); 
					connection.setConnectTimeout(8000);
					connection.setReadTimeout(8000);
					//��Ҫ����һ�������������ڶ�ȡconnection�е���Ϣ
                    //�����Ƕ�ȡ��Ϣ�Ĵ���
					InputStream in = connection.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(in));
					StringBuilder response = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {//���ж�ȡ
						response.append(line);// һ���еĶ�ȡ���ݲ�׷�ӵ�builder��ȥ
					}
					if (listener != null) {
						// �ص�onFinish()����
						listener.onFinish(response.toString());
					}
				} catch (Exception e) {
					//TODO:�����쳣 
					if (listener != null) {
						// �ص�onError()����
						listener.onError(e);
					}
				} finally {
					if (connection != null) {
						connection.disconnect();//���Ӳ�Ϊ�վ͹ر�����
					}
				}
			}
		}).start();//�����߳�
	}

}