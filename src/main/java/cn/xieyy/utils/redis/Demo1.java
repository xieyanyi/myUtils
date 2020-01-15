package cn.xieyy.utils.redis;

import redis.clients.jedis.Jedis;

public class Demo1 {

	private static Jedis jedis;

	public static void main(String[] args) throws Exception{
		jedis = new Jedis("10.1.33.181",6379);
		///jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        //System.out.println("服务正在运行: "+jedis.ping());
        
        String f1 = jedis.get("f1");
        System.out.println(f1);
	}
}
