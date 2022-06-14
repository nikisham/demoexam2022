package MyApp.ui;

import MyApp.entity.ProductEntity;
import MyApp.manager.ProductEntityManager;
import MyApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class ProductCreateForm extends BaseForm {
    private JPanel mainPanel;
    private JTextField TitleField;
    private JTextField ProductTypeField;
    private JTextField ImageField;
    private JTextField CostField;
    private JTextField DescField;
    private JTextField RegDateField;
    private JButton BackButton;
    private JButton AddButton;

    public ProductCreateForm(){
        super(400,400);
        setContentPane(mainPanel);

        initBoxes();
        initButtons();

        setVisible(true);
    }
    private void initBoxes(){

    }

    private void initButtons(){
        AddButton.addActionListener(e -> {
            String title = TitleField.getText();
            if(title.isEmpty() || title.length()> 100){
                JOptionPane.showMessageDialog(this,"Название не введено или слишком длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
            return;
            }
            String productType = ProductTypeField.getText();
            if(productType.isEmpty() || productType.length()> 100){
                JOptionPane.showMessageDialog(this,"Тип продукта не введен или слишком длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String image = ImageField.getText();
            if(image.isEmpty() || image.length()> 100){
                JOptionPane.showMessageDialog(this,"Адрес картинки не введен или слишком длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String cost = CostField.getText();
            if(cost.isEmpty() || cost.length()> 100){
                JOptionPane.showMessageDialog(this,"Цена не введена или слишком длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String desc = DescField.getText();
            if(desc.isEmpty() || desc.length()> 100){
                JOptionPane.showMessageDialog(this,"Описание не введено или слишком длинное","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String regDate = RegDateField.getText();
            if(regDate.isEmpty() || regDate.length()> 100){
                JOptionPane.showMessageDialog(this,"Дата регистрации не введена или слишком длинная","Ошибка",JOptionPane.ERROR_MESSAGE);
                return;
            }
            ProductEntity product = new ProductEntity(
              title,productType,image,cost,desc,regDate
            );

            try{
                ProductEntityManager.insert(product);
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(this,"Продукт успешно записан");
            dispose();
            new ProductTableForm();
        });
        BackButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });


    }
}
