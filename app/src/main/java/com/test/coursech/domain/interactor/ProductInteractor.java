package com.test.coursech.domain.interactor;

import com.test.coursech.R;
import com.test.coursech.domain.entity.ProductDetail;

public class ProductInteractor {
    public ProductDetail getProductDetail(int id) {
        if (id == 3) {
            return new ProductDetail(
                    "Хлеб",
                    R.drawable.product_item_4,
                    "27 руб.",
                    234,
                    4.1d,
                    "мужчины",
                    new int[]{1, 2, 3, 3, 7, 10, 8, 6, 3, 1},
                    new int[]{1, 2, 6, 5, 4}
            );
        } else if (id == 7) {
            return new ProductDetail(
                    "Горошек",
                    R.drawable.product_item_8,
                    "35 руб.",
                    42,
                    4.4d,
                    "женщины",
                    new int[]{1, 2, 8, 13, 12, 10, 8, 6, 3, 1},
                    new int[]{1, 2, 6, 7, 9}
            );
        }
        return null;
    }
}
