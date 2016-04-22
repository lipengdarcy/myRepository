package org.darcy.thread.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 并发3定律
 * 
 * Amdahl定律. 给定问题规模，可并行化部分占12%，那么即使把并行运用到极致，系统的性能最多也只能提高1/(1-0.12)=1.136倍。
 * 即：并行对提高系统性能有上限。 Gustafson定律. Gustafson定律说Amdahl定律没有考虑随着cpu的增多而有更多的计算能力可被使用。
 * 其本质在于更改问题规模从而可以把Amdahl定律中那剩下的88 %的串行处理并行化，从而可以突破性能门槛。本质上是一种空间换时间。 Sun-Ni定律.
 * 是前两个定律的进一步推广。其主要思想是计算的速度受限于存储而不是CPU的速度. 所以要充分利用存储空间等计算资源，尽量增大问题规模以产生更好/更精确的解.
 * 
 * 
 */
public class aCountDownLatch {
	// 启动线程，然后等待线程结束。即常用的主线程等所有子线程结束后再执行的问题。

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		final int count = 10;
		final CountDownLatch completeLatch = new CountDownLatch(count);// 定义了门插销的数目是10

		for (int i = 0; i < count; i++) {
			Thread thread = new Thread("worker thread" + i) {
				public void run() {
					// do xxxx
					System.out.println("worker thread");
					completeLatch.countDown();// 减少一根门插销
				}
			};
			thread.start();
		}
		completeLatch.await();// 如果门插销还没减完则等待。
	}

}
