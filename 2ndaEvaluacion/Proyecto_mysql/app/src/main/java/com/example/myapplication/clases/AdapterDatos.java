package com.example.myapplication.clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<ComidaViewHolder> {

    private Context contexto = null;
    private ArrayList<Comida> ListaComida = null;
    private LayoutInflater inflate = null;

    public AdapterDatos(Context contexto, ArrayList<Comida> listaComida) {
        this.contexto = contexto;
        this.ListaComida = listaComida;
        inflate = LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setContexto(Context contexto) {
        this.contexto = contexto;
    }

    public ArrayList<Comida> getListaComida() {
        return ListaComida;
    }

    public void setListaComida(ArrayList<Comida> listaComida) {
        ListaComida = listaComida;
    }


    @NonNull
    @Override
    public ComidaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = inflate.inflate(R.layout.item_rv_comida, parent, false);
        ComidaViewHolder cvo = new ComidaViewHolder(mItemView, this);

        return cvo;
    }

    @Override
    public void onBindViewHolder(@NonNull ComidaViewHolder holder, int position) {
        Comida comida1 = this.ListaComida.get(position);
        holder.getTxt_item_Nombre().setText("Nombre: " + comida1.getNombreComida());
        holder.getTxt_item_IdCarne().setText("Id: " + comida1.getIdComida());
        holder.getTxt_item_precio().setText("Precio " + comida1.getPrecio());
        //holder.getImg_item_carne().setImageResource(R.drawable.carneimg.jpg);
    }



    @Override
    public int getItemCount() {
       return this.ListaComida.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        public ViewHolderDatos(@NonNull View itemView, AdapterDatos adapterDatos) {
            super(itemView);
        }
    }
}
