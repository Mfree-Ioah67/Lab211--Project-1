
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

public class CategoryList {
    //props:
    ArrayList<Category> categoryList = new ArrayList<>();
    private final String URL_CATEGORY = "D:\\FPTU\\LAB211\\Lab1\\01_Category.txt";
    
    //method:
    //+loadDataFromFile
    public boolean loadDataFromFile() {
        categoryList.clear();
        File f = new File(URL_CATEGORY);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            while(line!=null){
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                Category newCate = new Category(id, name);
                categoryList.add(newCate);
                line = reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("File corruption!! " + e);
            return false;
        }
    }
    //+ searchIndexById
    public int searchIndexOfCategoryById(String keyId){
        for (int i = 0; i < categoryList.size(); i++) {
            if(categoryList.get(i).getId().equals(keyId)){
                return i;
            }
        }
        return -1;
    }
    //+searchCategoryById
    public Category searchCategoryById(String keyId){
        int pos = searchIndexOfCategoryById(keyId);
        return pos == -1 ? null : categoryList.get(pos);
    }  
    //+createNewCategory:
    public void createNewCategory(String Id){
        String Name = Inputter.getString("Input name of new category: ", 
            "That field is required!!!").trim();
        Category newCategory = new Category(Id, Name);
        categoryList.add(newCategory);
    }
    //+printCategoryList:
    public void printCategoryList(){
        System.out.println("These are the categories available in the system: ");
        for (Category cate : categoryList) {
            System.out.println("    " + cate);
        }
    }

    //+saveDataToFile:
    public boolean saveDataToFile(){
        File f = new File(URL_CATEGORY);
        try {
            OutputStreamWriter writter = new OutputStreamWriter(new FileOutputStream(f));
            for (Category ca : categoryList) {
                writter.write(ca.toString());
                writter.write("\n");
            }
            writter.flush();//save xong nhớ dừng
            return true;
        } catch (Exception e) {
            System.err.println("File corruption!!" + e);
            return false;
        }
    }
}
