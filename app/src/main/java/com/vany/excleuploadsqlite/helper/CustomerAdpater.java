package com.vany.excleuploadsqlite.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vany.excleuploadsqlite.R;
import com.vany.excleuploadsqlite.pojo.Customer;

import java.util.List;

public class CustomerAdpater extends RecyclerView.Adapter<CustomerAdpater.CustomerViewHolder> {

    Context context;
    List<Customer> myCustomerList;

    public CustomerAdpater(Context context, List<Customer> myCustomerList) {
        this.context = context;
        this.myCustomerList = myCustomerList;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_view_layout, parent, false);
        return new CustomerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        holder.cust_name.setText(myCustomerList.get(position).getCuName());
        holder.cust_contact.setText(myCustomerList.get(position).getCuContactNumber());
        holder.cust_email.setText(myCustomerList.get(position).getCuEmail());
    }

    @Override
    public int getItemCount() {
        return myCustomerList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView cust_name, cust_email, cust_contact;
        ImageView cust_image;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            cust_name = itemView.findViewById(R.id.cust_layout_main_name);
            cust_contact = itemView.findViewById(R.id.cust_layout_contact);
            cust_email = itemView.findViewById(R.id.cust_layout_email);
            cust_image = itemView.findViewById(R.id.cust_layout_image);
        }
    }
}
