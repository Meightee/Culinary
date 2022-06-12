package com.test.culinary.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.test.culinary.R;
import com.test.culinary.databinding.FragmentInstructionBinding;
import com.test.culinary.model.Recipe;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;

/*import ru.yandex.speechkit.Error;
import ru.yandex.speechkit.Language;
import ru.yandex.speechkit.OnlineVocalizer;
import ru.yandex.speechkit.SpeechKit;
import ru.yandex.speechkit.Synthesis;
import ru.yandex.speechkit.Vocalizer;
import ru.yandex.speechkit.VocalizerListener;
import ru.yandex.speechkit.Voice;*/

//implements VocalizerListener добавляй, не добавляй, не робит
public class InstructionFragment extends Fragment {
    //private static final String API_KEY_FOR_TESTS_ONLY = "AQVNzWBlweQNe1vS4g0e_CBfqFLtune41ot8TIzt";

    //private Vocalizer vocalizer;

    public Recipe mew;
    private FragmentInstructionBinding binding;
    ImageButton nextButton;
    ImageButton backButton;
    TextView textView;
    int num = 0;
    List<String> list = new ArrayList<>();

    @SuppressLint("InflateParams")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mew = getArguments().getParcelable("recipe");
        binding = FragmentInstructionBinding.inflate(inflater, container, false);
        /*try {
            SpeechKit.getInstance().init(getContext(), API_KEY_FOR_TESTS_ONLY);
            SpeechKit.getInstance().setUuid(UUID.randomUUID().toString());
        } catch (SpeechKit.LibraryInitializationException ignored) {
        }

        vocalizer = new OnlineVocalizer.Builder(Language.RUSSIAN, InstructionFragment.this)
                .setVoice(Voice.ERMIL)
                .setAutoPlay(true)
                .build();**/

        return inflater.inflate(R.layout.fragment_instruction, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nextButton = view.findViewById(R.id.nextButton);
        backButton = view.findViewById(R.id.backButton);
        JSONArray jsonArray = mew.getActions();
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                try {
                    list.add(jsonArray.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        textView = view.findViewById(R.id.instructionView);
        textView.setText(list.get(0));
        nextButton.setOnClickListener(oMyButton);
        backButton.setOnClickListener(oMyButton);
       /*textView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vocalizer.cancel();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }
        });*/
    }

    View.OnClickListener oMyButton = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.nextButton) {
                num++;
                if (num < list.size()) {
                    //vocalizer.cancel();
                    textView.setText(list.get(num));
                    //vocalizer.synthesize(list.get(num), Vocalizer.TextSynthesizingMode.INTERRUPT);
                } else {
                    infoBack();
                }
            } else {
                num--;
                if (num > 0) {
                    //vocalizer.cancel();
                    textView.setText(list.get(num));
                   // vocalizer.synthesize(list.get(num), Vocalizer.TextSynthesizingMode.INTERRUPT);
                } else {
                   infoBack();
                }
            }
        }
    };
        public void infoBack(){
            Fragment InfoFragment = new InfoFragment();
            FragmentTransaction trans = getFragmentManager().beginTransaction();
            Bundle args = new Bundle();
            args.putParcelable("recipe", mew);
            InfoFragment.setArguments(args);
            trans.replace(R.id.nav_host_fragment_activity_main, InfoFragment);
            trans.addToBackStack(null);
            trans.commit();

        }


        @Override
        public void onDestroyView() {
            super.onDestroyView();
            binding = null;
        }

   /* @Override
    public void onSynthesisDone(@android.support.annotation.NonNull @NonNull Vocalizer vocalizer) {

    }

    @Override
    public void onPartialSynthesis(@android.support.annotation.NonNull @NonNull Vocalizer vocalizer, @android.support.annotation.NonNull @NonNull Synthesis synthesis) {

    }

    @Override
    public void onPlayingBegin(@android.support.annotation.NonNull @NonNull Vocalize vocalizer) {

    }

    @Override
    public void onPlayingDone(@android.support.annotation.NonNull @NonNull Vocalizer vocalizer) {

    }

    @Override
    public void onVocalizerError(@android.support.annotation.NonNull @NonNull Vocalizer vocalizer, @android.support.annotation.NonNull @NonNull Error error) {

    }**/
}

