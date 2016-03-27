package com.kuaiba.site.front.taglib;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.tagext.TagSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kuaiba.site.db.entity.GlobalIDs;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

/**
 * 自定义标签父类
 * @author larry.qi
 */
public class AbstractTagSupport extends TagSupport {

	private static final long serialVersionUID = -5584097225723500445L;
	private static Logger logger = LoggerFactory.getLogger(AbstractTagSupport.class);

	// 获取RootMap对象
	private Map<String, Object> root;
	
	public AbstractTagSupport() {
		root = new HashMap<>();
	}

	/**
	 * 获取FreeMarker配置
	 * @return
	 */
	private Configuration getFreeMarker() {
		Configuration config = new Configuration(Configuration.VERSION_2_3_23);
		config.setDefaultEncoding(GlobalIDs.CHARSET);
		config.setOutputEncoding(GlobalIDs.CHARSET);
		config.setURLEscapingCharset(GlobalIDs.CHARSET);
		config.setServletContextForTemplateLoading(pageContext.getServletContext(), GlobalIDs.FREEMARK_TEMP);
		return config;
	}
	
	/**
	 * 渲染模板
	 * @param template
	 * @param dataModel
	 * @throws Exception
	 */
	protected void render(String template) {
		try {
			Template t = getFreeMarker().getTemplate(template, GlobalIDs.CHARSET);
			t.process(root, pageContext.getOut());
		} catch (TemplateNotFoundException e) {
			logger.debug("模板没有找到：{}", template);
		} catch (MalformedTemplateNameException e) {
			logger.debug("错误的模板名称：{}", template);
		} catch (ParseException e) {
			logger.debug("模板解析异常：{}", template);
		} catch (IOException e) {
			logger.debug("IO异常：{}", template);
		} catch (TemplateException e) {
			logger.debug("模板异常：{}", template);
		}
	}

	protected void addAttribute(String key, Object value) {
		root.put(key, value);
	}
	
}
