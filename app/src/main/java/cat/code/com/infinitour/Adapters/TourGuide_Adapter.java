package cat.code.com.infinitour.Adapters;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cat.code.com.infinitour.Classes.TourGudie;
import cat.code.com.infinitour.R;

public class TourGuide_Adapter extends ArrayAdapter<TourGudie> {
    ArrayList<TourGudie> item;
    Context context;
    int resource;
    String country;
    String city;
    ArrayList<String>langs;

    public TourGuide_Adapter(Context context, int resource ,ArrayList<TourGudie> item,String country,String city,ArrayList<String>langs) {
        super(context, resource , item);
        this.context = context ;
        this.resource = resource ;
        this.item = item ;
        this.country = country ;
        this.city = city ;
        this.langs = langs ;
    }


    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.tourguides_row, null, true);

        }


        ImageView imageView = (ImageView) view.findViewById(R.id.img_tourguide_picture);
        TextView name = view.findViewById(R.id.tv_tourguide_name);
        TextView age = view.findViewById(R.id.tv_tourguide_age);
        TextView lang = view.findViewById(R.id.tv_tourguide_language);

        imageView.setImageResource(R.drawable.ic_launcher_background);
        name.setText(item.get(position).getName());
        age.setText(item.get(position).getAge()+" Years old");
        lang.setText(langs.get(0));

        Glide.with(getContext())  //2
                .load(item.get(position).getPhoto_url()) //3
                .centerCrop() //4
                .placeholder(R.drawable.ic_launcher_background) //5
                .error(R.drawable.ic_launcher_background) //6
                .fallback(R.drawable.ic_launcher_background) //7
                .into(imageView);


        return view;
    }
}
