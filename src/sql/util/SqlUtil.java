package sql.util;


import sql.model.ServerInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by wanghaizhou on 2014/7/3.
 */
public interface SqlUtil {
//    public List<Map<String,Object>> select(String sql, List<ServerInfo> serverInfo);
    public List<Map<String,Object>> select(String sql, ServerInfo serverInfo);
}
