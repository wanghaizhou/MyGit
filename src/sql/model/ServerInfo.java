package sql.model;

/**
 * Created by wanghaizhou on 2014/7/3.
 */
public class ServerInfo {
    private  int sid;
    private  String serverip;
    private  String username;
    private  String password;
//    端口忽略

    public ServerInfo(int sid, String serverip) {
        this.sid = sid;
        this.serverip = serverip;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getServerip() {
        return serverip;
    }

    public void setServerip(String serverip) {
        this.serverip = serverip;
    }
}
