
package utils;
import java.util.Scanner;
/*
Inputter là 1 cái khuôn những ko dùng để đúc ra object
    mà dùng class này để lưu nhưng hàm hỗ trợ cho việc nhập
Vì là hàm nên static method
*/

public class Inputter {
    //props:tạo 1 cái scanner dùng chung cho các hàm
    public static Scanner sc = new Scanner(System.in);
    
    //những method hỗ trợ cho việc nhập:
        //+method ép nhười dùng nhập số nguyên chuẩn:
    public static int getAnInteger(String inpMsg, String errMsg){
        while(true){
            //hiển thị câu mời nhập
            System.out.printf(inpMsg);
            try {
                int number =  Integer.parseInt(sc.nextLine());
                return number;//nếu như ở trên ổn thỏa thì ném con số ra ngoài
            } catch (Exception e) {
                System.err.println(errMsg);
            };
        }
    }  
        //+method nhập số nguyên chuẩn nhưng phải ở trong khoản yêu cầu:
    public static int getAnInteger(String inpMsg, String errMsg,
                                    int lowerBound, int upperBound){
        //trường hợp nếu users nhập ngược
        if(lowerBound > upperBound){
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while(true){
            System.out.printf(inpMsg);//hiển thị câu mời nhập
            try {
                int number =  Integer.parseInt(sc.nextLine());
                if(number < lowerBound || number > upperBound){
                    throw new Exception();
                }
                return number;//nếu như ở trên ổn thỏa thì ném con số ra ngoài
            } catch (Exception e) {
                System.err.println(errMsg);
            };
        }
    }  
        //+nhập chuỗi ko đc để trống:
    public static String getString(String inpMsg, String errMsg){
        while(true){
            System.out.printf(inpMsg);
            try {
                String str = sc.nextLine();//này nhập chuỗi và chuỗi là ko có
                                        //giới hạn: bỏ trống .. đều đc
                                        //=> ko bao giwox có lỗi
                if(str.isEmpty()){
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
        //+nhập chuỗi ko đc để trống và phải đúng regex:
    public static String getString(String inpMsg, String errMsg, String regex){
        while(true){
            System.out.printf(inpMsg);
            try {
                String str = sc.nextLine();//này nhập chuỗi và chuỗi là ko có
                                        //giới hạn: bỏ trống .. đều đc
                                        //=> ko bao giờ có lỗi
                if(!str.matches(regex)){
                    throw new Exception();
                }
                return str;
            } catch (Exception e) {
                System.err.println(errMsg);
            }
        }
    }
}
