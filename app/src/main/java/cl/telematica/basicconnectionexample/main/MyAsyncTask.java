package cl.telematica.basicconnectionexample.main;

import android.os.AsyncTask;

import cl.telematica.basicconnectionexample.connection.HttpServerConnection;
import cl.telematica.basicconnectionexample.models.Libro;
import cl.telematica.basicconnectionexample.main.view.MainView;

public class MyAsyncTask extends AsyncTask<Void, Void, String> {

    private String url;
    private int timeOut;
    private MainView mainView;

    public MyAsyncTask(String url, int timeOut, MainView mainView) {
        this.url = url;
        this.timeOut = timeOut;
        this.mainView = mainView;
    }

    @Override
    protected void onPreExecute()
    {
        //Antes de ejecutar la hebra
    }

    @Override
    protected String doInBackground(Void... voids) {
        String resultado = new HttpServerConnection().connectToServer(url, timeOut);
        return resultado;
    }

    @Override
    protected void onPostExecute(String result) {
        if(result != null) {
            mainView.populateRecyclerView(Libro.getLibros(result));
        }
    }

}
