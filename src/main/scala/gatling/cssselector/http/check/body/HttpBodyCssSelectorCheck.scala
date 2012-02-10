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
import com.excilys.ebi.gatling.http.check.HttpCheck
import com.excilys.ebi.gatling.http.request.HttpPhase.CompletePageReceived
import com.excilys.ebi.gatling.core.check.CheckStrategy
import gatling.cssselector.http.check.body.extractor.HttpBodyCssSelectorExtractorFactory
import jodd.lagarto.dom.Node

class HttpBodyCssSelectorCheck(what: Session => String, occurrence: Option[Int],evalNode:Node => String, strategy: CheckStrategy, expected: List[Session => String], saveAs: Option[String])
		extends HttpCheck(what, new HttpBodyCssSelectorExtractorFactory(occurrence,evalNode), strategy, expected, saveAs, CompletePageReceived) {
}