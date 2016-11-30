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
	 * ����
	 */
	public static Employeer findEmployeerById(int id) {
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			Employeer employeer = (Employeer) session.selectOne(
					"com.test01.employeer_mapper.findEmployeerById", id);
			if (employeer == null) {
				System.out.println("��ѯ�����ڣ�");
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
	 * ����
	 */
	public static void insertEmployeer(Employeer employeer){
		SqlSession session = null;
		try {
			session = sqlSessionFactory.openSession();
			int insertCount = session.insert("com.test01.employeer_mapper.addEmployeer",employeer);
			System.out.println("��ǰ�����employeer_id :"+ employeer.getEmployeer_id() + "��ǰ�������ݿ�������:"+insertCount);
			session.commit();
		} finally {
			session.close();
		}
	}
	/**
	 * 
	 * ɾ��
	 */
	public static void deleteEmployeer(int id){
		SqlSession session = null;  
        try {  
            session = sqlSessionFactory.openSession();  
             //����ֵ�Ǽ�¼����    
             int resultCount=session.delete("com.test01.employeer_mapper",id);   
             System.out.println("��ǰɾ�����ݿ�������: "+resultCount);     
            session.commit() ;            
        } finally {  
            session.close();  
        }  
	}
	/**
	 * 
	 * ����
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
        employeer1.setEmployeer_name("����");  
        employeer1.setEmployeer_age(23);  
        employeer1.setEmployeer_department("��Ʒһ��");  
        employeer1.setEmployeer_worktype("��������ʦ");  
          
        Employeer employeer2=new Employeer();  
        employeer2.setEmployeer_name("����");  
        employeer2.setEmployeer_age(30);  
        employeer2.setEmployeer_department("��Ʒ����");  
        employeer2.setEmployeer_worktype("���Թ���ʦ");  
          
        Employeer employeer3=new Employeer();  
        employeer3.setEmployeer_name("С��");  
        employeer3.setEmployeer_age(22);  
        employeer3.setEmployeer_department("��Ʒ����");  
        employeer3.setEmployeer_worktype("���ݷ���ʦ");  
          
          
        Employeer employeer4=new Employeer();  
        employeer4.setEmployeer_name("����");  
        employeer4.setEmployeer_age(22);  
        employeer4.setEmployeer_department("�ƻᲿ");  
        employeer4.setEmployeer_worktype("������Ա");
        findEmployeerById(4);

	}

}
