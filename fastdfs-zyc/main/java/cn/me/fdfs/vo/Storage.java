package cn.me.fdfs.vo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-20
 * Time: 下午9:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tbstorage")
public class Storage {
    private String id;
    private String curStatus;
    private String ipAddr;
    private String srcIpAddr;
    private String domainName; //http domain name
    private String version;
    private long totalMB;  //total disk storage in MB
    private long freeMB;  //free disk storage in MB
    private int uploadPriority;  //upload priority
    private Date joinTime; //storage join timestamp (create timestamp)
    private Date upTime;   //storage service started timestamp
    private int storePathCount;  //store base path count of each storage server
    private int subdirCountPerPath;
    private int storagePort;
    private int storageHttpPort; //storage http server port
    private int currentWritePath; //current write path index
    private long totalUploadCount;
    private long successUploadCount;
    private long totalAppendCount;
    private long successAppendCount;
    private long totalModifyCount;
    private long successModifyCount;
    private long totalTruncateCount;
    private long successTruncateCount;
    private long totalSetMetaCount;
    private long successSetMetaCount;
    private long totalDeleteCount;
    private long successDeleteCount;
    private long totalDownloadCount;
    private long successDownloadCount;
    private long totalGetMetaCount;
    private long successGetMetaCount;
    private long totalCreateLinkCount;
    private long successCreateLinkCount;
    private long totalDeleteLinkCount;
    private long successDeleteLinkCount;
    private long totalUploadBytes;
    private long successUploadBytes;
    private long totalAppendBytes;
    private long successAppendBytes;
    private long totalModifyBytes;
    private long successModifyBytes;
    private long totalDownloadloadBytes;
    private long successDownloadloadBytes;
    private long totalSyncInBytes;
    private long successSyncInBytes;
    private long totalSyncOutBytes;
    private long successSyncOutBytes;
    private long totalFileOpenCount;
    private long successFileOpenCount;
    private long totalFileReadCount;
    private long successFileReadCount;
    private long totalFileWriteCount;
    private long successFileWriteCount;
    private Date lastSourceUpdate;
    private Date lastSyncUpdate;
    private Date lastSyncedTimestamp;
    private Date lastHeartBeatTime;
    private boolean ifTrunkServer;
    private String cpu;
    private float mem;
    private Group group;
    private String groupName;
    private Date created;

    
    public Storage() {
		super();
	}
    
