package alisa.aniskina.testtaskchat.service;

import alisa.aniskina.testtaskchat.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getProductList();
    public List<Product> getAvailableProduct(List<Product> productList);

    public String changeAvailableProduct(int id, boolean available);

}
