package org.apache.ignite.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.core.text.csv.CsvWriteConfig;
import cn.hutool.core.text.csv.CsvWriter;
import cn.hutool.core.util.CharUtil;
import org.apache.ignite.enums.GlobalEnum;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.ignite.global.GlobalConstants.*;

/**
 * @author weiQiang
 * @Date: 2022/1/17 18:08
 * @Version: 1.0
 * @Description:
 */
@Slf4j
public class GlobalUtils {
    /**
     * 百家姓
     */
    private static final String[] SUR_NAME = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒", "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭", "梅", "盛", "林", "刁", "钟", "徐", "邱", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝", "管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸", "左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "于", "惠", "甄", "曲", "家", "封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "钭", "厉", "戎", "祖", "武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "溥", "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "却", "璩", "桑", "桂", "濮", "牛", "寿", "通", "边", "扈", "燕", "冀", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦", "艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国", "文", "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖", "融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相", "查", "后", "荆", "红", "游", "郏", "竺", "权", "逯", "盖", "益", "桓", "公", "仉", "督", "岳", "帅", "缑", "亢", "况", "郈", "有", "琴", "归", "海", "晋", "楚", "闫", "法", "汝", "鄢", "涂", "钦", "商", "牟", "佘", "佴", "伯", "赏", "墨", "哈", "谯", "篁", "年", "爱", "阳", "佟", "言", "福", "南", "火", "铁", "迟", "漆", "官", "冼", "真", "展", "繁", "檀", "祭", "密", "敬", "揭", "舜", "楼", "疏", "冒", "浑", "挚", "胶", "随", "高", "皋", "原", "种", "练", "弥", "仓", "眭", "蹇", "覃", "阿", "门", "恽", "来", "綦", "召", "仪", "风", "介", "巨", "木", "京", "狐", "郇", "虎", "枚", "抗", "达", "杞", "苌", "折", "麦", "庆", "过", "竹", "端", "鲜", "皇", "亓", "老", "是", "秘", "畅", "邝", "还", "宾", "闾", "辜", "纵", "侴", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人", "东方", "赫连", "皇甫", "羊舌", "尉迟", "公羊", "澹台", "公冶", "宗正", "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐", "钟离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "兀官", "司寇", "南门", "呼延", "子车", "颛孙", "端木", "巫马", "公西", "漆雕", "车正", "壤驷", "公良", "拓跋", "夹谷", "宰父", "谷梁", "段干", "百里", "东郭", "微生", "梁丘", "左丘", "东门", "西门", "南宫", "第五", "公仪", "公乘", "太史", "仲长", "叔孙", "屈突", "尔朱", "东乡", "相里", "胡母", "司城", "张廖", "雍门", "毋丘", "贺兰", "綦毋", "屋庐", "独孤", "南郭", "北宫", "王孙"};
    /**
     * csv文件默认路径
     */
    public static String CSV_FILE_PATH = System.getProperty("user.home") + File.separator + "spring-boot" + File.separator + "csv-file" + File.separator;

    /**
     * 通用主键
     *
     * @return String
     */
    public synchronized static String ordinaryId() {
        Integer count = 6;
        return ordinaryId(count);
    }

    /**
     * 通用主键UUID
     *
     * @return String
     */
    public synchronized static String uuid() {
        return UUID.randomUUID().toString().replace("-", EMPTY_STRING);
    }

    /**
     * java字段转数据库字段
     *
     * @param column    Java字段
     * @param direction 排序字段
     * @return String
     */
    public static String changeColumn(String column, String direction) {
        if (StringUtils.isNotBlank(column)) {
            if (StringUtils.isBlank(direction)) {
                direction = ORDER_ASC;
            }
            return appendString(tableColumn(column), SPACE, direction);
        }
        return EMPTY_STRING;
    }

