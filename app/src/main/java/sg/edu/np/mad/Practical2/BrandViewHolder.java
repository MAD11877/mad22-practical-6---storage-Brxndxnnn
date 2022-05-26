package sg.edu.np.mad.Practical2;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class BrandViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView txt;
    TextView txt1;
    ImageView imageV;
    //View item;

    BrandsAdapter.OnProfileListener onProfileListener;

    public BrandViewHolder(@NonNull View itemView, BrandsAdapter.OnProfileListener onProfileListener) {
        super(itemView);
        txt = itemView.findViewById(R.id.username);
        txt1 = itemView.findViewById(R.id.description);
        imageV = itemView.findViewById(R.id.imageView4);
        //this.item = item;
        this.onProfileListener = onProfileListener;

        imageV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onProfileListener.onProfileClick(getAdapterPosition());
    }
}
