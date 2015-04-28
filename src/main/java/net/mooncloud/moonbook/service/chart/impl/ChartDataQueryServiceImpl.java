package net.mooncloud.moonbook.service.chart.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mooncloud.moonbook.entity.chart.ChartElement;
import net.mooncloud.moonbook.repository.chart.ChartDataQueryDao;
import net.mooncloud.moonbook.service.chart.ChartDataQueryService;

import org.springframework.beans.factory.annotation.Autowired;

public class ChartDataQueryServiceImpl implements ChartDataQueryService
{

	@Autowired
	ChartDataQueryDao chartDataQueryDao;

	// static Connection connection = null;
	// static Statement statement = null;

	public List<ChartElement> userPaymentFacetQuery(List<String> fields, List<String> aggregates, String index, String table, Map<String, Object> querys,
			List<String> orderby, String limit, String offset)
	{
		// try
		// {
		// connection = DBConf.getConnection();
		// statement = connection.createStatement();
		// connection.setAutoCommit(false);
		//
		// // statement.addBatch(SqlFacetQueryString.facetQueryString(querys,
		// // fields, aggregates, index, table, true));
		//
		// String facetQueryString =
		// SqlFacetQueryString.facetQueryString(fields, aggregates, index,
		// table, querys, orderby, limit, offset, true);
		// ResultSet resultSet = statement.executeQuery(facetQueryString);
		// while (resultSet.next())
		// {
		// // resultSet.get
		// }
		// statement.executeBatch();
		// connection.commit();
		// statement.clearBatch();
		// }
		// catch (SQLException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// catch (ClassNotFoundException e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// return null;

		String query = SqlFacetQueryString.facetQueryString(fields, aggregates, index, table, querys, orderby, limit, offset, true);
		Map<String, Object> queryMap = new HashMap<String, Object>(1);
		queryMap.put("query", query);
		return chartDataQueryDao.userPaymentFacetQuery(queryMap);
	}
}
