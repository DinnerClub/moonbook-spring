package net.mooncloud.moonbook.repository.chart;

import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.chart.ChartElement;

/**
 * 图表数据查询
 * 
 * @author yangjd
 *
 */

public interface ChartDataQueryDao
{
	/**
	 * 用户支出Facet查询
	 * 
	 * @param query
	 *            查询SQL; query: sql
	 */
	public List<ChartElement> userPaymentFacetQuery(Map query);
}
