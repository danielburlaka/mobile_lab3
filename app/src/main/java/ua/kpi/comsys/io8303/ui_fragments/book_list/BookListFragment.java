package ua.kpi.comsys.io8303.ui_fragments.book_list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.lab1.R;
import ua.kpi.comsys.io8303.adapters.BookListAdapter;
import ua.kpi.comsys.io8303.model.BookItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BookListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_books_list, container, false);

        String fileName = "BooksList.json";

        Gson gson = new Gson();
        Type listOfBooksItemsType = new TypeToken<ArrayList<BookItem>>() {}.getType();
        ArrayList<BookItem> books = new ArrayList<>();
        ArrayList<String> maintitle = new ArrayList<>();

        try {
            books = gson.fromJson(ReadTextFile(fileName), listOfBooksItemsType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (BookItem book: books) {
            maintitle.add(book.getTitle());
        }
        ListView list = root.findViewById(R.id.BooksListView);
        BookListAdapter adapter = new BookListAdapter(this, books, maintitle);
        list.setAdapter(adapter);

        return root;
    }

    public String ReadTextFile(String name) throws IOException {
        StringBuilder string = new StringBuilder();
        String line = "";
        InputStream is = getContext().getAssets().open(name);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            string.append(line);
        }
        is.close();
        return string.toString();
    }
}