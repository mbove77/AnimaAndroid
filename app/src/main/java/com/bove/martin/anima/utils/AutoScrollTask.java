package com.bove.martin.anima.utils;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by Martín Bove on 08/12/2019.
 * E-mail: mbove77@gmail.com
 */
public class AutoScrollTask extends TimerTask {
    private int position;
    private boolean end;
    private List sliders;
    private RecyclerView recyclerView;

    public AutoScrollTask(List sldiers, RecyclerView recycler) {
        this.sliders = sldiers;
        this.recyclerView = recycler;
    }

    @Override
    public void run() {
        if(position == sliders.size() -1){
            end = true;
        } else if (position == 0) {
            end = false;
        }
        if(!end){
            position++;
        } else {
            position--;
        }
        recyclerView.smoothScrollToPosition(position);
    }
}

