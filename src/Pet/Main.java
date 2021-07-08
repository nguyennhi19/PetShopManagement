package Pet;

import Pet.model.Pet;
import Pet.service.PetService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        PetService petService = new PetService();
//        try {
//            petService.loadData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int choose;
        do {
            creatMenu();
            Scanner sc = new Scanner(System.in);
            do {
                while (!sc.hasNextInt()){
                    System.out.println("Hãy nhập lại số pet");
                    sc.next();
                }
                choose = sc.nextInt();}
            while (choose<0);
            switch (choose){
                case 1:
                    printPet();
                    break;
                case 2:
                    try {
                        addPet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        edit();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        find();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        readToFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        writeToFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    exit();
                    break;
            }
        }while (choose != 8);
    }

    public static void creatMenu(){
        System.out.println("----- Quản lý trang trại Pet -----");
        System.out.println("Chọn chức năng theo số (để tiếp tục)");
        System.out.println("1. Xem danh sách");
        System.out.println("2. Thêm mới");
        System.out.println("3. Cập nhật");
        System.out.println("4. Xóa");
        System.out.println("5. Tìm kiếm");
        System.out.println("6. Đọc từ file");
        System.out.println("7. Ghi vào file");
        System.out.println("8. Thoát");
        System.out.println("Chọn chức năng");
        System.out.println("------------------------------------------");
    }

    public static void printPet(){
        PetService ps = new PetService();
        ps.print();
        System.out.println("Danh sách đang có " + ps.size());
    }

    public static void addPet() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập thông tin:");

        System.out.println("Nhập tên thú cưng: ");
        String name = sc.nextLine();


        int age;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("Nhập tuổi Pet");
            while (!in.hasNextInt()){
                System.out.println("Hãy nhập lại tuổi Pet");
                in.next();
            }
            age = in.nextInt();}
        while (age<0);

        System.out.println("Nhập giới tính thú cưng: ");
        String gender = sc.nextLine();

        String dateOfBirth;
        do {
            System.out.println("Nhập ngày tháng năm sinh của thú cưng hợp lệ(MM/DD/YYYY hoặc MM-DD-YYYY): ");
            dateOfBirth = sc.nextLine();
        }while (checkInputDateOfBirth(dateOfBirth) == false);

        System.out.println("Nhập màu lông của Pet: ");
        String color = sc.nextLine();

        System.out.println("Giống Mèo của Pet: ");
        String species = sc.nextLine();


        int price;
        Scanner inp = new Scanner(System.in);
        do {
            System.out.println("Nhập giá Pet");
            while (!inp.hasNextInt()){
                System.out.println("Hãy nhập lại giá Pet");
                inp.next();
            }
            price = inp.nextInt();}
        while (price<0);

        int quantity;
        Scanner input = new Scanner(System.in);

        do {
            System.out.println("Nhập số lượng Pet");
            while (!input.hasNextInt()){
                System.out.println("Hãy nhập lại số lượng Pet");
                input.next();
            }
            quantity = input.nextInt();}
        while (quantity<0);

        System.out.println("Nhập trạng thái: ");
        String  status = sc.nextLine();

        Pet pet = new Pet(name, age, gender,dateOfBirth, color, species, price, quantity, status);
        PetService petService = new PetService();
        Pet pet1 = petService.find(name);
        if (pet.equals(pet1)){
            System.out.println("tên Pet đã tồn tại:");

        }else {
            petService.add(pet);
//            petService.updateFile();
            System.out.println("Bạn đã thêm " + pet.getName() + " vào danh sách thành công!");
        }
    }

    public static void find(){
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Nhập tên Pet cần tìm kiếm:");
        name = scanner.nextLine();

        PetService petService = new PetService();
        Pet pet = petService.find(name);
        if (pet == null){
            System.out.println("Không tồn tại");
        }else {
            System.out.println(petService.find(name).toStringInfo());
        }
    }

    public static void delete() throws Exception{
        PetService petService = new PetService();
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Nhập tên Pet cần xóa:");
        name = scanner.nextLine();

        Pet pet = petService.find(name);
        if (pet == null){
            System.out.println("Không tìm được thấy tên Pet");
        }else {
            System.out.println(petService.find(name).toString());
            petService.remove(name);
            System.out.println("Đã xóa thành công!");
        }
    }

    public static void edit() throws Exception{
        Scanner sc = new Scanner(System.in);
        PetService petService = new PetService();

        String name;
        System.out.println("Nhập tên Pet:");
        name = sc.nextLine();

        Pet pet = petService.find(name);

        if (pet == null){
            System.out.println("Không tìm được tên Pet");
        }else {
            System.out.println("Nhập thông tin mới:");

            int age;
            Scanner in = new Scanner(System.in);
            do {
                System.out.println("Nhập tuổi Pet:");
                while (!in.hasNextInt()){
                    System.out.println("Hãy nhập lại tuổi Pet:");
                    in.next();
                }
                age = in.nextInt();}
            while (age<0);

            System.out.println("Nhập giới tính Pet: ");
            String gender = sc.nextLine();
            pet.setGender(gender);

            String dateOfBirth;
            do {
                System.out.println("Nhập ngày tháng năm sinh của thú cưng hợp lệ(MM/DD/YYYY hoặc MM-DD-YYYY): ");
                dateOfBirth = sc.nextLine();
            }while (checkInputDateOfBirth(dateOfBirth) == false);
            pet.setDateOfBirth(dateOfBirth);

            System.out.println("Nhập màu lông của Pet: ");
            String color = sc.nextLine();
            pet.setColor(color);

            System.out.println("Giống Mèo của Pet: ");
            String species = sc.nextLine();
            pet.setSpecies(species);

            System.out.println("Gía của Pet:");
            int price = sc.nextInt();
            pet.setPrice(price);

            System.out.println("Nhập số lượng: ");
            int  quantity = sc.nextInt();
            pet.setQuantity(quantity);
            String abc = sc.nextLine();

            System.out.println("Nhập trạng thái: ");
            String  status = sc.nextLine();
            pet.setStatus(status);

            System.out.println("Cập nhật thành công!");
            System.out.println(petService.find(name).toString());
            petService.updateFile();
        }
    }

    public static void exit(){
        System.out.println("Đã thoát");
        System.exit(0);
    }

    public static boolean checkInputDateOfBirth(String dateOfBirth){
        String regex = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateOfBirth);
        return matcher.find();
    }

    public static void readToFile() throws Exception{
        System.out.println("Đang tiến hành đọc từ file...");
        PetService petService = new PetService();
        try {
            petService.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Đọc từ file thành công");
    }

    public static void writeToFile() throws Exception{
        System.out.println("Đang tiến hành ghi vào file...");
        PetService petService = new PetService();
        petService.updateFile();
        System.out.println("Ghi vào file thành công");
    }
}
