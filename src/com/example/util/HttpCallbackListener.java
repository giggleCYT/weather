package com.example.test.util;

public interface HttpCallbackListener {
	//�������,����������Ӧʱ�򷵻�
	void onFinish(String response);
	//���ʳ���,������ʱ�򷵻�
	void onError(Exception e);

}
