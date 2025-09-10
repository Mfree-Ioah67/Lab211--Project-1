
package data;

import java.util.StringTokenizer;

public class Category {
    //props:
    private String id;
    private String name;
    
    //constructor:
    public Category(String Id, String name) {
        this.id = Id;
        setName(name);
    }
    
    //getter:
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    //setter:
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
        return String.format("%s, %s", id,name);
    }
}
