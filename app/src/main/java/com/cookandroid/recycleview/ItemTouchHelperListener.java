package com.cookandroid.recycleview;

public interface ItemTouchHelperListener {
    // item의 현재 위치와 이동위치를 입력받아 item list에 위치를 수정하도록 구현
    boolean onItemMove(int from_position, int to_position);
    // item의 position값을 받아 해당 item을 swipe할 때 로직을 구현
    void onItemSwipe(int position);
}
