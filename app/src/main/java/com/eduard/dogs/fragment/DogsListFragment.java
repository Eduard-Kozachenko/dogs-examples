package com.eduard.dogs.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.eduard.dogs.R;
import com.eduard.dogs.activity.MainActivity;
import com.eduard.dogs.adapter.DogsListAdapter;
import com.eduard.dogs.adapter.ExpListAdapter;
import com.eduard.dogs.adapter.ListItemClickListener;
import com.eduard.dogs.retrofit.ApiClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.eduard.dogs.constants.DogsConstants.BREED_NAME;


public class DogsListFragment extends Fragment  {

    public static String TAG = DogsListFragment.class.getSimpleName();

    String breedString;

    ExpandableListAdapter expListAdapter;
    ExpandableListView expListView;

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_dogs_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        expListView = view.findViewById(R.id.expListDogs);

        getBreedsList(getContext());

    }

    public void getBreedsList(final Context cont) {
        ApiClient.getInstance()
                .getBreedsService()
                .fetchBreedsList()
                .enqueue(new Callback<JsonObject>() {

                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        listDataHeader = new ArrayList<String>();
                        listDataChild = new HashMap<String, List<String>>();
                        JsonObject body = response.body();
                        JsonObject breedObj = body.get(BREED_NAME).getAsJsonObject();
                        Set<String> breedStrList;

                        if (body != null) {

                            breedStrList = breedObj.keySet();
                            Iterator<String> iterators = breedStrList.iterator();

                            while (iterators.hasNext()) {
                                listDataHeader.add(iterators.next());
                            }

                            ArrayList<String> subBreedS;

                            for (int i = 0; i < listDataHeader.size(); i++) {

                                subBreedS = new ArrayList<String>(breedObj.get(listDataHeader.get(i)).getAsJsonArray().size());
                                if (breedObj.get(listDataHeader.get(i)).getAsJsonArray().size() != 0) {
                                    for (int j = 0; j < breedObj.get(listDataHeader.get(i)).getAsJsonArray().size(); j++) {
                                        subBreedS.add(breedObj.get(listDataHeader.get(i)).getAsJsonArray().get(j).getAsString());

                                    }
                                }

                                listDataChild.put(listDataHeader.get(i), subBreedS);

                            }

                            expListView = (ExpandableListView) expListView.findViewById(R.id.expListDogs);
                            expListAdapter = new ExpListAdapter( cont , listDataHeader , listDataChild);
                            expListView.setAdapter(expListAdapter);

                            expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                                @Override
                                public boolean onGroupClick(ExpandableListView parent, View v,
                                                            int groupPosition, long id) {

                                    Toast.makeText(cont, "" +listDataChild.get(listDataHeader.get(groupPosition)).size(),   //schitaet cherez header skol'ko childerov
                                    Toast.LENGTH_SHORT).show();

                                    if (listDataChild.get(listDataHeader.get(groupPosition)).size() == 0){
                                        try {
                                            breedString = listDataHeader.get(groupPosition);
                                            ((MainActivity) getActivity()).replaceFragment(breedString,"");

                                        } catch (Exception e) {
                                            //TODO show error message
                                        }
                                    }

                                    return false;
                                }
                            });

                            expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                                @Override
                                public boolean onChildClick(ExpandableListView parent, View v,
                                                            int groupPosition, int childPosition, long id) {

                                    try {
                                        breedString = listDataHeader.get(groupPosition) + "/" + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                                        ((MainActivity)getActivity()).replaceFragment(listDataHeader.get(groupPosition),listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition));

                                    } catch (Exception e) {
                                        //TODO show error message
                                    }

                                    return false;
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d(TAG, "onFailure ");
                    }
                });
    }

}
