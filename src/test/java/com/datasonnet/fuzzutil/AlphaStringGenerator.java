package com.datasonnet.fuzzutil;

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
/*-
 * Changes:
 * - Copied source of original super class AbstractStringGenerator from junit-quickcheck
 */

/* JQF copyright/notice, per BSD-2-Clause § 1  */
/*-
 * Copyright (c) 2017-2018 The Regents of the University of California
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * 1. Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/* junit-quickcheck copyright/notice, per MIT */
/*
 The MIT License

 Copyright (c) 2010-2021 Paul R. Holser, Jr.

 Permission is hereby granted, free of charge, to any person obtaining
 a copy of this software and associated documentation files (the
 "Software"), to deal in the Software without restriction, including
 without limitation the rights to use, copy, modify, merge, publish,
 distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to
 the following conditions:

 The above copyright notice and this permission notice shall be
 included in all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.internal.Lists;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.pholser.junit.quickcheck.internal.Lists.shrinksOfOneItem;
import static com.pholser.junit.quickcheck.internal.Sequences.halving;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;

/**
 * @author Rohan Padhye
 */
public class AlphaStringGenerator extends Generator<String> {

    protected int nextCodePoint(SourceOfRandomness sourceOfRandomness) {
        return sourceOfRandomness.nextByte((byte) 'a', (byte) 'z');
    }

    protected boolean codePointInRange(int i) {
        return i >= 'a' && i <= 'z';
    }

    protected AlphaStringGenerator() {
        super(String.class);
    }

    @Override
    public String generate(SourceOfRandomness random, GenerationStatus status) {
        int[] codePoints = new int[status.size()];

        for (int i = 0; i < codePoints.length; ++i)
            codePoints[i] = nextCodePoint(random);

        return new String(codePoints, 0, codePoints.length);
    }

    @Override
    public boolean canShrink(Object larger) {
        return super.canShrink(larger) && codePointsInRange((String) larger);
    }

    @Override
    public List<String> doShrink(SourceOfRandomness random, String larger) {
        List<String> shrinks = new ArrayList<>();

        List<Integer> codePoints = larger.codePoints().boxed().collect(toList());
        shrinks.addAll(removals(codePoints));

        List<List<Integer>> oneItemShrinks =
                shrinksOfOneItem(random, codePoints, new CodePointShrink(this::codePointInRange));
        shrinks.addAll(oneItemShrinks.stream()
                .map(this::convert)
                .filter(this::codePointsInRange)
                .collect(toList()));

        return Collections.unmodifiableList(shrinks);
    }

    @Override
    public BigDecimal magnitude(Object value) {
        return BigDecimal.valueOf(narrow(value).length());
    }


    private boolean codePointsInRange(String s) {
        return s.codePoints().allMatch(this::codePointInRange);
    }

    private List<String> removals(List<Integer> codePoints) {
        return stream(halving(codePoints.size()).spliterator(), false)
                .map(i -> Lists.removeFrom(codePoints, i))
                .flatMap(Collection::stream)
                .map(this::convert)
                .collect(toList());
    }

    private String convert(List<Integer> codePoints) {
        StringBuilder s = new StringBuilder();
        codePoints.forEach(s::appendCodePoint);
        return s.toString();
    }
}