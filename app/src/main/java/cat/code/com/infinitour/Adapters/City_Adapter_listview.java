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
import java.util.ArrayList;

import cat.code.com.infinitour.R;


public class City_Adapter_listview extends ArrayAdapter<String> {
    ArrayList<String> item;
    Context context;
    int resource;

    public City_Adapter_listview(Context context, int resource ,ArrayList<String> item) {
        super(context, resource , item);
        this.context = context ;
        this.resource = resource ;
        this.item = item ;
    }


    public View getView(int position, View view, ViewGroup parent)
    {
        if (view == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.city_element, null, true);


        }
        TextView TV1 = view.findViewById(R.id.tv_cityname);
        TV1.setText(item.get(position)+"");

        return view;
    }

}

