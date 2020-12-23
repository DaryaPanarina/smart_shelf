package com.test.coursech.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.test.coursech.R;
import com.test.coursech.domain.entity.ProductDetail;
import com.test.coursech.domain.interactor.ProductInteractor;

public class DetailActivity extends AppCompatActivity {

    private ProductDetail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen);
        int id = getIntent().getIntExtra("id", -1);
        detail = new ProductInteractor().getProductDetail(id);

        ((ImageView)findViewById(R.id.preview)).setImageResource(detail.getPreview());
        ((TextView)findViewById(R.id.name)).setText(detail.getName());
        ((TextView)findViewById(R.id.price)).setText(detail.getPrice());

        ((TextView)findViewById(R.id.countLastUser)).setText("Количество покупателей за последние сутки - " + detail.getCountLastUser());
        ((TextView)findViewById(R.id.appraisal)).setText("Средняя оценка продукта - " + detail.getAppraisal());
        ((TextView)findViewById(R.id.mainGender)).setText("Основные покупатели - " + detail.getMainGender());

        DataPoint[] data = new DataPoint[detail.getActivityByH().length];
        for (int i = 1; i <= detail.getActivityByH().length; i++) {
            data[i - 1] = new DataPoint(10 + i * 3, detail.getActivityByH()[i - 1]);
        }
        GraphView graph = findViewById(R.id.activity);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(data);
        graph.addSeries(series);
        graph.setTitle("Интенсивность покупательской способности");

        GraphView graph2 = findViewById(R.id.appraisalAll);
        BarGraphSeries<DataPoint> series2 = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(1, detail.getAppraisalAll()[0]),
                new DataPoint(2, detail.getAppraisalAll()[1]),
                new DataPoint(3, detail.getAppraisalAll()[2]),
                new DataPoint(4, detail.getAppraisalAll()[3]),
                new DataPoint(5, detail.getAppraisalAll()[4])

        });
        graph2.setTitle("Распределение эмоцеональной отметки");
        graph2.addSeries(series2);
    }

}
