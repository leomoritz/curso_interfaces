package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installments;
import model.service.ContractService;
import model.service.PayPalService;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		System.out.println("Enter contract data");

		System.out.print("Number: ");
		int contractNumber = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (DD/MM/YYYY): ");
		Date contractDate = sdf.parse(sc.nextLine());
		System.out.print("Contract Value: ");
		double contractValue = sc.nextDouble();

		Contract contract = new Contract(contractNumber, contractDate, contractValue);

		System.out.print("Enter number of installments: ");
		int numberInstallments = sc.nextInt();

		ContractService cs = new ContractService(new PayPalService());
		cs.processContract(contract, numberInstallments);

		System.out.println("Installments");
		for (Installments installment : contract.getInstallments()) {
			System.out.println(installment);
		}

		sc.close();

	}

}
