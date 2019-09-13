package cat.code.com.infinitour.Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import cat.code.com.infinitour.Classes.Countryitem;
import cat.code.com.infinitour.R;

public class Autocomplete_country_adapter extends ArrayAdapter<Countryitem> {
    private List<Countryitem> fullcountrylist;
    public Autocomplete_country_adapter(@NonNull Context context,@NonNull List<Countryitem> countrylist) {
        super(context,0, countrylist);
        fullcountrylist=new ArrayList<>(countrylist);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return countryfilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(
                    R.layout.coutry_autocomplete_row,parent,false
            );
        }
        TextView textView=convertView.findViewById(R.id.tv_autocomplete_country);
        Countryitem countryitem=getItem(position);
        if(countryitem!=null){
            textView.setText(countryitem.getCountryname());
        }

        return convertView;
    }

    private Filter countryfilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results=new FilterResults();
            List<Countryitem> suggestion=new ArrayList<>();
            if(constraint==null || constraint.length()==0){
                suggestion.addAll(fullcountrylist);
            }
            else{
                String filterpattern=constraint.toString().toLowerCase().trim();
                for(Countryitem item :fullcountrylist){
                    if(item.getCountryname().toString().toLowerCase().trim().contains(filterpattern)){
                        suggestion.add(item);
                    }
                }
            }
            results.values=suggestion;
            results.count=suggestion.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List)results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Countryitem)resultValue).getCountryname();
        }
    };
}
