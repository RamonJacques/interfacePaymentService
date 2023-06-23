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
		LocalDate dueDate = contract.getDate();
		for (int i = 1; i <= months; i++) {
			double auxAmount;
			auxAmount = onlinePaymentService.interest(amount) * i + amount;
			auxAmount += onlinePaymentService.paymentFee(auxAmount);
			dueDate = dueDate.plusMonths(1);
			Installment installment = new Installment(dueDate, auxAmount);
			contract.installments.add(installment);
		}		
	}		
}
