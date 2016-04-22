package org.darcy.thread.concurrent;

import java.util.concurrent.CountDownLatch;

public class bCountDownLatch {
	// 启动很多线程，等待通知才能开始

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final CountDownLatch startLatch = new CountDownLatch(1);// 定义了一根门插销

		for (int i = 0; i < 10; i++) {
			Thread thread = new Thread("worker thread" + i) {
				public void run() {
					try {
						startLatch.await();// 如果门插销还没减完则等待
					} catch (InterruptedException e) {

					}
					// do xxxx
				}
			};
			thread.start();
		}
		startLatch.countDown();// 减少一根门插销
	}

}
