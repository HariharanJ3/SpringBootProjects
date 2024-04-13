
package com.global.translator.Utils;

import javax.activation.DataSource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.global.translator.controller.UserActions;

/**
 * @author Hariharan J
 *
 * 24-Sep-2022
 */
public class CommonMethods extends CommonUtils {
	
	public static boolean isLogin(HttpSession session) {
		
		_log.info("isLogin method is loading now....");	
		
		if(session != null) {
			
			if((String)session.getAttribute(LOGED_IN) != null && ((String)session.getAttribute(LOGED_IN)).equals("true") &&
					!((String)session.getAttribute(LOGED_IN)).equals(""))
				return true;
		}
		return false;
	}
	private static final Logger _log=LoggerFactory.getLogger(UserActions.class);
}
