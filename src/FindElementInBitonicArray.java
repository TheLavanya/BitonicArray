public class FindElementInBitonicArray {

	public static void main(String[] args) {

		int nums1[] = { -3, 8, 9, 20, 17, 5, 1 };
		int k1 = 20;
		System.out.println(
				"Element " + k1 + " is at index : " + usingLinearSearchApproach1(nums1, k1) + " using Linear Search");
		System.out.println(
				"Element " + k1 + " is at index : " + usingBinarySearchApproach2(nums1, k1) + " using Binary Search");

		int nums2[] = { 5, 6, 7, 8, 9, 10, 3, 2, 1 };
		int k2 = 30;
		System.out.println(
				"Element " + k2 + " is at index : " + usingBinarySearchApproach2(nums2, k2) + " using Binary Search");

		int k3 = 2;
		System.out.println(
				"Element " + k3 + " is at index : " + usingBinarySearchApproach2(nums2, k3) + " using Binary Search");
	}

	// Time complexity- O(n) & Space complexity- O(1)
	private static int usingLinearSearchApproach1(int[] array, int k) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == k) {
				return i;
			}
		}
		return -1;
	}

	// Time complexity- O(log n) & Space complexity- O(1)
	private static int usingBinarySearchApproach2(int[] array, int k) {
		int n = array.length;
		int left = 0;
		int right = n - 1;
		int bitonicPoint = findBitonicPoint(array, n, left, right);
		int x = searchElement(array, n, k, bitonicPoint);
		if (x == -1) {
			System.out.println("Element not found");
		} else {
			System.out.println("Element found at index " + x);
		}
		return x;
	}

	private static int findBitonicPoint(int[] array, int n, int left, int right) {
		int mid = (left + right) / 2;
		if (array[mid] > array[mid + 1] && array[mid] > array[mid - 1]) {
			return mid;
		} else if (array[mid] > array[mid - 1] && array[mid] < array[mid + 1]) {
			findBitonicPoint(array, n, mid, right);
		} else if (array[mid] < array[mid - 1] && array[mid] > array[mid + 1]) {
			findBitonicPoint(array, n, left, mid);
		}
		return mid;
	}

	private static int searchElement(int[] array, int n, int element, int bitonicIndex) {

		if (element > array[bitonicIndex]) {
			return -1;
		} else if (element == array[bitonicIndex]) {
			return bitonicIndex;
		} else {
			int temp = ascendingBinarySearch(array, element, 0, bitonicIndex - 1);
			if (temp != -1) {
				return temp;
			}
			return descendingBinarySearch(array, element, bitonicIndex + 1, n - 1);
		}
	}

	private static int ascendingBinarySearch(int[] array, int element, int low, int high) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (array[mid] == element) {
				return mid;
			}
			if (array[mid] > element) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return -1;
	}

	private static int descendingBinarySearch(int[] array, int element, int low, int high) {
		while (low <= high) {
			int mid = low + (high - low) / 2;
			if (array[mid] == element) {
				return mid;
			} else if (array[mid] > element) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}
		}
		return -1;
	}
}
