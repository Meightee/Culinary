package com.test.culinary.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.test.culinary.R;
import com.test.culinary.databinding.FragmentInfoBinding;
import com.test.culinary.model.Recipe;

public class InfoFragment extends Fragment {
    public InfoFragment(){}
    private FragmentInfoBinding binding;
    public Recipe mew;
    ImageButton playButton;
    ImageButton likeButton;
    SharedPreferences sharedPreferences;

    @SuppressLint("InflateParams")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mew = getArguments().getParcelable("recipe");
        binding = FragmentInfoBinding.inflate(inflater, container, false);

        return inflater.inflate(R.layout.fragment_info,null);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Fragment frag = getFragmentManager().findFragmentById(R.id.infoFragment);

        ((TextView) view.findViewById(R.id.titleView)).setText(mew.getTitle());
        ((TextView) view.findViewById(R.id.textTime)).setText(mew.getTime());
        ((TextView) view.findViewById(R.id.textAmount)).setText(mew.getAmount());
        ((TextView) view.findViewById(R.id.textComplexity)).setText(mew.getComplexity());
        ((TextView) view.findViewById(R.id.textMultiLine)).setText(mew.getProducts());

        ImageView imageView = view.findViewById(R.id.imageView);
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(view).load(mew.getImage()).apply(options).into(imageView);

        playButton = (ImageButton) view.findViewById(R.id.playButton);
        playButton.setOnClickListener(oMyButton);
        likeButton = (ImageButton) view.findViewById(R.id.likeButton);
        sharedPreferences= getActivity().getSharedPreferences("Like", Context.MODE_PRIVATE);

        if (likeNotLike(mew))
            likeButton.setImageResource(R.drawable.ic_favorite);
        else{
            // возвращаем первую картинку
            likeButton.setImageResource(R.drawable.ic_not_favorite);
        }

        likeButton.setOnClickListener(oMyButton);
    }

    View.OnClickListener oMyButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.playButton) {
                Fragment InstructionFragment = new InstructionFragment();
                FragmentTransaction trans = getFragmentManager().beginTransaction();
                Bundle args = new Bundle();
                args.putParcelable("recipe", mew);
                InstructionFragment.setArguments(args);
                trans.replace(R.id.nav_host_fragment_activity_main, InstructionFragment);
                trans.addToBackStack(null);
                trans.commit();
            } else {
                switch (mew.getId()) {
                    case(0):{
                    if (sharedPreferences.getBoolean("0", true))
                        {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("0", false);
                        editor.apply();
                        likeButton.setImageResource(R.drawable.ic_not_favorite);
                    } else {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("0", true);
                        editor.apply();
                        likeButton.setImageResource(R.drawable.ic_favorite);
                    }
                    break;
                    }

                    case(1):{
                        if (sharedPreferences.getBoolean("1", true))  {

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("1", false);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_not_favorite);
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("1", true);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_favorite);
                        }
                        break;
                    }
                    case(2):{
                        if (sharedPreferences.getBoolean("2", true)) {

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("2", false);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_not_favorite);
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("2", true);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_favorite);
                        }
                        break;
                    }
                    case(3):{
                        if (sharedPreferences.getBoolean("3", true)) {

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("3", false);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_not_favorite);
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("3", true);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_favorite);
                        }
                        break;
                    }
                    case(4):{
                        if (sharedPreferences.getBoolean("4", true)){

                            SharedPreferences.Editor editor =sharedPreferences.edit();
                            editor.putBoolean("4", false);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_not_favorite);
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("4", true);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_favorite);
                        }
                        break;
                    }
                    case(5):{
                        if (sharedPreferences.getBoolean("5", true)){

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("5", false);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_not_favorite);
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("5", true);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_favorite);
                        }
                        break;
                    }
                    case(6):{
                        if (sharedPreferences.getBoolean("6", true)){

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("6 ", false);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_not_favorite);
                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("6", true);
                            editor.apply();
                            likeButton.setImageResource(R.drawable.ic_favorite);
                        }
                        break;
                    }

                }
            }
        }
    };

    public boolean likeNotLike(Recipe mew){
        switch (mew.getId()) {
            case (0): {
                return sharedPreferences.getBoolean("0", false);
            }
            case (1): {
                return sharedPreferences.getBoolean("1", false);
            }
            case (2): {
                return sharedPreferences.getBoolean("2", false);
            }
            case (3): {
                return sharedPreferences.getBoolean("3", false);
            }
            case (4): {
                return sharedPreferences.getBoolean("4", false);
            }
            case (5): {
                return sharedPreferences.getBoolean("5", false);
            }
            case (6): {
                return sharedPreferences.getBoolean("6", false);
            }
            default: return  false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}