package com.bove.martin.anima.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bove.martin.anima.R;
import com.bove.martin.anima.activities.MainActivity;
import com.bove.martin.anima.model.Slider;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Martín Bove on 07/12/2019.
 * E-mail: mbove77@gmail.com
 */
public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.ViewHolder> {

        private List<Slider> slider;
        private int layout;
        private OnItemClickListener listener;
        private Context context;

        public SliderAdapter(List<Slider> slider, int layout, OnItemClickListener listener, Context context) {
            this.slider = slider;
            this.layout = layout;
            this.listener = listener;
            this.context = context;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //Inflamos la vista
            View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.bind(slider.get(position), listener);
        }

        @Override
        public int getItemCount() {
            return slider.size();
        }

        // ViewHolder
        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageViewFoto;
            private TextView tituloPrincipal;
            private TextView subtituloPrincipal;
            private Button buttonCounter;


            public ViewHolder(View itemView) {
                super(itemView);
                imageViewFoto = itemView.findViewById(R.id.imageViewMainSlider);
                tituloPrincipal = itemView.findViewById(R.id.textViewTitulo);
                subtituloPrincipal= itemView.findViewById(R.id.textViewSubtitulo);
                buttonCounter = itemView.findViewById(R.id.buttonCounter);
            }

            // Logica de remplazo de datos y asiganacion de eventos
            public void bind(final Slider slider, final OnItemClickListener listener) {

                int pxHeight = ((MainActivity)context).getDisplayHeight();
                int pxToolbar = ((MainActivity)context).getToolBarHeight();
                imageViewFoto.getLayoutParams().height = pxHeight - pxToolbar;

                // si es el dummy slider cargamos una image desde drawables
                if(slider.getId() == 10001) {
                    Glide.with(itemView)
                            .load(R.drawable.viajes_hero)
                            .into(imageViewFoto);
                } else {
                    Glide.with(itemView)
                            .load("http://www.martinbove.a2hosted.com/images/" + slider.getImage_url())
                            .placeholder(R.drawable.placeholder)
                            .into(imageViewFoto);

                    tituloPrincipal.setText(slider.getTitulo_principal());
                    subtituloPrincipal.setText(slider.getSubtitulo_principal());
                    buttonCounter.setText("0" + (getPosition() + 1));
                }

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(slider, getAdapterPosition());
                    }
                });
            }
        }

        // Listener para comunicar el item click
        public interface OnItemClickListener {
            void onItemClick(Slider slider, int posicion);
        }
    }


