package in.codecorp.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.codecorp.myapplication.GetTestActivity;
import in.codecorp.myapplication.R;
import in.codecorp.myapplication.TestActivity;
import in.codecorp.myapplication.TestDetailActivity;
import in.codecorp.myapplication.Utils.Level;


public class ListLevelAdapter extends ArrayAdapter<Level> {
    Context context;
    int resource;
    int a;
    List<String> keys;
    List<Level> cartList1, cartList2;


    public ListLevelAdapter(Context context, int resource, List<Level> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        cartList1 = objects;
        cartList2 = new ArrayList<>();
        cartList2.addAll(cartList1);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = null;
        view = LayoutInflater.from(context).inflate(resource, parent, false);
        keys = new ArrayList<>();

        TextView txtName = (TextView) view.findViewById(R.id.textViewName);




        final Level cart = cartList1.get(position);
        txtName.setText(cart.getLevelTitle());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GetTestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("levelid", cart.getLevelid() );
                context.startActivity(intent);
            }
        });

        return view;
    }
}

