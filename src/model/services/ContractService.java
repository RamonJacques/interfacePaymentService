package model.services;

import model.entities.Contract;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract (Contract contract, Integer months) {
		double amount = contract.getTotalValue()/months;
		for (int i = 1; i <= months; i++) {
			amount += onlinePaymentService.interest(amount) * i;
			amount += onlinePaymentService.paymentFee(amount);
		}
	}
		
}
