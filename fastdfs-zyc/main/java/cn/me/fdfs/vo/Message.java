package cn.me.fdfs.vo;

/**
 * Created with IntelliJ IDEA.
 * User: wanglt
 * Date: 12-8-28
 * Time: 下午10:49
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private String statusCode;
    private String message;
    private String navTabId;
    private String rel;
    private String callbackType;
    private String forwardUrl;
    private String ips;

    public Message() {
    }

    public Message(String ips) {
        this.ips = ips;
    }

    public Message(String statusCode, String message, String navTabId, String rel, String callbackType, String forwardUrl) {
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = navTabId;
        this.rel = rel;
        this.callbackType = callbackType;
        this.forwardUrl = forwardUrl;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNavTabId() {
        return navTabId;
    }

    public void setNavTabId(String navTabId) {
        this.navTabId = navTabId;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getCallbackType() {
        return callbackType;
    }

    public void setCallbackType(String callbackType) {
        this.callbackType = callbackType;
    }

    public String getForwardUrl() {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
}
