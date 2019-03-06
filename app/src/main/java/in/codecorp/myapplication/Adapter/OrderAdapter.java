package in.codecorp.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import in.codecorp.myapplication.R;
import in.codecorp.myapplication.Utils.Order;


public class OrderAdapter extends ArrayAdapter<Order> {
    Context context;
    int resource;
    int a;
    LinearLayout ll1;
    List<String> keys;
    List<Order> cartList1, cartList2;


    public OrderAdapter(Context context, int resource, List<Order> objects) {
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
        TextView txtPrice = (TextView) view.findViewById(R.id.textViewPrice);
        TextView sdesc = (TextView) view.findViewById(R.id.textViewSdesc);






        final Order cart = cartList1.get(position);
        txtName.setText(cart.getOd_init_date());
        txtPrice.setText(cart.getOd_pay_date());

        sdesc.setText(Html.fromHtml(cart.getOd_amount()));


        return view;
    }

}

