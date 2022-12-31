package io.github.jam01.xtrasonnet.document;

/*-
 * Copyright 2022 Jose Montoya.
 *
 * Licensed under the Elastic License 2.0; you may not use this file except in
 * compliance with the Elastic License 2.0.
 */

/* spring-framework copyright/notice, per Apache-2.0 § 4.c */
/*
 * Copyright 2002-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Changes made:
 * - Collected declared MimeTypes and MediaTypes
 * - Added CSV and Java MediaTypes
 * - Rename ALL as ANY
 */

/**
 * Collection of well known MediaTypes.
 *
 * @author Arjen Poutsma
 * @author Juergen Hoeller
 * @author Rossen Stoyanchev
 * @author Sebastien Deleuze
 * @author Kazuki Shimizu
 * @author Sam Brannen
 */
public class MediaTypes {
    /**
     * Public constant media type that includes all media ranges (i.e. "&#42;/&#42;").
     */
    public static final MediaType ANY;

    /**
     * A String equivalent of {@link MediaTypes#ANY}.
     */
    public static final String ANY_VALUE = "*/*";

    /**
     * Public constant media type for {@code application/atom+xml}.
     */
    public static final MediaType APPLICATION_ATOM_XML;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_ATOM_XML}.
     */
    public static final String APPLICATION_ATOM_XML_VALUE = "application/atom+xml";

    /**
     * Public constant media type for {@code application/cbor}.toString
     */
    public static final MediaType APPLICATION_CBOR;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_CBOR}.toString
     */
    public static final String APPLICATION_CBOR_VALUE = "application/cbor";

    /**
     * Public constant media type for {@code application/x-www-form-urlencoded}.
     */
    public static final MediaType APPLICATION_FORM_URLENCODED;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_FORM_URLENCODED}.
     */
    public static final String APPLICATION_FORM_URLENCODED_VALUE = "application/x-www-form-urlencoded";

    /**
     * Public constant media type for {@code application/json}.
     */
    public static final MediaType APPLICATION_JSON;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_JSON}.
     */
    public static final String APPLICATION_JSON_VALUE = "application/json";

    /**
     * Public constant media type for {@code application/octet-stream}.
     */
    public static final MediaType APPLICATION_OCTET_STREAM;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_OCTET_STREAM}.
     */
    public static final String APPLICATION_OCTET_STREAM_VALUE = "application/octet-stream";

    /**
     * Public constant media type for {@code application/pdf}.toString
     */
    public static final MediaType APPLICATION_PDF;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_PDF}.toString
     */
    public static final String APPLICATION_PDF_VALUE = "application/pdf";

    /**
     * Public constant media type for {@code application/problem+json}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7807#section-6.1">toString
     */
    public static final MediaType APPLICATION_PROBLEM_JSON;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_PROBLEM_JSON}.toString
     */
    public static final String APPLICATION_PROBLEM_JSON_VALUE = "application/problem+json";

    /**
     * Public constant media type for {@code application/problem+xml}.
     *
     * @see <a href="https://tools.ietf.org/html/rfc7807#section-6.2">toString
     */
    public static final MediaType APPLICATION_PROBLEM_XML;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_PROBLEM_XML}.toString
     */
    public static final String APPLICATION_PROBLEM_XML_VALUE = "application/problem+xml";

    /**
     * Public constant media type for {@code application/rss+xml}.toString
     */
    public static final MediaType APPLICATION_RSS_XML;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_RSS_XML}.toString
     */
    public static final String APPLICATION_RSS_XML_VALUE = "application/rss+xml";

    /**
     * Public constant media type for {@code application/stream+json}.toString
     */
    public static final MediaType APPLICATION_STREAM_JSON;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_STREAM_JSON}.toString
     */
    public static final String APPLICATION_STREAM_JSON_VALUE = "application/stream+json";

    /**
     * Public constant media type for {@code application/xhtml+xml}.
     */
    public static final MediaType APPLICATION_XHTML_XML;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_XHTML_XML}.
     */
    public static final String APPLICATION_XHTML_XML_VALUE = "application/xhtml+xml";

    /**
     * Public constant media type for {@code application/xml}.
     */
    public static final MediaType APPLICATION_XML;

    /**
     * A String equivalent of {@link MediaTypes#APPLICATION_XML}.
     */
    public static final String APPLICATION_XML_VALUE = "application/xml";

    /**
     * Public constant media type for {@code image/gif}.
     */
    public static final MediaType IMAGE_GIF;

    /**
     * A String equivalent of {@link MediaTypes#IMAGE_GIF}.
     */
    public static final String IMAGE_GIF_VALUE = "image/gif";

    /**
     * Public constant media type for {@code image/jpeg}.
     */
    public static final MediaType IMAGE_JPEG;

    /**
     * A String equivalent of {@link MediaTypes#IMAGE_JPEG}.
     */
    public static final String IMAGE_JPEG_VALUE = "image/jpeg";

    /**
     * Public constant media type for {@code image/png}.
     */
    public static final MediaType IMAGE_PNG;

    /**
     * A String equivalent of {@link MediaTypes#IMAGE_PNG}.
     */
    public static final String IMAGE_PNG_VALUE = "image/png";

    /**
     * Public constant media type for {@code multipart/form-data}.
     */
    public static final MediaType MULTIPART_FORM_DATA;

    /**
     * A String equivalent of {@link MediaTypes#MULTIPART_FORM_DATA}.
     */
    public static final String MULTIPART_FORM_DATA_VALUE = "multipart/form-data";

    /**
     * Public constant media type for {@code multipart/mixed}.toString
     */
    public static final MediaType MULTIPART_MIXED;

    /**
     * A String equivalent of {@link MediaTypes#MULTIPART_MIXED}.toString
     */
    public static final String MULTIPART_MIXED_VALUE = "multipart/mixed";

    /**
     * Public constant media type for {@code multipart/related}.toString
     */
    public static final MediaType MULTIPART_RELATED;

    /**
     * A String equivalent of {@link MediaTypes#MULTIPART_RELATED}.toString
     */
    public static final String MULTIPART_RELATED_VALUE = "multipart/related";

    /**
     * Public constant media type for {@code text/event-stream}.
     *toString
     */
    public static final MediaType TEXT_EVENT_STREAM;

    /**
     * A String equivalent of {@link MediaTypes#TEXT_EVENT_STREAM}.toString
     */
    public static final String TEXT_EVENT_STREAM_VALUE = "text/event-stream";

    /**
     * Public constant media type for {@code text/html}.
     */
    public static final MediaType TEXT_HTML;

    /**
     * A String equivalent of {@link MediaTypes#TEXT_HTML}.
     */
    public static final String TEXT_HTML_VALUE = "text/html";

    /**
     * Public constant media type for {@code text/markdown}.toString
     */
    public static final MediaType TEXT_MARKDOWN;

    /**
     * A String equivalent of {@link MediaTypes#TEXT_MARKDOWN}.toString
     */
    public static final String TEXT_MARKDOWN_VALUE = "text/markdown";

    /**
     * Public constant media type for {@code text/plain}.
     */
    public static final MediaType TEXT_PLAIN;

    /**
     * A String equivalent of {@link MediaTypes#TEXT_PLAIN}.
     */
    public static final String TEXT_PLAIN_VALUE = "text/plain";

    /**
     * Public constant media type for {@code text/xml}.
     */
    public static final MediaType TEXT_XML;

    /**
     * A String equivalent of {@link MediaTypes#TEXT_XML}.
     */
    public static final String TEXT_XML_VALUE = "text/xml";

    public static final MediaType APPLICATION_JAVA;

    public static final String APPLICATION_JAVA_VALUE = "application/x-java-object";

    public static final MediaType TEXT_CSV;

    public static final String TEXT_CSV_VALUE = "text/csv";

    // See Null Object pattern
    /**
     * Public constant media type for representing an unknown content type. This is meant to used to signal to xtrasonnet
     * that the content type of a given input is unknown at design time. xtrasonnet may, for example, look in the header
     * or default to a particular MediaType. Using this MediaType should be avoided outside of interactions with xtrasonnet
     */
    public static final MediaType UNKNOWN;

    public static final String UNKNOWN_VALUE = "unknown/unknown";

    public static final String PARAM_CHARSET = "charset";

    public static final String PARAM_QUALITY_FACTOR = "q";

    static {
        // Not using "valueOf' to avoid static init cost
        ANY = new MediaType("*", "*");
        APPLICATION_ATOM_XML = new MediaType("application", "atom+xml");
        APPLICATION_CBOR = new MediaType("application", "cbor");
        APPLICATION_FORM_URLENCODED = new MediaType("application", "x-www-form-urlencoded");
        APPLICATION_JSON = new MediaType("application", "json");
        APPLICATION_OCTET_STREAM = new MediaType("application", "octet-stream");
        APPLICATION_PDF = new MediaType("application", "pdf");
        APPLICATION_PROBLEM_JSON = new MediaType("application", "problem+json");
        APPLICATION_PROBLEM_XML = new MediaType("application", "problem+xml");
        APPLICATION_RSS_XML = new MediaType("application", "rss+xml");
        APPLICATION_STREAM_JSON = new MediaType("application", "stream+json");
        APPLICATION_XHTML_XML = new MediaType("application", "xhtml+xml");
        APPLICATION_XML = new MediaType("application", "xml");
        IMAGE_GIF = new MediaType("image", "gif");
        IMAGE_JPEG = new MediaType("image", "jpeg");
        IMAGE_PNG = new MediaType("image", "png");
        MULTIPART_FORM_DATA = new MediaType("multipart", "form-data");
        MULTIPART_MIXED = new MediaType("multipart", "mixed");
        MULTIPART_RELATED = new MediaType("multipart", "related");
        TEXT_EVENT_STREAM = new MediaType("text", "event-stream");
        TEXT_HTML = new MediaType("text", "html");
        TEXT_MARKDOWN = new MediaType("text", "markdown");
        TEXT_PLAIN = new MediaType("text", "plain");
        TEXT_XML = new MediaType("text", "xml");
        APPLICATION_JAVA = new MediaType("application", "x-java-object");
        TEXT_CSV = new MediaType("text", "csv");
        UNKNOWN = new MediaType("unknown", "unknown");
    }
}
