package sg.edu.np.mad.Practical2;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class BrandsAdapter extends RecyclerView.Adapter<BrandViewHolder>{
    private ArrayList<User> data;
    private OnProfileListener mOnProfileListener;

    public BrandsAdapter(ArrayList<User> data, OnProfileListener onProfileListener )
    {
        this.mOnProfileListener = onProfileListener;
        this.data = data;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;
        if(data.get(position).name.endsWith("7")){
            viewType = 0;
        }
        else{
            viewType = 1;
        }
        return viewType;
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if(viewType == 1)
        {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdetails,
                    null, false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.userdetailsif7,
                    parent, false);
        }
        return new BrandViewHolder(item, mOnProfileListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        User n = data.get(position);
        holder.txt.setText(n.name);
        holder.txt1.setText(n.description);
        //holder.status.setText(n.followed);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface OnProfileListener{
        void onProfileClick(int position);
    }
}

