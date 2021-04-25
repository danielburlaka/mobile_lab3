package ua.kpi.comsys.io8303.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.lab1.R;
import ua.kpi.comsys.io8303.model.BookItem;

import java.util.ArrayList;

public class BookListAdapter extends ArrayAdapter<String> {

    private final Fragment context;
    private final ArrayList<BookItem> movies;
    private final ArrayList<String> maintitle;

    public BookListAdapter(Fragment context, ArrayList<BookItem> movies, ArrayList<String> maintitle) {

        super(context.getContext(), R.layout.display_book_item, maintitle);

        this.context=context;
        this.movies=movies;
        this.maintitle = maintitle;
        System.out.println("Got here con");
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View rowView=inflater.inflate(R.layout.display_book_item, null,true);

        ImageView image = (ImageView) rowView.findViewById(R.id.poster);
        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        TextView yearText = (TextView) rowView.findViewById(R.id.year);
        TextView typeText = (TextView) rowView.findViewById(R.id.type);

        System.out.println(position);
        int drawableResourceId = this.getContext().getResources().getIdentifier(
                movies.get(position).getImage().toLowerCase().replace(".png", ""),
                "drawable", this.getContext().getPackageName());
        if (drawableResourceId == 0)
            image.setImageResource(R.drawable.no_poster);
        else
            image.setImageResource(drawableResourceId);

        titleText.setText(maintitle.get(position));
        yearText.setText(movies.get(position).getSubtitle());
        typeText.setText(movies.get(position).getPrice());

        return rowView;
    };
}