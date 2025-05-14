package org.apache.ignite.global;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 常量
 *
 * @author weiqiang
 * @date 2021-03-10
 */
public class GlobalConstants {

    /**
     * linePattern
     */
    public static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");
    /**
     * 格式化时间格式
     */
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化时间格式
     */
    public static final String DATE_TIME_FORMATTER = "yyyyMMddHHmmss";

    /**
     * 格式化时间格式
     */
    public static final String DATE_TIME_HOUR_FORMATTER = "yyyyMMddHH";
    /**
     * 格式化时间格式
     */
    public static final String DATE_FORMATTER = "yyyyMMdd";
    /**
     * 格式化时间格式
     */
    public static final String DATE_FORMATTER_CHN = "yyyy-MM-dd";
    /**
     * 前缀
     */
    public static final String DEFAULT_PREFIX = "(";
    /**
     * 后缀
     */
    public static final String DEFAULT_SUFFIX = ")";
    /**
     * EXCEL类型
     */
    public static final String DATA_TYPE_XLS = "xls";
    /**
     * EXCEL类型
     */
    public static final String DATA_TYPE_XLSX = "xlsx";
    /**
     * csv类型
     */
    public static final String DATA_TYPE_CSV = "csv";
    /**
     * 最大尝试登陆失败次数
     */
    public static final int FREQUENTLY_MAX_LOGIN_ERROR_COUNT = 15;
    /**
     * 逗号
     */
    public static final String INTERVAL_COMMA = ",";

    /**
     * 双引号
     */
    public static final String INTERVAL_DOUBLE_APOSTROPHE = "\"";
    /**
     * 间隔号
     */
    public static final String INTERVAL_NUMBER = "`";
    /**
     * 点
     */
    public static final String INTERVAL_POINT = "\\.";
    /**
     * 点
     */
    public static final String POINT = ".";
    /**
     * linux
     */
    public static final String LINUX_NAME = "linux";
    /**
     * mac
     */
    public static final String MAX_NAME = "mac";
    /**
     * 升序
     */
    public static final String ORDER_ASC = "ASC";
    /**
     * 降序
     */
    public static final String ORDER_DESC = "DESC";
    /**
     * 问号
     */
    public static final String QUERY_MARK = "?";
    /**
     * 与号
     */
    public static final String AND_MARK = "&";
    /**
     * 空格
     */
    public static final String SPACE = " ";
    /**
     *
     */
    public static final String AND = " and ";

    /**
     * or
     */
    public static final String OR = "||";

    /**
     * from
     */
    public static final String FROM = " from ";

    /**
     * group by
     */
    public static final String GROUP_BY = " group by ";

    /**
     * order by
     */
    public static final String ORDER_BY = "order by ";

    /**
     * datePicker
     */
    public static final String DATE_PICKER = "datePicker";

    /**
     * inputBetween
     */
    public static final String INPUT_BETWEEN = "inputBetween";
    /**
     * 成功
     */
    public static final String SUCCESS = "success";
    /**
     * 下划线
     */
    public static final String UNDER_LINE = "_";

    /**
     * 井号
     */
    public static final String WELL_MARK = "#";
    /**
     * 常量:未知
     */
    public static final String UNKNOWN = "unknown";
    /**
     * 更新
     */
    public static final String UPDATE = "UPDATE";

    /**
     * 增加
     */
    public static final String INSERT = "INSERT";

    /**
     * 查询
     */
    public static final String SELECT = "SELECT";

    /**
     * 删除
     */
    public static final String DELETE = "DELETE";
    /**
     * 批量更新
     */
    public static final String BATCH_UPDATE = "batchUpdate";

    /**
     * 批量增加
     */
    public static final String BATCH_INSERT = "batchInsert";

    /**
     * 批量删除
     */
    public static final String BATCH_DELETE = "batchDelete";

    /**
     * 批量更新
     */
    public static final String BATCH_UPDATE_QUOTA_NAME = "batchUpdateQuotaName";

    /**
     * 批量更新
     */
    public static final String BATCH_UPDATE_CACHE = "batchUpdateCache";

