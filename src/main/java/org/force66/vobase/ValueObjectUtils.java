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

import java.lang.reflect.Field;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;

class ValueObjectUtils {

	protected static void copyAllFields(Object source, Object target) {
		Validate.notNull(source, "Null source object fields can't be copied");
		Validate.notNull(target, "Null target object fields can't be copied");
		Validate.isTrue(ClassUtils.isAssignable(target.getClass(), source.getClass()), "Source and target classes must be assignable");
		
		Object value = null;
		for (Field field : FieldUtils.getAllFields(source.getClass())) {
			try {
				value = FieldUtils.readField(source, field.getName(), true);
			} catch (Exception e) {
				throw new ValueObjectException("Problem reading value object field value", e)
						.addContextValue("fieldName", field.getName())
						.addContextValue("className", source.getClass().getName());
			}

			try {
				FieldUtils.writeField(target, field.getName(), value, true);
			} catch (Exception e) {
				throw new ValueObjectException("Problem setting value object field value", e)
						.addContextValue("fieldName", field.getName())
						.addContextValue("className", target.getClass().getName());
			}
		}
	}

}
