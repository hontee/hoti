package com.hoti.site.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 属性实体
 * 
 * @author larry.qi
 *
 */
public class Props implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Props.Entry> entry;

	/**
	 * Gets the value of the entry property.
	 */
	public List<Props.Entry> getEntry() {
		if (entry == null) {
			entry = new ArrayList<>();
		}
		return this.entry;
	}
	
	/**
	 * Adds the entry property.
	 * @param key
	 * @param value
	 */
	public void addEntry(String key, Object value) {
		Props.Entry entry = new Props.Entry();
		entry.setKey(key);
		entry.setValue(value);
		this.getEntry().add(entry);
	}

	/**
	 * 实体
	 * @author larry.qi
	 */
	public static class Entry implements Serializable {

		private static final long serialVersionUID = 1L;

		protected String key;
		protected Object value;

		/**
		 * Gets the value of the key property.
		 *
		 * @return possible object is {@link Object }
		 *
		 */
		public String getKey() {
			return key;
		}

		/**
		 * Sets the value of the key property.
		 *
		 * @param value allowed object is {@link Object }
		 */
		public void setKey(String value) {
			this.key = value;
		}

		/**
		 * Gets the value of the value property.
		 *
		 * @return possible object is {@link Object }
		 *
		 */
		public Object getValue() {
			return value;
		}

		/**
		 * Sets the value of the value property.
		 *
		 * @param value allowed object is {@link Object }
		 *
		 */
		public void setValue(Object value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Props.Entry) {
				Props.Entry inObj = (Props.Entry) obj;
				return key.equals(inObj.getKey());
			}
			return false;
		}

		@Override
		public int hashCode() {
			int result = key != null ? key.hashCode() : 0;
			result = 31 * result + (value != null ? value.hashCode() : 0);
			return result;
		}
	}
}