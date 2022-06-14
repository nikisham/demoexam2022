package MyApp.manager;

import MyApp.App;
import MyApp.entity.ProductEntity;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductEntityManager {
    public static void insert(ProductEntity product) throws SQLException
    {
        try(Connection c = App.getConnection()){
            String sql = "INSERT INTO product(Title, ProductType, Description, Image, Cost, RegisterDate) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getDesc());
            ps.setString(4, product.getImage());
            ps.setString(5, product.getCost());
            ps.setString(6, product.getRegisterDate());

            ps.executeUpdate();

            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()){
                product.setId(resultSet.getInt(1));
                return;
            }

        }
    }
    public static void update(ProductEntity product) throws SQLException
    {
        try(Connection c = App.getConnection()){
            String sql = "UPDATE product SET Title=?, ProductType=?, Description=?, Image=?, Cost=?, RegisterDate=? WHERE ID=?";

            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, product.getTitle());
            ps.setString(2, product.getProductType());
            ps.setString(3, product.getDesc());
            ps.setString(4, product.getImage());
            ps.setString(5, product.getCost());
            ps.setString(6, product.getRegisterDate());
            ps.setInt(7,product.getId());

            ps.executeUpdate();



        }
    }
    public static List<ProductEntity> selectAll() throws SQLException
    {
        try(Connection c = App.getConnection()){
            String sql = "SELECT * FROM product";
            Statement s = c.createStatement();
            ResultSet resultSet = s.executeQuery(sql);

            List<ProductEntity> list = new ArrayList<>();
            while(resultSet.next()){
                list.add(new ProductEntity(
                        resultSet.getInt("ID"),
                        resultSet.getString("Title"),
                        resultSet.getString("ProductType"),
                        resultSet.getString("Description"),
                        resultSet.getString("Image"),
                        resultSet.getString("Cost"),
                        resultSet.getString("RegisterDate")

                ));
            }
            return list;
        }
    }
    public static void delete(int id) throws SQLException
    {
        try(Connection c = App.getConnection())
        {
            String sql = "DELETE FROM product WHERE ID=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
    }
    }
    public static void delete(ProductEntity product) throws SQLException
    {
        delete(product.getId());
    }
}
