package in.codecorp.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import in.codecorp.myapplication.MainActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.TestActivity;
import in.codecorp.myapplication.Utils.Category;


public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.ProductViewHolder> {

    private Context mCtx;
    private String pp, charges;
    private List<Category> orderList;
    String subtotal, discount;

    public PackageAdapter(Context mCtx, List<Category> orderList) {
        this.mCtx = mCtx;
        this.orderList = orderList;
        setHasStableIds(true);

    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_package_category, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, int position) {
        final Category courses = orderList.get(position);
        holder.name.setText(courses.getPcat_title());
       // holder.desc.setText(courses.getPcat_desc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mCtx, TestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("pcatid", courses.getPcat_id());
                mCtx.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount () {
        return orderList.size();
    }



    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, desc;
        Button view;

        public ProductViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
           // desc = itemView.findViewById(R.id.textViewDesc);
           // view = itemView.findViewById(R.id.btnExp);


        }
    }
}