package nl.knaw.dans.dataverse.util;

import net.sf.saxon.s9api.*;
import org.apache.commons.io.IOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @Author: Eko Indarto
 *
 */
public class DataverseJsonToDaraXml
{
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String INITIAL_TEMPLATE = "initialTemplate";
    private static final String PARAM_DVN_JSON = "dvnJson";
    public static String convert(String xsltSourceUrl, String dvnJsonMetadataSourceUrl) throws SaxonApiException, IOException {
        LOG.debug("xsltSourceUrl: {} \tdvnJsonMetadataSourceUrl: {}", xsltSourceUrl, dvnJsonMetadataSourceUrl);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Processor processor = new Processor(false);
        Serializer serializer = processor.newSerializer(baos);
        XsltCompiler compiler = processor.newXsltCompiler();
        XsltExecutable executable = compiler.compile(new StreamSource(xsltSourceUrl));
        XsltTransformer transformer = executable.load();
        transformer.setInitialTemplate(new QName(INITIAL_TEMPLATE));
        transformer.setParameter(new QName(PARAM_DVN_JSON), new XdmAtomicValue(IOUtils.toString(new URL(dvnJsonMetadataSourceUrl), StandardCharsets.UTF_8)));
        transformer.setDestination(serializer);
        transformer.transform();
        return baos.toString();
    }
}
