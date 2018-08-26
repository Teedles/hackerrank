package TenDaysOfStatsPoissonDistribution2;

public class Solution {

    public static void main(String[] args) {
		
		/*
		 * Poisson
		 * P(k,l) = l^k * e^-l / k!
		 * 
		 * p(k) = l*k / (k!)(e*l)
		 * 
		 * E[X²] = l + l²
		 */
		
		// cost for A => Ca = 160 + 40X² lambda = 0.88 X = num repairs
		// cost for B => Cb = 128 + 40Y² lambda = 1.55 Y = num repairs
		
		// machines start each day like new
		
		double lA = 0.88;
		double lB = 1.55;
		
		// daily costs
		
		// round to 3 places hr requirement
		System.out.format("%.3f%n",160 + 40 * ( lA + Math.pow(lA,2)));
		System.out.format("%.3f%n",128 +40 * ( lB + Math.pow(lB,2)));
	
    }    
}