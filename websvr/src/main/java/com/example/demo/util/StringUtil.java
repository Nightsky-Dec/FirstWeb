package com.example.demo.util;

//import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.*;

public class StringUtil {

	private static ObjectMapper om;
	private final static Logger logger = LoggerFactory.getLogger(StringUtil.class);
	
	public static boolean isNull(String str)
	{
		return str == null || str.trim().isEmpty();
	}
	
	public static boolean isNotNull(String str)
	{
		return str != null && !str.trim().isEmpty();
	}
	
	public static String trim(String str)
	{
		return str == null ? null : str.trim();
	}
	
	public static String join(List<String> list)
	{
		return StringUtils.collectionToCommaDelimitedString(list);
	}

    public static String join(Collection<String> list)
    {
        return StringUtils.collectionToCommaDelimitedString(list);
    }
	
	public static String joinStringIds(String stringIds)
	{
		return StringUtils.collectionToDelimitedString(StringUtils.commaDelimitedListToSet(stringIds), ",", "'", "'");
	}
	
	private static ObjectMapper getObjectMapper()
	{
		if(om == null)
		{
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.ALWAYS);
			om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		}
		
		return om;
	}
	
	public static String getJson(Object obj) 
	{
		try
		{
			return obj == null ? "" :getObjectMapper().writeValueAsString(obj);
		}
		catch(Exception e)
		{
			logger.error("write Object to json failed " + obj, e);
		}
		return null;
	}
	
	public static <T> T getObject(String json, Class<T> t) throws JsonParseException, JsonMappingException, IOException
	{
		return getObjectMapper().readValue(json, t);
	}
	
	public static String formatQuery(String key){
		return key.replaceAll("%", "");
	}
	
	public static String decodeBase64Str(String input)  
    {  
        byte[] debytes =Base64.getDecoder().decode(input);
        return new String(debytes);  
    }

    // 对象转成Json字符串
    public static <T> String mapToJson(Map<String, T> map) {
//        String jsonStr = JSON.toJSONString(map);
//        return jsonStr;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonStr = mapper.writeValueAsString(map);
            return jsonStr;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Json字符串转成对象
    public static Map<String, Object> StringToJsonMap(String message) {
//        Map<String, Object> map  = JSON.parseObject(message, new HashMap<String, Object>().getClass());
//        return map;
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> map = mapper.readValue(message, new HashMap<String, Object>().getClass());
            return map;
        } catch (java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    public static String ToJsonString(Object obj) {
//        return JSON.toJSONString(obj);
//    }

	public static String convertHexToString(String hex){

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		//49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for( int i=0; i<hex.length()-1; i+=2 ){

			//grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			//convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			//convert the decimal to character
			sb.append((char)decimal);

			temp.append(decimal);
		}

		return sb.toString();
	}

	/**
	 * 字符串转换unicode
	 */
	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}

	//将图片的base64字符串转成图片
	public static boolean generateImage(String imgString, String imgPath) {
        if (imgString == null)
            return false;
        Base64.Decoder decoder = Base64.getDecoder();
        try {
            byte[] b = decoder.decode(imgString);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(imgPath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	//将图片转成base64字符串
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(data);
	}
}