    /**
     * java字段转数据库字段
     *
     * @param column Java字段
     * @return String
     */
    public static String tableColumn(String column) {
        StringBuilder columnBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(column)) {
            char[] chars = column.toCharArray();
            for (char aChar : chars) {
                if (Character.isUpperCase(aChar)) {
                    columnBuilder.append(UNDER_LINE);
                    columnBuilder.append(String.valueOf(aChar).toLowerCase());
                } else {
                    columnBuilder.append(aChar);
                }
            }
        }
        return columnBuilder.toString();
    }

    /**
     * 通用主键
     *
     * @param count 随机数个数
     * @return String
     */
    public synchronized static String ordinaryId(Integer count) {
        return DateFormatUtils.format(new Date(), DATE_TIME_FORMATTER) + RandomStringUtils.randomNumeric(count);
    }

    /**
     * 判断是否是linux系统
     *
     * @return
     */
    public static boolean isOsLinux() {
        Properties properties = System.getProperties();
        String os = properties.getProperty("os.name");
        return os != null && (os.toLowerCase().indexOf(LINUX_NAME) > -1 || os.toLowerCase().indexOf(MAX_NAME) > -1);
    }

    /**
     * 根据系统转换为windows格式或者linux格式
     *
     * @param path 路径
     * @return
     */
    public static String winOrLinuxPath(String path) {
        if (!isOsLinux()) {
            path = path.replace("/", "\\");
        }
        return path;
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static String createDir(String path) {
        if (!path.endsWith(File.separator)) {
            path += File.separator;
        }
        path = winOrLinuxPath(path);
        File unZipFileDir = new File(path);
        if (!unZipFileDir.exists()) {
            unZipFileDir.mkdirs();
        }
        return path;
    }

    /**
     * Object 转 字符串
     *
     * @param obj obj
     * @return str
     */
    public static String objToStr(Object obj) {
        return String.valueOf(obj);
    }

    /**
     * Object 转 Double
     *
     * @param obj obj
     * @return Double
     */
    public static Double objToDouble(Object obj) {
        if (obj == null) {
            return 0.00;
        }
        return Double.valueOf(objToStr(obj));
    }

    /**
     * 固定精度
     *
     * @param v 浮点值
     * @return
     */
    public static double precision(Object v) {
        if (Objects.isNull(v) || !(NumberUtils.isNumber(objToStr(v)))) {
            v = 0;
        }

        return precisionD(objToDouble(v), 2);
    }

    /**
     * 固定精度 若为整数或者当前数的精度小于给定精度 不会补零
     * 例如：v 12 precision:2 return 12.0
     *
     * @param v         浮点值
     * @param precision 精度
     * @return 固定精度后的数值
     */
    public static double precisionD(double v, int precision) {
        if (Objects.isNull(v)) {
            v = 0d;
        }
        BigDecimal bigDecimal = new BigDecimal(v);
        return bigDecimal.setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(INTERVAL_COMMA) && ip.split(INTERVAL_COMMA).length > 1) {
            ip = ip.split(INTERVAL_COMMA)[0];
        }
        return ip;
    }

    /**
     * 获取本地ipv4地址
     *
     * @return String
     */
    public static String getHostIp() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();
                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress ip = addresses.nextElement();
                    //loopback地址即本机地址，IPv4的loopback范围是127.0.0.0 ~ 127.255.255.255
                    if (Objects.nonNull(ip) && ip instanceof Inet4Address && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(INTERVAL_COLON) == -1) {
                        log.debug("本机的IP:{}", ip.getHostAddress());
                        return ip.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取公网ip发生错误:{}", e.getMessage());
        }
        return LOCALHOST_CODE;
    }

    /**
     * 获取发起请求的浏览器名称
     */
    public static String getBrowserName(HttpServletRequest request) {
        String header = request.getHeader(USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        Browser browser = userAgent.getBrowser();
        return (Objects.isNull(browser) || Objects.isNull(browser.getName())) ? "未识别出浏览器" : browser.getName();
    }

    /**
     * 获取发起请求的浏览器版本号
     */
    public static String getBrowserVersion(HttpServletRequest request) {
        String header = request.getHeader(USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        //获取浏览器信息
        Browser browser = userAgent.getBrowser();
        //获取浏览器版本号
        Version version = browser.getVersion(header);
        return Objects.isNull(version) || Objects.isNull(version.getVersion()) ? "未识别出操作系统" : version.getVersion();
    }

    /**
     * 获取发起请求的操作系统名称
     */
    public static String getOsName(HttpServletRequest request) {
        String header = request.getHeader(USER_AGENT);
        UserAgent userAgent = UserAgent.parseUserAgentString(header);
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return (Objects.isNull(operatingSystem) || Objects.isNull(operatingSystem.getName())) ? "未识别出操作系统" : operatingSystem.getName();
    }

    /**
     * 组合字符串,并去空格拼接
     *
     * @param args 参数
     * @return String
     */
    public static String appendString(String... args) {
        if (Objects.isNull(args) || args.length < 1) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        for (String arg : args) {
            if (Objects.equals(arg, SPACE)) {
                buffer.append(arg);
            } else {
                if (Objects.equals(arg, NEXT_LINE)) {
                    buffer.append(arg);
                } else {
                    buffer.append(StringUtils.stripToEmpty(arg));
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        if (Objects.isNull(str)) {
            return EMPTY_STRING;
        }
        char firstChar = str.charAt(0);
        if (!str.contains(UNDER_LINE) && Character.isLowerCase(firstChar)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 比较大小
     *
     * @param one     比较数据
     * @param two     比较数据
     * @param operate 运算符
     * @return
     */
    public static boolean compareNumber(String one, String two, String operate) {
        Double numOne = Double.parseDouble(one);
        Double numTwo = Double.parseDouble(two);
        boolean bl = false;
        switch (operate) {
            case INTERVAL_MORE_THAN:
                bl = numOne > numTwo;
                break;
            case INTERVAL_MORE_EQUAL_THAN:
                bl = numOne >= numTwo;
                break;
            case INTERVAL_LESS_THAN:
                bl = numOne < numTwo;
                break;
            case INTERVAL_LESS_EQUAL_THAN:
                bl = numOne <= numTwo;
                break;
            case INTERVAL_EQUAL:
                bl = numOne.equals(numTwo);
                break;
            case INTERVAL_NOT_EQUAL:
                bl = Objects.equals(one, two);
                break;
            default:
                break;
        }
        return bl;
    }

    /**
     * 相差天数
     *
     * @param oriCalendar 旧日期
     * @return int
     */
    public static int compareDateDay(Calendar oriCalendar) {
        Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        return compareDate(oriCalendar, newCalendar, 0);
    }

    /**
     * 相差天数
     *
     * @param oriCalendar 旧日期
     * @param newCalendar 新日期
     * @return int
     */
    public static int compareDateDay(Calendar oriCalendar, Calendar newCalendar) {
        return compareDate(oriCalendar, newCalendar, 0);
    }

    /**
     * 相差月数
     *
     * @param oriCalendar 旧日期
     * @return int
     */
    public static int compareDateMonth(Calendar oriCalendar) {
        Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        return compareDate(oriCalendar, newCalendar, 1);
    }

    /**
     * 相差月数
     *
     * @param oriCalendar 旧日期
     * @param newCalendar 新日期
     * @return int
     */
    public static int compareDateMonth(Calendar oriCalendar, Calendar newCalendar) {
        return compareDate(oriCalendar, newCalendar, 1);
    }

    /**
     * 相差年数
     *
     * @param oriCalendar 旧日期
     * @return int
     */
    public static int compareDateYear(Calendar oriCalendar) {
        Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        return compareDate(oriCalendar, newCalendar, 2);
    }

    /**
     * 相差年数
     *
     * @param oriCalendar 旧日期
     * @param newCalendar 新日期
     * @return int
     */
    public static int compareDateYear(Calendar oriCalendar, Calendar newCalendar) {
        return compareDate(oriCalendar, newCalendar, 2);
    }

    /**
     * 相差日、月、年
     *
     * @param oriCalendar 旧日期
     * @param type        类型,0:天,1:月,2:年
     * @return int
     */
    public static int compareDate(Calendar oriCalendar, int type) {
        Calendar newCalendar = Calendar.getInstance(TimeZone.getDefault());
        return compareDate(oriCalendar, newCalendar, type);
    }

    /**
     * 相差日、月、年
     *
     * @param oriCalendar 旧日期
     * @param newCalendar 新日期
     * @param type        类型,0:天,1:月,2:年
     * @return int
     */
    public static int compareDate(Calendar oriCalendar, Calendar newCalendar, int type) {
        if (Objects.isNull(oriCalendar)) {
            return 0;
        }
        if (Objects.isNull(newCalendar)) {
            newCalendar = Calendar.getInstance(TimeZone.getDefault());
            newCalendar.setTime(new Date());
        }
        int n = 0, dayType = 1, monthType = 1, yearType = 2;
        if (type > yearType || type < dayType) {
            type = monthType;
        }
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(oriCalendar.getTime());
            c2.setTime(newCalendar.getTime());
        } catch (Exception e3) {
            log.error("相差日、月、年计算错误:{}", e3.getMessage());
        }
        while (!c1.after(c2)) {
            n++;
            if (type == monthType) {
                // 比较月份，月份+1
                c1.add(Calendar.MONTH, 1);
            } else {
                // 比较天数，日期+1
                c1.add(Calendar.DATE, 1);
            }
        }
        n = n - 1;
        if (type == yearType) {
            n = n / 365;
        }
        return n;
    }

    /**
     * 转换异常信息
     *
     * @param globalEnum 异常提示
     * @param args       参数
     */
    public static void convertMessage(GlobalEnum globalEnum, String... args) {
        String message = globalEnum.getMessage();
        convertMessage(message, args);
    }

    /**
     * 转换异常信息
     *
     * @param message 异常提示
     * @param args    参数
     */
    public static void convertMessage(String message, String... args) {
        message = convertMsg(message, args);
        throw new RuntimeException(message);
    }

    /**
     * 转换异常信息
     *
     * @param globalEnum 异常提示
     * @param args       参数
     * @return String
     */
    public static String convertMsg(GlobalEnum globalEnum, String... args) {
        return convertMsg(globalEnum.getMessage(), args);
    }

    /**
     * 转换异常信息
     *
     * @param message 异常提示
     * @param args    参数
     * @return String
     */
    public static String convertMsg(String message, String... args) {
        message = String.format(message, args);
        return message;
    }

    /**
     * 随机生成姓名
     *
     * @return String
     */
    public static String getChineseName() {
        String str = null, name = null;
        int highPos, lowPos;
        Random random = new Random();
        //区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
        highPos = (176 + Math.abs(random.nextInt(72)));
        random = new Random();
        //位码，0xA0打头，范围第1~94列
        lowPos = 161 + Math.abs(random.nextInt(94));

        byte[] bArr = new byte[2];
        bArr[0] = (Integer.valueOf(highPos)).byteValue();
        bArr[1] = (Integer.valueOf(lowPos)).byteValue();
        try {
            //区位码组合成汉字
            str = new String(bArr, "GB2312");
            int index = random.nextInt(SUR_NAME.length - 1);
            //获得一个随机的姓氏
            name = SUR_NAME[index] + str;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取分区SQL
     *
     * @param tableName 表名
     * @return String
     */
    public static String getPartitionSql(String tableName) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT B.PART_COND FROM V$USER_TAB_PARTS B INNER JOIN( SELECT MAX(PARTITION_ID) AS PT_ID FROM V$USER_TAB_DISTR WHERE TABLE_NAME = '").append(tableName).append("' AND  DATAPACK_SIZE > 0 ) A  ON A.PT_ID = B.PART_ID");
        return sqlBuilder.toString();
    }

    /**
     * 切分集合
     *
     * @param list  集合
     * @param split 切分条数
     * @param <T>   类型
     * @return <T>
     */
    public static <T> List<List<T>> splitList(Collection<T> list, int split) {
        List<List<T>> result = new ArrayList<List<T>>();
        int count = 0;
        List<T> cList = new ArrayList<>();
        for (T t : list) {
            cList.add(t);
            ++count;
            if (count >= split) {
                count = 0;
                result.add(cList);
                cList = new ArrayList<>();
            }
        }
        if (count > 0) {
            result.add(cList);
        }
        return result;
    }

    /**
     * 发送响应流方法
     *
     * @param response 响应
     * @param fileName 文件名称
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            GlobalUtils.convertMessage(GlobalEnum.ExceptionMessage, ex.getMessage());
        }
    }

    /**
     * 获取默认时间
     *
     * @param timeOut 超时时间
     * @return Integer
     */
    public static Integer convertTimeOut(Integer timeOut) {
        if (Objects.isNull(timeOut)) {
            return DEFAULT_TIME_OUT;
        }
        timeOut *= 1000;
        return timeOut;
    }

    /**
     * 生成csv文件
     *
     * @param headList     csv文件头
     * @param dataList     csv文件内容
     * @param filePrefix   文件前缀
     * @param headerIgnore 忽略头文件写入
     * @return 文件
     */
    public static File convertCsvFile(List<List<String>> headList, List<List<Object>> dataList, String filePrefix, Boolean headerIgnore) {
        String filePath = CSV_FILE_PATH + (StringUtils.isNotBlank(filePrefix) ? filePrefix : "") + GlobalUtils.ordinaryId() + CSV_FILE_TYPE;
        CsvWriteConfig csvWriteConfig = new CsvWriteConfig();
        csvWriteConfig.setFieldSeparator('`');
        csvWriteConfig.setLineDelimiter(new char[]{'\\', CharUtil.CR, CharUtil.LF});
        File importFile = new File(filePath);
        try (CsvWriter writer = CsvUtil.getWriter(importFile, StandardCharsets.UTF_8, true, csvWriteConfig)) {
            if (Objects.nonNull(headList) && headList.size() > 0 && !headerIgnore) {
                writer.write(headList);
            }
            if (Objects.nonNull(dataList)) {
                writer.write(dataList);
            }
            writer.flush();
        } catch (Exception e) {
            log.error("文件:{},写入失败:{}", filePath, e.getMessage());
        }
        return importFile;
    }

    /**
     * 生成csv文件
     *
     * @param headList csv文件头
     * @param dataList csv文件内容
     * @return 文件
     */
    public static File convertCsvFile(List<List<String>> headList, List<List<Object>> dataList) {
        return convertCsvFile(headList, dataList, null, true);
    }

    /**
     * 截取定长字符串
     *
     * @param oriString 原始字符串
     * @param maxLength 最大长度
     * @return String
     */
    public static String substring(String oriString, Integer maxLength) {
        if (StringUtils.isBlank(oriString)) {
            return EMPTY_STRING;
        }
        if (oriString.length() < maxLength) {
            return oriString;
        }
        return oriString.substring(0, maxLength);
    }

    /**
     * 获取某月的最后一天
     *
     * @return String
     */
    public static String getLastDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month += 1;
        return getLastDayOfMonth(year, month);
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return String
     */
    public static String getLastDayOfMonth(Integer year, Integer month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        return DateUtil.format(cal.getTime(), "dd");
    }

    /**
     * 加密出错返回源内容
     *
     * @param str 字符串
     * @return String
     */
    public static String encode(String str) {
        if (StringUtils.isBlank(str)) {
            return EMPTY_STRING;
        }
        try {
            return Base64.encode(str, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("数据encode发生错误:{}", e.getMessage());
            return str;
        }
    }

    /**
     * 解码，出错返回源内容
     *
     * @param str 字符串
     * @return String
     */
    public static String decode(String str) {
        if (StringUtils.isBlank(str)) {
            return EMPTY_STRING;
        }
        try {
            return new String(Base64.decode(str.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("数据decode发生错误:{}", e.getMessage());
            return str;
        }
    }

    /**
     * 生成定长密码
     *
     * @param passwordSize 密码长度
     * @return 定长密码
     */
    public static String passwordGenerate(Integer passwordSize) {
        SecureRandom random = new SecureRandom();
        final String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ", lowercaseLetters = "abcdefghijklmnopqrstuvwxyz", numbers = "0123456789", specialCharacters = "!@#$%^&*()_+-=[]{}|;':<>?,./";
        StringBuilder passwordBuilder = new StringBuilder();
        final String characterSet = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;
        for (int i = 0; i < passwordSize; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            char randomChar = characterSet.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }
        String password = passwordBuilder.toString();
        return password;
    }

    /**
     * 科学计数法转数值
     *
     * @param str 科学计数法字符串
     * @return 数值字符串
     */
    public static String scientificToNumber(String str) {
        String bigDecimal = "";
        boolean isNumber = false;
        try {
            bigDecimal = new BigDecimal(str).toString();
            isNumber = true;
        } catch (Exception ignored) {
        }
        if (isNumber) {
            return bigDecimal;
        }
        return str;
    }

    /**
     * 统计字符串出现的次数
     *
     * @param srcString    原始字符串
     * @param targetString 目标字符串
     * @return 字符串出现的次数
     */
    public static Integer appearCount(String srcString, String targetString) {
        Integer count = 0;
        if (StringUtils.isBlank(srcString) || StringUtils.isBlank(targetString)) {
            return count;
        }
        try {
            Pattern pattern = Pattern.compile(targetString);
            Matcher matcher = pattern.matcher(srcString);
            while (matcher.find()) {
                count++;
            }
        } catch (Exception e) {
            log.error("统计：{}在原始字符串:{}出现次数发生错误：{}", targetString, srcString, e.getMessage());
        }
        return count;
    }
}
