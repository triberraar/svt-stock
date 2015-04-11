package be.tribersoft.svt.stock;

import java.util.Scanner;

import javax.inject.Inject;
import javax.inject.Named;

import be.tribersoft.svt.stock.services.api.StockService;
import be.tribersoft.svt.stock.services.svt.api.SVTService;

@Named
public class Application {

	@Inject
	private SVTService svtService;
	@Inject
	private StockService stockService;

	public void run() {
		System.out.println("Welcome to SVT stock, type q to quit");
		System.out.println("Please enter a stock name: ");

		String command;

		Scanner commandLineScanner = new Scanner(System.in);
		do {
			command = commandLineScanner.nextLine();
			if (command.equals("q")) {
				break;
			} else {
				stockService.lookupStock(command);
			}
			System.out.println("Please enter a stock name: ");
		} while (commandLineScanner.hasNextLine());

		commandLineScanner.close();
	}

}
