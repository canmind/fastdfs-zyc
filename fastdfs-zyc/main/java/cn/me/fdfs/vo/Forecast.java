package cn.me.fdfs.vo;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 12-9-7
 * Time: 上午9:59
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Forecast {
    private String ipAddr; //预测的服务器的ip地址
    private long warning; //报警数据
    private Date timeForForecast; //预测到达报警数据的时间
    private long average; //过去每小时消耗的容量
     private Date now; //当前时间
    private long useHour;//预测还可以用多久
    private long freeMB; //现在还有多少容量
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public long getWarning() {
        return warning;
    }

    public void setWarning(long warning) {
        this.warning = warning;
    }

    public Date getTimeForForecast() {
        return timeForForecast;
    }

    public void setTimeForForecast(Date timeForForecast) {
        this.timeForForecast = timeForForecast;
    }

    public long getAverage() {
        return average;
    }

    public void setAverage(long average) {
        this.average = average;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }

    public long getUseHour() {
        return useHour;
    }

    public void setUseHour(long useHour) {
        this.useHour = useHour;
    }

    public long getFreeMB() {
        return freeMB;
    }

    public void setFreeMB(long freeMB) {
        this.freeMB = freeMB;
    }
}
