package com.test.zdrop;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.zdrop.utility.CartConverter;


public class ProductFragment extends Fragment {


    LinearLayout linearLayout;
    ImageView productImg, cartIV;
    Button cartBtn;
    static int cart_count = 0;
    Context context;
    TextView menuTv;
    OpenProductOption productOption;


    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context=context;
        productOption= (OpenProductOption) context;
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
        menuTv=view.findViewById(R.id.menuTV);

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
                productOption.onOptionClicked();
            }
        });

        menuTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(context, menuTv);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.option_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId()==R.id.productOp){
                        productOption.onOptionClicked();
                    }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        });
    }


    public interface OpenProductOption{
        void onOptionClicked();
    }
}