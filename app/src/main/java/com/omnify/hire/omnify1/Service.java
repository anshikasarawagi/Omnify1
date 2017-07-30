package com.omnify.hire.omnify1;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class Service extends android.app.Service {
    private int[] list=new int[20];
    private int[] list1=new int[20];
    private int[] helper=new int[20];
    private IBinder binder=new MyLocalBinder();
    public Service() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("list",list[0] +"in service");
       Bundle b=intent.getBundleExtra("numbers");
        list=b.getIntArray("list");
        list1=b.getIntArray("list");
        Log.d("list",list[0] +"in service");
        return binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



    public int[] getSortedList(int low, int high){

        int i = low, j = high;
                int pivot = list[((i+j)/2)];

        while (i <= j) {

            while (list[i] < pivot) {
                i++;
            }

            while ( list[j]> pivot) {
                j--;
            }

            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }

        if (low < j)
            getSortedList(low, j);
        if (i < high)
            getSortedList(i, high);
        return list;
    }


private void exchange(int i, int j) {
        int temp = list[i];
        list[i]=list[j];
        list[j]=temp;

        }

        public int[] sortByMergeSort(int low ,int high)
        {
            if (low < high) {
                int middle = low + (high - low) / 2;
                sortByMergeSort(low, middle);
                sortByMergeSort(middle + 1, high);
                merge(low, middle, high);

            }
            return list1;
        }
    private void merge(int low, int middle, int high) {

        for (int i = low; i <= high; i++) {
            helper[i] = list1[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                list1[k] = helper[i];
                i++;
            } else {
                list1[k] = helper[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            list1[k] = helper[i];
            k++;
            i++;
        }
    }

        public class MyLocalBinder extends Binder{
        Service getservice()
        {
            return Service.this;
        }
    }


}
