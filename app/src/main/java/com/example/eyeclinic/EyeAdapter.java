package com.example.eyeclinic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EyeAdapter extends BaseAdapter
{

    Context c;
    String [] names = {"Dinga Opicals","Vision Express","Alantila","Specx4less","Dinga Opical","Eye Destinatination","Real mmAAr.com","Baby Carrier","Himalaya Baby Care Gift Pack"};
    String [] descrition = {"Frame/Ani Glare/with power shades Available at Dinga Opticals","Buy one get one free including lenses","Night of specticle with bes price ","Discover best lences and specticles.","Frame/Ani Glare/with power shades Available at Dinga Opticals","When you Buy any three diaper packed listed","Toys and Baby care products","We always with you.","Happy moms and baby forever"};
    int [] images={R.drawable.shop1,
            R.drawable.shop2,
            R.drawable.shop3,
            R.drawable.shop4,
            R.drawable.shop5,
            R.drawable.shop6,
            R.drawable.shop7,
            R.drawable.shop8,
            R.drawable.shop9};


    public EyeAdapter(Context ctx)
    {
        this.c=ctx;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int pos) {
        return names[pos];
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View convertview, ViewGroup viewGroup) {

        if (convertview==null)
        {
            LayoutInflater inflater= (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview=inflater.inflate(R.layout.adds,null);
        }

        TextView nametxt = (TextView) convertview.findViewById(R.id.name);
        TextView descriptiontxt = (TextView) convertview.findViewById(R.id.description);
        ImageView image = (ImageView) convertview.findViewById(R.id.shop1);

        nametxt.setText(names[pos]);
        descriptiontxt.setText(descrition[pos]);
        image.setImageResource(images[pos]);

        return convertview;
    }
}
