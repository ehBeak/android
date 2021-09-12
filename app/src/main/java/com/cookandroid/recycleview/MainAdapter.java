package com.cookandroid.recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/* 메인 어댑터 구현하기 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    // item들을 집어 넣을 mainData 넣기
    private ArrayList<MainData> arrayList;


    public MainAdapter(ArrayList<MainData> arrayList) {
        this.arrayList = arrayList;
    }

    // 리스트 뷰 메뉴가 처음으로 생성될 때의 생명주기
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 처음 뷰가 생성될 때 item_list xml파일을 보여준다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    // 실제 추가 될 때에 대한 생명주기
    @Override
    public void onBindViewHolder(@NonNull MainAdapter.CustomViewHolder holder, int position) {

        // 아까 홀더에서 이미지 리소스를 가져와라, 해당 position에서..
        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile());
        holder.tv_name.setText(arrayList.get(position).getTv_name());
        holder.tv_content.setText(arrayList.get(position).getTv_content());

        // 리스트 뷰가 클릭이 되었을때, 롱 클릭이 되었을 때
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_name.getText().toString();
                Toast.makeText(view.getContext(), curName,Toast.LENGTH_SHORT).show();
            }
        });

        // 롱클릭을 눌렀을 때 리스트 뷰가 삭제되는 것
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAbsoluteAdapterPosition()); // getAdapterPosition
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    // 삭제 시키는 함수
    public void remove(int position) {
        try {
             arrayList.remove(position);
             notifyItemRemoved(position); // arrayList의 내용이 viewholder에 업데이트
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    // item설정한 xml을 가지고 있는 홀더
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_profile = (ImageView) itemView.findViewById(R.id.iv_profile);
            this.tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            this.tv_content = (TextView) itemView.findViewById(R.id.tv_content);
        }


    }
}