package com.omnify.hire.omnify1;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

/**
 * Created by DELL on 27-07-2017.
 */

public class RandomNmbersFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    int[] list;
    Button generate_nos,sort;
    Bundle b;

    public RandomNmbersFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_random_no,container,false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recyclerView);
        list=new int[20];
             sort=(Button)view.findViewById(R.id.sort);
        generate_nos=(Button)view.findViewById(R.id.generate_nos);
        sort.setOnClickListener(this);
        generate_nos.setOnClickListener(this);
                return view;
    }

   @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sort) {


            FragmentManager fragmentManager = getFragmentManager();
            SortingFragment fragment = new SortingFragment();
            fragment.setArguments(b);
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(R.id.fragment, fragment).addToBackStack(null).commit();
        }
        //generate nos
        else{

            Random n=new Random();
            for(int i=0;i<=19;i++)
            {
                list[i]=n.nextInt(90)+10;
            }
b=new Bundle();
           b.putIntArray("list",list);
            RandomnumberAdapter adapter=new RandomnumberAdapter((AppCompatActivity)getActivity(),list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);

        }

    }


}
