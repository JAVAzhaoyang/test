package com.zl.demo.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ListSort {
	/**
	 * 
	 * 返回一个根据Map<K, V>的K进行降序排序生成的新的list
	 * Int
	 * 降序
	 */
	public static List<Map<String, Object>> sortByIntByDesc(List<Map<String, Object>> list, String key) {
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				BigDecimal a=new BigDecimal(String.valueOf(o1.get(key)));
				BigDecimal b=new BigDecimal(String.valueOf(o2.get(key)));
				return b.compareTo(a);
			}
		});
		return list;
	}

	/**
	 * 
	 * 返回一个根据Map<K, V>的K进行升序排序生成的新的list
	 * Int
	 * 升序
	 */
	public static List<Map<String, Object>> sortByIntByASC(List<Map<String, Object>> list, String key) {
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				BigDecimal a=new BigDecimal(String.valueOf(o1.get(key)));
				BigDecimal b=new BigDecimal(String.valueOf(o2.get(key)));
				return a.compareTo(b);
			}
		});
		return list;
	}
	
	/**
	 * 
	 * 返回一个根据Map<K, V>的K进行降序排序生成的新的list
	 * Date
	 * 降序
	 */
	public static List<Map<String, Object>> sortByDateByDesc(List<Map<String, Object>> list, String key) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				// 进行判断
				try {
					return sdf.parse(String.valueOf(o2.get(key))).compareTo(sdf.parse(String.valueOf(o1.get(key))));
				} catch (ParseException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					return 0;
				}
			}
		});
		return list;
	}
	
	/**
	 * 
	 * 返回一个根据Map<K, V>的K进行升序排序生成的新的list
	 * Date
	 * 升序
	 */
	public static List<Map<String, Object>> sortByDateByASC(List<Map<String, Object>> list, String key) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Collections.sort(list, new Comparator<Map<String, Object>>() {
			@Override
			public int compare(Map<String, Object> o1, Map<String, Object> o2) {
				// 进行判断
				try {
					return sdf.parse(String.valueOf(o2.get(key))).compareTo(sdf.parse(String.valueOf(o1.get(key))));
				} catch (ParseException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
					return 0;
				}
			}
		});
		return list;
	}
}
