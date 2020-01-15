package cn.xieyy.utils.solr;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

public class SolrTest {

	
	private final static String solrUrl = "http://localhost:8888/solr/product_new";
	private HttpSolrClient solrServer = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000)
											.withSocketTimeout(60000).build();
    
	/**
	 * 查询
	 */
	@Test
	public void solrSelect() {
		try {
	        SolrQuery query = new SolrQuery();
	        query.set("q", "id:xieyyy_test_000000001");
	        //query.setStart(0);	// 默认值：0
	        //query.setRows(10);	// 默认值：10
	        // 调用server的查询方法，查询索引库
	        QueryResponse response = solrServer.query(query);
	        // 查询结果
	        SolrDocumentList results = response.getResults();
	        // 查询结果总数
	        long num = results.getNumFound();	// 不管有没有设置setStart和setRows，结果都是总记录数
	        System.out.println("查询结果总数:" + num);
	        for (SolrDocument doc : results) {
	        	System.out.print(""+doc.get("id"));
	        	System.out.print(" | "+doc.get("productName"));
	        	System.out.print(" | "+doc.get("model"));
	        	System.out.print(" | "+doc.get("province"));
	        	System.out.print(" | "+doc.get("city"));
	        	System.out.print(" | "+doc.get("companyName"));
	        	System.out.println();
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加
	 */
	@Test
	public void solrAdd() {
		try {
			SolrInputDocument document = new SolrInputDocument();
	        // 注意：id的域不能少
	        document.addField("id", "xieyyy_test_000000001");
	        document.addField("productName", "xieyy_test");
	        document.addField("model", "model1");
	        document.addField("province", "广东");
	        document.addField("city", "珠海");
	        solrServer.add(document);
	        solrServer.commit();
	        System.out.println("solrAdd:完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除
	 */
	@Test
	public void solrDelete() {
		try {
			solrServer.deleteById("xieyyy_test_000000001");
	        solrServer.commit();
			System.out.println("solrDelete:完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
