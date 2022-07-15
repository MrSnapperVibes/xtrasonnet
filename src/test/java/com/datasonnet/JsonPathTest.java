package com.datasonnet;

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

import com.datasonnet.document.DefaultDocument;
import com.datasonnet.document.MediaTypes;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonPathTest {

    @Test
    void testJsonPathSelector() throws Exception {
        String jsonData = TestUtils.resourceAsString("jsonPathTest.json");

        Mapper mapper = new Mapper("ds.jsonpath.select(payload, \"$..book[-2:]..author\")[0]");
        String mappedJson = mapper.transform(new DefaultDocument<String>(jsonData, MediaTypes.APPLICATION_JSON), Collections.emptyMap(), MediaTypes.APPLICATION_JSON).getContent();

        assertEquals(mappedJson, "\"Herman Melville\"");
    }

    @Test
    void testJsonPathArrSelector() throws Exception {
        String jsonData = TestUtils.resourceAsString("jsonPathArrTest.json");

        Mapper mapper = new Mapper("std.length(ds.jsonpath.select(payload, \"$..language[?(@.name == 'Java')]\")) > 0");
        String mappedJson = mapper.transform(new DefaultDocument<String>(jsonData, MediaTypes.APPLICATION_JSON), Collections.emptyMap(), MediaTypes.APPLICATION_JSON).getContent();

        assertEquals(mappedJson, "true");
    }

}
