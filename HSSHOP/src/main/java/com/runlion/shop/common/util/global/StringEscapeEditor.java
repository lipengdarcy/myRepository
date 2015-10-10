package com.runlion.shop.common.util.global;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * @author zhaowei
 * @description
 * @create_time 2015-6-29 下午2:28:19
 */
public class StringEscapeEditor extends PropertyEditorSupport {

	private boolean escapeHTML;

	private boolean escapeJavaScript;

	private boolean escapeSQL;

	public StringEscapeEditor() {

		super();

	}

	/**
	 *
	 * @param escapeHTML
	 * @param escapeJavaScript
	 * @param escapeSQL
	 * @create_time 2015-6-29 下午2:28:19
	 * @create_user zhaowei
	 * @whattodo 防止XSS、SQL注入攻击
	 * @modify_time like:date1/date2
	 * @modify_user like:user1/user2
	 * @modify_content like:content1/content2
	 */
	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript,
			boolean escapeSQL) {

		super();

		this.escapeHTML = escapeHTML;

		this.escapeJavaScript = escapeJavaScript;

		this.escapeSQL = escapeSQL;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	public void setAsText(String text) {

		if (text == null) {

			setValue(null);

		} else {

			String value = text;
			if (escapeHTML) {

				value = StringEscapeUtils.escapeHtml(value);

			}
			if (escapeJavaScript) {

				value = StringEscapeUtils.escapeJavaScript(value);

			}
			if (escapeSQL) {

				value = StringEscapeUtils.escapeSql(value);

			}
			setValue(value);

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	public String getAsText() {

		Object value = getValue();
		return value != null ? value.toString() : "";

	}

}