package com.qianxia.finance.utils;

/**
 * 雪花算法
 */
public class SnowflakeIdWorkerUtil {

    //得到二进制样例 10111100110111110011001010100001100111111100001000000000000

    /** 开始时间截 (2015-01-01) */
    private final long twepoch = 1420041600000L;

    //每一部分占用的位数
    /** 机器id所占的位数 */
    private final byte workerIdBits = 5;
    /** 数据标识id所占的位数 */
    private final byte dataCenterIdBits = 5;
    /** 序列在id中占的位数 */
    private final byte sequenceBits = 12;

    //每一部分的最大值
    /** 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数) */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    /** 支持的最大数据标识id，结果是31 */
    private final long maxDatacenterId = -1L ^ (-1L << dataCenterIdBits);
    /** 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095) */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    //每一部分向左的位移
    /** 机器ID向左移12位 */
    private final long workerIdShift = sequenceBits;
    /** 数据标识id向左移17位(12+5) */
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    /** 时间截向左移22位(5+5+12) */
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    /** 工作机器ID(0~31) */
    private long workerId;
    /** 数据中心ID(0~31) */
    private long dataCenterId;
    /** 毫秒内序列(0~4095) */
    private long sequence = 0L;
    /** 上次生成ID的时间截 */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     * @param workerId 工作组
     * @param dataCenterId 数据中心
     */
    SnowflakeIdWorkerUtil(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0){
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", workerId));
        }
        if (dataCenterId > maxDatacenterId || dataCenterId < 0){
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", dataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获取雪花id
     * @return uuid
     */
    synchronized long nextId(){
        // 获取当前毫秒值
        long timestamp = getCurrentTime();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp){
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp){
            sequence = (sequence + 1) & sequenceMask;//相同毫秒内，序列号自增
            //毫秒内序列溢出 //同一毫秒的序列数已经达到最大
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else {
            //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) //
                | (dataCenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;

    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp){
        long timestamp = getCurrentTime();
        while (timestamp <= lastTimestamp){
            timestamp = getCurrentTime();
        }
        return timestamp;
    }

    /**
     * 获取系统时间戳
     * @return
     */
    private long getCurrentTime(){
        return System.currentTimeMillis();
    }

    private final static SnowflakeIdWorkerUtil snowflakeIdWorker = new SnowflakeIdWorkerUtil(1,1);

    public static String getUuid(){
        return String.valueOf(snowflakeIdWorker.nextId());
    }

    public static void main(String[] args) {
        System.out.println(getUuid());
        System.out.println(getUuid().length());
    }
}
