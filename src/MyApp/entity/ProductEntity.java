package MyApp.entity;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import java.util.Date;

@Data
@AllArgsConstructor
public class ProductEntity {
    private int id;
    private String title;
    private String productType;
    private String desc;
    private String image;
    private String cost;
    private String registerDate;


    public ProductEntity(String title, String productType,String desc, String image, String cost, String registerDate) {
        this(-1,title,productType,desc,image,cost,registerDate);
    }
}
