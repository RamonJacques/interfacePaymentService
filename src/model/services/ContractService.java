package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

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
			double auxAmount = amount;
			LocalDate dueDate = contract.getDate().plusMonths(i);
			auxAmount += onlinePaymentService.interest(amount, i);
			auxAmount += onlinePaymentService.paymentFee(auxAmount);
			Installment installment = new Installment(dueDate, auxAmount);
			contract.installments.add(installment);
		}		
	}		
}
