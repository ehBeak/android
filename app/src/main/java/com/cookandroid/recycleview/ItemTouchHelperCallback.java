package com.cookandroid.recycleview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperListener listener;

    // ItemTouchHelper.Callback -> 아이템 위치 변경, 스와이프, 롱클릭
    public ItemTouchHelperCallback(ItemTouchHelperListener listener) {
        this.listener = listener;
    }

    // 리사이클러뷰와 리사이클러뷰 홀더를 입력받는다.
    // drag위치와 swipe위치를 ItemTouchHelper에서 받아 셋팅
    // makeMovementFlags메소드로 drag위치와 swipe위치를 입력하여 현재 위치 값을 int로 반환
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        int drag_flags = ItemTouchHelper.UP | ItemTouchHelper.DOWN |ItemTouchHelper.START | ItemTouchHelper.END;
        //int swipe_flags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(drag_flags,0);
    }

    // 리사이클러뷰, viewHolder, target(viewHolder 중 선택된 아이템)을 입력받아 움직임을 감지한다.
    // ItemTouchHelperListner의 onItemMove메소드로 해당 아이템의 움직임을 감지한다.
    // onItemMove 메소드는 아이템이 움직이고 있는 가를 판별하고 boolean값으로 반환한다.
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return listener.onItemMove(viewHolder.getAbsoluteAdapterPosition(), target.getAbsoluteAdapterPosition());
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onItemSwipe(viewHolder.getAbsoluteAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
}
