/**
 * Copyright 2016 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.reactivex.internal.operators.completable;

import static org.junit.Assert.fail;

import org.junit.Test;

import io.reactivex.TestHelper;
import io.reactivex.processors.PublishProcessor;

public class CompletableAwaitTest {

    @Test
    public void emptyEnum() {
        TestHelper.assertEmptyEnum(CompletableAwait.class);
    }
    
    @Test
    public void awaitInterrupted() {
        
        Thread.currentThread().interrupt();
        
        try {
            PublishProcessor.create().toCompletable().blockingAwait();
            fail("Should have thrown RuntimeException");
        } catch (RuntimeException ex) {
            if (!(ex.getCause() instanceof InterruptedException)) {
                fail("Wrong cause: " + ex.getCause());
            }
        }
        
    }
}