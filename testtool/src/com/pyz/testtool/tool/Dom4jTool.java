package com.pyz.testtool.tool;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 暂时只支持默认文件路径
 * @author PiYongze
 *
 */
public class Dom4jTool {

	public static Map handle() throws Exception{
		
		Map map=new HashMap();
        // 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象  
        Document document = reader.read(new File("src/com/pyz/testtool/xml/testtool.xml"));  
        //获取根节点元素对象  
        Element node = document.getRootElement();    
        String entity = node.element("document").element("table").getStringValue();
        map.put("entity",entity);
        List<Element> elements=node.element("document").element("columns").elements("column");
        for(Element element:elements){
        	map.put(element.element("pos").getStringValue(), element.element("field").getStringValue());
        }
        return map;
	}	
}
