package model.service;

public class PayPalService implements OnlinePaymentService {

	private final double MONTHLY_INTEREST = 0.01;
	private final double PAYMENT_FEE = 0.02;
	
	@Override
	public double interest(double amount, int months) {
		return amount * months * MONTHLY_INTEREST;
	}

	@Override
	public double paymentFee(double amount) {
		return amount * PAYMENT_FEE;
	}
}
