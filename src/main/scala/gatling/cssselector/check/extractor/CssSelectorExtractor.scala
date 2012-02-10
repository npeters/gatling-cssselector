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
package gatling.cssselector.check.extractor
import scala.collection.immutable.List
import com.excilys.ebi.gatling.core.check.extractor.Extractor
import jodd.lagarto.dom.Document
import jodd.lagarto.dom.LagartoDOMBuilder
import jodd.lagarto.dom.Node
import jodd.lagarto.dom.NodeSelector
import java.util.LinkedList
import com.excilys.ebi.gatling.core.util.StringHelper.EMPTY

class CssSelectorExtractor(content: String,occurence:Int,evalNode: Node => String) extends Extractor {
  val domBuilder: LagartoDOMBuilder = new LagartoDOMBuilder();
  val doc: Document = domBuilder.parse(content);
  val nodeSelector: NodeSelector = new NodeSelector(doc);

  def extract(expression: String): List[String] = {

    val selectedNodes: LinkedList[Node] = nodeSelector.select(expression);

    if (selectedNodes.isEmpty()) {
      return Nil
    };
    val result = evalNode(selectedNodes.get(Math.min(occurence,selectedNodes.size)));
    if (EMPTY.equals(result)) {
      Nil
    } else {
      List(result)
    }

  }
}