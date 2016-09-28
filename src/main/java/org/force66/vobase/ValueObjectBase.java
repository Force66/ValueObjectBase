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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * Base Value object abbreviating value object code.
 * 
 * @author D. Ashmore
 *
 */
public abstract class ValueObjectBase implements Cloneable {

	/**
	 * Will initialize a value object with all given named parameter values.
	 * 
	 * @param namedArgs
	 * @return
	 */
	public <T> T init(Arg... namedArgs) {
		for (Arg arg : namedArgs) {
			assignValue(this, arg);
		}
		return (T) this;
	}

	/**
	 * Will copy/clone the given value object overriding and fields with given
	 * name values.
	 * 
	 * @param namedArgs
	 * @return
	 */
	public <T> T copy(Arg... namedArgs) {
		ValueObjectBase newCopy = null;
		try {
			newCopy = this.getClass().newInstance();
		} catch (Exception e) {
			throw new ValueObjectException("Value Objects must have a null constructor", e)
					.addContextValue("class", this.getClass().getName());
		}
		ValueObjectUtils.copyAllFields(this, newCopy);

		for (Arg arg : namedArgs) {
			assignValue(newCopy, arg);
		}
		return (T) newCopy;
	}

	private void assignValue(ValueObjectBase obj, Arg arg) {
		try {
			FieldUtils.writeField(obj, arg.getFieldName(), arg.getValue(), true);
		} catch (Exception e) {
			throw new ValueObjectException("Problem assigning value object field value", e)
					.addContextValue("named field argument", arg);
		}
	}

	@Override
	public Object clone() {
		return this.copy();
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
