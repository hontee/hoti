package com.ikyer.site.front.cms;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ikyer.site.core.exception.SecurityException;
import com.ikyer.site.db.entity.ProductExample;
import com.ikyer.site.db.entity.RecommendExample;
import com.ikyer.site.db.entity.TopicExample;
import com.ikyer.site.front.BaseController;
import com.ikyer.site.rest.BaseService;

@Controller
@RequestMapping("/cms")
public class HomeCMS extends BaseController {
  
  @Resource
  private BaseService service;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public String home(Model model, HttpServletRequest request) throws SecurityException {
    
    // 统计类别数，产品数，主题数
    ProductExample pe = new ProductExample();
    TopicExample te = new TopicExample();
    int product = service.countProduct(pe);
    int topic = service.countTopic(te);
    
    // 统计待审核的产品数和主题数
    RecommendExample re = new RecommendExample();
    re.createCriteria().andStateEqualTo((byte) 1);
    int waitProduct = service.countRecommend(re);
    
    te.createCriteria().andStateEqualTo((byte) 2);
    int waitTopic = service.countTopic(te);
    
    model.addAttribute("product", product);
    model.addAttribute("topic", topic);
    
    model.addAttribute("waitProduct", waitProduct);
    model.addAttribute("waitTopic", waitTopic);
    return "cms/home";
  }

}
