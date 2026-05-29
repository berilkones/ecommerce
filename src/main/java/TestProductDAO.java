import com.ecommerce.dao.ProductDAO;
import com.ecommerce.model.Product;

import java.util.List;

public class TestProductDAO {
    public static void main(String[] args){
        ProductDAO productDAO = new ProductDAO();

        List <Product> productList = productDAO.getAllProducts();

        for (Product product: productList){
            System.out.println("ID: " + product.getId());
            System.out.println("Ürün Adı: " + product.getName());
            System.out.println("Fiyat: " + product.getPrice());
            System.out.println("Stok: " + product.getStock());
            System.out.println("------------------");

        }

    }
}