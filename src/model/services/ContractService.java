package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService paymentService;
	
	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}
	
	public void processContract(Contract contract, Integer months) {
		
		for(int i = 1; i <= months; i++) {
			
			Double baseValue = contract.getTotalValue()/months;
			Double interest = paymentService.interest(baseValue, i);
			Double paymentFee = paymentService.paymentFee(baseValue + interest);
			
			Double installmentValue = baseValue + interest + paymentFee;
			LocalDate installmentDue = contract.getDate().plusMonths(i);
			
			contract.addInstallment(new Installment(installmentDue, installmentValue));		
			
		}
			
	}
	
}
