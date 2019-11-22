package com.example.test.util;

public interface HttpCallbackListener {
	//访问完成,当服务器响应时候返回
	void onFinish(String response);
	//访问出错,当出错时候返回
	void onError(Exception e);

}
