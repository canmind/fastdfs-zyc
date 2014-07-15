package cn.me.fdfs.vo;

/**
 * Created with IntelliJ IDEA.
 * User: devuser
 * Date: 12-8-23
 * Time: 下午2:38
 * To change this template use File | Settings | File Templates.
 */
public class Machine {
    private String ip;
    private int port;
    private String username;
    private String password;
    private String logpath;
    private String ssh;
    private boolean configType;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public String getSsh() {
        return ssh;
    }

    public void setSsh(String ssh) {
        this.ssh = ssh;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;

    }

    public String getLogpath() {
        return logpath;
    }

    public boolean isConfigType() {
        return configType;
    }

    public void setConfigType(boolean configType) {
        this.configType = configType;
    }

    public void setLogpath(String logpath) {
        this.logpath = logpath;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
