package com.cookandroid.recycleview;

/* item으로 만든 xml의 정보를 이 클래스에 담기 */
public class MainData {

    private int iv_icon;
    private String tv_taskname;


    public MainData(int iv_icon, String tv_taskname) {
        this.iv_icon = iv_icon;
        this.tv_taskname = tv_taskname;
    }

    public int getIv_icon() {
        return iv_icon;
    }

    public void setIv_icon(int iv_icon) {
        this.iv_icon = iv_icon;
    }

    public String getTv_taskname() {
        return tv_taskname;
    }

    public void setTv_taskname(String tv_taskname) {
        this.tv_taskname = tv_taskname;
    }
}
