package cl.telematica.basicconnectionexample.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import cl.telematica.basicconnectionexample.R;
import cl.telematica.basicconnectionexample.models.Libro;

public class UIAdapter extends RecyclerView.Adapter<UIAdapter.ViewHolder> {

    private List<Libro> mDataset;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public TextView mGeneroView;
        public ImageView mFotoView;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.textName);
            mGeneroView = v.findViewById(R.id.textGenero);
            mFotoView = v.findViewById(R.id.foto);
        }
    }

    public UIAdapter(List<Libro> myDataset, Context cont) {
        mDataset = myDataset;
        this.context = cont;
    }

    @Override
    public UIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vista_libro, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Libro libro = mDataset.get(position);

        holder.mTextView.setText(libro.getNombre());
        holder.mGeneroView.setText(libro.getGenero());
        Glide.with(context)
                .load(libro.getFoto())
                .into(holder.mFotoView);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
