package runner;

import entity.Plants.FruitPlant;
import entity.humans.Human;
import entity.humans.Woman;
import x.gui.main.XMainWindow;

import java.util.Scanner;

// Класс для запуска программи
public class Runner {
 // Считываем данные с консоли и открываем главное окно
	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Which preferences do you want to use?");
		System.out.println("1. Standart");
		System.out.println("2. Custom");
		System.out.println("Enter 1 or 2");
		if (myObj.nextInt() == 2) {
			System.out.println("Enter average age of death");
			Human.setLifeDuration(myObj.nextInt());
			System.out.println("Enter pregnancy chance");
			Woman.setPregnancyChance(myObj.nextInt());
			System.out.println("Enter fruit drop chance");
			FruitPlant.setFruitDropChance(myObj.nextInt());
			System.out.println("Enter girl born chance");
			Woman.setBornChance(myObj.nextInt());
		}
		new XMainWindow();
	}

}