    /**
     * 更新数据接口信息
     */
    public static final String UPDATE_API_INFO = "updateApiInfo";

    /**
     * xml请求
     */
    public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    /**
     * 请求类型
     */
    public static final String X_REQUESTED_WIDTH = "X-Requested-With";

    /**
     * 多线程名称
     */
    public static final String THREAD_NAME = "common_thread_";

    /**
     * User-Agent
     */
    public static final String USER_AGENT = "User-Agent";

    /**
     * message
     */
    public static final String CODE_MESSAGE = "message";

    /**
     * body
     */
    public static final String CODE_BODY = "body";

    /**
     * Cookie
     */
    public static final String COOKIE_VALUE = "Set-Cookie";

    /**
     * 核心线程数
     */
    public static final Integer CORE_POOL_SIZE = 5;

    /**
     * 最大线程数
     */
    public static final Integer MAX_POOL_SIZE = 10;
    /**
     * 任务队列为 `ArrayBlockingQueue`，并且容量为 100
     */
    public static final Integer QUEUE_CAPACITY = 100;
    /**
     * 等待时间
     */
    public static final Long KEEP_ALIVE_TIME = 1L;
    /**
     * 默认精度
     */
    public static final int DEFAULT_PRECISION = 2;

    /**
     * success code
     */
    public static final String CODE_SUCCESS = "success";

    /**
     * null
     */
    public static final String NULL_CODE = "null";

    /**
     * 斜杠
     */
    public static final String SLASH_CODE = "/";

    /**
     * 单引号
     */
    public static final String INTERVAL_APOSTROPHE = "'";

    /**
     * script 头
     */
    public static final String SCRIPT_START = "<script>";

    /**
     * script 尾
     */
    public static final String SCRIPT_END = "</script>";

    /**
     * 大于
     */
    public static final String INTERVAL_MORE_THAN = ">";

    /**
     * 大于等于
     */
    public static final String INTERVAL_MORE_EQUAL_THAN = ">=";

    /**
     * 小于
     */
    public static final String INTERVAL_LESS_THAN = "<";

    /**
     * 小于等于
     */
    public static final String INTERVAL_LESS_EQUAL_THAN = "<=";

    /**
     * 等于
     */
    public static final String INTERVAL_EQUAL = "=";

    /**
     * in
     */
    public static final String INTERVAL_IN = "in";

    /**
     * like
     */
    public static final String INTERVAL_LIKE = "like";

    /**
     * is not null
     */
    public static final String INTERVAL_IS_NOT_NULL = "is not null";

    /**
     * 函数获取
     */
    public static final String INTERVAL_FUN_VALUE = "funValue";

    /**
     * 不等于
     */
    public static final String INTERVAL_NOT_EQUAL_1 = "!=";

    /**
     * %
     */
    public static final String PERCENT = "%";

    /**
     * ','
     */
    public static final String INTERVAL_APOSTROPHE_COMMA = "','";

    /**
     * 不等于
     */
    public static final String INTERVAL_NOT_EQUAL = "≠";

    /**
     * 冒号
     */
    public static final String INTERVAL_COLON = ":";
    /**
     * 分号
     */
    public static final String INTERVAL_SEMICOLON = ";";

    /**
     * db
     */
    public static final String INTERVAL_DB = "db";

    /**
     * SQL注释标识
     */
    public static final String INTERVAL_SQL_CANCEL = "--";

    /**
     * http
     */
    public static final String INTERVAL_HTTP = "http";

    /**
     * SQL内容
     */
    public static final String SQL_CONTENT_KEY = "sqlContent";

    /**
     * $
     */
    public static final String INTERVAL_DOLLAR = "$";

    /**
     * SQL关键词
     * and、or、like、delete、truncate、select、update、drop
     */
    public static final Pattern SQL_PATTERN = Pattern.compile("('{1}+\\s{0,}or\\s{0,})|(\\d+\\s{0,}or\\s{0,})|" +
            "(\\s+((drop)|(select)|(truncate)|(delete)|(update)|(insert)|(alter)(create))+\\s+)|" +
            "(\\s+and\\s+)|(;+)|(\\s+like+\\s)");
    /**
     * 数据库操作
     */
    public static final Pattern SQL_DATA_PATTERN = Pattern.compile("((drop)|(truncate)|(delete)|(update)|(insert)|(alter)(create))+\\s");

