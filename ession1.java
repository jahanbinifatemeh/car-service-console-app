import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class ession1 {

    //region variables
    private static int maxService = 56;
    private static String[] numbers = new String[maxService];
    private static String[] names = new String[maxService];
    private static String[] mobiles = new String[maxService];
    private static Boolean[] exitedList = new Boolean[maxService];
    private static Long[][] invoicePrices = new Long[maxService][];
    private static String[][] invoiceTitles = new String[maxService][];
    private static int currentIndex = 0;
    //endregion

    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() < 8) {
            print("working day not started yet!");
            return;
        }
        showDayMessage(now);
        while (true) {
            showMenu();
            int menu = getMenu();
            switch (menu) {
                case 1:
                    enterCarMenu();
                    break;
                case 2:
                    showRemainMenu();
                    break;
                case 3:
                    showDetailsMenu();
                    break;
                case 4:
                    exitCarMenu();
                    break;
                case 0:
                    print("goodbye!");
                    return;
            }
        }
    }

    //region helper methods
    private static void exitCarMenu() {
        print("Please enter your service number : ");
        Integer serviceNumber = Integer.parseInt(getInput());
        serviceNumber--;
        if (!checkNumberValidity(serviceNumber)) {
            print("Your number is invalid");
            return;
        }
        print("Please enter invoice items count : ");
        Integer invoiceItemCount = Integer.parseInt(getInput());
        Long totalPrice = 0l;
        invoiceTitles[serviceNumber] = new String[invoiceItemCount];
        invoicePrices[serviceNumber] = new Long[invoiceItemCount];
        for (int index = 0; index < invoiceItemCount; index++) {
            print("[" + (index + 1) + "] -> Please enter invoice item title : ");
            invoiceTitles[serviceNumber][index] = getInput();
            print("[" + (index + 1) + "] -> Please enter invoice item price : ");
            invoicePrices[serviceNumber][index] = Long.parseLong(getInput());
            totalPrice += invoicePrices[serviceNumber][index];
        }
        exitedList[serviceNumber] = true;
        print("Total Price : " + totalPrice);
    }

    private static void showDetailsMenu() {
        print("Please enter your service number : ");
        Integer number = Integer.parseInt(getInput());
        number--;
        if (!checkNumberValidity(number)) {
            print("Your number is invalid");
            return;
        }
        if(numbers[number] == null && names[number] == null&& mobiles[number] == null ) print("the first car did not enter its info");
        else{print("Number : " + numbers[number]);
        print("Name : " + names[number]);
        print("Mobile : " + mobiles[number]);
        print("Status : " + (exitedList[number] ? "Serviced" : "InService"));}
    }

    private static void showRemainMenu() {
        int remain = maxService - currentIndex;
        int exited = 0, entered = 0;
        for (Boolean item : exitedList) {
            if (item == null) ;
            else if (item) exited++;
            else entered++;
        }
        print("Remains : " + remain);
        print("InService : " + entered);
        print("Serviced : " + exited);
    }

    private static void enterCarMenu() {
        int remains = maxService - currentIndex;
        if (remains == 0) {
            print("Capacity is full!");
            return;
        }
        print("Please enter car NO : ");
        numbers[currentIndex] = getInput();
        print("Please enter car driver's name : ");
        names[currentIndex] = getInput();
        print("Please enter car driver's mobile : ");
        mobiles[currentIndex] = getInput();
        exitedList[currentIndex] = false;
        print("Your service number is : " + (currentIndex + 1));
        currentIndex++;
    }

    private static boolean checkNumberValidity(Integer serviceNumber) {
        return serviceNumber >= 0 && serviceNumber < currentIndex;
    }

    private static int getMenu() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(bufferedReader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static void showMenu() {
        print("Please enter menu number to continue : ");
        print("1: Enter a car information");
        print("2: Show remain & entered & exited cars count");
        print("3: Show car details");
        print("4: Exit a car");
        print("0: Exit from program");
    }

    private static void showDayMessage(LocalDateTime date) {
        switch (date.getDayOfWeek()) {
            case MONDAY:
                print("Doshanbatoon bekheyr");
                break;
            case TUESDAY:
                print("Seshanbe bekheyr");
                break;
            case WEDNESDAY:
                print("Chaharshanbe bekheyr");
                break;
            case THURSDAY:
                print("Panjshanbe bekheyr");
                break;
            case FRIDAY:
                print("Jome bekheyr");
                break;
            case SATURDAY:
                print("Shanbe bekheyr");
                break;
            case SUNDAY:
                print("Yekshanbe bekheyr");
                break;
        }
    }

    private static void print(String message) {
        System.out.println(message);
    }

    private static String getInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    //endregion

}



