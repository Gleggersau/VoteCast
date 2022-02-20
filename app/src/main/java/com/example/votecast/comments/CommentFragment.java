package com.example.votecast.comments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.example.votecast.R;
import com.example.votecast.SessionManager;
import com.example.votecast.databinding.FragmentCommentBinding;
import com.example.votecast.models.Comment;
import com.example.votecast.models.Post;
import com.example.votecast.z_demo.Demo_contents;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CommentFragment extends BottomSheetDialogFragment {


    FragmentCommentBinding binding;
    CommentAdapter commentAdapter = new CommentAdapter();
    SessionManager sessionManager;
    public CommentFragment() {
        // Required empty public constructor
    }

     Post post;

    public CommentFragment(Post post) {

        this.post = post;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment, container, false);
        sessionManager = new SessionManager(getActivity());
        initMain();
        return binding.getRoot();
    }

    private void initMain() {


        binding.rvComments.setAdapter(commentAdapter);
        commentAdapter.addData(Demo_contents.getComments());
        binding.tvCommentCount.setText(String.valueOf(commentAdapter.getItemCount()));

        binding.btnsend.setOnClickListener(v -> {
            String comment = binding.etComment.getText().toString();
            if (!comment.isEmpty()) {
                Comment comment1 = new Comment(sessionManager.getUser(), "Just Now", comment);
                commentAdapter.addSingleComment(comment1);
                binding.rvComments.scrollToPosition(commentAdapter.getItemCount() - 1);
                binding.etComment.setText("");
            }
        });
    }
}