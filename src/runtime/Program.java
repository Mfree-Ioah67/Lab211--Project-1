
package runtime;

import data.BrandList;
import data.CategoryList;
import data.ProductManagement;
import ui.Menu;
import utils.Inputter;

public class Program {
    public static void main(String[] args) {
        //file data:
        String URL_PRODUCT = "D:\\FPTU\\LAB211\\Lab1\\01_Product.txt";
        
        //create menu:        
        BrandList brandList = new BrandList();
        brandList.loadDataFromFile();
        CategoryList categoryList = new CategoryList();
        categoryList.loadDataFromFile();
        ProductManagement pm = new ProductManagement(brandList, categoryList);
        pm.loadDataFromFile(URL_PRODUCT);
        //
        Menu menu = new Menu("| Bike Stores Management System |");
        menu.addNewOption("Add a product.");
        menu.addNewOption("Search product by product name.");
        menu.addNewOption("Update product.");
        menu.addNewOption("Delete product.");
        menu.addNewOption("Save products to file.");
        menu.addNewOption("Print list products from the file.");
        menu.addNewOption("Others - Quit.");
      
        //Use:
        boolean check = true;
        while(check){
            menu.showMenuToUsers();
            System.out.println("");
            int choice = menu.getChoicesFromUser();
            switch(choice){
                case 1:{
                    System.out.println("----------Add new product----------");
                    pm.loadDataFromFile(URL_PRODUCT);
                    pm.createAProduct();
                    pm.saveDataToFile(URL_PRODUCT);
                    check = menu.askToGoBackToTheMainMenu(check);
                    System.out.println("");
                    break;
                }
                case 2:{
                    System.out.println("----------Search Product information by name----------");
                    pm.loadDataFromFile(URL_PRODUCT);
                    pm.searchProductInformationByName();
                    check = menu.askToGoBackToTheMainMenu(check);
                    System.out.println("");
                    break;
                }
                case 3:{
                    System.out.println("----------Update Product information----------");
                    pm.loadDataFromFile(URL_PRODUCT);
                    pm.updateProductInformation();
                    pm.saveDataToFile(URL_PRODUCT);
                    check = menu.askToGoBackToTheMainMenu(check);
                    System.out.println("");
                    break;
                }
                case 4:{
                    System.out.println("----------Update Product information----------");
                    pm.loadDataFromFile(URL_PRODUCT);
                    pm.deleteProductInformation();
                    pm.saveDataToFile(URL_PRODUCT);
                    check = menu.askToGoBackToTheMainMenu(check);
                    System.out.println("");
                    break;
                }
                case 5:{
                    System.out.println("----------Save to file----------");
                    pm.saveDataToFile(URL_PRODUCT);
                    System.out.println("----Saving file is successfully!!!----");
                    System.out.println("");
                    check = menu.askToGoBackToTheMainMenu(check);
                    System.out.println("");
                    break;
                }
                case 6:{
                    System.out.println("----------Print all lists from file----------");
                    pm.loadDataFromFile(URL_PRODUCT);
                    pm.printAllListsFromFile();
                    check = menu.askToGoBackToTheMainMenu(check);
                    System.out.println("");
                    break;
                }
                case 7:{
                    check = false;
                    System.out.println("");
                    break;
                }
            }
        }
        System.out.println("See you later! ^_^");
        pm.saveDataToFile(URL_PRODUCT);  
    }
}
