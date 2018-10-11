package nl.knaw.dans.dataverse.util;

import net.sf.saxon.s9api.SaxonApiException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DataverseJsonToDaraXml.class})
public class DataverseJsonToDaraXmlTest {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    @Before
    public void setUp() {
        PowerMockito.mockStatic(DataverseJsonToDaraXml.class);
    }

    @Test
    public void convert() throws IOException, SaxonApiException {
        final String xsltPath = "src/test/resources/dataverseJson-to-DaraXml.xsl";
        final String dvnJsonMetadataUrl = "https://act.dataverse.nl/api/datasets/:persistentId/versions/:draft?persistentId=hdl%3A10695%2FLBLSQZ&key=c42ca294-306e-4cf4-a8ae-18a45f83f9c1";
        when(DataverseJsonToDaraXml.convert(Mockito.anyString(), Mockito.anyString())).thenCallRealMethod();
        String result = DataverseJsonToDaraXml.convert(xsltPath, dvnJsonMetadataUrl);
        LOG.info("result: {}", result);
        Assert.assertNotNull(result);
    }
}