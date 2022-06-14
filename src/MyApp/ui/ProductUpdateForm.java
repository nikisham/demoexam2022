package MyApp.ui;

import MyApp.entity.ProductEntity;
import MyApp.manager.ProductEntityManager;
import MyApp.util.BaseForm;

import javax.swing.*;
import java.sql.SQLException;

public class ProductUpdateForm extends BaseForm {
    private JTextField IDField;
    private JTextField ProductTypeField;
    private JTextField ImageField;
    private JTextField CostField;
    private JTextField DescField;
    private JTextField RegDateField;
    private JTextField TitleField;
    private JButton BackButton;
    private JButton UpdateButton;
    private JPanel mainPanel;
    private JButton DeleteButton;

    private ProductEntity product;

    public ProductUpdateForm(ProductEntity product){
        super(400,400);
        this.product = product;
        setContentPane(mainPanel);
        initBoxes();
        initButtons();
        initFields();

        setVisible(true);

    }

    private void initBoxes(){

    }
    private void initFields(){
        IDField.setEditable(false);
        IDField.setText(String.valueOf(product.getId()));
        TitleField.setText(product.getTitle());
        ProductTypeField.setText(product.getProductType());
        DescField.setText(product.getDesc());
        ImageField.setText(product.getImage());
        CostField.setText(product.getCost());
        RegDateField.setText(product.getRegisterDate());
    }
    private void initButtons(){
        UpdateButton.addActionListener(e -> {
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

            product.setTitle(title);
            product.setProductType(productType);
            product.setDesc(desc);
            product.setImage(image);
            product.setCost(cost);
            product.setRegisterDate(regDate);

            try{
                ProductEntityManager.update(product);
            }catch (SQLException ex){
                ex.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(this,"Продукт успешно перезаписан");
            dispose();
            new ProductTableForm();
        });
        BackButton.addActionListener(e -> {
            dispose();
            new ProductTableForm();
        });
        DeleteButton.addActionListener(e -> {
            if(JOptionPane.showConfirmDialog(this,"Вы точно хотите удалить данную запись?","Подтверждение",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
                try{
                    ProductEntityManager.delete(product);
                    JOptionPane.showMessageDialog(this, "Клиент успешно удален");
                    dispose();
                    new ProductTableForm();
                }catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Ошибка удаления данных: " + ex.getMessage());
                }
            }
        });
    }
}