    /**
     * 空字符串
     */
    public static final String EMPTY_STRING = "";

    /**
     * 数字正在表达式
     */
    public static final Pattern NUM_PATTERN = Pattern.compile("^[0-9.]*$");

    /**
     * 字符正在表达式（中文、英文、数字包括下划线、中横线）
     */
    public static final Pattern STR_PATTERN = Pattern.compile("[\\s\\S]*+$");

    /**
     * 日期正在表达式（yyyy-MM-dd）|（yyyyMMdd）
     */
    public static final Pattern DATE_PATTERN = Pattern.compile("(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-9]))))" +
            "|(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})(((0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)(0[1-9]|[12][0-9]|30))|(02(0[1-9]|[1][0-9]|2[0-9]))))");

    /**
     * api前缀
     */
    public static final String API_PREFIX = "api/";

    /**
     * 天
     */
    public static final String TABLE_DAY = "_d";

    /**
     * 周
     */
    public static final String TABLE_WEEK = "_w";

    /**
     * 月
     */
    public static final String TABLE_MONTH = "_m";

    /**
     * 年
     */
    public static final String TABLE_YEAR = "_y";

    /**
     * httpCode
     */
    public static final String HTTP_CODE = "http://";

    /**
     * httpsCode
     */
    public static final String HTTPS_CODE = "https://";

    /**
     * 一天(毫秒)
     */
    public static final Integer SECONDS_ONE_DAY = 1000 * 60 * 60 * 24;
    /**
     * 一小时(毫秒)
     */
    public static final Integer SECONDS_ONE_HOUR = 1000 * 60 * 60;
    /**
     * 一分钟(毫秒)
     */
    public static final Integer SECONDS_ONE_MINUTE = 60000;
    /**
     * 两天(毫秒)
     */
    public static final Integer SECONDS_TWO_DAY = 1000 * 60 * 60 * 48;
    /**
     * 两分钟(毫秒)
     */
    public static final Integer SECONDS_TWO_MINUTE = 2 * 60 * 1000;

    /**
     * token再请求头中的Key
     */
    public static final String TOKEN_HEADER = "token";

    /**
     * 忽略校验token URI
     */
    public static final String[] IGNORE_URI = {"user/login", "sysQueryConfig/apiPrefix"};
    /**
     * 请求头中不设置tokenUri
     */
    public static final String[] NO_TOKEN_URI = {"user/logout"};

    /**
     * token再请求头中的Key
     */
    public static final String TOKEN_NEW_HEADER = "newToken";
    /**
     * token超时时间
     */
    public static final Integer TOKEN_TIME_OUT = SECONDS_TWO_DAY + SECONDS_ONE_MINUTE;
    /**
     * token密钥
     */
    public static final String LEXICAL_XSD_BASE64_BINARY = "quickFrame@123";

    /**
     * 当前登陆客户端用户信息
     */
    public static final String CLIENT_INFO = "clientInfo";
    /**
     * 客户端Ip
     */
    public static final String CLIENT_IP = "clientIp";

    /**
     * false
     */
    public static final Integer BOOLEAN_FALSE = 0;
    /**
     * true
     */
    public static final Integer BOOLEAN_TRUE = 1;

    /**
     * 5次登陆失败
     */
    public static final int FREQUENTLY_LOGIN_COUNT = 5;


    /**
     * 开始分页参数
     */
    public static final String STR_ENABLE_PAGE = "enablePage";

    /**
     * 初始页
     */
    public static final String STR_PAGE_NUM = "pageNum";

    /**
     * 每页显示数据条数
     */
    public static final String STR_PAGE_SIZE = "pageSize";

    /**
     * 分页总数
     */
    public static final String STR_TOTAL = "total";

    /**
     * and
     */
    public static final String QUERY_AND = "&";
    /**
     * 本地ip
     */
    public static final String LOCALHOST_CODE = "127.0.0.1";
    /**
     * 数据请求模式 httpClient
     */
    public static final String API_REQUEST_MODEL_HTTP_CLIENT = "httpClient";

