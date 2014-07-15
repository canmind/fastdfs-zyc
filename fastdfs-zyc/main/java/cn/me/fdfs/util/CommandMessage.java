package cn.me.fdfs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tozhangwj
 *
 */
public class CommandMessage implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static enum MessageType {
        OK, ERROR, CLIENT_ERROR
    }
	
	private String cmdString;
	private Map<String,String> parameters; 
	private MessageType type;
	private String message;
	private long executeTime;
	private long executeDate;
	private String requestIP;
    private List<String>  executeLines = new ArrayList<String>();
	

	public String getCmdString() {
		return cmdString;
	}
	public void setCmdString(String cmdString) {
		this.cmdString = cmdString;
	}
	public MessageType getType() {
		return type;
	}
	public void setType(MessageType type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getExecuteTime() {
		return executeTime;
	}

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public void setExecuteTime(long executeTime) {

		this.executeTime = executeTime;
	}

    public List<String> getExecuteLines() {
        return executeLines;
    }

    public void setExecuteLines(List<String> executeLines) {
        this.executeLines = executeLines;
    }

    public long getExecuteDate() {
		return executeDate;
	}
	public void setExecuteDate(long executeDate) {
		this.executeDate = executeDate;
	}
	public String getRequestIP() {
		return requestIP;
	}
	public void setRequestIP(String requestIP) {
		this.requestIP = requestIP;
	}




}
