package com.danieleloy.danieleloyproyectofirebasexd.Clases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.danieleloy.danieleloyproyectofirebasexd.DetallesComidaActivity;
import com.danieleloy.danieleloyproyectofirebasexd.MainActivity;
import com.danieleloy.danieleloyproyectofirebasexd.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComidaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_PRODUCTO_ITEM = "com.danieleloy.danieleloyproyectofirebasexd.Clases.ComidaViewHolder.Comida";
    public static final String EXTRA_POSICION = "posicion";
    // atributos
    private TextView txt_item_IdComida;
    private TextView txt_item_Nombre;
    private TextView txt_item_precio;
    private ImageView img_item_foto;
    //-------------------------------------
    private AdapterDatos Adapter; //Aqui pones la Clase Adapter

    public ImageView getImg_item_carne() {
        return img_item_foto;
    } //Aqui se devuelve la imagen

    public void setImg_item_ropa(ImageView img_item_ropa) {
        this.img_item_foto = img_item_ropa;
    }

    public ComidaViewHolder(@NonNull View itemView, AdapterDatos ListaCarneAdapter) { //Le pones el adater
        super(itemView);
        txt_item_IdComida = (TextView) itemView.findViewById(R.id.Item_Id_txt);
        txt_item_Nombre = (TextView) itemView.findViewById(R.id.Item_NombreComidaTxt);
        txt_item_precio = (TextView) itemView.findViewById(R.id.Item_Precio_txt);
        img_item_foto = (ImageView) itemView.findViewById(R.id.Imagen_Carne);
        //-----------------------------------------------------------------------------
        Adapter = ListaCarneAdapter;
        itemView.setOnClickListener(this);
    }

    public TextView getTxt_item_IdCarne() {
        return txt_item_IdComida;
    }

    public void setTxt_item_IdCarne(TextView txt_item_IdCarne) {
        this.txt_item_IdComida = txt_item_IdCarne;
    }

    public TextView getTxt_item_Nombre() {
        return txt_item_Nombre;
    }

    public void setTxt_item_Nombre(TextView txt_item_Nombre) {
        this.txt_item_Nombre = txt_item_Nombre;
    }

    public TextView getTxt_item_precio() {
        return txt_item_precio;
    }

    public void setTxt_item_precio(TextView txt_item_precio) {
        this.txt_item_precio = txt_item_precio;
    }

    public ImageView getImg_item_fotoCarne() {
        return img_item_foto;
    }

    public void setImg_item_fotoCarne(ImageView img_item_fotoCarne) {
        this.img_item_foto = img_item_fotoCarne;
    }



    @Override
    public void onClick(View view) {
        int posicion = getLayoutPosition();
        Comida c = Adapter.getListaComida().get(posicion);
        Intent intent = new Intent(Adapter.getContexto(), DetallesComidaActivity.class);
        intent.putExtra(EXTRA_PRODUCTO_ITEM,c);
        Adapter.getContexto().startActivity(intent);

        Context contexto = Adapter.getContexto();
        ((MainActivity) contexto).startActivity(intent);
    }
}
