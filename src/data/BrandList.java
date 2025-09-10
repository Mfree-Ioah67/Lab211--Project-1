
package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import utils.Inputter;

public class BrandList {
    //props:    
    ArrayList<Brand> brandList = new ArrayList<>();
    private final String URL_BRAND = "D:\\FPTU\\LAB211\\Lab1\\01_Brand.txt";
    
    //methods:
    //+loadDataFromFile
    public boolean loadDataFromFile() {
        brandList.clear();
        File f = new File(URL_BRAND);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line!=null){
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                String country = st.nextToken().trim();
                Brand newBrand = new Brand(id, name, country);
                brandList.add(newBrand);
                line = reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Your file is corrupted: " + e);
            return false;
        }
    }
    //+ searchIndexById
    public int searchIndexOfBrandById(String keyId){
        for (int i = 0; i < brandList.size(); i++) {
            if(brandList.get(i).getId().equals(keyId)){
                return i;
            }
        }
        return -1;
    }
    //+searchbrandById
    public Brand searchBrandById(String keyId){
        int pos = searchIndexOfBrandById(keyId);
        return pos == -1 ? null : brandList.get(pos);
    } 
    //+createNewBrand:
    public void createNewBrand(String Id){
        String Name = Inputter.getString("Input name of new brand: ", 
            "That fielđ is required!!!").trim();
        String country = Inputter.getString("Input country of new brand: ", 
            "That fielđ is required!!!").trim();
        Brand newBrand = new Brand(Id, Name, country);
        brandList.add(newBrand);
        saveDataToFile();
    }
    //+printbrandList:
    public void printbrandList(){
        System.out.println("These are the brands available in the system: ");
        for (Brand brand : brandList) {
            System.out.println("    " + brand);
        }
    }
    //+saveDataToFile:
    public boolean saveDataToFile(){
        File f = new File(URL_BRAND);
        try {
            OutputStreamWriter writter = new OutputStreamWriter(new FileOutputStream(f));
            for (Brand br : brandList) {
                writter.write(br.toString());
                writter.write("\n");
            }
            writter.flush();//save xong nhớ dừng
            return true;
        } catch (Exception e) {
            System.err.println("Your file is corrupted: " + e);
            return false;
        }
    }
}
