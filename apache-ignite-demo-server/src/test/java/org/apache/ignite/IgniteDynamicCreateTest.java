package org.apache.ignite;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Properties;

/**
 * Ignite 3.0 动态创建 Schema 和表示例
 */
public class IgniteDynamicCreateTest {

    @Test
    public void createSchemaAndTableTest() {
        // 动态 schema 名，可以用参数传递，也可以自定义
        String schemaName = "test_schema";

        // Ignite 3.0 JDBC 连接信息
        // 注意：Ignite 3.0 使用新的 JDBC URL 格式
        String host = "127.0.0.1";
        int port = 10800;
        String url = "jdbc:ignite:thin://" + host + ":" + port;

        Properties props = new Properties();
        // 在 Ignite 3.0 中设置连接属性
        props.setProperty("distributedJoins", "true");
        props.setProperty("queryTimeout", "60000");
        // 添加连接超时设置
        props.setProperty("socketTimeout", "30000");
        props.setProperty("connectionTimeout", "10000");

        try {
            // 加载 Ignite 3.0 JDBC 驱动
            Class.forName("org.apache.ignite.jdbc.IgniteJdbcDriver");

            // 尝试连接前先检查服务器是否可达
            System.out.println("尝试连接到 Ignite 服务器: " + host + ":" + port);

            try (Connection conn = DriverManager.getConnection(url, props)) {
                System.out.println("成功连接到 Ignite 3.0 集群");

                // 创建 Schema
                createSchema(conn, schemaName);

                // 创建表
                createTable(conn, schemaName);

                // 插入数据
                insertData(conn, schemaName);

                // 查询数据
                queryData(conn, schemaName);

            } catch (SQLException e) {
                System.err.println("SQL 操作异常: " + e.getMessage());
                System.err.println("SQL 状态: " + e.getSQLState());
                System.err.println("错误代码: " + e.getErrorCode());

                // 提供更详细的连接问题诊断
                if (e.getMessage().contains("Channel is closed") ||
                        e.getMessage().contains("Connection refused")) {
                    System.err.println("\n可能的解决方案:");
                    System.err.println("1. 确认 Ignite 服务器在 " + host + ":" + port + " 上运行");
                    System.err.println("2. 检查网络连接和防火墙设置");
                    System.err.println("3. 验证 Ignite 客户端和服务器版本兼容");
                    System.err.println("4. 尝试使用 Ignite 3.0 的正确连接字符串格式");
                    System.err.println("5. 确保 Ignite 服务器配置允许远程客户端连接");
                }

                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.err.println("找不到 Ignite JDBC 驱动: " + e.getMessage());
            System.err.println("\n解决方案: 确保在项目的 pom.xml 或 build.gradle 中添加了正确的 Ignite 3.0 依赖");
            e.printStackTrace();
        }
    }

    private static void createSchema(Connection conn, String schemaName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Ignite 3.0 中创建 schema 的语法
            stmt.executeUpdate("CREATE SCHEMA IF NOT EXISTS " + schemaName);
            System.out.println("Schema '" + schemaName + "' 创建成功或已存在");
        }
    }

    private static void createTable(Connection conn, String schemaName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Ignite 3.0 中创建表的语法
            String createTableSQL =
                    "CREATE TABLE IF NOT EXISTS " + schemaName + ".user_info (" +
                            "id INT PRIMARY KEY, " +
                            "name VARCHAR, " +
                            "age INT, " +
                            "email VARCHAR)";

            stmt.executeUpdate(createTableSQL);
            System.out.println("表 '" + schemaName + ".user_info' 创建成功或已存在");
        }
    }

    private static void insertData(Connection conn, String schemaName) throws SQLException {
        // 在 Ignite 3.0 中，可以使用批处理提高插入性能
        String insertSql = "INSERT INTO " + schemaName + ".user_info (id, name, age, email) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            // 第一条记录
            pstmt.setInt(1, 1);
            pstmt.setString(2, "张三");
            pstmt.setInt(3, 25);
            pstmt.setString(4, "zhangsan@example.com");
            pstmt.addBatch();

            // 第二条记录
            pstmt.setInt(1, 2);
            pstmt.setString(2, "李四");
            pstmt.setInt(3, 30);
            pstmt.setString(4, "lisi@example.com");
            pstmt.addBatch();

            // 第三条记录
            pstmt.setInt(1, 3);
            pstmt.setString(2, "王五");
            pstmt.setInt(3, 28);
            pstmt.setString(4, "wangwu@example.com");
            pstmt.addBatch();

            // 执行批处理
            int[] results = pstmt.executeBatch();
            System.out.println("成功插入 " + results.length + " 条数据到表 '" + schemaName + ".user_info'");
        }
    }

    private static void queryData(Connection conn, String schemaName) throws SQLException {
        String query = "SELECT * FROM " + schemaName + ".user_info ORDER BY id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("\n查询结果:");
            System.out.println("id\tname\tage\temail");
            System.out.println("--------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%d\t%s\t%d\t%s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"));
            }
        }
    }
}
