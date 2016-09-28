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
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.force66.beantester.valuegens.ValueGenerator;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ValueObjectBaseTest {

	TestVO populatedVO;

	@Before
	public void setUp() throws Exception {
		ValueGeneratorFactory valueFactory = new ValueGeneratorFactory();
		populatedVO = new TestVO();

		for (Field field : TestVO.class.getDeclaredFields()) {
			ValueGenerator valueGen = valueFactory.forClass(field.getType());
			if (valueGen != null) {
				FieldUtils.writeField(field, populatedVO, valueGen.makeValues()[0], true);
			}
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testToStringEmptyObject() throws Exception {
		String emptyObjectToString = new TestVO().toString();
		// System.out.println(emptyObjectToString);
		Assert.assertEquals(70, StringUtils.countMatches(emptyObjectToString, "="));
	}

	@Test
	public void testToStringPopulatedObject() throws Exception {
		String objString = populatedVO.toString();
		// System.out.println(objString);
		Assert.assertEquals(70, StringUtils.countMatches(objString, "="));
		Assert.assertTrue(objString.contains("testPublicObjectChar=" + populatedVO.testPublicObjectChar));
	}

	@Test
	public void testEqualsHashcode() throws Exception {
		Assert.assertEquals(populatedVO, populatedVO);
		Assert.assertEquals(populatedVO.hashCode(), populatedVO.hashCode());

		// TestVO clone = (TestVO) populatedVO.clone();
		// Assert.assertEquals(populatedVO, clone);
		// Assert.assertEquals(populatedVO.hashCode(), clone.hashCode());
		// clone.testBeanObjectArrayBoolean = new Boolean[] { Boolean.TRUE,
		// Boolean.FALSE, Boolean.FALSE };
		// populatedVO.testBeanObjectArrayBoolean = new Boolean[] {
		// Boolean.TRUE, Boolean.FALSE };
		// Assert.assertTrue(populatedVO != clone);
		// Assert.assertNotEquals(populatedVO.testBeanObjectArrayBoolean.length,
		// clone.testBeanObjectArrayBoolean.length);
		// Assert.assertNotEquals(populatedVO, clone);
	}

	@Test
	public void testInit() throws Exception {
		TestVO tVo = new TestVO().init(Arg.field("testBeanPrimitiveInt").is(4));
		Assert.assertEquals(4, tVo.getTestBeanPrimitiveInt());
	}

	@Test
	public void testCopy() throws Exception {
		Assert.assertNotEquals(4, populatedVO.getTestBeanPrimitiveInt());
		TestVO tVo = populatedVO.copy(Arg.field("testBeanPrimitiveInt").is(4));

		Assert.assertNotEquals(4, populatedVO.getTestBeanPrimitiveInt());
		Assert.assertEquals(4, tVo.getTestBeanPrimitiveInt());
	}

	@Test
	public void testClone() throws Exception {
		TestVO clone = (TestVO) populatedVO.clone();
		Assert.assertEquals(populatedVO, clone);
	}

	public static class TestVO extends ValueObjectBase {

		public byte testPublicPrimitiveByte;
		public short testPublicPrimitiveShort;
		public int testPublicPrimitiveInt;
		public long testPublicPrimitiveLong;
		public float testPublicPrimitiveFloat;
		public double testPublicPrimitiveDouble;
		public boolean testPublicPrimitiveBoolean;
		public char testPublicPrimitiveChar;

		public byte[] testPublicPrimitiveArrayByte;
		public short[] testPublicPrimitiveArrayShort;
		public int[] testPublicPrimitiveArrayInt;
		public long[] testPublicPrimitiveArrayLong;
		public float[] testPublicPrimitiveArrayFloat;
		public double[] testPublicPrimitiveArrayDouble;
		public boolean[] testPublicPrimitiveArrayBoolean;
		public char[] testPublicPrimitiveArrayChar;

		public Byte testPublicObjectByte;
		public Short testPublicObjectShort;
		public Integer testPublicObjectInt;
		public Long testPublicObjectLong;
		public Float testPublicObjectFloat;
		public Double testPublicObjectDouble;
		public Boolean testPublicObjectBoolean;
		public Character testPublicObjectChar;

		public Byte[] testPublicObjectArrayByte;
		public Short[] testPublicObjectArrayShort;
		public Integer[] testPublicObjectArrayInt;
		public Long[] testPublicObjectArrayLong;
		public Float[] testPublicObjectArrayFloat;
		public Double[] testPublicObjectArrayDouble;
		public Boolean[] testPublicObjectArrayBoolean;
		public Character[] testPublicObjectArrayChar;

		public Collection testPublicCollection;
		public List testPublicList;
		public Map testPublicMap;

		private byte testBeanPrimitiveByte;
		private short testBeanPrimitiveShort;
		private int testBeanPrimitiveInt;
		private long testBeanPrimitiveLong;
		private float testBeanPrimitiveFloat;
		private double testBeanPrimitiveDouble;
		private boolean testBeanPrimitiveBoolean;
		private char testBeanPrimitiveChar;

		private byte[] testBeanPrimitiveArrayByte;
		private short[] testBeanPrimitiveArrayShort;
		private int[] testBeanPrimitiveArrayInt;
		private long[] testBeanPrimitiveArrayLong;
		private float[] testBeanPrimitiveArrayFloat;
		private double[] testBeanPrimitiveArrayDouble;
		private boolean[] testBeanPrimitiveArrayBoolean;
		private char[] testBeanPrimitiveArrayChar;

		private Byte testBeanObjectByte;
		private Short testBeanObjectShort;
		private Integer testBeanObjectInt;
		private Long testBeanObjectLong;
		private Float testBeanObjectFloat;
		private Double testBeanObjectDouble;
		private Boolean testBeanObjectBoolean;
		private Character testBeanObjectChar;

		private Byte[] testBeanObjectArrayByte;
		private Short[] testBeanObjectArrayShort;
		private Integer[] testBeanObjectArrayInt;
		private Long[] testBeanObjectArrayLong;
		private Float[] testBeanObjectArrayFloat;
		private Double[] testBeanObjectArrayDouble;
		private Boolean[] testBeanObjectArrayBoolean;
		private Character[] testBeanObjectArrayChar;

		private Collection testBeanCollection;
		private List testBeanList;
		private Map testBeanMap;

		public byte getTestBeanPrimitiveByte() {
			return testBeanPrimitiveByte;
		}

		public void setTestBeanPrimitiveByte(byte testBeanPrimitiveByte) {
			this.testBeanPrimitiveByte = testBeanPrimitiveByte;
		}

		public short getTestBeanPrimitiveShort() {
			return testBeanPrimitiveShort;
		}

		public void setTestBeanPrimitiveShort(short testBeanPrimitiveShort) {
			this.testBeanPrimitiveShort = testBeanPrimitiveShort;
		}

		public int getTestBeanPrimitiveInt() {
			return testBeanPrimitiveInt;
		}

		public void setTestBeanPrimitiveInt(int testBeanPrimitiveInt) {
			this.testBeanPrimitiveInt = testBeanPrimitiveInt;
		}

		public long getTestBeanPrimitiveLong() {
			return testBeanPrimitiveLong;
		}

		public void setTestBeanPrimitiveLong(long testBeanPrimitiveLong) {
			this.testBeanPrimitiveLong = testBeanPrimitiveLong;
		}

		public float getTestBeanPrimitiveFloat() {
			return testBeanPrimitiveFloat;
		}

		public void setTestBeanPrimitiveFloat(float testBeanPrimitiveFloat) {
			this.testBeanPrimitiveFloat = testBeanPrimitiveFloat;
		}

		public double getTestBeanPrimitiveDouble() {
			return testBeanPrimitiveDouble;
		}

		public void setTestBeanPrimitiveDouble(double testBeanPrimitiveDouble) {
			this.testBeanPrimitiveDouble = testBeanPrimitiveDouble;
		}

		public boolean isTestBeanPrimitiveBoolean() {
			return testBeanPrimitiveBoolean;
		}

		public void setTestBeanPrimitiveBoolean(boolean testBeanPrimitiveBoolean) {
			this.testBeanPrimitiveBoolean = testBeanPrimitiveBoolean;
		}

		public char getTestBeanPrimitiveChar() {
			return testBeanPrimitiveChar;
		}

		public void setTestBeanPrimitiveChar(char testBeanPrimitiveChar) {
			this.testBeanPrimitiveChar = testBeanPrimitiveChar;
		}

		public byte[] getTestBeanPrimitiveArrayByte() {
			return testBeanPrimitiveArrayByte;
		}

		public void setTestBeanPrimitiveArrayByte(byte[] testBeanPrimitiveArrayByte) {
			this.testBeanPrimitiveArrayByte = testBeanPrimitiveArrayByte;
		}

		public short[] getTestBeanPrimitiveArrayShort() {
			return testBeanPrimitiveArrayShort;
		}

		public void setTestBeanPrimitiveArrayShort(short[] testBeanPrimitiveArrayShort) {
			this.testBeanPrimitiveArrayShort = testBeanPrimitiveArrayShort;
		}

		public int[] getTestBeanPrimitiveArrayInt() {
			return testBeanPrimitiveArrayInt;
		}

		public void setTestBeanPrimitiveArrayInt(int[] testBeanPrimitiveArrayInt) {
			this.testBeanPrimitiveArrayInt = testBeanPrimitiveArrayInt;
		}

		public long[] getTestBeanPrimitiveArrayLong() {
			return testBeanPrimitiveArrayLong;
		}

		public void setTestBeanPrimitiveArrayLong(long[] testBeanPrimitiveArrayLong) {
			this.testBeanPrimitiveArrayLong = testBeanPrimitiveArrayLong;
		}

		public float[] getTestBeanPrimitiveArrayFloat() {
			return testBeanPrimitiveArrayFloat;
		}

		public void setTestBeanPrimitiveArrayFloat(float[] testBeanPrimitiveArrayFloat) {
			this.testBeanPrimitiveArrayFloat = testBeanPrimitiveArrayFloat;
		}

		public double[] getTestBeanPrimitiveArrayDouble() {
			return testBeanPrimitiveArrayDouble;
		}

		public void setTestBeanPrimitiveArrayDouble(double[] testBeanPrimitiveArrayDouble) {
			this.testBeanPrimitiveArrayDouble = testBeanPrimitiveArrayDouble;
		}

		public boolean[] getTestBeanPrimitiveArrayBoolean() {
			return testBeanPrimitiveArrayBoolean;
		}

		public void setTestBeanPrimitiveArrayBoolean(boolean[] testBeanPrimitiveArrayBoolean) {
			this.testBeanPrimitiveArrayBoolean = testBeanPrimitiveArrayBoolean;
		}

		public char[] getTestBeanPrimitiveArrayChar() {
			return testBeanPrimitiveArrayChar;
		}

		public void setTestBeanPrimitiveArrayChar(char[] testBeanPrimitiveArrayChar) {
			this.testBeanPrimitiveArrayChar = testBeanPrimitiveArrayChar;
		}

		public Byte getTestBeanObjectByte() {
			return testBeanObjectByte;
		}

		public void setTestBeanObjectByte(Byte testBeanObjectByte) {
			this.testBeanObjectByte = testBeanObjectByte;
		}

		public Short getTestBeanObjectShort() {
			return testBeanObjectShort;
		}

		public void setTestBeanObjectShort(Short testBeanObjectShort) {
			this.testBeanObjectShort = testBeanObjectShort;
		}

		public Integer getTestBeanObjectInt() {
			return testBeanObjectInt;
		}

		public void setTestBeanObjectInt(Integer testBeanObjectInt) {
			this.testBeanObjectInt = testBeanObjectInt;
		}

		public Long getTestBeanObjectLong() {
			return testBeanObjectLong;
		}

		public void setTestBeanObjectLong(Long testBeanObjectLong) {
			this.testBeanObjectLong = testBeanObjectLong;
		}

		public Float getTestBeanObjectFloat() {
			return testBeanObjectFloat;
		}

		public void setTestBeanObjectFloat(Float testBeanObjectFloat) {
			this.testBeanObjectFloat = testBeanObjectFloat;
		}

		public Double getTestBeanObjectDouble() {
			return testBeanObjectDouble;
		}

		public void setTestBeanObjectDouble(Double testBeanObjectDouble) {
			this.testBeanObjectDouble = testBeanObjectDouble;
		}

		public Boolean getTestBeanObjectBoolean() {
			return testBeanObjectBoolean;
		}

		public void setTestBeanObjectBoolean(Boolean testBeanObjectBoolean) {
			this.testBeanObjectBoolean = testBeanObjectBoolean;
		}

		public Character getTestBeanObjectChar() {
			return testBeanObjectChar;
		}

		public void setTestBeanObjectChar(Character testBeanObjectChar) {
			this.testBeanObjectChar = testBeanObjectChar;
		}

		public Byte[] getTestBeanObjectArrayByte() {
			return testBeanObjectArrayByte;
		}

		public void setTestBeanObjectArrayByte(Byte[] testBeanObjectArrayByte) {
			this.testBeanObjectArrayByte = testBeanObjectArrayByte;
		}

		public Short[] getTestBeanObjectArrayShort() {
			return testBeanObjectArrayShort;
		}

		public void setTestBeanObjectArrayShort(Short[] testBeanObjectArrayShort) {
			this.testBeanObjectArrayShort = testBeanObjectArrayShort;
		}

		public Integer[] getTestBeanObjectArrayInt() {
			return testBeanObjectArrayInt;
		}

		public void setTestBeanObjectArrayInt(Integer[] testBeanObjectArrayInt) {
			this.testBeanObjectArrayInt = testBeanObjectArrayInt;
		}

		public Long[] getTestBeanObjectArrayLong() {
			return testBeanObjectArrayLong;
		}

		public void setTestBeanObjectArrayLong(Long[] testBeanObjectArrayLong) {
			this.testBeanObjectArrayLong = testBeanObjectArrayLong;
		}

		public Float[] getTestBeanObjectArrayFloat() {
			return testBeanObjectArrayFloat;
		}

		public void setTestBeanObjectArrayFloat(Float[] testBeanObjectArrayFloat) {
			this.testBeanObjectArrayFloat = testBeanObjectArrayFloat;
		}

		public Double[] getTestBeanObjectArrayDouble() {
			return testBeanObjectArrayDouble;
		}

		public void setTestBeanObjectArrayDouble(Double[] testBeanObjectArrayDouble) {
			this.testBeanObjectArrayDouble = testBeanObjectArrayDouble;
		}

		public Boolean[] getTestBeanObjectArrayBoolean() {
			return testBeanObjectArrayBoolean;
		}

		public void setTestBeanObjectArrayBoolean(Boolean[] testBeanObjectArrayBoolean) {
			this.testBeanObjectArrayBoolean = testBeanObjectArrayBoolean;
		}

		public Character[] getTestBeanObjectArrayChar() {
			return testBeanObjectArrayChar;
		}

		public void setTestBeanObjectArrayChar(Character[] testBeanObjectArrayChar) {
			this.testBeanObjectArrayChar = testBeanObjectArrayChar;
		}

		public Collection getTestBeanCollection() {
			return testBeanCollection;
		}

		public void setTestBeanCollection(Collection testBeanCollection) {
			this.testBeanCollection = testBeanCollection;
		}

		public List getTestBeanList() {
			return testBeanList;
		}

		public void setTestBeanList(List testBeanList) {
			this.testBeanList = testBeanList;
		}

		public Map getTestBeanMap() {
			return testBeanMap;
		}

		public void setTestBeanMap(Map testBeanMap) {
			this.testBeanMap = testBeanMap;
		}
	}

}
