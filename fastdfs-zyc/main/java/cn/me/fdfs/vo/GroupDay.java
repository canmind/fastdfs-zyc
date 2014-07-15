package cn.me.fdfs.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-21
 * Time: 上午10:57
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbgroupday")
public class GroupDay  {
    private String id;
    private String groupName;  //name of this group
    private long freeMB;       //free disk space in MB
    private long trunkFreeMB;  //trunk free space in MB
    private int storageCount;  //storage server count
    private int storagePort;   //storage server port
    private int storageHttpPort; //storage server HTTP port
    private int activeCount;     //active storage server count
    private int currentWriteServer; //current storage server index to upload file
    private int storePathCount;     //store base path count of each storage server
    private int subdirCountPerPath; //sub dir count per store path
    private int currentTrunkFileId; //current trunk file id

    private List<StorageDay> storageList = new ArrayList<StorageDay>();

    private Date created;

    @Id
    @GeneratedValue(generator = "system_uuid")
    @GenericGenerator(name = "system_uuid", strategy = "uuid")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getFreeMB() {
        return freeMB;
    }

    public void setFreeMB(long freeMB) {
        this.freeMB = freeMB;
    }

    public long getTrunkFreeMB() {
        return trunkFreeMB;
    }

    public void setTrunkFreeMB(long trunkFreeMB) {
        this.trunkFreeMB = trunkFreeMB;
    }

    public int getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(int storageCount) {
        this.storageCount = storageCount;
    }

    public int getStoragePort() {
        return storagePort;
    }

    public void setStoragePort(int storagePort) {
        this.storagePort = storagePort;
    }

    public int getStorageHttpPort() {
        return storageHttpPort;
    }

    public void setStorageHttpPort(int storageHttpPort) {
        this.storageHttpPort = storageHttpPort;
    }

    public int getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(int activeCount) {
        this.activeCount = activeCount;
    }

    public int getCurrentWriteServer() {
        return currentWriteServer;
    }

    public void setCurrentWriteServer(int currentWriteServer) {
        this.currentWriteServer = currentWriteServer;
    }

    public int getStorePathCount() {
        return storePathCount;
    }

    public void setStorePathCount(int storePathCount) {
        this.storePathCount = storePathCount;
    }

    public int getSubdirCountPerPath() {
        return subdirCountPerPath;
    }

    public void setSubdirCountPerPath(int subdirCountPerPath) {
        this.subdirCountPerPath = subdirCountPerPath;
    }

    public int getCurrentTrunkFileId() {
        return currentTrunkFileId;
    }

    public void setCurrentTrunkFileId(int currentTrunkFileId) {
        this.currentTrunkFileId = currentTrunkFileId;
    }

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public List<StorageDay> getStorageList() {
        return storageList;
    }

    public void setStorageList(List<StorageDay> storageList) {
        this.storageList = storageList;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
