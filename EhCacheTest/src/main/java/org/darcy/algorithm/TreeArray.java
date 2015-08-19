package org.darcy.algorithm;

/**
 * S[1]=a[1];
 * 
 * S[2]=a[1]+a[2];
 * 
 * S[3]=a[3];
 * 
 * S[4]=a[1]+a[2]+a[3]+a[4];
 * 
 * S[5]=a[5];
 * 
 * S[6]=a[5]+a[6];
 * 
 * S[7]=a[7];
 * 
 * S[8]=a[1]+a[2]+a[3]+a[4]+a[5]+a[6]+a[7]+a[8];
 * 
 * 之所以采用这样的分布方式，是因为我们使用的是这样的一个公式：S[i]=a[i-2k+1]+....+a[i]。
 * 
 * 其中：2k 中的k表示当前S[i]在树中的层数，它的值就是i的二进制中末尾连续0的个数，2k也就是表示S[i]中包含了哪些a[]，
 * 
 * 举个例子: i=610=01102 ；可以发现末尾连续的0有一个，即k=1，则说明S[6]是在树中的第二层，并且S[6]中有21项，随后我们求出了起始项：
 * 
 * a[6-21+1]=a[5]，但是在编码中求出k的值还是有点麻烦的，所以我们采用更灵巧的Lowbit技术，即：2k=i&-i 。
 * 
 * 则：S[6]=a[6-21+1]=a[6-(6&-6)+1]=a[5]+a[6]。
 * */
public class TreeArray {

	static int[] sumArray = new int[8];

	static int[] arr = new int[8];

	public static void main(String[] args) {
		Init();

		System.out.print("A数组的值:");
		for (int element : arr) {
			System.out.printf("%s ", element);
		}
		System.out.print("\nS数组的值:");
		for (int element : sumArray) {
			System.out.printf("%s ", element);
		}

		System.out.println("\n修改A[1]的值为3");
		Modify(1, 3);

		System.out.print("A数组的值:");
		for (int element : arr) {
			System.out.printf("%s ", element);
		}
		System.out.print("\nS数组的值:");
		for (int element : sumArray) {
			System.out.printf("%s ", element);
		}

	}

	// / 初始化两个数组
	public static void Init() {
		for (int i = 1; i <= 8; i++) {
			arr[i - 1] = i;

			// 设置其实坐标：i=1开始
			int start = (i - Lowbit(i));
			int sum = 0;
			while (start < i) {
				sum += arr[start];
				start++;
			}
			sumArray[i - 1] = sum;
		}
	}

	public static void Modify(int x, int newValue) {
		// 拿出原数组的值
		int oldValue = arr[x];
		arr[x] = newValue;
		for (int i = x; i < arr.length; i += Lowbit(i + 1)) {
			// 减去老值，换一个新值
			sumArray[i] = sumArray[i] - oldValue + newValue;
		}
	}

	// 求前n项和
	public static int Sum(int x) {
		int ans = 0;
		int i = x;
		while (i > 0) {
			ans += sumArray[i - 1];
			// 当前项的最大子树
			i -= Lowbit(i);
		}
		return ans;
	}

	// 当前的sum数列的起始下标
	public static int Lowbit(int i) {
		return i & -i;
	}

}
