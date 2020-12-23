package com.test.coursech.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.test.coursech.R;
import com.test.coursech.domain.entity.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Product> products;

    ProductAdapter(Context context, List<Product> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.id = product.getId();
        holder.previewView.setImageResource(product.getPreview());
        holder.nameView.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView previewView;
        final TextView nameView;
        int id = 0;
        ViewHolder(View view){
            super(view);
            previewView = view.findViewById(R.id.preview_product);
            nameView = view.findViewById(R.id.name_product);
            view.findViewById(R.id.product).setOnClickListener(v -> {
                Intent intent = new Intent(inflater.getContext(), DetailActivity.class);
                intent.putExtra("id", id);
                inflater.getContext().startActivity(intent);
            });
        }
    }

}
