<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns="http://da-ra.de/schema/kernel-4">
  <xsl:output indent="yes"/>
  <xsl:strip-space elements="*"/>

  <xsl:param name="dvnJson"/>

  <xsl:mode on-no-match="shallow-copy"/>

  <xsl:template name="initialTemplate">
    <xsl:apply-templates select="json-to-xml($dvnJson)"/>
  </xsl:template>

  <xsl:template match="/" xpath-default-namespace="http://www.w3.org/2005/xpath-functions">
    <resource>
      <resourceType>Collection</resourceType>
      <xsl:call-template name="resourceIdentifier"/>
      <xsl:call-template name="titles"/>
      <xsl:call-template name="creators"/>
    </resource>
  </xsl:template>

  <xsl:template name="resourceIdentifier">
    <resourceIdentifier>
      <identifier>INDENTIFIER</identifier>
      <currentVersion>1.0</currentVersion>
    </resourceIdentifier>
  </xsl:template>
  <xsl:template name="titles" match="." xpath-default-namespace="http://www.w3.org/2005/xpath-functions">
    <titles>
      <title>
        <language>en</language>
        <titleName><xsl:value-of select="//array[@key='fields']/map[1]/string[@key='value']/."/></titleName>
      </title>
    </titles>
  </xsl:template>
  <xsl:template name="creators" match="." xpath-default-namespace="http://www.w3.org/2005/xpath-functions">
    <creators>
      <creator>
        <person>
          <xsl:variable name="name" select="tokenize(//array[1]/map[3]/array[1]/map[1]/map[1]/string[3], ',')"/>
          <firstName><xsl:value-of select="$name[2]"/></firstName>
          <lastName><xsl:value-of select="$name[1]"/></lastName>
        </person>
      </creator>
      <creator>
        <institution>
          <institutionName><xsl:value-of select="//array[1]/map[3]/array[1]/map[1]/map[2]/string[3]"/></institutionName>
        </institution>
      </creator>
    </creators>
  </xsl:template>
</xsl:stylesheet>
