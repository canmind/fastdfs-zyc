package cn.me.fdfs.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-27
 * Time: 下午9:03
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbwarningdata")
public class WarningData implements Serializable {
    private String id;
    private long wdFreeMB;  //free disk storage in MB
    private String wdCpu;
    private float wdMem;
    private String wdIpAddr;
    private String wdGroupName;

    public WarningData() {
    }

    public WarningData(String id, long wdFreeMB, String wdCpu, float wdMem, String wdIpAddr, String wdGroupName) {
        this.id = id;
        this.wdFreeMB = wdFreeMB;
        this.wdCpu = wdCpu;
        this.wdMem = wdMem;
        this.wdIpAddr = wdIpAddr;
        this.wdGroupName = wdGroupName;
    }
    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getWdFreeMB() {
        return wdFreeMB;
    }

    public void setWdFreeMB(long wdFreeMB) {
        this.wdFreeMB = wdFreeMB;
    }

    public String getWdCpu() {
        return wdCpu;
    }

    public void setWdCpu(String wdCpu) {
        this.wdCpu = wdCpu;
    }

    public float getWdMem() {
        return wdMem;
    }

    public void setWdMem(float wdMem) {
        this.wdMem = wdMem;
    }

    public String getWdIpAddr() {
        return wdIpAddr;
    }

    public void setWdIpAddr(String wdIpAddr) {
        this.wdIpAddr = wdIpAddr;
    }

    public String getWdGroupName() {
        return wdGroupName;
    }

    public void setWdGroupName(String wdGroupName) {
        this.wdGroupName = wdGroupName;
    }
}
