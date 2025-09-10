
package data;

import java.util.StringTokenizer;

public class Product {
    //props:
    private String id;
    private String name;
    private String brandId;
    private String categoryId;
    private int modelYear;
    private int listPrice;
    
    //constructor:
    public Product(String id, String name, String brandId, String categoryId, 
            int modelYear, int listPrice) {
        this.id = id;
        setName(name);
        this.brandId = brandId;
        this.categoryId = categoryId;
        this.modelYear = modelYear;
        this.listPrice = listPrice;
    }
    
    //getter:
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getModelYear() {
        return modelYear;
    }

    public int getListPrice() {
        return listPrice;
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

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setModelYear(int modelYear) {
        this.modelYear = modelYear;
    }

    public void setListPrice(int listPrice) {
        this.listPrice = listPrice;
    }
    
    //toString:
    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %d, %d", id, name, brandId,
                    categoryId, modelYear,listPrice);
    }
}
