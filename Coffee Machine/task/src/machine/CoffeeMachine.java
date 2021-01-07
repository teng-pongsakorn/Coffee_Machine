package machine;

import java.util.Scanner;


public class CoffeeMachine {

    final static Scanner scanner = new Scanner(System.in);

    // coffee formula
    final static int water = 0, milk = 1, beans = 2, price = 3;
    final static int[] espressoFormula = {250, 0, 16, 4};
    final static int[] latteFormula = {350, 75, 20, 7};
    final static int[] cappuccinoFormula = {200, 100, 12, 6};

    // ingredient variables
    private int remainingWater;
    private int remainingMilk;
    private int remainingBeans;
    private int remainingCups;
    private int currentMoney;
    private boolean isRunning = true;

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.run();
    }

    public CoffeeMachine() {
        this.remainingWater = 400;
        this.remainingMilk = 540;
        this.remainingBeans = 120;
        this.remainingCups = 9;
        this.currentMoney = 550;
    }

    public void run() {
        while (this.isRunning) {
            System.out.println("\nWrite action (buy, fill, take, remain, exit):");
            String action = scanner.next();
            switch (action) {
                case "buy":
                    this.sellCoffee();
                    break;
                case "fill":
                    this.fillMachine();
                    break;
                case "take":
                    this.takeMoney();
                    break;
                case "remaining":
                    this.printState();
                    break;
                case "exit":
                    this.isRunning = false;
                    break;
            }
        }
    }

    private void takeMoney() {
        System.out.println("I gave you $" + currentMoney);
        this.currentMoney = 0;
    }

    private void fillMachine() {
        System.out.println("\nWrite how many ml of water do you want to add:");
        this.remainingWater += scanner.nextInt();
        System.out.println("Write how many ml of milk do you want to add:");
        this.remainingMilk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans do you want to add:");
        this.remainingBeans += scanner.nextInt();
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        this.remainingCups += scanner.nextInt();
    }

    private void sellCoffee() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        try {
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    this.makeCoffee(espressoFormula);
                    break;
                case 2:
                    this.makeCoffee(latteFormula);
                    break;
                case 3:
                    this.makeCoffee(cappuccinoFormula);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid choice");
        }
    }

    private boolean hasEnoughIngredients(int water, int milk, int beans) {
        return water <= this.remainingWater && milk <= this.remainingMilk &&
                beans <= this.remainingBeans && 1 <= this.remainingCups;
    }

    private void makeCoffee(int[] formula) {
        if (hasEnoughIngredients(formula[water], formula[milk], formula[beans])) {
            System.out.println("I have enough resources, making you a coffee!");
            this.remainingCups -= 1;
            this.remainingMilk -= formula[milk];
            this.remainingWater -= formula[water];
            this.remainingBeans -= formula[beans];
            this.currentMoney += formula[price];
        } else {
            StringBuilder builder = new StringBuilder();
            builder.append("Sorry, not enough ");
            builder.append(remainingWater < formula[water] ? "water" : "");
            builder.append(remainingMilk < formula[milk] ? ", milk" : "");
            builder.append(remainingBeans < formula[beans] ? ", beans" : "");
            builder.append(remainingCups < 1 ? ", cup" : "");
            System.out.println(builder.toString());
        }
    }

    @Override
    public String toString() {
        String text = "%nThe cofee machine has:%n" +
                "%d of water%n" +
                "%d of milk%n" +
                "%d of cofee beans%n" +
                "%d of disposable cups%n" +
                "$%d of money%n%n";
        return String.format(text, remainingWater, remainingMilk, remainingBeans, remainingCups, currentMoney);
    }

    private void printState() {
        String text = "%nThe cofee machine has:%n" +
                "%d of water%n" +
                "%d of milk%n" +
                "%d of cofee beans%n" +
                "%d of disposable cups%n" +
                "$%d of money%n%n";
        System.out.printf(text, this.remainingWater, this.remainingMilk, this.remainingBeans,
                this.remainingCups, this.currentMoney);
    }
}
