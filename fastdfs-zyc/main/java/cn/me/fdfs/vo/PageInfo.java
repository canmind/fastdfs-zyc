package cn.me.fdfs.vo;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-9-11
 * Time: 上午11:51
 * To change this template use File | Settings | File Templates.
 */
public class PageInfo {
    private int numPerPage;//每页显示多少条
    private String orderField;//查询排序
    private String orderDirection;//升序or 降序
    private int totalCount;//总数
    private int pageNumShown;//页标个数
    private int pageNum;//当前页数

    public PageInfo() {
    }

    public PageInfo(int numPerPage, String orderField, String orderDirection, int totalCount, int pageNumShown, int pageNum) {
        this.numPerPage = numPerPage;
        this.orderField = orderField;
        this.orderDirection = orderDirection;
        this.totalCount = totalCount;
        this.pageNumShown = pageNumShown;
        this.pageNum = pageNum;
    }

    public int getNumPerPage() {
        if(numPerPage==0){
            numPerPage=20;
        }
        return numPerPage;
    }

    public void setNumPerPage(int numPerPage) {
        this.numPerPage = numPerPage;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageNumShown() {
        return pageNumShown;
    }

    public void setPageNumShown(int pageNumShown) {
        this.pageNumShown = pageNumShown;
    }

    public int getPageNum() {
        if(pageNum==0){
            pageNum=1;
        }
        return pageNum;
    }

    public void setpageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
