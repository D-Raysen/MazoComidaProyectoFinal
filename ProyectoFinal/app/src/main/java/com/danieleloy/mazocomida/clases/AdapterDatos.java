package com.danieleloy.mazocomida.clases;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.danieleloy.mazocomida.R;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<ComidaViewHolder> {

    private Context contexto = null;
    private ArrayList<Comida> ListaComida = null;
    private LayoutInflater inflate = null;
    private boolean clickable = true;
    public AdapterDatos(Context contexto, ArrayList<Comida> listaComida) {
        this.contexto = contexto;
        this.ListaComida = listaComida;
        inflate = LayoutInflater.from(this.contexto);
    }

    public Context getContexto() {
        return contexto;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ComidaViewHolder holder, int position) {


        Comida comida1 = this.ListaComida.get(position);
        holder.getTxt_item_Nombre().setText("Nombre: " + comida1.getNombreComida());
        holder.getTxt_item_IdCarne().setText("Id: " + comida1.getIdComida());

        //holder.getImg_item_carne().setImageResource(R.drawable.carneimg.jpg);

        String carpeta = "Comida";
        ImageView imagen = holder.getImg_item_fotoCarne() ;
        ImagenesFirebase.descargarFoto(carpeta,comida1.getNombreComida(),imagen);
        ImageView imagen1 = imagen;
        holder.setImg_item_fotoCarne(imagen1);
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
