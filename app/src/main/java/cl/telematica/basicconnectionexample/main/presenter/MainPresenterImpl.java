package cl.telematica.basicconnectionexample.main.presenter;

import android.widget.Toast;

import java.util.List;

import cl.telematica.basicconnectionexample.main.GetDataService;
import cl.telematica.basicconnectionexample.main.MyAsyncTask;
import cl.telematica.basicconnectionexample.main.RetrofitClientInstance;
import cl.telematica.basicconnectionexample.main.view.MainView;
import cl.telematica.basicconnectionexample.models.Libro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void fetchData(final String url, final int timeOut) {
        MyAsyncTask task = new MyAsyncTask(url, timeOut, mainView);
        task.execute();

    }
    public void fetchDataWithRetrofit(){ //Metodo agregado para no borrar lo que estaba hecho
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Libro>> call = service.getAllLibros();
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                mainView.populateRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
            }
        });
    }
}
