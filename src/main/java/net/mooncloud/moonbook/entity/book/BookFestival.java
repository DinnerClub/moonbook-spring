package net.mooncloud.moonbook.entity.book;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * 节假日
 * 
 * @author yangjd
 *
 */
@Alias(value = "bookFestival")
public class BookFestival implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int thedate; // int(4) DEFAULT NULL COMMENT '日期yyyymmdd',
	private short year; // int(2) DEFAULT NULL COMMENT '年',
	private byte month; // int(1) DEFAULT NULL COMMENT '月',
	private byte date; // int(1) DEFAULT NULL COMMENT '日',
	private byte day;// tinyint(4) DEFAULT NULL COMMENT '星期',
	private String festival; // varchar(32) DEFAULT NULL COMMENT '节日',
	private byte lunar; // int(1) DEFAULT NULL COMMENT '农历或阳历:0-阳历;1-农历;其他',

	private short syn; // int(2) DEFAULT '0' COMMENT '同步标识',
	private Date created; // timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT
							// '创建时间',
	private Date updated; // timestamp NULL DEFAULT '0000-00-00 00:00:00'
							// COMMENT
							// '更新时间',
	private short status; // int(2) DEFAULT '0' COMMENT '状态',

	public int getThedate()
	{
		return thedate;
	}

	public void setThedate(int thedate)
	{
		this.thedate = thedate;
	}

	public short getYear()
	{
		return year;
	}

	public void setYear(short year)
	{
		this.year = year;
	}

	public byte getMonth()
	{
		return month;
	}

	public void setMonth(byte month)
	{
		this.month = month;
	}

	public byte getDate()
	{
		return date;
	}

	public void setDate(byte date)
	{
		this.date = date;
	}

	public byte getDay()
	{
		return day;
	}

	public void setDay(byte day)
	{
		this.day = day;
	}

	public String getFestival()
	{
		return festival;
	}

	public void setFestival(String festival)
	{
		this.festival = festival;
	}

	public byte getLunar()
	{
		return lunar;
	}

	public void setLunar(byte lunar)
	{
		this.lunar = lunar;
	}

	public short getSyn()
	{
		return syn;
	}

	public void setSyn(short syn)
	{
		this.syn = syn;
	}

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public Date getUpdated()
	{
		return updated;
	}

	public void setUpdated(Date updated)
	{
		this.updated = updated;
	}

	public short getStatus()
	{
		return status;
	}

	public void setStatus(short status)
	{
		this.status = status;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		Class<?> clazz = this.getClass();
		sb.append(clazz.getName()).append(" [");
		for (Field field : this.getClass().getDeclaredFields())
		{
			PropertyDescriptor pd;
			try
			{
				pd = new PropertyDescriptor(field.getName(), this.getClass());
				Method wM = pd.getReadMethod();// 获得写方法
				sb.append(field.getName()).append("=").append(wM.invoke(this)).append(", ");
			}
			catch (Exception e)
			{
				// e.printStackTrace();
			}
		}
		sb.append("]");
		return sb.toString();
	}

}
