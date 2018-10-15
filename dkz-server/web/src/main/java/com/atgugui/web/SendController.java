package com.atgugui.web;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atgugui.annotation.Log;
import com.atgugui.consumer.HelloService;
import com.atgugui.enums.exceptionals.StateEnum;
import com.atgugui.exceptions.BaseException;
import com.atgugui.model.Employee;
import com.atgugui.result.BaseResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

 
///**
// * @author zhuzhe
// * @date 2018/5/25 16:00
// * @email 1529949535@qq.com
// */
@Api("swagger hello")
@RestController
public class SendController {
 
	@Autowired
	private HelloService helloService;
	
	@Log(title="测试LOG自定义注解")
	@ApiOperation(value = "根据id查询",notes = "查询")
	@ApiImplicitParam(name ="id",value = "id",paramType = "path",required = true,dataType = "String")
    @GetMapping("/send")
    public String send(){
//    	String hello = helloService.hello();
		String hello = "hello";
        return hello;
    }
    
    @GetMapping("/insert")
    public BaseResult insert(){
    	try {
    		Employee hello = helloService.insert();
    		return BaseResult.newSuccess(hello);
		} catch (BaseException e) {//捕获自定义异常,并且获取code吗
			return BaseResult.newFailed(e.getStateEnum());
		}catch (Exception e) {//运行时异常统一抛出
			return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
		}
    }
    
    
    /** 使用restful 风格的 get请求
     * @param id
     * @return 
     */
    @GetMapping("/emp/{id}")
    public BaseResult getEmp(@PathVariable("id") Integer id){
       try {
    	   Employee byCacheEmployee = helloService.getByCache(id);
    	   return BaseResult.newSuccess(byCacheEmployee);
	} catch (Exception e) {
		return BaseResult.newFailed(StateEnum.ERROR_SYSTEM);
	}
    	
    }
    
}
