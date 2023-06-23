package model.services;

public class PaypalService implements OnlinePaymentService{

	@Override
	public Double paymentFee(Double amount) {
		amount *= 0.02;
		return amount;
	}

	@Override
	public Double interest(Double amount) {
		amount *= 0.01;
		return amount;
	}

}
