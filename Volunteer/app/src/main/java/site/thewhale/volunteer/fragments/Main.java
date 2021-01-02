package site.thewhale.volunteer.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import site.thewhale.volunteer.R;
import site.thewhale.volunteer.adapters.ChildItem;
import site.thewhale.volunteer.adapters.MovieAdapter;
import site.thewhale.volunteer.adapters.ParentAdapter;
import site.thewhale.volunteer.adapters.ParentItem;
import site.thewhale.volunteer.objects.Movies;

public class Main extends Fragment {

    public Main() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView
                ParentRecyclerViewItem
                = view.findViewById(
                R.id.parent_recyclerview);

        LinearLayoutManager
                layoutManager
                = new LinearLayoutManager(
                view.getContext());

        ParentAdapter parentItemAdapter = new ParentAdapter( ParentItemList());

        ParentRecyclerViewItem
                .setAdapter(parentItemAdapter);
        ParentRecyclerViewItem
                .setLayoutManager(layoutManager);


        ArrayList<Movies> moviesArrayList = new ArrayList<Movies>();
        moviesArrayList.add(
                new Movies("Harry Potter and the Sorcerer's Stone", R.drawable.group,
                        "An orphaned boy enrolls in a school of wizardry, where he learns the truth about himself, his family and the terrible evil that haunts the magical world.",
                        "2001", "152 min",
                        "7.6", "https://www.imdb.com/title/tt0241527/?ref_=ttls_li_tt"));
        moviesArrayList.add(
                new Movies("Harry Potter and the Chamber of Secrets", R.drawable.group,
                        "An ancient prophecy seems to be coming true when a mysterious presence begins stalking the corridors of a school of magic and leaving its victims paralyzed.",
                        "2002", "161 min",
                        "7.4", "https://www.imdb.com/title/tt0295297/?ref_=ttls_li_tt"));
        moviesArrayList.add(
                new Movies("Harry Potter and the Prisoner of Azkaban", R.drawable.group,
                        "Harry Potter, Ron and Hermione return to Hogwarts School of Witchcraft and Wizardry for their third year of study, where they delve into the mystery surrounding an escaped prisoner who poses a dangerous threat to the young wizard.",
                        "2004", "142 min",
                        "7.9", "https://www.imdb.com/title/tt0304141/?ref_=ttls_li_tt"));
        moviesArrayList.add(
                new Movies("Harry Potter and the Goblet of Fire", R.drawable.group,
                        "Harry Potter finds himself competing in a hazardous tournament between rival schools of magic, but he is distracted by recurring nightmares.",
                        "2005", "157 min",
                        "7.7", "https://www.imdb.com/title/tt0330373/?ref_=ttls_li_tt"));

        RecyclerView rView = view.findViewById(R.id.rView);

        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(view.getContext());
        rView.setLayoutManager(lm);

        MovieAdapter movieAdapter = new MovieAdapter(moviesArrayList, view.getContext());
        rView.setAdapter(movieAdapter);

        return view;
    }

    private List<ParentItem> ParentItemList()
    {
        List<ParentItem> itemList
                = new ArrayList<>();

        ParentItem item
                = new ParentItem(
                "Harry Potter's Movies Series",
                ChildItemList());
        itemList.add(item);


        return itemList;
    }

    private List<ChildItem> ChildItemList()
    {
        List<ChildItem> ChildItemList
                = new ArrayList<>();

        ChildItemList.add(new ChildItem("Part 1", R.drawable.group));
        ChildItemList.add(new ChildItem("Part 2", R.drawable.group));
        ChildItemList.add(new ChildItem("Part 3", R.drawable.group));
        ChildItemList.add(new ChildItem("Part 4",R.drawable.group));

        return ChildItemList;
    }
}