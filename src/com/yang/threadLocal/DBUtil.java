// package com.yang.threadLocal;
//
// import java.io.IOException;
// import java.io.InputStream;
// import java.sql.SQLException;
// import java.util.Properties;
//
// public class DBUtil {
//     //数据库连接池
//     private static BasicDataSource source;
//
//     //为不同的线程管理连接
//     private static ThreadLocal<Connection> local;
//
//
//     static {
//         try {
//             //加载配置文件
//             Properties properties = new Properties();
//
//             //获取读取流
//             InputStream stream = DBUtil.class.getClassLoader().getResourceAsStream("连接池/config.properties");
//
//             //从配置文件中读取数据
//             properties.load(stream);
//
//             //关闭流
//             stream.close();
//
//             //初始化连接池
//             source = new BasicDataSource();
//
//             //设置驱动
//             source.setDriverClassName(properties.getProperty("driver"));
//
//             //设置url
//             source.setUrl(properties.getProperty("url"));
//
//             //设置用户名
//             source.setUsername(properties.getProperty("user"));
//
//             //设置密码
//             source.setPassword(properties.getProperty("pwd"));
//
//             //设置初始连接数量
//             source.setInitialSize(Integer.parseInt(properties.getProperty("initsize")));
//
//             //设置最大的连接数量
//             source.setMaxActive(Integer.parseInt(properties.getProperty("maxactive")));
//
//             //设置最长的等待时间
//             source.setMaxWait(Integer.parseInt(properties.getProperty("maxwait")));
//
//             //设置最小空闲数
//             source.setMinIdle(Integer.parseInt(properties.getProperty("minidle")));
//
//             //初始化线程本地
//             local = new ThreadLocal<>();
//
//
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//
//     public static Connection getConnection() throws SQLException {
//         if(local.get()!=null){
//             return local.get();
//         }else{
//
//           //获取Connection对象
//           Connection connection = source.getConnection();
//
//           //把Connection放进ThreadLocal里面
//           local.set(connection);
//
//           //返回Connection对象
//           return connection;
//        }
//     }
//
//     //关闭数据库连接
//     public static void closeConnection() {
//         //从线程中拿到Connection对象
//         Connection connection = local.get();
//
//         try {
//             if (connection != null) {
//                 //恢复连接为自动提交
//                 connection.setAutoCommit(true);
//
//                 //这里不是真的把连接关了,只是将该连接归还给连接池
//                 connection.close();
//
//                 //既然连接已经归还给连接池了,ThreadLocal保存的Connction对象也已经没用了
//                 local.remove();
//
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         // finally 这里也需要关闭 connection 和 remove  local
//
//     }
// }