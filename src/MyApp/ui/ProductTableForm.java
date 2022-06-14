package MyApp.ui;

import MyApp.App;
import MyApp.entity.ProductEntity;
import MyApp.manager.ProductEntityManager;
import MyApp.util.BaseForm;
import MyApp.util.CustomTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.PrimitiveIterator;

public class ProductTableForm extends BaseForm
{

    private JPanel mainPanel;
    private JTable table;
    private JButton AddButton;

    private CustomTableModel<ProductEntity> model;


    public ProductTableForm() {
        super(1200, 800);
        setContentPane(mainPanel);

        initTable();
        initButtons();

        setVisible(true);
    }
    public void initTable(){
        table.getTableHeader().setReorderingAllowed(false);
        try {
            model = new CustomTableModel<>(
                    ProductEntity.class,
                    new String[]{"","","","","","",""},
                    ProductEntityManager.selectAll()
            );
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(App.IS_ADMIN) {
            table.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        int row = table.rowAtPoint(e.getPoint());
                        if (row != -1) {
                            dispose();
                            new ProductUpdateForm(model.getRows().get(row));
                        }
                    }
                }
            });
        }
    }
    public void initButtons(){
        if(App.IS_ADMIN ) {
            AddButton.addActionListener(e -> {
                dispose();
                new ProductCreateForm();
            });
        }else AddButton.setEnabled(false);
    }
}

