package Pet.model;

public class Pet {
    String name;
    int age;
    String gender;
    String dateOfBirth;
    String color;
    String species;
    int price;
    int quantity;
    String status;

    public Pet(String name, int age, String gender, String dateOfBirth,
               String color, String species, int price, int quantity, String status) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.color = color;
        this.species = species;
        this.price = price;
        this.quantity = quantity;
        this.status =status;
    }

    public Pet(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pet){
            Pet pet = (Pet) o;
            if (pet.getName().equals(this.name)){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public String toString() {
        return  "| Tên : " + String.format("%-10s|",name) + " Tuổi: " + String.format("%-4s |", age) +
                " Giới tính: " + String.format("%-6s|",gender)  + " Ngày sinh: " + String.format("%-6s|",dateOfBirth)
                + " Màu lông: " + String.format("%-10s |",color) + " Giống loài: " + String.format("%-14s |",species) +
                " Gía: " + String.format("%-6s |",String.format("%,d",price)) + " Số lượng: " + String.format("%-4s |",
                quantity) + " Trạng thái: " + String.format("%-6s |",status);
    }

    public String toStringCSV(){
        return name+","+ age +","+gender+","+dateOfBirth+","+color+","+species+","+price+","+quantity+","+status+"\n";
    }

    public String toStringInfo() {
        return   "| Tên : " + String.format("%-10s|",name) + " Tuổi: " + String.format("%-4s |", age) +
                " Giới tính: " + String.format("%-6s|",gender)  + " Ngày sinh: " + String.format("%-6s|",dateOfBirth)
                + " Màu lông: " + String.format("%-10s |",color) + " Giống loài: " + String.format("%-14s |",species) +
                " Gía: " + String.format("%-6s |",String.format("%,d",price)) + " Số lượng: " + String.format("%-4s |",
                quantity) + " Trạng thái: " + String.format("%-6s |",status);
    }
}
