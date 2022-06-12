package com.test.culinary.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.test.culinary.R;
import com.test.culinary.model.Recipe;
import com.test.culinary.model.ItemAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private static final String TAG = "aaaaaa";
    List<Recipe> viewItems;
    private RecyclerView rv;


    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,null);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv=(RecyclerView)view.findViewById(R.id.recipesList);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        viewItems =new ArrayList<>();
        addItemsFromJSON();
    }


    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);
                int id = itemObj.getInt("id");
                String title = itemObj.getString("title");
                String time = itemObj.getString("time");
                String amount = itemObj.getString("amount");
                String complexity = itemObj.getString("complexity");
                String image = itemObj.getString("image");
                String products = itemObj.getString("products");
                JSONArray actions = (JSONArray) itemObj.getJSONArray("actions");

                Recipe recipes = new Recipe(id, title, time, amount, complexity, image, products, actions);
                viewItems.add(recipes);
                setupData(viewItems);
            }

        } catch (JSONException | IOException e) {
            Log.d(TAG, "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException{
        InputStream inputStream= null;
        StringBuilder builder = new StringBuilder();
        try {
            String jsonString;
            inputStream = getResources().openRawResource(R.raw.han);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            while ((jsonString = bufferedReader.readLine()) != null){
                builder.append(jsonString);
            }
        }finally {
            if(inputStream!= null){
                inputStream.close();
            }
        }return  new String(builder);
    }


    private void setupData(List<Recipe> viewItems) {
        ItemAdapter.OnRecipeClickListener onRecipeClickListener = recipe -> {
            Fragment InfoFragment = new InfoFragment();
            FragmentTransaction trans=getFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putParcelable("recipe", recipe);
            InfoFragment.setArguments(args);
            trans.replace(R.id.nav_host_fragment_activity_main, InfoFragment);
            trans.addToBackStack(null);
            trans.commit();
        };
        ItemAdapter adapter = new ItemAdapter(viewItems, getContext(), onRecipeClickListener);


        rv.setAdapter(adapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}