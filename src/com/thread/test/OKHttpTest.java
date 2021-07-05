//package com.thread.test;
//
//import okhttp3.*;
//
//import java.io.IOException;
//
//public class OKHttpTest {
//
//    public static void main(String[] args) {
//
//        requestByOkHttp();
//
//    }
//
//    /**
//     * 一个最简单的使用okhttp的例子
//     */
//    public static void requestByOkHttp() {
//
//        //1. 使用RequestBuilder传入url等http请求需要的参数信息
//        Request.Builder requestBuilder = new Request.Builder();
//
//        requestBuilder.url("http://www.baidu.com");
//
//        //2. 使用Builder.build构建出一个Request请求类
//        Request request = requestBuilder.build();
//
//        //3. 构建client客户端
//        OkHttpClient okHttpClient = new OkHttpClient();
//
//        //4. 使用客户端构建成一个Call对象，虚拟出来的代表每个"请求call"，执行executed方法，会关联到Okhttpcleint的dispatcher进行，放到异步的队列中
//        Call call = okHttpClient.newCall(request);
//
//        //5. 执行到ReallCall的execute方法，会执行okhttpclient的Dispatcher类，然后执行execute方法，添加到异步的请求队列中
//        //这块可以讲讲Dispatcher类的设计，主要是runningAsyncCalls以及readAsyncCalls两个双头队列（比单纯的队列更加高效），以及这里的最大并发请求队列64，还有单个主机5个请求的相关设置
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                System.out.println("okhttp 请求失败");
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("okhttp 请求成功");
//
//            }
//        });
//
//    }
//
//}
