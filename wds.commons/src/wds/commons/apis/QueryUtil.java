package wds.commons.apis;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import wds.commons.apis.Query.Condition;

public class QueryUtil {

    public static QueryParams parserRequest(String query, String range) {
        try {
            query = URLDecoder.decode(query, "UTF-8");
            QueryParams queryParams = BaseUtil.readValue(query, QueryParams.class);
            queryParams.setFirst(0);
            queryParams.setMax(Integer.MAX_VALUE);
            try {
                String[] ranges = range.replace("items=", "").split("-");
                int from = Integer.parseInt(ranges[0]);
                int to = Integer.parseInt(ranges[1]);
                queryParams.setFirst(from);
                queryParams.setMax(to - from + 1);
            } catch ( Exception e ) {

            }
            return queryParams;
        } catch ( Exception e ) {
            throw new IllegalArgumentException("查询参数错误", e);
        }
    }
    
    public static QueryParams parserRequestQuery(HttpServletRequest request) {
        try {
        	JSONObject jo = toJSON(request);
        	
            String query = URLDecoder.decode(jo.toString(), "UTF-8");
            QueryParams queryParams = BaseUtil.readValue(query, QueryParams.class);
            queryParams.setFirst(0);
            queryParams.setMax(Integer.MAX_VALUE);
            try {
            	String range = "";
                String[] ranges = range.replace("items=", "").split("-");
                int from = Integer.parseInt(ranges[0]);
                int to = Integer.parseInt(ranges[1]);
                queryParams.setFirst(from);
                queryParams.setMax(to - from + 1);
            } catch ( Exception e ) {

            }
            return queryParams;
        } catch ( Exception e ) {
            throw new IllegalArgumentException("查询参数错误", e);
        }
    }
    
    public static JSONObject toJSON(HttpServletRequest request){
    	JSONObject jo = new JSONObject();
    	Enumeration<?> em = request.getParameterNames();
    	 while (em.hasMoreElements()) {
    	    String name = (String) em.nextElement();
    	    String value = request.getParameter(name);
    	    jo.put(name, value);
    	}
    	 return jo;
    }
    
    public static Query getQuery(Condition condition, String key, String value){
		Query query = new Query();
		query.setCondition(condition);
		query.setField(key);
		query.setValue(value);
		return query;
	}
}
