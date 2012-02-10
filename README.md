# Motivation
gatling-cssselector is an extension for the [Gatling](https://github.com/excilys/gatling) stress tool that provides CSS Selector extraction based on [CSSelly](http://jodd.org/doc/csselly/) contains in project [jodd](http://jodd.org).

gatling-cssselector is inspired of [gatling-vtd](https://github.com/excilys/gatling-vtd)

CSSelly is a Java implementation of the W3C Selectors Level 3 specification..

# Usage

## Step 1 : adding the dependency
### When using the Gatling bundle

Just drop the gatling-cssselector-&lt;version&gt;.jar into the lib directory.

### When using maven Maven

Use the following in you pom.xml :

``` xml
<repository>
	<id>excilys</id>
	<name>Excilys Repository</name>
	<url>http://repository.excilys.com/content/groups/public</url>
</repository>
<dependencies>
	<dependency>
		<groupId>gatling.cssselector</groupId>
		<artifactId>gatling-cssselector</artifactId>
		<version>1.0-SNAPSHOT</version>
	</dependency>
</dependencies>
```

## Step 2 : using in the scripts

If you use the txt format, the required imports are automatically registered.
If you use the scala format, add the following import :

    import gatling.cssselector.http.check.body.HttpBodyCssSelectorCheckBuilder._

This will let you use the new extractor builtins. The syntax is similar to the standard XPath one, just replace the prefix "xpath" with "vtdXpath". For example :

    check(cssSelector("#loginForm fieldset legend",innerHtml _).eq("Please log in"))
	
# Important notice : license

CSSelly use a BSD license.