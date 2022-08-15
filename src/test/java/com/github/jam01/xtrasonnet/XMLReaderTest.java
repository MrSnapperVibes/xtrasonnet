package com.github.jam01.xtrasonnet;

/*-
 * Copyright 2022 Jose Montoya.
 *
 * Licensed under the Elastic License 2.0; you may not use this file except in
 * compliance with the Elastic License 2.0.
 */

/* datasonnet-mapper copyright/notice, per Apache-2.0 § 4.c */
/*-
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.github.jam01.xtrasonnet.document.Document;
import com.github.jam01.xtrasonnet.document.MediaTypes;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;

public class XMLReaderTest {

    @Test
    void testNonAscii() throws Exception {
        mapAndAssert("xml/xmlNonAscii.xml", "xml/xmlNonAscii.json");
    }

    @Test
    void testOverrideNamespaces() throws Exception {
        String xml = "<a xmlns='http://example.com/1' xmlns:b='http://example.com/2'><b:b/></a>";
        // note how b is bound to the default namespace, which means the 'b' above needs to be auto-rebound

        String jsonnet = "/** DataSonnet\n" +
                "version=2.0\n" +
                "input payload application/xml;NamespaceDeclarations.b=\"http://example.com/1\"\n" +
                "*/\n" +
                "payload";

        Transformer transformer = new Transformer(jsonnet);


        String mapped = transformer.transform(Document.of(xml, MediaTypes.APPLICATION_XML), Collections.emptyMap(), MediaTypes.APPLICATION_JSON).getContent();

        // the b namespace must have been remapped
        assertThat(mapped, not(containsString("b:b")));
        // the default namespace must now be a
        assertThat(mapped, containsString("b:a"));

        // must include both namespaces
        assertThat(mapped, containsString("http://example.com/1"));
        assertThat(mapped, containsString("http://example.com/2"));
    }

    @Test
    void testXMLReaderExt() throws Exception {
        String xmlData = TestUtils.resourceAsString("xml/readXMLExtTest.xml");
        String jsonnet = TestUtils.resourceAsString("xml/readXMLExtTest.ds");
        String expectedJson = TestUtils.resourceAsString("xml/readXMLExtTest.json");

        Transformer transformer = new Transformer(jsonnet);


        String mappedJson = transformer.transform(Document.of(xmlData, MediaTypes.APPLICATION_XML.withParameter("badgerfish", "extended")), Collections.emptyMap(), MediaTypes.APPLICATION_JSON).getContent();

        JSONAssert.assertEquals(expectedJson, mappedJson, true);
    }

    @Test
    void nestedNamespaces() throws Exception {
        mapAndAssert("xml/xmlNestedNamespaces.xml", "xml/xmlNestedNamespaces.json");
    }

    @Test
    void testMixedContent() throws Exception {
        mapAndAssert("xml/xmlMixedContent.xml", "xml/xmlMixedContent.json");
    }

    @Test
    void testCDATA() throws Exception {
        mapAndAssert("xml/xmlCDATA.xml", "xml/xmlCDATA.json");
    }

    @Test
    void testMultipleCDATA() throws Exception {
        mapAndAssert("xml/xmlMultipleCDATA.xml", "xml/xmlMultipleCDATA.json");
    }

    private void mapAndAssert(String inputFileName, String expectedFileName) throws Exception {
        String xmlData = TestUtils.resourceAsString(inputFileName);
        String expectedJson = TestUtils.resourceAsString(expectedFileName);

        Transformer transformer = new Transformer("payload");

        String mappedJson = transformer.transform(Document.of(xmlData, MediaTypes.APPLICATION_XML.withParameter("badgerfish", "extended")), Collections.emptyMap(), MediaTypes.APPLICATION_JSON).getContent();
        JSONAssert.assertEquals(expectedJson, mappedJson, true);
    }

}
