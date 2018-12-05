package cl.telematica.basicconnectionexample.main.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

import cl.telematica.basicconnectionexample.R;
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
    private Context context;
    ProgressDialog pDialog;

    public MainPresenterImpl(MainView mainView,Context con) {
        this.mainView = mainView;
        this.context = con;
    }

    @Override
    public void fetchData(final String url, final int timeOut) {
        MyAsyncTask task = new MyAsyncTask(url, timeOut, mainView);
        task.execute();
    }

    public void fetchDataWithRetrofit(){ //Metodo agregado para no borrar lo que estaba hecho
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Actualizando lista...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Libro>> call = service.getAllLibros();
        call.enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {
                pDialog.dismiss();
                mainView.populateRecyclerView(response.body());
            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                pDialog.dismiss();
                Toast.makeText(context,"Ocurrió un error, intentelo mas tarde",Toast.LENGTH_LONG).show();
            }
        });
    }
}
