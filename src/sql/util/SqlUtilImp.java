package sql.util;

import sql.model.ServerInfo;

import java.sql.*;
import java.util.*;

/**
 * Created by wanghaizhou on 2014/7/3.
 */


public class SqlUtilImp implements SqlUtil {
    Connection conn = null;//conn用于连接数据库
    PreparedStatement stmt = null;//stmt用于发送sql语句到数据库并执行sql语句
    ResultSet resultSet=null;
    Object object;

//    public List<Map<String, Object>> select(String sql, List<ServerInfo> serverInfos) {
//        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
//        try {
//            for (ServerInfo serverInfo : serverInfos) {
//                String connectionString = "jdbc:mysql://" + serverInfo.getServerip() + ":3306/library?user=root&password=mysql";
//                System.out.println(connectionString);
//                Class.forName("com.mysql.jdbc.Driver").newInstance();
//                conn = DriverManager.getConnection(connectionString);
//                stmt = conn.prepareStatement(sql);
//                ResultSet resultSet = stmt.executeQuery(sql);
//                resultSets.put(serverInfo.getSid(), resultSet);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                stmt.close();
//                conn.close();
//            } catch (Exception e) {
//                System.out.println("连接失败");
//            }
//
//        }
//        return resultSets;
//   }

    //查询语句接口关键方法（访问单个服务器的数据库） 返回List<Map<String, Object>>
    public List<Map<String, Object>> select(String sql, ServerInfo serverInfo) {
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        try {
            String connectionString = "jdbc:mysql://" + serverInfo.getServerip() + ":3306/library?user=root&password=mysql";
            System.out.println(connectionString);
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(connectionString);
            stmt = conn.prepareStatement(sql);
            resultSet= stmt.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<String, Object>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    // 打印列名
//                    System.out.println(metaData.getColumnName(i)+"列  值"+resultSet.getObject(i));
                    row.put(metaData.getColumnName(i), resultSet.getObject(i));
                }
                lists.add(row);
            }
        }catch(Exception e){
                e.printStackTrace();
        }finally{
                try {
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    System.out.println("关闭连接失败");
                    }
                }
            return lists;
    }
    //多个服务器数据查询接口
    public List<Map<String, Object>> selectAll(String sql, List<ServerInfo> serverInfos){
        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        for(ServerInfo serverInfo:serverInfos){
            try {
                String connectionString = "jdbc:mysql://" + serverInfo.getServerip() + ":3306/library?user=root&password=mysql";
                System.out.println(connectionString);
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(connectionString);
                stmt = conn.prepareStatement(sql);
                resultSet= stmt.executeQuery(sql);
                ResultSetMetaData metaData = resultSet.getMetaData();
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<String, Object>();
                    for (int i = 1; i <= metaData.getColumnCount(); i++) {
                        // 打印列名
//                    System.out.println(metaData.getColumnName(i)+"列  值"+resultSet.getObject(i));
                        row.put(metaData.getColumnName(i), resultSet.getObject(i));
                    }
                    lists.add(row);
                }
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                try {
                    resultSet.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    System.out.println("关闭连接失败");
                }
            }
        }
        return lists;
    }



    public static void main(String args[]) {
//        List<ServerInfo> serverInfos = new ArrayList<ServerInfo>();
//        ServerInfo serverInfo = new ServerInfo(1, "localhost");
//        ServerInfo serverInfo2 = new ServerInfo(2, "localhost");
//        serverInfos.add(serverInfo);
//        serverInfos.add(serverInfo2);
//        SqlUtil sqlUtil = new SqlUtilImp();
//        String sql = "select * from user";
//        Map<Integer, ResultSet> resultSets = sqlUtil.select(sql, serverInfos);
//        Iterator iter = resultSets.entrySet().iterator();
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            ResultSet val = (ResultSet) entry.getValue();
//            System.out.println("服务端id:" + key);
//            try {
//                int i = 1;
//                while (val.next()) {
//                    System.out.println("数据" + val.getObject(i));
//                    i++;
//                }
//            } catch (Exception e) {
//            }
//        }
        new SqlUtilImp().test();

    }

    //测试select所用
    public void test(){
        ServerInfo serverInfo = new ServerInfo(1, "localhost");
        String sql = "select * from user";
        List<Map<String, Object>> results=select(sql,serverInfo);
        for (Map<String, Object> map:results){
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                System.out.println(key+"段 值"+val);
            }
        }
    }
    public void run(){

    }

}
