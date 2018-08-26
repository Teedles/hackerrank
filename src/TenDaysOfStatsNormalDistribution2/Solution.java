package TenDaysOfStatsNormalDistribution2;

public class Solution {

    public static void main(String[] args) {

    	int mean = 70;
    	int sdev = 10;
    	
		// round to 2 places hr requirement DIFFERENT!!!!
    	// cdf 100-80 - scored higer than 80
		System.out.format("%.2f%n", (100 - cdf(mean, sdev, 80) * 100));
		// cdf 100-60 - passed the test
		System.out.format("%.2f%n", (100 - cdf(mean, sdev, 60) * 100));
		// cdf 60 -0 - failed the test
		System.out.format("%.2f%n", (cdf(mean, sdev, 60) * 100));

    }
    
    // cumulative distribution funcion
    private static double cdf(double mean, double sdev, double x) {
    	double parm = (x - mean) / (sdev * Math.sqrt(2));
    	
    	return 0.5 * (1 + erf(parm));
    }
    
    // yay internets! no idea how to have done this on my own
    private static double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));

        // use Horner's method
        double ans = 1 - t * Math.exp( -z*z   -   1.26551223 +
                                            t * ( 1.00002368 +
                                            t * ( 0.37409196 + 
                                            t * ( 0.09678418 + 
                                            t * (-0.18628806 + 
                                            t * ( 0.27886807 + 
                                            t * (-1.13520398 + 
                                            t * ( 1.48851587 + 
                                            t * (-0.82215223 + 
                                            t * ( 0.17087277))))))))));
        if (z >= 0) return  ans;
        else        return -ans;
}
    
}
