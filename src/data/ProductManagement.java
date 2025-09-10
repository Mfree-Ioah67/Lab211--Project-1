

package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import utils.Inputter;

public class ProductManagement{
    //props:
    private final static String URL_PRODUCTID = "D:\\FPTU\\LAB211\\Lab1\\01_ProductID.txt";
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<String> productIdList = new ArrayList<>();
    //
    BrandList brandList;
    CategoryList categoryList;
    
    //constructor:
    public ProductManagement(BrandList brandList, CategoryList categoryList) {
        this.brandList = brandList;
        this.categoryList = categoryList;
    }
    
    //methods:
    //+loadDataFromFile
    public boolean loadDataFromFile(String url) {
        productList.clear();
        File f = new File(url);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            if(line == null) return true;
            while(line != null){
                StringTokenizer st = new StringTokenizer(line, ",");
                String id = st.nextToken().trim();
                String name = st.nextToken().trim();
                String brandId = st.nextToken().trim();
                String categoryId = st.nextToken().trim();
                int modelYear = Integer.parseInt(st.nextToken().trim());
                int listPrice = Integer.parseInt(st.nextToken().trim());
                Product newProduct = new Product(id, name, brandId, categoryId,
                        modelYear, listPrice);
                productList.add(newProduct);
                line = reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Your file is corrupted : " + e);
            return false;
        }
    }
    //+loadIdHasUsedFromFile
    public boolean loadIdHasUsedFromFile() {
        productIdList.clear();
        File f = new File(URL_PRODUCTID);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(f));
            String line = reader.readLine();
            if(line == null) return true;
            while(line != null){
                productIdList.add(line.trim());
                line = reader.readLine();
            }
            return true;
        } catch (Exception e) {
            System.err.println("Your file is corrupted: " + e);
            return false;
        }
    }
    //+printDataInTable
    public void printDataInTable(ArrayList<Product> productListPrint){
        System.out.printf("%-6s|%-25s|%-25s|%-25s|%-15s|%-15s|%n", 
                  "ID", "NAME", "BRAND name", "CATEGORY Name", 
                  "MODEL YEAR", "LIST PRICE");
        System.out.println
            ("----------------------------------------------------------------"
                    + "-----------------------------------------------");
        for (Product product : productListPrint) {
            brandList.loadDataFromFile();
            String brandName = 
                brandList.searchBrandById(product.getBrandId()).getName();
            categoryList.loadDataFromFile();
            String categoryName = 
                categoryList.searchCategoryById(product.getCategoryId()).getName();
            System.out.printf("%-6s|%-25s|%-25s|%-25s|%-15d|%-15d|%n",
                    product.getId(), product.getName(), 
                    brandName, categoryName,
                    product.getModelYear(), product.getListPrice());
            System.out.println
            ("--------------------------------------------------------------"
                    + "-------------------------------------------------");
        }
        System.out.println("");
    }
    //+printAllListsFromFile
    public void printAllListsFromFile(){
        if(productList.isEmpty()){
            System.out.println("---Nothing to print!!!---");
            return;
        }
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                if(p1.getListPrice() == p2.getListPrice()){
                    return p1.getName().compareTo(p2.getName());
                }
                return p1.getListPrice() > p2.getListPrice() ? -1 : 1;
            }
        }); 
        printDataInTable(productList);
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getId().compareTo(p2.getId()) == 1 ? -1 : 1;
            }
        }); 
    }
    //+ searchIndexById
    public int searchIndexOfProductById(String keyId){
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getId().equals(keyId)) return i;
        }
        return -1;
    }
    //+searchProductById
    public Product searchProductById(String keyId){
        int pos = searchIndexOfProductById(keyId);
        return pos == -1 ? null : productList.get(pos);
    }    
    //+ SearchProductInformationByName:
    public void searchProductInformationByName(){
        String inputName = Inputter.getString("- Input a part of product name: ", 
                    "That field is required!!!").toLowerCase().trim();
        ArrayList<Product> suitableProducts = new ArrayList<>();
        for (Product product : productList) {
            if(product.getName().toLowerCase().contains(inputName)){
                suitableProducts.add(product);
            }
        }
        if(suitableProducts.isEmpty()){
            System.out.println("Have no any Product");
            return;
        }
        Collections.sort(suitableProducts, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return p1.getModelYear() > p2.getModelYear() ? 1 : -1;
            }
        }); 
        printDataInTable(suitableProducts);
    }
    //+UpdateProductInformation:
    public void updateProductInformation(){
        //input key
        String keyId = Inputter.getString
        ("- Input the Id of the product you want to update[ProXXX]: ",
            "That field is required!!!").trim();
        keyId = Character.toUpperCase(keyId.charAt(0)) 
                + keyId.substring(1, keyId.length());
        //find:
        Product pro = searchProductById(keyId);
        if(pro == null){
            System.out.println("---Product does not exist!!!---");
            return;
        }
        //if finded:
        System.out.println("- The Product information before updating:");
        System.out.println(pro.toString());
        System.out.println("- Are you sure you want to update this product "
                + "information ?");
        String choose = Inputter.getString("Yes/No[Y/N]: ",
                "That field is required!!!", "[YyNn]");
        if (!choose.matches("[YyNn]")) {
            System.out.println("---Updating is failure!---");
            return;
        }
        //Update:
        System.out.println("----Updating...-----");
        System.out.printf("- Input the new name of product: ");
        String newName = Inputter.sc.nextLine().trim();
        if (!(newName.isEmpty())) {
            pro.setName(newName);
        }
        //Brand id:
        brandList.loadDataFromFile();
        brandList.printbrandList();
        while (true) {
            String newBrandId = Inputter.getString("- Input the new brandId: ", 
                            "The Brand ID must be fill on fotmat: BXXX!!!", 
                            "^\\s*([Bb]\\d{3})?\\s*$").trim();
            if (newBrandId.isEmpty()) break;
            //
            newBrandId = Character.toUpperCase(newBrandId.charAt(0)) 
                + newBrandId.substring(1, newBrandId.length());
            int pos = brandList.searchIndexOfBrandById(newBrandId);
            if (pos == -1) {
                System.out.println("- Your brandId can not be finded in "
                        + "the BrandList, so do you wanna create new Brand ?");
                System.out.println("1.Yes: Y");
                System.out.println("2.No: N");
                String get = Inputter.getString("- Input your choice: ",
                        "That field must be Y or N!!!", "[YyNn]").toUpperCase();
                if (get.equals("Y")) {
                    brandList.createNewBrand(newBrandId);
                    brandList.saveDataToFile();
                    System.out.println("----Create new brand is successfully!----\n");
                    pro.setBrandId(newBrandId);
                    break;
                }
                continue;
            }
            pro.setBrandId(newBrandId);
            break;
        }
        //Category id:
        categoryList.loadDataFromFile();
        categoryList.printCategoryList();
        while (true) {
            String newCategoryId = Inputter.getString("- Input the new categoryId: ", 
                        "The Category ID must be fill on fotmat: CXXX", 
                        "^\\s*([Cc]\\d{3})?\\s*$").trim();
            if (newCategoryId.isEmpty()) break;
            newCategoryId = Character.toUpperCase(newCategoryId.charAt(0)) 
                + newCategoryId.substring(1, newCategoryId.length());
            int pos = categoryList.searchIndexOfCategoryById(newCategoryId);
            if (pos == -1) {
                System.out.println("- Your categoryId can not be "
                        + "finded in the CategoryList, so do you wanna "
                        + "create new Brand ?");
                System.out.println("1.Yes: Y");
                System.out.println("2.No: N");
                String get = Inputter.getString("- Input your choice: ",
                        "That field must be Y or N!!!", "[YyNn]").toUpperCase();
                if (get.equals("Y")) {
                    categoryList.createNewCategory(newCategoryId);
                    categoryList.saveDataToFile();
                    System.out.println("----Create new category is successfully!----\n");
                    pro.setCategoryId(newCategoryId);
                    break;
                }
                continue;
            }
            pro.setCategoryId(newCategoryId);
            break;
        }
        //Model year:
        while (true) {
            System.out.printf("- Input the new model year: ");
            String newModelYear = Inputter.sc.nextLine().trim();
            if (newModelYear.isEmpty()) break;
            try {
                int ModelYear = Integer.parseInt(newModelYear);
                if (ModelYear > Year.now().getValue() || ModelYear < 1900) {
                    throw new Exception();
                }
                pro.setModelYear(ModelYear);
                break;
            } catch (Exception e) {
                System.err.println("The Model Year must be a valid year!!!!");
                continue;
            }
        }
        //List price:
        while (true) {
            System.out.printf("- Input the new list price: ");
            String newListPrice = Inputter.sc.nextLine().trim();
            if (newListPrice.isEmpty()) break;
            try {
                int listPrice = Integer.parseInt(newListPrice);
                if (listPrice <= 0) throw new Exception();
                pro.setListPrice(listPrice);
                break;
            } catch (Exception e) {
                System.err.println("The List Price must be a positive number!!!!");
                continue;
            }
        }
        System.out.println("----Updating is successfully!!!!----");
        System.out.println("");
    }
    //+deleteProductInformation:
    public void deleteProductInformation(){
        String keyId = Inputter.getString
        ("- Input the Id of the product you want to remove[ProXXX]: ",
            "That field is required!!!").trim();
        keyId = Character.toUpperCase(keyId.charAt(0)) 
                + keyId.substring(1, keyId.length());
        Product pro = searchProductById(keyId);
        if(pro == null){
            System.out.println("---Product does not exist!---");
            return;
        }
        System.out.println("- The Bike information before removing:");
        System.out.println(pro.toString());
        //
        System.out.println("- Are you sure you want to remove this product ?");
        String choose = Inputter.getString("Yes/No[Y/N]: ",
                "That field is required!!!", "[YyNn]");
        if (!choose.matches("[YyNn]")) {
            System.out.println("Removing is failure!!!");
            return;
        }
        //
        System.out.println("Removing......");
        productList.remove(pro);
        System.out.println("Removing is successful!!!");
        System.out.println("");
    }
    //+createAProduct:
    public void createAProduct(){
        //id
        loadIdHasUsedFromFile();
        String newId;
        if(productIdList.isEmpty()){
            newId="Pro001";
        }else{
            int numId = productIdList.size() + 1;
            newId = String.format("Pro%03d", numId);
        }
        // name:
        String newName = Inputter.getString("- Input name of product: ", 
            "That fielđ is required!!!").trim();
        //Brand id:
        String newBrandId;
        brandList.loadDataFromFile();
        brandList.printbrandList();
        while(true){
            newBrandId = Inputter.getString("- Input BrandId of product[BXXX]: ", 
                "Fill in the format[BXXX]!!!", 
                "^\\s*[Bb]\\d{3}\\s*$").trim();
            newBrandId = Character.toUpperCase(newBrandId.charAt(0)) 
                + newBrandId.substring(1, newBrandId.length());
            int pos = brandList.searchIndexOfBrandById(newBrandId);
            if(pos == -1) {
                System.out.println("- Your brandId can not be finded in the "
                        + "BrandList, so do you wanna create new Brand ?");
                System.out.println("1.Yes: Y");
                System.out.println("2.No: N");
                String get = Inputter.getString("- Input your choice: ", 
                            "That field must be Y or N!!!", "[YyNn]").toUpperCase();
                if(get.equals("Y")){
                    brandList.createNewBrand(newBrandId);
                    brandList.saveDataToFile();
                    System.out.println("----Create new brand is successfully!----\n");
                    break;
                }
                continue;
            }
            break;
        }
        //Category id:
        categoryList.loadDataFromFile();
        categoryList.printCategoryList();
        String newCategoryId;
        while(true){
            newCategoryId = Inputter.getString("- Input CategoryId of product[CXXX]: ", 
                "Fill in the format[CXXX]!!!", 
                "^\\s*[Cc]\\d{3}\\s*$").trim();
            newCategoryId = Character.toUpperCase(newCategoryId.charAt(0)) 
                + newCategoryId.substring(1, newCategoryId.length());
            int pos = categoryList.searchIndexOfCategoryById(newCategoryId);
            if(pos == -1) {
                System.out.println("- Your categoryId can not be finded in the "
                        + "CategoryList, so do you wanna create new Category ?");
                System.out.println("1.Yes: Y");
                System.out.println("2.No: N");
                String get = Inputter.getString("- Input your choice: ", 
                            "That field must be Y or N!!!", "[YyNn]").toUpperCase();
                if(get.equals("Y")){
                    categoryList.createNewCategory(newCategoryId);
                    categoryList.saveDataToFile();
                    System.out.println("----Create new category is "
                            + "successfully!----\n");
                    break;
                }
                continue;
            }
            break;
        }
        //Model year:
        int newModelYear = Inputter.getAnInteger("- Input model year of product: ", 
                "The model year of product must be a valid year!!!", 
                1900, Year.now().getValue());
        //List price:
        int newListPrice = Inputter.getAnInteger("- Input list price of product: ", 
                    "The list price of product must be a positive number!!!",
                    1, Integer.MAX_VALUE);
        //lưu:
        Product newPr = new Product(newId, newName, newBrandId, 
                newCategoryId, newModelYear, newListPrice);
        productList.add(newPr);
        productIdList.add(newId);
        saveIdHasUsedToFile();
        System.out.println("----Create new product is successfully!----");
        System.out.println(newPr + "\n");          
    }
    //+saveDataToFile:
    public boolean saveDataToFile(String url){
        File f = new File(url);
        try {
            OutputStreamWriter writter = new OutputStreamWriter(new FileOutputStream(f));
            for (Product pro : productList) {
                writter.write(pro.toString());
                writter.write("\n");
            }
            writter.flush();//save xong nhớ dừng
            return true;
        } catch (Exception e) {
            System.err.println("Your file is corrupted: " + e);
            return false;
        }
    }
    //+saveIdHasUsedToFile:
    public boolean saveIdHasUsedToFile(){
        File f = new File(URL_PRODUCTID);
        try {
            OutputStreamWriter writter = new OutputStreamWriter(new FileOutputStream(f));
            for (String str : productIdList) {
                writter.write(str.trim());
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
