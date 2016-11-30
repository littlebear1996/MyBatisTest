package com.test01;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.ibatis.common.resources.Resources;


public class TestMyBatis {

	private static SqlSessionFactory sqlSessionFactory;
	private static Reader reader;
	static{
		try {
			reader = Resources.getResourceAsReader("mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 查找
	 */
	public static Employeer findEmployeerById(int id) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Employeer employeer = (Employeer) session.selectOne(
					"com.test01.employeer_mapper.findEmployeerById", id);
			if (employeer == null) {
				System.out.println("查询不存在！");
			} else {
				System.out.println(employeer);
			}
			return employeer;
		} finally {
			session.close();
		}

	}
	/**
	 * 
	 * 插入
	 */
	public static void insertEmployeer(Employeer employeer){
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int insertCount = session.insert("com.test01.employeer_mapper.addEmployeer",employeer);
			System.out.println("当前插入的employeer_id :"+ employeer.getEmployeer_id() + "当前插入数据库中条数:"+insertCount);
			session.commit();
		} finally {
			session.close();
		}
	}
	/**
	 * 
	 * 删除
	 */
	public static void deleteEmployeer(int id){
		SqlSession session = null;  
        try {  
            session = sqlSessionFactory.openSession();  
             //返回值是记录条数    
             int resultCount=session.delete("com.test01.employeer_mapper",id);   
             System.out.println("当前删除数据库中条数: "+resultCount);     
            session.commit() ;            
        } finally {  
            session.close();  
        }  
	}
	/**
	 * 
	 * 更新
	 */
	public static void updateEmployeer(Employeer employeer){
		SqlSession session = null;  
        try {  
            session = sqlSessionFactory.openSession();   
            session.update("com.test01.employeer_mapper",employeer);      
            session.commit() ;            
        } finally {  
            session.close();  
        }  
	}
	public static void main(String[] args) {
		Employeer employeer1=new Employeer();  
        employeer1.setEmployeer_name("李四");  
        employeer1.setEmployeer_age(23);  
        employeer1.setEmployeer_department("产品一部");  
        employeer1.setEmployeer_worktype("开发工程师");  
          
        Employeer employeer2=new Employeer();  
        employeer2.setEmployeer_name("张三");  
        employeer2.setEmployeer_age(30);  
        employeer2.setEmployeer_department("产品二部");  
        employeer2.setEmployeer_worktype("测试工程师");  
          
        Employeer employeer3=new Employeer();  
        employeer3.setEmployeer_name("小王");  
        employeer3.setEmployeer_age(22);  
        employeer3.setEmployeer_department("产品三部");  
        employeer3.setEmployeer_worktype("数据分析师");  
          
          
        Employeer employeer4=new Employeer();  
        employeer4.setEmployeer_name("明明");  
        employeer4.setEmployeer_age(22);  
        employeer4.setEmployeer_department("财会部");  
        employeer4.setEmployeer_worktype("财务人员");
        findEmployeerById(4);

	}

}
