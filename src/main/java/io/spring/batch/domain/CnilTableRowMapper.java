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

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.spring.batch.parametrage.TableParametrageCnil;

/**
 * @author 
 */
public class CnilTableRowMapper implements RowMapper<TableParametrageCnil> {
	@Override
	public TableParametrageCnil mapRow(ResultSet resultSet, int i) throws SQLException {
		return new TableParametrageCnil(
				resultSet.getLong("id"),
				resultSet.getString("type_traitement"),
				resultSet.getString("libelle"),
				resultSet.getString("nom_table"),
				resultSet.getString("nom_colonne"));

		/*this.traitementTypeId = traitementTypeId;
		this.processingType = processingType;
		this.label = label;
		this.tableName = tableName;
		this.columnName = columnName;*/

	}
}
