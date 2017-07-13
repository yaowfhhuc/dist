/*
 * This file is part of "JTA - Telnet/SSH for the JAVA(tm) platform".
 *
 * (c) Matthias L. Jugel, Marcus Meißner 1996-2005. All Rights Reserved.
 *
 * Please visit http://javatelnet.org/ for updates and contact.
 *
 * --LICENSE NOTICE--
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 * --LICENSE NOTICE--
 *
 */
package me.test.util.unix.prococol.telnet;

import java.util.regex.Pattern;

/**
 * A script handler, that tries to match strings and returns true when it found
 * the string it searched for.
 * <P>
 * <B>Maintainer:</B> Matthias L. Jugel
 * 
 * @version $Id: ScriptHandler.java 499 2005-09-29 08:24:54Z leo $
 * @author Matthias L. Jugel, Marcus Mei�ner
 */
/**
 * A script handler, that tries to match strings and returns true when it found
 * the string it searched for.
 * <P>
 * <B>Maintainer:</B> Matthias L. Jugel
 * 
 * @version $Id: ScriptHandler.java 499 2005-09-29 08:24:54Z leo $
 * @author Matthias L. Jugel, Marcus Mei锟絥er
 */
public class ScriptHandler {
	/** debugging level */
	@SuppressWarnings("unused")
	private final static int debug = 0;

	private String[] exactMatchs = null;

	private String[] regexMatchs = null;

	/**
	 * 设置精确查找条件, 可以是多个字符串
	 * 
	 * @param match
	 */
	public void setupExactMatch(String[] matchs) {
		exactMatchs = matchs;
	}

	/**
	 * 设置正则表达式匹配条件
	 * 
	 * @param match
	 */
	public void setupRegexMatch(String[] matchs) {
		regexMatchs = matchs;
	}

	/**
	 * 进行匹配查找
	 * 
	 * @param haystack
	 * @return
	 */
	public boolean match(String haystack) {
		boolean found = false;
		if (exactMatchs != null && exactMatchs.length > 0) {
			for (String str : exactMatchs) {
				if (haystack.contains(str)) {
					// System.err.println("##### exact found prompt = "+ str );
					return true;
				}
			}
		}
		if (regexMatchs != null && regexMatchs.length > 0) {
			for (String pattern : regexMatchs) {
				Pattern p = Pattern.compile(pattern);
				if (p.matcher(haystack).lookingAt())
					return true;
			}
		}
		return found;
	}

}
