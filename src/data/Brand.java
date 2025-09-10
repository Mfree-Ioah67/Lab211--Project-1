
package data;

import java.util.StringTokenizer;

public class Brand {
    //props:
    private String id;
    private String name;
    private String country;
    
    //constructor:
    public Brand(String Id, String name, String country) {
        this.id = Id;
        setName(name);
        setCountry(country);
    }
    
    //getter:
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
    
    //setter:
    public void setCountry(String country) {
        String res = "";
        StringTokenizer st = new StringTokenizer(country.trim(), " ");
        while(st.hasMoreElements()){
            String str = st.nextToken().toLowerCase();;
            res = res + str.toUpperCase().charAt(0) + str.substring(1,str.length()) + " ";
        }
        this.country = res.trim();
    }
    public void setName(String name) {
        String res = "";
        StringTokenizer st = new StringTokenizer(name.trim(), " ");
        while(st.hasMoreElements()){
            String str = st.nextToken().toLowerCase();;
            res = res + str.toUpperCase().charAt(0) + str.substring(1,str.length()) + " ";
        }
        this.name = res.trim();
    }
    
    //tostring:
    @Override
    public String toString() {
        return String.format("%s, %s, %s", id,name,country);
    } 
}
