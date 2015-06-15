package com.zuoxiaolong.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author 左潇龙
 * @since 2015年5月29日 上午1:04:31
 */
public abstract class ProvinceDao extends BaseDao {
	
	public static List<Map<String, String>> getProvinces() {
		return execute(new Operation<List<Map<String, String>>>() {
			@Override
			public List<Map<String, String>> doInConnection(Connection connection) {
				List<Map<String, String>> result = new ArrayList<Map<String,String>>();
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("select * from dictionary_province");
					while (resultSet.next()) {
						result.add(transfer(resultSet));
					}
				} catch (SQLException e) {
					error("query dictionary_province failed ..." , e);
				}
				return result;
			}
		});
	}
	
	public static Map<String, String> transfer(ResultSet resultSet){
		Map<String, String> tag = new HashMap<String, String>();
		try {
			tag.put("id", resultSet.getString("id"));
			tag.put("name", resultSet.getString("name"));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return tag;
	}
	
}
