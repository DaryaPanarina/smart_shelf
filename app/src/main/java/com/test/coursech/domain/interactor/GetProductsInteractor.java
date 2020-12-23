package com.test.coursech.domain.interactor;

import com.test.coursech.R;
import com.test.coursech.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GetProductsInteractor {
    public List<Product> getProducts() {
        ArrayList products = new ArrayList();
        products.add(new Product(0, "Яблоко", R.drawable.product_item_1));
        products.add(new Product(1,"Чай", R.drawable.product_item_2));
        products.add(new Product(2,"Молоко", R.drawable.product_item_3));
        products.add(new Product(3,"Хлеб", R.drawable.product_item_4));
        products.add(new Product(4,"Пиво", R.drawable.product_item_5));
        products.add(new Product(5,"Яйца", R.drawable.product_item_6));
        products.add(new Product(6,"Печенье", R.drawable.product_item_7));
        products.add(new Product(7,"Горошек", R.drawable.product_item_8));
        products.add(new Product(8,"Туалетная бумага", R.drawable.product_item_9));
        products.add(new Product(9,"Жвачка", R.drawable.product_item_10));
        products.add(new Product(10,"Йогурт", R.drawable.product_item_11));
        products.add(new Product(11,"Торт", R.drawable.product_item_12));
        return products;
    }
}
