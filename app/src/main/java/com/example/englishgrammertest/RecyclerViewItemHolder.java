package com.example.englishgrammertest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private TextView question = null;

    private TextView answer = null;

    public RecyclerViewItemHolder(View itemView) {
        super(itemView);

        if(itemView != null)
        {
            question = (TextView)itemView.findViewById(R.id.card_view_question);

            answer = (TextView) itemView.findViewById(R.id.card_view_answer);
        }
    }

    public TextView getQuestion() {
        return question;
    }

    public TextView getAnswer() {
        return answer;
    }
}

