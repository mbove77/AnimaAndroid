package com.bove.martin.anima.activities;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MenuItem;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.SnapHelper;

import com.bove.martin.anima.R;
import com.bove.martin.anima.adapter.SliderAdapter;
import com.bove.martin.anima.model.Slider;
import com.bove.martin.anima.services.ApiService;
import com.bove.martin.anima.utils.AutoScrollTask;
import com.bove.martin.anima.utils.SpeedyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Martín Bove on 07/12/2019.
 * E-mail: mbove77@gmail.com
 */
public class MainActivity extends AppCompatActivity implements Callback<List<Slider>>, SliderAdapter.OnItemClickListener {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ApiService service;
    private List<Slider> sliders;
    private int displayHeight;
    private Slider dummySlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main);

        // menu item handler
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeMenu :
                        Toast.makeText(MainActivity.this, "Home Click", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.tendenciaMenu :
                        Toast.makeText(MainActivity.this, "Tendencia click", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.blogMenu :
                        Toast.makeText(MainActivity.this, "Blog click", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.refreshMenu :
                        refershItems();
                        return true;
                    default:
                        return true;
                }
            }
        });

        // Iniciamos los elementos de la ui
        recyclerView = findViewById(R.id.recyclerViewNews);
        displayHeight = getDisplayHeight();

        // Iniciamos Retrofit para hacer el request
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.martinbove.a2hosted.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Hacemos la llamada
        service = retrofit.create(ApiService.class);
        Call<List<Slider>> callSlider = service.getAllSliders();
        callSlider.enqueue(this);

        // Iniciamos el recyclerView
        layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(this, SpeedyLinearLayoutManager.HORIZONTAL, false));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        // Snap Helper
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // Cargamos un primer dumySlider
        sliders = new ArrayList<Slider>();
        dummySlider = new Slider(10001);
        sliders.add(dummySlider);

        adapter = new SliderAdapter(sliders, R.layout.slider_item, this, this);
        recyclerView.setAdapter(adapter);
    }

    private void refershItems() {
        //sliders.clear();
        adapter.notifyDataSetChanged();
        Call<List<Slider>> callSlider = service.getAllSliders();
        callSlider.enqueue(this);
        Toast.makeText(MainActivity.this, "Refrescando Información!", Toast.LENGTH_LONG).show();
    }

    public int getToolBarHeight() {
        int[] attrs = new int[] {R.attr.actionBarSize};
        TypedArray ta = obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }

    public int getDisplayHeight() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        int dpHeight = Math.round(outMetrics.heightPixels);
        return dpHeight;
    }

    // Item click
    @Override
    public void onItemClick(Slider slider, int posicion) {
        Toast.makeText(MainActivity.this, "slider click", Toast.LENGTH_SHORT).show();
    }

    // Recibimos los datos de Retrofit ya parseados
    @Override
    public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
       sliders.clear();
       sliders.addAll(response.body());
       adapter.notifyDataSetChanged();
       setAutoRation();
    }

    private void setAutoRation() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new AutoScrollTask(sliders, recyclerView), 2000, 5000);
    }

    @Override
    public void onFailure(Call<List<Slider>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Fallo la conexión", Toast.LENGTH_LONG).show();
    }

}

