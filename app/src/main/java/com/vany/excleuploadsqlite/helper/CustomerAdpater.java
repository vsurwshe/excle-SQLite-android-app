package com.vany.excleuploadsqlite.helper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vany.excleuploadsqlite.R;
import com.vany.excleuploadsqlite.customer.ViewDetails;
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
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, final int position) {
        holder.cust_name.setText(myCustomerList.get(position).getCuName());
        holder.cust_contact.setText(myCustomerList.get(position).getCuContactNumber());
        holder.cust_email.setText(myCustomerList.get(position).getCuEmail());
        holder.cust_address.setText(myCustomerList.get(position).getCuAddress());
        holder.cust_image.setImageResource(R.drawable.user);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer tempObject = myCustomerList.get(position);
                Intent customerView = new Intent(context, ViewDetails.class);
                customerView.putExtra("Customer", tempObject);
                context.startActivity(customerView);
                Toast.makeText(context, "You Click on the " + position + " this record", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("Size " + myCustomerList + " / " + myCustomerList.size());
        return myCustomerList.size();
    }

    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        TextView cust_name, cust_email, cust_contact, cust_address;
        ImageView cust_image;

        public CustomerViewHolder(@NonNull View itemView) {
            super(itemView);
            cust_name = itemView.findViewById(R.id.cust_layout_main_name);
            cust_contact = itemView.findViewById(R.id.cust_layout_contact);
            cust_email = itemView.findViewById(R.id.cust_layout_email);
            cust_image = itemView.findViewById(R.id.cust_layout_image);
            cust_address = itemView.findViewById(R.id.cust_layout_address);
        }
    }
}
