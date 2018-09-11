package com.mysocket;

import java.io.*;
import java.net.*;
import net.sf.json.*;

public class Client {
    public static void main(String argv[]) throws Exception {
        Socket client = new Socket("127.0.0.1", 6799);
        System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);//发送给服务端的数据容器

        //获取键盘输入
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("你的用户名:");
        String name = br.readLine();
        System.out.println("你的密码:");
        BufferedReader cr = new BufferedReader(new InputStreamReader(System.in));
        String password = cr.readLine();
        JSONObject info = new JSONObject();
        info.put("ID", name);
        info.put("password", password);
        System.out.println(info.toString());

        out.writeUTF(info.toString());//传送给服务端的数据
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);//服务器响应数据容器
        System.out.println("服务器响应： " + in.readUTF());
        client.close();
    }

}