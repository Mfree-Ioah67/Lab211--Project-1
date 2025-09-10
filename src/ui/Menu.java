
package ui;

import java.util.ArrayList;
import utils.Inputter;

public class Menu {
    //props:
    ArrayList<String> optionList = new ArrayList<>();
    private String titleOfMenu;
    
    //methods:
    public Menu(String titleOfMenu) {
        this.titleOfMenu = titleOfMenu;
    }
    public void addNewOption(String newOption){
        optionList.add(newOption);
    }
    
    //showMenu
    public void showMenuToUsers(){
        int number = 1;
        System.out.println("----------"+titleOfMenu+"----------");
        for (String option : optionList) {
            System.out.println(number + ". " + option);
            number++;
        }
    }
    
    //getChoice
    public int getChoicesFromUser(){
        int choice = Inputter.getAnInteger("- Input your choice: ", 
                "Your choice must be between 1 and "+optionList.size()+"!!!", 1, 
                optionList.size());
        return choice;
    }
    
    //Ask to go back to the main menu
    public boolean askToGoBackToTheMainMenu(boolean check){
        System.out.println("- Do you wanna go back to main menu ?");
        System.out.println("1.Yes: Y\n2.No: N");
        String get = Inputter.getString("- Input your choice: ", 
                    "That field must be Y or N!!!", "[YyNn]").toUpperCase().trim();
        return get.equals("Y");
    }
}
