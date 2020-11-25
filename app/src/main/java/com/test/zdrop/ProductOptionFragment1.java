package com.test.zdrop;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ProductOptionFragment1 extends Fragment {

    ImageView productImg, btnNxt;
    Button closeBtn, incBtn, decBtn;
    LinearLayout linearLayout;
    TextView itemCounter;
    Fragment fragment;
    static int counter = 0;
    private Context context;

    public ProductOptionFragment1() {
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
        View view= inflater.inflate(R.layout.fragment_product_option1, container, false);
        productImg=view.findViewById(R.id.productIV);
        closeBtn=view.findViewById(R.id.closeBtn);
        linearLayout=view.findViewById(R.id.imgLayout);
        incBtn=view.findViewById(R.id.incrementBtn);
        decBtn=view.findViewById(R.id.decrementBtn);
        btnNxt=view.findViewById(R.id.btnNxt);
        itemCounter=view.findViewById(R.id.quantity_counterTV);
        productImg.setClipToOutline(true);

        itemCounter.setText(String.valueOf(counter));

        LayoutInflater itemInflater= LayoutInflater.from(context);

        for (int i=0; i<12; i++){
            View horizontalItemView= itemInflater.inflate(R.layout.horizontal_item,linearLayout,false);
            ImageView itemImage = horizontalItemView.findViewById(R.id.hr_item_iv);
            itemImage.setImageResource(R.drawable.baggy);

            linearLayout.addView(horizontalItemView);
        }


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getFragmentManager().popBackStack();
//                FragmentManager fm = getFragmentManager();
////                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
////                    fm.popBackStack();
////                }
//                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
//                int count = fm.getBackStackEntryCount();
//                for(int i = 0; i < count; ++i) {
//                    fm.popBackStack();
//                }

                String name = getFragmentManager().getBackStackEntryAt(0).getName();
                Log.e("", "onClick: "+name );
                getFragmentManager().popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        incBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                itemCounter.setText(String.valueOf(counter));
            }
        });

        decBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter >= 1){
                    counter--;
                    itemCounter.setText(String.valueOf(counter));
                }
            }
        });

        btnNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment=new ProductOptionFragment2();
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.enter_right_to_left,R.anim.exit_right_to_left,
                                R.anim.enter_left_to_right,R.anim.exit_left_to_right)
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
        });

    }
}