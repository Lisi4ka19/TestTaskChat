package alisa.aniskina.testtaskchat.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Product {
    private int id;
    private String name;
    private boolean available;




    public Product(int id, String name, boolean available) {
        this.id = id;
        this.name = name;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
