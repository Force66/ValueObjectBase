/*
 * This software is licensed under the Apache License, Version 2.0
 * (the "License") agreement; you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.force66.vobase;

public class Arg extends ValueObjectBase {
	private String fieldName;
	private Object value;

	public Arg(String fieldName) {
		this.fieldName = fieldName;
	}

	public Arg is(Object value) {
		this.value = value;
		return this;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getValue() {
		return value;
	}

	public static Arg field(String fieldName) {
		return new Arg(fieldName);
	}

}
