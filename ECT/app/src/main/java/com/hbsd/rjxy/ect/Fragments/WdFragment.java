package com.hbsd.rjxy.ect.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hbsd.rjxy.ect.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WdFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WdFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public View view;
    public ImageView wg_imgmore;
    public ImageView wg_imghead;
    public TextView wg_textname;
    public ImageView wg_imgto;
    public TextView wg_textpro;
    public TextView wg_textnumemail;
    public ProgressBar progressBar;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WdFragment newInstance(String param1, String param2) {
        WdFragment fragment = new WdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment


        //view =inflater.inflate(R.layout.fragment_wd,container,false);
        return inflater.inflate(R.layout.fragment_wd, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //生成控件定义
        /*wg_imghead =(ImageView)  view.findViewById(R.id.imghead);
        wg_textname =(TextView)  view.findViewById(R.id.textname);
        wg_imgto =(ImageView)  view.findViewById(R.id.imgto);
        wg_textpro =(TextView)  view.findViewById(R.id.textpro);
        wg_textnumemail =(TextView) view. findViewById(R.id.textnumemail);
        progressBar= (ProgressBar)  view.findViewById(R.id.prog);*/
    }

}
