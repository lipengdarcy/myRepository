package org.darcy.algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 优先级队列是不同于先进先出队列的另一种队列。每次从队列中取出的是具有最高优先权的元素。
 * 
 * 　　PriorityQueue是从JDK1.5开始提供的新的数据结构接口。
 * 
 * 　　如果不提供Comparator的话，优先队列中元素默认按自然顺序排列，也就是数字默认是小的在队列头，字符串则按字典序排列。
 * 
 * 由于网上的资料大多将优先级队列各个方法属性，很少有实例讲解的，为方便大家以后使用，我就写了个demo~
 * 
 * 如果想实现按照自己的意愿进行优先级排列的队列的话，需要实现Comparator接口。下面的方法，实现了根据某个变量，来进行优先级队列的建立。
 * */
public class PriorityQueueTest {

	private String name;
	private int population;

	public PriorityQueueTest(String name, int population) {
		this.name = name;
		this.population = population;
	}

	public String getName() {
		return this.name;
	}

	public int getPopulation() {
		return this.population;
	}

	public String toString() {
		return getName() + " - " + getPopulation();
	}

	public static void main(String args[]) {
		Comparator<PriorityQueueTest> OrderIsdn = new Comparator<PriorityQueueTest>() {
			public int compare(PriorityQueueTest o1, PriorityQueueTest o2) {
				// TODO Auto-generated method stub
				int numbera = o1.getPopulation();
				int numberb = o2.getPopulation();
				if (numberb > numbera) {
					return 1;
				} else if (numberb < numbera) {
					return -1;
				} else {
					return 0;
				}

			}
		};
		Queue<PriorityQueueTest> priorityQueue = new PriorityQueue<PriorityQueueTest>(
				11, OrderIsdn);

		PriorityQueueTest t1 = new PriorityQueueTest("t1", 1);
		PriorityQueueTest t3 = new PriorityQueueTest("t3", 3);
		PriorityQueueTest t2 = new PriorityQueueTest("t2", 2);
		PriorityQueueTest t4 = new PriorityQueueTest("t4", 0);
		priorityQueue.add(t1);
		priorityQueue.add(t3);
		priorityQueue.add(t2);
		priorityQueue.add(t4);
		System.out.println(priorityQueue.poll().toString());
	}
}
