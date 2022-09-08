package com.chen.common.utils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SftpUtil {

    private static ChannelSftp channelSftp;
    private static Session session;

    /**
     * 使用sftp的方式连接远程服务器
     * @param userName
     * @param host
     * @param port
     * @param password
     * @return
     * @throws Exception
     */
    public static ChannelSftp login(String userName,String host,int port,String password) throws Exception{
        //JSch 是SSH2的一个纯Java实现。它允许你连接到一个sshd 服务器，
        // 使用端口转发，X11转发，文件传输等等。你可以将它的功能集成到你自己的 程序中。同时该项目也提供一个J2ME版本用来在手机上直连SSHD服务器
        JSch jSch = new JSch();
        //设置用户名和主机，端口一般是22,获取session
        session = jSch.getSession(userName,host,port);
        session.setPassword(password);
        //读取Java的配置文件
        Properties config = new Properties();
        config.put("StrictHostKeyChecking","no");
        session.setConfig(config);
        session.connect();
        //创建sftp连接
        Channel channel = session.openChannel("sftp");
        channel.connect();

        channelSftp = (ChannelSftp) channel;
        System.out.println("sftp connect success......");

        return channelSftp;
    }

    /**
     * 如果处于连接中，则关闭连接
     */
    public static void logOut(){
        if (null != channelSftp){
            if (channelSftp.isConnected()){
                channelSftp.disconnect();
            }
        }
        if (null != session){
            if (session.isConnected()){
                session.disconnect();
            }
        }
    }

    /**
     * 通过传入参数构建sftp连接，获取文件数据，关闭连接
     * @param userName
     * @param password
     * @param port
     * @param host
     * @param filePath
     * @return
     */
    public static List<String> readFile(String userName, String password, int port, String host, String filePath){
        //缓存流
        BufferedReader bufferedReader = null;
        List<String> listString = new ArrayList<>();
        try {
            //登陆服务器
            ChannelSftp sftp = SftpUtil.login(userName, host, port, password);
            bufferedReader = new BufferedReader(new InputStreamReader(sftp.get(filePath), StandardCharsets.UTF_8));
            String str;
            while ((str = bufferedReader.readLine()) != null) {
                listString.add(str);
            }
            bufferedReader.close();
            //退出连接
            SftpUtil.logOut();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listString;
    }
}
