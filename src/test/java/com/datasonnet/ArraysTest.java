package com.datasonnet;

/*-
 * Copyright 2022 Jose Montoya.
 *
 * Licensed under the Elastic License 2.0; you may not use this file except in
 * compliance with the Elastic License 2.0.
 */

/* datasonnet-mapper copyright/notice, per Apache-2.0 § 4.c */
/*-
 * Copyright 2019-2021 the original author or authors.
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

import org.junit.jupiter.api.Test;

import static com.datasonnet.TestUtils.transform;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArraysTest {
    @Test
    void testArrays_countBy() {
        assertEquals("3", transform("tro.arrays.countBy([1,2,3,4,5], function(it) it > 2)"));
    }

    @Test
    void testArrays_divideBy() {
        assertEquals("[[1,2],[3,4],[5]]", transform("tro.arrays.splitEvery([1,2,3,4,5], 2)"));
        assertEquals("[[1,2,3],[4,5]]", transform("tro.arrays.splitEvery([1,2,3,4,5], 3)"));
    }

    @Test
    void testArrays_drop() {
        assertEquals("[3,4,5]", transform("tro.arrays.drop([1,2,3,4,5], 2)"));
        assertEquals("[2,3,4,5]", transform("tro.arrays.drop([1,2,3,4,5], 1)"));
        assertEquals("[]", transform("tro.arrays.drop([1,2,3,4,5], 10)"));
    }

    @Test
    void testArrays_dropWhile() {
        assertEquals("[3,4,5]", transform("tro.arrays.dropWhile([1,2,3,4,5], function(item) item < 3)"));
    }

    @Test
    void testArrays_duplicates() {
        assertEquals("[1,2,3]", transform("tro.arrays.duplicates([1,2,3,4,5,3,2,1])"));
    }

    @Test
    void testArrays_every() {
        assertEquals("true", transform("tro.arrays.all([1,1,1], function(item) item == 1)"));
        assertEquals("false", transform("tro.arrays.all([1,2,1], function(item) item == 1)"));
    }

    @Test
    void testArrays_firstWith() {
        assertEquals("2", transform("tro.arrays.find([1,2,3], function(item) (item % 2) == 0)"));
        assertEquals("null", transform("tro.arrays.find([1,2,3], function(item) (item % 10) == 0)"));
    }

    @Test
    void testArrays_deepFlatten() {
        assertEquals("[1,2,3,1,2,null,a]", transform("tro.arrays.deepFlatten([[1,2,3,[1,2]], [null,'a']])"));
    }

    @Test
    void testArrays_indexOf() {
        assertEquals("2", transform("tro.arrays.indexOf([1,2,3,4,5,3], 3)"));
        assertEquals("2", transform("tro.arrays.indexOf(['Mariano', 'Leandro', 'Julian', 'Julian'], 'Julian')"));
        assertEquals("1", transform("tro.arrays.indexOf([1,2,3], 2)"));
        assertEquals("-1", transform("tro.arrays.indexOf([1,2,3], 5)"));
        assertEquals("1", transform("tro.arrays.indexOf([1,2,3,2], 2)"));
        assertEquals("2", transform("tro.arrays.indexOf('Hello', 'l')"));
        assertEquals("-1", transform("tro.arrays.indexOf('Hello', 'x')"));
    }

    @Test
    void testArrays_indexWhere() {
        assertEquals("2", transform("tro.arrays.indexWhere([1,2,3,4,5,3], function(item) item == 3)"));
        assertEquals("2", transform("tro.arrays.indexWhere(['Mariano', 'Leandro', 'Julian', 'Julian'], function(item) item == 'Julian')"));
    }

    @Test
    void testArrays_join() {
        assertEquals("[{l:{id:1,v:a},r:{id:1,v:c}},{l:{id:1,v:b},r:{id:1,v:c}}]",
                transform("tro.arrays.innerJoin([{'id':1,'v':'a'},{'id':1,'v':'b'}],[{'id':1,'v':'c'}], function(item) item.id, function(item) item.id)"));
    }

    @Test
    void testArrays_lastIndexOf() {
        assertEquals("1", transform("tro.arrays.lastIndexOf([1,2,3], 2)"));
        assertEquals("-1", transform("tro.arrays.lastIndexOf([1,2,3], 5)"));
        assertEquals("3", transform("tro.arrays.lastIndexOf([1,2,3,2], 2)"));
        assertEquals("3", transform("tro.arrays.lastIndexOf('Hello', 'l')"));
        assertEquals("-1", transform("tro.arrays.lastIndexOf('Hello', 'x')"));
    }

    @Test
    void testArrays_leftJoin() {
        assertEquals("[{l:{id:1,v:a},r:{id:1,v:c}},{l:{id:1,v:b},r:{id:1,v:c}},{l:{id:2,v:d}}]",
                transform("tro.arrays.leftJoin([{'id':1,'v':'a'},{'id':1,'v':'b'},{'id':2,'v':'d'}],[{'id':1,'v':'c'},{'id':3,'v':'e'}], function(item) item.id,function(item) item.id)"));
    }

    @Test
    void testArrays_occurrences() {
        assertEquals("{1:2,2:2,3:2,4:1,6:1}", transform("tro.arrays.occurrences([1,2,3,4,3,2,1,6], function(item) item)"));
    }

    @Test
    void testArrays_outerJoin() {
        assertEquals("[{l:{id:1,v:a},r:{id:1,v:c}},{l:{id:1,v:b},r:{id:1,v:c}},{l:{id:2,v:d}},{r:{id:3,v:e}}]",
                transform("tro.arrays.rightJoin([{'id':1,'v':'a'},{'id':1,'v':'b'},{'id':2,'v':'d'}],[{'id':1,'v':'c'},{'id':3,'v':'e'}], function(item) item.id,function(item) item.id)"));
    }

    @Test
    void testArrays_partition() {
        assertEquals("{success:[0,2,4],failure:[1,3,5]}", transform("tro.arrays.partition([0,1,2,3,4,5], function(item) ((item % 2) ==0) )"));
    }

    @Test
    void testArrays_slice() {
        long start = System.currentTimeMillis();
        assertEquals("[1,2,3,4]", transform("tro.arrays.slice([0,1,2,3,4,5], 1, 5)"));
        assertEquals("[1,2,3,3]", transform("tro.arrays.slice([0,1,2,3,3,3], 1, 5)"));
    }

    @Test
    void testArrays_some() {
        assertEquals("true", transform("tro.arrays.any([1,2,3], function(item) (item % 2) == 0)"));
        assertEquals("true", transform("tro.arrays.any([1,2,3], function(item) (item % 2) == 1)"));
        assertEquals("true", transform("tro.arrays.any([1,2,3], function(item) item == 3)"));
        assertEquals("false", transform("tro.arrays.any([1,2,3], function(item) item == 4)"));
    }

    @Test
    void testArrays_splitAt() {
        assertEquals("{l:[A,B],r:[C]}", transform("tro.arrays.splitAt(['A','B','C'], 2)"));
        assertEquals("{l:[A],r:[B,C]}", transform("tro.arrays.splitAt(['A','B','C'], 1)"));
    }

    @Test
    void testArrays_splitWhere() {
        assertEquals("{l:[A],r:[B,C,D]}", transform("tro.arrays.splitWhere(['A','B','C','D'], function(item) item=='B')"));
        assertEquals("{l:[A,B],r:[C,D]}", transform("tro.arrays.splitWhere(['A','B','C','D'], function(item) item=='C')"));
    }

    @Test
    void testArrays_sumBy() {
        assertEquals("6", transform("tro.arrays.sumBy([{a:1},{a:2},{a:3}], function(item) item.a)"));
    }

    @Test
    void testArrays_take() {
        assertEquals("[A,B]", transform("tro.arrays.take(['A','B','C'], 2)"));
    }

    @Test
    void testArrays_takeWhile() {
        assertEquals("[0,1]", transform("tro.arrays.takeWhile([0,1,2,1], function(item) item <= 1)"));
    }
}