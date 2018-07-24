/*
 * Copyright 2015 the original author or authors.
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
package io.spring.batch.domain;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

//import com.foncia.assurance.metier.Police;

/**
 * @author 
 */
public class PoliceRowMapper implements RowMapper<Police> {

	private String columnName;
	Method method;

	// Method[] methods = Class.forName(Police).

	public PoliceRowMapper(String columnName) {
		this.columnName = columnName;
	}

	@Override
	public Police mapRow(ResultSet resultSet, int i) throws SQLException {
		Police police = new Police();

		police.setpNotes(resultSet.getString(columnName));
		police.setId(Long.parseLong(resultSet.getString("ID")));
		return police;
	}
}
