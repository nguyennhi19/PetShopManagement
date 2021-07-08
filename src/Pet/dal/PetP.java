package Pet.dal;

import Pet.model.Pet;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PetP {
    public static Map<String, Pet> petList;

    static {
        petList = new HashMap<>();
    }

    public void saveFile() throws Exception{
        File file = new File("pet.csv");
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream("pet.csv",true);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        for (Map.Entry<String, Pet> entry : petList.entrySet()){
            bos.write(entry.getValue().toStringCSV().getBytes());
        }
        bos.flush();
        bos.close();
        fos.close();
    }

    public void add(Pet pet) throws Exception{
        petList.put(pet.getName(), pet);
    }

    public void readFile() throws Exception{

        File file = new File("pet.csv");
        if (!file.exists()){
            return;
        }else {
            FileReader fileReader = new FileReader("pet.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String[] arr = line.split(",");
                Pet pet = new Pet(arr[0],Integer.parseInt(arr[1]),arr[2],
                        arr[3],arr[4],arr[5],Integer.parseInt(arr[6]),Integer.parseInt(arr[7]),arr[8]);
                add(pet);
            }
            bufferedReader.close();
            fileReader.close();
        }
    }

    public boolean remove(String name){
        boolean result = petList.remove(name) == null;
        return result;
    }
}
