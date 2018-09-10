package com.springboottika.util;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author ShengGuang.Ye
 * @version V1.0
 * @Description: 读取PDF文件
 * @date 2018/8/3 10:38
 */
public class PdfParse {

    /**
     * 获取文件内容
     *
     * @param path
     * @return
     * @throws TikaException
     * @throws SAXException
     * @throws IOException
     */
    public static String getContent(String path) throws TikaException, SAXException, IOException {
        FileInputStream inputstream = new FileInputStream(new File(path));
        BodyContentHandler handler = new BodyContentHandler(inputstream.available());
        Metadata metadata = new Metadata();
        ParseContext pcontext = new ParseContext();
        //parsing the document using PDF parser
        PDFParser pdfparser = new PDFParser();
        pdfparser.parse(inputstream, handler, metadata, pcontext);
        return handler.toString();
    }
}
