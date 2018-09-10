package com.springboottika.controller;

import com.springboottika.util.PdfParse;
import com.springboottika.util.StringUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.tika.exception.TikaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class Api {

    // 查询本地文件内容数据
    @RequestMapping("/{value}")
    public String find(@PathVariable("value") String value) throws IOException, TikaException, SAXException {
        // 获取文件内容
        String content = PdfParse.getContent("C:\\Users\\DELL\\Downloads\\pdf\\10018.pdf");
        // 全文搜索内容
        String retValue = StringUtil.strSearch(content, value, 20, 20);
        return retValue == null ? "未搜索到数据" : "搜索到的数据：" + retValue;
    }

    @Autowired
    private SolrClient solrClient;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${solr-config2}")
    private String host;

    // 查询服务器高亮数据
    @RequestMapping("/find/{value}")
    public String findS(@PathVariable("value") String value) {
        SolrQuery query = new SolrQuery("content:" + value);
        query.setStart(0);
        query.setRows(20);
        setHighlight(query);   // 开启高亮组件
        try {
            // 查询
            QueryResponse response = solrClient.query(query);
            // 获得高亮字段
            Map<String, Map<String, List<String>>> retMap = response.getHighlighting();
            for (String key : retMap.keySet()) {
                Map<String, List<String>> map = retMap.get(key);
                for (String key2 : map.keySet()) {
                    List<String> list = map.get(key2);
                    for (String va : list) {
                        System.err.println(va);
                    }
                }
            }
        } catch (Exception e) {
            log.error("查询索引异常");
            e.printStackTrace();
        }

        return "请查看系统打印结果";
    }

    /**
     * 开启高亮组件
     *
     * @param query
     */
    private void setHighlight(SolrQuery query) {
        query.setHighlight(true);   // 开启高亮组件
        query.setHighlightRequireFieldMatch(true);
        query.addHighlightField("content");
        query.setHighlightSimplePre("|||"); // 高亮单词的前缀
        query.setHighlightSimplePost("|||"); // 高亮单词的后缀
        query.setHighlightFragsize(20);
    }

    @RequestMapping("/upload")
    public void upload() throws IOException, SolrServerException {
        HttpSolrClient cli = new HttpSolrClient(host);
        ContentStreamUpdateRequest up = new ContentStreamUpdateRequest("/update/extract");
        File file = new File("C:\\Users\\DELL\\Downloads\\jar\\800fcd17d9021ee2a39ddb125cbeda0f.pdf");
        up.addFile(file, getFileContentType(file.getName()));
        up.setParam("literal.id", file.getName());
        up.setParam("fmap.content", "content");
        up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
        cli.request(up);
    }

    /**
     * @Author：sks
     * @Description：根据文件名获取文件的ContentType类型
     * @Date：
     */
    public static String getFileContentType(String filename) {
        String contentType;
        String prefix = filename.substring(filename.lastIndexOf(".") + 1);
        if (prefix.equals("xlsx")) {
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else if (prefix.equals("pdf")) {
            contentType = "application/pdf";
        } else if (prefix.equals("doc")) {
            contentType = "application/msword";
        } else if (prefix.equals("txt")) {
            contentType = "text/plain";
        } else if (prefix.equals("xls")) {
            contentType = "application/vnd.ms-excel";
        } else if (prefix.equals("docx")) {
            contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if (prefix.equals("ppt")) {
            contentType = "application/vnd.ms-powerpoint";
        } else if (prefix.equals("pptx")) {
            contentType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
        } else {
            contentType = "othertype";
        }

        return contentType;
    }
}
