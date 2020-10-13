package com.elsafty.roomposts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private PostAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private CompositeDisposable mCompositeDisposable;
    private PostsDatabase mPostsDatabase;
    private EditText titleEditText, bodyEditText;
    private Button insertButton, getDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleEditText = findViewById(R.id.editTexttitle);
        bodyEditText = findViewById(R.id.editTextBody);
        insertButton = findViewById(R.id.insertButton);
        getDataButton = findViewById(R.id.getButton);
        mRecyclerView = findViewById(R.id.posts_recyclerView);
        mAdapter = new PostAdapter();
        mCompositeDisposable = new CompositeDisposable();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mPostsDatabase = PostsDatabase.getInstance(this);
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPostsDatabase.postDao().insertPost(new Post(1,new User(1,"Ahmed"), titleEditText.getText().toString(), bodyEditText.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                               Toast.makeText(MainActivity.this, "Post inserted successfully", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });
        getDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPostsDatabase.postDao().getAllPosts()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onSuccess(List<Post> posts) {
                                mAdapter.setList(posts);
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });
            }
        });

    }

}