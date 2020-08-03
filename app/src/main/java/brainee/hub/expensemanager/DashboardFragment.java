package brainee.hub.expensemanager;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DashboardFragment extends Fragment {

    //Flaoting btn
    private FloatingActionButton income_btn;
    private FloatingActionButton expense_btn;
    private FloatingActionButton main_btn;

    //TextViews
    private TextView income_txt, expense_txt;

    //isOpen
    private Boolean isOpen = false;

    private Animation fadeOpen, fadeClose;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        main_btn = view.findViewById(R.id.main_ft_plus_btn);
        expense_btn = view.findViewById(R.id.expense_ft_btn);
        income_btn = view.findViewById(R.id.income_ft_btn);

        expense_txt = view.findViewById(R.id.expense_ft_text);
        income_txt = view.findViewById(R.id.income_ft_text);

        fadeClose = AnimationUtils.loadAnimation(getContext(), R.anim.fade_close);
        fadeOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fade_open);

        main_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    income_btn.startAnimation(fadeClose);
                    expense_btn.startAnimation(fadeClose);
                    income_btn.setClickable(false);
                    expense_btn.setClickable(false);

                    income_txt.startAnimation(fadeClose);
                    expense_txt.startAnimation(fadeClose);
                    income_txt.setClickable(false);
                    expense_txt.setClickable(false);
                    isOpen = false;

                }else {
                    income_btn.startAnimation(fadeOpen);
                    expense_btn.startAnimation(fadeOpen);
                    income_btn.setClickable(true);
                    expense_btn.setClickable(true);

                    income_txt.startAnimation(fadeOpen);
                    expense_txt.startAnimation(fadeOpen);
                    income_txt.setClickable(true);
                    expense_txt.setClickable(true);
                    isOpen = true;
                }
            }
        });
    }
}