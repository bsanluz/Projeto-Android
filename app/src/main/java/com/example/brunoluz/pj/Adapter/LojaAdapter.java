package com.example.brunoluz.pj.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.brunoluz.pj.R;
import com.example.brunoluz.pj.model.Loja;

import java.util.ArrayList;

/**
 * Created by Bruno Luz on 01/08/2016.
 */
public class LojaAdapter extends BaseAdapter {
    private Context c;
    private ArrayList<Loja> lista;

    public LojaAdapter(Context c, ArrayList<Loja> lista) {
        this.c = c;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        Loja loja = lista.get(position);
        ViewHolder holder;
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.itenslista,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
        holder = (ViewHolder) convertView.getTag();
        }
        String ender = loja.getEnderecoLoja()+", "+loja.getCidadeLoja()+"-"+loja.getEstadoLoja();
        holder.nomeLoja.setText(loja.getNomeLoja().toString());
        holder.enderecoLoja.setText(ender);
        holder.contatoLoja.setText(loja.getContatoTel());
        holder.tipoLoja.setText(loja.getTipoLoja());
        holder.icLoja.setImageResource(R.drawable.ic_store_black_24dp);
        holder.icContato.setImageResource(R.drawable.ic_phone_black_24dp);
        holder.icEndereco.setImageResource(R.drawable.ic_room_black_24dp);
        holder.icTipo.setImageResource(R.drawable.ic_loyalty_black_24dp);

        return convertView;
    }

    public class ViewHolder{
        TextView nomeLoja, enderecoLoja, contatoLoja, tipoLoja;
        ImageView icLoja, icEndereco, icContato, icTipo;

        ViewHolder(View v){
            nomeLoja = (TextView) v.findViewById(R.id.textView_NomeLola);
            enderecoLoja = (TextView) v.findViewById(R.id.textView_Endereco);
            contatoLoja = (TextView) v.findViewById(R.id.textView_Contato);
            tipoLoja = (TextView) v.findViewById(R.id.textView_Tipo);
            icLoja= (ImageView) v.findViewById(R.id.imageView_IconeLoja);
            icEndereco = (ImageView) v.findViewById(R.id.imageView_IconeEnder);
            icContato = (ImageView) v.findViewById(R.id.imageView_IconeContato);
            icTipo = (ImageView) v.findViewById(R.id.imageView_IconeTipo);
        }
    }
}
