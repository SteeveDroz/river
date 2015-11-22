package com.github.steevedroz.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides useful operations on Strings.
 * 
 * @author Steeve Droz
 * 
 */
public final class StringUtils {
	/**
	 * This method makes a <code>String</code> with every elements of a list and
	 * separates them with whatever separator is given.<br />
	 * <br />
	 * <b>Example:</b><br />
	 * 
	 * <pre>
	 * List&lt;String&gt; elements = new ArrayList&lt;String&gt;();
	 * elements.add(&quot;Hello&quot;);
	 * elements.add(&quot;world,&quot;);
	 * elements.add(&quot;how&quot;);
	 * elements.add(&quot;are&quot;);
	 * elements.add(&quot;you?&quot;);
	 * String sentence = StringUtils.join(elements, &quot; &quot;);
	 * </pre>
	 * 
	 * The value of <code>sentence</code> is
	 * <code>"Hello world, how are you?"</code>.<br />
	 * <br />
	 * The reverse operation is {@link #split(String, String)}.
	 * 
	 * @param elements
	 *            The list of elements
	 * @param separator
	 *            The substring that will separate every element
	 * @return A string containing all the elements
	 */
	public static String join(List<?> elements, String separator) {
		if (elements == null) {
			return null;
		}
		StringBuilder str = new StringBuilder();

		for (int i = 0; i < elements.size(); i++) {
			if (i > 0) {
				str.append(separator);
			}
			str.append(elements.get(i).toString());
		}

		return str.toString();
	}

	/**
	 * This method takes a string and splits it in substring each time the
	 * <code>separator</code> is met.<br />
	 * <br />
	 * This method is very similar (and in fact uses)
	 * {@link String#split(String)}, except the return value is a
	 * <code>List<String></code> instead of a <code>String[]</code>.<br />
	 * <br />
	 * The reverse operation is {@link #join(List, String)}.
	 * 
	 * @param source
	 *            The string to split
	 * @param separator
	 *            The regex separator used to split it
	 * @return A list of strings
	 */
	public static List<String> split(String source, String separator) {
		if (source == null)
			return null;
		String[] elements = source.split(separator);
		List<String> stringElements = new ArrayList<String>();
		for (String element : elements) {
			stringElements.add(element);
		}
		return stringElements;
	}
}
