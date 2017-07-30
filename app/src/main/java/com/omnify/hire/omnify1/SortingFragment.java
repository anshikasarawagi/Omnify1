package com.omnify.hire.omnify1;

import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DELL on 27-07-2017.
 */

public class SortingFragment extends Fragment {
private int[] list;
    RecyclerView recyclerViewM,recyclerViewQ;
    Service service;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new int[20];
        Bundle b=getArguments();
        list=b.getIntArray("list");
        Log.d("list",list[0] +"sort fragment create");
        Intent i=new Intent(getActivity(),Service.class);
        Bundle bundle=new Bundle();
        bundle.putIntArray("list",list);
        i.putExtra("numbers",bundle);
        getActivity().bindService(i,quicksortServiceConnection, Context.BIND_AUTO_CREATE);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sorting,container,false);



        recyclerViewM=(RecyclerView)view.findViewById(R.id.mergeSort);
        recyclerViewQ=(RecyclerView)view.findViewById(R.id.quickSort);





        return view;
    }

    public  void getList() {
        Log.d("list", list[0] + "getList");
       if (service !=null ) {

            int[] sortedQuick;
            sortedQuick = service.getSortedList(0, list.length - 1);
            RandomnumberAdapter adapterQ = new RandomnumberAdapter((AppCompatActivity) getActivity(), sortedQuick);
            recyclerViewQ.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewQ.setAdapter(adapterQ);

            RandomnumberAdapter adapter = new RandomnumberAdapter((AppCompatActivity) getActivity(),
                    service.sortByMergeSort(0, list.length - 1));
            recyclerViewM.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewM.setAdapter(adapter);
       }
    }



    private ServiceConnection quicksortServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Service.MyLocalBinder binder=(Service.MyLocalBinder) service;
            SortingFragment.this.service =binder.getservice();
            getList();


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {



        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unbindService(quicksortServiceConnection);

    }

}
