package cn.me.fdfs.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-27
 * Time: 上午9:19
 * To change this template use File | Settings | File Templates.
 */
public class Line {
    private String name;

    private List<Object[]> data = new ArrayList<Object[]>();

    public Line(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Object[]> getData() {
        return data;
    }

    public void setData(List<Object[]> data) {
        this.data = data;
    }

}
