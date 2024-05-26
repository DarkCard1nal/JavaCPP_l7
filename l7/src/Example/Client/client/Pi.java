package client;

import compute.Task;
import java.math.BigDecimal;

public class Pi implements Task<BigDecimal> {
	private static final long serialVersionUID = 227L;
	private static final BigDecimal FOUR = BigDecimal.valueOf(4);
	@SuppressWarnings("deprecation")
	private static final int roundingMode = BigDecimal.ROUND_HALF_EVEN;
	private final int digits;

	public Pi(int digits) {
		this.digits = digits;
	}

	@Override
	public BigDecimal execute() {
		return computePi(digits);
	}

	@SuppressWarnings("deprecation")
	public static BigDecimal computePi(int digits) {
		int scale = digits + 5;
		BigDecimal arctan1_5 = arctan(5, scale);
		BigDecimal arctan1_239 = arctan(239, scale);
		BigDecimal pi = arctan1_5.multiply(FOUR).subtract(arctan1_239).multiply(FOUR);
		return pi.setScale(digits, BigDecimal.ROUND_HALF_UP);
	}

	@SuppressWarnings("deprecation")
	public static BigDecimal arctan(int X, int scale) {
		int d, i = 1;
		BigDecimal result, num, ter;
		BigDecimal invX = BigDecimal.valueOf(X);
		BigDecimal invX2 = BigDecimal.valueOf((long) X * X);

		num = BigDecimal.ONE.divide(invX, scale, roundingMode);

		result = num;

		do {
			d = 2 * i + 1;
			
			num = num.divide(invX2, scale, roundingMode);
			ter = num.divide(BigDecimal.valueOf(d), scale, roundingMode);
			
			if ((i % 2) != 0) {
				result = result.subtract(ter);
			} else {
				result = result.add(ter);
			}
			
			i++;
			
		} while (ter.compareTo(BigDecimal.ZERO) != 0);
		
		return result;
	}
}
