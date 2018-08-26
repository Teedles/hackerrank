package TenDaysOfStatsCentralLimitTheorem3;

public class Solution {

	public static void main (String[] args) {

		int n = 100;
		double mean = 500;
		double sdev = 80;
		double z = 1.96; // this is 95% confidence

		double moe = z * sdev / Math.sqrt(n); // !!!

		// round to 2 places hr requirement DIFFERENT!!!!
		System.out.format("%.2f%n", (mean - moe));
		System.out.format("%.2f%n", (mean + moe));

	}
}
