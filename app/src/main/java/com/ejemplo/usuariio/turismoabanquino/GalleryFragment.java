package com.ejemplo.usuariio.turismoabanquino;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class GalleryFragment extends Fragment {

    private AppBarLayout appBar;
    private TabLayout pestanas;
    private ViewPager viewPager;
    private ArrayList<LugaresVo> lugares;

    public static GalleryFragment newInstance(ArrayList<LugaresVo> lugares){
        Bundle bn=new Bundle();
        bn.putParcelableArrayList("LUGARES",lugares);
        GalleryFragment ff=new GalleryFragment();
        ff.setArguments(bn);
        return ff;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_gallery, container, false);

        lugares=getArguments().getParcelableArrayList("LUGARES");

        View contenedor=(View)container.getParent();
        appBar=(AppBarLayout) contenedor.findViewById(R.id.appBar);
        pestanas=new TabLayout(getActivity());
        pestanas.setTabTextColors(Color.parseColor("#ffffff"),Color.parseColor("#ffffff"));
        appBar.addView(pestanas);

        viewPager=(ViewPager)view.findViewById(R.id.pager);
        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        pestanas.setupWithViewPager(viewPager);


        return view;
    }


    @Override
    public void onDestroyView(){
        super.onDestroyView();
        appBar.removeView(pestanas);
    }
    public class ViewPagerAdapter extends FragmentStatePagerAdapter{
        public ViewPagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }
        String [] tituloPestana={"LISTA","MAPA"};

        @Override
        public Fragment getItem(int posecion){
            switch (posecion){
                case 0 : return LISTAFragment.newInstance(lugares);
                case 1 : //return new MAPAFragment();
                        return FragmentMap.newInstance(0,null,lugares);
            }

            return null;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tituloPestana[position];

        }

        @Override
        public int getCount() {
            return 2;
        }



    }
}
