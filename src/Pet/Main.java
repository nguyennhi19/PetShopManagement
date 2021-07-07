package Pet;

import Pet.model.Pet;
import Pet.service.PetService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetService petService = new PetService();
        try {
            petService.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int choose;
        do {
            creatMenu();
            choose = scanner.nextInt();
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

        System.out.println("Nhập tuổi thú cưng: ");
        int age = sc.nextInt();
        String a = sc.nextLine();

        System.out.println("Nhập giới tính thú cưng: ");
        String gender = sc.nextLine();

        String dateOfBirth;
        do {
            System.out.println("Nhập ngày tháng năm sinh của thú cưng hợp lệ(MM/DD/YYYY hoặc MM-DD-YYYY): ");
            dateOfBirth = sc.nextLine();
        }while (checkInputDateOfBirth(dateOfBirth) == false);

        System.out.println("Nhập màu lông của thú cưng: ");
        String color = sc.nextLine();

        System.out.println("Giống Mèo của thú cưng: ");
        String species = sc.nextLine();

        System.out.println("Gía của thú cưng:");
        int price = sc.nextInt();

        System.out.println("Nhập số lượng: ");
        int  quantity = sc.nextInt();
        String abc = sc.nextLine();

        System.out.println("Nhập trạng thái: ");
        String  status = sc.nextLine();

        Pet pet = new Pet(name, age, gender,dateOfBirth, color, species, price, quantity, status);
        PetService petService = new PetService();
        Pet pet1 = petService.find(name);
        if (pet.equals(pet1)){
            System.out.println("tên Pet đã tồn tại:");
            System.out.println(pet1.toString());
        }else {
            petService.add(pet);
            System.out.println("Bạn đã thêm " + pet.getName() + " vào danh sách thành công!");
        }
    }

    public static void find(){
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Nhập tên Pet cần tìm kiếm(yêu cầu số điện thoại hợp lệ):");
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
        System.out.println("Nhập tên Pet cần xóa(yêu cầu số điện thoại hợp lệ):");
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
        System.out.println("Nhập tên Pet(yêu cầu số điện thoại hợp lệ):");
        name = sc.nextLine();

        Pet pet = petService.find(name);

        if (name == null){
            System.out.println("Không tìm được tên Pet");
        }else {
            System.out.println("Nhập thông tin mới:");

            System.out.println("Nhập tuổi thú cưng: ");
            int age = sc.nextInt();
            pet.setAge(age);
            String a = sc.nextLine();

            System.out.println("Nhập giới tính thú cưng: ");
            String gender = sc.nextLine();
            pet.setGender(gender);

            String dateOfBirth;
            do {
                System.out.println("Nhập ngày tháng năm sinh của thú cưng hợp lệ(MM/DD/YYYY hoặc MM-DD-YYYY): ");
                dateOfBirth = sc.nextLine();
            }while (checkInputDateOfBirth(dateOfBirth) == false);
            pet.setDateOfBirth(dateOfBirth);

            System.out.println("Nhập màu lông của thú cưng: ");
            String color = sc.nextLine();
            pet.setColor(color);

            System.out.println("Giống Mèo của thú cưng: ");
            String species = sc.nextLine();
            pet.setSpecies(species);

            System.out.println("Gía của thú cưng:");
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
        PetService petService = new PetService();
        petService.print();
    }

    public static void writeToFile() throws Exception{
        System.out.println("Đang tiến hành ghi vào file...");
        PetService petService = new PetService();
        petService.updateFile();
        System.out.println("Ghi vào file thành công");
    }
}