    /**
     * 下一行
     */
    public static final String NEXT_LINE = "\r\n";
    /**
     * 下一行
     */
    public static final String NEXT_LINE_1 = "\r";
    /**
     * 下一行
     */
    public static final String NEXT_LINE_2 = "\n";

    /**
     * 父ID
     */
    public static final String DEFAULT_PARENT_ID = "-1";
    /**
     * true
     */
    public static final String TRUE_VALUE = "1";
    /**
     * false
     */
    public static final String FALSE_VALUE = "0";

    /**
     * xcloud
     */
    public static final String XCLOUD = "xcloud";

    /**
     * es
     */
    public static final String ES = "es";

    /**
     * es
     */
    public static final String DM = "dm";

    /**
     * kingbase8
     */
    public static final String KINGBASE8 = "kingbase8";

    /**
     * mysql
     */
    public static final String MYSQL = "mysql";

    /**
     * oracle
     */
    public static final String ORACLE = "oracle";

    /**
     * 默认时间
     */
    public static final Integer DEFAULT_TIME_OUT = 60 * 1000;

    /**
     * csv文件
     */
    public static final String CSV_FILE_TYPE = ".csv";

    /**
     * .xlsx文件
     */
    public static final String XLSX_FILE_TYPE = ".xlsx";
    /**
     * .xls文件
     */
    public static final String XLS_FILE_TYPE = ".xls";

    /**
     * prod
     */
    public static final String PROD_FILE = "prod";

    /**
     * test
     */
    public static final String TEST_FILE = "test";

    /**
     * dev
     */
    public static final String DEV_FILE = "dev";


    /**
     * 查询日期类型
     */
    public static final String QUERY_DATE_TYPE = "datePicker";


    /**
     * operation操作符
     */
    public static final String OPERATION = "operation";

    /**
     * 管理员用户ID
     */
    public static final String ADMIN_USER_ID = "admin";

    /**
     * 组织机构缓存key信息,后面拼接组织ID
     */
    public static final String SYS_DEPARTMENT_REDIS_KEY = "sys_department_redis_key_";

    /**
     * 组织机构的层级缓存key信息,后面拼接组织ID
     */
    public static final String SYS_DEPARTMENT_LAYER_REDIS_KEY = "sys_department_layer_redis_key_";

    /**
     * 组织机构子节点缓存key信息,后面拼接组织子节点ID集合
     */
    public static final String SYS_DEPARTMENT_CHILD_REDIS_KEY = "sys_department_child_redis_key_";

    /**
     * 组织机构树缓存key信息
     */
    public static final String SYS_DEPARTMENT_TREE_REDIS_KEY = "sys_department_tree_redis_key";

    /**
     * 用户有权限组织机构缓存key信息,后面拼接用户ID,局领导用户
     */
    public static final String SYS_USER_DEPARTMENTS_REDIS_KEY = "sys_user_departments_redis_key_";

    /**
     * 组织Map，key为当前组织Id，值为所有子组织Id集合
     */
    public static final String SYS_DEPARTMENT_PARENT_CHILD_REDIS_KEY = "sys_department_parent_child_redis_key";

    /**
     * 用户有权限组织机构缓存key信息,后面拼接用户ID,所有用户
     */
    public static final String SYS_USER_HAS_DEPARTMENTS_REDIS_KEY = "sys_user_has_departments_redis_key_";


    /**
     * 税务机关部门关联,后面拼接税务机关
     */
    public static final String SYS_DEPARTMENT_SWJG_REDIS_KEY = "sys_swig_department_redis_key_";
    /**
     * 部门下人员数量缓存key信息,后面拼接组织ID
     */
    public static final String SYS_DEPARTMENT_USER_COUNT_REDIS_KEY = "sys_department_user_count_redis_key_";
    /**
     * "*"
     */
    public static final String ALL_CODE = "*";

    /**
     * 默认排序字段
     */
    public static final String DEFAULT_SORT_NAME = "updateTime";


