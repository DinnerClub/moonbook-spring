package net.mooncloud.moonbook.service.chart;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.chart.ChartElement;

/**
 * 图表数据查询
 * 
 * @author yangjd
 *
 */

public interface ChartDataQueryService
{
	/**
	 * 用户支出Facet查询
	 * 
	 * @param fields
	 *            维度组合
	 * @param aggregates
	 *            聚合函数
	 * @param index
	 *            聚合字段
	 * @param table
	 *            表
	 * @param querys
	 *            查询条件组合(AND)
	 * @param orderby
	 *            排序字段
	 * @param limit
	 * @param offset
	 */
	public List<ChartElement> userPaymentFacetQuery(List<String> fields, List<String> aggregates, String index, String table, Map<String, Object> querys,
			List<String> orderby, String limit, String offset);
}
