package brainee.hub.expensemanager;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

import brainee.hub.expensemanager.Model.Data;


public class DashboardFragment extends Fragment {

    //Floating btn
    private FloatingActionButton income_btn;
    private FloatingActionButton expense_btn;
    private FloatingActionButton main_btn;

    //TextViews
    private TextView income_txt, expense_txt;

    //isOpen
    private Boolean isOpen = false;

    private Animation fadeOpen, fadeClose;

    //Firebase
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore incomeDb, expenseDb;


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

        firebaseAuth = FirebaseAuth.getInstance();
        incomeDb = FirebaseFirestore.getInstance();
        expenseDb = FirebaseFirestore.getInstance();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

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

                addData();
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

                } else {
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

    private void addData() {
        income_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                incomeDataInsert();
            }
        });

        expense_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expenseDataInsert();
            }
        });
    }

    private void expenseDataInsert(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_layout_data_insert, null);
        mydialog.setView(view);
        final AlertDialog dialog = mydialog.create();

        final EditText amount_editText = view.findViewById(R.id.edit_txt_amount);
        final EditText type_editText = view.findViewById(R.id.edit_txt_type);
        final EditText note_editText = view.findViewById(R.id.edit_txt_note);

        Button btn_save = view.findViewById(R.id.btn_save);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = type_editText.getText().toString().trim();
                String note = note_editText.getText().toString().trim();
                String amount = amount_editText.getText().toString().trim();

                if (TextUtils.isEmpty(type)) {
                    type_editText.setError("Field required...");
                    return;
                }
                if (TextUtils.isEmpty(note)) {
                    note_editText.setError("Field required...");
                    return;
                }
                if (TextUtils.isEmpty(amount)) {
                    amount_editText.setError("Field required...");
                }

                int ourAmount = Integer.parseInt(amount);
                String uid = firebaseAuth.getUid();

                //Random random = new Random();
                //String id = String.valueOf(random.nextInt());
                String date = DateFormat.getDateInstance().format(new Date());
                Data data = new Data(ourAmount, type, note, uid, date);

                expenseDb.collection("expenseData")
                        .document(type)
                        .set(data)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Data Added...", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                dialog.dismiss();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void incomeDataInsert() {
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.custom_layout_data_insert, null);
        mydialog.setView(view);
        final AlertDialog dialog = mydialog.create();

        final EditText amount_editText = view.findViewById(R.id.edit_txt_amount);
        final EditText type_editText = view.findViewById(R.id.edit_txt_type);
        final EditText note_editText = view.findViewById(R.id.edit_txt_note);

        Button btn_save = view.findViewById(R.id.btn_save);
        Button btn_cancel = view.findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = type_editText.getText().toString().trim();
                String note = note_editText.getText().toString().trim();
                String amount = amount_editText.getText().toString().trim();

                if (TextUtils.isEmpty(type)) {
                    type_editText.setError("Field required...");
                    return;
                }
                if (TextUtils.isEmpty(note)) {
                    note_editText.setError("Field required...");
                    return;
                }
                if (TextUtils.isEmpty(amount)) {
                    amount_editText.setError("Field required...");
                }

                int ourAmount = Integer.parseInt(amount);
                String uid = firebaseAuth.getUid();

                Random random = new Random();
                String id = String.valueOf(random.nextInt());
                String date = DateFormat.getDateInstance().format(new Date());
                Data data = new Data(ourAmount, type, note, uid, date);

                incomeDb.collection("incomeData")
                        .document(type)
                        .set(data)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Data Added...", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getContext(), "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                dialog.dismiss();

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}