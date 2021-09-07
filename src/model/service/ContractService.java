package model.service;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installments;

public class ContractService {

	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, Integer months) {
		double installmentValue = contract.getTotalValue() / months;
		double installmentQuota = 0.0;
		double totalInstallmentQuota = 0.0;
		Date dateInstallments = contract.getDate();

		for (int i = 1; i <= months; i++) {
			installmentQuota = installmentValue + onlinePaymentService.interest(installmentValue, i);
			totalInstallmentQuota = installmentQuota + onlinePaymentService.paymentFee(installmentQuota);
			contract.getInstallments().add(new Installments(addMonth(dateInstallments, i), totalInstallmentQuota));
		}

	}

	public Date addMonth(Date date, Integer n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, n);
		return cal.getTime();
	}

}
