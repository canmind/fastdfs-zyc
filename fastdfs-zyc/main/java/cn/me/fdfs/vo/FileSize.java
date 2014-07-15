package cn.me.fdfs.vo;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-9-3
 * Time: 下午11:50
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class FileSize {
    private String groupName;
    private int miniSmall;//微小型0~30k
    private int small;//小型
    private int middle;//中型
    private int large;//大型

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getMiniSmall() {
        return miniSmall;
    }

    public void setMiniSmall(int miniSmall) {
        this.miniSmall = miniSmall;
    }

    public int getSmall() {
        return small;
    }

    public void setSmall(int small) {
        this.small = small;
    }

    public int getMiddle() {
        return middle;
    }

    public void setMiddle(int middle) {
        this.middle = middle;
    }

    public int getLarge() {
        return large;
    }

    public void setLarge(int large) {
        this.large = large;
    }
}
