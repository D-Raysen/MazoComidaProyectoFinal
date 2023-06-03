package com.example.myapplication.clases;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.myapplication.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ComidaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public static final String EXTRA_PRODUCTO_ITEM = "Carne";

    public static final String EXTRA_POSICION_CASILLA = "com.example.myapplication.clases.ComidaViewHolder.posicion";
    // atributos
    private TextView txt_item_IdCarne;
    private TextView txt_item_Nombre;
    private TextView txt_item_precio;
    private ImageView img_item_fotoCarne;
    //-------------------------------------
    private AdapterDatos CarneAdapter; //Aqui pones la Clase Adapter

    public ImageView getImg_item_carne() {
        return img_item_fotoCarne;
    } //Aqui se devuelve la imagen

    public void setImg_item_ropa(ImageView img_item_ropa) {
        this.img_item_fotoCarne = img_item_ropa;
    }

    public ComidaViewHolder(@NonNull View itemView, AdapterDatos ListaCarneAdapter) { //Le pones el adater
        super(itemView);
        txt_item_IdCarne = (TextView) itemView.findViewById(R.id.Item_Id_txt);
        txt_item_Nombre = (TextView) itemView.findViewById(R.id.Item_NombreComidaTxt);
        txt_item_precio = (TextView) itemView.findViewById(R.id.Item_Precio_txt);
        img_item_fotoCarne = (ImageView) itemView.findViewById(R.id.Imagen_Carne);
        //-----------------------------------------------------------------------------
        CarneAdapter = ListaCarneAdapter;
        itemView.setOnClickListener(this);
    }

    public TextView getTxt_item_IdCarne() {
        return txt_item_IdCarne;
    }

    public void setTxt_item_IdCarne(TextView txt_item_IdCarne) {
        this.txt_item_IdCarne = txt_item_IdCarne;
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
        return img_item_fotoCarne;
    }

    public void setImg_item_fotoCarne(ImageView img_item_fotoCarne) {
        this.img_item_fotoCarne = img_item_fotoCarne;
    }



    @Override
    public void onClick(View view) {
        //      int posicion = getLayoutPosition();
        //      Ropa p = lpa.getRopas().get(posicion);
        //      Intent intent = new Intent(lpa.getContexto(),DetallesRopaActivity.class);
        //      intent.putExtra(EXTRA_ROPA_ITEM,p);
        //      lpa.getContexto().startActivity(intent);
    }
}
