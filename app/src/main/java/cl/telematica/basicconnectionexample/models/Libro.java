package cl.telematica.basicconnectionexample.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Libro {
    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("editorial")
    private String editorial;
    @SerializedName("genero")
    private String genero;
    @SerializedName("autor")
    private int autor;
    @SerializedName("photo")
    private String foto;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAutor() {
        return autor;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public static List<Libro> getLibros(String result) {
        List<Libro> lista = new ArrayList<>();

        try {
            JSONArray listaArray = new JSONArray(result);
            int size = listaArray.length();

            for(int i = 0; i < size; i++) {
                Libro libro = new Libro();
                JSONObject obj = listaArray.getJSONObject(i);

                libro.setId(obj.getInt("id"));
                libro.setNombre(obj.getString("nombre"));
                libro.setEditorial(obj.getString("editorial"));
                libro.setGenero(obj.getString("genero"));
                libro.setAutor(obj.getInt("autor"));

                lista.add(libro);
            }
            return lista;
        } catch (JSONException e) {
            e.printStackTrace();
            return lista;
        }
    }
}