	public Storage(String id, String curStatus, String ipAddr,
			String srcIpAddr, String domainName, String version, long totalMB,
			long freeMB, int uploadPriority, Date joinTime, Date upTime,
			int storePathCount, int subdirCountPerPath, int storagePort,
			int storageHttpPort, int currentWritePath, long totalUploadCount,
			long successUploadCount, long totalAppendCount,
			long successAppendCount, long totalModifyCount,
			long successModifyCount, long totalTruncateCount,
			long successTruncateCount, long totalSetMetaCount,
			long successSetMetaCount, long totalDeleteCount,
			long successDeleteCount, long totalDownloadCount,
			long successDownloadCount, long totalGetMetaCount,
			long successGetMetaCount, long totalCreateLinkCount,
			long successCreateLinkCount, long totalDeleteLinkCount,
			long successDeleteLinkCount, long totalUploadBytes,
			long successUploadBytes, long totalAppendBytes,
			long successAppendBytes, long totalModifyBytes,
			long successModifyBytes, long totalDownloadloadBytes,
			long successDownloadloadBytes, long totalSyncInBytes,
			long successSyncInBytes, long totalSyncOutBytes,
			long successSyncOutBytes, long totalFileOpenCount,
			long successFileOpenCount, long totalFileReadCount,
			long successFileReadCount, long totalFileWriteCount,
			long successFileWriteCount, Date lastSourceUpdate,
			Date lastSyncUpdate, Date lastSyncedTimestamp,
			Date lastHeartBeatTime, boolean ifTrunkServer, String cpu,
			float mem, Group group, Date created) {
		super();
		this.id = id;
		this.curStatus = curStatus;
		this.ipAddr = ipAddr;
		this.srcIpAddr = srcIpAddr;
		this.domainName = domainName;
		this.version = version;
		this.totalMB = totalMB;
		this.freeMB = freeMB;
		this.uploadPriority = uploadPriority;
		this.joinTime = joinTime;
		this.upTime = upTime;
		this.storePathCount = storePathCount;
		this.subdirCountPerPath = subdirCountPerPath;
		this.storagePort = storagePort;
		this.storageHttpPort = storageHttpPort;
		this.currentWritePath = currentWritePath;
		this.totalUploadCount = totalUploadCount;
		this.successUploadCount = successUploadCount;
		this.totalAppendCount = totalAppendCount;
		this.successAppendCount = successAppendCount;
		this.totalModifyCount = totalModifyCount;
		this.successModifyCount = successModifyCount;
		this.totalTruncateCount = totalTruncateCount;
		this.successTruncateCount = successTruncateCount;
		this.totalSetMetaCount = totalSetMetaCount;
		this.successSetMetaCount = successSetMetaCount;
		this.totalDeleteCount = totalDeleteCount;
		this.successDeleteCount = successDeleteCount;
		this.totalDownloadCount = totalDownloadCount;
		this.successDownloadCount = successDownloadCount;
		this.totalGetMetaCount = totalGetMetaCount;
		this.successGetMetaCount = successGetMetaCount;
		this.totalCreateLinkCount = totalCreateLinkCount;
		this.successCreateLinkCount = successCreateLinkCount;
		this.totalDeleteLinkCount = totalDeleteLinkCount;
		this.successDeleteLinkCount = successDeleteLinkCount;
		this.totalUploadBytes = totalUploadBytes;
		this.successUploadBytes = successUploadBytes;
		this.totalAppendBytes = totalAppendBytes;
		this.successAppendBytes = successAppendBytes;
		this.totalModifyBytes = totalModifyBytes;
		this.successModifyBytes = successModifyBytes;
		this.totalDownloadloadBytes = totalDownloadloadBytes;
		this.successDownloadloadBytes = successDownloadloadBytes;
		this.totalSyncInBytes = totalSyncInBytes;
		this.successSyncInBytes = successSyncInBytes;
		this.totalSyncOutBytes = totalSyncOutBytes;
		this.successSyncOutBytes = successSyncOutBytes;
		this.totalFileOpenCount = totalFileOpenCount;
		this.successFileOpenCount = successFileOpenCount;
		this.totalFileReadCount = totalFileReadCount;
		this.successFileReadCount = successFileReadCount;
		this.totalFileWriteCount = totalFileWriteCount;
		this.successFileWriteCount = successFileWriteCount;
		this.lastSourceUpdate = lastSourceUpdate;
		this.lastSyncUpdate = lastSyncUpdate;
		this.lastSyncedTimestamp = lastSyncedTimestamp;
		this.lastHeartBeatTime = lastHeartBeatTime;
		this.ifTrunkServer = ifTrunkServer;
		this.cpu = cpu;
		this.mem = mem;
		this.group = group;
		this.created = created;
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

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public float getMem() {
        return mem;
    }

    public void setMem(float mem) {
        this.mem = mem;
    }

    public String getCurStatus() {
        return curStatus;
    }

    public void setCurStatus(String curStatus) {
        this.curStatus = curStatus;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getSrcIpAddr() {
        return srcIpAddr;
    }

    public void setSrcIpAddr(String srcIpAddr) {
        this.srcIpAddr = srcIpAddr;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTotalMB() {
        return totalMB;
    }

    public void setTotalMB(long totalMB) {
        this.totalMB = totalMB;
    }

    public long getFreeMB() {
        return freeMB;
    }

    public void setFreeMB(long freeMB) {
        this.freeMB = freeMB;
    }

    public int getUploadPriority() {
        return uploadPriority;
    }

    public void setUploadPriority(int uploadPriority) {
        this.uploadPriority = uploadPriority;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
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

    public int getCurrentWritePath() {
        return currentWritePath;
    }

    public void setCurrentWritePath(int currentWritePath) {
        this.currentWritePath = currentWritePath;
    }

    public long getTotalUploadCount() {
        return totalUploadCount;
    }

    public void setTotalUploadCount(long totalUploadCount) {
        this.totalUploadCount = totalUploadCount;
    }

    public long getSuccessUploadCount() {
        return successUploadCount;
    }

    public void setSuccessUploadCount(long successUploadCount) {
        this.successUploadCount = successUploadCount;
    }

    public long getTotalAppendCount() {
        return totalAppendCount;
    }

    public void setTotalAppendCount(long totalAppendCount) {
        this.totalAppendCount = totalAppendCount;
    }

    public long getSuccessAppendCount() {
        return successAppendCount;
    }

    public void setSuccessAppendCount(long successAppendCount) {
        this.successAppendCount = successAppendCount;
    }

    public long getTotalModifyCount() {
        return totalModifyCount;
    }

    public void setTotalModifyCount(long totalModifyCount) {
        this.totalModifyCount = totalModifyCount;
    }

    public long getSuccessModifyCount() {
        return successModifyCount;
    }

    public void setSuccessModifyCount(long successModifyCount) {
        this.successModifyCount = successModifyCount;
    }

    public long getTotalTruncateCount() {
        return totalTruncateCount;
    }

    public void setTotalTruncateCount(long totalTruncateCount) {
        this.totalTruncateCount = totalTruncateCount;
    }

    public long getSuccessTruncateCount() {
        return successTruncateCount;
    }

    public void setSuccessTruncateCount(long successTruncateCount) {
        this.successTruncateCount = successTruncateCount;
    }

    public long getTotalSetMetaCount() {
        return totalSetMetaCount;
    }

    public void setTotalSetMetaCount(long totalSetMetaCount) {
        this.totalSetMetaCount = totalSetMetaCount;
    }

    public long getSuccessSetMetaCount() {
        return successSetMetaCount;
    }

    public void setSuccessSetMetaCount(long successSetMetaCount) {
        this.successSetMetaCount = successSetMetaCount;
    }

    public long getTotalDeleteCount() {
        return totalDeleteCount;
    }

    public void setTotalDeleteCount(long totalDeleteCount) {
        this.totalDeleteCount = totalDeleteCount;
    }

    public long getSuccessDeleteCount() {
        return successDeleteCount;
    }

    public void setSuccessDeleteCount(long successDeleteCount) {
        this.successDeleteCount = successDeleteCount;
    }

    public long getTotalDownloadCount() {
        return totalDownloadCount;
    }

    public void setTotalDownloadCount(long totalDownloadCount) {
        this.totalDownloadCount = totalDownloadCount;
    }

    public long getSuccessDownloadCount() {
        return successDownloadCount;
    }

    public void setSuccessDownloadCount(long successDownloadCount) {
        this.successDownloadCount = successDownloadCount;
    }

    public long getTotalGetMetaCount() {
        return totalGetMetaCount;
    }

    public void setTotalGetMetaCount(long totalGetMetaCount) {
        this.totalGetMetaCount = totalGetMetaCount;
    }

    public long getSuccessGetMetaCount() {
        return successGetMetaCount;
    }

    public void setSuccessGetMetaCount(long successGetMetaCount) {
        this.successGetMetaCount = successGetMetaCount;
    }

    public long getTotalCreateLinkCount() {
        return totalCreateLinkCount;
    }

    public void setTotalCreateLinkCount(long totalCreateLinkCount) {
        this.totalCreateLinkCount = totalCreateLinkCount;
    }

    public long getSuccessCreateLinkCount() {
        return successCreateLinkCount;
    }

    public void setSuccessCreateLinkCount(long successCreateLinkCount) {
        this.successCreateLinkCount = successCreateLinkCount;
    }

    public long getTotalDeleteLinkCount() {
        return totalDeleteLinkCount;
    }

    public void setTotalDeleteLinkCount(long totalDeleteLinkCount) {
        this.totalDeleteLinkCount = totalDeleteLinkCount;
    }

    public long getSuccessDeleteLinkCount() {
        return successDeleteLinkCount;
    }

    public void setSuccessDeleteLinkCount(long successDeleteLinkCount) {
        this.successDeleteLinkCount = successDeleteLinkCount;
    }

    public long getTotalUploadBytes() {
        return totalUploadBytes;
    }

    public void setTotalUploadBytes(long totalUploadBytes) {
        this.totalUploadBytes = totalUploadBytes;
    }

    public long getSuccessUploadBytes() {
        return successUploadBytes;
    }

    public void setSuccessUploadBytes(long successUploadBytes) {
        this.successUploadBytes = successUploadBytes;
    }

    public long getTotalAppendBytes() {
        return totalAppendBytes;
    }

    public void setTotalAppendBytes(long totalAppendBytes) {
        this.totalAppendBytes = totalAppendBytes;
    }

    public long getSuccessAppendBytes() {
        return successAppendBytes;
    }

    public void setSuccessAppendBytes(long successAppendBytes) {
        this.successAppendBytes = successAppendBytes;
    }

    public long getTotalModifyBytes() {
        return totalModifyBytes;
    }

    public void setTotalModifyBytes(long totalModifyBytes) {
        this.totalModifyBytes = totalModifyBytes;
    }

    public long getSuccessModifyBytes() {
        return successModifyBytes;
    }

    public void setSuccessModifyBytes(long successModifyBytes) {
        this.successModifyBytes = successModifyBytes;
    }

    public long getTotalDownloadloadBytes() {
        return totalDownloadloadBytes;
    }

    public void setTotalDownloadloadBytes(long totalDownloadloadBytes) {
        this.totalDownloadloadBytes = totalDownloadloadBytes;
    }

    public long getSuccessDownloadloadBytes() {
        return successDownloadloadBytes;
    }

    public void setSuccessDownloadloadBytes(long successDownloadloadBytes) {
        this.successDownloadloadBytes = successDownloadloadBytes;
    }

    public long getTotalSyncInBytes() {
        return totalSyncInBytes;
    }

    public void setTotalSyncInBytes(long totalSyncInBytes) {
        this.totalSyncInBytes = totalSyncInBytes;
    }

    public long getSuccessSyncInBytes() {
        return successSyncInBytes;
    }

    public void setSuccessSyncInBytes(long successSyncInBytes) {
        this.successSyncInBytes = successSyncInBytes;
    }

    public long getTotalSyncOutBytes() {
        return totalSyncOutBytes;
    }

    public void setTotalSyncOutBytes(long totalSyncOutBytes) {
        this.totalSyncOutBytes = totalSyncOutBytes;
    }

    public long getSuccessSyncOutBytes() {
        return successSyncOutBytes;
    }

    public void setSuccessSyncOutBytes(long successSyncOutBytes) {
        this.successSyncOutBytes = successSyncOutBytes;
    }

    public long getTotalFileOpenCount() {
        return totalFileOpenCount;
    }

    public void setTotalFileOpenCount(long totalFileOpenCount) {
        this.totalFileOpenCount = totalFileOpenCount;
    }

    public long getSuccessFileOpenCount() {
        return successFileOpenCount;
    }

    public void setSuccessFileOpenCount(long successFileOpenCount) {
        this.successFileOpenCount = successFileOpenCount;
    }

    public long getTotalFileReadCount() {
        return totalFileReadCount;
    }

    public void setTotalFileReadCount(long totalFileReadCount) {
        this.totalFileReadCount = totalFileReadCount;
    }

    public long getSuccessFileReadCount() {
        return successFileReadCount;
    }

    public void setSuccessFileReadCount(long successFileReadCount) {
        this.successFileReadCount = successFileReadCount;
    }

    public long getTotalFileWriteCount() {
        return totalFileWriteCount;
    }

    public void setTotalFileWriteCount(long totalFileWriteCount) {
        this.totalFileWriteCount = totalFileWriteCount;
    }

    public long getSuccessFileWriteCount() {
        return successFileWriteCount;
    }

    public void setSuccessFileWriteCount(long successFileWriteCount) {
        this.successFileWriteCount = successFileWriteCount;
    }

    public Date getLastSourceUpdate() {
        return lastSourceUpdate;
    }

    public void setLastSourceUpdate(Date lastSourceUpdate) {
        this.lastSourceUpdate = lastSourceUpdate;
    }

    public Date getLastSyncUpdate() {
        return lastSyncUpdate;
    }

    public void setLastSyncUpdate(Date lastSyncUpdate) {
        this.lastSyncUpdate = lastSyncUpdate;
    }

    public Date getLastSyncedTimestamp() {
        return lastSyncedTimestamp;
    }

    public void setLastSyncedTimestamp(Date lastSyncedTimestamp) {
        this.lastSyncedTimestamp = lastSyncedTimestamp;
    }

    public Date getLastHeartBeatTime() {
        return lastHeartBeatTime;
    }

    public void setLastHeartBeatTime(Date lastHeartBeatTime) {
        this.lastHeartBeatTime = lastHeartBeatTime;
    }

    public boolean isIfTrunkServer() {
        return ifTrunkServer;
    }

    public void setIfTrunkServer(boolean ifTrunkServer) {
        this.ifTrunkServer = ifTrunkServer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Group getGroup() {
        return group;
    }
    public void setGroup(Group group) {
        this.group = group;
    }
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
