/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package gatling.cssselector.http.check.body

import com.excilys.ebi.gatling.core.session.Session
import com.excilys.ebi.gatling.core.util.StringHelper.interpolate
import com.excilys.ebi.gatling.http.check.HttpCheckBuilder
import com.excilys.ebi.gatling.http.request.HttpPhase.{ HttpPhase, CompletePageReceived }
import com.excilys.ebi.gatling.core.check.CheckBuilderSave
import com.excilys.ebi.gatling.core.check.CheckBuilderVerify
import com.excilys.ebi.gatling.core.check.CheckBuilderFind
import com.excilys.ebi.gatling.core.check.CheckBuilderVerifyAll
import com.excilys.ebi.gatling.core.check.CheckBuilderVerifyOne
import com.excilys.ebi.gatling.core.check.CheckStrategy
import jodd.lagarto.dom.Node

object HttpBodyCssSelectorCheckBuilder {
	
  def innerHtml(n:Node):String = {
		n.getInnerHtml()
  }
  
  def value(n:Node):String = {
		n.getAttribute("value")
  }
  
  /**
	 *
	 */
	def cssSelector(what: Session => String,evalNode:Node => String) = new HttpBodyCssSelectorCheckBuilder(what, Some(0),evalNode, CheckBuilderVerify.eq, Nil, None) with CheckBuilderFind[HttpCheckBuilder[HttpBodyCssSelectorCheckBuilder]]
	/**
	 *
	 */
	def cssSelector(expression: String,evalNode:Node => String): HttpBodyCssSelectorCheckBuilder with CheckBuilderFind[HttpCheckBuilder[HttpBodyCssSelectorCheckBuilder]] = cssSelector(interpolate(expression),evalNode)
}	

/**
 * This class builds a response body check based on XPath expressions
 *
 * @param what the function returning the expression representing what is to be checked
 * @param to the optional context key in which the extracted value will be stored
 * @param strategy the strategy used to check
 * @param expected the expected value against which the extracted value will be checked
 */
class HttpBodyCssSelectorCheckBuilder(what: Session => String, occurrence: Option[Int],evalNode:Node => String,  strategy: CheckStrategy, expected: List[Session => String], saveAs: Option[String])
		extends HttpCheckBuilder[HttpBodyCssSelectorCheckBuilder](what, occurrence, strategy, expected, saveAs, CompletePageReceived) {

	def newInstance(what: Session => String, occurrence: Option[Int], strategy: CheckStrategy, expected: List[Session => String], saveAs: Option[String], when: HttpPhase) =
		new HttpBodyCssSelectorCheckBuilder(what, occurrence,evalNode, strategy, expected, saveAs)

	def newInstanceWithFindOne(occurrence: Int) =
		new HttpBodyCssSelectorCheckBuilder(what, Some(occurrence),evalNode, strategy, expected, saveAs) with CheckBuilderVerifyOne[HttpCheckBuilder[HttpBodyCssSelectorCheckBuilder]]

	def newInstanceWithFindAll =
		new HttpBodyCssSelectorCheckBuilder(what, None,evalNode, strategy, expected, saveAs) with CheckBuilderVerifyAll[HttpCheckBuilder[HttpBodyCssSelectorCheckBuilder]]

	def newInstanceWithVerify(strategy: CheckStrategy, expected: List[Session => String] = Nil) =
		new HttpBodyCssSelectorCheckBuilder(what, occurrence,evalNode, strategy, expected, saveAs) with CheckBuilderSave[HttpCheckBuilder[HttpBodyCssSelectorCheckBuilder]]

	def build = new HttpBodyCssSelectorCheck(what, occurrence,evalNode, strategy, expected, saveAs)
}
