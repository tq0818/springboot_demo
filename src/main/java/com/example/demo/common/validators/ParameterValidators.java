package com.example.demo.common.validators;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.exception.ParamException;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class ParameterValidators {
	
	/**
	 * 验证入口
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T validate(String json, Class<T> clazz){
		if(StringUtils.isBlank(json)){
			throw ParamException.INVALID_PARAMETERS_NULL;
		}
		validateFormat(json);
		validateType(json,clazz);
		validateSize(json,clazz);
		T obj = validateData(json,clazz);
		return obj;
	}
	
	/**
	 * 进行json格式校验
	 * @param json
	 */
	protected static void validateFormat(String json){
		try{
			JSONObject.parseObject(json);
		}catch(Exception p){
			throw ParamException.INVALID_PARAMETERS_FORMAT;
		}
	}
	
	
	/**
	 * 进行参数类型校验
	 * @param json
	 * @param clazz
	 * @return 
	 */
	protected static <T> void validateType(String json, Class<T> clazz){		
		try{
			JSONObject.parseObject(json, clazz);
		}catch(Exception p){
			throw ParamException.INVALID_PARAMETERS_TYPE;
		}
	}
	
	/**
	 * 进行参数数量校验
	 * @param json
	 */
	protected static <T> void validateSize(String json, Class<T> clazz){
		JSONObject obj = JSONObject.parseObject(json);
		int classSize = clazz.getDeclaredFields().length;
		if(classSize != obj.size()){
			throw ParamException.INVALID_PARAMETERS_SIZE;
		}
	}
	
	/**
	 * 进行参数数据正确性校验
	 * @param json
	 * @param clazz
	 */
	protected static <T> T validateData(String json, Class<T> clazz, Class<?>... groups){
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		T obj = JSONObject.parseObject(json, clazz);
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, groups);
		if (!constraintViolations.isEmpty()) {
			String message = "";
			for (ConstraintViolation<T> violation : constraintViolations) {
				message = message + violation.getMessage() +";";
			}			
			throw new ParamException("INVALID_PARAMETERS_DATA","参数异常："+message);
		}
		return obj;
	}
	
}
