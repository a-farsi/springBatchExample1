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

/**
 * @author 
 */
public class AdministrateurRowMapper implements RowMapper<Administrateur> {
	@Override
	public Administrateur mapRow(ResultSet resultSet, int i) throws SQLException {
		Administrateur admin = new Administrateur();

		admin.setPassword(resultSet.getString("password"));
		/*
		admin.setNumeroEmetteur(resultSet.getInt("numeroEmetteur"));
		admin.setLogin(resultSet.getString("login"));
		admin.setCodeBqeRIB(resultSet.getString("codeBqeRIB"));
		admin.setNumCpteRIB(resultSet.getString("numCpteRIB"));
		admin.setCleRIB(resultSet.getString("cleRIB"));
		admin.setDomiciliationRIB(resultSet.getString("domiciliationRIB"));
		*/
		return admin;
	}
}
