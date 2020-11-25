package com.test.zdrop;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.test.zdrop.utility.CartConverter;


public class ProductFragment extends Fragment {


    LinearLayout linearLayout;
    ImageView productImg, cartIV;
    Button cartBtn;
    static int cart_count = 0;
    Context context;
    Fragment fragment;


    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_product, container, false);

        linearLayout=view.findViewById(R.id.imgLayout);
        cartBtn=view.findViewById(R.id.btnCart);
        cartIV=view.findViewById(R.id.cartIV);
        productImg=view.findViewById(R.id.productIV);

        LayoutInflater itemInflater= LayoutInflater.from(context);

        for (int i=0; i<12; i++){
            View horizontalItemView= itemInflater.inflate(R.layout.horizontal_item,linearLayout,false);
            ImageView itemImage = horizontalItemView.findViewById(R.id.hr_item_iv);
            itemImage.setImageResource(R.drawable.keds);

            linearLayout.addView(horizontalItemView);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart_count++;
                cartIV.setImageDrawable(CartConverter.convertLayoutToImage(context, cart_count, R.drawable.cart));
            }
        });

        productImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new ProductOptionFragment1();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("main").commit();
            }
        });
    }
}