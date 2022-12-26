package pack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class Factory_Job {

	static class Job {
		int start, finish, profit;

		Job(int start, int finish, int profit) {
			this.start = start;
			this.finish = finish;
			this.profit = profit;
		}
	}

	static int latestNonConflict(Job arr[], int i) {
		for (int j = i - 1; j >= 0; j--) {
			if (arr[j].finish <= arr[i - 1].start)
				return j;
		}
		return -1;
	}

	static int findMaxProfitRec(Job arr[], int n) {

		if (n == 1)
			return arr[n - 1].profit;

		int inclProf = arr[n - 1].profit;
		int i = latestNonConflict(arr, n);
		if (i != -1)
			inclProf += findMaxProfitRec(arr, i + 1);

		int exclProf = findMaxProfitRec(arr, n - 1);

		return Math.max(inclProf, exclProf);
	}

	static int findMaxProfit(Job arr[], int n) {
		Arrays.sort(arr, new Comparator<Job>() {
			public int compare(Job j1, Job j2) {
				return j1.finish - j2.finish;
			}
		});

		return findMaxProfitRec(arr, n);
	}

	// Read file and store to a integer file
	private static int[] readFiles(String file) {

		try {
			File f = new File(file);
			Scanner s = new Scanner(f);
			int ctr = 0;
			while (s.hasNextInt()) {
				ctr++;
				s.nextInt();
			}
			int[] arr = new int[ctr];
			Scanner s1 = new Scanner(f);

			for (int i = 0; i < arr.length; i++) {
				arr[i] = s1.nextInt();
			}
			return arr;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

// Driver program
	public static void main(String args[]) {

		int[] data = readFiles("C:\\Users\\Dell\\Desktop\\Java_practice\\HighPeak\\src\\pack\\Input1");
		int m = data[0];

		Job arr[] = new Job[m];
		int n = arr.length;

		int arrLen = 0;

		for (int j = 1; j < data.length; j++) {
			arr[arrLen] = new Job(data[j], data[j + 1], data[j + 2]);
			arrLen++;
			j++;
			j++;
		}

		String output = "Earnings " + findMaxProfit(arr, m);
		File file1 = new File("C:\\Users\\Dell\\Desktop\\Java_practice\\HighPeak\\src\\pack\\Output1");
		
		try {
			FileWriter fw = new FileWriter(file1);
			PrintWriter pw = new PrintWriter(fw);
			pw.write(output);
			System.out.println("successful Execution");
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

