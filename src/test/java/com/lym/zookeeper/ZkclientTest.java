package com.lym.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.nio.charset.Charset;

/**
 * Created by liuyumeng on 2018/9/19.
 */
public class ZkclientTest {
    public static void main(String[] args) {
        //建立链接
        String connetAddr = "118.24.99.29:2181";
        ZkClient zkc = new ZkClient(new ZkConnection(connetAddr), 10000);

        //1.创建一个持久化有序的节点 参数可选
        //zkc.create("/lym","lym-data", CreateMode.PERSISTENT_SEQUENTIAL);

        //2.创建一个持久化递归节点，但不能赋值
        //zkc.createPersistent("/lymdg/child",true);

        //3.删除节点
        //zkc.delete("/lymdg");


        //4.获取子节点并读取数据
        //读取数据序列化报错，要自定义序列化实现 https://my.oschina.net/u/118686/blog/1935238
          zkc.setZkSerializer(new MyZkSerializer());
//        List<String> list = zkc.getChildren("/lyms0000000003");
//        for(String p : list){
//            System.out.println(p);
//            String rp = "/lyms0000000003/" + p;
//            String data = zkc.readData(rp);
//            System.out.println("节点为：" + rp + "，内容为: " + data);
//        }

        //5.更新节点  中文存储有问题：新内容 -> �� t 	新内容
        //zkc.writeData("/lyms0000000003/c1", "新内容");
        //System.out.println((String)zkc.readData("/lyms0000000003/c1"));

        //6.订阅子节点变化
//        - parentPath 为监听节点全路径
//                - currentChilds为新的子节点列表
//        IZKChildListener事件说明针对于下面三个事件触发：
//        - 新增子节点
//                - 减少子节点
//                - 删除节点
//        注意： 不监听节点内容的变化
//        zkc.subscribeChildChanges("/lyms0000000003", new IZkChildListener() {
//            @Override
//            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
//                System.out.println("parentPath: " + parentPath);
//                System.out.println("currentChilds: " + currentChilds);
//            }
//        });

        //7.订阅节点内容变化
        zkc.subscribeDataChanges("/lyms0000000003", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("data is changed: " + s);
            }

            //delete 或 rmr
            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("data is deleted: " + s);
            }
        });

        try {
            Thread.sleep(300_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyZkSerializer implements ZkSerializer {
        public Object deserialize(byte[] bytes) throws ZkMarshallingError {
            return new String(bytes, Charset.forName("UTF-8"));
        }

        public byte[] serialize(Object obj) throws ZkMarshallingError {
            return String.valueOf(obj).getBytes(Charset.forName("UTF-8"));
        }
    }
}