    /**
     * 字段类型
     */
    public static final Map<String, String> COLUMN_TYPE_MAP = new HashMap<String, String>(20) {{
        put("TINYINT", "数值型");
        put("SMALLINT", "数值型");
        put("MEDIUMINT", "数值型");
        put("INT", "数值型");
        put("BIGINT", "数值数值型");
        put("FLOAT", "小数型");
        put("DOUBLE", "小数型");
        put("DECIMAL", "小数型");
        put("YEAR", "日期时间型");
        put("TIMESTAMP", "日期时间型");
        put("TIME", "日期时间型");
        put("DATE", "日期时间型");
        put("DATETIME", "日期时间型");
        put("SET", "字符串型");
        put("ENUM", "字符串型");
        put("BLOB", "字符串型");
        put("TEXT", "字符串型");
        put("LONGTEXT", "字符串型");
        put("VARCHAR", "字符串型");
        put("CHAR", "字符串型");
    }};

    /**
     * restful
     */
    public static final String JOB_API_TYPE_RESTFUL = "restful";
    /**
     * webservice
     */
    public static final String JOB_API_TYPE_WEBSERVICE = "webservice";
    /**
     * socket
     */
    public static final String JOB_API_TYPE_SOCKET = "socket";
    /**
     * POST_FORM
     */
    public static final String REQUEST_METHOD_POST_FORM = "POST_FORM";
    /**
     * 参数位置，请求头
     */
    public static final String PARAM_POSITION_HEADER = "header";
    /**
     * 参数位置,查询
     */
    public static final String PARAM_POSITION_BODY = "body";
    /**
     * 参数位置,查询
     */
    public static final String PARAM_POSITION_QUERY = "query";

    /**
     * 返回值数据key
     */
    public static final String RESULT_DATA_KEY = "data";

    /**
     * 返回数据条数Key
     */
    public static final String RESULT_TOTAL_KEY = "total";

    /**
     * 忽略日志统计
     */
    public static final String LOG_IGNORE_STATISTICS = "logIgnoreStatistics";

    /**
     * 二包风险模型统筹导出缓存Key
     */
    public static final String EB_DATA_EXPORT_CONFIG_REDIS_KEY = "eb_data_export_config_redis_key_";


    /**
     * 字符型
     */
    public static final String TYPE_STRING_CODE = "string";

    /**
     * 数字型
     */
    public static final String TYPE_NUMBER_CODE = "number";

    /**
     * 日期型
     */
    public static final String TYPE_DATE_CODE = "date";

    /**
     * 列别名
     */
    public static final String COLUMN_ALIAS = " as ";

    /**
     * 左关联
     */
    public static final String LEFT_JOIN_SQL = " left join ";

    /**
     * 右关联
     */
    public static final String RIGHT_JOIN_SQL = " right join ";

    /**
     * on
     */
    public static final String ON_SQL = " on ";

    /**
     * mybatis参数拼接参数起始符号
     */
    public static final String MYBATIS_VALUE_BEGIN = " #{";
    /**
     * mybatis参数拼接参数结束符号
     */
    public static final String MYBATIS_VALUE_END = "} ";

    /**
     * 用户信息Key
     */
    public static final String SYS_USER_INFO_KEY = "user_id_key_";

    /**
     * 应用URI缓存Key
     */
    public static final String APP_URI_REDIS_KEY = "app_uri_redis_key";

    /**
     * 压缩文件后缀
     */
    public static final String ZIP_FILE_SUFFIX = ".zip";

    /**
     * 部门的权限组
     */
    public static final String PERMISSION_DEPT_REDIS_KEY = "permission_dept_redis_key";

    /**
     * 用户的权限组
     */
    public static final String PERMISSION_USER_REDIS_KEY = "permission_user_redis_key";

    /**
     * 角色的权限组
     */
    public static final String PERMISSION_ROLE_REDIS_KEY = "permission_role_redis_key";

    /**
     * 用户拥有菜单缓存key信息,后面拼接用户ID,所有用户
     */
    public static final String SYS_USER_HAS_MENUS_REDIS_KEY = "sys_user_has_menus_redis_key_";
}
