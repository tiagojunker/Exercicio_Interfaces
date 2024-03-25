package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.services.ContractService;
import model.services.OnlinePaymentService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Entre com os dados do Contrato: ");
		System.out.print("Número: ");
		// sc.nextLine();
		Integer contractNumber = sc.nextInt();
		
		System.out.print("Data (DD/MM/AAAA): ");
		LocalDate contractDate = LocalDate.parse(sc.next(), formatter);
		
		System.out.print("Valor do Contrato R$: ");
		Double contractValue = sc.nextDouble();
		
		System.out.print("Número de parcelas: ");
		Integer numberOfInstallments = sc.nextInt();
		
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		
		OnlinePaymentService paypalService = new PaypalService();
		ContractService cs = new ContractService(paypalService);
		
		cs.processContract(contract, numberOfInstallments);
		
		System.out.println(contract.toString());
		
		sc.close();
	}
;
}
