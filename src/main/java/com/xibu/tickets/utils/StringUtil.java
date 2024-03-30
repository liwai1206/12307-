package com.xibu.tickets.utils;

import java.util.Map;

/**
 * 判空工具类
 * @author 外哥
 *
 */
public class StringUtil {
	public static boolean checkNull( String ... strs ) {
		if ( strs == null || strs.length <= 0  ) {
			 return true ;
		}
		
		for (String str : strs) {
			if ( str == null || "".equals(str)) {
				return true ;
			}
		}
		return false ;
	}

    public static boolean checkNull(Map<String, Object> map) {
		if ( map == null || map.size() <= 0 ) {
			return true ;
		}

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			String value = map.get(key) + "";
			boolean result = checkNull(value);
			if ( result ) {
				return true;
			}
		}

		return false ;
    }
}
