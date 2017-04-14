package com.dwi.mm.sycure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    ArrayList<Message> pesanpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setDummyData();
    }

    @OnClick(R.id.btn_addmsg)
    public void add(){
        startActivity(new Intent(this,AddMessageActivity.class));
    }

    public void setDummyData(){
        pesanpesan = new ArrayList<>();
        pesanpesan.add(new Message("Alexander Sutomo","Lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
        pesanpesan.add(new Message("Jack Budi","Lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
        pesanpesan.add(new Message("Marcus Purwoto","Lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
        pesanpesan.add(new Message("Dadang Ackerman","Lorem ipsum dolor sit amet, consectetur adipiscing elit,"));
        recyclerView.setAdapter(new MessageAdapter());
    }

    private class Message {
        String nama;
        String pesanl;

        public Message(String nama, String pesanl) {
            this.nama = nama;
            this.pesanl = pesanl;
        }
    }

    class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.orang.setText(pesanpesan.get(position).nama);
            holder.pesan.setText(pesanpesan.get(position).pesanl);

        }

        @Override
        public int getItemCount() {
            return pesanpesan.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.orang)
            TextView orang;
            @BindView(R.id.pesan)
            TextView pesan;

            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }

}
