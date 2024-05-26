package client;

import compute.Task;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class E implements Task<BigDecimal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal accuracy;
	private BigDecimal val;

	public E(double accuracy, double val) {
		this.val = BigDecimal.valueOf(val);
		this.accuracy = BigDecimal.valueOf(accuracy);
	}

	@Override
	public BigDecimal execute() {
		BigDecimal e = BigDecimal.ZERO;
		BigDecimal i = BigDecimal.ZERO;
		BigDecimal tmp;
		
		while (true) {
			tmp = calculate(i, val);
			
			if (tmp.compareTo(accuracy) < 0) {
				break;
			}
			
			e = e.add(tmp);
			i = i.add(BigDecimal.ONE);
		}

		return e;
	}

	private BigDecimal calculate(BigDecimal n, BigDecimal x) {
		BigDecimal f = BigDecimal.ONE;
		
		for (BigDecimal i = BigDecimal.ONE; i.compareTo(n) <= 0; i = i.add(BigDecimal.ONE)) {
			f = f.multiply(i);
		}

		for (BigDecimal i = BigDecimal.ONE; i.compareTo(n) <= 0; i = i.add(BigDecimal.ONE)) {
			x = x.multiply(x);
		}

		return x.divide(f, 99, RoundingMode.HALF_UP);
	}

}