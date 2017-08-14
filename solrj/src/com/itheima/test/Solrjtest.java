package com.itheima.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Before;
import org.junit.Test;

public class Solrjtest {
	@Test
	public void testCreateAndUpdateIndex() throws SolrServerException, IOException{
		//创建httpSolrServer对象
		//设置solr服务接口，浏览器客户端地址http://127.0.0.1:8091/solr/#/
		String baseURL="http://127.0.0.1:8091/solr";
		HttpSolrServer httpSolrServer=new HttpSolrServer(baseURL);
		//创建SolrInputDocument对象
		SolrInputDocument document=new SolrInputDocument();
		document.addField("id", "c001");
		document.addField("content", "曲强大仙");
		httpSolrServer.add(document);
		httpSolrServer.commit();
		
	}
	//提取httpSolrserver创建
	private HttpSolrServer httpSolrServer;
	@Before
	public void init() {
		//1.创建HttpSolrServer对象
		//设置solr服务器接口，浏览前客户端地址http：//127.0.0.1:8091/solr
		String baseURL="http://127.0.0.1:8091/solr/";
		this.httpSolrServer=new HttpSolrServer(baseURL);
		
	}
	@Test
	public void testDeleteIndex() throws SolrServerException, IOException{
		//根据id删除索引数据
//				this.httpSolrServer.deleteById("c001");
				//根据条件删除（如果是*：*就表示全部删除，慎用
			this.httpSolrServer.deleteByQuery("*:*");
		//提交
		
				this.httpSolrServer.commit();
	}
	@Test
	public void testSearchIndex1() throws SolrServerException{
		//创建搜索对象
		SolrQuery query=new SolrQuery();
		//设置搜索条件
		query.setQuery("*:*");
		//发起搜索请求
		QueryResponse response=this.httpSolrServer.query(query);
		SolrDocumentList results = response.getResults();
		System.out.println("搜索到的结果总数是："+results.getNumFound());
		for (SolrDocument solrDocument : results) {
			System.out.println("==========");
			System.out.println("id"+solrDocument.get("id"));
			System.out.println("name"+solrDocument.get("content"));
		}
		
	}
}
