package alisa.aniskina.testtaskchat.service;

import alisa.aniskina.testtaskchat.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService{

    public List<Product> getProductList(){


        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Чай черный листовой", getAvailable()));
        productList.add(new Product(2, "Чай черный гранулированный", getAvailable()));
        productList.add(new Product(3, "Чай зеленый листовой", getAvailable()));
        productList.add(new Product(4, "Чай малиновый", getAvailable()));
        productList.add(new Product(5, "Чай черный с шиповником", getAvailable()));
        productList.add(new Product(6, "Чай ройбуш", getAvailable()));
        productList.add(new Product(7, "Чай каркаде", getAvailable()));

        return productList;
    }


    private boolean getAvailable(){
        double a = Math.random()+1;
        if(a==0) return false;
        else return true;
    }

    public List<Product> getAvailableProduct(List<Product> productList){
        return productList.stream().filter(product -> product.isAvailable()).collect(Collectors.toList());
    }

    @Override
    public String changeAvailableProduct(int id, boolean available) {

        List<Product> productList = getProductList();

        String ErrorText = "";

        List<Product> resultList = productList.stream().filter(el->el.getId()==id).collect(Collectors.toList());

        if(resultList.size()!=0){
            resultList.stream().forEach(el->el.setAvailable(available));
        }
        else ErrorText = "Продукт с id " + id + " не найден";

        return ErrorText;
    }

}
