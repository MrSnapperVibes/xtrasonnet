/**
 * Copyright 2022 Jose Montoya.
 *
 * Licensed under the Elastic License 2.0; you may not use this file except in
 * compliance with the Elastic License 2.0.
 */

/* datasonnet-mapper copyright retention, per Apache 2.0-4.c */
/**
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
{
    local PortXMapper = self,
    local defaultF = function(x) x,

    /*
     * Turns an array into an object, where the keys are the result of calling keyF on each value (which becomes the value at the key). If valueF is provided it gets run on the value.
     */
    mapToObject(arr, keyF, valueF=defaultF)::
        std.foldl(function(aggregate, x)
                     local keyX = keyF(x);
                     if (std.objectHas(aggregate, keyX)) then aggregate
                     else aggregate + { [keyX]: valueF(x) },
                  arr,
                  {}),
}