package wds.commons.apis;
import java.util.ArrayList;
import java.util.List;

public class QueryParams {
	
	/**查询条件*/
	private List<Query> querys = new ArrayList<Query>();
	/**排序条件*/
	private String sort;
	/**查询开始位置*/
	private int first = 0;
	/**查询结束位置*/
	private int max = Integer.MAX_VALUE;
	
	public List<Query> getQuerys() {
		return querys;
	}
	public void setQuerys(List<Query> querys) {
		this.querys = querys;
	}
	
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	
}
