package com.dorin.smartravel.Activities;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dorin.smartravel.Adapters.QuestionAdapter;
import com.dorin.smartravel.Objects.Question;
import com.dorin.smartravel.Objects.Trip;
import com.dorin.smartravel.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;


public class ViewDialogRating {

    private MaterialTextView dialog_List_LBL_title;
    private MaterialButton dialog_List_BTN_back;
    private RecyclerView dialog_LST_Questions;
    private MaterialButton dialog_BTN_Rate;
    private LinearLayoutManager linearLayoutManager;


    public interface Callback_ViewDialog{
        void timeToRate(Trip trip);
    }

    public void showDialog(Activity activity, Trip trip , ArrayList<Question> list, Callback_ViewDialog callBack_viewDialogItem) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogbox_rating);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        findViews(dialog);
        dialog_List_LBL_title.setText("Rating "+trip.getName());
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        dialog_LST_Questions.setLayoutManager(linearLayoutManager);
        dialog_LST_Questions.setHasFixedSize(true);
        dialog_LST_Questions.setItemAnimator(new DefaultItemAnimator());


        QuestionAdapter adapter_rate = new QuestionAdapter(activity, list);
        dialog_LST_Questions.setAdapter(adapter_rate);


        adapter_rate.setQuestionClickListener(new QuestionAdapter.QuestionClickListener() {
            @Override
            public void rateClicked(int adapterPosition, Question question, int value) {
                question.setAnswerRate(value);
            }
        });



        dialog_List_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog_BTN_Rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trip.setIsRate(true);
                Log.d("trip",trip.getRates().get(0).getAnswerRate()+"");
                dialog.dismiss();
            }
        });



        dialog.show();
    }

    private void findViews(Dialog dialog) {

        dialog_List_LBL_title = dialog.findViewById(R.id.dialog_List_LBL_title);
        dialog_LST_Questions = dialog.findViewById(R.id.dialog_LST_Questions);
        dialog_List_BTN_back = dialog.findViewById(R.id.dialog_List_BTN_back);
        dialog_BTN_Rate = dialog.findViewById(R.id.dialog_BTN_Rate);


    }


}
