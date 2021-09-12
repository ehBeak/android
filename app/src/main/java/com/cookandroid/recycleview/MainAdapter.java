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
import java.util.Collection;
import java.util.Collections;

/* 메인 어댑터 구현하기 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> implements ItemTouchHelperListener{

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
        holder.iv_icon.setImageResource(arrayList.get(position).getIv_icon());
        holder.tv_taskname.setText(arrayList.get(position).getTv_taskname());

        // 리스트 뷰가 클릭이 되었을때, 롱 클릭이 되었을 때
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String curName = holder.tv_taskname.getText().toString();
                Toast.makeText(view.getContext(), curName,Toast.LENGTH_SHORT).show();
            }
        });

        // 롱클릭을 눌렀을 때 리스트 뷰가 삭제되는 것
        /*holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                remove(holder.getAbsoluteAdapterPosition()); // getAdapterPosition
                return true;
            }
        });*/
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

    public void setItems(ArrayList<MainData> itemList) {
        arrayList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        MainData item = arrayList.get(from_position);
        arrayList.remove(from_position);

        arrayList.add(to_position,item);
        item.setNumber(to_position);
        notifyItemMoved(from_position,to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {

        arrayList.remove(position);
        notifyItemRemoved(position);
    }


    // item설정한 xml을 가지고 있는 홀더
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView iv_icon;
        protected TextView tv_taskname;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            this.tv_taskname = (TextView) itemView.findViewById(R.id.tv_taskname);
        }


    }
}
