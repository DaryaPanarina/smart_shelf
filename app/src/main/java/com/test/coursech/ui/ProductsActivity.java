package com.test.coursech.ui;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.test.coursech.R;
import com.test.coursech.domain.interactor.GetCompanyInteractor;
import com.test.coursech.domain.interactor.GetProductsInteractor;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView list;
    private TextView companyName;
    private GetCompanyInteractor getCompanyInteractor = new GetCompanyInteractor();
    private GetProductsInteractor getProductsInteractor = new GetProductsInteractor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_screen);
        list = findViewById(R.id.productList);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ProductAdapter(this, getProductsInteractor.getProducts()));
        companyName = findViewById(R.id.companyName);
        companyName.setText(getCompanyInteractor.getCompanyName());
    }

}
