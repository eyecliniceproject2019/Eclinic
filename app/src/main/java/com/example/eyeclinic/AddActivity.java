package com.example.eyeclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AddActivity extends AppCompatActivity
{
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Intent intent = getIntent();
        //pos = intent.getExtras().getInt("Position");
        pos = intent.getIntExtra(Eyecare.EXTRA_POS,0);

        final EyeAdapter adapter=new EyeAdapter(this);
        final ImageView image=(ImageView)findViewById(R.id.baby1);
        final TextView name=(TextView)findViewById(R.id.addname);
        final TextView description=(TextView)findViewById(R.id.adddescription);

        image.setImageResource(adapter.images[pos]);
        name.setText(adapter.names[pos]);
        description.setText(adapter.descrition[pos]);

        Button btnnext=(Button)findViewById(R.id.btnnext);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position =pos;
                image.setImageResource(adapter.images[position]);
                name.setText(""+adapter.names[position]);
                description.setText(""+adapter.descrition[position]);


                if (!(position>=adapter.getCount()-1)){
                    pos += 1;
                }else {
                    pos -= 1;
                }

            }
        });
    }
}
