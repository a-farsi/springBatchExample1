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

//import java.util.Date;

/**
 * @author
 */
public class Police {

	private long id;

	private String pNotes;

	public long getId() {
		return id;
	}

	public String getpNotes() {
		return pNotes;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setpNotes(String pNotes) {
		this.pNotes = pNotes;
	}

	@Override
	public String toString() {
		return "Police [id=" + id + ", pNotes=" + pNotes + "]";
	}

}