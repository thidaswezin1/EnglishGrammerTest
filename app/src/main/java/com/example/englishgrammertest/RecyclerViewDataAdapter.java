package com.example.englishgrammertest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class RecyclerViewDataAdapter  extends RecyclerView.Adapter<RecyclerViewItemHolder> {

    private List<QuestionAndAnswer> questionAndAnswers;

    public RecyclerViewDataAdapter(List<QuestionAndAnswer> questionAndAnswer) {
        this.questionAndAnswers = questionAndAnswer;
    }

    @Override
    public RecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        View quesAnsView = layoutInflater.inflate(R.layout.activity_card_view_item, parent, false);


        final TextView question = (TextView)quesAnsView.findViewById(R.id.card_view_question);

        final TextView answer = (TextView)quesAnsView.findViewById(R.id.card_view_answer);



        // Create and return our custom Car Recycler View Item Holder object.
        RecyclerViewItemHolder ret = new RecyclerViewItemHolder(quesAnsView);
        return ret;
    }

    @Override
    public void onBindViewHolder(RecyclerViewItemHolder holder, int position) {
        if(questionAndAnswers!=null) {
            // Get car item dto in list.
            QuestionAndAnswer questionAndAnswer = questionAndAnswers.get(position);

            if(questionAndAnswer != null) {
                // Set car item title.
                holder.getQuestion().setText(questionAndAnswer.getQuestion_name());
                // Set car image resource id.
                holder.getAnswer().setText(questionAndAnswer.getCorrect_answer());
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(questionAndAnswers!=null)
        {
            ret = questionAndAnswers.size();
        }
        return ret;
    }
}
