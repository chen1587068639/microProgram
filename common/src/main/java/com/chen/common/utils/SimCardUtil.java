package com.chen.common.utils;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SimCardUtil {

    /**
     * sim卡供应商用户名
     */
    public static String USER_NAME = "zzcx";

    @Value("${simUserName}")
    public void simUserName(String simUserName) {
        USER_NAME = simUserName;
    }

    /**
     * sim卡供应商appKey
     */
    public static String APP_KEY = "chdi7mnks0n0n9xy07";

    @Value("${simAppKey}")
    public void simAppKey(String simAppKey) {
        APP_KEY = simAppKey;
    }

    /**
     * sim卡供应商appSecret
     */
    public static String APP_SECRET = "9l52ho2sd3fv9hweok0ad6qwu0cvuhde";

    @Value("${simAppSecret}")
    public void simAppSecret(String simAppSecret) {
        APP_SECRET = simAppSecret;
    }

    /**
     * sim卡供应商ip
     */
    public static String HOST = "http://121.89.194.200:8082";

    @Value("${simHost}")
    public void simHost(String simHost) {
        HOST = simHost;
    }

    /**
     * 流量池(流量池就是卡组)查询接口：如果有多个流量池，可以返回多个流量池的数据
     */
    public static final String QUERY_USER_POOL = "/admin/api/v2/userPoolList";

    /**
     * 批量查询sim卡数据接口
     * 返回数据：iccid，msisdn，balance卡余额，usedGprs流量使用量，prodTotal套餐总量，
     * prodName套餐名称，simStatusSIM卡状态，gprsStatus网络状态，activeTime激活时间，expTime过期时间，updateTime数据同步时间
     */
    public static final String QUERY_CARD_LIST = "/admin/api/v2/cardInfos";

    /**
     * 业务状态变更;卡状态操作
     * SIM卡状态:
     * 0：正常 1：待激活 3：单向停机 4：停机 5过户  6：可测试 7：库存 8：预销户 9：已销户 10：已激活  11：测试期 12沉默期 99-其它
     * 用于操作卡状态操作，接回调用成功后，卡状态在半小时内生效。
     */
    public static final String CARD_OPERATOR = "/admin/api/v2/cardOperator";

    /**
     * 查询单卡数据接口
     * 包括套餐信息，激活日期、沉默期到期时间、SIM卡状态、GPRS流量使用情况等信息
     */
    public static final String QUERY_CARD = "/admin/api/v2/cardInfo";

    /**
     * sim卡主套餐充值接口
     * 用于预付费物联网卡主套餐充值续费
     */
    public static final String TRAFFIC_RECHARGE = "/admin/api/v2/renew";

    /**
     * sim卡加油包充值
     */
    public static final String PACKAGE_RECHARGE = "/admin/api/v2/package";

    /**
     * 用户套餐资费列表
     */
    public static final String USER_TRAFFIC_LIST = "/admin/api/v2/userTariffList";

    /**
     * 用户订购套餐查询
     */
    public static final String TRAFFIC_INFO_LIST = "/admin/api/v2/billingInfoList";

    /**
     * 按照支付订单号查询充值记录
     */
    public static final String QUERY_PAY_RECORD = "/admin/api/v2/renewalPayRecords";

    /**
     * 批量主套餐充值
     */
    public static final String RENEW_BATCH = "/admin/api/v2/renewBatch";

    /**
     * 批量加油包充值
     * 订购加油包后，在主套餐基础上叠加加油包流量。订购后1小时内生效，次月失效。
     */
    public static final String PACKAGE_BATCH = "/admin/api/v2/packageBatch";

    /**
     * 发送短信
     * 用于物联网卡发送消息的接口。
     * 接口执行成功后，会返回一个批次号，可根据该批次号查询该批次物联网卡消息发送是否成功。
     */
    public static final String CARD_SEND = "/admin/api/v2/ cardSend";

    /**
     * 查询短信发送状态
     * 用于查询某一批次物联网卡消息发送状态。
     */
    public static final String GET_CARD_SEND_STATUS = "/admin/api/v2/ getCardSendStatus";

    /**
     * 返回加密sign
     *
     * @return
     */
    public static String getSign() {
        return MD5Util.getMD5Code("appKey=" + APP_KEY + "&timeStamp=" + DateUtil.DATE_FORMAT.format(new Date()) + "&userName=" + USER_NAME + "&appSecret=" + APP_SECRET);
    }

//    /**
//     * 返回signTemp
//     * @param date
//     * @return
//     */
//    public static String getSignTemp(Date date){
//        return "appKey=" + APP_KEY + "&timeStamp=" + DateUtils.DATE_FORMAT.format(new Date()) +"&userName=" + USER_NAME + "&appSecret=" + APP_SECRET;
//    }

    /**
     * 返回sim卡供应商http所需请求头
     *
     * @return
     */
    public static Map<String, String> getHeader() {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        return header;
    }

    /**
     * 返回sim卡供应商http所需部分参数，具体到接口需要在自己添加
     *
     * @return
     */
    public static Map<String, String> getBody() {
        Map<String, String> body = new HashMap<String, String>();
        body.put("appKey", APP_KEY);
        body.put("timeStamp", DateUtil.DATE_FORMAT.format(new Date()));
        body.put("userName", USER_NAME);
        body.put("sign", getSign());
        return body;
    }

}
