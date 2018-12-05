package cl.telematica.basicconnectionexample.main;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import cl.telematica.basicconnectionexample.R;
import cl.telematica.basicconnectionexample.main.presenter.MainPresenterImpl;
import cl.telematica.basicconnectionexample.models.Libro;
import cl.telematica.basicconnectionexample.main.view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainView {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerView);
        setTitle("Lista de Libros");
        setRecyclerViewParams();
        MainPresenterImpl presenter = new MainPresenterImpl(this,this);
        //presenter.fetchData("http://www.mocky.io/v2/5bfc6aa9310000780039be36", 15000);
        presenter.fetchDataWithRetrofit();
    }

    @Override
    public void setRecyclerViewParams() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public void populateRecyclerView(List<Libro> libros) {
        mAdapter = new UIAdapter(libros,this);
        mRecyclerView.setAdapter(mAdapter);
    }

}
